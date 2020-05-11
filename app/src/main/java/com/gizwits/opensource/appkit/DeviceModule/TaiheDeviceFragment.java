package com.gizwits.opensource.appkit.DeviceModule;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.view.View.OnClickListener;
import com.gizwits.opensource.appkit.R;
import com.gizwits.opensource.appkit.SettingsModule.GosAboutActivity;
import com.gizwits.opensource.appkit.UserModule.GosRegisterUserActivity;
import com.gizwits.opensource.appkit.UserModule.GosUserLoginActivity;
import com.gizwits.opensource.appkit.DeviceModule.GosMainActivity;
import java.util.List;
import java.util.UUID;

public class TaiheDeviceFragment extends Fragment{
    private RecyclerView mTaiheDeviceRelative;
    private TaiheDeviceAdapter mAdapter;

    private Intent intent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        View view = inflater.inflate(R.layout.fragment_taihe_device_list,container,false);
        mTaiheDeviceRelative = (RecyclerView) view.findViewById(R.id.taihedevice_list_view);
        mTaiheDeviceRelative.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        return view;
    }


    private class TaiheDeviceHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView mDeviceName;
        private TextView mbattay;
        private TaiheDevice mTaiheDevice;
        public TaiheDeviceHolder(LayoutInflater inflater,ViewGroup parent){
            super(inflater.inflate(R.layout.view_gos_title_listview,parent,false));
            mDeviceName = (TextView) itemView.findViewById(R.id.tvDeviceName);
            mbattay = (TextView) itemView.findViewById(R.id.tvDeviceStatus);
            itemView.setOnClickListener(this);
        }

        public void bind(TaiheDevice taiheDevice){
            mTaiheDevice = taiheDevice;
            mDeviceName.setText(mTaiheDevice.getDeviceName());
            mbattay.setText(mTaiheDevice.getMbattery());
        }

        @Override
        public void onClick(View view) {
  //                  intent = new Intent(getActivity(), TaiheDeviceDetailsActivity.class);
                    Intent intent = TaiheDeviceDetailsPageActivity.newIntent(getActivity(),mTaiheDevice.getId());
                    startActivity(intent);
        }
    }

    private class TaiheDeviceAdapter extends RecyclerView.Adapter<TaiheDeviceHolder>{
        private List<TaiheDevice> mTaiheDevices;

        public TaiheDeviceAdapter(List<TaiheDevice> TaiheDevices){
            mTaiheDevices = TaiheDevices;
        }

        @Override
        public TaiheDeviceHolder onCreateViewHolder(ViewGroup parent,int viewType){
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new TaiheDeviceHolder(layoutInflater,parent);
        }

        @Override
        public void onBindViewHolder(TaiheDeviceHolder holder,int positon){
            TaiheDevice taiheDevice = mTaiheDevices.get(positon);
            holder.bind(taiheDevice);
        }

        @Override
        public int getItemCount(){
            return mTaiheDevices.size();
        }
    }

    private void updateUI(){
        TaiheDeviceLab taiheDeviceLab = TaiheDeviceLab.get(getActivity());
        List<TaiheDevice> taiheDevices = taiheDeviceLab.getTaiheDevices();
        mAdapter = new TaiheDeviceAdapter(taiheDevices);
        mTaiheDeviceRelative.setAdapter(mAdapter);
    }

    @Override
    public void onResume(){
        super.onResume();
        updateUI();
    }
}
