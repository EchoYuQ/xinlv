package com.lyz.xinlv.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lyz.Bean.UserDataBean;
import com.lyz.SG.SGFilter;
import com.lyz.utils.SaveData;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by yuqing on 2017/1/1.
 */
public class SaveDateActivity extends Activity implements View.OnClickListener {

    private EditText et_mUserName;
    private EditText et_mAge;
    private RadioGroup rg_mSex;
    private RadioButton rb_mMan;
    private RadioButton rb_mWoman;
    private EditText et_mFatigue;
    private Button btn_mSaveDataBtn;
    private UserDataBean mUserDataBean = new UserDataBean();
    private String mfileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_savedata);
        init();
    }

    void init() {
        et_mUserName = (EditText) findViewById(R.id.id_et_usermame);
        et_mAge = (EditText) findViewById(R.id.id_et_age);
        et_mFatigue = (EditText) findViewById(R.id.id_et_fatigue);
        et_mUserName = (EditText) findViewById(R.id.id_et_usermame);
        btn_mSaveDataBtn = (Button) findViewById(R.id.id_btn_savedata);
        btn_mSaveDataBtn.setOnClickListener(this);

        rg_mSex = (RadioGroup) findViewById(R.id.id_rg_sex);
        rb_mMan = (RadioButton) findViewById(R.id.id_rb_man);
        rb_mMan.setChecked(true);
        rb_mWoman = (RadioButton) findViewById(R.id.id_rb_woman);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_btn_savedata:
                final UserDataBean userDataBean = (UserDataBean) getIntent().getSerializableExtra("userdatabean");
                Log.i("Constants.datas", userDataBean.getDatas().toString());

                // 将源数据List转成数组
                List<Double> data_origin_list=userDataBean.getDatas();
                double[] data_origin=new double[data_origin_list.size()];
                for(int i=0;i<data_origin_list.size();i++)
                {
                    data_origin[i]=data_origin_list.get(i);
                }
                double[] data_smoothed=new double[data_origin_list.size()];

                // SG算法的参数矩阵
                double[] coeffs = SGFilter.computeSGCoefficients(5, 5, 5);
                // SG算法去噪处理
                data_smoothed=new SGFilter(5, 5).smooth(data_origin, coeffs);

                List<Double> data_smoothed_list=new ArrayList<Double>();
                for(int i=0;i<data_smoothed.length;i++)
                {
                    data_smoothed_list.add(data_smoothed[i]);
                }
                Log.i("data_smoothed.length",data_smoothed.length+"");

                userDataBean.setNew_datas(data_smoothed_list);

                long currenttime = System.currentTimeMillis();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd-HHmmss");
                Date date = new Date(currenttime);
                String currentTimeStr = dateFormat.format(date);
                userDataBean.setCurrentTime(currentTimeStr);

                String username = et_mUserName.getText().toString().trim();
                if (username.equals("")) {
                    Toast.makeText(SaveDateActivity.this, "请输入您的姓名", Toast.LENGTH_SHORT).show();
                    break;
                } else {
                    userDataBean.setUserName(username);
                }

                if (et_mAge.getText().toString().equals("")) {
                    Toast.makeText(SaveDateActivity.this, "请输入您的年龄", Toast.LENGTH_SHORT).show();
                    break;
                } else {
                    int age = Integer.parseInt(et_mAge.getText().toString());
                    Log.i("age",age+"");
                    if (age >= 1 && age <= 100) {
                        userDataBean.setAge(age);
                    } else {
                        Toast.makeText(SaveDateActivity.this, "请输入您正确的年龄，范围1-100", Toast.LENGTH_SHORT).show();
                        break;
                    }
                }

                rg_mSex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        if (checkedId == rb_mMan.getId()) {
                            userDataBean.setSex(0);
                        } else {
                            userDataBean.setSex(1);
                        }
                    }
                });
                if (et_mFatigue.getText().toString().equals("")) {
                    Toast.makeText(SaveDateActivity.this, "请输入您的疲劳值", Toast.LENGTH_SHORT).show();
                    break;

                } else {
                    int fatigue = Integer.parseInt(et_mFatigue.getText().toString());
                    if (fatigue >= 0 && fatigue <= 100) {
                        userDataBean.setFatigue(fatigue);
                    } else {
                        Toast.makeText(SaveDateActivity.this, "请输入您的正确的疲劳值，范围0-100", Toast.LENGTH_SHORT).show();
                        break;
                    }
                }


                Gson gson = new Gson();

                final String jsonstring = gson.toJson(userDataBean);
                mfileName = currentTimeStr;
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        SaveData.saveData2Sdcard(jsonstring, mfileName);

                    }
                }).start();
                Toast.makeText(SaveDateActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
                break;
        }

    }
}

