package com.gizwits.opensource.appkit.DeviceModule;

import java.util.Date;
import java.util.UUID;

public class Devicebyte {
    private UUID mICCID;
    private int mSumbyte;
    private int mLastbyte;
    private int mbyte;
    private Date mDate;

    public Devicebyte() {
        mICCID = getID();
        mSumbyte = getSumbyte();
        mLastbyte = getLastbyte();
        mbyte = getbyte();
        mDate = getDate();

    }

    public UUID getID() {
        return mICCID;
    }

    public int getSumbyte() {
        return mSumbyte;
    }

    public int getLastbyte() {
        return mLastbyte;
    }

    public int getbyte() {
        return mbyte;
    }

    public Date getDate() {
        return mDate;
    }
}
