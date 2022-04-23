package com.example.broadcastpractice.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.broadcastpractice.MainActivity;
import com.example.broadcastpractice.base.BaseActivity;
import com.example.broadcastpractice.R;

public class LoginActivity extends BaseActivity {

    private EditText mPwdEt;
    private EditText mAccountEt;
    private Button mLoginBtn;
    private CheckBox mCheckBox;
    private SharedPreferences pref;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
        initEvent();
    }

    private void initEvent() {
        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String account = mAccountEt.getText().toString().trim();
                String password = mPwdEt.getText().toString().trim();

                //数据存储
                SharedPreferences.Editor editor = pref.edit();
                if (mCheckBox.isChecked()) {
                    editor.putString("account", account);
                    editor.putString("password", password);
                    editor.putBoolean("remember", true);
                } else {
                    editor.clear();
                }
                editor.apply();

                //登录账号密码校验
                if (account.equals("123456") && password.equals("123456")){
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "账号或密码错误！", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initView() {

        pref = PreferenceManager.getDefaultSharedPreferences(this);

        mLoginBtn = this.findViewById(R.id.login);
        mAccountEt = this.findViewById(R.id.account);
        mPwdEt = this.findViewById(R.id.password);
        mCheckBox = this.findViewById(R.id.remember_pwd);

        //第一次使用时肯定没有被选中，所以默认值为fasle
        boolean isRemember = pref.getBoolean("remember", false);

        //如果被选中，则读取数据显示到页面上
        if (isRemember) {
            mAccountEt.setText(pref.getString("account", ""));
            mPwdEt.setText(pref.getString("password", ""));
            mCheckBox.setChecked(true);
        }
    }
}
