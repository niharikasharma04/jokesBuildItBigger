package com.example.android.myandroidlibrary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

public class AndroidActivity extends AppCompatActivity {
    public static String JOKE_KEY = "key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android);

        Log.d("activity android","created");
        Intent intent = getIntent();
        String joke = intent.getStringExtra(AndroidActivity.JOKE_KEY);

        TextView jokeTextView = (TextView) findViewById(R.id.android_joke_textview);
        if (joke != null && joke.length() != 0) {
            jokeTextView.setText(joke);
        }

    }
}
