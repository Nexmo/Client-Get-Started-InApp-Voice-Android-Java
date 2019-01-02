package com.nexmo.client.getstarted.calls;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.nexmo.client.NexmoClient;
import com.nexmo.client.NexmoUser;
import com.nexmo.client.request_listener.NexmoApiError;
import com.nexmo.client.request_listener.NexmoRequestListener;

public class LoginActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

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
                NexmoHelper.user = user;

                Intent intent = new Intent(getBaseContext(), CreateCallActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
