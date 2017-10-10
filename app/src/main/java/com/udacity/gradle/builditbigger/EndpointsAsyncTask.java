package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.nihar.myapplication.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

/**
 * Created by nihar on 4/19/2017.
 */

public class EndpointsAsyncTask extends AsyncTask<Context, Void, String> {
    private static MyApi myApiService = null;
    private Context context;
    public TaskDelegate delegate = null;





    public interface TaskDelegate {
        void processFinish(String output);
    }
    public EndpointsAsyncTask(TaskDelegate delegate){
        this.delegate = delegate;
        Log.d("endpoint const","delegate="+delegate);
    }



    @Override
    protected String doInBackground(Context... params) {
        if(myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            myApiService = builder.build();
        }


            //context = params[0].first;
            //String name = params[0].second;


            try {
               String value= myApiService.getJokes().execute().getData();
                Log.d("endpo try","return"+value);
                return myApiService.getJokes().execute().getData();
            } catch (IOException e) {
                Log.d("catch","catch");
                return e.getMessage();
            }
        }


    @Override
    protected void onPostExecute(String result) {
        Log.d("on post execute","before finish");
        delegate.processFinish(result);
        //Toast.makeText(context, result, Toast.LENGTH_LONG).show();
    }
}
