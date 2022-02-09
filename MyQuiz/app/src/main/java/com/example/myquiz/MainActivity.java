package com.example.myquiz;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startEvaluation(View view) {
        String[] answers = evaluateGui();

        int result = evaluateQuiz(answers);

        toastResult(result);
    }

    public String[] evaluateGui() {
        String[] ret = new String[4];
        EditText editTextQuestion1 = findViewById(R.id.question_1);

        CheckBox checkBoxQuestion2Continent = findViewById(R.id.question_2_Continent);
        CheckBox checkBoxQuestion2Country = findViewById(R.id.question_2_Country);
        CheckBox checkBoxQuestion2LargestContinent = findViewById(R.id.question_2_Largest_Continent);

        boolean answerQuestion2 = false;

        if (checkBoxQuestion2Continent.isChecked() && !checkBoxQuestion2Country.isChecked() && checkBoxQuestion2LargestContinent.isChecked()) {
            answerQuestion2 = true;
        }

        CheckBox checkBoxQuestion4Africa = findViewById(R.id.question_4_africa);
        CheckBox checkBoxQuestion4EastAsia = findViewById(R.id.question_4_east_asia);
        CheckBox checkBoxQuestion4SouthAsia = findViewById(R.id.question_4_south_asia);

        boolean answerQuestion4 = false;

        boolean Africa = checkBoxQuestion4Africa.isChecked();
        boolean EastAsia = checkBoxQuestion4EastAsia.isChecked();
        boolean SouthAsia = checkBoxQuestion4SouthAsia.isChecked();


        if (!Africa && !EastAsia && SouthAsia) {
            answerQuestion4 = true;
        }

        ret[0] = editTextQuestion1.getText().toString().toLowerCase();
        ret[1] = Boolean.toString(answerQuestion2);
        ret[2] = evaluateRadioGroup(R.id.radio_group_question_3).toLowerCase();
        ret[3] = Boolean.toString(answerQuestion4);


        return ret;
    }

    public int evaluateQuiz(String[] answers) {
        int result = 0;
        String[] correctAnswers = {"paris", "true", "algeria", "true"};

        for (int i = 0; i < correctAnswers.length; i++) {
            if (answers[i].equals(correctAnswers[i])) {
                result++;
            }
        }

        return result;
    }

    public void toastResult(int result) {
        String message = result + " out of 4. ";

        if (result == 0) {
            message += "Poor.";
        } else if (result == 1) {
            message += "You could do better.";
        } else if (result == 2) {
            message += "Try Again.";
        } else if (result == 3) {
            message += "Nice.";
        } else if (result == 4) {
            message += "Great!";
        }

        Toast reportResult = Toast.makeText(getApplicationContext(), message,
                Toast.LENGTH_SHORT);
        reportResult.show();
    }

    private String evaluateRadioGroup(int id) {
        RadioGroup radioGroupQuestion;
        RadioButton radioButtonQuestion;

        radioGroupQuestion = findViewById(id);

        int radioButtonId = radioGroupQuestion.getCheckedRadioButtonId();
        radioButtonQuestion = findViewById(radioButtonId);

        if (radioButtonQuestion == null) {
            return "";
        }

        return (String)radioButtonQuestion.getText();
    }

    public void reset(View view) {
        EditText editText = findViewById(R.id.question_1);
        editText.setText("");

        CheckBox checkBox = findViewById(R.id.question_2_Continent);
        checkBox.setChecked(false);

        checkBox = findViewById(R.id.question_2_Country);
        checkBox.setChecked(false);

        checkBox = findViewById(R.id.question_2_Largest_Continent);
        checkBox.setChecked(false);

        RadioGroup radioGroup = findViewById(R.id.radio_group_question_3);
        radioGroup.clearCheck();

        checkBox = findViewById(R.id.question_4_africa);
        checkBox.setChecked(false);

        checkBox = findViewById(R.id.question_4_east_asia);
        checkBox.setChecked(false);

        checkBox = findViewById(R.id.question_4_south_asia);
        checkBox.setChecked(false);



    }
}