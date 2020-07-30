package com.example.divy.cityguide;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

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

public class rating_review extends AppCompatActivity {
    String Idb = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating_review);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {

            Idb = extras.getString("Idb");

            //Toast.makeText(this, Idb, Toast.LENGTH_SHORT).show();
        }


        String URL = getResources().getString(R.string.url) + "City_Guide/Reting_Review_Get_Service.php?BusinessIdFK="+ Idb;
        new AsyncCallReting_Review_Get_Service().execute(URL);
    }
    private ArrayList<informationDataModel> listArray(String result) {
        ArrayList<informationDataModel> objList = new ArrayList<informationDataModel>();
        informationDataModel dm;

        String KEY_ITEM = "post";
        String KEY_User = "UserName";
        String KEY_REVIEW = "Review";
        String KEY_RETING = "Rating";

        XMLParsing xp = new XMLParsing();
        Document doc = xp.getDomElement(result);

        if (doc != null) {
            NodeList nl = doc.getElementsByTagName(KEY_ITEM);
            if (nl.getLength() > 0) {
                for (int i = 0; i < nl.getLength(); i++) {
                    Element e = (Element) nl.item(i);

                    dm = new informationDataModel();
                    dm.setLanguageTitle(xp.getValue(e, KEY_User).toString());
                    dm.setreting(xp.getValue(e,KEY_RETING).toString());
                    dm.setDesc(xp.getValue(e,KEY_REVIEW).toString());
                    objList.add(dm);
                }
            }
        }

        /*dm = new informationDataModel();
        dm.setLanguageTitle("Android");
        dm.setDesc("Hello, I am Android..!! I am Mobile Application Development Platform..!!");
        dm.setImgSrc(R.drawable.star);
        objList.add(dm);

        dm = new informationDataModel();
        dm.setLanguageTitle("Android");
        dm.setDesc("Hello, I am Android..!! I am Mobile Application Development Platform..!!");
        dm.setImgSrc(R.drawable.star);
        objList.add(dm);

        dm = new informationDataModel();
        dm.setLanguageTitle("Android");
        dm.setDesc("Hello, I am Android..!! I am Mobile Application Development Platform..!!");
        dm.setImgSrc(R.drawable.star);
        objList.add(dm);

        dm = new informationDataModel();
        dm.setLanguageTitle("Android");
        dm.setDesc("Hello, I am Android..!! I am Mobile Application Development Platform..!!");
        dm.setImgSrc(R.drawable.star);
        objList.add(dm);

        dm = new informationDataModel();
        dm.setLanguageTitle("Android");
        dm.setDesc("Hello, I am Android..!! I am Mobile Application Development Platform..!!");
        dm.setImgSrc(R.drawable.star);
        objList.add(dm);

        dm = new informationDataModel();
        dm.setLanguageTitle("Android");
        dm.setDesc("Hello, I am Android..!! I am Mobile Application Development Platform..!!");
        dm.setImgSrc(R.drawable.star);
        objList.add(dm);

        dm = new informationDataModel();
        dm.setLanguageTitle("Android");
        dm.setDesc("Hello, I am Android..!! I am Mobile Application Development Platform..!!");
        dm.setImgSrc(R.drawable.star);
        objList.add(dm);

        dm = new informationDataModel();
        dm.setLanguageTitle("Android");
        dm.setDesc("Hello, I am Android..!! I am Mobile Application Development Platform..!!");
        dm.setImgSrc(R.drawable.star);
        objList.add(dm);*/

        return objList;
    }

    private class AsyncCallReting_Review_Get_Service extends AsyncTask<String, Void, String> {
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
    }
    private void GetReturnedValueFromAsync(String result) {

        ArrayList<informationDataModel> _liDataModel = listArray(result);

        ListView listView1 = (ListView) findViewById(R.id.listViewData);
        listView1.setAdapter(new informationMyCustomAdapter(_liDataModel, this));
    }

}
