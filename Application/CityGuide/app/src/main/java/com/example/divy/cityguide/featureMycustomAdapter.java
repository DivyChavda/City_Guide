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

public class featureMycustomAdapter extends BaseAdapter {

    ArrayList<DataModel> listDataModel = new ArrayList<DataModel>();
    LayoutInflater minflate;
    Context mContext;

    public featureMycustomAdapter(ArrayList<DataModel> _listDataModel, Context context) {
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
        final featureMycustomAdapter.ViewHolder vh;
        final DataModel objDataModel = (DataModel) getItem(position);
        if (convertView == null) {
            convertView = minflate.inflate(R.layout.listinterface, null);
            vh = new featureMycustomAdapter.ViewHolder();
            vh.txtTitle = (TextView) convertView.findViewById(R.id.txtTitle);
            vh.imgplacestgb = (ImageView) convertView.findViewById(R.id.imgplacestgb);
            vh.txtid1 = (TextView) convertView.findViewById(R.id.txtid1);
            convertView.setTag(vh);
        } else {
            vh = (featureMycustomAdapter.ViewHolder) convertView.getTag();
        }
        vh.txtTitle.setText(objDataModel.getLanguageTitle().toString());
        vh.txtid1.setText(objDataModel.getID().toString());

        final int radius = 10;
        final int margin = 10;
        final Transformation transformation = new RoundedCornersTransformation(radius, margin);
        Picasso.with(mContext)
                .load(objDataModel.getImgSrc())
                .placeholder(R.drawable.logo)
                .fit()
                .centerCrop()
                .transform(transformation)
                .into(vh.imgplacestgb);

        return convertView;
    }

    public class ViewHolder {
        TextView txtTitle;
        ImageView imgplacestgb;
        TextView txtid1;
    }

    public Bitmap getBitmapFromURL(String src) {
        try {
            java.net.URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("Exception", e.getMessage());
            return null;
        }
    }

}
