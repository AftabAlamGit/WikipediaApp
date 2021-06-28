package com.example.wikipedia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static String query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void submitQuery(View view){
        EditText queryField = (EditText) findViewById(R.id.query_field);
        query = queryField.getText().toString();

        Intent resultIntent = new Intent(MainActivity.this, ResultActivity.class);
        startActivity(resultIntent);
    }

    public static class Query{
        public String newquery=query;
        public String getQuery(){
            return newquery;
        }
    }
}