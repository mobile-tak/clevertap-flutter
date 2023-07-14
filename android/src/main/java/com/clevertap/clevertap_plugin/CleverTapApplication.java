package com.clevertap.clevertap_plugin;

import android.util.Log;

import com.clevertap.android.sdk.CleverTapAPI;
import com.clevertap.android.sdk.pushnotification.CTPushNotificationListener;
import com.clevertap.clevertap_plugin.isolate.CleverTapBackgroundIsolateRunner;

import java.util.HashMap;

import io.flutter.app.FlutterApplication;

public class CleverTapApplication extends FlutterApplication implements CTPushNotificationListener {

    private static final String TAG = "CleverTapApplication";

    @Override
    public void onCreate() {
        super.onCreate();

        CleverTapAppContextHolder.setApplicationContext(getApplicationContext());
        CleverTapAPI cleverTapAPI = CleverTapAPI.getDefaultInstance(this);
        if (cleverTapAPI != null) {
            cleverTapAPI.setCTPushNotificationListener(this);
        }
    }

    @Override
    public void onNotificationClickedPayloadReceived(HashMap<String, Object> payload) {
        //Notification is clicked in killed state
        Log.i(TAG, "onNotificationClickedPayloadReceived!");
        CleverTapBackgroundIsolateRunner.startBackgroundIsolate(this, payload);
    }
}
