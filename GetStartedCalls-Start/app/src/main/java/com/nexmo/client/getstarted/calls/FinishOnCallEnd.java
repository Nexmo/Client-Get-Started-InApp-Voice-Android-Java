package com.nexmo.client.getstarted.calls;

import android.support.v7.app.AppCompatActivity;

import com.nexmo.client.NexmoCallEventListener;
import com.nexmo.client.NexmoCallMember;
import com.nexmo.client.NexmoCallStatus;
import com.nexmo.client.NexmoMediaActionState;

import java.lang.ref.WeakReference;

public class FinishOnCallEnd implements NexmoCallEventListener {
    private WeakReference<AppCompatActivity> activityRef;

    public FinishOnCallEnd(AppCompatActivity activity) {
        this.activityRef = new WeakReference<>(activity);
    }

    @Override
    public void onMemberStatusUpdated(NexmoCallStatus nexmoCallStatus, NexmoCallMember nexmoCallMember) {
        if (((nexmoCallStatus == NexmoCallStatus.COMPLETED || nexmoCallStatus == NexmoCallStatus.CANCELED)) && (activityRef.get() != null)) {
            activityRef.get().finish();
        }
    }

    @Override
    public void onMuteChanged(NexmoMediaActionState nexmoMediaActionState, NexmoCallMember nexmoCallMember) {

    }

    @Override
    public void onEarmuffChanged(NexmoMediaActionState nexmoMediaActionState, NexmoCallMember nexmoCallMember) {

    }
}
