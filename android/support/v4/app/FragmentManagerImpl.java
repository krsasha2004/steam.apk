package android.support.v4.app;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.FragmentManager.BackStackEntry;
import android.support.v4.util.DebugUtils;
import android.support.v4.util.LogWriter;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater.Factory;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.ScaleAnimation;
import com.valvesoftware.android.steam.community.C0151R;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

/* compiled from: FragmentManager */
final class FragmentManagerImpl extends FragmentManager implements Factory {
    static final Interpolator ACCELERATE_CUBIC;
    static final Interpolator ACCELERATE_QUINT;
    static boolean DEBUG;
    static final Interpolator DECELERATE_CUBIC;
    static final Interpolator DECELERATE_QUINT;
    static final boolean HONEYCOMB;
    ArrayList<Fragment> mActive;
    FragmentActivity mActivity;
    ArrayList<Fragment> mAdded;
    ArrayList<Integer> mAvailBackStackIndices;
    ArrayList<Integer> mAvailIndices;
    ArrayList<BackStackRecord> mBackStack;
    ArrayList<Object> mBackStackChangeListeners;
    ArrayList<BackStackRecord> mBackStackIndices;
    FragmentContainer mContainer;
    ArrayList<Fragment> mCreatedMenus;
    int mCurState;
    boolean mDestroyed;
    Runnable mExecCommit;
    boolean mExecutingActions;
    boolean mHavePendingDeferredStart;
    boolean mNeedMenuInvalidate;
    String mNoTransactionsBecause;
    Fragment mParent;
    ArrayList<Runnable> mPendingActions;
    SparseArray<Parcelable> mStateArray;
    Bundle mStateBundle;
    boolean mStateSaved;
    Runnable[] mTmpActions;

    /* renamed from: android.support.v4.app.FragmentManagerImpl.1 */
    class FragmentManager implements Runnable {
        FragmentManager() {
        }

        public void run() {
            FragmentManagerImpl.this.execPendingActions();
        }
    }

    /* renamed from: android.support.v4.app.FragmentManagerImpl.2 */
    class FragmentManager implements Runnable {
        FragmentManager() {
        }

        public void run() {
            FragmentManagerImpl.this.popBackStackState(FragmentManagerImpl.this.mActivity.mHandler, null, -1, 0);
        }
    }

    /* renamed from: android.support.v4.app.FragmentManagerImpl.3 */
    class FragmentManager implements Runnable {
        final /* synthetic */ int val$flags;
        final /* synthetic */ String val$name;

        FragmentManager(String str, int i) {
            this.val$name = str;
            this.val$flags = i;
        }

        public void run() {
            FragmentManagerImpl.this.popBackStackState(FragmentManagerImpl.this.mActivity.mHandler, this.val$name, -1, this.val$flags);
        }
    }

    /* renamed from: android.support.v4.app.FragmentManagerImpl.5 */
    class FragmentManager implements AnimationListener {
        final /* synthetic */ Fragment val$fragment;

        FragmentManager(Fragment fragment) {
            this.val$fragment = fragment;
        }

        public void onAnimationEnd(Animation animation) {
            if (this.val$fragment.mAnimatingAway != null) {
                this.val$fragment.mAnimatingAway = null;
                FragmentManagerImpl.this.moveToState(this.val$fragment, this.val$fragment.mStateAfterAnimating, 0, 0, false);
            }
        }

        public void onAnimationRepeat(Animation animation) {
        }

        public void onAnimationStart(Animation animation) {
        }
    }

    /* compiled from: FragmentManager */
    static class FragmentTag {
        public static final int[] Fragment;

        static {
            Fragment = new int[]{16842755, 16842960, 16842961};
        }
    }

    FragmentManagerImpl() {
        this.mCurState = 0;
        this.mStateBundle = null;
        this.mStateArray = null;
        this.mExecCommit = new FragmentManager();
    }

    static {
        boolean z = false;
        DEBUG = false;
        if (VERSION.SDK_INT >= 11) {
            z = true;
        }
        HONEYCOMB = z;
        DECELERATE_QUINT = new DecelerateInterpolator(2.5f);
        DECELERATE_CUBIC = new DecelerateInterpolator(1.5f);
        ACCELERATE_QUINT = new AccelerateInterpolator(2.5f);
        ACCELERATE_CUBIC = new AccelerateInterpolator(1.5f);
    }

    private void throwException(RuntimeException ex) {
        Log.e("FragmentManager", ex.getMessage());
        Log.e("FragmentManager", "Activity state:");
        PrintWriter pw = new PrintWriter(new LogWriter("FragmentManager"));
        if (this.mActivity != null) {
            try {
                this.mActivity.dump("  ", null, pw, new String[0]);
            } catch (Exception e) {
                Log.e("FragmentManager", "Failed dumping state", e);
            }
        } else {
            try {
                dump("  ", null, pw, new String[0]);
            } catch (Exception e2) {
                Log.e("FragmentManager", "Failed dumping state", e2);
            }
        }
        throw ex;
    }

    public FragmentTransaction beginTransaction() {
        return new BackStackRecord(this);
    }

    public boolean executePendingTransactions() {
        return execPendingActions();
    }

    public void popBackStack() {
        enqueueAction(new FragmentManager(), false);
    }

    public boolean popBackStackImmediate() {
        checkStateLoss();
        executePendingTransactions();
        return popBackStackState(this.mActivity.mHandler, null, -1, 0);
    }

    public void popBackStack(String name, int flags) {
        enqueueAction(new FragmentManager(name, flags), false);
    }

    public int getBackStackEntryCount() {
        return this.mBackStack != null ? this.mBackStack.size() : 0;
    }

    public BackStackEntry getBackStackEntryAt(int index) {
        return (BackStackEntry) this.mBackStack.get(index);
    }

    public void putFragment(Bundle bundle, String key, Fragment fragment) {
        if (fragment.mIndex < 0) {
            throwException(new IllegalStateException("Fragment " + fragment + " is not currently in the FragmentManager"));
        }
        bundle.putInt(key, fragment.mIndex);
    }

