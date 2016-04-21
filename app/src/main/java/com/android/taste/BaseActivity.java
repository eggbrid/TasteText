package com.android.taste;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by xus on 2016/4/20.
 */
public class BaseActivity extends FragmentActivity implements View.OnClickListener {
    public Button back;
    public Button home;
    public TextView title;
    private SweetAlertDialog pDialog;

    public void showToast(String s) {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }

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

    public void initTitle(boolean isShowBack, boolean isShowHome, String titles) {
        back = (Button) findViewById(R.id.back);
        home = (Button) findViewById(R.id.home);
        title = (TextView) findViewById(R.id.title);
        back.setVisibility(isShowBack?View.VISIBLE:View.GONE);
        home.setVisibility(isShowHome?View.VISIBLE:View.GONE);
        back.setOnClickListener(this);
        home.setOnClickListener(this);
        title.setText(titles);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                this.finish();
                break;
            case R.id.home:
                BaseActivity.this.startActivity(new Intent(BaseActivity.this, MainActivity.class));
                break;
        }
    }
}
