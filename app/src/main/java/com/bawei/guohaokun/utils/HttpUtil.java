package com.bawei.guohaokun.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/*
* 作者：郭昊坤
* 时间：2019年9月3日
* 功能：封装网络   请求判断网络*/
public class HttpUtil {
    private static HttpUtil httpUtil;
    private URL url;
    private HttpURLConnection httpURLConnection;
    private InputStream inputStream;
    private ByteArrayOutputStream byteArrayOutputStream;
    private byte[] bytes;
    private ConnectivityManager connectivityManager;
    private NetworkInfo networkInfo;
    private Bitmap bitmap;

    private HttpUtil(){}
    public static synchronized HttpUtil getInstance(){
        if (httpUtil==null){
            httpUtil = new HttpUtil();
        }
        return httpUtil;
    }
    //网络请求
    public String getData(String path)throws IOException{
        url = new URL(path);
        httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setConnectTimeout(1000);
        httpURLConnection.setReadTimeout(1000);
        if (httpURLConnection.getResponseCode()==200){
            inputStream = httpURLConnection.getInputStream();
            byteArrayOutputStream = new ByteArrayOutputStream();
            bytes = new byte[1024];
            int len;
            while ((len=inputStream.read(bytes))!=-1){
                byteArrayOutputStream.write(bytes,0,len);
            }
            inputStream.close();
        }
        return byteArrayOutputStream.toString();
    }
    //网络图片请求
    public Bitmap getImg(String path)throws IOException{
        url = new URL(path);
        httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setConnectTimeout(1000);
        httpURLConnection.setReadTimeout(1000);
        if (httpURLConnection.getResponseCode()==200){
            inputStream = httpURLConnection.getInputStream();
            bitmap = BitmapFactory.decodeStream(inputStream);
            inputStream.close();
        }
        return bitmap;
    }
    //网络判断请求
    public boolean getWang(Context context){
        connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo!=null){
            return networkInfo.isAvailable();
        }
        return false;
    }
}
