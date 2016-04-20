package com.android.taste.sql.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.android.taste.sql.database.bean.DataBaseTest;
import com.android.taste.sql.database.bean.DataBaseUser;
import com.android.taste.sql.database.bean.DataBaseSeting;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Created by wx on 2015/12/3.
 */
public class DataBaseHelper extends OrmLiteSqliteOpenHelper {
    public static final String DATABASE_NAME = "pushMessage.db";
    private static final int DATABASE_VERSON = 2;

    public DataBaseHelper(Context context, String databaseName, SQLiteDatabase.CursorFactory factory, int databaseVersion) {
        super(context, databaseName, factory, databaseVersion);
    }

    private static DataBaseHelper dataBaseHelper;

    public static synchronized DataBaseHelper getInstance(Context context) {
        if (dataBaseHelper == null) {
            dataBaseHelper = new DataBaseHelper(context, DATABASE_NAME, null, DATABASE_VERSON);
        }
        return dataBaseHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try {
            Log.e("wangxu", "onCreate");

            TableUtils.createTable(connectionSource, DataBaseUser.class);
            TableUtils.createTable(connectionSource, DataBaseTest.class);
            TableUtils.createTable(connectionSource, DataBaseSeting.class);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i1) {

    }


    public Dao<DataBaseUser, Integer> getMessageDoa() throws SQLException {
        return getDao(DataBaseUser.class);
    }

    public Dao<DataBaseTest, Integer> getDataBaseDrugDoa() throws SQLException {
        return getDao(DataBaseTest.class);
    }

    public Dao<DataBaseSeting, Integer> getDataBaseSetingDoa() throws SQLException {
        return getDao(DataBaseSeting.class);
    }
}
