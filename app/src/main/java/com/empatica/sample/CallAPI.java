package com.empatica.sample;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class CallAPI extends AsyncTask<String, String, String> {

    public MainActivity activity;

    public CallAPI(MainActivity a)
    {
        this.activity = a;
        //set context variables if required
    }

    @Override
    protected void onPreExecute() {
        //Log.i("EDA-CALL", "Sending data...");
        activity.updateLabel(activity.statusSendingLabel, "Sending data...");
        //activity.updateLabel(activity.statusSendingLabel, "");
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {
        String urlString = params[0]; // URL to call
        String data = params[1]; //data to post
        OutputStream out = null;

        try {
            URL url = new URL(urlString);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");
            out = new BufferedOutputStream(urlConnection.getOutputStream());

            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out, "UTF-8"));
            writer.write(data);
            writer.flush();
            writer.close();
            out.close();

            urlConnection.connect();

            int responseCode = urlConnection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                InputStream in = urlConnection.getInputStream();
                BufferedReader rd = new BufferedReader(new InputStreamReader(in));
                String line;
                StringBuilder response = new StringBuilder();
                while ((line = rd.readLine()) != null) {
                    response.append(line);
                }
                rd.close();
                //Log.i("EDA-CALL", response.toString());
                activity.updateLabel(activity.statusSendingLabel, response.toString());
            } else {
                //Log.e("EDA-CALL", "Connection failed 2");
                activity.updateLabel(activity.statusSendingLabel, "Response error");
                //activity.updateLabel(activity.statusReceivingLabel, "");
            }

            urlConnection.disconnect(); // close the connection after usage
        } catch (Exception e) {
            //Log.e("EDA-CALL", e.getMessage());
            activity.updateLabel(activity.statusSendingLabel, "Connection failed \nUrl: " + urlString + "\nData: " + data);
            //activity.updateLabel(activity.statusReceivingLabel, "");
        }

        //Log.i("EDA-CALL", urlString);
        //Log.i("EDA-CALL", data);
        //activity.updateLabel(activity.statusSendingLabel, data);
        return urlString;
    }
}
