package com.example.divy.cityguide;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class business_images extends AppCompatActivity {

    ImageFragmentPagerAdapter imageFragmentPagerAdapter;
    ViewPager viewPager;

    String Idb = "";
    String Businessimg = "";
    String num = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_images);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {

            Idb = extras.getString("Idb");
            num = extras.getString("num");
        }
        Businessimg = getResources().getString(R.string.url) + "Business_User/BusinessImages/";
        viewPager = (ViewPager) findViewById(R.id.pager);

        String URL = getResources().getString(R.string.url) + "city_Guide/BusinessImageService.php?BusinessIdFK=" + Idb;
        new AsyncCallBusinessImageService().execute(URL);
    }

    public static class ImageFragmentPagerAdapter extends FragmentPagerAdapter {

        ArrayList<homeDataMode> _li;

        public ImageFragmentPagerAdapter(FragmentManager fm, ArrayList<homeDataMode> li) {
            super(fm);
            this._li = li;
        }

        @Override
        public int getCount() {
            return _li.size();
        }

        @Override
        public Fragment getItem(int position) {
            SwipeFragment fragment = new SwipeFragment();
            return SwipeFragment.newInstance(position, _li.get(position).getImgSrc().toString());
        }
    }

    public static class SwipeFragment extends Fragment {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View swipeView = inflater.inflate(R.layout.business_images_page, container, false);
            final ImageView imageView = (ImageView) swipeView.findViewById(R.id.imgbusinessimgs);
            Bundle bundle = getArguments();
            int position = bundle.getInt("position");

            final String imageFileName = bundle.getString("imgsrc");

            //IMAGE_NAME[position];

           /* int imgResId = getResources().getIdentifier(imageFileName, "drawable", "com.example.divy.cityguide");
            imageView.setImageResource(imgResId);*/

            new Thread(new Runnable() {
                @Override
                public void run() {

                    String strurls = imageFileName;
                    final Bitmap b = getBitmapFromURL(strurls);
                    imageView.post(new Runnable() {
                        @Override
                        public void run() {
                            imageView.setImageBitmap(b);
                        }
                    });
                }
            }).start();

            return swipeView;
        }

        static SwipeFragment newInstance(int position, String _imgsrc) {
            SwipeFragment swipeFragment = new SwipeFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("position", position);
            bundle.putString("imgsrc", _imgsrc);
            swipeFragment.setArguments(bundle);
            return swipeFragment;
        }
    }

    private class AsyncCallBusinessImageService extends AsyncTask<String, Void, String> {
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
            GetReturnedValueFromgetBusinessImg(result);
        }
    }

    public void GetReturnedValueFromgetBusinessImg(String result) {
        String KEY_ITEM = "post";
        String IMAGE = "BusinessImages";
        final XMLParsing xp = new XMLParsing();
        Document doc = xp.getDomElement(result);

        ArrayList<homeDataMode> objList = new ArrayList<homeDataMode>();
        homeDataMode dm;

        if (doc != null) {
            NodeList nl = doc.getElementsByTagName(KEY_ITEM);
            if (nl.getLength() > 0) {
                for (int i = 0; i < nl.getLength(); i++) {
                    Element e = (Element) nl.item(i);
                    dm = new homeDataMode();
                    dm.setImgSrc(Businessimg + xp.getValue(e, IMAGE).toString());
                    objList.add(dm);
                }
            }
        }
        imageFragmentPagerAdapter = new ImageFragmentPagerAdapter(getSupportFragmentManager(), objList);

        viewPager.setAdapter(imageFragmentPagerAdapter);
        viewPager.setCurrentItem(Integer.valueOf(num));
    }

    public static Bitmap getBitmapFromURL(String src1) {
        try {
            java.net.URL url = new URL(src1);
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
