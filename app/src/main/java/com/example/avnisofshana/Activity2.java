package com.example.avnisofshana;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Random;

public class Activity2 extends AppCompatActivity {
    TextView tvHello, tvLeft, tvRight;
    Button btnMouthOpenRight, btnEquals, btnMouthOpenLeft, btnSeeScore;
    ArrayList<Question> questions;
    int currentIdx  = 0;//will hold which Question were showing right now
    int correctSoFar = 0;//will hold running score
    private ArrayList<Question> populateQuestions(int numberOfQuestionsToCreate) {
        int minValue = 0;
        int maxValue = 99;
        Random rng = new Random(); //creating a random generator so the questions are random
        ArrayList<Question> list = new ArrayList<>(numberOfQuestionsToCreate);
        for (int i = 0; i < numberOfQuestionsToCreate; i++) {
            int left  = rng.nextInt(maxValue - minValue + 1) + minValue;
            int right = rng.nextInt(maxValue - minValue + 1) + minValue;
            list.add(new Question(left, right));
        }
        return list;
    }

    private void makeCurrentQuestion() {
        Question q = questions.get(currentIdx);
        tvLeft.setText(String.valueOf(q.getLeft()));
        tvRight.setText(String.valueOf(q.getRight()));//instead of writing this code in everypart i can just call this function
    }//this function connects the tv with the question

    private void handleAnswer(int choice) {
        Question q = questions.get(currentIdx);
        if (q.isCorrect(choice)) {//checks if the answer is correct if so ups the score by 1
            correctSoFar++;
        }

        currentIdx = (currentIdx + 1) % questions.size();
        makeCurrentQuestion();//moves to the next question
    }//i made this so instead of writing this code in everypart i can just call this function

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        tvHello=findViewById(R.id.tvHello);
        String username = getIntent().getStringExtra("USERNAME_KEY");
        tvHello.setText("Hello " + username + "!");//making the tv show the username of the user

        tvLeft=findViewById(R.id.tvLeft);
        tvRight=findViewById(R.id.tvRight);
        btnMouthOpenRight=findViewById(R.id.btnMouthOpenRight);
        btnEquals=findViewById(R.id.btnEquals);
        btnMouthOpenLeft=findViewById(R.id.btnMouthOpenLeft);
        btnSeeScore=findViewById(R.id.btnSeeScore);//connects the buttons and tv with the xml

        questions = populateQuestions(100);//creates 100 questions so the user can answer up to 100 question if he wants(the amount can be changed)
        makeCurrentQuestion();//makes the question show on the screen

        btnMouthOpenRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleAnswer(-1);
            }
        });//user chose <

        btnEquals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleAnswer(0);
            }
        });//user chose equals

        btnMouthOpenLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleAnswer(1);
            }
        });//user chose >

        btnSeeScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(Activity2.this)
                        .setTitle("Save & reset?")
                        .setMessage("This will save your score and show your stats Continue?")
                        .setPositiveButton("OK", (dialog, which) -> {
                            Intent i = new Intent(Activity2.this, Activity3.class);
                            i.putExtra("score",  correctSoFar);
                            i.putExtra("total",  questions.size());
                            startActivity(i);//saves the score and shows the users stats

                            correctSoFar = 0;
                            currentIdx   = 0;
                            makeCurrentQuestion();//resets the game after user went to see his stats
                        })
                        .setNegativeButton("Cancel", null)// do nothing, dialog closes
                        .show();
            }
        });
    }


}