package android.support.v4.app;

public abstract class FragmentManager {

    public interface BackStackEntry {
        String getName();
    }

    public abstract FragmentTransaction beginTransaction();

    public abstract boolean executePendingTransactions();

    public abstract Fragment findFragmentById(int i);

    public abstract Fragment findFragmentByTag(String str);

    public abstract BackStackEntry getBackStackEntryAt(int i);

    public abstract int getBackStackEntryCount();

    public abstract void popBackStack();

    public abstract void popBackStack(String str, int i);
}
