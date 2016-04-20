package com.android.taste.sql.database.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by wx on 2015/12/14.
 */
@DatabaseTable
public class DataBaseUser {
    public static final String ID = "id", NAME = "user_name";
    @DatabaseField(generatedId = true, columnName = ID)
    public long id;
    @DatabaseField
    public String userPhone;//用户手机
    @DatabaseField(columnName = NAME)
    public String username;//用户id
    @DatabaseField()
    public String usersex;//用户性别
    @DatabaseField()
    public String userbirthsay;//用户性生日
    @DatabaseField
    public String userhight;//用户身高
    @DatabaseField(columnName = NAME)
    public String userweight;//用户体重
    @DatabaseField()
    public String bloodsugar;//血糖
    @DatabaseField()
    public String bloodpressure;//血压
}
