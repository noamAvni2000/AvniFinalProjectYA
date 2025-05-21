package com.example.avnisofshana;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class Activity3 extends AppCompatActivity {

    static ArrayList<String> scoreHistory = new ArrayList<>();//the static keeps the score history from being lost when switching screens
    Button btnBackToQuestions;
    ArrayList<String> arrList;
    ArrayAdapter<String> arrAdapt;
    ListView lv;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_3);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Intent intent = getIntent();
        username = intent.getStringExtra("USERNAME_KEY");
        btnBackToQuestions=findViewById(R.id.btnBackToQuestions);
        lv=findViewById(R.id.lv);
        arrAdapt = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, scoreHistory);
        lv.setAdapter(arrAdapt);//setting the adapter to the list view

        int score  = intent.getIntExtra("score",  0);
        int total  = intent.getIntExtra("total",  1);
        double percent = (score / (double) total) * 100;
        int roundedPercent = (int) Math.round(percent);
        if (scoreHistory.size() >= 10) {
            scoreHistory.remove(0);//keep only last 10 scores
        }
        scoreHistory.add(username + " - " + roundedPercent + "% - great score!");
        arrAdapt.notifyDataSetChanged();

        btnBackToQuestions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Activity3.this, Activity2.class);
                i.putExtra("USERNAME_KEY", getIntent().getStringExtra("USERNAME_KEY"));//the thing that makes sure the username doesnt get lost when switching screens
                startActivity(i);//goes back to the questions screen
            }
        });
    }
}