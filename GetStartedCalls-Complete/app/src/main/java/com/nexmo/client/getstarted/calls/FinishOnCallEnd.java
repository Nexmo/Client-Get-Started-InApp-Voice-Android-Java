package com.nexmo.client.getstarted.calls;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.nexmo.client.NexmoCallEventListener;
import com.nexmo.client.NexmoCallMember;
import com.nexmo.client.NexmoCallStatus;
import com.nexmo.client.NexmoMediaActionState;

import java.lang.ref.WeakReference;

import static android.support.constraint.Constraints.TAG;

public class FinishOnCallEnd implements NexmoCallEventListener {
    private WeakReference<AppCompatActivity> activityRef;

    FinishOnCallEnd(AppCompatActivity activity) {
        this.activityRef = new WeakReference<>(activity);
    }

    @Override
    public void onMemberStatusUpdated(NexmoCallStatus nexmoCallStatus, NexmoCallMember nexmoCallMember) {
        if (((nexmoCallStatus == NexmoCallStatus.COMPLETED || nexmoCallStatus == NexmoCallStatus.CANCELED)) && (activityRef.get() != null)) {
            activityRef.get().finish();
        }
    }

    @Override
    public void onMuteChanged(NexmoMediaActionState nexmoMediaActionState, NexmoCallMember callMember) {
        Log.d(TAG, "NexmoCallEventListener.onMuteChanged: " + callMember.getMember().getUser().getName() + " : " + nexmoMediaActionState);
    }

    @Override
    public void onEarmuffChanged(NexmoMediaActionState nexmoMediaActionState, NexmoCallMember callMember) {
        Log.d(TAG, "NexmoCallEventListener.onEarmuffChanged: " + callMember.getMember().getUser().getName() + " : " + nexmoMediaActionState);
    }

    @Override
    public void onDTMF(String dtmf, NexmoCallMember callMember) {
        Log.d(TAG, "NexmoCallEventListener.onDTMF: " + callMember.getMember().getUser().getName() + " : " + dtmf);
    }
}
