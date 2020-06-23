package com.nexmo.client.getstarted.calls;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.nexmo.client.NexmoClient;
import com.nexmo.client.NexmoUser;
import com.nexmo.client.request_listener.NexmoApiError;
import com.nexmo.client.request_listener.NexmoConnectionListener;
import com.nexmo.client.request_listener.NexmoRequestListener;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

public class LoginActivity extends BaseActivity {

    private static final String TAG = "Nexmo-get-started";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setUiAccordingToEnabledFeatures();

        NexmoHelper.init(getApplicationContext());

        NexmoClient.get().setConnectionListener(new NexmoConnectionListener() {

            @Override
            public void onConnectionStatusChange(@NotNull ConnectionStatus status, @NotNull ConnectionStatusReason reason) {
                Log.d(TAG, "NexmoConnectionListener.onConnectionStatusChange : $status : $reason");
                if(status == ConnectionStatus.CONNECTED) {
                    Intent intent = new Intent(getBaseContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    public void onLoginJaneClick(View view) {
        loginToSdk(NexmoHelper.JWT_JANE);
    }

    public void onLoginJoeClick(View view) {
        loginToSdk(NexmoHelper.JWT_JOE);
    }

    private void loginToSdk(String token) {
        NexmoClient.get().login(token);
    }

    private void setUiAccordingToEnabledFeatures() {
        Button btnLoginJoe = findViewById(R.id.btnLoginJoe);
        List<NexmoHelper.Features> featuresList = Arrays.asList(NexmoHelper.enabledFeatures);
        btnLoginJoe.setVisibility(featuresList.contains(NexmoHelper.Features.IN_APP_to_IN_APP)? View.VISIBLE:View.GONE);
    }

}
