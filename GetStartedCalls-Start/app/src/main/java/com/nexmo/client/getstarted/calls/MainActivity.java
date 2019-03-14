package com.nexmo.client.getstarted.calls;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUiAccordingToEnabledFeatures();
    }

    public void onInAppCallClick(View view) {
        //TODO implement
    }

    public void onPhoneCallClick(View view) {
        //TODO implement
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
