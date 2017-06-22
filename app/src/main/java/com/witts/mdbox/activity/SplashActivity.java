package com.witts.mdbox.activity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.witts.mdbox.R;
import com.witts.mdbox.common.Constant;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SplashActivity extends BasedActivity {

    @BindView(R.id.tvpwd)
    EditText tvpwd;

    @BindView(R.id.btncancel)
    Button btncancel;

    @BindView(R.id.btnok)
    Button btnok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mWifi = connManager.getActiveNetworkInfo();

        final InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        if (( mWifi==null || !mWifi.isConnected())) {
            // Do whatever
            setContentView(R.layout.activity_splash);
            ButterKnife.bind(this);
            tvpwd.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if(hasFocus) {
                        imm.showSoftInput(tvpwd, InputMethodManager.SHOW_IMPLICIT);
                    }
                    else {
                        imm.hideSoftInputFromWindow(tvpwd.getWindowToken(), 0);
                    }
                }
            });
        }
        else {
            Intent intent = new Intent(this,LanguageActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mWifi = connManager.getActiveNetworkInfo();
        final InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        if (( mWifi==null || !mWifi.isConnected())) {
            // Do whatever
            setContentView(R.layout.activity_splash);
            ButterKnife.bind(this);
            tvpwd.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if(hasFocus) {
                        imm.showSoftInput(tvpwd, InputMethodManager.SHOW_IMPLICIT);
                    }
                    else {
                        imm.hideSoftInputFromWindow(tvpwd.getWindowToken(), 0);
                    }
                }
            });
            btnok.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (hasFocus) {
                        btnok.setBackground(getApplicationContext().getResources().getDrawable(R.drawable.alert_button_background_on));
                    } else{
                        btnok.setBackground(getApplicationContext().getResources().getDrawable(R.drawable.alert_button_background_off));
                    }
                }
            });

            btncancel.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (hasFocus) {
                        btncancel.setBackground(getResources().getDrawable(R.drawable.alert_button_background_on));
                    } else{
                        btncancel.setBackground(getResources().getDrawable(R.drawable.alert_button_background_off));
                    }
                }
            });
        }
        else {
            Intent intent = new Intent(this,LanguageActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @OnClick(R.id.btnok)
    public void clickOkButton()
    {
        String pwd = tvpwd.getText().toString();
        if(pwd.isEmpty()&& pwd.equals("")){
            showAlert("Please Enter Password");
        }
        else if(!pwd.equals(Constant.WIFI_PASSWORD)){
            tvpwd.setText("");
            showAlert("Invalid Password");
        }
        else {
            goToWiFiSetting();
        }
    }

    @OnClick(R.id.btncancel)
    public void clickCancelButton()
    {
        tvpwd.setText("");
    }

    private void goToWiFiSetting() {
        Intent intent = new Intent(Settings.ACTION_SETTINGS);
        startActivity(intent);
    }
}
