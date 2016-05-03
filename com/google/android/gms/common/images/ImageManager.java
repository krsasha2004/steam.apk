package com.google.android.gms.common.images;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable.ConstantState;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.os.ResultReceiver;
import android.util.Log;
import com.google.android.gms.internal.af;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Map;

public final class ImageManager {
    private final af<Uri, WeakReference<ConstantState>> aj;
    private final Map<Uri, C0101b> al;
    private final Context mContext;

    /* renamed from: com.google.android.gms.common.images.ImageManager.b */
    public final class C0101b extends ResultReceiver {
        final /* synthetic */ ImageManager an;
        private final ArrayList<Object> ao;
        private final Uri mUri;

        public void onReceiveResult(int resultCode, Bundle resultData) {
            ParcelFileDescriptor parcelFileDescriptor = (ParcelFileDescriptor) resultData.getParcelable("com.google.android.gms.extra.fileDescriptor");
            if (parcelFileDescriptor != null) {
                Bitmap decodeFileDescriptor = BitmapFactory.decodeFileDescriptor(parcelFileDescriptor.getFileDescriptor());
                try {
                    parcelFileDescriptor.close();
                } catch (Throwable e) {
                    Log.e("ImageManager", "closed failed", e);
                }
                this.an.aj.put(this.mUri, new WeakReference(new BitmapDrawable(this.an.mContext.getResources(), decodeFileDescriptor).getConstantState()));
            }
            this.an.al.remove(this.mUri);
            int size = this.ao.size();
            for (int i = 0; i < size; i++) {
                this.ao.get(i);
            }
        }
    }
}
