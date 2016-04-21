package com.android.taste.sql.util;

import android.content.Context;


import com.android.taste.sql.database.DataBaseHelper;
import com.android.taste.sql.database.bean.DataBaseTest;
import com.android.taste.sql.database.bean.DataBaseSeting;
import com.android.taste.sql.database.bean.DataBaseUser;
import com.j256.ormlite.stmt.PreparedQuery;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wx on 2015/12/7.
 */
public class DataBaseUtil {
    public DataBaseHelper dataBaseHelper;

    public DataBaseUtil(Context context) {
        dataBaseHelper = DataBaseHelper.getInstance(context);
    }

    /**
     * 通过关键字获取药品列表
     */
    public List<DataBaseTest> getTestList(String sex) {
        List<DataBaseTest> drugs = new ArrayList<DataBaseTest>();
        List<DataBaseTest> drugs1 = null;
        try {
            PreparedQuery<DataBaseTest> query =
                    dataBaseHelper.getDataBaseDrugDoa().queryBuilder().orderBy(DataBaseTest.TID, true).where().not().eq(DataBaseTest.SEX,sex).prepare();
            drugs1 = dataBaseHelper.getDataBaseDrugDoa().query(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (drugs1 != null && drugs1.size() > 0) {
            drugs.addAll(drugs1);
        }
        return drugs;
    }


    /**
     * 保存测试
     */
    public void saveDrugList(List<DataBaseTest> tests) {
        try {
            for (DataBaseTest drug : tests) {
                dataBaseHelper.getDataBaseDrugDoa().create(drug);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public long getTestCount() {
        PreparedQuery<DataBaseTest> query = null;
        long i = 0;
        try {
            query = dataBaseHelper.getDataBaseDrugDoa().queryBuilder().setCountOf(true).prepare();
            i = dataBaseHelper.getDataBaseDrugDoa().countOf(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;

    }

    /**
     * 获取设置信息
     */
    public DataBaseSeting getDataBaseSeting() {
        DataBaseSeting setting = null;
        try {
            setting = dataBaseHelper.getDataBaseSetingDoa().queryForId(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return setting;
    }

    /**
     * 保存设置信息
     */
    public void saveDataBaseSeting(DataBaseSeting drug) {
        try {
            dataBaseHelper.getDataBaseSetingDoa().createOrUpdate(drug);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //保存用户信息
    public void saveDataBaseUser(DataBaseUser user) {
        try {
            dataBaseHelper.getDataBaseUser().create(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
