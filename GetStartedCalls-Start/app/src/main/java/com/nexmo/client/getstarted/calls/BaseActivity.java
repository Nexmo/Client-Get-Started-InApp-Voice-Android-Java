package com.nexmo.client.getstarted.calls;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;

class BaseActivity extends AppCompatActivity {

    private final int CALL_PERMISSIONS_REQ = 121;

    private final static String[] callsPermissions = {android.Manifest.permission.READ_PHONE_STATE, android.Manifest.permission.RECORD_AUDIO,
            android.Manifest.permission.PROCESS_OUTGOING_CALLS, android.Manifest.permission.ACCESS_WIFI_STATE,
            android.Manifest.permission.CHANGE_WIFI_STATE, android.Manifest.permission.ACCESS_NETWORK_STATE,
            android.Manifest.permission.MODIFY_AUDIO_SETTINGS};

    @Override
    protected void onStart() {
        super.onStart();
        if (doesNeedCallPermissions()) {
            requestCallPermissions();
        }
    }

    public boolean doesNeedCallPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            for (String perm : callsPermissions) {
                if (ActivityCompat.checkSelfPermission(this, perm) != PackageManager.PERMISSION_GRANTED) {
                    return true;
                }
            }
        }
        return false;
    }

    public void requestCallPermissions() {
        ActivityCompat.requestPermissions(this, callsPermissions, CALL_PERMISSIONS_REQ);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode != CALL_PERMISSIONS_REQ) {
            requestCallPermissions();
        }
    }
}

