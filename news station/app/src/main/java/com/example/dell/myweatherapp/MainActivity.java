package com.example.dell.myweatherapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
Button i1;
ImageView theme;
EditText e1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
;

        e1=findViewById(R.id.e1);

        i1=findViewById(R.id.imageview1);
        i1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String topic=e1.getText().toString().trim();

                Intent i=new Intent(MainActivity.this,Main2Activity.class);
                i.putExtra("topic",topic);
                startActivity(i);

            }
        });
    }
}
