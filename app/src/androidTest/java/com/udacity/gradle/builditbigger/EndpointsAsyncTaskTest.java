package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.test.RenamingDelegatingContext;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static junit.framework.Assert.assertNotNull;

/**
 * Created by nihar on 4/20/2017.
 */

public class EndpointsAsyncTaskTest implements EndpointsAsyncTask.TaskDelegate{

    Context mockContext;

    @Before
    public void setUp() {
        mockContext = new RenamingDelegatingContext(InstrumentationRegistry.getTargetContext(), "test_");
    }
    @Test
    public void doInBackground() throws Exception {
        String response = "";
        try{

            EndpointsAsyncTask task= new EndpointsAsyncTask(this);
            task.execute();
            response= task.get(30, TimeUnit.SECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
        }

        assertNotNull("not null",response);
    }

    @Test
    public void onPostExecute() throws Exception {

    }

    @Override
    public void processFinish(String output) {

    }
}
