package com.google.android.gms.gcm;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class GoogleCloudMessaging {
    static GoogleCloudMessaging ej;
    private Context ek;
    private PendingIntent el;
    final BlockingQueue<Intent> em;
    private Handler en;
    private Messenger eo;

    /* renamed from: com.google.android.gms.gcm.GoogleCloudMessaging.1 */
    class C01081 extends Handler {
        final /* synthetic */ GoogleCloudMessaging ep;

        C01081(GoogleCloudMessaging googleCloudMessaging, Looper looper) {
            this.ep = googleCloudMessaging;
            super(looper);
        }

        public void handleMessage(Message msg) {
            this.ep.em.add((Intent) msg.obj);
        }
    }

    public GoogleCloudMessaging() {
        this.em = new LinkedBlockingQueue();
        this.en = new C01081(this, Looper.getMainLooper());
        this.eo = new Messenger(this.en);
    }

    private void ax() {
        Intent intent = new Intent("com.google.android.c2dm.intent.UNREGISTER");
        intent.setPackage("com.google.android.gms");
        this.em.clear();
        intent.putExtra("google.messenger", this.eo);
        m37b(intent);
        this.ek.startService(intent);
    }

    private void m36b(String... strArr) {
        String c = m38c(strArr);
        Intent intent = new Intent("com.google.android.c2dm.intent.REGISTER");
        intent.setPackage("com.google.android.gms");
        intent.putExtra("google.messenger", this.eo);
        m37b(intent);
        intent.putExtra("sender", c);
        this.ek.startService(intent);
    }

    public static synchronized GoogleCloudMessaging getInstance(Context context) {
        GoogleCloudMessaging googleCloudMessaging;
        synchronized (GoogleCloudMessaging.class) {
            if (ej == null) {
                ej = new GoogleCloudMessaging();
                ej.ek = context;
            }
            googleCloudMessaging = ej;
        }
        return googleCloudMessaging;
    }

    synchronized void m37b(Intent intent) {
        if (this.el == null) {
            this.el = PendingIntent.getBroadcast(this.ek, 0, new Intent(), 0);
        }
        intent.putExtra("app", this.el);
    }

    String m38c(String... strArr) {
        if (strArr == null || strArr.length == 0) {
            throw new IllegalArgumentException("No senderIds");
        }
        StringBuilder stringBuilder = new StringBuilder(strArr[0]);
        for (int i = 1; i < strArr.length; i++) {
            stringBuilder.append(',').append(strArr[i]);
        }
        return stringBuilder.toString();
    }

    public String register(String... senderIds) throws IOException {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            throw new IOException("MAIN_THREAD");
        }
        this.em.clear();
        m36b(senderIds);
        try {
            Intent intent = (Intent) this.em.poll(5000, TimeUnit.MILLISECONDS);
            if (intent == null) {
                throw new IOException("SERVICE_NOT_AVAILABLE");
            }
            String stringExtra = intent.getStringExtra("registration_id");
            if (stringExtra != null) {
                return stringExtra;
            }
            intent.getStringExtra("error");
            String stringExtra2 = intent.getStringExtra("error");
            if (stringExtra2 != null) {
                throw new IOException(stringExtra2);
            }
            throw new IOException("SERVICE_NOT_AVAILABLE");
        } catch (InterruptedException e) {
            throw new IOException(e.getMessage());
        }
    }

    public void unregister() throws IOException {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            throw new IOException("MAIN_THREAD");
        }
        ax();
        try {
            Intent intent = (Intent) this.em.poll(5000, TimeUnit.MILLISECONDS);
            if (intent == null) {
                throw new IOException("SERVICE_NOT_AVAILABLE");
            } else if (intent.getStringExtra("unregistered") == null) {
                String stringExtra = intent.getStringExtra("error");
                if (stringExtra != null) {
                    throw new IOException(stringExtra);
                }
                throw new IOException("SERVICE_NOT_AVAILABLE");
            }
        } catch (InterruptedException e) {
            throw new IOException(e.getMessage());
        }
    }
}
