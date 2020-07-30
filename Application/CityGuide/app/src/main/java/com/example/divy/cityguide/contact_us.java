package com.example.divy.cityguide;

import android.app.Fragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
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

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class contact_us extends android.support.v4.app.Fragment {

    Button btnappsubmit;
    EditText editappname, editappemail, editapprphone, editappreview;
    TextView txtuername, txtuermail, txtusernumber, txtuserreview, txtcuruemail;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_contact_us, container, false);

        btnappsubmit = (Button) v.findViewById(R.id.btnappsubmit);
        editappname = (EditText) v.findViewById(R.id.editappname);
        editappemail = (EditText) v.findViewById(R.id.editappemail);
        editapprphone = (EditText) v.findViewById(R.id.editapprphone);
        editappreview = (EditText) v.findViewById(R.id.editappreview);

        txtuername = (TextView) v.findViewById(R.id.txtuername);
        txtuermail = (TextView) v.findViewById(R.id.txtuermail);
        txtusernumber = (TextView) v.findViewById(R.id.txtusernumber);
        txtuserreview = (TextView) v.findViewById(R.id.txtuserreview);
        txtcuruemail = (TextView) v.findViewById(R.id.txtcuruemail);


        editappreview.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View view, MotionEvent event) {
                if (view.getId() == R.id.editappreview) {
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

        btnappsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int flag = 0;

                String username = editappname.getText().toString();
                if (username.equals("")) {
                    txtuername.setVisibility(View.VISIBLE);
                    flag = 1;
                } else {
                    txtuername.setVisibility(View.GONE);
                }

                String useremail = editappemail.getText().toString();
                if (useremail.equals("")) {
                    txtcuruemail.setVisibility(View.GONE);
                    txtuermail.setVisibility(View.VISIBLE);
                    flag = 1;
                } else {
                    if (useremail.matches(emailPattern) == false) {
                        txtuermail.setVisibility(View.GONE);
                        txtcuruemail.setVisibility(View.VISIBLE);
                        flag = 1;
                    }
                }

                String usernumber = editapprphone.getText().toString();
                if (usernumber.equals("")) {
                    txtusernumber.setVisibility(View.VISIBLE);
                    flag = 1;
                } else {
                    txtusernumber.setVisibility(View.GONE);
                }

                String userreview = editappreview.getText().toString();
                if (userreview.equals("")) {
                    txtuserreview.setVisibility(View.VISIBLE);
                    flag = 1;
                } else {
                    txtuserreview.setVisibility(View.GONE);
                }

                if (flag == 0) {

                    String URL = getResources().getString(R.string.url) + "city_Guide/UserFeedbackService.php?UserName=" + username + "&UserEmail=" + useremail + "&UserNumber=" + usernumber + "&UserReview=" + userreview.replace(" ", "+");

                    new AsyncCallUserFeedbackService().execute(URL);

                }
            }
        });
        return v;

    }

    private class AsyncCallUserFeedbackService extends AsyncTask<String, Void, String> {
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
        Intent i = new Intent(getActivity(), navigationdrawer.class);
        startActivity(i);

        Toast.makeText(getActivity(), "Thank You", Toast.LENGTH_SHORT).show();
    }
}
