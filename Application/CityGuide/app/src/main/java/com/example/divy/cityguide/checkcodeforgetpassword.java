package com.example.divy.cityguide;

import android.content.Intent;
import android.opengl.Visibility;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class checkcodeforgetpassword extends AppCompatActivity {
Button btnconform;
EditText edtcode;
TextView txtcode;
String uemail;
String code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkcodeforgetpassword);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {

            uemail = extras.getString("uemail");

        }

        edtcode = (EditText) findViewById(R.id.edtcode);
        txtcode = (TextView) findViewById(R.id.txtcode);
        btnconform = (Button) findViewById(R.id.btnconform);
        btnconform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int flag=0;
                code = edtcode.getText().toString();
                if(code.equals("")){
                    txtcode.setVisibility(View.VISIBLE);
                    flag=1;
                }
                if(flag == 0){

                    String URL = getResources().getString(R.string.url) + "City_Guide/CheckCodeForgetPassService.php?Email="+ uemail +"&Code="+ code;

                    new AsyncCallCheckCodeForgetPassService().execute(URL);
                }
            }
        });
    }

    private class AsyncCallCheckCodeForgetPassService extends AsyncTask<String, Void, String> {
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
    public void GetReturnedValueFromAsync(String result) {
        String KEY_ITEM = "post";
        String key_result = "result";

        XMLParsing xp = new XMLParsing();
        Document doc = xp.getDomElement(result);
        if (doc != null) {
            NodeList nl = doc.getElementsByTagName(KEY_ITEM);
            if (nl.getLength() > 0) {
                Element e = (Element) nl.item(0);
                String s = xp.getValue(e, key_result);
                if (s.equals("success") == true) {

                    Toast.makeText(this, "Password Send In Your Email", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, login.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(this, "Code is wrong..", Toast.LENGTH_SHORT).show();
                }
            }
            else
            {
                Toast.makeText(this, "Code is wrong..", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
