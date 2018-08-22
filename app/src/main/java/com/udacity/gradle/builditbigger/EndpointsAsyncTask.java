package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.util.Pair;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.jokeApi.JokeApi;
import com.udacity.gradle.builditbigger.backend.jokeApi.model.JokeBean;

import java.io.IOException;

import co.asterv.jokeandroidlibrary.DisplayJokeActivity;


public class EndpointsAsyncTask extends AsyncTask<Pair<Context, String>, Void, String> {
    private static JokeApi myApiService = null;
    private Context context;

    public EndpointsAsyncTask(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(Pair<Context, String>... pairs) {
        if (myApiService == null) {
            JokeApi.Builder builder = new JokeApi.Builder (AndroidHttp.newCompatibleTransport (),
                    new AndroidJsonFactory (), null)
                    .setRootUrl ("http://10.0.2.2:8080/_ah/api");

            myApiService = builder.build();
        }

        try {
            return myApiService.putJoke (new JokeBean ()).execute ().getJoke ();
        } catch (IOException e) {
            return e.getMessage ();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        Intent intent = new Intent(context, DisplayJokeActivity.class);
        intent.putExtra(DisplayJokeActivity.JOKE_INTENT, result);
        intent.addFlags (Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity (intent);
    }
}
