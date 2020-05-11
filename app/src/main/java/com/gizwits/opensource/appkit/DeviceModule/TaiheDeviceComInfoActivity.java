package com.gizwits.opensource.appkit.DeviceModule;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.gizwits.opensource.appkit.R;

import java.io.File;
import java.util.List;

public class TaiheDeviceComInfoActivity extends AppCompatActivity {


    private ImageButton mImageButton;
    private ImageView mImageView;
    private TextView mTextView;
    private File mphotoFile;
    private TaiheDevice mTaiheDevice;
    private String TAG = "stephen";



    private static final int REQUEST_PHOTO = 2;
    private static final String EXTRA_DEVICE_NAME_SHOWN = "com.example.myapplication.MainActivity.device_name_show";
    //private static final String EXTRA_DEVICE_NAME_SHOWN = ""
    private static final String DIALOG_PHOTO = "photobig";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_com_info);


        mTaiheDevice = new TaiheDevice();

        mphotoFile = TaiheDeviceLab.get(this).getPhotoFile(this,mTaiheDevice,getIntent().getStringExtra(EXTRA_DEVICE_NAME_SHOWN));
        Log.d(TAG,mphotoFile.toString());

        final Intent captureImage = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        boolean canTakePhoto = mphotoFile !=null && captureImage.resolveActivity(getPackageManager()) != null;

        mImageButton = (ImageButton) findViewById(R.id.crime_camera);

        mTextView = (TextView) findViewById(R.id.yitiji_name);

        mTextView.setText(getIntent().getStringExtra(EXTRA_DEVICE_NAME_SHOWN));



        mImageButton.setEnabled(canTakePhoto);
        mImageButton.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = FileProvider.getUriForFile(TaiheDeviceComInfoActivity.this,"com.bignerdranch.android.criminalitent.fileprovider",mphotoFile);
                captureImage.putExtra(MediaStore.EXTRA_OUTPUT,uri);


                List<ResolveInfo> cameraActivities = TaiheDeviceComInfoActivity.this.getPackageManager().queryIntentActivities(captureImage, PackageManager.MATCH_DEFAULT_ONLY);

                for(ResolveInfo activity : cameraActivities){
                    TaiheDeviceComInfoActivity.this.grantUriPermission(activity.activityInfo.packageName,uri,Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);
                }

                startActivityForResult(captureImage,REQUEST_PHOTO);
            }

        });

        mImageView = (ImageView) findViewById(R.id.crime_photo);
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager = getSupportFragmentManager();
                DevicePhotoFragment dialog = DevicePhotoFragment.newInstance(getIntent().getStringExtra(EXTRA_DEVICE_NAME_SHOWN));
                dialog.show(manager,DIALOG_PHOTO);
            }
        });
        updatePhotoView();




    }
    public static Intent newIntent(Context packageContext, String name){
        Intent intent = new Intent(packageContext,TaiheDeviceComInfoActivity.class);
        intent.putExtra(EXTRA_DEVICE_NAME_SHOWN,name);
        return intent;
    }

    private void updatePhotoView(){
        if (mphotoFile == null || !mphotoFile.exists()){
            mImageView.setImageDrawable(null);
        }
        else {
            Bitmap bitmap = PictureUtils.getScaledBitmap(mphotoFile.getPath(),this);
            mImageView.setImageBitmap(bitmap);
        }
    }

    @Override
    public void onActivityResult(int requestCode,int resultCode,Intent data){
        if (requestCode == REQUEST_PHOTO)
        {
            Uri uri = FileProvider.getUriForFile(this,"com.bignerdranch.android.criminalitent.fileprovider",mphotoFile);
            this.revokeUriPermission(uri,Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            updatePhotoView();
        }
    }

}
