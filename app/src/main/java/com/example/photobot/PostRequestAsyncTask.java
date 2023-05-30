package com.example.photobot;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class PostRequestAsyncTask extends AsyncTask<String, Void, String>{
    private Activity context;
    private static final String API_URL = "https://photobotapp.wn.r.appspot.com/parse";
    public PostRequestAsyncTask(Activity context){
        this.context = context;
    }
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
            Log.d("hi",img.substring(0,50));
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
            try {
                JSONObject json = new JSONObject(response);
                Intent intent = new Intent(context, output_screen.class);
                intent.putExtra("question",json.getString("question"));
                intent.putExtra("answer",json.getString("response"));
                context.startActivity(intent);
            } catch (JSONException e) {
                Log.d("hi",e.toString());
                throw new RuntimeException(e);
            }
        } else {
        }
    }
}
