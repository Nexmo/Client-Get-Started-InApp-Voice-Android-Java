package com.nexmo.client.getstarted.calls;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.nexmo.client.NexmoCallEventListener;
import com.nexmo.client.NexmoCallMember;
import com.nexmo.client.NexmoCallMemberStatus;
import com.nexmo.client.NexmoMediaActionState;

import java.lang.ref.WeakReference;
import java.util.logging.Logger;

import static android.support.constraint.Constraints.TAG;

public class FinishOnCallEnd implements NexmoCallEventListener {
    private WeakReference<AppCompatActivity> activityRef;

    FinishOnCallEnd(AppCompatActivity activity) {
        this.activityRef = new WeakReference<>(activity);
    }

    @Override
    public void onMemberStatusUpdated(NexmoCallMemberStatus nexmoCallStatus, NexmoCallMember nexmoCallMember) {
        if (((nexmoCallStatus == NexmoCallMemberStatus.COMPLETED || nexmoCallStatus == NexmoCallMemberStatus.CANCELED)) && (activityRef.get() != null)) {
            activityRef.get().finish();
        }
    }

    @Override
    public void onMuteChanged(NexmoMediaActionState nexmoMediaActionState, NexmoCallMember callMember) {
        Log.d(TAG, "NexmoCallEventListener.onMuteChanged: " + callMember.getUser().getName() + " : " + nexmoMediaActionState);
    }

    @Override
    public void onEarmuffChanged(NexmoMediaActionState nexmoMediaActionState, NexmoCallMember callMember) {
        Log.d(TAG, "NexmoCallEventListener.onEarmuffChanged: " + callMember.getUser().getName() + " : " + nexmoMediaActionState);
    }

    @Override
    public void onDTMF(String dtmf, NexmoCallMember callMember) {
        Log.d(TAG, "NexmoCallEventListener.onDTMF: " + callMember.getUser().getName() + " : " + dtmf);
    }
}
