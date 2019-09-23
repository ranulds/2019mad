package com.example.rajadhahana;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Dashboard extends AppCompatActivity {
    Button ba,bb,b3;
    String n;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        ba=findViewById(R.id.button2);
        Intent intent2 = getIntent();
        n = intent2.getStringExtra("name");
        ba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(Dashboard.this, Profile.class);
                intent1.putExtra("name",n);
                startActivity(intent1);

            }
        });

        bb=findViewById(R.id.button8);
        bb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(Dashboard.this, Invite.class);
                startActivity(intent1);

            }
        });

        b3 = findViewById(R.id.btncArt);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Dashboard.this, Resevation.class);
                startActivity(intent);
            }
        });

    }
}
