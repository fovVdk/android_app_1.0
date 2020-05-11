package com.gizwits.opensource.appkit.DeviceModule;

import android.content.Context;
import android.content.Intent;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.widget.Toast;

import com.gizwits.opensource.appkit.DeviceModule.TaiheDeviceFragment;
import com.gizwits.opensource.appkit.R;

import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

public class TaiheDeviceDetailsActivity extends AppCompatActivity {

    public static final String EXTRA_TAIHE_ID="com.bignerdranch.android.RelativeLayout.taihedevice_id";
    private TaiheDevice mTaiheDevice;
    public static Intent newIntent(Context packageContext, UUID taihedevice_id){
        Intent intent = new Intent(packageContext,TaiheDeviceDetailsActivity.class);
        intent.putExtra(EXTRA_TAIHE_ID,taihedevice_id);
        return intent;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taihedevice_details);
        FragmentManager fm = getFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.taihedevice_details);

        if(fragment == null)
        {
            UUID taiheDeviceID = (UUID) TaiheDeviceDetailsActivity.this.getIntent().getSerializableExtra(TaiheDeviceDetailsActivity.EXTRA_TAIHE_ID);
            //fragment = new TaiheDeviceDetailsFragment().newInstance(taiheDeviceID);

            fm.beginTransaction().add(R.id.taihedevice_details,fragment).commit();
        }





//        initView();
//        initEvent();
    }



}
