package com.nexmo.client.getstarted.calls;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Arrays;
import java.util.List;

public class LoginActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setUiAccordingToEnabledFeatures();

        NexmoHelper.init();
    }

    public void onLoginJaneClick(View view) {
        loginToSdk(NexmoHelper.JWT_JANE);
    }

    public void onLoginJoeClick(View view) {
        loginToSdk(NexmoHelper.JWT_JOE);
    }

    private void loginToSdk(String token) {
        //TODO: to complete
    }




    private void setUiAccordingToEnabledFeatures() {
        Button btnLoginJoe = findViewById(R.id.btnLoginJoe);
        List<NexmoHelper.Features> featuresList = Arrays.asList(NexmoHelper.enabledFeatures);
        btnLoginJoe.setVisibility(featuresList.contains(NexmoHelper.Features.IN_APP_to_IN_APP) ? View.VISIBLE : View.GONE);
    }
}
