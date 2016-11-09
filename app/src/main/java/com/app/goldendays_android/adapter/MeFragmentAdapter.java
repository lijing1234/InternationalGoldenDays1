package com.app.goldendays_android.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.app.goldendays_android.R;

import java.util.List;

/**
 * Created by lijing on 2016/11/9.
 */

public class MeFragmentAdapter extends BaseAdapter {
    private List<String> list;
    private Context ctx;

    public MeFragmentAdapter(List<String> list, Context ctx) {
        this.list = list;
        this.ctx = ctx;
    }

    @Override
    public int getCount() {
        return 20;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = View.inflate(ctx, R.layout.item_me_fragment_gv, null);
        ImageView img = (ImageView) view.findViewById(R.id.GoodsValueItem_LvImg);
        return view;
    }
}
