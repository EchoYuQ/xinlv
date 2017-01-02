package com.lyz.xinlv.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioButton;

/**
 * Created by yuqing on 2017/1/1.
 */
public class SaveDateActivity extends Activity{

    private EditText et_mUserName;
    private EditText et_mAge;
    private RadioButton rb_mSex;
    private EditText et_mFatigue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_savedata);
        init();
    }

    void init(){
        et_mUserName= (EditText) findViewById(R.id.id_et_usermame);
        et_mAge= (EditText) findViewById(R.id.id_et_age);
        et_mFatigue= (EditText) findViewById(R.id.id_et_fatigue);
        et_mUserName= (EditText) findViewById(R.id.id_et_usermame);
    }
}

