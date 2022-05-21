package com.example.fipro;

import androidx.appcompat.app.AppCompatActivity;

import android.net.http.HttpResponseCache;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.JsonReader;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {


                // All your networking logic
                // should be here
                try {
                    //Cache 100_000 byte
                    HttpResponseCache myCache = HttpResponseCache.install(
                            getCacheDir(), 100000L);
                    // check cache
                    if (myCache.getHitCount() > 0) {
                        // The cache is working
                    }
                    // Create URL
                    URL githubEndpoint = new URL("https://api.github.com/");
                    // Create connection
                    HttpsURLConnection myConnection =
                            (HttpsURLConnection) githubEndpoint.openConnection();

                    myConnection.setRequestProperty("User-Agent",
                            "my-rest-app-v0.1"); //unikalny zagolovok.
                    myConnection.setRequestProperty("Accept",
                            "application/vnd.github.v3+json");
                    myConnection.setRequestProperty("Contact-Me",
                            "hathibelagal@example.com");

                    if (myConnection.getResponseCode() == 200) { //validnyy otvet
                        InputStream responseBody = myConnection.getInputStream();

                        InputStreamReader responseBodyReader =
                                new InputStreamReader(responseBody, "UTF-8");
                        JsonReader jsonReader = new JsonReader(responseBodyReader); //razbor JSONa

                        jsonReader.beginObject(); // Start processing the JSON object
                        while (jsonReader.hasNext()) { // Loop through all keys
                            String key = jsonReader.nextName(); // Fetch the next key
                            if (key.equals("organization_url")) { // Check if desired key
                                // Fetch the value as a String
                                String value = jsonReader.nextString();

                                // Do something with the value
                                // ...

                                break; // Break out of the loop
                            } else {
                                jsonReader.skipValue(); // Skip values of other keys
                            }
                        }
                        jsonReader.close();

                    } else {
                        // Error handling code goes here
                        Toast.makeText(MainActivity.this, "CONNECTION LOST", Toast.LENGTH_SHORT).show();
                    }
                    myConnection.disconnect();

                    //POST
                    URL httpbinEndpoint = new URL("https://httpbin.org/post");
                    HttpsURLConnection myConnection1
                            = (HttpsURLConnection) httpbinEndpoint.openConnection();

                    myConnection1.setRequestMethod("POST");

                    // Create the data
                    String myData = "message=Hello";

                    // Enable writing
                    myConnection1.setDoOutput(true);

                    // Write the data
                    myConnection1.getOutputStream().write(myData.getBytes());


                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        });

    }
}