package com.example.photobot;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class output_screen extends AppCompatActivity {
    private TextView questionoutput;
    private TextView answeroutput;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_output_screen);
        questionoutput = (TextView)findViewById(R.id.questionOutput);
        answeroutput = (TextView)findViewById(R.id.answerOutput);
        String question = getIntent().getStringExtra("question");
        String answer = getIntent().getStringExtra("answer");
        questionoutput.setText(question);
        answeroutput.setText(answer);

    }
}