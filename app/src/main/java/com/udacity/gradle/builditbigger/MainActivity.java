package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.MyJokes;
import com.example.android.myandroidlibrary.AndroidActivity;


public class MainActivity extends AppCompatActivity implements EndpointsAsyncTask.TaskDelegate {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void tellJoke(View view) {
        //MyJokes mJokes = new MyJokes();
        //Toast.makeText(this,mJokes.getAJoke(), Toast.LENGTH_SHORT).show();
       // EndpointsAsyncTask jokeDownloader = new EndpointsAsyncTask();
               //String joke= jokeDownloader.execute();
        new EndpointsAsyncTask(this).execute();
    }
    public void launchJokeActivity(View view){
        Intent intent = new Intent(this, AndroidActivity.class);
        MyJokes myJavaJoke = new MyJokes();

        intent.putExtra(AndroidActivity.JOKE_KEY, myJavaJoke.getAJoke());
        startActivity(intent);
    }

    @Override
    public void processFinish(String output) {
        Log.d("in process finish","output="+output);
        Intent intent = new Intent(this, AndroidActivity.class);
        intent.putExtra(AndroidActivity.JOKE_KEY, output);
        startActivity(intent);
    }
}
