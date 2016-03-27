package com.hatenablog.nimzo.calculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

public class Calculator extends AppCompatActivity {

    private TextView textView;
    private EditText editText;
    private Button button;
    private static final DecimalFormat format = new DecimalFormat("###,###.#########");

    private int recentOperator = R.id.button_equal;
    private double result;
    private boolean isOperatorKeyPushed;

    private View.OnClickListener buttonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            textView.setText(editText.getText().toString());
        }
    };

    private View.OnClickListener buttonNumberListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Button button = (Button) view;

            if (isOperatorKeyPushed == true) {
                editText.setText(button.getText());
            } else {
                editText.append(button.getText());
            }
            isOperatorKeyPushed = false;
        }
    };

    private View.OnClickListener buttonOperatorListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Button operatorButton = (Button) view;
            double value = Double.parseDouble(editText.getText().toString());
            if (recentOperator == R.id.button_equal) {
                result = value;
            } else {
                result = calc(recentOperator, result, value);
                editText.setText(format.format(result));
            }

            recentOperator = operatorButton.getId();
            textView.setText(operatorButton.getText());
            isOperatorKeyPushed = true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_calclator);

        textView = (TextView) this.findViewById(R.id.textview);
        editText = (EditText) this.findViewById(R.id.edittext);
        button = (Button) this.findViewById(R.id.button);
        button.setOnClickListener(buttonListener);

        findViewById(R.id.button_1).setOnClickListener(buttonNumberListener);
        findViewById(R.id.button_2).setOnClickListener(buttonNumberListener);
        findViewById(R.id.button_3).setOnClickListener(buttonNumberListener);
        findViewById(R.id.button_4).setOnClickListener(buttonNumberListener);
        findViewById(R.id.button_5).setOnClickListener(buttonNumberListener);
        findViewById(R.id.button_6).setOnClickListener(buttonNumberListener);
        findViewById(R.id.button_7).setOnClickListener(buttonNumberListener);
        findViewById(R.id.button_8).setOnClickListener(buttonNumberListener);
        findViewById(R.id.button_9).setOnClickListener(buttonNumberListener);
        findViewById(R.id.button_0).setOnClickListener(buttonNumberListener);
        findViewById(R.id.button_dot).setOnClickListener(buttonNumberListener);

        findViewById(R.id.button_add).setOnClickListener(this.buttonOperatorListener);
        findViewById(R.id.button_subtract).setOnClickListener(this.buttonOperatorListener);
        findViewById(R.id.button_multiply).setOnClickListener(this.buttonOperatorListener);
        findViewById(R.id.button_divide).setOnClickListener(this.buttonOperatorListener);
        findViewById(R.id.button_equal).setOnClickListener(this.buttonOperatorListener);
    }

    private double calc(int operator, double value1, double value2) {
        switch (operator) {
            case R.id.button_add:
                return value1 + value2;
            case R.id.button_subtract:
                return value1 - value2;
            case R.id.button_multiply:
                return value1 * value2;
            case R.id.button_divide:
                if (value2 == 0) {
                    return 0;
                }
                return value1 / value2;
            default:
                return value1;
        }
    }
}
