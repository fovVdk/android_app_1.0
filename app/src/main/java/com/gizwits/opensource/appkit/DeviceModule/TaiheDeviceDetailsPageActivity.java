package com.gizwits.opensource.appkit.DeviceModule;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.gizwits.opensource.appkit.R;

import java.util.List;
import java.util.UUID;

public class TaiheDeviceDetailsPageActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private List<TaiheDevice> mTaiheDevices;
    private static final String EXTRA_TAIHE_ID="com.bignerdranch.android.RelativeLayout.taihedevice_id";

    public static Intent newIntent(Context packageContext, UUID taiheDeviceID){
        Intent intent = new Intent(packageContext,TaiheDeviceDetailsPageActivity.class);
        intent.putExtra(EXTRA_TAIHE_ID,taiheDeviceID);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taihedevicedetails_pager);

        final UUID taiheDeviceID = (UUID) getIntent().getSerializableExtra(EXTRA_TAIHE_ID);
        mViewPager = (ViewPager) findViewById(R.id.activity_pager_view_pager);

        mTaiheDevices = TaiheDeviceLab.get(this).getTaiheDevices();
        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                TaiheDevice taiheDevice = mTaiheDevices.get(position);
                return TaiheDeviceDetailsFragment.newInstance(taiheDevice.getId());
            }

            @Override
            public int getCount() {
                return mTaiheDevices.size();
            }
        });

        for(int i=0; i < mTaiheDevices.size();i++)
        {
            if(mTaiheDevices.get(i).getId().equals(taiheDeviceID)){
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }


}
