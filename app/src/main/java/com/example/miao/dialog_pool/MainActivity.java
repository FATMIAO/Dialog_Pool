package com.example.miao.dialog_pool;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends Activity implements View.OnClickListener {
    private Button bt_NomalDialog,bt_MutiDialog,bt_listDialog,bt_singleSelectDialog,bt_MultiSelectDialog,bt_WaitDialog,bt_progressDialog,bt_editDialog,bt_customDialog;
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
        bt_listDialog = (Button)findViewById(R.id.bt_three);
        bt_listDialog.setOnClickListener(this);
        bt_singleSelectDialog = (Button)findViewById(R.id.bt_four);
        bt_singleSelectDialog.setOnClickListener(this);
        bt_MultiSelectDialog = (Button)findViewById(R.id.bt_five);
        bt_MultiSelectDialog.setOnClickListener(this);
        bt_WaitDialog = (Button)findViewById(R.id.bt_six);
        bt_WaitDialog.setOnClickListener(this);
        bt_progressDialog = (Button)findViewById(R.id.bt_seven);
        bt_progressDialog.setOnClickListener(this);
        bt_editDialog = (Button)findViewById(R.id.bt_eight);
        bt_editDialog.setOnClickListener(this);
        bt_customDialog = (Button)findViewById(R.id.bt_nine);
        bt_customDialog.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_one:
                showNomalDialog();
                break;
            case R.id.bt_two:
                showMutiDialog();
                break;
            case R.id.bt_three:
                showListDialog();
                break;
            case R.id.bt_four:
                singleSelectDialog();
                break;
            case R.id.bt_five:
                MultiSelectDialog();
                break;
            case R.id.bt_six:
                WaitDialog();
                break;
            case R.id.bt_eight:
                editDialog();
                break;
            case R.id.bt_seven:
                progressDialog();
                break;
            case R.id.bt_nine:
                customDialog();
                break;
        }

    }

    private void customDialog() {
         /* @setView 装入自定义View ==> R.layout.dialog_customize
     * 由于dialog_customize.xml只放置了一个EditView，因此和图8一样
     * dialog_customize.xml可自定义更复杂的View
     */
        AlertDialog.Builder customizeDialog =
                new AlertDialog.Builder(MainActivity.this);
        final View dialogView = LayoutInflater.from(MainActivity.this)
                .inflate(R.layout.activity_main,null);
        customizeDialog.setTitle("我是一个自定义Dialog");
        customizeDialog.setView(dialogView);
        customizeDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 获取EditView中的输入内容
                        EditText edit_text =
                                (EditText) dialogView.findViewById(R.id.edit_text);
                        Toast.makeText(MainActivity.this,
                                edit_text.getText().toString(),
                                Toast.LENGTH_SHORT).show();
                    }
                });
        customizeDialog.show();
    }

    private void progressDialog() {
        final int MAX_PROGRESS = 100;
        final ProgressDialog progressDialog =
                new ProgressDialog(MainActivity.this);
        progressDialog.setProgress(0);
        progressDialog.setTitle("我是一个进度条Dialog");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setMax(MAX_PROGRESS);
        progressDialog.show();
    /* 模拟进度增加的过程
     * 新开一个线程，每个100ms，进度增加1
     */
        new Thread(new Runnable() {
            @Override
            public void run() {
                int progress= 0;
                while (progress < MAX_PROGRESS){
                    try {
                        Thread.sleep(100);
                        progress++;
                        progressDialog.setProgress(progress);
                    } catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
                // 进度达到最大值后，窗口消失
                progressDialog.cancel();
            }
        }).start();
    }

    private void editDialog() {
        final EditText editText = new EditText(MainActivity.this);
        AlertDialog.Builder inputDialog =
                new AlertDialog.Builder(MainActivity.this);
        inputDialog.setTitle("我是一个输入Dialog").setView(editText);
        inputDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,
                                editText.getText().toString(),
                                Toast.LENGTH_SHORT).show();
                    }
                }).show();
    }

    private void WaitDialog() {
         /* 等待Dialog具有屏蔽其他控件的交互能力
     * @setCancelable 为使屏幕不可点击，设置为不可取消(false)
     * 下载等事件完成后，主动调用函数关闭该Dialog
     */
        ProgressDialog waitingDialog=
                new ProgressDialog(MainActivity.this);
        waitingDialog.setTitle("我是一个等待Dialog");
        waitingDialog.setMessage("等待中...");
        waitingDialog.setIndeterminate(true);
        waitingDialog.setCancelable(false);
        waitingDialog.show();
    }

    //多选Dialog
   ArrayList<Integer> yourChoices = new ArrayList<>();
    private void MultiSelectDialog() {
        final String[] items = { "我是1","我是2","我是3","我是4" };
        // 设置默认选中的选项，全为false默认均未选中
        final boolean initChoiceSets[]={false,false,false,false};
        yourChoices.clear();
        AlertDialog.Builder multiChoiceDialog =
                new AlertDialog.Builder(MainActivity.this);
        multiChoiceDialog.setTitle("我是一个多选Dialog");
        multiChoiceDialog.setMultiChoiceItems(items, initChoiceSets,
                new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which,
                                        boolean isChecked) {
                        if (isChecked) {
                            yourChoices.add(which);
                        } else {
                            yourChoices.remove(which);
                        }
                    }
                });
        multiChoiceDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int size = yourChoices.size();
                        String str = "";
                        for (int i = 0; i < size; i++) {
                            str += items[yourChoices.get(i)] + " ";
                        }
                        Toast.makeText(MainActivity.this,
                                "你选中了" + str,
                                Toast.LENGTH_SHORT).show();
                    }
                });
        multiChoiceDialog.show();
    }

    //单选Dialog
    int position = -1;
    private void singleSelectDialog() {

        final String[] items = {"大刚1","大刚2","大刚3","大刚4"};
        AlertDialog.Builder singleDialog = new AlertDialog.Builder(MainActivity.this);
        singleDialog.setTitle("单选Dialog");
        singleDialog.setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                position = which;
            }
        });
        if(position!=-1){
            Toast.makeText(MainActivity.this,"你点击了"+items[position],Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(MainActivity.this,"请点击",Toast.LENGTH_SHORT).show();
        }
        singleDialog.show();
    }

    //列表Dialog
    private void showListDialog() {
       final String[] items = {"大刚1","大刚2","大刚3","大刚4"};
        AlertDialog.Builder listDialog = new AlertDialog.Builder(MainActivity.this);
        listDialog.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
             Toast.makeText(MainActivity.this,"你点击了"+items[which],Toast.LENGTH_LONG).show();
            }
        });
        listDialog.show();
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
        MutiDialog.show();
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
