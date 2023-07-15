package com.example.kid_game;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class Level0 extends AppCompatActivity implements View.OnClickListener{

    TextView Level,TotalQ,Question;
    Button AnsA, AnsB,AnsC,submitBtn;

    int score=0;
    int totQuestion=QuestionAnswer.question.length;
    int currentQuestionIndex=0;
    String selectedAnswer="";





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level0);

        Level=findViewById(R.id.idLevel);
        TotalQ=findViewById(R.id.idTotalQ);
        Question=findViewById(R.id.idQuestion);


        AnsA=findViewById(R.id.idansA);
        AnsB=findViewById(R.id.idansB);
        AnsC=findViewById(R.id.idansC);
        submitBtn=findViewById(R.id.idSubmit);

        AnsA.setOnClickListener(this);
        AnsB.setOnClickListener(this);
        AnsC.setOnClickListener(this);
        submitBtn.setOnClickListener(this);

        TotalQ.setText("Total question: "+totQuestion);
        loadNewQuestion();


    }

    @Override
    public void onClick(View view) {


        AnsA.setBackgroundColor(Color.WHITE);
        AnsB.setBackgroundColor(Color.WHITE);
        AnsC.setBackgroundColor(Color.WHITE);

        Button clickedButton=(Button) view;

        if (clickedButton.getId()==R.id.idSubmit){

            if(selectedAnswer.equals(QuestionAnswer.correctAnswers[currentQuestionIndex]))
            {
                score++;
            }
            currentQuestionIndex++;
            loadNewQuestion();
        }
        else{
            selectedAnswer=clickedButton.getText().toString();
            clickedButton.setBackgroundColor(Color.GREEN);
        }



    }

    public void loadNewQuestion(){

        if(currentQuestionIndex==totQuestion){
            finishQuiz();
            return;
        }

        Question.setText(QuestionAnswer.question[currentQuestionIndex]);
        AnsA.setText(QuestionAnswer.choices[currentQuestionIndex][0]);
        AnsB.setText(QuestionAnswer.choices[currentQuestionIndex][1]);
        AnsC.setText(QuestionAnswer.choices[currentQuestionIndex][2]);
    }

    public void finishQuiz(){
        String passStatus="";


        if(score>totQuestion){
            passStatus="Passed";
        }

        else{
            passStatus="Failed";
        }
        new AlertDialog.Builder(this)
                .setTitle(passStatus)
                .setMessage("Score is "+score+"out of"+totQuestion)
                .setPositiveButton("Restart",(dialogInterface, i) -> restartQuiz() )
                .setCancelable(false)
                .show();

    }

    public void restartQuiz(){
        score=0;
        currentQuestionIndex=0;
        loadNewQuestion();


    }




}