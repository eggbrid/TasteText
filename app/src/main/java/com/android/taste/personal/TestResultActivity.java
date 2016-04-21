package com.android.taste.personal;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.taste.BaseActivity;
import com.android.taste.MyApplication;
import com.android.taste.R;
import com.android.taste.sql.database.bean.DataBaseTest;
import com.j256.ormlite.field.DatabaseField;

/**
 * Created by xus on 2016/4/22.
 */
public class TestResultActivity extends BaseActivity {
    private TextView user_info, user_body, about;
    private Button last, next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_result);
        initTitle(true,true,"综合信息");
        user_info = (TextView) findViewById(R.id.user_info);
        user_body = (TextView) findViewById(R.id.user_body);
        about = (TextView) findViewById(R.id.about);
        last=(Button)findViewById(R.id.last);
        next=(Button)findViewById(R.id.next);
        last.setOnClickListener(this);
        next.setOnClickListener(this);
        String user = "姓名：" + MyApplication.user.username + "  |  性别：" + MyApplication.user.usersex + "\n生日：" + MyApplication.user.userbirthsay + "\n电话："
                + MyApplication.user.userPhone + "\n" + (TextUtils.isEmpty(MyApplication.user.userweight) ? "" : "身高："
                + MyApplication.user.userhight + "cm |  ") + (TextUtils.isEmpty(MyApplication.user.userweight) ? "" : "体重：" + MyApplication.user.userweight) + "kg\n"
                + (TextUtils.isEmpty(MyApplication.user.bloodpressure) ? "" : "血糖：" + MyApplication.user.bloodpressure) + "mmol/L   |   "
                + (TextUtils.isEmpty(MyApplication.user.bloodpressure) ? "" : "血压：" + MyApplication.user.bloodpressure) + "mmhg";
        user_info.setText(user);
        getBoday();
    }

    public void getBoday() {
        showDialog("正在计算结果");
        new Thread(new Runnable() {

            @Override
            public void run() {
                int ph = 0;
                int qx = 0;
                int xx = 0;
                int yinx = 0;
                int yangx = 0;
                int qy = 0;
                int xy = 0;
                int ts = 0;
                int sr = 0;
                int hr = 0;
                for (DataBaseTest test : MyApplication.userList) {
                    if (test.isno.equals("is")) {
                        ph = ph + test.qy_has;
                        qx = qx + test.qx_has;
                        xx = xx + test.xx_has;
                        yinx = yinx + test.yinx_has;
                        yangx = yangx + test.yang_has;
                        qy = qy + test.qy_has;
                        xy = xy + test.xy_has;
                        ts = ts + test.ts_has;
                        sr = sr + test.sr_has;
                        hr = hr + test.hr_has;
                    } else {
                        ph = ph + test.qy_no;
                        qx = qx + test.qx_no;
                        xx = xx + test.xx_no;
                        yinx = yinx + test.yinx_no;
                        yangx = yangx + test.yangx_no;
                        qy = qy + test.qy_no;
                        xy = xy + test.xy_no;
                        ts = ts + test.ts_no;
                        sr = sr + test.sr_no;
                        hr = hr + test.hr_no;
                    }
                }
                String boday = "您的体质可能为：";
                if (ph >= 15)
                    boday = boday + " 平和 ";
                if (qx >= 15)
                    boday = boday + " 气虚 ";
                if (xx >= 15)
                    boday = boday + " 血虚 ";
                if (yinx >= 15)
                    boday = boday + " 阴虚 ";
                if (yangx >= 15)
                    boday = boday + " 阳虚 ";
                if (qy >= 15)
                    boday = boday + " 气郁 ";
                if (xy >= 15)
                    boday = boday + " 血瘀 ";
                if (ts >= 15)
                    boday = boday + " 痰湿 ";
                if (sr >= 15)
                    boday = boday + " 湿热 ";
                if (hr >= 15)
                    boday = boday + " 火热 ";
                final String bodys = boday;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        dismissDialog();
                        user_body.setText(bodys);
                    }
                });
            }
        }).start();
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.last:
                this.finish();
                break;
            case R.id.next:
                showToast("该功能暂未开放");
                break;
        }
    }
}
