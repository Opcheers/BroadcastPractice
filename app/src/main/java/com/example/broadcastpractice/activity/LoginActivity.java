package com.example.broadcastpractice.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initview();
        initEvent();
    }

    private void initEvent() {
        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String account = mAccountEt.getText().toString().trim();
                String password = mPwdEt.getText().toString().trim();
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

    private void initview() {

        mLoginBtn = this.findViewById(R.id.login);
        mAccountEt = this.findViewById(R.id.account);
        mPwdEt = this.findViewById(R.id.password);
    }
}
