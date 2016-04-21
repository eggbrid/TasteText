package com.android.taste.sql.database.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by wx on 2015/12/14.
 */
public class User implements Serializable{
    public long id;
    public String userPhone;//用户手机
    public String username;//用户id
 
    public String usersex;//用户性别
 
    public String userbirthsay;//用户性生日
    public String userhight;//用户身高
    public String userweight;//用户体重
 
    public String bloodsugar;//血糖
 
    public String bloodpressure;//血压
}
