package com.example.myapplication;
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
public class MainActivity extends AppCompatActivity{
    int i,c,s,a;
    int nt=0;
    private TextView Success,Attempts,newtext;
    private EditText editText;
    Random r = new Random();
    int randomNumber=r.nextInt(100);
    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putInt("Iterates",i);
        outState.putInt("Colour",c);
        outState.putInt("Random",randomNumber);
        outState.putInt("nt",nt);
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences sp=getApplicationContext().getSharedPreferences("Mypref",0);
        s=sp.getInt("S",s);
        a=sp.getInt("A",a);
        String success="Successful Attempts: "+s;
        String total="Failed attempts: "+a;
        setContentView(R.layout.activity_main);
        final ConstraintLayout Layout=findViewById(R.id.constraintLayout);
        if (savedInstanceState!=null){
            c=savedInstanceState.getInt("Colour",0);
            Success=findViewById(R.id.textView2);
            Attempts=findViewById(R.id.textView3);
            newtext=findViewById(R.id.textView);
            Success.setText(success);
            Attempts.setText(total);
            i=savedInstanceState.getInt("Iterates",0);
            nt=savedInstanceState.getInt("nt",nt);
            randomNumber=savedInstanceState.getInt("Random",r.nextInt(100));
            if(nt==1){newtext.setText(R.string.lower);}
            else if(nt==2){newtext.setText(R.string.Higher);}else if(nt==3){newtext.setText(R.string.Correct);}
            if(i!=0){Layout.setBackgroundColor(Color.rgb(c,200-c,0));}

        }
        Button button=findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText=findViewById(R.id.editText);
                String message = editText.getText().toString();
                int guess = Integer.valueOf(message);
                newtext=findViewById(R.id.textView);
                String Over="Game over. Answer: "+randomNumber;
                if (i>4 & randomNumber!=guess){ newtext.setText(Over);i=-1;r=new Random();
                randomNumber=r.nextInt(100);
                a++;
                nt=0;}
                else if (randomNumber > guess) {
                    newtext.setText(R.string.lower);nt=1;
                }   else if (randomNumber < guess) {
                    newtext.setText(R.string.Higher);nt=2;
                }   else {
                    newtext.setText(R.string.Correct);i=-1;r=new Random();
                    randomNumber=r.nextInt(100);s++;nt=3;
                }
                c=2*java.lang.Math.abs(randomNumber-guess);
                if(i!=-1){Layout.setBackgroundColor(Color.rgb(c,200-c,0));}
                else{Layout.setBackgroundColor(Color.rgb(200,200,200));}
                i = i + 1;
                SharedPreferences sp=getApplicationContext().getSharedPreferences("Mypref",0);
                String success="Successful Attempts: "+s;
                String total="Failed attempts: "+a;
                Success=findViewById(R.id.textView2);
                Attempts=findViewById(R.id.textView3);
                Success.setText(success);
                Attempts.setText(total);
                SharedPreferences.Editor editor=sp.edit();
                editor.putInt("S",s);
                editor.putInt("A",a);
                editor.apply();
            }});}}
