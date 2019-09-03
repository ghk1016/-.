package com.bawei.guohaokun;

import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.bawei.guohaokun.base.BaseActivity;
import com.bawei.guohaokun.model.ModelBean;
import com.bawei.guohaokun.presenter.PresenterAdapter;
import com.bawei.guohaokun.utils.HttpUtil;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.io.IOException;
import java.util.List;

/*
* 作者：郭昊坤
* 时间：2019年9月3日
* 功能：回调 gson解析*/
public class MainActivity extends BaseActivity {

    private int page=1;
    String path = "http://blog.zhaoliang5156.cn/api/shop/shop1.json";
    private PullToRefreshListView list_view;


    @Override
    public int getlayoutid() {
        return R.layout.activity_main;
    }

    public void initView() {
        list_view = findViewById(R.id.list_view);
        list_view.setMode(PullToRefreshBase.Mode.BOTH);
        list_view.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                page=1;
                new MyAsync().execute();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                page++;
                new MyAsync().execute();
            }
        });
        if (HttpUtil.getInstance().getWang(MainActivity.this)){
            Toast.makeText(this, "WIFI", Toast.LENGTH_SHORT).show();
            new MyAsync().execute();

        }else {
            Toast.makeText(this, "无网", Toast.LENGTH_SHORT).show();

        }
    }

    public class MyAsync extends AsyncTask<Integer,Integer,String>{

        private String data;

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Gson gson = new Gson();
            ModelBean modelBean = gson.fromJson(s, ModelBean.class);
            List<ModelBean.DataBean> data = modelBean.getData();
            PresenterAdapter presenterAdapter = new PresenterAdapter(data, MainActivity.this);
            list_view.setAdapter(presenterAdapter);
            list_view.onRefreshComplete();
        }

        @Override
        protected String doInBackground(Integer... integers) {
            try {
                data = HttpUtil.getInstance().getData(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return data;
        }
    }
    @Override
    public void initData() {

    }
}
