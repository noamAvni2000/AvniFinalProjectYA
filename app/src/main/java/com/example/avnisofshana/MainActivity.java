package com.example.avnisofshana;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText etUsername, etPassword;//here i am declaring the variables
    Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnLogin=findViewById(R.id.btnLogin);
        etUsername=findViewById(R.id.etUsername);
        etPassword=findViewById(R.id.etPassword);//here i am connecting the variables with the xml

        UsersDatabase db= UsersDatabase.getInstance(this);
        TavlaDao tavlaDao=db.tavlaDao();
        Tavla t=new Tavla();//here i am creating a tavla object

        //note to self on how to solve!!!! i think i need to do a for loop that checks if when id=i the username in database equals to username in
        //edit text&& in the same id the password in database equals to password in edit text
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });
    }
}
