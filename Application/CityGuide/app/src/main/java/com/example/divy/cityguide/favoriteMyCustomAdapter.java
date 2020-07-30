package com.example.divy.cityguide;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

public class favoriteMyCustomAdapter extends BaseAdapter {

    ArrayList<favoriteDataModel> listDataModel = new ArrayList<favoriteDataModel>();
    LayoutInflater minflate;
    String URL2 = "";
    Context mContext;

    public favoriteMyCustomAdapter(ArrayList<favoriteDataModel> _listDataModel, Context context) {
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
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
        final ViewHolder vh;
        final favoriteDataModel objDataModel = (favoriteDataModel) getItem(position);
        if (convertView == null) {
            convertView = minflate.inflate(R.layout.favoriteinterface, null);
            vh = new ViewHolder();
            vh.txtfavorite = (TextView) convertView.findViewById(R.id.txtfavorite);
            vh.txtfavoriteDesc = (TextView) convertView.findViewById(R.id.txtfavoriteDesc);
            //userid
            //businessid
            vh.imgfavorite = (ImageView) convertView.findViewById(R.id.imgfavorite);
            vh.imgdelete = (ImageView) convertView.findViewById(R.id.imgdelete);
            vh.txtuserid = (TextView) convertView.findViewById(R.id.txtuserid);
            vh.txtbusinessid = (TextView) convertView.findViewById(R.id.txtbusinessid);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        vh.txtfavorite.setText(objDataModel.getLanguageTitle().toString());

        vh.txtfavoriteDesc.setText(objDataModel.getDesc().toString());

        vh.txtuserid.setText(objDataModel.getUserID().toString());

        vh.txtbusinessid.setText(objDataModel.getBusinessID().toString());

        /*new Thread(new Runnable() {
            @Override
            public void run() {

                String strurl = objDataModel.getImgSrc();
                final Bitmap b = getBitmapFromURL(strurl);
                vh.imgfavorite.post(new Runnable() {
                    @Override
                    public void run() {
                        vh.imgfavorite.setImageBitmap(b);
                    }
                });
            }
        }).start();*/

        Picasso.with(mContext)
                .load(objDataModel.getImgSrc())
                .placeholder(R.drawable.logo)
                .fit()
                .centerCrop()
                .into(vh.imgfavorite);


        vh.imgdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String userid = vh.txtuserid.getText().toString();
                String Idb = vh.txtbusinessid.getText().toString();

                URL2 = mContext.getResources().getString(R.string.url);

                URL2 = URL2 + "City_Guide/FavouriteDeleteService.php?BussinessIdFK=" + Idb + "&BusinessUserIdFK=" + userid;
                new AsyncCallgetfavoriteAfterdelete().execute(URL2);
                listDataModel.remove(position);
                updateResults(listDataModel);
            }
        });

        return convertView;
    }

    public void updateResults(ArrayList<favoriteDataModel> results) {
        //Triggers the list update
        notifyDataSetChanged();
    }


    public class ViewHolder {
        TextView txtfavorite, txtfavoriteDesc, txtuserid, txtbusinessid;
        ImageView imgfavorite, imgdelete;
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

    private class AsyncCallgetfavoriteAfterdelete extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            String xml = null;

            try {
                // defaultHttpClient
                DefaultHttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost(String.valueOf(params[0]));

                HttpResponse httpResponse = httpClient.execute(httpPost);
                HttpEntity httpEntity = httpResponse.getEntity();
                xml = EntityUtils.toString(httpEntity);

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return xml;
        }

        @Override
        protected void onPostExecute(String result) {
            GetReturnedValueFromAsync(result);
        }

        private void GetReturnedValueFromAsync(String result) {

        }

    }

}
