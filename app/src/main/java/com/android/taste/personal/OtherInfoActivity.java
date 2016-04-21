package com.android.taste.personal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.taste.BaseActivity;
import com.android.taste.MyApplication;
import com.android.taste.R;
import com.android.taste.sql.util.DataBaseUtil;

/**
 * Created by xus on 2016/4/22.
 */
public class OtherInfoActivity extends BaseActivity {
    private EditText ehight, eweight, ebloodsugar, ebloodpressure;
    private Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_info);
        initTitle(true, false, "补充信息");
        initView();
    }

    private void initView(){
        ehight = (EditText) findViewById(R.id.ehight);
        eweight = (EditText) findViewById(R.id.eweight);
        ebloodsugar = (EditText) findViewById(R.id.ebloodsugar);
        ebloodpressure = (EditText) findViewById(R.id.ebloodpressure);
        submit = (Button) findViewById(R.id.submit);
        submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (v.getId()==R.id.submit){
            MyApplication.user.userhight=ehight.getText().toString();
            MyApplication.user.userweight=eweight.getText().toString();
            MyApplication.user.bloodsugar=ebloodsugar.getText().toString();
            MyApplication.user.bloodpressure=ebloodpressure.getText().toString();
            new DataBaseUtil(this).saveDataBaseUser(MyApplication.user);
startActivity(new Intent(this, TestResultActivity.class));
        }
    }
}
