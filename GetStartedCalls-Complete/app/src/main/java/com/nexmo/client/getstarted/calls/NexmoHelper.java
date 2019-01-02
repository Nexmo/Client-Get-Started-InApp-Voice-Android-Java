package com.nexmo.client.getstarted.calls;

import android.content.Context;

import com.nexmo.client.NexmoCall;
import com.nexmo.client.NexmoClient;
import com.nexmo.client.NexmoConnectionState;
import com.nexmo.client.NexmoUser;
import com.nexmo.client.request_listener.NexmoLoginListener;

import java.lang.ref.WeakReference;

class NexmoHelper {

    private static final String USER_NAME_JANE = "Jane";
    private static final String USER_NAME_JOE = "Joe";
    private static final String USER_ID_JANE = "USR-XXX"; //TODO: swap with the UserId you generated for Jane
    private static final String USER_ID_JOE = "USR-XXX"; //TODO: swap with the UserId you generated for Joe
    public static final String JWT_JANE = "PLACEHOLDER";//TODO: swap with the JWT you generated for Jane
    public static final String JWT_JOE = "PLACEHOLDER"; //TODO: swap with the JWT you generated for Joe

    public static NexmoUser user;
    public static NexmoCall currentCall;
    private static WeakReference<Context> contextRef;
    private static boolean didInit;


    static NexmoLoginListener loginListener = new NexmoLoginListener() {
        @Override
        public void onLoginStateChange(NexmoLoginListener.ELoginState eLoginState, NexmoLoginListener.ELoginStateReason eLoginStateReason) {
            //TODO implement
        }

        @Override
        public void onAvailabilityChange(NexmoLoginListener.EAvailability eAvailability, NexmoConnectionState nexmoConnectionState) {
            //TODO implement
        }
    };

    public static void init(Context appContext) {
        if (didInit) {
            return;
        }
        didInit = true;
        contextRef = new WeakReference<>(appContext);
        NexmoClient.init(new NexmoClient.NexmoClientConfig(), appContext, loginListener);
    }

    public static String getUserName() {
        return user.getName();
    }

    public static String getOtherUserName() {
        return user.getName().equals(USER_NAME_JANE) ? USER_NAME_JOE : USER_NAME_JANE;
    }

    public static String getOtherUserId() {
        return user.getName().equals(USER_NAME_JANE) ? USER_ID_JOE : USER_ID_JANE;
    }
}
