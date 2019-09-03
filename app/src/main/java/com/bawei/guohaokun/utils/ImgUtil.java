package com.bawei.guohaokun.utils;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;

public class ImgUtil extends AsyncTask<Integer,Integer, Bitmap> {
    String path;
    private ImageView imageView;
    private Bitmap img;

    public ImgUtil(String path, ImageView imageView) {
        this.path = path;
        this.imageView = imageView;
    }

    @Override
    protected Bitmap doInBackground(Integer... integers) {
        try {
            img = HttpUtil.getInstance().getImg(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        imageView.setImageBitmap(bitmap);
    }
}
