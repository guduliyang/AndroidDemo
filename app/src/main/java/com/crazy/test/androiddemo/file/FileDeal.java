package com.crazy.test.androiddemo.file;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by ADMIN on 2017/6/13.
 */

public class FileDeal {
    private static final String TAG=FileDeal.class.getSimpleName();

    /**
     * 【读取assets文件夹下Properties文件】
     * @param context
     * @return
     */
    public static Properties readFromAssets(Context context){
        Properties properties = new Properties();
        String fileName="Config";
        try{
            //获取assets文件夹内的Properties文件
            InputStream inputStream = context.getAssets().open(fileName);
            properties.load(inputStream);
            inputStream.close();
        } catch (IOException e) {
            Log.e(TAG, "readFromAssets: 文件"+fileName+"不存在" );
            return null;
        }
        return properties;
    }

    /**
     * 【读取 /data/data/<package name>/files/下文件】
     * @param context
     * @param fileName
     * @return
     */
    public static String readFromData(Context context,String fileName){
        String content="";
        try {
            FileInputStream inputStream = context.openFileInput(fileName);//只传入文件名（带后缀）
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();//输出到内存
            int len=0;
            byte[] buffer = new byte[1024];
            while((len=inputStream.read(buffer))!=-1){
                outputStream.write(buffer,0,len);
            }
            byte[] content_byte = outputStream.toByteArray();
            content = new String(content_byte);
            inputStream.close();
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    /**
     * 【写数据到 /data/data/<package name>/files/下文件中】
     * @param context
     * @param fileName
     * @param content
     * @return
     */
    public static boolean writeToData(Context context,String fileName,String content){
        try {
            FileOutputStream outputStream = context.openFileOutput(fileName,Context.MODE_PRIVATE);
            outputStream.write(content.getBytes());
            outputStream.close();
        } catch (FileNotFoundException e) {
            Log.e(TAG, "writeToData: "+e.getMessage() );
            return false;
        } catch (IOException e) {
            Log.e(TAG, "writeToData: "+e.getMessage() );
            return false;
        }
        Log.i(TAG, "writeToData: 成功写入数据到文件："+fileName);
        return true;
    }

    /**
     * 【读取SD卡根目录下文件】
     * @param fileName
     * @return
     */
    public static String readFromSD(String fileName){
        String content =null;
        try {
            File file = Environment.getExternalStorageDirectory();//获取SD卡目录
            File fileDir = new File(file,fileName);
            FileInputStream inputStream = new FileInputStream(fileDir);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] array = new byte[1024];
            int len =-1;
            while((len=inputStream.read(array))!=-1){
                outputStream.write(array,0,len);
            }
            byte[] content_byte = outputStream.toByteArray();
            content = new String(content_byte);
            outputStream.close();
            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    /**
     * 【向SD卡根目录中写入文件】
     * @param fileName
     * @param content
     * @return
     */
    public static boolean writeToSD(String fileName,String content){
        File file = Environment.getExternalStorageDirectory();//获取SD卡目录
        File fileDir = new File(file,fileName);
        try {
            FileOutputStream outputStream = new FileOutputStream(fileDir);
            outputStream.write(content.getBytes());
            outputStream.close();
        } catch (FileNotFoundException e) {
            Log.e(TAG, "writeToSD: 写入失败："+e.getMessage() );
            return false;
        } catch (IOException e) {
            Log.e(TAG, "writeToSD: 写入失败："+e.getMessage() );
            return false;
        }
        return true;
    }
}
