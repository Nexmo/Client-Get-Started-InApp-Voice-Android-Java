package com.nexmo.client.getstarted.calls;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.nexmo.client.NexmoCall;
import com.nexmo.client.NexmoCallEventListener;
import com.nexmo.client.request_listener.NexmoApiError;
import com.nexmo.client.request_listener.NexmoRequestListener;

public class OnCallActivity extends BaseActivity {

    NexmoCallEventListener callEventListener = new FinishOnCallEnd(this);


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_call);
    }

    @Override
    protected void onStart() {
        super.onStart();
        NexmoHelper.currentCall.addCallEventListener(callEventListener);
    }

    public void onHangup(View view) {
        NexmoHelper.currentCall.hangup(new NexmoRequestListener<NexmoCall>() {
            @Override
            public void onError(NexmoApiError nexmoApiError) {

            }

            @Override
            public void onSuccess(NexmoCall nexmoCall) {
                finish();
            }
        });
    }

    @Override
    protected void onStop() {
        NexmoHelper.currentCall.removeCallEventListener(callEventListener);
        super.onStop();
    }

}
