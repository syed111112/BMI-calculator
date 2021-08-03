package com.example.individualassignmentsyed;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.nfc.Tag;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    //declaration what to use
    EditText weight;
    EditText height;
    Button calcbutton;
    TextView result;
    TextView category;
    TextView healthrisk;
    String cat="";
    String health="";
    private Button abus;
    private SharedPreferences mpreferences;
    private SharedPreferences.Editor meditor;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("BMI Calculator");

        





        // assign each component to xml file
        weight=(EditText) findViewById(R.id.editTextweight);
        height=(EditText) findViewById(R.id.editTextheight);
        calcbutton=(Button) findViewById(R.id.calc_button);
        result = (TextView) findViewById(R.id.textView_calculate);
        category = (TextView) findViewById(R.id.textViewbmicat);
        healthrisk = (TextView) findViewById(R.id.textViewhealthrisk);
        abus=(Button) findViewById(R.id.aboutus);



        mpreferences = PreferenceManager.getDefaultSharedPreferences(this);
        //mpreferences = getSharedPreferences("mydatabase", Context.MODE_PRIVATE);
        meditor = mpreferences.edit();

        //meditor.putString("key","mitch");
       //meditor.commit();

       // String name = mpreferences.getString("some_other_key", "default");
        //Log.d(TAG, "onCreate: name: " + name);

        checkSharedPreferences();




        abus.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View V){
                openaboutus();
            }
        });



        calcbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                //save weight
                String aweight = weight.getText().toString();
                meditor.putString(getString(R.string.uweight), aweight);
                meditor.commit();
                //save height
                String aheight = height.getText().toString();
                meditor.putString(getString(R.string.uheight), aheight);
                meditor.commit();

                if(weight.getText().toString().equals("0") || weight.getText().toString().equals(""))
                {
                    weight.setError("Enter Weight");
                }
                if(height.getText().toString().equals("0") || height.getText().toString().equals(""))
                {
                    height.setError("Enter Height");
                }


                if((!weight.getText().toString().equals("0") && !weight.getText().toString().equals(""))&&(!height.getText().toString().equals("0") && !height.getText().toString().equals("")))
                {


                    double w = Double.parseDouble(weight.getText().toString());
                    double h = Double.parseDouble(height.getText().toString());

                    double sum = (w / h / h) * 10000;

                    if(!weight.getText().toString().equals("0") && !height.getText().toString().equals("0"))
                    {
                        if (sum<=18.4)
                        {
                            cat="Underweight";
                            health="Malnutration risk";
                        }
                        else if (sum<=24.9)
                        {
                            cat="Normal weight";
                            health="Low risk";
                        }
                        else if (sum<=29.9)
                        {
                            cat="Overweight";
                            health="Enhanced risk";
                        }
                        else if (sum<=34.9)
                        {
                            cat="Moderately obese";
                            health="Medium risk";
                        }
                        else if (sum<=39.9)
                        {
                            cat="Severely obese";
                            health="High risk";
                        }
                        else
                        {
                            cat="Very Severely obese";
                            health="Very high risk";
                        }

                        Toast.makeText(MainActivity.this, "Successfull", Toast.LENGTH_SHORT).show();

                        result.setText(String.format("%.1f", sum));
                        category.setText(String.format(cat));
                        healthrisk.setText(String.format(health));
                    }}}
        });



    }
    public void openaboutus(){
        Intent intent = new Intent(this, aboutus.class);
        startActivity(intent);
    }
    private void checkSharedPreferences(){
        String mweight = mpreferences.getString(getString(R.string.uweight), "");
        String mheight = mpreferences.getString(getString(R.string.uheight), "");


        weight.setText(mweight);
        height.setText(mheight);
    }
}