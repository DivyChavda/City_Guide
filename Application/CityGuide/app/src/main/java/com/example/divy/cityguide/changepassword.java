package com.example.divy.cityguide;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class changepassword extends Fragment {
    Button btnsubmit;
    EditText txtCurUPass, txtNewUPass, txtNewReUPass;
    TextView txtCurPass1, txtCurPass2, txtNewPass1, txtNewPass2, txtNewRePass1, txtNewRePass2;
    String userid = "";
    String CurPass, NewPass, ReNewPass;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    private Pattern pattern;
    private Matcher matcher;
    private static final String PASSWORD_PATTERN =
            "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";
    public static final String PreferencesName = "AppPref";

    SharedPreferences sharedPreferences;

    @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_changepassword, container, false);

        sharedPreferences = getActivity().getSharedPreferences(PreferencesName, Context.MODE_PRIVATE);
        if (sharedPreferences != null) {
            userid = sharedPreferences.getString("UserId", null);
        }

        btnsubmit = (Button) v.findViewById(R.id.btnsubmit);
        txtCurUPass = (EditText) v.findViewById(R.id.txtCurUPass);
        txtNewUPass = (EditText) v.findViewById(R.id.txtNewUPass);
        txtNewReUPass = (EditText) v.findViewById(R.id.txtNewReUPass);

        txtCurPass1 = (TextView) v.findViewById(R.id.txtCurPass1);
        txtCurPass2 = (TextView) v.findViewById(R.id.txtCurPass2);
        txtNewPass1 = (TextView) v.findViewById(R.id.txtNewPass1);
        txtNewPass2 = (TextView) v.findViewById(R.id.txtNewPass2);
        txtNewRePass1 = (TextView) v.findViewById(R.id.txtNewRePass1);
        txtNewRePass2 = (TextView) v.findViewById(R.id.txtNewRePass2);

        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int flag = 0;

                CurPass = txtCurUPass.getText().toString();
                if (CurPass.equals("")) {
                    txtCurPass1.setVisibility(View.VISIBLE);
                    flag = 1;
                } else {
                    txtCurPass1.setVisibility(View.GONE);
                }

                NewPass = txtNewUPass.getText().toString();
                pattern = Pattern.compile(PASSWORD_PATTERN);
                matcher = pattern.matcher(NewPass);
                if (NewPass.equals("")) {
                    txtNewPass2.setVisibility(View.GONE);
                    txtNewPass1.setVisibility(View.VISIBLE);
                    flag = 1;
                } else {
                    if (NewPass.matches(PASSWORD_PATTERN) == false) {
                        txtNewPass2.setVisibility(View.VISIBLE);
                        txtNewPass1.setVisibility(View.GONE);
                        flag = 1;
                    }

                }

                ReNewPass = txtNewReUPass.getText().toString();
                if (ReNewPass.equals("")) {
                    txtNewRePass2.setVisibility(view.GONE);
                    txtNewRePass1.setVisibility(View.VISIBLE);
                    flag = 1;
                } else {
                    if (ReNewPass.equals(NewPass) == false) {
                        txtNewRePass2.setVisibility(view.VISIBLE);
                        txtNewRePass1.setVisibility(View.GONE);
                        flag = 1;
                    }
                }

                if (flag == 0) {
                    String URL = getResources().getString(R.string.url) + "city_Guide/changepasswordservice.php?UserId=" + userid + "&UserPassword=" + CurPass + "&UserNewPassword=" + NewPass;

                    new AsyncCallChangePasswrodService().execute(URL);
                }
            }
        });
        return v;
    }

    private class AsyncCallChangePasswrodService extends AsyncTask<String, Void, String> {

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

                    Toast.makeText(getActivity(), "Passwrod Change Successful..", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getActivity(), "Current password is wrong..", Toast.LENGTH_SHORT).show();
                }
            }
            else
            {
                Toast.makeText(getActivity(), "Current password is wrong..", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
