package com.zx.customviews;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.zx.customviews.UnReadMsgView.UnReadMsgView;

public class UnReadMsgActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mSetNumBtn;
    private Button mClearBtn;

    private UnReadMsgView mMsgRb;
    private UnReadMsgView mContactsRb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_un_read_msg);
        mSetNumBtn = (Button) findViewById(R.id.set_num);
        mClearBtn = (Button) findViewById(R.id.clear_num);

        mMsgRb = (UnReadMsgView) findViewById(R.id.rb_home_tab_msg);
        mContactsRb = (UnReadMsgView) findViewById(R.id.rb_home_tab_contacts);


        mSetNumBtn.setOnClickListener(this);
        mClearBtn.setOnClickListener(this);

        mMsgRb.setOnClickListener(this);
        mContactsRb.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.set_num:
                mMsgRb.setmNum(60);
                break;
            case R.id.clear_num:
                mContactsRb.clearNum();
                break;
            case R.id.rb_home_tab_contacts:
                Toast.makeText(this, "点击...", Toast.LENGTH_LONG).show();
                break;
            case R.id.rb_home_tab_msg:
                Toast.makeText(this, "点击...", Toast.LENGTH_LONG).show();
                break;
        }
    }
}