    public Fragment getFragment(Bundle bundle, String key) {
        int index = bundle.getInt(key, -1);
        if (index == -1) {
            return null;
        }
        if (index >= this.mActive.size()) {
            throwException(new IllegalStateException("Fragment no longer exists for key " + key + ": index " + index));
        }
        Fragment f = (Fragment) this.mActive.get(index);
        if (f != null) {
            return f;
        }
        throwException(new IllegalStateException("Fragment no longer exists for key " + key + ": index " + index));
        return f;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("FragmentManager{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(" in ");
        if (this.mParent != null) {
            DebugUtils.buildShortClassTag(this.mParent, sb);
        } else {
            DebugUtils.buildShortClassTag(this.mActivity, sb);
        }
        sb.append("}}");
        return sb.toString();
    }

    public void dump(String prefix, FileDescriptor fd, PrintWriter writer, String[] args) {
        int N;
        int i;
        Fragment f;
        String innerPrefix = prefix + "    ";
        if (this.mActive != null) {
            N = this.mActive.size();
            if (N > 0) {
                writer.print(prefix);
                writer.print("Active Fragments in ");
                writer.print(Integer.toHexString(System.identityHashCode(this)));
                writer.println(":");
                for (i = 0; i < N; i++) {
                    f = (Fragment) this.mActive.get(i);
                    writer.print(prefix);
                    writer.print("  #");
                    writer.print(i);
                    writer.print(": ");
                    writer.println(f);
                    if (f != null) {
                        f.dump(innerPrefix, fd, writer, args);
                    }
                }
            }
        }
        if (this.mAdded != null) {
            N = this.mAdded.size();
            if (N > 0) {
                writer.print(prefix);
                writer.println("Added Fragments:");
                for (i = 0; i < N; i++) {
                    f = (Fragment) this.mAdded.get(i);
                    writer.print(prefix);
                    writer.print("  #");
                    writer.print(i);
                    writer.print(": ");
                    writer.println(f.toString());
                }
            }
        }
        if (this.mCreatedMenus != null) {
            N = this.mCreatedMenus.size();
            if (N > 0) {
                writer.print(prefix);
                writer.println("Fragments Created Menus:");
                for (i = 0; i < N; i++) {
                    f = (Fragment) this.mCreatedMenus.get(i);
                    writer.print(prefix);
                    writer.print("  #");
                    writer.print(i);
                    writer.print(": ");
                    writer.println(f.toString());
                }
            }
        }
        if (this.mBackStack != null) {
            N = this.mBackStack.size();
            if (N > 0) {
                writer.print(prefix);
                writer.println("Back Stack:");
                for (i = 0; i < N; i++) {
                    BackStackRecord bs = (BackStackRecord) this.mBackStack.get(i);
                    writer.print(prefix);
                    writer.print("  #");
                    writer.print(i);
                    writer.print(": ");
                    writer.println(bs.toString());
                    bs.dump(innerPrefix, fd, writer, args);
                }
            }
        }
        synchronized (this) {
            if (this.mBackStackIndices != null) {
                N = this.mBackStackIndices.size();
                if (N > 0) {
                    writer.print(prefix);
                    writer.println("Back Stack Indices:");
                    for (i = 0; i < N; i++) {
                        bs = (BackStackRecord) this.mBackStackIndices.get(i);
                        writer.print(prefix);
                        writer.print("  #");
                        writer.print(i);
                        writer.print(": ");
                        writer.println(bs);
                    }
                }
            }
            if (this.mAvailBackStackIndices != null && this.mAvailBackStackIndices.size() > 0) {
                writer.print(prefix);
                writer.print("mAvailBackStackIndices: ");
                writer.println(Arrays.toString(this.mAvailBackStackIndices.toArray()));
            }
        }
        if (this.mPendingActions != null) {
            N = this.mPendingActions.size();
            if (N > 0) {
                writer.print(prefix);
                writer.println("Pending Actions:");
                for (i = 0; i < N; i++) {
                    Runnable r = (Runnable) this.mPendingActions.get(i);
                    writer.print(prefix);
                    writer.print("  #");
                    writer.print(i);
                    writer.print(": ");
                    writer.println(r);
                }
            }
        }
        writer.print(prefix);
        writer.println("FragmentManager misc state:");
        writer.print(prefix);
        writer.print("  mActivity=");
        writer.println(this.mActivity);
        writer.print(prefix);
        writer.print("  mContainer=");
        writer.println(this.mContainer);
        if (this.mParent != null) {
            writer.print(prefix);
            writer.print("  mParent=");
            writer.println(this.mParent);
        }
        writer.print(prefix);
        writer.print("  mCurState=");
        writer.print(this.mCurState);
        writer.print(" mStateSaved=");
        writer.print(this.mStateSaved);
        writer.print(" mDestroyed=");
        writer.println(this.mDestroyed);
        if (this.mNeedMenuInvalidate) {
            writer.print(prefix);
            writer.print("  mNeedMenuInvalidate=");
            writer.println(this.mNeedMenuInvalidate);
        }
        if (this.mNoTransactionsBecause != null) {
            writer.print(prefix);
            writer.print("  mNoTransactionsBecause=");
            writer.println(this.mNoTransactionsBecause);
        }
        if (this.mAvailIndices != null && this.mAvailIndices.size() > 0) {
            writer.print(prefix);
            writer.print("  mAvailIndices: ");
            writer.println(Arrays.toString(this.mAvailIndices.toArray()));
        }
    }

    static Animation makeOpenCloseAnimation(Context context, float startScale, float endScale, float startAlpha, float endAlpha) {
        AnimationSet set = new AnimationSet(false);
        ScaleAnimation scale = new ScaleAnimation(startScale, endScale, startScale, endScale, 1, 0.5f, 1, 0.5f);
        scale.setInterpolator(DECELERATE_QUINT);
        scale.setDuration(220);
        set.addAnimation(scale);
        AlphaAnimation alpha = new AlphaAnimation(startAlpha, endAlpha);
        alpha.setInterpolator(DECELERATE_CUBIC);
        alpha.setDuration(220);
        set.addAnimation(alpha);
        return set;
    }

    static Animation makeFadeAnimation(Context context, float start, float end) {
        AlphaAnimation anim = new AlphaAnimation(start, end);
        anim.setInterpolator(DECELERATE_CUBIC);
        anim.setDuration(220);
        return anim;
    }

    Animation loadAnimation(Fragment fragment, int transit, boolean enter, int transitionStyle) {
        Animation animObj = fragment.onCreateAnimation(transit, enter, fragment.mNextAnim);
        if (animObj != null) {
            return animObj;
        }
        if (fragment.mNextAnim != 0) {
            Animation anim = AnimationUtils.loadAnimation(this.mActivity, fragment.mNextAnim);
            if (anim != null) {
                return anim;
            }
        }
        if (transit == 0) {
            return null;
        }
        int styleIndex = transitToStyleIndex(transit, enter);
        if (styleIndex < 0) {
            return null;
        }
        switch (styleIndex) {
            case C0151R.styleable.View_paddingStart /*1*/:
                return makeOpenCloseAnimation(this.mActivity, 1.125f, 1.0f, 0.0f, 1.0f);
            case C0151R.styleable.View_paddingEnd /*2*/:
                return makeOpenCloseAnimation(this.mActivity, 1.0f, 0.975f, 1.0f, 0.0f);
            case C0151R.styleable.Toolbar_subtitle /*3*/:
                return makeOpenCloseAnimation(this.mActivity, 0.975f, 1.0f, 0.0f, 1.0f);
            case C0151R.styleable.Toolbar_contentInsetStart /*4*/:
                return makeOpenCloseAnimation(this.mActivity, 1.0f, 1.075f, 1.0f, 0.0f);
            case C0151R.styleable.Toolbar_contentInsetEnd /*5*/:
                return makeFadeAnimation(this.mActivity, 0.0f, 1.0f);
            case C0151R.styleable.Toolbar_contentInsetLeft /*6*/:
                return makeFadeAnimation(this.mActivity, 1.0f, 0.0f);
            default:
                if (transitionStyle == 0 && this.mActivity.getWindow() != null) {
                    transitionStyle = this.mActivity.getWindow().getAttributes().windowAnimations;
                }
                if (transitionStyle == 0) {
                    return null;
                }
                return null;
        }
    }

    public void performPendingDeferredStart(Fragment f) {
        if (!f.mDeferStart) {
            return;
        }
        if (this.mExecutingActions) {
            this.mHavePendingDeferredStart = true;
            return;
        }
        f.mDeferStart = false;
        moveToState(f, this.mCurState, 0, 0, false);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    void moveToState(android.support.v4.app.Fragment r11, int r12, int r13, int r14, boolean r15) {
        /*
        r10 = this;
        r0 = r11.mAdded;
        if (r0 == 0) goto L_0x0008;
    L_0x0004:
        r0 = r11.mDetached;
        if (r0 == 0) goto L_0x000c;
    L_0x0008:
        r0 = 1;
        if (r12 <= r0) goto L_0x000c;
    L_0x000b:
        r12 = 1;
    L_0x000c:
        r0 = r11.mRemoving;
        if (r0 == 0) goto L_0x0016;
    L_0x0010:
        r0 = r11.mState;
        if (r12 <= r0) goto L_0x0016;
    L_0x0014:
        r12 = r11.mState;
    L_0x0016:
        r0 = r11.mDeferStart;
        if (r0 == 0) goto L_0x0023;
    L_0x001a:
        r0 = r11.mState;
        r1 = 4;
        if (r0 >= r1) goto L_0x0023;
    L_0x001f:
        r0 = 3;
        if (r12 <= r0) goto L_0x0023;
    L_0x0022:
        r12 = 3;
    L_0x0023:
        r0 = r11.mState;
        if (r0 >= r12) goto L_0x023f;
    L_0x0027:
        r0 = r11.mFromLayout;
        if (r0 == 0) goto L_0x0030;
    L_0x002b:
        r0 = r11.mInLayout;
        if (r0 != 0) goto L_0x0030;
    L_0x002f:
        return;
    L_0x0030:
        r0 = r11.mAnimatingAway;
        if (r0 == 0) goto L_0x0041;
    L_0x0034:
        r0 = 0;
        r11.mAnimatingAway = r0;
        r2 = r11.mStateAfterAnimating;
        r3 = 0;
        r4 = 0;
        r5 = 1;
        r0 = r10;
        r1 = r11;
        r0.moveToState(r1, r2, r3, r4, r5);
    L_0x0041:
        r0 = r11.mState;
        switch(r0) {
            case 0: goto L_0x0049;
            case 1: goto L_0x012c;
            case 2: goto L_0x01f3;
            case 3: goto L_0x01f3;
            case 4: goto L_0x020f;
            default: goto L_0x0046;
        };
    L_0x0046:
        r11.mState = r12;
        goto L_0x002f;
    L_0x0049:
        r0 = DEBUG;
        if (r0 == 0) goto L_0x005f;
    L_0x004d:
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r1 = "moveto CREATED: ";
        r0 = r0.append(r1);
        r0 = r0.append(r11);
        r0.toString();
    L_0x005f:
        r0 = r11.mSavedFragmentState;
        if (r0 == 0) goto L_0x00a7;
    L_0x0063:
        r0 = r11.mSavedFragmentState;
        r1 = r10.mActivity;
        r1 = r1.getClassLoader();
        r0.setClassLoader(r1);
        r0 = r11.mSavedFragmentState;
        r1 = "android:view_state";
        r0 = r0.getSparseParcelableArray(r1);
        r11.mSavedViewState = r0;
        r0 = r11.mSavedFragmentState;
        r1 = "android:target_state";
        r0 = r10.getFragment(r0, r1);
        r11.mTarget = r0;
        r0 = r11.mTarget;
        if (r0 == 0) goto L_0x0091;
    L_0x0086:
        r0 = r11.mSavedFragmentState;
        r1 = "android:target_req_state";
        r2 = 0;
        r0 = r0.getInt(r1, r2);
        r11.mTargetRequestCode = r0;
    L_0x0091:
        r0 = r11.mSavedFragmentState;
        r1 = "android:user_visible_hint";
        r2 = 1;
        r0 = r0.getBoolean(r1, r2);
        r11.mUserVisibleHint = r0;
        r0 = r11.mUserVisibleHint;
        if (r0 != 0) goto L_0x00a7;
    L_0x00a0:
        r0 = 1;
        r11.mDeferStart = r0;
        r0 = 3;
        if (r12 <= r0) goto L_0x00a7;
    L_0x00a6:
        r12 = 3;
    L_0x00a7:
        r0 = r10.mActivity;
        r11.mActivity = r0;
        r0 = r10.mParent;
        r11.mParentFragment = r0;
        r0 = r10.mParent;
        if (r0 == 0) goto L_0x00e4;
    L_0x00b3:
        r0 = r10.mParent;
        r0 = r0.mChildFragmentManager;
    L_0x00b7:
        r11.mFragmentManager = r0;
        r0 = 0;
        r11.mCalled = r0;
        r0 = r10.mActivity;
        r11.onAttach(r0);
        r0 = r11.mCalled;
        if (r0 != 0) goto L_0x00e9;
    L_0x00c5:
        r0 = new android.support.v4.app.SuperNotCalledException;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "Fragment ";
        r1 = r1.append(r2);
        r1 = r1.append(r11);
        r2 = " did not call through to super.onAttach()";
        r1 = r1.append(r2);
        r1 = r1.toString();
        r0.<init>(r1);
        throw r0;
    L_0x00e4:
        r0 = r10.mActivity;
        r0 = r0.mFragments;
        goto L_0x00b7;
    L_0x00e9:
        r0 = r11.mParentFragment;
        r0 = r11.mRetaining;
        if (r0 != 0) goto L_0x00f4;
    L_0x00ef:
        r0 = r11.mSavedFragmentState;
        r11.performCreate(r0);
    L_0x00f4:
        r0 = 0;
        r11.mRetaining = r0;
        r0 = r11.mFromLayout;
        if (r0 == 0) goto L_0x012c;
    L_0x00fb:
        r0 = r11.mSavedFragmentState;
        r0 = r11.getLayoutInflater(r0);
        r1 = 0;
        r2 = r11.mSavedFragmentState;
        r0 = r11.performCreateView(r0, r1, r2);
        r11.mView = r0;
        r0 = r11.mView;
        if (r0 == 0) goto L_0x0236;
    L_0x010e:
        r0 = r11.mView;
        r11.mInnerView = r0;
        r0 = r11.mView;
        r0 = android.support.v4.app.NoSaveStateFrameLayout.wrap(r0);
        r11.mView = r0;
        r0 = r11.mHidden;
        if (r0 == 0) goto L_0x0125;
    L_0x011e:
        r0 = r11.mView;
        r1 = 8;
        r0.setVisibility(r1);
    L_0x0125:
        r0 = r11.mView;
        r1 = r11.mSavedFragmentState;
        r11.onViewCreated(r0, r1);
    L_0x012c:
        r0 = 1;
        if (r12 <= r0) goto L_0x01f3;
    L_0x012f:
        r0 = DEBUG;
        if (r0 == 0) goto L_0x0145;
    L_0x0133:
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r1 = "moveto ACTIVITY_CREATED: ";
        r0 = r0.append(r1);
        r0 = r0.append(r11);
        r0.toString();
    L_0x0145:
        r0 = r11.mFromLayout;
        if (r0 != 0) goto L_0x01e2;
    L_0x0149:
        r7 = 0;
        r0 = r11.mContainerId;
        if (r0 == 0) goto L_0x019d;
    L_0x014e:
        r0 = r10.mContainer;
        r1 = r11.mContainerId;
        r7 = r0.findViewById(r1);
        r7 = (android.view.ViewGroup) r7;
        if (r7 != 0) goto L_0x019d;
    L_0x015a:
        r0 = r11.mRestored;
        if (r0 != 0) goto L_0x019d;
    L_0x015e:
        r0 = new java.lang.IllegalArgumentException;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "No view found for id 0x";
        r1 = r1.append(r2);
        r2 = r11.mContainerId;
        r2 = java.lang.Integer.toHexString(r2);
        r1 = r1.append(r2);
        r2 = " (";
        r1 = r1.append(r2);
        r2 = r11.getResources();
        r3 = r11.mContainerId;
        r2 = r2.getResourceName(r3);
        r1 = r1.append(r2);
        r2 = ") for fragment ";
        r1 = r1.append(r2);
        r1 = r1.append(r11);
        r1 = r1.toString();
        r0.<init>(r1);
        r10.throwException(r0);
    L_0x019d:
        r11.mContainer = r7;
        r0 = r11.mSavedFragmentState;
        r0 = r11.getLayoutInflater(r0);
        r1 = r11.mSavedFragmentState;
        r0 = r11.performCreateView(r0, r7, r1);
        r11.mView = r0;
        r0 = r11.mView;
        if (r0 == 0) goto L_0x023b;
    L_0x01b1:
        r0 = r11.mView;
        r11.mInnerView = r0;
        r0 = r11.mView;
        r0 = android.support.v4.app.NoSaveStateFrameLayout.wrap(r0);
        r11.mView = r0;
        if (r7 == 0) goto L_0x01d0;
    L_0x01bf:
        r0 = 1;
        r6 = r10.loadAnimation(r11, r13, r0, r14);
        if (r6 == 0) goto L_0x01cb;
    L_0x01c6:
        r0 = r11.mView;
        r0.startAnimation(r6);
    L_0x01cb:
        r0 = r11.mView;
        r7.addView(r0);
    L_0x01d0:
        r0 = r11.mHidden;
        if (r0 == 0) goto L_0x01db;
    L_0x01d4:
        r0 = r11.mView;
        r1 = 8;
        r0.setVisibility(r1);
    L_0x01db:
        r0 = r11.mView;
        r1 = r11.mSavedFragmentState;
        r11.onViewCreated(r0, r1);
    L_0x01e2:
        r0 = r11.mSavedFragmentState;
        r11.performActivityCreated(r0);
        r0 = r11.mView;
        if (r0 == 0) goto L_0x01f0;
    L_0x01eb:
        r0 = r11.mSavedFragmentState;
        r11.restoreViewState(r0);
    L_0x01f0:
        r0 = 0;
        r11.mSavedFragmentState = r0;
    L_0x01f3:
        r0 = 3;
        if (r12 <= r0) goto L_0x020f;
    L_0x01f6:
        r0 = DEBUG;
        if (r0 == 0) goto L_0x020c;
    L_0x01fa:
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r1 = "moveto STARTED: ";
        r0 = r0.append(r1);
        r0 = r0.append(r11);
        r0.toString();
    L_0x020c:
        r11.performStart();
    L_0x020f:
        r0 = 4;
        if (r12 <= r0) goto L_0x0046;
    L_0x0212:
        r0 = DEBUG;
        if (r0 == 0) goto L_0x0228;
    L_0x0216:
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r1 = "moveto RESUMED: ";
        r0 = r0.append(r1);
        r0 = r0.append(r11);
        r0.toString();
    L_0x0228:
        r0 = 1;
        r11.mResumed = r0;
        r11.performResume();
        r0 = 0;
        r11.mSavedFragmentState = r0;
        r0 = 0;
        r11.mSavedViewState = r0;
        goto L_0x0046;
    L_0x0236:
        r0 = 0;
        r11.mInnerView = r0;
        goto L_0x012c;
    L_0x023b:
        r0 = 0;
        r11.mInnerView = r0;
        goto L_0x01e2;
    L_0x023f:
        r0 = r11.mState;
        if (r0 <= r12) goto L_0x0046;
    L_0x0243:
        r0 = r11.mState;
        switch(r0) {
            case 1: goto L_0x024a;
            case 2: goto L_0x02bd;
            case 3: goto L_0x02a1;
            case 4: goto L_0x0285;
            case 5: goto L_0x0266;
            default: goto L_0x0248;
        };
    L_0x0248:
        goto L_0x0046;
    L_0x024a:
        r0 = 1;
        if (r12 >= r0) goto L_0x0046;
    L_0x024d:
        r0 = r10.mDestroyed;
        if (r0 == 0) goto L_0x025d;
    L_0x0251:
        r0 = r11.mAnimatingAway;
        if (r0 == 0) goto L_0x025d;
    L_0x0255:
        r9 = r11.mAnimatingAway;
        r0 = 0;
        r11.mAnimatingAway = r0;
        r9.clearAnimation();
    L_0x025d:
        r0 = r11.mAnimatingAway;
        if (r0 == 0) goto L_0x032a;
    L_0x0261:
        r11.mStateAfterAnimating = r12;
        r12 = 1;
        goto L_0x0046;
    L_0x0266:
        r0 = 5;
        if (r12 >= r0) goto L_0x0285;
    L_0x0269:
        r0 = DEBUG;
        if (r0 == 0) goto L_0x027f;
    L_0x026d:
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r1 = "movefrom RESUMED: ";
        r0 = r0.append(r1);
        r0 = r0.append(r11);
        r0.toString();
    L_0x027f:
        r11.performPause();
        r0 = 0;
        r11.mResumed = r0;
    L_0x0285:
        r0 = 4;
        if (r12 >= r0) goto L_0x02a1;
    L_0x0288:
        r0 = DEBUG;
        if (r0 == 0) goto L_0x029e;
    L_0x028c:
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r1 = "movefrom STARTED: ";
        r0 = r0.append(r1);
        r0 = r0.append(r11);
        r0.toString();
    L_0x029e:
        r11.performStop();
    L_0x02a1:
        r0 = 3;
        if (r12 >= r0) goto L_0x02bd;
    L_0x02a4:
        r0 = DEBUG;
        if (r0 == 0) goto L_0x02ba;
    L_0x02a8:
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r1 = "movefrom STOPPED: ";
        r0 = r0.append(r1);
        r0 = r0.append(r11);
        r0.toString();
    L_0x02ba:
        r11.performReallyStop();
    L_0x02bd:
        r0 = 2;
        if (r12 >= r0) goto L_0x024a;
    L_0x02c0:
        r0 = DEBUG;
        if (r0 == 0) goto L_0x02d6;
    L_0x02c4:
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r1 = "movefrom ACTIVITY_CREATED: ";
        r0 = r0.append(r1);
        r0 = r0.append(r11);
        r0.toString();
    L_0x02d6:
        r0 = r11.mView;
        if (r0 == 0) goto L_0x02e9;
    L_0x02da:
        r0 = r10.mActivity;
        r0 = r0.isFinishing();
        if (r0 != 0) goto L_0x02e9;
    L_0x02e2:
        r0 = r11.mSavedViewState;
        if (r0 != 0) goto L_0x02e9;
    L_0x02e6:
        r10.saveFragmentViewState(r11);
    L_0x02e9:
        r11.performDestroyView();
        r0 = r11.mView;
        if (r0 == 0) goto L_0x031f;
    L_0x02f0:
        r0 = r11.mContainer;
        if (r0 == 0) goto L_0x031f;
    L_0x02f4:
        r6 = 0;
        r0 = r10.mCurState;
        if (r0 <= 0) goto L_0x0302;
    L_0x02f9:
        r0 = r10.mDestroyed;
        if (r0 != 0) goto L_0x0302;
    L_0x02fd:
        r0 = 0;
        r6 = r10.loadAnimation(r11, r13, r0, r14);
    L_0x0302:
        if (r6 == 0) goto L_0x0318;
    L_0x0304:
        r8 = r11;
        r0 = r11.mView;
        r11.mAnimatingAway = r0;
        r11.mStateAfterAnimating = r12;
        r0 = new android.support.v4.app.FragmentManagerImpl$5;
        r0.<init>(r8);
        r6.setAnimationListener(r0);
        r0 = r11.mView;
        r0.startAnimation(r6);
    L_0x0318:
        r0 = r11.mContainer;
        r1 = r11.mView;
        r0.removeView(r1);
    L_0x031f:
        r0 = 0;
        r11.mContainer = r0;
        r0 = 0;
        r11.mView = r0;
        r0 = 0;
        r11.mInnerView = r0;
        goto L_0x024a;
    L_0x032a:
        r0 = DEBUG;
        if (r0 == 0) goto L_0x0340;
    L_0x032e:
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r1 = "movefrom CREATED: ";
        r0 = r0.append(r1);
        r0 = r0.append(r11);
        r0.toString();
    L_0x0340:
        r0 = r11.mRetaining;
        if (r0 != 0) goto L_0x0347;
    L_0x0344:
        r11.performDestroy();
    L_0x0347:
        r0 = 0;
        r11.mCalled = r0;
        r11.onDetach();
        r0 = r11.mCalled;
        if (r0 != 0) goto L_0x0370;
    L_0x0351:
        r0 = new android.support.v4.app.SuperNotCalledException;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "Fragment ";
        r1 = r1.append(r2);
        r1 = r1.append(r11);
        r2 = " did not call through to super.onDetach()";
        r1 = r1.append(r2);
        r1 = r1.toString();
        r0.<init>(r1);
        throw r0;
    L_0x0370:
        if (r15 != 0) goto L_0x0046;
    L_0x0372:
        r0 = r11.mRetaining;
        if (r0 != 0) goto L_0x037b;
    L_0x0376:
        r10.makeInactive(r11);
        goto L_0x0046;
    L_0x037b:
        r0 = 0;
        r11.mActivity = r0;
        r0 = 0;
        r11.mParentFragment = r0;
        r0 = 0;
        r11.mFragmentManager = r0;
        r0 = 0;
        r11.mChildFragmentManager = r0;
        goto L_0x0046;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.app.FragmentManagerImpl.moveToState(android.support.v4.app.Fragment, int, int, int, boolean):void");
    }

    void moveToState(Fragment f) {
        moveToState(f, this.mCurState, 0, 0, false);
    }

    void moveToState(int newState, boolean always) {
        moveToState(newState, 0, 0, always);
    }

    void moveToState(int newState, int transit, int transitStyle, boolean always) {
        if (this.mActivity == null && newState != 0) {
            throw new IllegalStateException("No activity");
        } else if (always || this.mCurState != newState) {
            this.mCurState = newState;
            if (this.mActive != null) {
                boolean loadersRunning = false;
                for (int i = 0; i < this.mActive.size(); i++) {
                    Fragment f = (Fragment) this.mActive.get(i);
                    if (f != null) {
                        moveToState(f, newState, transit, transitStyle, false);
                        if (f.mLoaderManager != null) {
                            loadersRunning |= f.mLoaderManager.hasRunningLoaders();
                        }
                    }
                }
                if (!loadersRunning) {
                    startPendingDeferredFragments();
                }
                if (this.mNeedMenuInvalidate && this.mActivity != null && this.mCurState == 5) {
                    this.mActivity.supportInvalidateOptionsMenu();
                    this.mNeedMenuInvalidate = false;
                }
            }
        }
    }

    void startPendingDeferredFragments() {
        if (this.mActive != null) {
            for (int i = 0; i < this.mActive.size(); i++) {
                Fragment f = (Fragment) this.mActive.get(i);
                if (f != null) {
                    performPendingDeferredStart(f);
                }
            }
        }
    }

    void makeActive(Fragment f) {
        if (f.mIndex < 0) {
            if (this.mAvailIndices == null || this.mAvailIndices.size() <= 0) {
                if (this.mActive == null) {
                    this.mActive = new ArrayList();
                }
                f.setIndex(this.mActive.size(), this.mParent);
                this.mActive.add(f);
            } else {
                f.setIndex(((Integer) this.mAvailIndices.remove(this.mAvailIndices.size() - 1)).intValue(), this.mParent);
                this.mActive.set(f.mIndex, f);
            }
            if (DEBUG) {
                "Allocated fragment index " + f;
            }
        }
    }

    void makeInactive(Fragment f) {
        if (f.mIndex >= 0) {
            if (DEBUG) {
                "Freeing fragment index " + f;
            }
            this.mActive.set(f.mIndex, null);
            if (this.mAvailIndices == null) {
                this.mAvailIndices = new ArrayList();
            }
            this.mAvailIndices.add(Integer.valueOf(f.mIndex));
            this.mActivity.invalidateSupportFragment(f.mWho);
            f.initState();
        }
    }

    public void addFragment(Fragment fragment, boolean moveToStateNow) {
        if (this.mAdded == null) {
            this.mAdded = new ArrayList();
        }
        if (DEBUG) {
            "add: " + fragment;
        }
        makeActive(fragment);
        if (!fragment.mDetached) {
            if (this.mAdded.contains(fragment)) {
                throw new IllegalStateException("Fragment already added: " + fragment);
            }
            this.mAdded.add(fragment);
            fragment.mAdded = true;
            fragment.mRemoving = false;
            if (fragment.mHasMenu && fragment.mMenuVisible) {
                this.mNeedMenuInvalidate = true;
            }
            if (moveToStateNow) {
                moveToState(fragment);
            }
        }
    }

    public void removeFragment(Fragment fragment, int transition, int transitionStyle) {
        boolean inactive;
        if (DEBUG) {
            "remove: " + fragment + " nesting=" + fragment.mBackStackNesting;
        }
        if (fragment.isInBackStack()) {
            inactive = false;
        } else {
            inactive = true;
        }
        if (!fragment.mDetached || inactive) {
            int i;
            if (this.mAdded != null) {
                this.mAdded.remove(fragment);
            }
            if (fragment.mHasMenu && fragment.mMenuVisible) {
                this.mNeedMenuInvalidate = true;
            }
            fragment.mAdded = false;
            fragment.mRemoving = true;
            if (inactive) {
                i = 0;
            } else {
                i = 1;
            }
            moveToState(fragment, i, transition, transitionStyle, false);
        }
    }

    public void hideFragment(Fragment fragment, int transition, int transitionStyle) {
        if (DEBUG) {
            "hide: " + fragment;
        }
        if (!fragment.mHidden) {
            fragment.mHidden = true;
            if (fragment.mView != null) {
                Animation anim = loadAnimation(fragment, transition, false, transitionStyle);
                if (anim != null) {
                    fragment.mView.startAnimation(anim);
                }
                fragment.mView.setVisibility(8);
            }
            if (fragment.mAdded && fragment.mHasMenu && fragment.mMenuVisible) {
                this.mNeedMenuInvalidate = true;
            }
            fragment.onHiddenChanged(true);
        }
    }

    public void showFragment(Fragment fragment, int transition, int transitionStyle) {
        if (DEBUG) {
            "show: " + fragment;
        }
        if (fragment.mHidden) {
            fragment.mHidden = false;
            if (fragment.mView != null) {
                Animation anim = loadAnimation(fragment, transition, true, transitionStyle);
                if (anim != null) {
                    fragment.mView.startAnimation(anim);
                }
                fragment.mView.setVisibility(0);
            }
            if (fragment.mAdded && fragment.mHasMenu && fragment.mMenuVisible) {
                this.mNeedMenuInvalidate = true;
            }
            fragment.onHiddenChanged(false);
        }
    }

    public void detachFragment(Fragment fragment, int transition, int transitionStyle) {
        if (DEBUG) {
            "detach: " + fragment;
        }
        if (!fragment.mDetached) {
            fragment.mDetached = true;
            if (fragment.mAdded) {
                if (this.mAdded != null) {
                    if (DEBUG) {
                        "remove from detach: " + fragment;
                    }
                    this.mAdded.remove(fragment);
                }
                if (fragment.mHasMenu && fragment.mMenuVisible) {
                    this.mNeedMenuInvalidate = true;
                }
                fragment.mAdded = false;
                moveToState(fragment, 1, transition, transitionStyle, false);
            }
        }
    }

    public void attachFragment(Fragment fragment, int transition, int transitionStyle) {
        if (DEBUG) {
            "attach: " + fragment;
        }
        if (fragment.mDetached) {
            fragment.mDetached = false;
            if (!fragment.mAdded) {
                if (this.mAdded == null) {
                    this.mAdded = new ArrayList();
                }
                if (this.mAdded.contains(fragment)) {
                    throw new IllegalStateException("Fragment already added: " + fragment);
                }
                if (DEBUG) {
                    "add from attach: " + fragment;
                }
                this.mAdded.add(fragment);
                fragment.mAdded = true;
                if (fragment.mHasMenu && fragment.mMenuVisible) {
                    this.mNeedMenuInvalidate = true;
                }
                moveToState(fragment, this.mCurState, transition, transitionStyle, false);
            }
        }
    }

    public Fragment findFragmentById(int id) {
        int i;
        Fragment f;
        if (this.mAdded != null) {
            for (i = this.mAdded.size() - 1; i >= 0; i--) {
                f = (Fragment) this.mAdded.get(i);
                if (f != null && f.mFragmentId == id) {
                    return f;
                }
            }
        }
        if (this.mActive != null) {
            for (i = this.mActive.size() - 1; i >= 0; i--) {
                f = (Fragment) this.mActive.get(i);
                if (f != null && f.mFragmentId == id) {
                    return f;
                }
            }
        }
        return null;
    }

    public Fragment findFragmentByTag(String tag) {
        int i;
        Fragment f;
        if (!(this.mAdded == null || tag == null)) {
            for (i = this.mAdded.size() - 1; i >= 0; i--) {
                f = (Fragment) this.mAdded.get(i);
                if (f != null && tag.equals(f.mTag)) {
                    return f;
                }
            }
        }
        if (!(this.mActive == null || tag == null)) {
            for (i = this.mActive.size() - 1; i >= 0; i--) {
                f = (Fragment) this.mActive.get(i);
                if (f != null && tag.equals(f.mTag)) {
                    return f;
                }
            }
        }
        return null;
    }

    private void checkStateLoss() {
        if (this.mStateSaved) {
            throw new IllegalStateException("Can not perform this action after onSaveInstanceState");
        } else if (this.mNoTransactionsBecause != null) {
            throw new IllegalStateException("Can not perform this action inside of " + this.mNoTransactionsBecause);
        }
    }

    public void enqueueAction(Runnable action, boolean allowStateLoss) {
        if (!allowStateLoss) {
            checkStateLoss();
        }
        synchronized (this) {
            if (this.mDestroyed || this.mActivity == null) {
                throw new IllegalStateException("Activity has been destroyed");
            }
            if (this.mPendingActions == null) {
                this.mPendingActions = new ArrayList();
            }
            this.mPendingActions.add(action);
            if (this.mPendingActions.size() == 1) {
                this.mActivity.mHandler.removeCallbacks(this.mExecCommit);
                this.mActivity.mHandler.post(this.mExecCommit);
            }
        }
    }

    public int allocBackStackIndex(BackStackRecord bse) {
        synchronized (this) {
            int index;
            if (this.mAvailBackStackIndices == null || this.mAvailBackStackIndices.size() <= 0) {
                if (this.mBackStackIndices == null) {
                    this.mBackStackIndices = new ArrayList();
                }
                index = this.mBackStackIndices.size();
                if (DEBUG) {
                    "Setting back stack index " + index + " to " + bse;
                }
                this.mBackStackIndices.add(bse);
                return index;
            }
            index = ((Integer) this.mAvailBackStackIndices.remove(this.mAvailBackStackIndices.size() - 1)).intValue();
            if (DEBUG) {
                "Adding back stack index " + index + " with " + bse;
            }
            this.mBackStackIndices.set(index, bse);
            return index;
        }
    }

    public void setBackStackIndex(int index, BackStackRecord bse) {
        synchronized (this) {
            if (this.mBackStackIndices == null) {
                this.mBackStackIndices = new ArrayList();
            }
            int N = this.mBackStackIndices.size();
            if (index < N) {
                if (DEBUG) {
                    "Setting back stack index " + index + " to " + bse;
                }
                this.mBackStackIndices.set(index, bse);
            } else {
                while (N < index) {
                    this.mBackStackIndices.add(null);
                    if (this.mAvailBackStackIndices == null) {
                        this.mAvailBackStackIndices = new ArrayList();
                    }
                    if (DEBUG) {
                        "Adding available back stack index " + N;
                    }
                    this.mAvailBackStackIndices.add(Integer.valueOf(N));
                    N++;
                }
                if (DEBUG) {
                    "Adding back stack index " + index + " with " + bse;
                }
                this.mBackStackIndices.add(bse);
            }
        }
    }

    public void freeBackStackIndex(int index) {
        synchronized (this) {
            this.mBackStackIndices.set(index, null);
            if (this.mAvailBackStackIndices == null) {
                this.mAvailBackStackIndices = new ArrayList();
            }
            if (DEBUG) {
                "Freeing back stack index " + index;
            }
            this.mAvailBackStackIndices.add(Integer.valueOf(index));
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean execPendingActions() {
        /*
        r8 = this;
        r7 = 0;
        r5 = r8.mExecutingActions;
        if (r5 == 0) goto L_0x000d;
    L_0x0005:
        r5 = new java.lang.IllegalStateException;
        r6 = "Recursive entry to executePendingTransactions";
        r5.<init>(r6);
        throw r5;
    L_0x000d:
        r5 = android.os.Looper.myLooper();
        r6 = r8.mActivity;
        r6 = r6.mHandler;
        r6 = r6.getLooper();
        if (r5 == r6) goto L_0x0023;
    L_0x001b:
        r5 = new java.lang.IllegalStateException;
        r6 = "Must be called from main thread of process";
        r5.<init>(r6);
        throw r5;
    L_0x0023:
        r0 = 0;
    L_0x0024:
        monitor-enter(r8);
        r5 = r8.mPendingActions;	 Catch:{ all -> 0x0096 }
        if (r5 == 0) goto L_0x0031;
    L_0x0029:
        r5 = r8.mPendingActions;	 Catch:{ all -> 0x0096 }
        r5 = r5.size();	 Catch:{ all -> 0x0096 }
        if (r5 != 0) goto L_0x0058;
    L_0x0031:
        monitor-exit(r8);	 Catch:{ all -> 0x0096 }
        r5 = r8.mHavePendingDeferredStart;
        if (r5 == 0) goto L_0x00a4;
    L_0x0036:
        r3 = 0;
        r2 = 0;
    L_0x0038:
        r5 = r8.mActive;
        r5 = r5.size();
        if (r2 >= r5) goto L_0x009d;
    L_0x0040:
        r5 = r8.mActive;
        r1 = r5.get(r2);
        r1 = (android.support.v4.app.Fragment) r1;
        if (r1 == 0) goto L_0x0055;
    L_0x004a:
        r5 = r1.mLoaderManager;
        if (r5 == 0) goto L_0x0055;
    L_0x004e:
        r5 = r1.mLoaderManager;
        r5 = r5.hasRunningLoaders();
        r3 = r3 | r5;
    L_0x0055:
        r2 = r2 + 1;
        goto L_0x0038;
    L_0x0058:
        r5 = r8.mPendingActions;	 Catch:{ all -> 0x0096 }
        r4 = r5.size();	 Catch:{ all -> 0x0096 }
        r5 = r8.mTmpActions;	 Catch:{ all -> 0x0096 }
        if (r5 == 0) goto L_0x0067;
    L_0x0062:
        r5 = r8.mTmpActions;	 Catch:{ all -> 0x0096 }
        r5 = r5.length;	 Catch:{ all -> 0x0096 }
        if (r5 >= r4) goto L_0x006b;
    L_0x0067:
        r5 = new java.lang.Runnable[r4];	 Catch:{ all -> 0x0096 }
        r8.mTmpActions = r5;	 Catch:{ all -> 0x0096 }
    L_0x006b:
        r5 = r8.mPendingActions;	 Catch:{ all -> 0x0096 }
        r6 = r8.mTmpActions;	 Catch:{ all -> 0x0096 }
        r5.toArray(r6);	 Catch:{ all -> 0x0096 }
        r5 = r8.mPendingActions;	 Catch:{ all -> 0x0096 }
        r5.clear();	 Catch:{ all -> 0x0096 }
        r5 = r8.mActivity;	 Catch:{ all -> 0x0096 }
        r5 = r5.mHandler;	 Catch:{ all -> 0x0096 }
        r6 = r8.mExecCommit;	 Catch:{ all -> 0x0096 }
        r5.removeCallbacks(r6);	 Catch:{ all -> 0x0096 }
        monitor-exit(r8);	 Catch:{ all -> 0x0096 }
        r5 = 1;
        r8.mExecutingActions = r5;
        r2 = 0;
    L_0x0085:
        if (r2 >= r4) goto L_0x0099;
    L_0x0087:
        r5 = r8.mTmpActions;
        r5 = r5[r2];
        r5.run();
        r5 = r8.mTmpActions;
        r6 = 0;
        r5[r2] = r6;
        r2 = r2 + 1;
        goto L_0x0085;
    L_0x0096:
        r5 = move-exception;
        monitor-exit(r8);	 Catch:{ all -> 0x0096 }
        throw r5;
    L_0x0099:
        r8.mExecutingActions = r7;
        r0 = 1;
        goto L_0x0024;
    L_0x009d:
        if (r3 != 0) goto L_0x00a4;
    L_0x009f:
        r8.mHavePendingDeferredStart = r7;
        r8.startPendingDeferredFragments();
    L_0x00a4:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.app.FragmentManagerImpl.execPendingActions():boolean");
    }

    void reportBackStackChanged() {
        if (this.mBackStackChangeListeners != null) {
            for (int i = 0; i < this.mBackStackChangeListeners.size(); i++) {
                this.mBackStackChangeListeners.get(i);
            }
        }
    }

    void addBackStackState(BackStackRecord state) {
        if (this.mBackStack == null) {
            this.mBackStack = new ArrayList();
        }
        this.mBackStack.add(state);
        reportBackStackChanged();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    boolean popBackStackState(android.os.Handler r12, java.lang.String r13, int r14, int r15) {
        /*
        r11 = this;
        r9 = r11.mBackStack;
        if (r9 != 0) goto L_0x0006;
    L_0x0004:
        r9 = 0;
    L_0x0005:
        return r9;
    L_0x0006:
        if (r13 != 0) goto L_0x0039;
    L_0x0008:
        if (r14 >= 0) goto L_0x0039;
    L_0x000a:
        r9 = r15 & 1;
        if (r9 != 0) goto L_0x0039;
    L_0x000e:
        r9 = r11.mBackStack;
        r9 = r9.size();
        r5 = r9 + -1;
        if (r5 >= 0) goto L_0x001a;
    L_0x0018:
        r9 = 0;
        goto L_0x0005;
    L_0x001a:
        r9 = r11.mBackStack;
        r1 = r9.remove(r5);
        r1 = (android.support.v4.app.BackStackRecord) r1;
        r2 = new android.util.SparseArray;
        r2.<init>();
        r6 = new android.util.SparseArray;
        r6.<init>();
        r1.calculateBackFragments(r2, r6);
        r9 = 1;
        r10 = 0;
        r1.popFromBackStack(r9, r10, r2, r6);
        r11.reportBackStackChanged();
    L_0x0037:
        r9 = 1;
        goto L_0x0005;
    L_0x0039:
        r4 = -1;
        if (r13 != 0) goto L_0x003e;
    L_0x003c:
        if (r14 < 0) goto L_0x008e;
    L_0x003e:
        r9 = r11.mBackStack;
        r9 = r9.size();
        r4 = r9 + -1;
    L_0x0046:
        if (r4 < 0) goto L_0x005c;
    L_0x0048:
        r9 = r11.mBackStack;
        r1 = r9.get(r4);
        r1 = (android.support.v4.app.BackStackRecord) r1;
        if (r13 == 0) goto L_0x0060;
    L_0x0052:
        r9 = r1.getName();
        r9 = r13.equals(r9);
        if (r9 == 0) goto L_0x0060;
    L_0x005c:
        if (r4 >= 0) goto L_0x0069;
    L_0x005e:
        r9 = 0;
        goto L_0x0005;
    L_0x0060:
        if (r14 < 0) goto L_0x0066;
    L_0x0062:
        r9 = r1.mIndex;
        if (r14 == r9) goto L_0x005c;
    L_0x0066:
        r4 = r4 + -1;
        goto L_0x0046;
    L_0x0069:
        r9 = r15 & 1;
        if (r9 == 0) goto L_0x008e;
    L_0x006d:
        r4 = r4 + -1;
    L_0x006f:
        if (r4 < 0) goto L_0x008e;
    L_0x0071:
        r9 = r11.mBackStack;
        r1 = r9.get(r4);
        r1 = (android.support.v4.app.BackStackRecord) r1;
        if (r13 == 0) goto L_0x0085;
    L_0x007b:
        r9 = r1.getName();
        r9 = r13.equals(r9);
        if (r9 != 0) goto L_0x008b;
    L_0x0085:
        if (r14 < 0) goto L_0x008e;
    L_0x0087:
        r9 = r1.mIndex;
        if (r14 != r9) goto L_0x008e;
    L_0x008b:
        r4 = r4 + -1;
        goto L_0x006f;
    L_0x008e:
        r9 = r11.mBackStack;
        r9 = r9.size();
        r9 = r9 + -1;
        if (r4 != r9) goto L_0x009b;
    L_0x0098:
        r9 = 0;
        goto L_0x0005;
    L_0x009b:
        r8 = new java.util.ArrayList;
        r8.<init>();
        r9 = r11.mBackStack;
        r9 = r9.size();
        r3 = r9 + -1;
    L_0x00a8:
        if (r3 <= r4) goto L_0x00b6;
    L_0x00aa:
        r9 = r11.mBackStack;
        r9 = r9.remove(r3);
        r8.add(r9);
        r3 = r3 + -1;
        goto L_0x00a8;
    L_0x00b6:
        r9 = r8.size();
        r0 = r9 + -1;
        r2 = new android.util.SparseArray;
        r2.<init>();
        r6 = new android.util.SparseArray;
        r6.<init>();
        r3 = 0;
    L_0x00c7:
        if (r3 > r0) goto L_0x00d5;
    L_0x00c9:
        r9 = r8.get(r3);
        r9 = (android.support.v4.app.BackStackRecord) r9;
        r9.calculateBackFragments(r2, r6);
        r3 = r3 + 1;
        goto L_0x00c7;
    L_0x00d5:
        r7 = 0;
        r3 = 0;
    L_0x00d7:
        if (r3 > r0) goto L_0x0105;
    L_0x00d9:
        r9 = DEBUG;
        if (r9 == 0) goto L_0x00f3;
    L_0x00dd:
        r9 = new java.lang.StringBuilder;
        r9.<init>();
        r10 = "Popping back stack state: ";
        r9 = r9.append(r10);
        r10 = r8.get(r3);
        r9 = r9.append(r10);
        r9.toString();
    L_0x00f3:
        r9 = r8.get(r3);
        r9 = (android.support.v4.app.BackStackRecord) r9;
        if (r3 != r0) goto L_0x0103;
    L_0x00fb:
        r10 = 1;
    L_0x00fc:
        r7 = r9.popFromBackStack(r10, r7, r2, r6);
        r3 = r3 + 1;
        goto L_0x00d7;
    L_0x0103:
        r10 = 0;
        goto L_0x00fc;
    L_0x0105:
        r11.reportBackStackChanged();
        goto L_0x0037;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.app.FragmentManagerImpl.popBackStackState(android.os.Handler, java.lang.String, int, int):boolean");
    }

    ArrayList<Fragment> retainNonConfig() {
        ArrayList<Fragment> fragments = null;
        if (this.mActive != null) {
            for (int i = 0; i < this.mActive.size(); i++) {
                Fragment f = (Fragment) this.mActive.get(i);
                if (f != null && f.mRetainInstance) {
                    if (fragments == null) {
                        fragments = new ArrayList();
                    }
                    fragments.add(f);
                    f.mRetaining = true;
                    f.mTargetIndex = f.mTarget != null ? f.mTarget.mIndex : -1;
                    if (DEBUG) {
                        "retainNonConfig: keeping retained " + f;
                    }
                }
            }
        }
        return fragments;
    }

    void saveFragmentViewState(Fragment f) {
        if (f.mInnerView != null) {
            if (this.mStateArray == null) {
                this.mStateArray = new SparseArray();
            } else {
                this.mStateArray.clear();
            }
            f.mInnerView.saveHierarchyState(this.mStateArray);
            if (this.mStateArray.size() > 0) {
                f.mSavedViewState = this.mStateArray;
                this.mStateArray = null;
            }
        }
    }

    Bundle saveFragmentBasicState(Fragment f) {
        Bundle result = null;
        if (this.mStateBundle == null) {
            this.mStateBundle = new Bundle();
        }
        f.performSaveInstanceState(this.mStateBundle);
        if (!this.mStateBundle.isEmpty()) {
            result = this.mStateBundle;
            this.mStateBundle = null;
        }
        if (f.mView != null) {
            saveFragmentViewState(f);
        }
        if (f.mSavedViewState != null) {
            if (result == null) {
                result = new Bundle();
            }
            result.putSparseParcelableArray("android:view_state", f.mSavedViewState);
        }
        if (!f.mUserVisibleHint) {
            if (result == null) {
                result = new Bundle();
            }
            result.putBoolean("android:user_visible_hint", f.mUserVisibleHint);
        }
        return result;
    }

    Parcelable saveAllState() {
        execPendingActions();
        if (HONEYCOMB) {
            this.mStateSaved = true;
        }
        if (this.mActive == null || this.mActive.size() <= 0) {
            return null;
        }
        int i;
        int N = this.mActive.size();
        FragmentState[] active = new FragmentState[N];
        boolean haveFragments = false;
        for (i = 0; i < N; i++) {
            Fragment f = (Fragment) this.mActive.get(i);
            if (f != null) {
                if (f.mIndex < 0) {
                    throwException(new IllegalStateException("Failure saving state: active " + f + " has cleared index: " + f.mIndex));
                }
                haveFragments = true;
                FragmentState fs = new FragmentState(f);
                active[i] = fs;
                if (f.mState <= 0 || fs.mSavedFragmentState != null) {
                    fs.mSavedFragmentState = f.mSavedFragmentState;
                } else {
                    fs.mSavedFragmentState = saveFragmentBasicState(f);
                    if (f.mTarget != null) {
                        if (f.mTarget.mIndex < 0) {
                            throwException(new IllegalStateException("Failure saving state: " + f + " has target not in fragment manager: " + f.mTarget));
                        }
                        if (fs.mSavedFragmentState == null) {
                            fs.mSavedFragmentState = new Bundle();
                        }
                        putFragment(fs.mSavedFragmentState, "android:target_state", f.mTarget);
                        if (f.mTargetRequestCode != 0) {
                            fs.mSavedFragmentState.putInt("android:target_req_state", f.mTargetRequestCode);
                        }
                    }
                }
                if (DEBUG) {
                    "Saved state of " + f + ": " + fs.mSavedFragmentState;
                }
            }
        }
        if (!haveFragments) {
            return null;
        }
        int[] added = null;
        BackStackState[] backStack = null;
        if (this.mAdded != null) {
            N = this.mAdded.size();
            if (N > 0) {
                added = new int[N];
                for (i = 0; i < N; i++) {
                    added[i] = ((Fragment) this.mAdded.get(i)).mIndex;
                    if (added[i] < 0) {
                        throwException(new IllegalStateException("Failure saving state: active " + this.mAdded.get(i) + " has cleared index: " + added[i]));
                    }
                    if (DEBUG) {
                        "saveAllState: adding fragment #" + i + ": " + this.mAdded.get(i);
                    }
                }
            }
        }
        if (this.mBackStack != null) {
            N = this.mBackStack.size();
            if (N > 0) {
                backStack = new BackStackState[N];
                for (i = 0; i < N; i++) {
                    backStack[i] = new BackStackState(this, (BackStackRecord) this.mBackStack.get(i));
                    if (DEBUG) {
                        "saveAllState: adding back stack #" + i + ": " + this.mBackStack.get(i);
                    }
                }
            }
        }
        Parcelable fms = new FragmentManagerState();
        fms.mActive = active;
        fms.mAdded = added;
        fms.mBackStack = backStack;
        return fms;
    }

    void restoreAllState(Parcelable state, ArrayList<Fragment> nonConfig) {
        if (state != null) {
            FragmentManagerState fms = (FragmentManagerState) state;
            if (fms.mActive != null) {
                int i;
                Fragment f;
                FragmentState fs;
                if (nonConfig != null) {
                    for (i = 0; i < nonConfig.size(); i++) {
                        f = (Fragment) nonConfig.get(i);
                        if (DEBUG) {
                            "restoreAllState: re-attaching retained " + f;
                        }
                        fs = fms.mActive[f.mIndex];
                        fs.mInstance = f;
                        f.mSavedViewState = null;
                        f.mBackStackNesting = 0;
                        f.mInLayout = false;
                        f.mAdded = false;
                        f.mTarget = null;
                        if (fs.mSavedFragmentState != null) {
                            fs.mSavedFragmentState.setClassLoader(this.mActivity.getClassLoader());
                            f.mSavedViewState = fs.mSavedFragmentState.getSparseParcelableArray("android:view_state");
                            f.mSavedFragmentState = fs.mSavedFragmentState;
                        }
                    }
                }
                this.mActive = new ArrayList(fms.mActive.length);
                if (this.mAvailIndices != null) {
                    this.mAvailIndices.clear();
                }
                for (i = 0; i < fms.mActive.length; i++) {
                    fs = fms.mActive[i];
                    if (fs != null) {
                        f = fs.instantiate(this.mActivity, this.mParent);
                        if (DEBUG) {
                            "restoreAllState: active #" + i + ": " + f;
                        }
                        this.mActive.add(f);
                        fs.mInstance = null;
                    } else {
                        this.mActive.add(null);
                        if (this.mAvailIndices == null) {
                            this.mAvailIndices = new ArrayList();
                        }
                        if (DEBUG) {
                            "restoreAllState: avail #" + i;
                        }
                        this.mAvailIndices.add(Integer.valueOf(i));
                    }
                }
                if (nonConfig != null) {
                    for (i = 0; i < nonConfig.size(); i++) {
                        f = (Fragment) nonConfig.get(i);
                        if (f.mTargetIndex >= 0) {
                            if (f.mTargetIndex < this.mActive.size()) {
                                f.mTarget = (Fragment) this.mActive.get(f.mTargetIndex);
                            } else {
                                Log.w("FragmentManager", "Re-attaching retained fragment " + f + " target no longer exists: " + f.mTargetIndex);
                                f.mTarget = null;
                            }
                        }
                    }
                }
                if (fms.mAdded != null) {
                    this.mAdded = new ArrayList(fms.mAdded.length);
                    for (i = 0; i < fms.mAdded.length; i++) {
                        f = (Fragment) this.mActive.get(fms.mAdded[i]);
                        if (f == null) {
                            throwException(new IllegalStateException("No instantiated fragment for index #" + fms.mAdded[i]));
                        }
                        f.mAdded = true;
                        if (DEBUG) {
                            "restoreAllState: added #" + i + ": " + f;
                        }
                        if (this.mAdded.contains(f)) {
                            throw new IllegalStateException("Already added!");
                        }
                        this.mAdded.add(f);
                    }
                } else {
                    this.mAdded = null;
                }
                if (fms.mBackStack != null) {
                    this.mBackStack = new ArrayList(fms.mBackStack.length);
                    for (i = 0; i < fms.mBackStack.length; i++) {
                        BackStackRecord bse = fms.mBackStack[i].instantiate(this);
                        if (DEBUG) {
                            "restoreAllState: back stack #" + i + " (index " + bse.mIndex + "): " + bse;
                            bse.dump("  ", new PrintWriter(new LogWriter("FragmentManager")), false);
                        }
                        this.mBackStack.add(bse);
                        if (bse.mIndex >= 0) {
                            setBackStackIndex(bse.mIndex, bse);
                        }
                    }
                    return;
                }
                this.mBackStack = null;
            }
        }
    }

    public void attachActivity(FragmentActivity activity, FragmentContainer container, Fragment parent) {
        if (this.mActivity != null) {
            throw new IllegalStateException("Already attached");
        }
        this.mActivity = activity;
        this.mContainer = container;
        this.mParent = parent;
    }

    public void noteStateNotSaved() {
        this.mStateSaved = false;
    }

    public void dispatchCreate() {
        this.mStateSaved = false;
        moveToState(1, false);
    }

    public void dispatchActivityCreated() {
        this.mStateSaved = false;
        moveToState(2, false);
    }

    public void dispatchStart() {
        this.mStateSaved = false;
        moveToState(4, false);
    }

    public void dispatchResume() {
        this.mStateSaved = false;
        moveToState(5, false);
    }

    public void dispatchPause() {
        moveToState(4, false);
    }

    public void dispatchStop() {
        this.mStateSaved = true;
        moveToState(3, false);
    }

    public void dispatchReallyStop() {
        moveToState(2, false);
    }

    public void dispatchDestroyView() {
        moveToState(1, false);
    }

    public void dispatchDestroy() {
        this.mDestroyed = true;
        execPendingActions();
        moveToState(0, false);
        this.mActivity = null;
        this.mContainer = null;
        this.mParent = null;
    }

    public void dispatchConfigurationChanged(Configuration newConfig) {
        if (this.mAdded != null) {
            for (int i = 0; i < this.mAdded.size(); i++) {
                Fragment f = (Fragment) this.mAdded.get(i);
                if (f != null) {
                    f.performConfigurationChanged(newConfig);
                }
            }
        }
    }

    public void dispatchLowMemory() {
        if (this.mAdded != null) {
            for (int i = 0; i < this.mAdded.size(); i++) {
                Fragment f = (Fragment) this.mAdded.get(i);
                if (f != null) {
                    f.performLowMemory();
                }
            }
        }
    }

    public boolean dispatchCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        int i;
        Fragment f;
        boolean show = false;
        ArrayList<Fragment> newMenus = null;
        if (this.mAdded != null) {
            for (i = 0; i < this.mAdded.size(); i++) {
                f = (Fragment) this.mAdded.get(i);
                if (f != null && f.performCreateOptionsMenu(menu, inflater)) {
                    show = true;
                    if (newMenus == null) {
                        newMenus = new ArrayList();
                    }
                    newMenus.add(f);
                }
            }
        }
        if (this.mCreatedMenus != null) {
            for (i = 0; i < this.mCreatedMenus.size(); i++) {
                f = (Fragment) this.mCreatedMenus.get(i);
                if (newMenus == null || !newMenus.contains(f)) {
                    f.onDestroyOptionsMenu();
                }
            }
        }
        this.mCreatedMenus = newMenus;
        return show;
    }

    public boolean dispatchPrepareOptionsMenu(Menu menu) {
        boolean show = false;
        if (this.mAdded != null) {
            for (int i = 0; i < this.mAdded.size(); i++) {
                Fragment f = (Fragment) this.mAdded.get(i);
                if (f != null && f.performPrepareOptionsMenu(menu)) {
                    show = true;
                }
            }
        }
        return show;
    }

    public boolean dispatchOptionsItemSelected(MenuItem item) {
        if (this.mAdded != null) {
            for (int i = 0; i < this.mAdded.size(); i++) {
                Fragment f = (Fragment) this.mAdded.get(i);
                if (f != null && f.performOptionsItemSelected(item)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean dispatchContextItemSelected(MenuItem item) {
        if (this.mAdded != null) {
            for (int i = 0; i < this.mAdded.size(); i++) {
                Fragment f = (Fragment) this.mAdded.get(i);
                if (f != null && f.performContextItemSelected(item)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void dispatchOptionsMenuClosed(Menu menu) {
        if (this.mAdded != null) {
            for (int i = 0; i < this.mAdded.size(); i++) {
                Fragment f = (Fragment) this.mAdded.get(i);
                if (f != null) {
                    f.performOptionsMenuClosed(menu);
                }
            }
        }
    }

    public static int reverseTransit(int transit) {
        switch (transit) {
            case 4097:
                return 8194;
            case 4099:
                return 4099;
            case 8194:
                return 4097;
            default:
                return 0;
        }
    }

    public static int transitToStyleIndex(int transit, boolean enter) {
        int animAttr = -1;
        switch (transit) {
            case 4097:
                animAttr = enter ? 1 : 2;
                break;
            case 4099:
                animAttr = enter ? 5 : 6;
                break;
            case 8194:
                animAttr = enter ? 3 : 4;
                break;
        }
        return animAttr;
    }

    public View onCreateView(String name, Context context, AttributeSet attrs) {
        if (!"fragment".equals(name)) {
            return null;
        }
        String fname = attrs.getAttributeValue(null, "class");
        TypedArray a = context.obtainStyledAttributes(attrs, FragmentTag.Fragment);
        if (fname == null) {
            fname = a.getString(0);
        }
        int id = a.getResourceId(1, -1);
        String tag = a.getString(2);
        a.recycle();
        if (!Fragment.isSupportFragmentClass(this.mActivity, fname)) {
            return null;
        }
        int containerId;
        View parent = null;
        if (parent != null) {
            containerId = parent.getId();
        } else {
            containerId = 0;
        }
        if (containerId == -1 && id == -1 && tag == null) {
            throw new IllegalArgumentException(attrs.getPositionDescription() + ": Must specify unique android:id, android:tag, or have a parent with an id for " + fname);
        }
        Fragment fragment;
        if (id != -1) {
            fragment = findFragmentById(id);
        } else {
            fragment = null;
        }
        if (fragment == null && tag != null) {
            fragment = findFragmentByTag(tag);
        }
        if (fragment == null && containerId != -1) {
            fragment = findFragmentById(containerId);
        }
        if (DEBUG) {
            "onCreateView: id=0x" + Integer.toHexString(id) + " fname=" + fname + " existing=" + fragment;
        }
        if (fragment == null) {
            int i;
            fragment = Fragment.instantiate(context, fname);
            fragment.mFromLayout = true;
            if (id != 0) {
                i = id;
            } else {
                i = containerId;
            }
            fragment.mFragmentId = i;
            fragment.mContainerId = containerId;
            fragment.mTag = tag;
            fragment.mInLayout = true;
            fragment.mFragmentManager = this;
            fragment.onInflate(this.mActivity, attrs, fragment.mSavedFragmentState);
            addFragment(fragment, true);
        } else if (fragment.mInLayout) {
            throw new IllegalArgumentException(attrs.getPositionDescription() + ": Duplicate id 0x" + Integer.toHexString(id) + ", tag " + tag + ", or parent id 0x" + Integer.toHexString(containerId) + " with another fragment for " + fname);
        } else {
            fragment.mInLayout = true;
            if (!fragment.mRetaining) {
                fragment.onInflate(this.mActivity, attrs, fragment.mSavedFragmentState);
            }
        }
        if (this.mCurState >= 1 || !fragment.mFromLayout) {
            moveToState(fragment);
        } else {
            moveToState(fragment, 1, 0, 0, false);
        }
        if (fragment.mView == null) {
            throw new IllegalStateException("Fragment " + fname + " did not create a view.");
        }
        if (id != 0) {
            fragment.mView.setId(id);
        }
        if (fragment.mView.getTag() == null) {
            fragment.mView.setTag(tag);
        }
        return fragment.mView;
    }

    Factory getLayoutInflaterFactory() {
        return this;
    }
}
