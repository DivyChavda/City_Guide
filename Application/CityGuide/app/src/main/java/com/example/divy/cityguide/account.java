package com.example.divy.cityguide;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
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

public class account extends Fragment {
    Button btnupdate;
    EditText txtUserName,textUserEmail,txtUserMobile;
    RadioButton robMale,robFamle;
    TextView txtuname,txtuemail,txtcuruemail,txtunumber,ugender;
    String userid;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    public static final String PreferencesName = "AppPref";

    SharedPreferences sharedPreferences;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_account, container, false);

        btnupdate = (Button) v.findViewById(R.id.btnupdate);
        txtUserName = (EditText) v.findViewById(R.id.txtUserName);
        textUserEmail = (EditText) v.findViewById(R.id.textUserEmail);
        txtUserMobile = (EditText) v.findViewById(R.id.txtUserMobile);
        robMale = (RadioButton) v.findViewById(R.id.robMale);
        robFamle = (RadioButton) v.findViewById(R.id.robFamle);
        txtuname = (TextView) v.findViewById(R.id.txtuname);
        txtuemail = (TextView) v.findViewById(R.id.txtuemail);
        txtcuruemail = (TextView) v.findViewById(R.id.txtcuruemail);
        txtunumber = (TextView) v.findViewById(R.id.txtunumber);
        ugender = (TextView) v.findViewById(R.id.ugender);

        sharedPreferences = getActivity().getSharedPreferences(PreferencesName, Context.MODE_PRIVATE);
        if (sharedPreferences != null) {
            userid = sharedPreferences.getString("UserId", null);
        }

        String URL = getResources().getString(R.string.url) + "city_Guide/AccountService.php?UserId=" + userid;

        new AsyncCallAccountService().execute(URL);

        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int flag = 0;

                String uname = txtUserName.getText().toString();
                if (uname.equals("")) {
                    txtuname.setVisibility(View.VISIBLE);
                    flag = 1;
                } else {
                    txtuname.setVisibility(View.GONE);
                }

                String uemail = textUserEmail.getText().toString();

                if (uemail.equals("")) {
                    txtcuruemail.setVisibility(View.GONE);
                    txtuemail.setVisibility(View.VISIBLE);
                    flag = 1;
                } else {
                    if (uemail.matches(emailPattern) == false) {
                        txtuemail.setVisibility(View.GONE);
                        txtcuruemail.setVisibility(View.VISIBLE);
                        flag = 1;
                    }
                }

                String umobile = txtUserMobile.getText().toString();
                if (umobile.equals("")) {
                    txtunumber.setVisibility(View.VISIBLE);
                    flag = 1;
                } else {
                    txtunumber.setVisibility(View.GONE);
                }

                String gender = "";
                if (robMale.isChecked() == true) {
                    gender = "Male";
                } else if (robFamle.isChecked() == true) {
                    gender = "Female";
                }
                if (gender.equals("")) {
                    ugender.setVisibility(View.VISIBLE);
                    flag = 1;
                } else {
                    ugender.setVisibility(View.GONE);
                }

                if (flag == 0) {
                    String URL1 = getResources().getString(R.string.url) + "city_Guide/AccountChangeService.php?UserName=" + uname + "&UserEmail=" + uemail + "&UserMobileNumber=" + umobile + "&UserGender=" + gender + "&UserId=" + userid;

                    new AsyncCallAccountChangeService().execute(URL1);
                }
            }
        });
        return v;
    }

    private class AsyncCallAccountService extends AsyncTask<String, Void, String> {

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
        String KEY_NAME = "UserName";
        String KEY_EMAIL = "UserEmail";
        String KEY_NUMBER = "UserMobileNumber";
        String KEY_GENDER = "UserGender";

        final XMLParsing xp = new XMLParsing();
        Document doc = xp.getDomElement(result);

        if (doc != null) {
            NodeList nl = doc.getElementsByTagName(KEY_ITEM);
            if (nl.getLength() > 0) {
                Element e = (Element) nl.item(0);

                String name = xp.getValue(e,KEY_NAME).toString();
                String email = xp.getValue(e,KEY_EMAIL).toString();
                String number = xp.getValue(e,KEY_NUMBER).toString();
                String gender = xp.getValue(e,KEY_GENDER).toString();

                txtUserName.setText(name);
                textUserEmail.setText(email);
                txtUserMobile.setText(number);

                if(gender.equals("Male")==true){
                    robMale.setChecked(true);
                }
                if(gender.equals("Female")==true){
                    robFamle.setChecked(true);
                }
            }
        }
    }

    private class AsyncCallAccountChangeService extends AsyncTask<String, Void, String>{

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
            GetReturnedValueFromAsyncaccount(result);
        }
    }
    private void GetReturnedValueFromAsyncaccount(String result) {

        sharedPreferences =getActivity().getSharedPreferences(PreferencesName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Username", txtUserName.getText().toString());
        editor.commit();

        Intent i = new Intent(getActivity(), navigationdrawer.class);
        startActivity(i);

        Toast.makeText(getActivity(), "Profile Update Successful..", Toast.LENGTH_SHORT).show();
    }

}
