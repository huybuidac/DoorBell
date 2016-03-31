package com.doorbell.controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import static android.content.Intent.ACTION_MAIN;

import com.doorbell.model.LoginActivityCallback;
import com.doorbell.view.LoginActivity;

import org.linphone.LinphoneService;

/**
 * Created by huybu on 3/30/2016.
 */
public class LoginController {

    private static final String TAG = LoginController.class.getName();

    private LoginActivityCallback mCallback;
    private ServiceWaitThread mThread;
    private Context mContext;
    private Handler mHandler;

    public LoginController(LoginActivityCallback loginActivityCallback, Context applicationContext) {
        mCallback = loginActivityCallback;
        mContext = applicationContext;
        mHandler = new Handler();
        Log.d(TAG, "LoginController: LinphoneService.isReady()="+LinphoneService.isReady());
        if (LinphoneService.isReady()) {
            Toast.makeText(mContext,"LingphoneService is ready!",Toast.LENGTH_SHORT).show();
        } else {
            // start linphone as background

            mThread = new ServiceWaitThread();
            mThread.start();

        }
    }

    public void Login(String user, String password) {
        if (!TextUtils.isEmpty(user) && !TextUtils.isEmpty(password)) {

        }
    }
    private class ServiceWaitThread extends Thread {
        public void run() {
            while (!LinphoneService.isReady()) {
                try {
                    sleep(100);
                    Log.d(TAG, "run: TAO SLEEP");
                } catch (InterruptedException e) {
                    Log.e(TAG, "run: error!", e);
                }
            }
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(mContext, "Start LinphoneService done",
                            Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

}
