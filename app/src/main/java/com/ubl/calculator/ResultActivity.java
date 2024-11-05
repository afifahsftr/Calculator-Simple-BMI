package com.ubl.calculator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {
    private TextView textViewResult;
    private Button buttonBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        textViewResult = findViewById(R.id.textViewResult);
        buttonBack = findViewById(R.id.buttonBack);

        double bmi = getIntent().getDoubleExtra("bmiValue", 0);
        String category = getIntent().getStringExtra("bmiCategory");
        int age = getIntent().getIntExtra("age", 0);
        String gender = getIntent().getStringExtra("gender");

        String resultText = String.format("BMI: %.2f\n%s\nGender: %s\nAge: %d", bmi, category, gender, age);
        textViewResult.setText(resultText);

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Go back to MainActivity
            }
        });
    }
}
