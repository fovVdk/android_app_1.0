package com.gizwits.opensource.appkit.DeviceModule;

import java.util.Date;

public class DeviceCamera {
    private boolean mdevice1;
    private boolean mdevice2;
    private boolean mdevice3;
    private boolean mdevice4;
    private boolean mdevice5;
    private boolean mdevice6;
    private boolean mdevice7;
    private boolean mdevice8;
    private Date mDate;
    public boolean isMdevice1() {
        return mdevice1;
    }

    public boolean isMdevice2() {
        return mdevice2;
    }

    public boolean isMdevice3() {
        return mdevice3;
    }

    public boolean isMdevice4() {
        return mdevice4;
    }

    public boolean isMdevice5() {
        return mdevice5;
    }

    public boolean isMdevice6() {
        return mdevice6;
    }

    public boolean isMdevice7() {
        return mdevice7;
    }

    public boolean isMdevice8() {
        return mdevice8;
    }

    public Date getDate() {
        return mDate;
    }

    public DeviceCamera() {
        mDate = getDate();
        mdevice1 = isMdevice1();
        mdevice2 = isMdevice2();
        mdevice3 = isMdevice3();
        mdevice4 = isMdevice4();
        mdevice5 = isMdevice5();
        mdevice6 = isMdevice6();
        mdevice7 = isMdevice7();
        mdevice8 = isMdevice8();
    }

}
