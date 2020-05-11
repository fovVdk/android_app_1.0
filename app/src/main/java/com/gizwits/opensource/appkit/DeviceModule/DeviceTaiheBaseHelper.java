package com.gizwits.opensource.appkit.DeviceModule;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.gizwits.opensource.appkit.DeviceModule.DeviceTaiheDbSchema.DeviceTable;

public class DeviceTaiheBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "devicetaiheBase.db";
    public DeviceTaiheBaseHelper(Context context){
        super(context,DATABASE_NAME,null,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("create table " +  DeviceTable.NAME + "(" + " _id integer primary key autoincrement, " + DeviceTable.Cols.UUID + ", " + DeviceTable.Cols.BATTERY + ", " + DeviceTable.Cols.CUSTOMER + ", " + DeviceTable.Cols.D_DATE + ", " + DeviceTable.Cols.DGPLISTs + ", " + DeviceTable.Cols.NAME + ", " + DeviceTable.Cols.ONLINE + ", " + DeviceTable.Cols.SAFEGATWAY + ", " + DeviceTable.Cols.TYPE + ", " + DeviceTable.Cols.USENAME + ", " + DeviceTable.Cols.USEPHONE + ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){}
}
