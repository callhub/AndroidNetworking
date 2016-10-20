package com.example.chetan.androidnetworking;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Chetan on 12/09/16.
 */
public class MyAsync extends AsyncTask<String, Void, Integer> {

        private Context mContext;

        public MyAsync(Context context) {
            mContext = context.getApplicationContext();
        }

        @Override
        protected void onPreExecute() {
            Toast.makeText(mContext, "Going for the network call..", Toast.LENGTH_LONG).show();
        }

        @Override
        protected Integer doInBackground(String... params) {
            HttpURLConnection connection;
            try {
                connection = (HttpURLConnection) new URL(params[0])
                        .openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setRequestProperty("Accept", "application/json");
                connection.setRequestProperty("Authorization", "Token fab11c9b6bd4215a989c5bf57eb678");
                JSONObject jsonParam = new JSONObject();
                try {
                    jsonParam.put("campaign_id", "4193");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                OutputStreamWriter outputStream = new OutputStreamWriter(connection.getOutputStream());
                outputStream.write(jsonParam.toString());
                outputStream.flush();
                outputStream.close();

                return connection.getResponseCode();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return -1;
        }

        @Override
        protected void onPostExecute(Integer integer) {

            Toast.makeText(mContext, "Response code: " + integer,
                    Toast.LENGTH_LONG).show();

        }
}
