package com.android.taste;

import com.android.taste.sql.database.bean.DataBaseTest;
import com.android.taste.sql.database.bean.DataBaseUser;
import com.android.taste.sql.database.bean.User;

import java.util.List;

/**
 * Created by xus on 2016/4/22.
 */
public class MyApplication extends android.app.Application {
    public static List<DataBaseTest> allList;
    public static List<DataBaseTest> userList;
    public static List<DataBaseTest> pageList;
    public static DataBaseUser user;
}
