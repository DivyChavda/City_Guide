package com.example.divy.cityguide;

import android.content.Intent;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.w3c.dom.Text;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class singup extends AppCompatActivity {

    Button btnsingup;
    TextView txtuname, txtuemail, txtcuruemail, txtupass,txtcurupass, txtunumber, ugender, txtlogin;
    EditText Name, Email, Password, Mobile;
    RadioButton RadioButtonMale, RadioButtonFemale;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    private Pattern pattern;
    private Matcher matcher;
    private static final String PASSWORD_PATTERN =
            "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singup);
        btnsingup = (Button) findViewById(R.id.btnsingup);
        Name = (EditText) findViewById(R.id.txtUserName);
        Email = (EditText) findViewById(R.id.textUserEmail);

        Password = (EditText) findViewById(R.id.txtUserPassword);
        Mobile = (EditText) findViewById(R.id.txtUserMobile);
        RadioButtonMale = (RadioButton) findViewById(R.id.robMale);
        RadioButtonFemale = (RadioButton) findViewById(R.id.robFamle);
        txtuname = (TextView) findViewById(R.id.txtuname);
        txtuemail = (TextView) findViewById(R.id.txtuemail);
        txtcuruemail = (TextView) findViewById(R.id.txtcuruemail);
        txtupass = (TextView) findViewById(R.id.txtupass);
        txtcurupass = (TextView) findViewById(R.id.txtcurupass);
        txtunumber = (TextView) findViewById(R.id.txtunumber);
        ugender = (TextView) findViewById(R.id.ugender);
        txtlogin = (TextView) findViewById(R.id.txtlogin);

        btnsingup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int flag = 0;

                String uname = Name.getText().toString();
                if (uname.equals("")) {
                    txtuname.setVisibility(View.VISIBLE);
                    flag = 1;
                } else {
                    txtuname.setVisibility(View.GONE);
                }

                String uemail = Email.getText().toString();
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


                String upass = Password.getText().toString();
                pattern = Pattern.compile(PASSWORD_PATTERN);
                matcher = pattern.matcher(upass);
                if (upass.equals("")) {
                    txtcurupass.setVisibility(View.GONE);
                    txtupass.setVisibility(View.VISIBLE);
                    flag = 1;
                } else {
                    if (upass.matches(PASSWORD_PATTERN) == false) {
                        txtupass.setVisibility(View.GONE);
                        txtcurupass.setVisibility(View.VISIBLE);
                        flag = 1;
                    }
                }

                String umobile = Mobile.getText().toString();
                if (umobile.equals("")) {
                    txtunumber.setVisibility(View.VISIBLE);
                    flag = 1;
                } else {
                    txtunumber.setVisibility(View.GONE);
                }
                String gender = "";
                if (RadioButtonMale.isChecked() == true) {
                    gender = "Male";
                } else if (RadioButtonFemale.isChecked() == true) {
                    gender = "Female";
                }
                if (gender.equals("")) {
                    ugender.setVisibility(View.VISIBLE);
                    flag = 1;
                } else {
                    ugender.setVisibility(View.GONE);
                }

                if (flag == 0) {

                    String URL = getResources().getString(R.string.url) + "City_Guide/UserSignUp.php?UserName=" + uname + "&UserEmail=" + uemail + "&UserPassword=" + upass + "&UserMobileNumber=" + umobile + "&UserGender=" + gender + "";

                    new AsyncCallSingUpService().execute(URL);
                }

            }
        });

        txtlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(singup.this,login.class);
                startActivity(i);
            }
        });

    }

    public class AsyncCallSingUpService extends AsyncTask<String, Void, String> {

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
        Intent i = new Intent(singup.this, login.class);
        startActivity(i);

        Toast.makeText(this, "Registered..", Toast.LENGTH_SHORT).show();
    }
}
