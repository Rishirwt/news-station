package com.example.dell.myweatherapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class Main2Activity extends AppCompatActivity {
    RecyclerView rv;
TextView t1;

String topic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent getme=getIntent();
         topic=getme.getStringExtra("topic");
         t1=findViewById(R.id.t1);
         t1.setText("new feed related to topic - "+topic +":");

updateview(topic);

    }

    private void updateview(String topic) {

        networktask nt=new networktask();
        nt.execute("https://newsapi.org/v2/everything?q="+topic+"&apiKey=ca80b8fe731b4e93a459ae6edf0ccad8");

    }

    public void onclick(View view) {
        Intent i=new Intent(Main2Activity.this,MainActivity.class);
        startActivity(i);
    }

    class networktask extends AsyncTask<String,Void,String>
    {

        @Override
        protected String doInBackground(String... strings) {
            String url=strings[0];
            try {
                URL url1=new URL(url);
                HttpURLConnection httpURLConnection= (HttpURLConnection) url1.openConnection();
                InputStream inputStream=httpURLConnection.getInputStream();
                Scanner scanner=new Scanner(inputStream);
                scanner.useDelimiter("\\A");
                if(scanner.hasNext())
                {
                    String s=scanner.next();
                    return s;
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "failed to load";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            ArrayList<newsblock> user=parsejson(s);
            rv=findViewById(R.id.rv);

            newsadapt na=new newsadapt(user);

            rv.setLayoutManager(new LinearLayoutManager(getBaseContext()));


            rv.setAdapter(na);

            Log.e("Mainact",""+user.size());
//            t1=findViewById(R.id.t1);
//            t1.setText(s);
        }
    }
    ArrayList<newsblock> parsejson(String s)
    {
        ArrayList<newsblock> ar=new ArrayList<>();
        try {
            JSONObject root=new JSONObject(s);
            JSONArray item=root.getJSONArray("articles");
            for (int i = 0; i < item.length(); i++) {
                JSONObject user=item.getJSONObject(i);
                String published=user.getString("publishedAt");
                String image=user.getString("urlToImage");
                String title=user.getString("title");
               String desc=user.getString("description");
                String author=user.getString("author");
                String url=user.getString("url");
                newsblock git=new newsblock(author,title,desc,image,url,published);
                ar.add(git);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //parseing json

        return ar;
    }
}
