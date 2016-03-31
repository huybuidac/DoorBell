package com.doorbell.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.doorbell.R;
import com.doorbell.controller.LoginController;
import com.doorbell.model.LoginActivityCallback;

import org.linphone.LinphoneService;

import static android.content.Intent.ACTION_MAIN;

public class LoginActivity extends AppCompatActivity implements LoginActivityCallback {

    private static final String TAG = LoginActivity.class.getName();
    private LoginController mLoginController;
    private EditText mEdtUserName;
    private EditText mEdtPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEdtUserName = (EditText) findViewById(R.id.edtUserName);
        mEdtPassword = (EditText) findViewById(R.id.edtPassword);

        Log.d(TAG, "LoginController: START service");
        startService(new Intent(this,LinphoneService.class));

        mLoginController = new LoginController(this, getApplicationContext());
    }

    public void btnLogin_click(View v) {
        String userName = mEdtUserName.getText().toString();
        String password = mEdtPassword.getText().toString();
        mLoginController.Login(userName, password);
    }
}
