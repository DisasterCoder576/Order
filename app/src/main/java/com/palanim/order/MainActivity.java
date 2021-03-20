package com.palanim.order;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.location.Address;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private int progressStatus = 0;

    private Handler handler = new Handler();

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView cook2 = findViewById(R.id.cook2);
        ImageView cook3 = findViewById(R.id.cook3);
        ImageView cook4 = findViewById(R.id.cook4);
        ImageView cook5 = findViewById(R.id.cook5);
        ImageView dot1 = findViewById(R.id.dot1);
        ImageView dot2 = findViewById(R.id.dot2);
        ImageView dot3 = findViewById(R.id.dot3);
        ImageView dot4 = findViewById(R.id.dot4);

        cook2.setVisibility(View.INVISIBLE);
        cook3.setVisibility(View.INVISIBLE);
        cook4.setVisibility(View.INVISIBLE);
        cook5.setVisibility(View.INVISIBLE);

        dot1.setVisibility(View.VISIBLE);
        dot2.setVisibility(View.VISIBLE);
        dot3.setVisibility(View.VISIBLE);
        dot4.setVisibility(View.VISIBLE);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setProgressTintList(ColorStateList.valueOf(Color.RED));
        // Start long running operation in a background thread
        new Thread(new Runnable() {
            public void run() {
                while (progressStatus < 100) {
                    progressStatus += 1;


                    Log.i("STATUS", String.valueOf(progressStatus));
                    // Update the progress bar and display the
                    //current value in the text view
                    handler.post(new Runnable() {
                        public void run() {
                            progressBar.setProgress(progressStatus);
                            if(progressStatus==25){
                                Log.i("IN","IN");
                                dot1.setVisibility(View.INVISIBLE);
                                Log.i("IN","OUT");
                                cook2.setVisibility(View.VISIBLE);
                                Log.i("IN","OUT1");
                            }
                            else if(progressStatus==50){
                                dot2.setVisibility(View.INVISIBLE);
                                cook3.setVisibility(View.VISIBLE);
                            }
                            else if(progressStatus==75){
                                dot3.setVisibility(View.INVISIBLE);
                                cook4.setVisibility(View.VISIBLE);
                            }
                            else if(progressStatus==100){
                                dot4.setVisibility(View.INVISIBLE);
                                cook5.setVisibility(View.VISIBLE);
                            }
                        }
                    });
                    try {
                        // Sleep for 200 milliseconds.
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

    }
    public void openMaps(View view){
        TextView Address = findViewById(R.id.addr);
        String yourAddress = Address.getText().toString();
        String map = "http://maps.google.co.in/maps?q=" + yourAddress;
       Intent intent = new Intent(android.content.Intent.ACTION_VIEW,  Uri.parse(map));
       startActivity(intent);
    }
}