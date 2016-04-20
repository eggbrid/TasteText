package com.android.taste.sql.database.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by wx on 2016/1/18.
 */
@DatabaseTable
public class DataBaseSeting {
    public static final String LAST_UPDATE = "last_update", URL = "url",ID = "id";
    @DatabaseField(generatedId = true, columnName = ID)
    public long id;
    @DatabaseField( columnName = LAST_UPDATE)
    public String last_update;
    @DatabaseField( columnName = URL)
    public String url;

}
