package com.example.divy.cityguide;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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

import static com.example.divy.cityguide.login.PreferencesName;

public class favorite extends Fragment {
    ListView listView1;
    String imageurl = "";
    String userid = "";
    ImageView imgdelete;
    String Idb;
    public static final String PreferencesName = "AppPref";
    SharedPreferences sharedPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_favorite, container, false);

        imageurl = getResources().getString(R.string.url) + "Business_User/BusinessIcons/";

        Bundle extras = getActivity().getIntent().getExtras();
        if (extras != null) {

            Idb = extras.getString("Idb");

        }

        listView1 = (ListView) v.findViewById(R.id.listViewData);

        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                String Idb = ((TextView) v.findViewById(R.id.txtbusinessid)).getText().toString();
                Intent intent = new Intent(getActivity(), information.class);
                intent.putExtra("Idb", Idb);
                startActivity(intent);
            }
        });

        imgdelete = (ImageView) v.findViewById(R.id.imgdelete);

        sharedPreferences = getActivity().getSharedPreferences(PreferencesName, Context.MODE_PRIVATE);
        if (sharedPreferences != null) {
            userid = sharedPreferences.getString("UserId", null);
            if (userid != null) {
                String URL = getResources().getString(R.string.url) + "City_Guide/FavouriteGetService.php?UserId=" + userid;
                new AsyncCallFavouriteGetService().execute(URL);
            }
        }

        return v;
    }


    private ArrayList<favoriteDataModel> listArray(String result) {
        ArrayList<favoriteDataModel> objList = new ArrayList<favoriteDataModel>();
        favoriteDataModel dm;

        String KEY_ITEM = "post";
        String KEY_NAME = "BusinessTitle";
        String IMAGE = "BusinessProfilePicture";
        String KEY_DESCRIPTION = "BusinessDescription";
        String KEY_USERID = "BusinessUserIdFK";
        String KEY_BUSINESSID = "BussinessIdFK";
        XMLParsing xp = new XMLParsing();
        Document doc = xp.getDomElement(result);

        if (doc != null) {
            NodeList nl = doc.getElementsByTagName(KEY_ITEM);
            if (nl.getLength() > 0) {
                for (int i = 0; i < nl.getLength(); i++) {
                    Element e = (Element) nl.item(i);

                    dm = new favoriteDataModel();
                    dm.setLanguageTitle(xp.getValue(e, KEY_NAME).toString());
                    dm.setImgSrc(imageurl + xp.getValue(e, IMAGE).toString());
                    dm.setUserID(xp.getValue(e, KEY_USERID).toString());
                    dm.setBusinessID(xp.getValue(e, KEY_BUSINESSID).toString());
                    String desc = xp.getValue(e, KEY_DESCRIPTION).toString();
                    if (desc.length() > 70) {
                        dm.setDesc(xp.getValue(e, KEY_DESCRIPTION).toString().substring(0, 70));
                    } else {
                        dm.setDesc(xp.getValue(e, KEY_DESCRIPTION).toString());
                    }

                    objList.add(dm);
                }
            }
        }

        /*dm = new favoriteDataModel();
        dm.setLanguageTitle("Android");
        dm.setDesc("Hello, I am .NET..!! I am Web Application Development Platform..!!");
        dm.setImgSrc(R.drawable.tjb);
        objList.add(dm);

        dm = new favoriteDataModel();
        dm.setLanguageTitle(".NET");
        dm.setDesc("Hello, I am .NET..!! I am Web Application Development Platform..!!");
        dm.setImgSrc(R.drawable.kiranhp);
        objList.add(dm);
*/
        return objList;
    }

    private class AsyncCallFavouriteGetService extends AsyncTask<String, Void, String> {
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

            ArrayList<favoriteDataModel> _liDataModel = listArray(result);
            listView1.setAdapter(new favoriteMyCustomAdapter(_liDataModel, getActivity()));

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
            GetReturnedValueFromAsync1(result);
        }

        private void GetReturnedValueFromAsync1(String result) {

            ArrayList<favoriteDataModel> _liDataModel = listArray(result);
            favoriteMyCustomAdapter f = new favoriteMyCustomAdapter(_liDataModel, getActivity());
            listView1.setAdapter(f);


        }
    }
}