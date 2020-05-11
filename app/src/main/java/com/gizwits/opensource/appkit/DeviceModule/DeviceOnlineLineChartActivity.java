package com.gizwits.opensource.appkit.DeviceModule;


import android.os.Bundle;


import com.gizwits.opensource.appkit.CommonModule.GosBaseActivity;
import com.gizwits.opensource.appkit.R;

public class DeviceOnlineLineChartActivity extends GosBaseActivity {

    private static final String[] HORIZONTAL_AXIS= {"5h", "6h", "7h", "8h",
            "9h", "10h", "11h", "12h", "13h", "14h", "15h", "16h","17h","18h","19h","20h"};

    private static final int[] DATA = {12, 24, 45, 56, 89, 70, 49, 22, 23, 10, 12, 3,24,4,34,23};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_online_line);
        DeviceOnlineLineChartView lineChartView = (DeviceOnlineLineChartView) findViewById(R.id.line_chart_view);
        lineChartView.setHorizontalAxis(HORIZONTAL_AXIS);
        lineChartView.setDataList(DATA, 89);
    }
}