package com.example.theanti_anxietyjournal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
// below are grayed out if you want to clear the file
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * MainActivity is the first to open when the app runs. It has two questions, and two buttons
 * to go to the next journal page or to the previous journals.
 */
public class MainActivity extends AppCompatActivity {
    /**
     * onCreate includes the button onClickListeners and the function calls
     * for each button.
     *
     * @param savedInstanceState keeps Bundle to save Instance
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /* The below code is commented if you want to clear everything from the file. Uncomment and
        re-run the program to delete the sample inputs.
        */
//        File dir = getFilesDir();
//        File file = new File(dir, "UserEntryFile.txt");
//        file.delete();
        Button pastEntries = findViewById(R.id.viewEntries); // reference to .xml
        pastEntries.setOnClickListener(new View.OnClickListener() { // on click
            @Override
            public void onClick(View view) {
                openHomePage();
            }
        });
        Button nextButton = findViewById(R.id.nextBtn);
        nextButton.setOnClickListener(new View.OnClickListener() { // on click
            @Override
            public void onClick(View view) {
                writeToFile(); // write user input to file
                openActivity2(); // open next activity
            }
        });
    }

    /**
     * openHomePage is a function for the button PastEntries to open the HomePage. Intent is an abstract
     * holder for something to be performed on, which in this case, is startActivity. It is public
     * as per the instructions on the android studio developer website.
     */
    public void openHomePage() {
        Intent intentHP = new Intent(MainActivity.this, HomePage.class);
        startActivity(intentHP);
    }

    /**
     * openActivity2 is a function for the button next to open the next activity. Intent is an abstract
     * holder for something to be performed on, which in this case, is startActivity. It is public
     * as per the instructions on the android studio developer website.
     */
    public void openActivity2() {
        Intent intentA2 = new Intent(MainActivity.this, Activity2.class);
        startActivity(intentA2);
    }

    /**
     * writeToFile uses xml referenced (find by id) to retrieve the answer
     * inputted to the EditText. Used SimpleDateFormat to get the date, and Locale.US
     * to order it upon Android Studio's suggestion.
     */
    private void writeToFile() {
        EditText question1Answer = findViewById(R.id.answer1Txt); // reference to XML
        EditText question2Answer = findViewById(R.id.answer2Txt);
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yy, hh:mm a", Locale.US);
        // Above line creates a dateFormatter and Locale.US to signify the date
        String date = "\n" + dateFormat.format(new Date()) + ":\n"; // today's date is retrieved with
        // new lines before and after
        try {
            FileOutputStream fos = openFileOutput("UserEntryFile.txt", MODE_APPEND);
            /*
            The above line creates a fileOutputStream to write bytes to, and appends the text to
            the existing file.
             */
            fos.write(date.getBytes()); // write the date
            fos.write("What happened: ".getBytes()); // write prompt for question 1
            fos.write(returnedStr(question1Answer).getBytes()); // write answer to question 1
            fos.write("What you thought: ".getBytes()); // write prompt for question 2
            fos.write(returnedStr(question2Answer).getBytes()); // write answer to question 2
            fos.close(); // close the fos
        } catch (IOException e) { // exception produced by FOS
            e.printStackTrace();
        }
    }

    /**
     * This method takes an EditText, gets the text the user inputs, converts to string, and
     * adds a new line to then be used into writeToFile.
     * @param idName EditText
     * @return String
     */
    private String returnedStr(EditText idName) {
        return idName.getText().toString() + "\n"; // this just returns the editText as a string to
        // be converted into a byte in writeToFile.
    }

}