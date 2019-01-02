package com.nexmo.client.getstarted.calls;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.nexmo.client.NexmoCall;
import com.nexmo.client.NexmoCallEventListener;
import com.nexmo.client.request_listener.NexmoApiError;
import com.nexmo.client.request_listener.NexmoRequestListener;

public class IncomingCallActivity extends BaseActivity {

    NexmoCallEventListener callEventListener = new FinishOnCallEnd(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incoming_call);
    }

    @Override
    protected void onStart() {
        super.onStart();
        NexmoHelper.currentCall.addCallEventListener(callEventListener);
    }

    public void onAnswer(View view) {
        NexmoHelper.currentCall.answer(new NexmoRequestListener<NexmoCall>() {
            @Override
            public void onError(NexmoApiError nexmoApiError) {
                notifyError(nexmoApiError);
            }

            @Override
            public void onSuccess(NexmoCall call) {
                startActivity(new Intent(IncomingCallActivity.this, OnCallActivity.class));
                finish();
            }
        });
    }

    public void onHangup(View view) {
        NexmoHelper.currentCall.hangup();
        finish();
    }

    @Override
    protected void onStop() {
        NexmoHelper.currentCall.removeCallEventListener(callEventListener);
        super.onStop();
    }
}
