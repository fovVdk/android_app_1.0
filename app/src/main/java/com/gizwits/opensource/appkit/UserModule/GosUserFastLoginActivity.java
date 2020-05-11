package com.gizwits.opensource.appkit.UserModule;

import java.util.Timer;
import java.util.TimerTask;

import com.gizwits.gizwifisdk.api.GizWifiSDK;
import com.gizwits.gizwifisdk.enumration.GizUserAccountType;
import com.gizwits.gizwifisdk.enumration.GizWifiErrorCode;
import com.gizwits.opensource.appkit.R;
import com.gizwits.opensource.appkit.CommonModule.GosDeploy;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("HandlerLeak")
public class GosUserFastLoginActivity extends GosUserModuleBaseActivity
        implements OnClickListener {


    /** The et Name */
    private EditText etName;

    /** The btn GetCode */
    private Button btnGetCode;

    /** The et Code */
    private EditText etCode;



    /** The btn Rrgister */
    private TextView tvRegister;

    Intent intent;
    private Button btnLogin;

    GradientDrawable drawable;

    /**
     * 验证码重发倒计时
     */
    int secondleft = 60;

    /**
     * The timer.
     */
    Timer timer;

    /** 数据变量 */
    String name, code, psw;

    private enum handler_key {

        /** 获取验证码. */
        GETCODE,

        /** 提示信息 */
        TOAST,

        /** 手机验证码发送成功. */
        SENDSUCCESSFUL,

        /**
         * 倒计时通知
         */
        TICK_TIME,

        /** 注册 */
        LOGIN,
    }

    Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            super.handleMessage(msg);
            GosUserFastLoginActivity.handler_key key = GosUserFastLoginActivity.handler_key.values()[msg.what];
            switch (key) {
                case GETCODE:
                    progressDialog.show();
                    String AppSecret = GosDeploy.setAppSecret();
                    GizWifiSDK.sharedInstance().requestSendPhoneSMSCode(AppSecret,
                            msg.obj.toString());

                    break;
                case TOAST:
                    Toast.makeText(GosUserFastLoginActivity.this,
                            msg.obj.toString(), toastTime).show();
                    String successfulText = (String) getText(R.string.register_successful);

                    if (msg.obj.toString().equals(successfulText)) {
                        // spf.edit().putString("UserName", name).commit();
                        // spf.edit().putString("PassWord", psw).commit();
                        isclean = true;
                        finish();
                    }
                    break;
                case SENDSUCCESSFUL:
                    etName.setEnabled(false);
                    etName.setTextColor(getResources().getColor(
                            R.color.text_gray_light));
                    isStartTimer();

                    break;

                case TICK_TIME:
                    String getCodeAgain = getString(R.string.getcode_again);
                    String timerMessage = getString(R.string.timer_message);
                    secondleft--;
                    if (secondleft <= 0) {
                        timer.cancel();
                        btnGetCode.setBackgroundDrawable(drawable);
                        btnGetCode.setTextColor(GosDeploy.setButtonTextColor());
                        btnGetCode.setEnabled(true);
                        btnGetCode.setText(getCodeAgain);
                    } else {
                        btnGetCode.setText(secondleft + timerMessage);
                    }
                    break;
                case LOGIN:
                    progressDialog.show();
//                    GizWifiSDK.sharedInstance().registerUser(name, psw, code,
//                            GizUserAccountType.GizUserPhone);
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gos_user_fast_login);
        // 设置ActionBar

        initView();
        initEvent();
    }


    private void initView() {
        etName = (EditText) findViewById(R.id.etName);//用户名
        btnGetCode = (Button) findViewById(R.id.btnGetCode);
        etCode = (EditText) findViewById(R.id.etCode);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        tvRegister = (TextView) findViewById(R.id.tvRegister);

        // 配置文件部署
        drawable = (GradientDrawable) GosDeploy.setButtonBackgroundColor();
        drawable.setCornerRadius(30);
        //btnGetCode.setBackgroundDrawable(drawable);
        //btnRrgister.setBackgroundDrawable(GosDeploy.setButtonBackgroundColor());
        //btnRrgister.setTextColor(GosDeploy.setButtonTextColor());

    }

    private void initEvent() {
        final Timer etTimer = new Timer();
        etTimer.schedule(new TimerTask() {

            @Override
            public void run() {
                etName.requestFocus();
                InputMethodManager imm = (InputMethodManager) etName
                        .getContext().getSystemService(
                                Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(0, InputMethodManager.SHOW_FORCED);
                etTimer.cancel();

            }
        }, 500);

        btnGetCode.setOnClickListener(this);
        tvRegister.setOnClickListener(this);
        btnLogin.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnGetCode:
                name = etName.getText().toString();
                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(GosUserFastLoginActivity.this,
                            R.string.name_hint, toastTime).show();
                    return;
                }
                Message msg = new Message();
                msg.obj = name;
                msg.what = GosUserFastLoginActivity.handler_key.GETCODE.ordinal();
                handler.sendMessage(msg);
                break;

            case R.id.btnLogin:
                name = etName.getText().toString();
                code = etCode.getText().toString();
                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(GosUserFastLoginActivity.this,
                            R.string.toast_name_wrong, toastTime).show();
                    return;
                }
                if (code.length() != 6) {
                    Toast.makeText(GosUserFastLoginActivity.this,
                            R.string.no_getcode, toastTime).show();
                    return;
                }
                /*
                 * if (psw.length() < 6) {
                 * Toast.makeText(GosRegisterUserActivity.this,
                 * R.string.toast_psw_short, toastTime).show(); return; }
                 */
                handler.sendEmptyMessage(handler_key.LOGIN.ordinal());
                break;

            case R.id.tvRegister:
                intent = new Intent(GosUserFastLoginActivity.this, GosRegisterUserActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void didRequestSendPhoneSMSCode(GizWifiErrorCode result,
                                              String token) {
        progressDialog.cancel();
        Message msg = new Message();
        if (GizWifiErrorCode.GIZ_SDK_SUCCESS != result) {
            msg.what = GosUserFastLoginActivity.handler_key.TOAST.ordinal();
            msg.obj = toastError(result);
            handler.sendMessage(msg);
        } else {
            handler.sendEmptyMessage(GosUserFastLoginActivity.handler_key.SENDSUCCESSFUL.ordinal());
            msg.what = GosUserFastLoginActivity.handler_key.TOAST.ordinal();
            String sendSuccessful = (String) getText(R.string.send_successful);
            msg.obj = sendSuccessful;
            handler.sendMessage(msg);
        }
    }

    /**
     * 倒计时
     */
    public void isStartTimer() {
        btnGetCode.setEnabled(false);
        btnGetCode.setBackgroundResource(R.drawable.btn_getcode_shape_gray);
        secondleft = 60;
        timer = new Timer();
        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                handler.sendEmptyMessage(GosUserFastLoginActivity.handler_key.TICK_TIME.ordinal());
            }
        }, 1000, 1000);
    }
}
