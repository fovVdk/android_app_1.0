package com.gizwits.opensource.appkit.DeviceModule;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.content.FileProvider;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TaiheDeviceLab {
    private Context mContext;
    private SQLiteDatabase mDatabase;
    private static TaiheDeviceLab sTaiheDeviceLab;
    private List<TaiheDevice> mTaiheDevices;
    public static TaiheDeviceLab get(Context context){
        if (sTaiheDeviceLab == null)
        {
            sTaiheDeviceLab = new TaiheDeviceLab(context);

        }
        return sTaiheDeviceLab;
    }

    private TaiheDeviceLab(Context context){

//        mContext = context.getApplicationContext();
//        mDatabase = new DeviceTaiheBaseHelper(mContext).getWritableDatabase();
        mTaiheDevices = new ArrayList<>();
        for (int i=0;i<100;i++)
        {
            TaiheDevice taiheDevice = new TaiheDevice();
            taiheDevice.setDeviceName("DC-400-"+i);
            taiheDevice.setMbattery("电量:1000");
            taiheDevice.setDevicegroup("中油龙慧");
            mTaiheDevices.add(taiheDevice);
        }

    }

    public List<TaiheDevice> getTaiheDevices() {
        return mTaiheDevices;
    }

    public TaiheDevice getTaiheDevice(UUID id){
        for (TaiheDevice taiheDevice : mTaiheDevices){
            if(taiheDevice.getId().equals(id)){
                return taiheDevice;
            }
        }
        return null;
    }


    public File getPhotoFile(Context ncontext,TaiheDevice taiheDevice,String photoname){
        File filesDir = ncontext.getFilesDir();
        return new File(filesDir,taiheDevice.getPhotoFilename(photoname));
    }
//    private static ContentValues getContentValues(TaiheDevice taiheDevice){
//        ContentValues values = new ContentValues();
//        values.put(DeviceTaiheDbSchema.DeviceTable.Cols.UUID,taiheDevice.getId().toString());
//        values.put(DeviceTaiheDbSchema.DeviceTable.Cols.D_DATE,taiheDevice.getId().toString());
//        values.put(DeviceTaiheDbSchema.DeviceTable.Cols.TYPE,taiheDevice.getId().toString());
//        values.put(DeviceTaiheDbSchema.DeviceTable.Cols.NAME,taiheDevice.getId().toString());
//        values.put(DeviceTaiheDbSchema.DeviceTable.Cols.BATTERY,taiheDevice.getId().toString());
//        values.put(DeviceTaiheDbSchema.DeviceTable.Cols.SAFEGATWAY,taiheDevice.getId().toString());
//        values.put(DeviceTaiheDbSchema.DeviceTable.Cols.DGPLISTs,taiheDevice.getId().toString());
//        values.put(DeviceTaiheDbSchema.DeviceTable.Cols.CUSTOMER,taiheDevice.getId().toString());
//        values.put(DeviceTaiheDbSchema.DeviceTable.Cols.USENAME,taiheDevice.getId().toString());
//        values.put(DeviceTaiheDbSchema.DeviceTable.Cols.USEPHONE,taiheDevice.getId().toString());
//
//        return values;
//
//
//    }

}
