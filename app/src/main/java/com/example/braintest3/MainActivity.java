package com.example.braintest3;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ArrayList<Integer> answers =new ArrayList<Integer>();
    int locationofcorrect;
    int numberofquestion;

    Button startbutton;
    TextView timerr;
    TextView resulttestview;
    TextView pointstestview;
    TextView mathtext;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button playagain;
    CountDownTimer countDownTimer;
    RelativeLayout relativeLayout;



    public void go_click(View view){                    // go button clicked
        startbutton.setVisibility(View.INVISIBLE);          // Go button click to invisible
        relativeLayout.setVisibility(View.VISIBLE);       // after vanishing go then display all game

        playagain_click(findViewById(R.id.playagain_id)); //play again button cllickfunction, method with -view id call
    }

    public void playagain_click(View view){     // play again button clicked
            score=0;
            numberofquestion=0;
            timerr.setText("30s");
            pointstestview.setText("0/0");
            resulttestview.setText("");
            playagain.setVisibility(View.INVISIBLE);
            generatequestion();
            countDownTimer=new CountDownTimer(30000+100,1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    timerr.setText((String.valueOf(millisUntilFinished / 1000)+"s"));
                }

                @Override
                public void onFinish() {                    //When countdown finish
                    playagain.setVisibility(View.VISIBLE);
                    timerr.setText("0s");
                    resulttestview.setText("Correct Answer :"+Integer.toString(score)+"\nTotal Question :"+Integer.toString(numberofquestion));

                    button0.setClickable(false);
                    button1.setClickable(false);
                    button2.setClickable(false);
                    button3.setClickable(false);
                }
            }.start();



    }


    int score=0;

    public void generatequestion(){         //question generate function
        button0.setClickable(true);
        button1.setClickable(true);
        button2.setClickable(true);
        button3.setClickable(true);

        Random random=new Random();   //random name e random number assign korlam
        int a= random.nextInt(21);  // a random number will generate 21 ta
        int b=random.nextInt(21);
        mathtext.setText(Integer.toString(a)+"+"+Integer.toString(b)); //show in dislay

        locationofcorrect= random.nextInt(4);       //int value assign, 4 random num gen in locationofcorrect
        answers.clear();                                    //Array list assign. to answer first cleeared

        for(int i=0;i<5;i++){                       // 4 ta button e je random number create hobe tar moddhe loop chalebe
            if(i==locationofcorrect){           // i jodi khuje pai sei number , tahole kon loacation e ase seita milabe
                answers.add(a+b);               // answer hobe sieta (.add mane answer er moddhe nia asa)
            }else{
                int incorrectans=random.nextInt(41);       // incorrect answer 41 ta gen hoobe
                while (incorrectans==a+b){
                    incorrectans=random.nextInt(41);    //2*22=42-1(answe taken)
                }
                answers.add(incorrectans);          //incorrect e sob ans add
            }

        }
        button0.setText(Integer.toString(answers.get(0)));      //button 0 te text set -answer list er 0 no index
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
    }

    public void clickedd(View view) {               // all button on clik func
        if (view.getTag().toString().equals(Integer.toString(locationofcorrect))) { // locationofcorret jei buttuon e ase seitate jodi click hoi

            score++;                                                                // Thaole score 1 kore barbe
            resulttestview.setText("Correct!");                     // resultview text settext correct

        }else{
            resulttestview.setText("Wrong!");                       // resultview text settext wrong
        }
        numberofquestion++;                                 //Ek ek ta click e numofquestion 1 bar kore add hobe
        pointstestview.setText(Integer.toString(score)+"/"+Integer.toString(numberofquestion)); //danpase score/number set text
        generatequestion();
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button0=(Button) findViewById(R.id.firstans);
        button1=(Button) findViewById(R.id.secondans);
        button2=(Button) findViewById(R.id.thirdans);
        button3=(Button) findViewById(R.id.fourthans);
        resulttestview=(TextView)  findViewById(R.id.resultunder_id);
        mathtext=(TextView) findViewById(R.id.math_id);
        pointstestview=(TextView) findViewById(R.id.score_id);
        startbutton=(Button) findViewById(R.id.go_id);
        timerr=(TextView) findViewById(R.id.timer_id);
        playagain=(Button)findViewById(R.id.playagain_id);
        relativeLayout=(RelativeLayout)findViewById(R.id.relative_id) ;


    }
}