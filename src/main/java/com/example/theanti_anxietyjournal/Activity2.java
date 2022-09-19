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
 * Activity2 is the second to open after the first set of questions. It has two questions, and one
 * button to go to the next prompt.
 */
public class Activity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        Button nextBtn2 = findViewById(R.id.nextBtn2); // reference to xml
        nextBtn2.setOnClickListener(new View.OnClickListener() { // on click
            @Override
            public void onClick(View view) {
                writeToFile2(); // write user input to the file
                openActivity3(); // open next activity
            }
        });
    }
    /**
     * writeToFile2 uses xml referenced (find by id) to retrieve the answer
     * inputted to the EditText. This is the same as MainActivity, just with different
     * questions written to the file and no date.
     */
    private void writeToFile2() {
        EditText question3Answer = findViewById(R.id.answer3Txt);
        EditText question4Answer = findViewById(R.id.answer4Txt);
        try {
            FileOutputStream fos = openFileOutput("UserEntryFile.txt",MODE_APPEND); // byte writer,
            // appends to end of file internally
            fos.write("Emotions you felt: ".getBytes());
            fos.write(returnedStr2(question3Answer).getBytes());
            fos.write("Your thought patterns: ".getBytes());
            fos.write(returnedStr2(question4Answer).getBytes());
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
    private String returnedStr2(EditText idName) {
        return idName.getText().toString() + "\n";
    }

    /**
     * openActivity3 is a function for the button next to open the next activity. Intent is an abstract
     * holder for something to be performed on, which in this case, is startActivity. It is public
     * as per the instructions on the android studio developer website.
     */
    public void openActivity3() {
        Intent intentActivity3 = new Intent(Activity2.this, Activity3.class);
        startActivity(intentActivity3);
    }
}