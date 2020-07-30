package com.example.divy.cityguide;

import android.content.Intent;
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

public class forgetpassword extends AppCompatActivity {
Button btnsubmit;
EditText edtemail;
TextView txtuemail,txtuemailvalidation;
String uemail;
String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
private static final String PASSWORD_PATTERN =
        "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpassword);
        edtemail = (EditText) findViewById(R.id.edtemail);
        txtuemail = (TextView) findViewById(R.id.txtuemail);
        txtuemailvalidation = (TextView)findViewById(R.id.txtuemailvalidation);
        btnsubmit = (Button) findViewById(R.id.btnsubmit);

        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int flag = 0;
                uemail = edtemail.getText().toString();
                if(uemail.equals("")){
                    txtuemailvalidation.setVisibility(View.GONE);
                    txtuemail.setVisibility(view.VISIBLE);
                    flag=1;
                }else {
                    if (uemail.matches(emailPattern) == false) {
                        txtuemail.setVisibility(View.GONE);
                        txtuemailvalidation.setVisibility(View.VISIBLE);
                        flag = 1;
                    }
                }
                if(flag == 0) {
                    String URL = getResources().getString(R.string.url) + "City_Guide/ForgetPassEmailService.php?UserEmail=" + uemail;

                    new AsyncCallForgrtPassService().execute(URL);
                }
            }
        });
    }

    private class AsyncCallForgrtPassService extends AsyncTask<String, Void, String> {

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

                    Toast.makeText(this, "Code Sent In Your Email", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, checkcodeforgetpassword.class);
                    intent.putExtra("uemail", uemail);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(this, "Email is wrong..", Toast.LENGTH_SHORT).show();
                }
            }
            else
            {
                Toast.makeText(this, "Email is wrong..", Toast.LENGTH_SHORT).show();
            }
        }
    }
}