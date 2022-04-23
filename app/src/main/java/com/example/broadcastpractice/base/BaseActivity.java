package com.example.broadcastpractice.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.broadcastpractice.activity.LoginActivity;
import com.example.broadcastpractice.utils.ActivityManager;

public class BaseActivity extends AppCompatActivity {

    private static final String TAG = "BaseActivity";
    private ForceOutlineReceiver receiver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: " + getClass().getSimpleName());
        ActivityManager.addActivity(this);
    }


    //保证只有在栈顶的活动才能收到强制下线的广播
    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter("com.example.broadcastpractice.FORCE_OUTLINE");
        receiver = new ForceOutlineReceiver();
        registerReceiver(receiver, intentFilter);
    }

    //活动不在栈顶时候，就会自动取消广播接收器的注册
    @Override
    protected void onPause() {
        super.onPause();
        if (receiver != null) {
            unregisterReceiver(receiver);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: " + getClass().getSimpleName());
        ActivityManager.removeActivity(this);
    }


    private class ForceOutlineReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Warning");
            builder.setMessage("你将被强制下线，请重试！");
            builder.setCancelable(false);//设置对话框不可取消
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    ActivityManager.finishAll();//点击ok，销毁所有活动，回到登陆界面
                    Intent intent = new Intent(context, LoginActivity.class);
                    context.startActivity(intent);
                }
            });
            builder.show();
        }
    }
}
