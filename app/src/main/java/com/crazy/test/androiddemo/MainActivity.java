package com.crazy.test.androiddemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.crazy.test.androiddemo.file.FileDeal;

import java.util.Properties;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        test();
    }

    public void test(){
        System.out.println("--------------写数据到 /data/data/<package name>/files/下文件中----------------");
        FileDeal.writeToData(this,"setting.txt","hello world");
        System.out.println("-------------------------向SD卡根目录中写入文件--------------------------------");
        FileDeal.writeToSD("test.txt","羞答答的玫瑰");
        System.out.println("---------------------读取assets文件夹下Properties文件--------------------------");
        Properties properties = FileDeal.readFromAssets(this);
        System.out.println(properties.get("url"));
        System.out.println("----------------读取 /data/data/<package name>/files/下文件--------------------");
        System.out.println(FileDeal.readFromData(this,"setting.txt"));
        System.out.println("---------------------------读取SD卡根目录下文件--------------------------------");
        System.out.println(FileDeal.readFromSD("test.txt"));

    }
}
