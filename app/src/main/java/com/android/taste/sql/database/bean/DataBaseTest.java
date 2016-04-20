package com.android.taste.sql.database.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by wx on 2015/12/14.
 */
@DatabaseTable
public class DataBaseTest {
    public static final String ID = "id", TID = "testid", SEX = "sex", NAME = "name";
    @DatabaseField(generatedId = true, columnName = ID)
    public long id;//测试id
    @DatabaseField(columnName = TID)
    public int test_id;//测试序号
    @DatabaseField(columnName = SEX)
    public String sex;//测试者性别
    @DatabaseField(columnName = NAME)
    public String name;//c测试题目
    @DatabaseField()
    public int ph_no=0;//平和-没有
    @DatabaseField()
    public int ph_has=0;//平和-经常
    @DatabaseField()
    public int qx_no=0;//气虚-没有
    @DatabaseField()
    public int qx_has=0;//气虚-经常
    @DatabaseField()
    public int xx_no=0;//血虚-没有
    @DatabaseField()
    public int xx_has=0;//血虚-经常
    @DatabaseField()
    public int yinx_no=0;//阴虚-没有
    @DatabaseField()
    public int yinx_has=0;//阴虚-经常
    @DatabaseField()
    public int yangx_no=0;//阳虚-没有
    @DatabaseField()
    public int yang_has=0;//阳虚-经常
    @DatabaseField()
    public int qy_no=0;//气郁-没有
    @DatabaseField()
    public int qy_has=0;//气郁-经常
    @DatabaseField()
    public int xy_no=0;//血淤-没有
    @DatabaseField()
    public int xy_has=0;//血淤-经常
    @DatabaseField()
    public int ts_no=0;//痰湿-没有
    @DatabaseField()
    public int ts_has=0;//痰湿-经常
    @DatabaseField()
    public int sr_no=0;//湿热-没有
    @DatabaseField()
    public int sr_has=0;//湿热-经常
    @DatabaseField()
    public int hr_no=0;//火热-没有
    @DatabaseField()
    public int hr_has=0;//火热-经常
}
