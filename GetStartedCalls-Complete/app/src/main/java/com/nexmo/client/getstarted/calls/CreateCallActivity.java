package com.nexmo.client.getstarted.calls;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.nexmo.client.NexmoCall;
import com.nexmo.client.NexmoCallHandler;
import com.nexmo.client.NexmoClient;
import com.nexmo.client.NexmoIncomingCallListener;
import com.nexmo.client.request_listener.NexmoApiError;
import com.nexmo.client.request_listener.NexmoRequestListener;

import java.util.ArrayList;
import java.util.List;

public class CreateCallActivity extends BaseActivity {


    private NexmoIncomingCallListener incomingCallListener = new NexmoIncomingCallListener() {
        @Override
        public void onIncomingCall(NexmoCall call) {

            NexmoHelper.currentCall = call;
            startActivity(new Intent(CreateCallActivity.this, IncomingCallActivity.class));
        }
    };


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_call);
        TextView title = findViewById(R.id.tvTitle);
        title.setText(String.format("Hi, %s!", NexmoHelper.getUserName()));
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

            Intent intent = new Intent(CreateCallActivity.this, OnCallActivity.class);
            startActivity(intent);
        }
    };

    public void onInAppCallClick(View view) {
        List<String> callee = new ArrayList<>();
        callee.add(NexmoHelper.getOtherUserName());

        NexmoClient.get().call(callee, NexmoCallHandler.IN_APP, callListener);
    }

    @Override
    protected void onStop() {
        NexmoClient.get().removeIncomingCallListeners();
        super.onStop();
    }

}
