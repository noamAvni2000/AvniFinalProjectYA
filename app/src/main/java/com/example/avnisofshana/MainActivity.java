package com.example.avnisofshana;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
        Tavla t=new Tavla();
        t.setUsername("noam");
        t.setPassword("123");
        tavlaDao.insert(t);
        t=new Tavla();
        t.setUsername("uri");
        t.setPassword("abc");
        tavlaDao.insert(t);
        t=new Tavla();
        t.setUsername("oren");
        t.setPassword("456");
        tavlaDao.insert(t);//here i am inserting the users into the database

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username=etUsername.getText().toString().trim();
                String password=etPassword.getText().toString().trim();//getting the text from the user(trim removes unnecessary spaces)
                Tavla t=tavlaDao.login(username,password);
                if(t!=null)
                {
                    Intent intent = new Intent(MainActivity.this, Activity2.class);
                    intent.putExtra("USERNAME_KEY", username);//saves the username of the user
                    startActivity(intent);
                    finish();
                }//using the function from the dao to check if the user is in the database(it retuns null if not found)
                else {
                    Toast.makeText(MainActivity.this, "UserName or Password is incorrect", Toast.LENGTH_SHORT).show();
                }//a toast to show the user their login attempt failed
            }
        });
    }
}
