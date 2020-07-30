package com.example.divy.cityguide;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyCustomAdapter extends BaseAdapter {
    ArrayList<DataModel> listDataModel = new ArrayList<DataModel>();
    LayoutInflater minflate;

    public MyCustomAdapter(ArrayList<DataModel> _listDataModel, Context context) {
        this.listDataModel = _listDataModel;
        this.minflate = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return listDataModel.size();
    }

    @Override
    public Object getItem(int position) {
        return listDataModel.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder vh;
        DataModel objDataModel = (DataModel) getItem(position);
        if (convertView == null) {
            convertView = minflate.inflate(R.layout.listinterface, null);
            vh = new ViewHolder();
            vh.txtTitle = (TextView) convertView.findViewById(R.id.txtTitle);
            vh.img = (ImageView) convertView.findViewById(R.id.imgplacestgb);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        vh.txtTitle.setText(objDataModel.getLanguageTitle().toString());
        //vh.img.setImageResource(objDataModel.getImgSrc());

        return convertView;
    }

    public class ViewHolder {
        TextView txtTitle;
        ImageView img;
    }

}
