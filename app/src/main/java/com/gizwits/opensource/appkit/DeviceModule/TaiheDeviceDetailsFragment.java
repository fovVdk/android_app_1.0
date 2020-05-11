package com.gizwits.opensource.appkit.DeviceModule;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.gizwits.opensource.appkit.R;
import com.gizwits.opensource.appkit.UserModule.GosForgetPasswordActivity;
import com.gizwits.opensource.appkit.UserModule.GosRegisterUserActivity;
import com.gizwits.opensource.appkit.UserModule.GosUserLoginActivity;

import java.lang.ref.SoftReference;
import java.util.UUID;

import static com.gizwits.opensource.appkit.DeviceModule.DevicedepartmentFragment.TAG;

public class TaiheDeviceDetailsFragment extends Fragment {

    private TaiheDevice mTaiheDevice;
    private TextView myitijiNum;
    private static final String ARG_TAIHEDEVICE_ID = "taihedevice_id";
    private static final String DIALOG_DEPARTMENT = "departmentDate";
    private static final int REQUEST_DATE = 0;
    private Button mDepartmentButton1;
    private Button mDepartmentButton2;
    private Button mDepartmentButton3;
    private Button mDepartmentButton4;
    private ImageButton mbattayView;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mTaiheDevice = new TaiheDevice();
       // UUID taiheDeviceID = (UUID) getActivity().getIntent().getSerializableExtra(TaiheDeviceDetailsActivity.EXTRA_TAIHE_ID);

       UUID taiheDeviceID = (UUID) getArguments().getSerializable(ARG_TAIHEDEVICE_ID);
        mTaiheDevice = TaiheDeviceLab.get(getActivity()).getTaiheDevice(taiheDeviceID);


    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        View view = inflater.inflate(R.layout.activity_device_detail,container,false);
        myitijiNum = (TextView) view.findViewById(R.id.yitiji_number);
        myitijiNum.setText(mTaiheDevice.getDeviceName());


        mbattayView = (ImageButton) view.findViewById(R.id.yitiji_buttay);
        mbattayView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent = new Intent(getActivity(), DevicebattayDataActivity.class);
                startActivity(intent);
            }
        });

        mDepartmentButton1 = (Button) view.findViewById(R.id.button01);
        mDepartmentButton1.setText(mTaiheDevice.getDevicegroup().toString());
        mDepartmentButton1.setTextSize(10);
        mDepartmentButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager = getFragmentManager();
                DevicedepartmentFragment dialog = DevicedepartmentFragment.newInstance(mTaiheDevice.getDevicegroup());
                dialog.setTargetFragment(TaiheDeviceDetailsFragment.this,REQUEST_DATE);
                dialog.show(manager,DIALOG_DEPARTMENT);
            }
        });
        myitijiNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = TaiheDeviceComInfoActivity.newIntent(getActivity(),mTaiheDevice.getDeviceName());
                startActivity(intent);
            }
        });
        return view;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent date){
        if (resultCode != Activity.RESULT_OK)
        {
            return;
        }
        if(requestCode == REQUEST_DATE){
            String data = (String) date.getSerializableExtra(DevicedepartmentFragment.EXTRA_DATA);
            mTaiheDevice.setDevicegroup(data);
            mDepartmentButton1.setText(mTaiheDevice.getDevicegroup().toString());
        }
    }

    public static TaiheDeviceDetailsFragment newInstance(UUID taihedeviceID){
        Bundle args = new Bundle();
        args.putSerializable(ARG_TAIHEDEVICE_ID,taihedeviceID);

        TaiheDeviceDetailsFragment fragment = new TaiheDeviceDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }


}
