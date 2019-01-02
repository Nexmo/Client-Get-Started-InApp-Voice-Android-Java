package com.nexmo.client.getstarted.calls;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

public class CreateCallActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_call);
        TextView title = findViewById(R.id.tvTitle);
        title.setText(String.format("Hi, %s!", NexmoHelper.getUserName()));
    }

    public void onInAppCallClick(View view) {
        //TODO implement
    }

}
