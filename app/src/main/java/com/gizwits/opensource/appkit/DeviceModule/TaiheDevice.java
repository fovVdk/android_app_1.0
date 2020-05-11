package com.gizwits.opensource.appkit.DeviceModule;

import android.content.Context;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class TaiheDevice {

    private Date mDate;
    private String mDevicetype;
    private String mDeviceName;
    private String mbattery;
    private boolean mSafegatway;
    private boolean monlinestate;
    private String mDevicegroup;
    private String mDevicecustomer;
    private String mDeviceUserName;
    private String mDeviceUserPhone;
    private UUID mId;
    private String mPhotoFilename;


    private List<Devicebyte> mDevicebytes;
    private List<DeviceCamera> mDeviceCameras;

    public TaiheDevice() {
        mDate = getDate();
        mDevicetype = getDevicetype();
        mDeviceName = getDeviceName();
        mbattery = getMbattery();
        mSafegatway = isSafegatway();
        monlinestate = isMonlinestate();
        mDevicegroup = getDevicegroup();
        mDevicecustomer = getDevicecustomer();
        mDeviceUserName = getDeviceUserName();
        mDeviceUserPhone = getDeviceUserPhone();
        mDevicebytes = getDevicebytes();
        mDeviceCameras = getDeviceCameras();
        mId = UUID.randomUUID();
        mPhotoFilename = getPhotoFilename(mDeviceName);

    }

    public Date getDate() {
        return mDate;
    }

    public String getDevicetype() {
        return mDevicetype;
    }

    public String getDeviceName() {
        return mDeviceName;
    }

    public String setDeviceName(String name){
        mDeviceName = name;
        return mDeviceName;
    }

    public String setDevicegroup(String group){
        mDevicegroup = group;
        return mDevicegroup;
    }

    public String getMbattery() {
        return mbattery;
    }
    public String setMbattery(String battery){
        mbattery = battery;
        return mbattery;
    }

    public UUID getId()
    {
        return mId;
    }
    public boolean isSafegatway() {
        return mSafegatway;
    }

    public boolean isMonlinestate() {
        return monlinestate;
    }

    public String getDevicegroup() {
        return mDevicegroup;
    }

    public String getDevicecustomer() {
        return mDevicecustomer;
    }

    public String getDeviceUserName() {
        return mDeviceUserName;
    }

    public String getDeviceUserPhone() {
        return mDeviceUserPhone;
    }

    public List<Devicebyte> getDevicebytes() {
        return mDevicebytes;
    }

    public List<DeviceCamera> getDeviceCameras() {
        return mDeviceCameras;
    }

    public String getPhotoFilename(String photoname) {
        return "IMG_" + photoname + ".jpg";
    }
}



