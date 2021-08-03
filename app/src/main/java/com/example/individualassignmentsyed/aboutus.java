package com.example.individualassignmentsyed;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class aboutus extends AppCompatActivity {
    TextView linktextView;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutus);
        getSupportActionBar().setTitle("BMI Calculator");


        linktextView = findViewById(R.id.hyperlink);
        linktextView.setMovementMethod(LinkMovementMethod.getInstance());



        button=(Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View V){
                openmainpage();
            }
        });

    }

    public void openmainpage(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}