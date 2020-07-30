package com.example.divy.cityguide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class selector extends AppCompatActivity {
    Button btnselectorlogin, btnselectorsingup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selector);

        btnselectorlogin = (Button) findViewById(R.id.btnselectorlogin);
        btnselectorlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(selector.this, login.class);
                startActivity(i);
            }
        });
        btnselectorsingup = (Button) findViewById(R.id.btnselectorsingup);
        btnselectorsingup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(selector.this, singup.class);
                startActivity(i);
            }
        });
    }
}
