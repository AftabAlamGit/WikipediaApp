package com.example.wikipedia;

import android.app.usage.UsageEvents;
import android.os.AsyncTask;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.wikipedia.databinding.ActivityResultBinding;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ResultActivity extends AppCompatActivity {

    /** Tag for the log messages */
    public static final String LOG_TAG = MainActivity.class.getSimpleName();

    public String newWord = new MainActivity.Query().getQuery();
    /** URL to query the wikipedia dataset for information */
    private static String WIKI_REQUEST_URL =
            "https://en.wikipedia.org/w/api.php?format=json&action=query&prop=extracts&exintro=&explaintext=&titles=";

    private String WIKI_URL = WIKI_REQUEST_URL + newWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView textView = findViewById(R.id.response);

        final ExecutorService executorService = Executors.newSingleThreadExecutor();

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                // doInBackground
                String result = Utils.fetchData(WIKI_URL);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // postExecute
                        textView.setText(result);
                    }
                });
            }
        });

    }
}