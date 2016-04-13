package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.os.AsyncTask;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

import at.inclumedia.joker.backend.myApi.MyApi;


/**
 * Created by Martin Melcher on 03/03/16.
 */
public class EndpointAsyncTask extends AsyncTask<Context, Void, String> {

    private final static Boolean RUN_LOCALLY = true;
    private final static String LOC_SERVER_URL = "http://10.0.2.2:8080/_ah/api/";
    private final static String REM_SERVER_URL = "https://nano-joker.appspot.com/_ah/api/";

    private static MyApi myApiService = null;
    private Context context;

    @Override
    protected String doInBackground(Context ... params) {
        if(myApiService == null) {  // Only do this once
            MyApi.Builder builder;
            if (RUN_LOCALLY) {
                builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                        new AndroidJsonFactory(), null)
                        .setRootUrl(LOC_SERVER_URL)
                        .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                            @Override
                            public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                                abstractGoogleClientRequest.setDisableGZipContent(true);
                            }
                        });
            }
            else {
                builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                        .setRootUrl(REM_SERVER_URL);
            }

            myApiService = builder.build();
        }

        context = params[0];

        try {
            return myApiService.tellJoke().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }
}
