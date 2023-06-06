package com.example.photobot;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class output_screen extends AppCompatActivity {
    private TextView questionoutput;
    private TextView answeroutput;

    private Button back;
    private Activity context;
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
        back = (Button) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(output_screen.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}