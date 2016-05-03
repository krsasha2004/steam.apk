package com.valvesoftware.android.steam.community.fragment;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageContainer;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import java.util.HashSet;

public class NetworkImageViewWithBackup extends ImageView {
    private static final HashSet<String> badImageUrls;
    private String mBackupUrl;
    private int mDefaultImageId;
    private int mErrorImageId;
    private ImageContainer mImageContainer;
    private ImageLoader mImageLoader;
    private String mUrl;

    /* renamed from: com.valvesoftware.android.steam.community.fragment.NetworkImageViewWithBackup.1 */
    class C02471 implements ImageListener {
        final /* synthetic */ String val$currentUrl;
        final /* synthetic */ boolean val$isInLayoutPass;

        /* renamed from: com.valvesoftware.android.steam.community.fragment.NetworkImageViewWithBackup.1.1 */
        class C02451 implements Runnable {
            C02451() {
            }

            public void run() {
                NetworkImageViewWithBackup.this.loadImageIfNecessary(C02471.this.val$isInLayoutPass);
            }
        }

        /* renamed from: com.valvesoftware.android.steam.community.fragment.NetworkImageViewWithBackup.1.2 */
        class C02462 implements Runnable {
            final /* synthetic */ ImageContainer val$response;

            C02462(ImageContainer imageContainer) {
                this.val$response = imageContainer;
            }

            public void run() {
                C02471.this.onResponse(this.val$response, false);
            }
        }

        C02471(String str, boolean z) {
            this.val$currentUrl = str;
            this.val$isInLayoutPass = z;
        }

        public void onErrorResponse(VolleyError error) {
            NetworkImageViewWithBackup.badImageUrls.add(this.val$currentUrl);
            if (this.val$currentUrl.equals(NetworkImageViewWithBackup.this.mUrl)) {
                NetworkImageViewWithBackup.this.post(new C02451());
            } else if (NetworkImageViewWithBackup.this.mErrorImageId != 0) {
                NetworkImageViewWithBackup.this.setImageResource(NetworkImageViewWithBackup.this.mErrorImageId);
            }
        }

        public void onResponse(ImageContainer response, boolean isImmediate) {
            if (isImmediate && this.val$isInLayoutPass) {
                NetworkImageViewWithBackup.this.post(new C02462(response));
            } else if (response.getBitmap() != null) {
                NetworkImageViewWithBackup.this.setImageBitmap(response.getBitmap());
            } else if (NetworkImageViewWithBackup.this.mDefaultImageId != 0) {
                NetworkImageViewWithBackup.this.setImageResource(NetworkImageViewWithBackup.this.mDefaultImageId);
            }
        }
    }

    static {
        badImageUrls = new HashSet();
    }

    public NetworkImageViewWithBackup(Context context) {
        this(context, null);
    }

    public NetworkImageViewWithBackup(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NetworkImageViewWithBackup(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setImageUrl(String url, String backupUrl, ImageLoader imageLoader) {
        this.mBackupUrl = backupUrl;
        this.mUrl = url;
        this.mImageLoader = imageLoader;
        loadImageIfNecessary(false);
    }

    public void setDefaultImageResId(int defaultImage) {
        this.mDefaultImageId = defaultImage;
    }

    public void setErrorImageResId(int errorImage) {
        this.mErrorImageId = errorImage;
    }

    private void loadImageIfNecessary(boolean isInLayoutPass) {
        int width = getWidth();
        int height = getHeight();
        String url = this.mUrl;
        if (badImageUrls.contains(this.mUrl)) {
            if (badImageUrls.contains(this.mBackupUrl)) {
                url = null;
            } else {
                url = this.mBackupUrl;
            }
        }
        if (width != 0 || height != 0) {
            if (TextUtils.isEmpty(url)) {
                if (this.mImageContainer != null) {
                    this.mImageContainer.cancelRequest();
                    this.mImageContainer = null;
                }
                setImageBitmap(null);
                return;
            }
            if (!(this.mImageContainer == null || this.mImageContainer.getRequestUrl() == null)) {
                if (!this.mImageContainer.getRequestUrl().equals(url)) {
                    this.mImageContainer.cancelRequest();
                    setImageBitmap(null);
                } else {
                    return;
                }
            }
            String currentUrl = url;
            this.mImageContainer = this.mImageLoader.get(currentUrl, new C02471(currentUrl, isInLayoutPass));
        }
    }

    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        loadImageIfNecessary(true);
    }

    protected void onDetachedFromWindow() {
        if (this.mImageContainer != null) {
            this.mImageContainer.cancelRequest();
            setImageBitmap(null);
            this.mImageContainer = null;
        }
        super.onDetachedFromWindow();
    }

    protected void drawableStateChanged() {
        super.drawableStateChanged();
        invalidate();
    }
}
