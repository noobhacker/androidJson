package com.example.song.myapplication;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Song on 4/16/2017.
 */

public class HttpGetAsyncTask extends AsyncTask<String, Void, JSONObject> {

    public JSONResponse delegate = null;
    public HttpGetAsyncTask(JSONResponse delegate){
        this.delegate = delegate;
    }

    protected JSONObject doInBackground(String... inputUrl) {
        try {
            URL url = new URL(inputUrl[0]);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setInstanceFollowRedirects(true);
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                }
                bufferedReader.close();
                return new JSONObject(stringBuilder.toString());
            }
            finally{
                urlConnection.disconnect();
            }
        }
        catch(Exception e) {
            Log.e("ERROR", e.getMessage(), e);
            return null;
        }
    }

    protected void onPostExecute(JSONObject response) {
       delegate.onResponse(response);
    }

}
