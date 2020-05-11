package com.gizwits.opensource.appkit.DeviceModule;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.gizwits.opensource.appkit.R;
import com.gizwits.opensource.appkit.UserModule.GosUserFastLoginActivity;
import com.gizwits.opensource.appkit.UserModule.GosUserLoginActivity;

import java.io.File;

public class DevicePhotoFragment extends DialogFragment {
    private static final String ARG_PHOTO = "photo";
    public static final String TAG = "DEBUG";
    private TaiheDevice mTaiheDevice;
    private ImageView mImageView;

    public static final String EXTRA_DATA = "com.bignerdranch.android.dialogEditText.data";
    public static DevicePhotoFragment newInstance(String data){
        Bundle args = new Bundle();
        args.putSerializable(ARG_PHOTO,data);

        DevicePhotoFragment fragment = new DevicePhotoFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        mTaiheDevice = new TaiheDevice();
        String data = (String) getArguments().getSerializable(ARG_PHOTO);
        File mDevicephotofile = TaiheDeviceLab.get(getActivity()).getPhotoFile(getActivity(),mTaiheDevice,data);
        Bitmap bitmap = PictureUtils.getScaledBitmap(mDevicephotofile.getPath(),getActivity());
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_device_photo,null);
        mImageView = (ImageView) v.findViewById(R.id.device_photo_view);
        mImageView.setImageBitmap(bitmap);
        return new AlertDialog.Builder(getActivity()).setView(v).setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        }).create();
    }

}
