package com.example.chetan.androidnetworking;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

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
