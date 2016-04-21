package com.android.taste.personal.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.android.taste.R;
import com.android.taste.sql.database.bean.DataBaseTest;

import java.util.List;

/**
 * Created by xus on 2016/4/21.
 */
public class TestAdapter extends BaseAdapter  {
    public List<DataBaseTest> list;
    public Context context;

    public List<DataBaseTest> getList() {
        return list;
    }

    public void setList(List<DataBaseTest> list) {
        this.list = list;
    }

    public TestAdapter(Context context, List<DataBaseTest> list) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public DataBaseTest getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_test_item, parent, false);
            holder = new ViewHolder();
            holder.content = (TextView) convertView.findViewById(R.id.content);
            holder.id = (TextView) convertView.findViewById(R.id.id);
            holder.is = (CheckBox) convertView.findViewById(R.id.is);
            holder.no = (CheckBox) convertView.findViewById(R.id.no);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        DataBaseTest thisTest = list.get(position);
        DataBaseTest lastTest = null;
        if (position != 0) {
            lastTest = list.get(position - 1);
        }
        boolean isChangeIdColor;
        if (lastTest != null && !TextUtils.isEmpty(lastTest.isno) && TextUtils.isEmpty(thisTest.isno)) {
            isChangeIdColor = true;
        } else if (lastTest == null && TextUtils.isEmpty(thisTest.isno)) {
            isChangeIdColor = true;
        } else {
            isChangeIdColor = false;
        }
        if (isChangeIdColor) {
            holder.id.setTextColor(context.getResources().getColor(R.color.ttblue));
        } else {
            holder.id.setTextColor(context.getResources().getColor(R.color.bg_color));
        }
        holder.id.setText(thisTest.test_id + "");
        holder.content.setText(thisTest.name);
        holder.is.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.get(position).isno = "is";
                notifyDataSetChanged();
            }
        });
        holder.no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.get(position).isno = "no";
                notifyDataSetChanged();
            }
        });
        if(TextUtils.isEmpty(list.get(position).isno)){
            holder.is.setChecked(false);
            holder.no.setChecked(false);
        }else{
            if (list.get(position).isno.equals("is")){
                holder.is.setChecked(true);
                holder.no.setChecked(false);

            }else{
                holder.is.setChecked(false);
                holder.no.setChecked(true);
            }
        }

        return convertView;
    }



    public class ViewHolder {
        TextView id;
        TextView content;
        CheckBox is, no;
    }
}
