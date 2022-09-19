package com.example.theanti_anxietyjournal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class HomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        Button button = findViewById(R.id.buttonfinal);
        Button newEntry = findViewById(R.id.newEntryBtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                readFile();
            }
        });
        newEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showMainActivity();
            }
        });
    }
    public void showMainActivity() {
        Intent intentMain = new Intent(HomePage.this,MainActivity.class);
        startActivity(intentMain);
    }
    private void readFile() {
        int totalJournalEntries = 0;
        TextView displaytxt = findViewById(R.id.textViewFinal);
        displaytxt.setMovementMethod(new ScrollingMovementMethod()); // allows textView to be scrollable
        try {
            FileInputStream fileInputStream = openFileInput("UserEntryFile.txt"); // getting bytes from a file (since I wrote the file in bytes)
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream); // bridges from bytes to characters
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader); // reads characters efficiently
            bufferedReader.readLine(); // read the first line which will be empty
            String line; // the first line is null
            StringBuilder sb = new StringBuilder(); // StringBuilder allows growing strings for new entries
            //String lines;
            while ((line = bufferedReader.readLine())!=null) {
                // skips the first empty line by reading from line after that
                sb.append(line).append("\n"); // append the line and new empty line to stringBuilder
                if(line.startsWith("0") || line.startsWith("1")) // for dates (how I separate entries)
                    totalJournalEntries+=1; // add 1 to total entries
            }
            displaytxt.setText(sb.toString()); // Display read text
            journalCount(totalJournalEntries); // call function to display journal entries number
            fileInputStream.close(); // closing resource
            inputStreamReader.close(); // closing resource
            bufferedReader.close(); // closing resource
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void journalCount(int journalEntriesNum) {
        TextView journalCount = findViewById(R.id.totalEntriesTxt);
        String outputCount = "\nTotal Journal Entries: " + journalEntriesNum;
        journalCount.setText(outputCount);
    }
}