package com.example.divy.cityguide;


import android.app.SearchManager;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;
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

public class SearchResultsActivity extends AppCompatActivity {
    private TextView txtQuery;
    GridView listView1;

    String imageurl = "", searchtext = "";

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        toolbar = (Toolbar) findViewById(R.id.SearchToolBar);
        setSupportActionBar(toolbar);

        imageurl = getResources().getString(R.string.url) + "City_Guide/CategoryIcons/";
        listView1 = (GridView) findViewById(R.id.listViewData);

        // Enabling Back navigation on Action Bar icon
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setTitle("Category");

        txtQuery = (TextView) findViewById(R.id.txtQuery);

        handleIntent(getIntent());

        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                String Id = ((TextView) v.findViewById(R.id.txtid)).getText().toString();

                Intent intent = new Intent(SearchResultsActivity.this, SearchCity.class);
                intent.putExtra("Id", Id);
                intent.putExtra("city", searchtext);
                startActivity(intent);
            }
        });
        String URL = getResources().getString(R.string.url) + "City_Guide/searchhomeservice.php?city=" + searchtext;

        new AsyncCallHomeService().execute(URL);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        handleIntent(intent);
    }

    /**
     * Handling intent data
     */
    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);

            /**
             * Use this query to display search results like
             * 1. Getting the data from SQLite and showing in listview
             * 2. Making webrequest and displaying the data
             * For now we just display the query only
             */

            searchtext = query;
        }

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
            } else {
                Toast.makeText(this, "Not found..", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Not found..", Toast.LENGTH_SHORT).show();
        }


        return objList;


    }

    private class AsyncCallHomeService extends AsyncTask<String, Void, String> {

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

        ArrayList<homeDataMode> _liDataModel = listArray(result);

        GridView listViewData = (GridView) findViewById(R.id.listViewData);
        listViewData.setAdapter(new homeMyCustomAdapter(_liDataModel, this));


    }


}
