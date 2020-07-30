package com.example.divy.cityguide;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

public class homeMyCustomAdapter extends BaseAdapter {

    ArrayList<homeDataMode> listDataModel = new ArrayList<homeDataMode>();
    LayoutInflater minflate;
    Context mContext;

    public homeMyCustomAdapter(ArrayList<homeDataMode> _listDataModel, Context context) {
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
        final homeMyCustomAdapter.ViewHolder vh;
        final homeDataMode objDataModel = (homeDataMode) getItem(position);
        if (convertView == null) {
            convertView = minflate.inflate(R.layout.homeinterface, null);
            vh = new homeMyCustomAdapter.ViewHolder();
            vh.txtHomeTitle = (TextView) convertView.findViewById(R.id.txtHomeTitle);
            vh.imghome = (ImageView) convertView.findViewById(R.id.imghome);
            vh.txtid = (TextView) convertView.findViewById(R.id.txtid);
            convertView.setTag(vh);
        } else {
            vh = (homeMyCustomAdapter.ViewHolder) convertView.getTag();
        }
        vh.txtHomeTitle.setText(objDataModel.getLanguageTitle().toString());
        vh.txtid.setText(objDataModel.getID().toString());

        final int radius = 10;
        final int margin = 0;
        final Transformation transformation = new RoundedCornersTransformation(radius, margin);

        Picasso.with(mContext)
                .load(objDataModel.getImgSrc())
                .placeholder(R.drawable.logo)
                .fit()
                .centerCrop()
                .transform(transformation)
                .into(vh.imghome);

        return convertView;
    }

    public class ViewHolder {
        TextView txtHomeTitle;
        ImageView imghome;
        TextView txtid;
    }

}
