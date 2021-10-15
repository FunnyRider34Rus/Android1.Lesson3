package com.example.lesson3.Presenter;

import com.example.lesson3.logic.Calc;
import com.example.lesson3.logic.Operation;
import com.example.lesson3.ui.CalcView;

public class Presenter {


    private static final int DECIMAL_BASE = 10;

    private final CalcView view;
    private final Calc calculator;

    private Double argOne = 0.0;
    private Double argTwo = null;
    private Operation previousOperation = null;
    private Operation operationSelected;
    private boolean isDotPressed;
    private int divider;

    public Presenter(CalcView view, Calc calculator) {
        this.view = view;
        this.calculator = calculator;
    }

    public void onDigitPressed(int digit) {
        if (argTwo == null) {
            if (isDotPressed) {
                argOne = argOne + digit / (double) divider;
                divider *= DECIMAL_BASE;
            }else {
                argOne = argOne * DECIMAL_BASE + digit;
            }
            displayResult(argOne);
        } else {
            if (isDotPressed) {
                argTwo = argTwo + digit /(double) divider;
                divider *= DECIMAL_BASE;
            } else {
                argTwo = argTwo * DECIMAL_BASE + digit;
            }
            displayResult(argTwo);
        }
    }

    public void onOperationPressed(Operation operation) {
        operationSelected = operation;
        if (previousOperation != null) {
            double result = calculator.performOperation(argOne, argTwo, previousOperation);
            displayResult(result);
            argOne = result;
        }
        previousOperation = operation;
        argTwo = 0.0;
        isDotPressed = false;
    }

    public void onSummKeyPressed() {
        double result = calculator.performOperation(argOne, argTwo, operationSelected);
        displayResult(result);
    }

    public void onDotPressed() {
        if (!isDotPressed) {
            isDotPressed = true;
            divider = DECIMAL_BASE;
        }
    }

    public void onCleanPressed() {
        argOne = 0.0;
        argTwo = null;
        previousOperation = null;
        operationSelected = null;
        isDotPressed = false;
        view.showResult("0");
    }

    private void displayResult(double arg) {

        long longValue = (long) arg;

        if (longValue == arg) {
            view.showResult(String.valueOf(longValue));
        } else {
            view.showResult(String.valueOf(arg));
        }
    }

}
