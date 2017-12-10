package com.example.ryan.sendmail;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

public class SendMailActivity extends Activity {


    private Button btnSend = null;

    private static final String TAG = "SendMailActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_mail);
        btnSend = (Button) findViewById(R.id.btnSend);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //耗时操作要起线程...有几个新手都是这个问题
                new Thread(new Runnable() {

                    @Override
                    public void run() {
                        try {
                            EmailSender sender = new EmailSender();
                            //设置服务器地址和端口，网上搜的到
                            sender.setProperties("smtp.163.com", "25");
                            //分别设置发件人，邮件标题和文本内容
                            sender.setMessage("15527927670@163.com", "This is title", "This is content！");
                            //设置收件人的邮箱

                            sender.setReceiver(new String[]{"RyanHuang323@163.com"});
                            File file = new File("/sdcard/DCIM/Camera/IMG_20150917_141427.jpg");
                            if(file.exists()) {
                                Log.d(TAG,"file.exists()------>>>>>>");
                                //添加附件
                                //这个附件的路径是我手机里的啊，要发你得换成你手机里正确的路径
                                sender.addAttachment("/sdcard/DCIM/Camera/IMG_20150917_141427.jpg");

                            } else {
                                Log.d(TAG,"file.notexists()------>>>>>>");
                            }

                            //发送邮件,sender.setMessage("你的163邮箱账号", "EmailS//ender", "Java Mail ！");这里面两个邮箱账号要一致
                            sender.sendEmail("smtp.163.com", "15527927670@163.com", "password");

                        } catch (AddressException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        } catch (MessagingException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });
    }
}
