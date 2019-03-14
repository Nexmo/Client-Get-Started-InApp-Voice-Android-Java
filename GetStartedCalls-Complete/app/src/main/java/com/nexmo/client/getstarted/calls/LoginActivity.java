package com.nexmo.client.getstarted.calls;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.nexmo.client.NexmoClient;
import com.nexmo.client.NexmoUser;
import com.nexmo.client.request_listener.NexmoApiError;
import com.nexmo.client.request_listener.NexmoRequestListener;

import java.util.Arrays;
import java.util.List;

public class LoginActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setUiAccordingToEnabledFeatures();

        NexmoHelper.init(getApplicationContext());
    }

    public void onLoginJaneClick(View view) {
        loginToSdk(NexmoHelper.JWT_JANE);
    }

    public void onLoginJoeClick(View view) {
        loginToSdk(NexmoHelper.JWT_JOE);
    }

    private void loginToSdk(String token) {
        NexmoClient.get().login(token, new NexmoRequestListener<NexmoUser>() {

            @Override
            public void onError(NexmoApiError nexmoApiError) {
                notifyError(nexmoApiError);
            }

            @Override
            public void onSuccess(NexmoUser user) {
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }



    private void setUiAccordingToEnabledFeatures() {
        Button btnLoginJoe = findViewById(R.id.btnLoginJoe);
        List<NexmoHelper.Features> featuresList = Arrays.asList(NexmoHelper.enabledFeatures);
        btnLoginJoe.setVisibility(featuresList.contains(NexmoHelper.Features.IN_APP_to_IN_APP)? View.VISIBLE:View.GONE);
    }

}
