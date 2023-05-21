package com.example.mad_labassign_01;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    TextView resultTv, solutionTv;
    MaterialButton B0, B1, B2, B3, B4, B5, B6, B7, B8, B9, OpeningBracket, ClosingButton, Dot, Clear, AllClear, Add, Subtract, Divide, Multiply, Equal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultTv = findViewById(R.id.result_textview);
        solutionTv = findViewById(R.id.solution_textview);

        B0 = findViewById(R.id.b0);
        B1 = findViewById(R.id.b1);
        B2 = findViewById(R.id.b2);
        B3 = findViewById(R.id.b3);
        B4 = findViewById(R.id.b4);
        B5 = findViewById(R.id.b5);
        B6 = findViewById(R.id.b6);
        B7 = findViewById(R.id.b7);
        B8 = findViewById(R.id.b8);
        B9 = findViewById(R.id.b9);
        OpeningBracket = findViewById(R.id.opening_bracket);
        ClosingButton = findViewById(R.id.closing_bracket);
        Dot = findViewById(R.id.dot);
        Clear = findViewById(R.id.clear);
        AllClear = findViewById(R.id.all_clear);
        Equal = findViewById(R.id.equal);
        Subtract = findViewById(R.id.subtract);
        Add = findViewById(R.id.add);
        Divide = findViewById(R.id.divide);
        Multiply = findViewById(R.id.multiply);

        assignId(B0, R.id.b0);
        assignId(B1, R.id.b1);
        assignId(B2, R.id.b2);
        assignId(B3, R.id.b3);
        assignId(B4, R.id.b4);
        assignId(B5, R.id.b5);
        assignId(B6, R.id.b6);
        assignId(B7, R.id.b7);
        assignId(B8, R.id.b8);
        assignId(B9, R.id.b9);
        assignId(OpeningBracket, R.id.opening_bracket);
        assignId(ClosingButton, R.id.closing_bracket);
        assignId(Dot, R.id.dot);
        assignId(Clear, R.id.clear);
        assignId(AllClear, R.id.all_clear);
        assignId(Equal, R.id.equal);
        assignId(Add, R.id.add);
        assignId(Subtract, R.id.subtract);
        assignId(Divide, R.id.divide);
        assignId(Multiply, R.id.multiply);
    }

    void assignId(MaterialButton btn, int id) {
        btn = findViewById(id);
        btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        MaterialButton button = (MaterialButton) v;
        String buttonText = button.getText().toString();
        String dataToCalculate = solutionTv.getText().toString();
        if (buttonText.equals("AC")) {
            solutionTv.setText("");
            resultTv.setText("0");
            return;
        }
        if (buttonText.equals("=")) {
            solutionTv.setText(resultTv.getText());
            return;
        }
        if (buttonText.equals("C")) {
            dataToCalculate = dataToCalculate.substring(0, dataToCalculate.length() - 1);
        }
        else {
            dataToCalculate = dataToCalculate + buttonText;

        }
        solutionTv.setText(dataToCalculate);

        String finalResult = getResult(dataToCalculate);
        if (!finalResult.equals("Error")) {
            resultTv.setText(finalResult);
        }

    }

    String getResult(String data) {
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult = context.evaluateString(scriptable, data, "Javascript", 1, null).toString();
            if (finalResult.endsWith(".0")) {
                finalResult = finalResult.replace(".0", "");
            }
            return finalResult;
        } catch (Exception e) {
            return "Error";
        }

    }
}