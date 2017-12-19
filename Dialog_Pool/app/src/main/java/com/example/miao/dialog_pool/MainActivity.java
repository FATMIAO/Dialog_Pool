package com.example.miao.dialog_pool;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener {
    private Button bt_NomalDialog,bt_MutiDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        bt_NomalDialog = (Button)findViewById(R.id.bt_one);
        bt_NomalDialog.setOnClickListener(this);
        bt_MutiDialog = (Button)findViewById(R.id.bt_two);
        bt_MutiDialog.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_one:
                showNomalDialog();
                break;
            case R.id.bt_two:
                showMutiDialog();

        }

    }
    //三个按钮的Dialog
    private void showMutiDialog() {
        AlertDialog.Builder MutiDialog= new AlertDialog.Builder(MainActivity.this);
        MutiDialog.setTitle("三个按钮的对话框");
        MutiDialog.setMessage("你好啊大刚");
        MutiDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this,"你好大刚",Toast.LENGTH_SHORT).show();
            }
        });
        MutiDialog.setNeutralButton("提问", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this,"你是谁",Toast.LENGTH_SHORT).show();
            }
        });
        MutiDialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this,"再见大刚",Toast.LENGTH_SHORT).show();
            }
        });
    }

    //普通的Dialog
    private void showNomalDialog(){
         /* @setIcon 设置对话框图标
         * @setTitle 设置对话框标题
         * @setMessage 设置对话框消息提示
         * setXXX方法返回Dialog对象，因此可以链式设置属性
         */
        AlertDialog.Builder NomalDialog = new AlertDialog.Builder(MainActivity.this);
        NomalDialog.setTitle("普通的对话框");
        NomalDialog.setMessage("你好大刚");
        NomalDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this,"你好大刚",Toast.LENGTH_SHORT).show();
            }
        });
        NomalDialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this,"再见大刚",Toast.LENGTH_SHORT).show();
            }
        });
        NomalDialog.show();
    }
}
