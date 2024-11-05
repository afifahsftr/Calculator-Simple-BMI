package com.ubl.calculator;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText editTextWeight, editTextHeight, editTextAge;
    private RadioGroup radioGender; // Updated ID for RadioGroup
    private Button buttonCalculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextWeight = findViewById(R.id.editTextWeight);
        editTextHeight = findViewById(R.id.editTextHeight);
        editTextAge = findViewById(R.id.editTextAge);
        radioGender = findViewById(R.id.radio_gender); // Updated ID for RadioGroup
        buttonCalculate = findViewById(R.id.buttonCalculate);

        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (TextUtils.isEmpty(editTextWeight.getText()) ||
                            TextUtils.isEmpty(editTextHeight.getText()) ||
                            TextUtils.isEmpty(editTextAge.getText())) {

                        Toast.makeText(MainActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    double weight = Double.parseDouble(editTextWeight.getText().toString());
                    double height = Double.parseDouble(editTextHeight.getText().toString());
                    int age = Integer.parseInt(editTextAge.getText().toString());

                    if (weight <= 0 || height <= 0) {
                        Toast.makeText(MainActivity.this, "Weight and height must be positive", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    int selectedGenderId = radioGender.getCheckedRadioButtonId();
                    if (selectedGenderId == -1) {
                        Toast.makeText(MainActivity.this, "Please select a gender", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    String gender = ((RadioButton) findViewById(selectedGenderId)).getText().toString();

                    double bmi = weight / (height / 100 * height / 100);
                    String category;
                    if (bmi < 18.5) {
                        category = "You are underweight";
                    } else if (bmi > 25) {
                        category = "You are overweight";
                    } else {
                        category = "You are a healthy weight";
                    }

                    Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                    intent.putExtra("bmiValue", bmi);
                    intent.putExtra("bmiCategory", category);
                    intent.putExtra("age", age);
                    intent.putExtra("gender", gender);
                    startActivity(intent);

                } catch (NumberFormatException e) {
                    Toast.makeText(MainActivity.this, "Invalid input. Please enter valid numbers.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}