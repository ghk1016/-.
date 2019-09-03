package com.bawei.guohaokun.presenter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.guohaokun.R;
import com.bawei.guohaokun.model.ModelBean;
import com.bawei.guohaokun.utils.ImgUtil;

import java.util.List;

public class PresenterAdapter extends BaseAdapter {
    private List<ModelBean.DataBean> list;
    private Context context;

    public PresenterAdapter(List<ModelBean.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view==null){
            view = View.inflate(context, R.layout.list_layout,null);
            holder = new ViewHolder();
            holder.goods_name = view.findViewById(R.id.goods_name);
            holder.goods_thumb = view.findViewById(R.id.goods_thumb);
            holder.currency_price = view.findViewById(R.id.currency_price);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }
        holder.goods_name.setText(list.get(i).getGoods_name());
        holder.currency_price.setText(list.get(i).getCurrency_price());
        new ImgUtil(list.get(i).getGoods_thumb(),holder.goods_thumb).execute();
        return view;
    }
    class ViewHolder{
        private TextView currency_price;
        private TextView goods_name;
        private ImageView goods_thumb;
    }
}
