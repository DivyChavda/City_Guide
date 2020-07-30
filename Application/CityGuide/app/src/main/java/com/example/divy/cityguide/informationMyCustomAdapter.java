package com.example.divy.cityguide;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class informationMyCustomAdapter extends BaseAdapter {

    ArrayList<informationDataModel> listDataModel = new ArrayList<informationDataModel>();
    LayoutInflater minflate;
    Context mContext;

    public informationMyCustomAdapter(ArrayList<informationDataModel> _listDataModel, Context context) {
        this.listDataModel = _listDataModel;
        this.minflate = LayoutInflater.from(context);
        this.mContext = context;
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
        informationDataModel objDataModel = (informationDataModel) getItem(position);
        if (convertView == null) {
            convertView = minflate.inflate(R.layout.rating_review_interface, null);
            vh = new ViewHolder();
            vh.txtLanguageTitle = (TextView) convertView.findViewById(R.id.txtusername);
            vh.txtDesc = (TextView) convertView.findViewById(R.id.txtreview);
            vh.retingbar = (RatingBar) convertView.findViewById(R.id.retingbar);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        vh.txtLanguageTitle.setText(objDataModel.getLanguageTitle().toString());
        vh.txtDesc.setText(objDataModel.getDesc().toString());
        vh.retingbar.setRating(Float.valueOf(objDataModel.getReting().toString()));

        return convertView;
    }

    public class ViewHolder {
        TextView txtLanguageTitle;
        TextView txtDesc;
        RatingBar retingbar;
    }
}
