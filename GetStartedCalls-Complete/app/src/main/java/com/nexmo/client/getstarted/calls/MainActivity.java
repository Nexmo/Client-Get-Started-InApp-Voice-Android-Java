package com.nexmo.client.getstarted.calls;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.nexmo.client.NexmoCall;
import com.nexmo.client.NexmoCallHandler;
import com.nexmo.client.NexmoClient;
import com.nexmo.client.NexmoIncomingCallListener;
import com.nexmo.client.request_listener.NexmoApiError;
import com.nexmo.client.request_listener.NexmoRequestListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends BaseActivity {


    private NexmoIncomingCallListener incomingCallListener = new NexmoIncomingCallListener() {
        @Override
        public void onIncomingCall(NexmoCall call) {

            NexmoHelper.currentCall = call;
            startActivity(new Intent(MainActivity.this, IncomingCallActivity.class));
        }
    };


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUiAccordingToEnabledFeatures();
    }

    @Override
    protected void onStart() {
        super.onStart();
        NexmoClient.get().addIncomingCallListener(incomingCallListener);
    }

    NexmoRequestListener<NexmoCall> callListener = new NexmoRequestListener<NexmoCall>() {
        @Override
        public void onError(NexmoApiError nexmoApiError) {
            notifyError(nexmoApiError);
        }

        @Override
        public void onSuccess(NexmoCall call) {
            NexmoHelper.currentCall = call;

            Intent intent = new Intent(MainActivity.this, OnCallActivity.class);
            startActivity(intent);
        }
    };

    public void onInAppCallClick(View view) {
        List<String> callee = new ArrayList<>();
        callee.add(NexmoHelper.getOtherUserName());

        NexmoClient.get().call(callee, NexmoCallHandler.IN_APP, callListener);
    }

    public void onPhoneCallClick(View view) {
        List<String> callee = new ArrayList<>();
        callee.add("PLACEHOLDER");  //TODO: swap with your phone number

        NexmoClient.get().call(callee, NexmoCallHandler.SERVER, callListener);
    }

    @Override
    protected void onStop() {
        NexmoClient.get().removeIncomingCallListeners();
        super.onStop();
    }



    private void setUiAccordingToEnabledFeatures() {
        TextView title = findViewById(R.id.tvTitle);
        TextView tvOr1 = findViewById(R.id.tvOr1);
        TextView tvOr2 = findViewById(R.id.tvOr2);
        Button btnCallUser = findViewById(R.id.btnCallUser);
        Button btnCallPhone = findViewById(R.id.btnCallPhone);

        title.setText(String.format("Hi, %s!", NexmoHelper.getUserName()));

        List<NexmoHelper.Features> featuresList = Arrays.asList(NexmoHelper.enabledFeatures);
        if (featuresList.contains(NexmoHelper.Features.IN_APP_to_IN_APP)){
            tvOr1.setVisibility(View.VISIBLE);
            btnCallUser.setVisibility(View.VISIBLE);
            btnCallUser.setText(String.format("In-App call to user %s", NexmoHelper.getUserName()));
        } else {
            tvOr1.setVisibility(View.GONE);
            btnCallUser.setVisibility(View.GONE);
        }

        if (featuresList.contains(NexmoHelper.Features.IN_APP_to_PHONE)){
            tvOr2.setVisibility(View.VISIBLE);
            btnCallPhone.setVisibility(View.VISIBLE);
        } else {
            tvOr2.setVisibility(View.GONE);
            btnCallPhone.setVisibility(View.GONE);
        }
    }
}
