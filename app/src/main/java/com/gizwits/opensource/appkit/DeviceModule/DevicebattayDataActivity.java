package com.gizwits.opensource.appkit.DeviceModule;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.gizwits.opensource.appkit.CommonModule.GosBaseActivity;
import com.gizwits.opensource.appkit.DeviceModule.DevicebattayDataView;
import com.gizwits.opensource.appkit.R;

public class DevicebattayDataActivity extends GosBaseActivity {

    private static final String[] HORIZONTAL_AXIS= {"1", "2", "3", "4",
            "5", "6", "7", "8", "9", "10", "11", "12"};

    private static final float[] DATA = {12, 24, 45, 56, 89, 70, 49, 22, 23, 10, 12, 3};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battay_data);
        DevicebattayDataView lineChart = (DevicebattayDataView) findViewById(R.id.bar_chart);
        lineChart.setHorizontalAxis(HORIZONTAL_AXIS);
        lineChart.setDataList(DATA, 89);
    }
}
