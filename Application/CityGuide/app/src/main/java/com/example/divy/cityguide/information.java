package com.example.divy.cityguide;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

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

public class information extends AppCompatActivity {
    String Idb = "";
    String userid = "";
    TextView infoBusinesstitle, infoBusinessFacilities, infoBusinessAddress, infoBusinessMobile, infoBusinessWebsite, infoBusinessDescription, txtreting, txtreview, txtviewall, ratinguser, reviewuser, txtLatitude, txtLongitude, txtname;
    String imageurl = "";
    String Businessimg = "";
    RatingBar ratingbar;
    EditText DwEdit;
    ImageView imgBusiness;
    String rating, review;
    public static final String PreferencesName = "AppPref";
    SharedPreferences sharedPreferences;
    ImageView imgfav, imgunfav, img1, img2, img3;
    RatingBar businessretingbar;
    Button mapview;

    LinearLayout linearRate;

    SupportMapFragment mapFragment;
    GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        final EditText dwEdit = (EditText) findViewById(R.id.DwEdit);

        imageurl = getResources().getString(R.string.url) + "Business_User/BusinessIcons/";
        Businessimg = getResources().getString(R.string.url) + "Business_User/BusinessImages/";
        imgBusiness = (ImageView) findViewById(R.id.imgBusiness);
        infoBusinesstitle = (TextView) findViewById(R.id.infoBusinesstitle);
        infoBusinessFacilities = (TextView) findViewById(R.id.infoBusinessFacilities);
        infoBusinessAddress = (TextView) findViewById(R.id.infoBusinessAddress);
        infoBusinessMobile = (TextView) findViewById(R.id.infoBusinessMobile);
        infoBusinessWebsite = (TextView) findViewById(R.id.infoBusinessWebsite);
        infoBusinessDescription = (TextView) findViewById(R.id.infoBusinessDescription);
        ratingbar = (RatingBar) findViewById(R.id.ratingBar);
        txtreting = (TextView) findViewById(R.id.txtreting);
        txtreview = (TextView) findViewById(R.id.txtreview);
        txtviewall = (TextView) findViewById(R.id.txtviewall);
        img1 = (ImageView) findViewById(R.id.img1);
        img2 = (ImageView) findViewById(R.id.img2);
        img3 = (ImageView) findViewById(R.id.img3);
        businessretingbar = (RatingBar) findViewById(R.id.businessretingbar);
        ratinguser = (TextView) findViewById(R.id.ratinguser);
        reviewuser = (TextView) findViewById(R.id.reviewuser);
        linearRate = (LinearLayout) findViewById(R.id.linearRate);
        txtLatitude = (TextView) findViewById(R.id.txtLatitude);
        txtLongitude = (TextView) findViewById(R.id.txtLongitude);

        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapinfo);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {

            Idb = extras.getString("Idb");

            //Toast.makeText(this, Idb, Toast.LENGTH_SHORT).show();
        }

        mapview = (Button) findViewById(R.id.mapview);

        mapview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String latitude = txtLatitude.getText().toString();
                String longitude = txtLongitude.getText().toString();
                String name = infoBusinesstitle.getText().toString();
                Intent map = new Intent(information.this, MapsActivity.class);
                map.putExtra("Name", name);
                map.putExtra("Latitude", latitude);
                map.putExtra("Longitude", longitude);
                startActivity(map);
            }
        });


        sharedPreferences = getSharedPreferences(PreferencesName, Context.MODE_PRIVATE);
        if (sharedPreferences != null) {
            userid = sharedPreferences.getString("UserId", null);
        }

        Button btnselectorsubmit;


        imgfav = (ImageView) findViewById(R.id.imgfav);
        imgunfav = (ImageView) findViewById(R.id.imgunfav);

        dwEdit.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View view, MotionEvent event) {
                if (view.getId() == R.id.DwEdit) {
                    view.getParent().requestDisallowInterceptTouchEvent(true);
                    switch (event.getAction() & MotionEvent.ACTION_MASK) {
                        case MotionEvent.ACTION_UP:
                            view.getParent().requestDisallowInterceptTouchEvent(false);
                            break;
                    }
                }
                return false;
            }
        });

        btnselectorsubmit = (Button) findViewById(R.id.btnselectorsubmit);
        btnselectorsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int flag = 0;

                sharedPreferences = getSharedPreferences(PreferencesName, Context.MODE_PRIVATE);
                if (sharedPreferences != null) {
                    String userName = sharedPreferences.getString("Username", null);
                    if (userName != null) {

                        rating = String.valueOf(ratingbar.getRating());
                        if (rating.equals("0.0")) {
                            txtreting.setVisibility(View.VISIBLE);
                            flag = 1;
                        } else {
                            txtreting.setVisibility(View.GONE);
                        }
                        String review = dwEdit.getText().toString();
                        if (review.equals("")) {
                            txtreview.setVisibility(View.VISIBLE);
                            flag = 1;
                        } else {
                            txtreview.setVisibility(View.GONE);
                        }

                        if (flag == 0) {
                            String URL1 = getResources().getString(R.string.url) + "City_Guide/Reting_Review.php?BusinessIdFK=" + Idb + "&BusinessUserIdFK=" + userid + "&Rating=" + rating + "&Review=" + review.replace(" ", "+");
                            new AsyncCallretingservice().execute(URL1);
                            dwEdit.setText("");
                            ratingbar.setNumStars(0);

                        }
                    } else {
                        Intent i = new Intent(information.this, selector.class);
                        startActivity(i);
                    }

                } else {
                    Intent i = new Intent(information.this, selector.class);
                    startActivity(i);
                }


            }
        });

        String URL3 = getResources().getString(R.string.url) + "City_Guide/Reting_Review_Get_Service1.php?BusinessIdFK=" + Idb;
        new AsyncCallReting_Review_Get_Service1().execute(URL3);


        imgfav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sharedPreferences != null) {
                    String userName = sharedPreferences.getString("Username", null);
                    if (userName != null) {
                        String URL2 = getResources().getString(R.string.url) + "City_Guide/FavouriteInsertService.php?BussinessIdFK=" + Idb + "&BusinessUserIdFK=" + userid;
                        new AsyncCallgetfavoriteinsert().execute(URL2);

                    } else {
                        Intent i = new Intent(information.this, selector.class);
                        startActivity(i);
                    }
                } else {
                    Intent i = new Intent(information.this, selector.class);
                    startActivity(i);
                }
            }
        });

        imgunfav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sharedPreferences != null) {
                    String userName = sharedPreferences.getString("Username", null);
                    if (userName != null) {
                        String URL3 = getResources().getString(R.string.url) + "City_Guide/FavouriteDeleteService.php?BussinessIdFK=" + Idb + "&BusinessUserIdFK=" + userid;
                        new AsyncCallgetfavoritedelete().execute(URL3);
                    } else {
                        Intent i = new Intent(information.this, selector.class);
                        startActivity(i);
                    }
                } else {
                    Intent i = new Intent(information.this, selector.class);
                    startActivity(i);
                }
            }
        });

        String URL = getResources().getString(R.string.url) + "city_Guide/informationservice.php?BusinessId=" + Idb;
        new AsyncCallinformationservice().execute(URL);

        String URL2 = getResources().getString(R.string.url) + "city_Guide/BusinessImageService.php?BusinessIdFK=" + Idb;
        new AsyncCallBusinessImageService().execute(URL2);

        if (userid != null) {
            String URL1 = getResources().getString(R.string.url) + "city_Guide/FavouriteService.php?BussinessIdFK=" + Idb + "&BusinessUserIdFK=" + userid + "";
            new AsyncCallgetfavorite().execute(URL1);
        }

        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(information.this, business_images.class);
                i.putExtra("Idb", Idb);
                i.putExtra("num", "0");
                startActivity(i);
            }
        });

        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(information.this, business_images.class);
                i.putExtra("Idb", Idb);
                i.putExtra("num", "1");
                startActivity(i);
            }
        });

        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(information.this, business_images.class);
                i.putExtra("Idb", Idb);
                i.putExtra("num", "2");
                startActivity(i);
            }
        });

        txtviewall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(information.this, rating_review.class);
                i1.putExtra("Idb", Idb);
                startActivity(i1);
            }
        });
    }


    private void saveStae(boolean isFavourite) {
        SharedPreferences aSharedPreferenes = this.getSharedPreferences(
                "Favourite", Context.MODE_PRIVATE);
        SharedPreferences.Editor aSharedPreferenesEdit = aSharedPreferenes
                .edit();
        aSharedPreferenesEdit.putBoolean("State", isFavourite);
        aSharedPreferenesEdit.commit();
    }

    private boolean readStae() {
        SharedPreferences aSharedPreferenes = this.getSharedPreferences(
                "Favourite", Context.MODE_PRIVATE);
        return aSharedPreferenes.getBoolean("State", true);
    }

    /*@Override
    public void onBackPressed() {
        Intent i = new Intent(information.this, navigationdrawer.class);
        startActivity(i);
    }*/

    private class AsyncCallinformationservice extends AsyncTask<String, Void, String> {
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

        protected void onPostExecute(String result) {
            GetReturnedValueFromAsync(result);
        }

    }

    private void GetReturnedValueFromAsync(String result) {
        String KEY_ITEM = "post";
        String IMAGE = "BusinessProfilePicture";
        String KEY_NAME = "BusinessTitle";
        String KEY_FACILITIES = "BusinessFacility";
        String KEY_ADDRESS = "BusinessAddress";
        String KEY_MOBILE = "BusinessPhoneNo";
        String KEY_WEBSITE = "BusinessWebsite";
        String KEY_DESCRIPTION = "BusinessDescription";
        String KEY_LATITUDE = "BusinessLatitude";
        String KEY_LONGITUDE = "BusinessLongitude";


        final XMLParsing xp = new XMLParsing();
        Document doc = xp.getDomElement(result);

        if (doc != null) {
            NodeList nl = doc.getElementsByTagName(KEY_ITEM);
            if (nl.getLength() > 0) {
                Element e = (Element) nl.item(0);

                final String image = xp.getValue(e, IMAGE).toString();
                String title = xp.getValue(e, KEY_NAME).toString();
                String facilities = xp.getValue(e, KEY_FACILITIES).toString();
                String address = xp.getValue(e, KEY_ADDRESS).toString();
                String mobile = xp.getValue(e, KEY_MOBILE).toString();
                String website = xp.getValue(e, KEY_WEBSITE).toString();
                String description = xp.getValue(e, KEY_DESCRIPTION).toString();
                String latitude = xp.getValue(e, KEY_LATITUDE).toString();
                String gongitude = xp.getValue(e, KEY_LONGITUDE).toString();


                infoBusinesstitle.setText(title);
                infoBusinessFacilities.setText(facilities);
                infoBusinessAddress.setText(address);
                infoBusinessMobile.setText(mobile);
                infoBusinessWebsite.setText(website);
                infoBusinessDescription.setText(description);
                txtLatitude.setText(latitude);
                txtLongitude.setText(gongitude);

                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        String strurl = imageurl + image;
                        final Bitmap b = getBitmapFromURL(strurl);
                        imgBusiness.post(new Runnable() {
                            @Override
                            public void run() {
                                imgBusiness.setImageBitmap(b);
                            }
                        });
                    }
                }).start();
            }
        }

        if (mapFragment != null) {
            mapFragment.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(GoogleMap map) {
                    loadMap(map);
                }
            });
        } else {
            Toast.makeText(this, "Error - Map Fragment was null!!", Toast.LENGTH_SHORT).show();
        }

    }

    protected void loadMap(GoogleMap googleMap) {
        mMap = googleMap;
        if (mMap != null) {

            LatLng sydney = new LatLng(Double.valueOf(txtLatitude.getText().toString()), Double.valueOf(txtLongitude.getText().toString()));
            mMap.addMarker(new MarkerOptions().position(sydney).title(infoBusinesstitle.getText().toString()));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(Double.valueOf(txtLatitude.getText().toString()), Double.valueOf(txtLongitude.getText().toString())), 17.0f));

        } else {
            Toast.makeText(this, "Error - Map was null!!", Toast.LENGTH_SHORT).show();
        }
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

    private class AsyncCallretingservice extends AsyncTask<String, Void, String> {
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
            GetReturnedValueFromAsync1(result1);
        }

    }

    public void GetReturnedValueFromAsync1(String result1) {
        Toast.makeText(this, "Success..", Toast.LENGTH_SHORT).show();
    }


    private class AsyncCallgetfavorite extends AsyncTask<String, Void, String> {
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
        protected void onPostExecute(String result2) {
            GetReturnedValueFromgetfavorite(result2);
        }
    }

    public void GetReturnedValueFromgetfavorite(String result2) {
        String KEY_ITEM = "post";
        XMLParsing xp = new XMLParsing();
        Document doc = xp.getDomElement(result2);
        if (doc != null) {
            NodeList nl = doc.getElementsByTagName(KEY_ITEM);
            if (nl.getLength() > 0) {
                //  imgfav.setImageResource(R.drawable.heart1);
                imgfav.setVisibility(View.GONE);
                imgunfav.setVisibility(View.VISIBLE);

            } else {
                imgfav.setVisibility(View.VISIBLE);
                imgunfav.setVisibility(View.GONE);
                //imgfav.setImageResource(R.drawable.heart);
            }
        } else {
            imgfav.setVisibility(View.VISIBLE);
            imgunfav.setVisibility(View.GONE);
        }
    }

    private class AsyncCallgetfavoriteinsert extends AsyncTask<String, Void, String> {
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
        protected void onPostExecute(String result2) {
            GetReturnedValueFromgetfavoriteinsert(result2);
        }

    }

    public void GetReturnedValueFromgetfavoriteinsert(String result2) {

        imgfav.setVisibility(View.GONE);
        imgunfav.setVisibility(View.VISIBLE);

    }

    private class AsyncCallgetfavoritedelete extends AsyncTask<String, Void, String> {

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
        protected void onPostExecute(String result2) {
            GetReturnedValueFromgetfavoritedelete(result2);
        }
    }

    public void GetReturnedValueFromgetfavoritedelete(String result2) {

        imgunfav.setVisibility(View.GONE);
        imgfav.setVisibility(View.VISIBLE);

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
        protected void onPostExecute(String result3) {
            GetReturnedValueFromgetBusinessImg(result3);
        }
    }

    public void GetReturnedValueFromgetBusinessImg(String result3) {
        String KEY_ITEM = "post";
        String IMAGE = "BusinessImages";
        final XMLParsing xp = new XMLParsing();
        Document doc = xp.getDomElement(result3);

        if (doc != null) {
            NodeList nl = doc.getElementsByTagName(KEY_ITEM);
            if (nl.getLength() > 0) {
                Element e = (Element) nl.item(0);

                final String image = xp.getValue(e, IMAGE).toString();

                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        String strurls = Businessimg + image;
                        final Bitmap b = getBitmapFromURL1(strurls);
                        img1.post(new Runnable() {
                            @Override
                            public void run() {
                                img1.setImageBitmap(b);
                            }
                        });
                    }
                }).start();
            }

            if (nl.getLength() > 0) {
                Element e = (Element) nl.item(1);

                final String image = xp.getValue(e, IMAGE).toString();

                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        String strurls = Businessimg + image;
                        final Bitmap b = getBitmapFromURL1(strurls);
                        img2.post(new Runnable() {
                            @Override
                            public void run() {
                                img2.setImageBitmap(b);
                            }
                        });
                    }
                }).start();
            }

            if (nl.getLength() > 0) {
                Element e = (Element) nl.item(2);

                final String image = xp.getValue(e, IMAGE).toString();

                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        String strurls = Businessimg + image;
                        final Bitmap b = getBitmapFromURL1(strurls);
                        img3.post(new Runnable() {
                            @Override
                            public void run() {
                                img3.setImageBitmap(b);
                            }
                        });
                    }
                }).start();
            }
        }
    }

    public Bitmap getBitmapFromURL1(String src1) {
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


    private class AsyncCallReting_Review_Get_Service1 extends AsyncTask<String, Void, String> {
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
        protected void onPostExecute(String result4) {
            GetReturnedValueFromgetRating(result4);
        }
    }

    private void GetReturnedValueFromgetRating(String result4) {
        String KEY_ITEM = "post";
        String KEY_User = "UserName";
        String KEY_REVIEW = "Review";
        String KEY_RETING = "Rating";


        final XMLParsing xp = new XMLParsing();
        Document doc = xp.getDomElement(result4);

        if (doc != null) {
            NodeList nl = doc.getElementsByTagName(KEY_ITEM);
            if (nl.getLength() > 0) {
                Element e = (Element) nl.item(0);

                String title = xp.getValue(e, KEY_User).toString();
                String description = xp.getValue(e, KEY_REVIEW).toString();
                String Rating = xp.getValue(e, KEY_RETING).toString();


                ratinguser.setText(title);
                businessretingbar.setRating(Float.valueOf(Rating));
                reviewuser.setText(description);

            } else {
                linearRate.setVisibility(View.GONE);
            }
        } else {
            linearRate.setVisibility(View.GONE);
        }

    }
}
