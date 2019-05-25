package com.example.vsaivignesh.deaththinghackermode;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    int i;
    int c;
    int s,a;
    Random r = new Random();
    int randomNumber;
    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putInt("Iterates",i);
    }
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences sp=getApplicationContext().getSharedPreferences("Mypref",0);
        super.onCreate(savedInstanceState);
        s=sp.getInt("Success",0);if (savedInstanceState!=null){
        i=savedInstanceState.getInt("Iterates",0);}
        a=sp.getInt("Attempts",0);
        randomNumber=sp.getInt("Random",r.nextInt(100));
        setContentView(R.layout.activity_main);
        Button button=findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConstraintLayout Layout= findViewById(R.id.constraintLayout);
                TextView Success=findViewById(R.id.textView2);
                TextView Attempts=findViewById(R.id.textView3);
                TextView newtext = findViewById(R.id.textView);
                EditText editText = findViewById(R.id.editText);
                String message = editText.getText().toString();
                int guess = Integer.valueOf(message);
                if (i>10){ newtext.setText(R.string.Over);i=-1;r=new Random();randomNumber=r.nextInt(100);}
                else if (randomNumber > guess) {
                    newtext.setText(R.string.lower);
                    a++;
                }   else if (randomNumber < guess) {
                    newtext.setText(R.string.Higher);a++;
                }   else {
                    newtext.setText(R.string.Correct);i=-1;r=new Random();randomNumber=r.nextInt(100);a++;s++;
                }
                c=2*java.lang.Math.abs(randomNumber-guess);
                if(i!=-1){Layout.setBackgroundColor(Color.rgb(c,200-c,0));}
                else{Layout.setBackgroundColor(Color.rgb(200,200,200));}
                i = i + 1;
                String success="Successful Attempts: "+s;
                String total="Failed Attempts: "+a;
                Success.setText(success);
                Attempts.setText(total);
                SharedPreferences sp=getApplicationContext().getSharedPreferences("Mypref",0);
                Success.setText(success);
                Attempts.setText(total);
                SharedPreferences.Editor editor=sp.edit();
                editor.putInt("Random",randomNumber);
                editor.putInt("Success",s);
                editor.putInt("Attempts",a);
                editor.putInt("Iterates",i);
                editor.apply();
            }});}
}
