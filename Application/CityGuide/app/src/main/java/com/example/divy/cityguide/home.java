package com.example.divy.cityguide;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.viewpagerindicator.CirclePageIndicator;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class home extends Fragment {

    GridView listView1;

    String imageurl = "";
    String imgAdurl = "";

    private static ViewPager mPager;
    CirclePageIndicator indicator;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    private ArrayList<ImageModel> imageModelArrayList;

    private int[] myImageList = new int[]{R.drawable.tjb1, R.drawable.tjb2, R.drawable.tjb3};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_home, container, false);

        imageModelArrayList = new ArrayList<>();

        mPager = (ViewPager) v.findViewById(R.id.pager);
        indicator = (CirclePageIndicator) v.findViewById(R.id.indicator);


        imageurl = getResources().getString(R.string.url) + "City_Guide/CategoryIcons/";

        imgAdurl = getResources().getString(R.string.url) + "City_Guide/Advertisement_Image/";

        listView1 = (GridView) v.findViewById(R.id.listViewData);

        /*imghomehotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), featured_places.class);
                startActivity(i);
            }
        });*/

        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                String Id = ((TextView) v.findViewById(R.id.txtid)).getText().toString();
                //Toast.makeText(getActivity(), Id , Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), featured_places.class);
                intent.putExtra("Id", Id);
                startActivity(intent);
            }
        });
        String URL = getResources().getString(R.string.url) + "City_Guide/homeservice.php";
        new AsyncCallHomeService().execute(URL);

        String URL1 = getResources().getString(R.string.url) + "City_Guide/AdvertisementService.php";
        new AsyncCallAdvertisementService().execute(URL1);

        return v;
    }

    private ArrayList<homeDataMode> listArray(String result) {
        ArrayList<homeDataMode> objList = new ArrayList<homeDataMode>();
        homeDataMode dm;

        String KEY_ITEM = "post";
        String KEY_NAME = "CategoryName";
        String IMAGE = "CategoryIcon";
        String KEY_ID = "CategoryId";
        XMLParsing xp = new XMLParsing();
        Document doc = xp.getDomElement(result);

        if (doc != null) {
            NodeList nl = doc.getElementsByTagName(KEY_ITEM);
            if (nl.getLength() > 0) {
                for (int i = 0; i < nl.getLength(); i++) {
                    Element e = (Element) nl.item(i);

                    dm = new homeDataMode();
                    dm.setLanguageTitle(xp.getValue(e, KEY_NAME).toString());
                    dm.setImgSrc(imageurl + xp.getValue(e, IMAGE).toString());
                    dm.setID(xp.getValue(e, KEY_ID).toString());
                    objList.add(dm);
                }
            }
        }
/*


        dm = new DataModel();
        dm.setLanguageTitle("Hospital");
        dm.setImgSrc(R.drawable.hospital);
        objList.add(dm);*/


        return objList;


    }

    private ArrayList<ImageModel> populateList(String result1) {

        ArrayList<ImageModel> list = new ArrayList<ImageModel>();
        ImageModel dm;
        String KEY_ITEM = "post";
        String IMAGE = "AdvertisementImage";

        XMLParsing xp = new XMLParsing();
        Document doc = xp.getDomElement(result1);

        if (doc != null) {
            NodeList nl = doc.getElementsByTagName(KEY_ITEM);
            if (nl.getLength() > 0) {
                for (int i = 0; i < nl.getLength(); i++) {
                    Element e = (Element) nl.item(i);

                    dm = new ImageModel();
                    dm.setImage_drawable(imgAdurl + xp.getValue(e, IMAGE).toString());
                    list.add(dm);
                }
            }
        }

        /*for (int i = 0; i < 3; i++) {
            ImageModel imageModel = new ImageModel();
            imageModel.setImage_drawable(myImageList[i]);
            list.add(imageModel);
        }*/

        return list;
    }

    private void init(View v) {


    }

    private class AsyncCallHomeService extends AsyncTask<String, Void, String> {
        ProgressDialog dialog = new ProgressDialog(getActivity());

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // this = YourActivity
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.setTitle("Loading");
            dialog.setMessage("Loading. Please wait...");
            dialog.setIndeterminate(true);
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
        }

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
            dialog.dismiss();
        }

        private void GetReturnedValueFromAsync(String result) {

            ArrayList<homeDataMode> _liDataModel = listArray(result);
            listView1.setAdapter(new homeMyCustomAdapter(_liDataModel, getActivity()));

        }

    }


    private class AsyncCallAdvertisementService extends AsyncTask<String, Void, String> {
        ProgressDialog dialog = new ProgressDialog(getActivity());

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // this = YourActivity
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.setTitle("Loading");
            dialog.setMessage("Loading. Please wait...");
            dialog.setIndeterminate(true);
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
        }

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
        protected void onPostExecute(String result1) {
            GetReturnedValueFromAd(result1);
            dialog.dismiss();
        }

        private void GetReturnedValueFromAd(String result1) {

            imageModelArrayList = populateList(result1);


            mPager.setAdapter(new SlidingImage_Adapter(getActivity(), imageModelArrayList));


            indicator.setViewPager(mPager);

            final float density = getResources().getDisplayMetrics().density;

//Set circle indicator radius
            indicator.setRadius(5 * density);

            NUM_PAGES = imageModelArrayList.size();

            // Auto start of viewpager
            final Handler handler = new Handler();
            final Runnable Update = new Runnable() {
                public void run() {
                    if (currentPage == NUM_PAGES) {
                        currentPage = 0;
                    }
                    mPager.setCurrentItem(currentPage++, true);
                }
            };
            Timer swipeTimer = new Timer();
            swipeTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    handler.post(Update);
                }
            }, 3000, 3000);

            // Pager listener over indicator
            indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

                @Override
                public void onPageSelected(int position) {
                    currentPage = position;

                }

                @Override
                public void onPageScrolled(int pos, float arg1, int arg2) {

                }

                @Override
                public void onPageScrollStateChanged(int pos) {

                }
            });

        }

    }
}
