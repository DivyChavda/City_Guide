package com.example.divy.cityguide;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import java.lang.annotation.Documented;
import java.security.cert.Extension;

public class login extends AppCompatActivity {
    Button btnlogin;
    EditText edtLoginUserEmail, edtLoginUserPassword;
    TextView txtuemail, txtupass,txtforgetpass,txtsingup;
    public static final String PreferencesName = "AppPref";

    SharedPreferences sharedPreferences;

    @Override
    protected void onResume() {
        super.onResume();
        String userid = "";
        sharedPreferences = getSharedPreferences(PreferencesName, Context.MODE_PRIVATE);
        if (sharedPreferences != null) {
            userid = sharedPreferences.getString("UserId", null);
            if (userid != null) {
                Intent i = new Intent(login.this, navigationdrawer.class);
                startActivity(i);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtLoginUserEmail = (EditText) findViewById(R.id.edtLoginUserEmail);
        edtLoginUserPassword = (EditText) findViewById(R.id.edtLoginUserPassword);

        txtuemail = (TextView) findViewById(R.id.txtuemail);
        txtupass = (TextView) findViewById(R.id.txtupass);

        btnlogin = (Button) findViewById(R.id.btnlogin);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int flag = 0;


                String uemail = edtLoginUserEmail.getText().toString();
                if (uemail.equals("")) {
                    txtuemail.setVisibility(View.VISIBLE);
                    flag = 1;
                } else {
                    txtuemail.setVisibility(View.GONE);
                }
                String upass = edtLoginUserPassword.getText().toString();
                if (upass.equals("")) {
                    txtupass.setVisibility(View.VISIBLE);
                    flag = 1;
                } else {
                    txtupass.setVisibility(View.GONE);
                }
                if (flag == 0) {
                    String URL = getResources().getString(R.string.url) + "City_Guide/UserLogin.php?UserEmail=" + uemail + "&UserPassword=" + upass + "";

                    new AsyncCallLoginService().execute(URL);
                }
            }
        });

        txtforgetpass = (TextView)findViewById(R.id.txtforgetpass);

        txtforgetpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(login.this,forgetpassword.class);
                startActivity(i);
            }
        });

        txtsingup = (TextView) findViewById(R.id.txtsingup);

        txtsingup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(login.this,singup.class);
                startActivity(i1);
            }
        });
    }

    public class AsyncCallLoginService extends AsyncTask<String, Void, String> {

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
        String KEY_NAME = "UserName";
        String KEY_ID = "UserId";
        XMLParsing xp = new XMLParsing();
        Document doc = xp.getDomElement(result);

        if (doc != null) {
            NodeList nl = doc.getElementsByTagName(KEY_ITEM);
            if (nl.getLength() > 0) {
                Element e = (Element) nl.item(0);
                String s = xp.getValue(e, KEY_NAME);
                String uid = xp.getValue(e, KEY_ID);


                //Initialize object of SharedPreferences and storing data
                sharedPreferences = getSharedPreferences(PreferencesName, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("Username", s);
                editor.putString("UserId", uid);
                editor.commit();

                Intent i = new Intent(login.this, navigationdrawer.class);
                startActivity(i);
                Toast.makeText(this, "Login..", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Invalid..", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Invalid..", Toast.LENGTH_SHORT).show();
        }
    }


}
