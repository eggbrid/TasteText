package com.android.taste;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.taste.personal.BaseInfoActivity;
import com.android.taste.sql.database.bean.DataBaseTest;
import com.android.taste.sql.util.DataBaseUtil;
import com.j256.ormlite.field.DatabaseField;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class MainActivity extends FragmentActivity implements View.OnClickListener {
    public Button personal;
    public Button setting;
    public Button group;
    private SweetAlertDialog pDialog;
    private List<DataBaseTest> list = new ArrayList<>();

    public void showDialog(String text) {
        pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText(text);
        pDialog.setCancelable(true);
        pDialog.show();
    }

    public void dismissDialog() {
        if (pDialog != null && pDialog.isShowing()) {
            pDialog.dismissWithAnimation();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        personal = (Button) findViewById(R.id.personal);
        group = (Button) findViewById(R.id.group);
        setting = (Button) findViewById(R.id.setting);
        personal.setOnClickListener(this);
        setting.setOnClickListener(this);
        group.setOnClickListener(this);
        if (new DataBaseUtil(this).getTestCount() == 0) {
            showDialog("正在初始化数据");
            new Thread(new Runnable() {
                @Override
                public void run() {
                    readText();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            dismissDialog();
                        }
                    });
                }
            }).start();
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.personal:
                MainActivity.this.startActivity(new Intent(MainActivity.this, BaseInfoActivity.class));
                break;
            default:
                Toast.makeText(MainActivity.this, "该功能暂未开放", Toast.LENGTH_LONG).show();
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        MyApplication.userList=null;
        MyApplication.pageList=null;
        MyApplication.allList=null;
        MyApplication.user=null;
    }

    public void readText() {
        try {
            InputStream is = getAssets().open("data.txt");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String text = new String(buffer, "Unicode");
            text = text.replace("\\r\\n", "");
            String[] strings = text.split(";");
            for (String s : strings) {
                String[] name = s.split(",");
                DataBaseTest test = new DataBaseTest();
                test.test_id = Integer.parseInt(name[0]);
                test.sex = name[1];
                test.name = name[2];
                test.ph_no = !TextUtils.isEmpty(name[3]) ? Integer.parseInt(name[3]) : 0;//平和-没有
                test.ph_has = !TextUtils.isEmpty(name[4]) ? Integer.parseInt(name[4]) : 0;//平和-经常
                test.qx_no = !TextUtils.isEmpty(name[5]) ? Integer.parseInt(name[5]) : 0;//气虚-没有
                test.qx_has = !TextUtils.isEmpty(name[6]) ? Integer.parseInt(name[6]) : 0;//气虚-经常
                test.xx_no = !TextUtils.isEmpty(name[7]) ? Integer.parseInt(name[7]) : 0;//血虚-没有
                test.xx_has = !TextUtils.isEmpty(name[8]) ? Integer.parseInt(name[8]) : 0;//血虚-经常
                test.yinx_no = !TextUtils.isEmpty(name[9]) ? Integer.parseInt(name[9]) : 0;//阴虚-没有
                test.yinx_has = !TextUtils.isEmpty(name[10]) ? Integer.parseInt(name[10]) : 0;//阴虚-经常
                test.yangx_no = !TextUtils.isEmpty(name[11]) ? Integer.parseInt(name[11]) : 0;//阳虚-没有
                test.yang_has = !TextUtils.isEmpty(name[12]) ? Integer.parseInt(name[12]) : 0;//阳虚-经常
                test.qy_no = !TextUtils.isEmpty(name[13]) ? Integer.parseInt(name[13]) : 0;//气郁-没有
                test.qy_has = !TextUtils.isEmpty(name[14]) ? Integer.parseInt(name[14]) : 0;//气郁-经常
                test.xy_no = !TextUtils.isEmpty(name[15]) ? Integer.parseInt(name[15]) : 0;//血淤-没有
                test.xy_has = !TextUtils.isEmpty(name[16]) ? Integer.parseInt(name[16]) : 0;//血淤-经常
                test.ts_no = !TextUtils.isEmpty(name[17]) ? Integer.parseInt(name[17]) : 0;//痰湿-没有
                test.ts_has = !TextUtils.isEmpty(name[18]) ? Integer.parseInt(name[18]) : 0;//痰湿-经常
                test.sr_no = !TextUtils.isEmpty(name[19]) ? Integer.parseInt(name[19]) : 0;//湿热-没有
                test.sr_has = !TextUtils.isEmpty(name[20]) ? Integer.parseInt(name[20]) : 0;//湿热-经常
                test.hr_no = !TextUtils.isEmpty(name[21]) ? Integer.parseInt(name[21]) : 0;//火热-没有
                test.hr_has = !TextUtils.isEmpty(name[22]) ? Integer.parseInt(name[22]) : 0;//火热-经常
                list.add(test);
            }
            new DataBaseUtil(MainActivity.this).saveDrugList(list);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitBy2Click(); //调用双击退出函数
        }
        return false;
    }
    /**
     * 双击退出函数
     */
    private static Boolean isExit = false;

    private void exitBy2Click() {
        Timer tExit = null;
        if (isExit == false) {
            isExit = true; // 准备退出
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            tExit = new Timer();
            tExit.schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false; // 取消退出
                }
            }, 2000); // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务

        } else {
            finish();
            System.exit(0);
        }
    }
}
