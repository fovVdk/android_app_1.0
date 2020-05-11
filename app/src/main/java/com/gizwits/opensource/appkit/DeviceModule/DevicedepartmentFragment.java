package com.gizwits.opensource.appkit.DeviceModule;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.gizwits.opensource.appkit.R;

import org.w3c.dom.Text;

import java.util.Calendar;

public class DevicedepartmentFragment extends DialogFragment {
    private static final String ARG_DATA = "data";
    private EditText mEditText;
    public static final String TAG = "DEBUG";

    public static final String EXTRA_DATA = "com.bignerdranch.android.dialogEditText.data";
    public static DevicedepartmentFragment newInstance(String data){
        Bundle args = new Bundle();
        args.putSerializable(ARG_DATA,data);

        DevicedepartmentFragment fragment = new DevicedepartmentFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        String data = (String) getArguments().getSerializable(ARG_DATA);
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_department_data,null);
        mEditText = (EditText) v.findViewById(R.id.dialog_department_data);
        mEditText.setText(data);
        return new AlertDialog.Builder(getActivity()).setView(v).setTitle(R.string.device_department_information).setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String data = mEditText.getText().toString();
                sendResult(Activity.RESULT_OK,data);

            }
        }).create();
    }

    private void sendResult(int resultCode,String data){
        if (getTargetFragment() == null)
        {
            return;
        }
        Intent intent = new Intent();
        intent.putExtra(EXTRA_DATA,data);

        getTargetFragment().onActivityResult(getTargetRequestCode(),resultCode,intent);
    }
}
