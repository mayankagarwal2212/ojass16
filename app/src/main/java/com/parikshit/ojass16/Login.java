package com.parikshit.ojass16;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.widget.LoginButton;

public class Login extends AppCompatActivity implements FacebookCallback{

    CallbackManager mFacebookCallbackManager;
    LoginButton mfbLoginButton;
    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_login);

        ActionBar actionBar;
        actionBar = getSupportActionBar();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#3F51B5"));
        actionBar.setBackgroundDrawable(colorDrawable);


        mFacebookCallbackManager = CallbackManager.Factory.create();

        mfbLoginButton = (LoginButton) findViewById(R.id.login_button);
        mfbLoginButton.registerCallback(mFacebookCallbackManager, this);

        Button btn_skip=(Button)findViewById(R.id.button_skip);
        btn_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(Login.this,Activity2.class);
                startActivity(in);
                finish();
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mFacebookCallbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onSuccess(Object o) {

        int login_soccess=1;
        MainActivity.val=1;
        SharedPreferences pref =getSharedPreferences("login",0);
        SharedPreferences.Editor e=pref.edit();
        e.putInt("status", login_soccess);
        e.commit();

        Intent i=new Intent(Login.this,Activity2.class);
        startActivity(i);
        finish();
    }


    @Override
    public void onCancel() {
        int login_soccess=0;
        MainActivity.val=0;
        SharedPreferences pref =getSharedPreferences("login",0);
        SharedPreferences.Editor e=pref.edit();
        e.putInt("status",login_soccess);
        e.commit();
    }

    @Override
    public void onError(FacebookException e) {
        Toast.makeText(Login.this,"Error while login....please try again",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Logs 'app deactivate' App Event.
        AppEventsLogger.deactivateApp(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Logs 'install' and 'app activate' App Events.
            AppEventsLogger.activateApp(this);
        super.onResume();
    }

}
