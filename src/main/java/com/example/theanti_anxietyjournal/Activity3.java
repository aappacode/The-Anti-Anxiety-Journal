package com.example.theanti_anxietyjournal;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Activity3 displays the therapist note and question 5 (which must be filled out to proceed), as
 * CBT theory empirically backs up, supporting the mission of the app.
 */
public class Activity3 extends AppCompatActivity {
    TextView therapistNote; // create textview

    /**
     * Upon opening, the therapist note is retrieved and the noteGenerator() sets
     * the text for the textview. Then, the buttons give the option of either continuing
     * journaling or going to the HomePage. If the question 5 box is empty, an error will
     * appear on screen with the message.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);
        therapistNote = findViewById(R.id.therapistNote); // xml reference
        therapistNote.setText(noteGenerator()); // set the text from a string
        EditText question5Answer = findViewById(R.id.answer5Txt); // reference to answer
        Button noButton = findViewById(R.id.noFBtn); // uml reference
        noButton.setOnClickListener(new View.OnClickListener() { // on click
            @Override
            public void onClick(View view) {
                if(question5Answer.length() == 0) { // if the box is empty
                    question5Answer.setError("Try reframing your thoughts."); // show error
                } else { // if not empty
                    writeToFile3(question5Answer); // write to file
                    goHome(); // go to HomePage
                }
            }
        });

        Button yesBtn = findViewById(R.id.yesBtn); // continue button
        yesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(question5Answer.length() == 0) { // if box is empty
                    question5Answer.setError("Try reframing your thoughts."); // show error
                } else {
                    writeToFile3(question5Answer); // write to file if it has contents
                    openYourSpace(); // open extended journal
                }
            }
        });
    }

    /**
     * goHome is a function for the button view entries/done to open the next activity. Intent is an abstract
     * holder for something to be performed on, which in this case, is startActivity. It is public
     * as per the instructions on the android studio developer website.
     */
    public void goHome() {
        Intent intentgH = new Intent(Activity3.this, HomePage.class);
        startActivity(intentgH);
    }

    /**
     *  writeToFile3 uses xml referenced (find by id) to retrieve the answer
     *  inputted to the EditText. This is the same as MainActivity, just with different
     *  questions written to the file and no date.
     * @param question5
     */
    private void writeToFile3(EditText question5) {
        try {
            FileOutputStream fos = openFileOutput("UserEntryFile.txt",MODE_APPEND); // byte writer,
            // appends to end of file internally
            fos.write("How you thought about it differently: ".getBytes());
            fos.write(returnedStr3(question5).getBytes());
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
    private String returnedStr3(EditText idName) {
        return idName.getText().toString() + "\n";
    }

    /**
     * This function holds the list of strings of therapist's notes and returns a random one
     * selected from the string using the Random class
     * @return single String from List allNotes
     */
    private String noteGenerator() {
        // Use list here to easily find the element indexed at a certain location.
        List<String> allNotes =
                Arrays.asList("One helpful way to look at a situation differently is to think about it from the perspective of a friend.",
                        "In developing your mindset, practice compassion. What's a more empathetic and forgiving lens through which to view yourself and others?",
                        "Mindfulness is not the state of thinking about nothing. Rather, it is practicing focus and paying attention to the present moment.",
                        "When you don't have the journal handy, use the CBT shortcut: Catch it, Check it, Change it. Observe your thoughts, challenge them, then reframe them.",
                        "When you feel stuck, remember you are resilient. Reflect on a situation when you had to adapt. What helped you succeed?",
                        "Try naming frequent thought topics (e.g. money worry) to make it easier to notice and redirect your mind.",
                        "Remind yourself to slow down. Whether you're eating, talking, emailing, or texting, try slowing your velocity on everything.",
                        "Our minds work at lightning speed. Take a deep breath and try slowing down your thoughts so you can clearly observe what you are thinking."
                );
        Random random = new Random(); // new random
        int randInt = random.nextInt(allNotes.size()); // random int to get index from list
        return "NOTE FROM A THERAPIST:\n" + "\n" + allNotes.get(randInt); // return string and get the element at index random
    }

    /**
     * openYourSpace is a function for the button continue to open the next activity. Intent is an abstract
     * holder for something to be performed on, which in this case, is startActivity. It is public
     * as per the instructions on the android studio developer website.
     */
    public void openYourSpace() {
        Intent intentYS = new Intent(this, ExtendedJournal.class);
        startActivity(intentYS);
    }
}