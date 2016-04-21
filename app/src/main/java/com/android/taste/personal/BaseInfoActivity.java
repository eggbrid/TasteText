package com.android.taste.personal;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.android.taste.BaseActivity;
import com.android.taste.MyApplication;
import com.android.taste.R;
import com.android.taste.sql.database.bean.DataBaseUser;
import com.android.taste.sql.database.bean.User;

import java.util.Calendar;

/**
 * Created by xus on 2016/4/20.
 */
public class BaseInfoActivity extends BaseActivity {
    Calendar calendar = Calendar.getInstance();
    private Button birthday;
    private EditText name, mobile;
    private CheckBox man, woman;
    private Button submit;
    private String birthdays = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_info);
        initTitle(true, false, "基本信息");
        initView();

    }

    public void initView() {
        birthday = (Button) findViewById(R.id.birthday);
        name = (EditText) findViewById(R.id.name);
        mobile = (EditText) findViewById(R.id.mobile);
        woman = (CheckBox) findViewById(R.id.woman);
        man = (CheckBox) findViewById(R.id.man);
        submit = (Button) findViewById(R.id.submit);
        birthday.setOnClickListener(this);
        man.setOnClickListener(this);
        woman.setOnClickListener(this);
        submit.setOnClickListener(this);

    }

    public void showTime() {
        DatePickerDialog datePicker = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                birthdays = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                birthday.setText(birthdays);
            }
        }, calendar
                .get(Calendar.YEAR), calendar
                .get(Calendar.MONTH), calendar
                .get(Calendar.DAY_OF_MONTH));
        datePicker.setTitle("选择出生日期");
        datePicker.show();
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.birthday:
                showTime();
                break;
            case R.id.woman:
                man.setChecked(false);
                woman.setChecked(true);
                break;
            case R.id.man:
                man.setChecked(true);
                woman.setChecked(false);
                break;
            case R.id.submit:
                subMit();
                break;
        }
    }

    public void subMit() {
        String names = name.getText().toString();
        String mobiles = mobile.getText().toString();
        String sex = man.isChecked() ? "男" : "女";

        if (TextUtils.isEmpty(names)) {
            showToast("请填写您的姓名");
            return;
        }
        if (TextUtils.isEmpty(birthdays)) {
            showToast("请选择那您得生日");
            return;
        }
        if (TextUtils.isEmpty(mobiles)) {
            showToast("请填写您的电话");
            return;
        }
        if (mobiles.length()!=11){
            showToast("请填写正确的电话号码");
            return;
        }
        DataBaseUser user = new DataBaseUser();
        user.userbirthsay = birthdays;
        user.username = names;
        user.usersex = sex;
        user.userPhone = mobiles;
        MyApplication.user=user;
        Intent intent = new Intent(this, TestActivity.class);
        startActivity(intent);
    }
}
