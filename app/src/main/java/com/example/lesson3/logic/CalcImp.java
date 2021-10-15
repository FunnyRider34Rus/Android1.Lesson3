package com.example.lesson3.logic;

public class CalcImp  implements Calc {

    @Override
    public double performOperation(double argOne, double argTwo, Operation operation) {
        switch (operation) {
            case ADD:
                return argOne + argTwo;
            case SUB:
                return argOne - argTwo;
            case MULT:
                return argOne * argTwo;
            case DIV:
                return argOne / argTwo;
            case EXPON:
                return argOne * argOne;
            case SQRT:
                return Math.sqrt(argOne);
            case MODUL:
                return (int)(argOne/2);
        }
        return 0.0;
    }
}
