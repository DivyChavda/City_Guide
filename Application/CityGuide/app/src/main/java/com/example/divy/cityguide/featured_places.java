package com.example.divy.cityguide;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
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

public class featured_places extends AppCompatActivity {

    String imageurl="";
    ImageView imgplacestgb;
    GridView listView1;
    String Id = "";
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_featured_places);

        toolbar = (Toolbar) findViewById(R.id.FeatureToolBar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setTitle("Places");

        Bundle extras = getIntent().getExtras();
        if (extras != null) {

            Id = extras.getString("Id");

            //Toast.makeText(this, Id, Toast.LENGTH_SHORT).show();
        }

        //imgplacestgb = (ImageView) findViewById(R.id.imgplacestgb);
        imageurl=getResources().getString(R.string.url)+"Business_User/BusinessIcons/";


         listView1 = (GridView) findViewById(R.id.listViewDatafeatured);


        /*imgplacestgb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(featured_places.this,information.class);
                startActivity(i);
            }
        });*/

        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                String Idb = ((TextView) v.findViewById(R.id.txtid1)).getText().toString();
                Intent intent = new Intent(featured_places.this,information.class);
                intent.putExtra("Idb", Idb);
                startActivity(intent);
            }
        });

        String URL = getResources().getString(R.string.url)+"City_Guide/featuredservice.php?BusinessCategoryIdFK="+Id;

        new AsyncCallFeatureService().execute(URL);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public ArrayList<DataModel> listArray(String result) {
        ArrayList<DataModel> objList = new ArrayList<DataModel>();
        DataModel dm;

        String KEY_ITEM = "post";
        String KEY_NAME = "BusinessTitle";
        String IMAGE = "BusinessProfilePicture";
        String KEY_ID = "BusinessId";
        XMLParsing xp = new XMLParsing();
        Document doc = xp.getDomElement(result);

        if (doc != null) {
            NodeList nl = doc.getElementsByTagName(KEY_ITEM);
            if (nl.getLength() > 0) {
                for (int i = 0; i < nl.getLength(); i++) {
                    Element e = (Element) nl.item(i);

                    dm = new DataModel();
                    dm.setLanguageTitle(xp.getValue(e, KEY_NAME).toString());
                    dm.setImgSrc(imageurl+xp.getValue(e,IMAGE).toString());
                    dm.setID(xp.getValue(e,KEY_ID).toString());
                    objList.add(dm);
                }
            }
        }

        /*dm = new DataModel();
        dm.setLanguageTitle("TGB");
        dm.setImgSrc(R.drawable.tjb);
        objList.add(dm);

        dm = new DataModel();
        dm.setLanguageTitle("Kiran Hospital");
        dm.setImgSrc(R.drawable.kiranhp);
        objList.add(dm);

        dm = new DataModel();
        dm.setLanguageTitle("Tulsi Restaurant");
        dm.setImgSrc(R.drawable.tulsi);
        objList.add(dm);

        dm = new DataModel();
        dm.setLanguageTitle("SBI ATM");
        dm.setImgSrc(R.drawable.sbi);
        objList.add(dm);

        dm = new DataModel();
        dm.setLanguageTitle("Kiran Hospital");
        dm.setImgSrc(R.drawable.kiranhp);
        objList.add(dm);

        dm = new DataModel();
        dm.setLanguageTitle("Kiran Hospital");
        dm.setImgSrc(R.drawable.kiranhp);
        objList.add(dm);

        dm = new DataModel();
        dm.setLanguageTitle("Kiran Hospital");
        dm.setImgSrc(R.drawable.kiranhp);
        objList.add(dm);

        dm = new DataModel();
        dm.setLanguageTitle("Kiran Hospital");
        dm.setImgSrc(R.drawable.kiranhp);
        objList.add(dm);

        dm = new DataModel();
        dm.setLanguageTitle("Kiran Hospital");
        dm.setImgSrc(R.drawable.kiranhp);
        objList.add(dm);

        dm = new DataModel();
        dm.setLanguageTitle("Kiran Hospital");
        dm.setImgSrc(R.drawable.kiranhp);
        objList.add(dm);

        dm = new DataModel();
        dm.setLanguageTitle("Kiran Hospital");
        dm.setImgSrc(R.drawable.kiranhp);
        objList.add(dm);

        dm = new DataModel();
        dm.setLanguageTitle("Kiran Hospital");
        dm.setImgSrc(R.drawable.kiranhp);
        objList.add(dm);
        return  objList;*/
        return objList;
    }


    private class AsyncCallFeatureService extends AsyncTask<String, Void, String> {
        ProgressDialog dialog = new ProgressDialog(featured_places.this);

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

    }
    private void GetReturnedValueFromAsync(String result) {

        ArrayList<DataModel> _liDataModel = listArray(result);
        listView1.setAdapter(new featureMycustomAdapter(_liDataModel,this));

    }
}
