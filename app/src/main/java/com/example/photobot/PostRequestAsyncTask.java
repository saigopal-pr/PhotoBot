package com.example.photobot;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class PostRequestAsyncTask extends AsyncTask<String, Void, String> {
    private static final String API_URL = "https://photobotapp.wn.r.appspot.com/parse";

    @Override
    protected String doInBackground(String... params) {
        String img = params[0];

        try {
            // Create URL object
            URL url = new URL(API_URL);

            // Create connection object
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set the required HTTP Method
            connection.setRequestMethod("POST");

            // Enable input and output streams
            connection.setDoOutput(true);
            connection.setDoInput(true);

            // Set the request headers (optional)
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            // Create the image parameter
            String postData = "image=" + URLEncoder.encode(img, "UTF-8");

            // Write the POST data to the request body
            DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
            outputStream.writeBytes(postData);
            outputStream.flush();
            outputStream.close();

            // Get the response status code
            int responseCode = connection.getResponseCode();

            // Read the response from the input stream
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // Close the connection
            connection.disconnect();

            // Return the response
            return response.toString();
        } catch (Exception e) {
            Log.d("hi",e.toString());
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(String response) {
        Log.d("hi",response);
        if (response != null) {
            // Do something with the response
            // For example, update UI elements or pass the response to another method
        } else {
            // Handle the case where the response is null or there was an error
        }
    }
}
