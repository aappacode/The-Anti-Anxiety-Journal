package com.example.theanti_anxietyjournal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * This class shows one large textview for extended journaling
 */
public class ExtendedJournal extends AppCompatActivity {
    /**
     * Upon opening the app, open the xml file and on click write to file and open next activity.
     * @param savedInstanceState saves the bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extended_journal);
        Button finishedButton = findViewById(R.id.finishedBtn); // xml
        finishedButton.setOnClickListener(new View.OnClickListener() { // on click
            @Override
            public void onClick(View view) {
                writeToFileLast(); // write to file upon finished button click
                openFinal(); // open HomePage
            }
        });

    }
    /**
     * writeToFile uses xml referenced (find by id) to retrieve the answer
     * inputted to the EditText. This is the same as MainActivity, just with different
     * questions written to the file and no date.
     */
    private void writeToFileLast() {
        EditText finalText = findViewById(R.id.yourSpaceTxtBox);
        try {
            FileOutputStream fos = openFileOutput("UserEntryFile.txt",MODE_APPEND); // append to
            // end of file
            fos.write("Your Space: ".getBytes()); // write prompt
            fos.write(returnedStrFinal(finalText).getBytes()); // write answer
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * This method takes an EditText, gets the text the user inputs, converts to string, and
     * adds a new line to then be used into writeToFile.
     * @param idName EditText
     * @return String
     */
    private String returnedStrFinal(EditText idName) {
        return idName.getText().toString() + "\n";
    }

    /**
     * openFinal is a function for the button finished to open the final activity. Intent is an abstract
     * holder for something to be performed on, which in this case, is startActivity. It is public
     * as per the instructions on the android studio developer website.
     */
    public void openFinal() {
        Intent intentF = new Intent(this, HomePage.class);
        startActivity(intentF);
    }
}