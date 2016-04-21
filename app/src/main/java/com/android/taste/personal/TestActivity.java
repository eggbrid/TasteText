package com.android.taste.personal;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.android.taste.BaseActivity;
import com.android.taste.MainActivity;
import com.android.taste.MyApplication;
import com.android.taste.R;
import com.android.taste.personal.adapter.TestAdapter;
import com.android.taste.sql.database.bean.DataBaseTest;
import com.android.taste.sql.util.DataBaseUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xus on 2016/4/21.
 */
public class TestActivity extends BaseActivity {
    private ListView test;
    private Button last, next;

    private TestAdapter adapter;
    private boolean isfirstPage = true;
    private boolean isLatsPage = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("wangxu","onCreate");
        setContentView(R.layout.activity_test);
        initTitle(true, false, "体制辨识");
        initView();
        initData();
        setPage();
    }

    public void nextPage() {
        last.setVisibility(View.VISIBLE);

        isfirstPage = false;
        for (DataBaseTest test : MyApplication.pageList) {
            if (TextUtils.isEmpty(test.isno)) {
                showToast("本页还有没回答的问题");
                return;
            }
        }
        MyApplication.userList.addAll(MyApplication.pageList);
        MyApplication.pageList.clear();
        if (MyApplication.allList.size() > 5) {
            MyApplication.pageList.addAll(MyApplication.allList.subList(0, 5));
            MyApplication.allList.removeAll(MyApplication.pageList);
        } else {
            MyApplication.pageList.addAll(MyApplication.allList);
            MyApplication.allList.removeAll(MyApplication.pageList);
            next.setText("完成测试");
            isLatsPage = true;
        }
        adapter.notifyDataSetChanged();


    }
    public void lastPage() {
        next.setText("下一页");

        isLatsPage = false;
        MyApplication.allList.addAll(0,MyApplication.pageList);
        MyApplication.pageList.clear();
        if (MyApplication.userList.size() > 5) {
            MyApplication.pageList.addAll(MyApplication.userList.subList(MyApplication.userList.size()-5, MyApplication.userList.size()));
            MyApplication.userList.removeAll(MyApplication.pageList);
        } else {
            MyApplication.pageList.addAll(MyApplication.userList);
            MyApplication.userList.removeAll(MyApplication.pageList);
            last.setVisibility(View.GONE);
            next.setText("下一页");
            isfirstPage = true;
        }
        adapter.notifyDataSetChanged();
    }
    public void setPage() {
        adapter = new TestAdapter(this, MyApplication.pageList);
        test.setAdapter(adapter);
    }

    private void initView() {
        test = (ListView) findViewById(R.id.test);
        last = (Button) findViewById(R.id.last);
        next = (Button) findViewById(R.id.next);
        last.setOnClickListener(this);
        next.setOnClickListener(this);

    }

    public void initData() {
        MyApplication.allList = new DataBaseUtil(this).getTestList(MyApplication.user.usersex.equals("男")?"女":"男");
        MyApplication.userList = new ArrayList<>();
        MyApplication.pageList = new ArrayList<>();
        MyApplication.pageList.addAll(MyApplication.allList.subList(0, 5));
        MyApplication.allList.removeAll(MyApplication.pageList);
        last.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.next:
                if (isLatsPage)
                    startActivity(new Intent(this,OtherInfoActivity.class));
                else
                nextPage();
                break;
            case R.id.last:
                lastPage();
                break;

            case R.id.back:
                if (isfirstPage)
                    this.finish();
                else
                    lastPage();

                break;

        }
    }
}
