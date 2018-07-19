package com.example.shirayama.mymemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;

public class ListAdapter extends ArrayAdapter<MemoBean>{
    private LayoutInflater mInflater;

    public ListAdapter(Context context, int textViewResourceId, List<MemoBean> objects){
        super(context, textViewResourceId, objects);
        mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // 指定行のデータを取得

        MemoBean memo = (MemoBean)getItem(position);

        if(null == convertView){
            convertView = mInflater.inflate(R.layout.row, null);
        }

        // 行のデータを項目へ設定

        TextView title = (TextView)convertView.findViewById(R.id.titleView);
        title.setText(memo.getTitle());

        TextView content = (TextView)convertView.findViewById(R.id.contentView);
        content.setText(memo.getContent());

        return convertView;

    }



}
