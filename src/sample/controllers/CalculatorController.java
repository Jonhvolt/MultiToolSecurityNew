package sample.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class CalculatorController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label labelResultCalc;

    @FXML
    private Button ClearCalcBtn;

    @FXML
    private Button plus_minusCalcBtn;

    @FXML
    private Button percentCalcBtn;

    @FXML
    private Button delCalcBtn;

    @FXML
    private Button sevenCalcBtn;

    @FXML
    private Button eightCalcBtn;

    @FXML
    private Button fourCalcBtn;

    @FXML
    private Button nineCalcBtn;

    @FXML
    private Button multCalcBtn;

    @FXML
    private Button fiveCalcBtn;

    @FXML
    private Button sixCalcBtn;

    @FXML
    private Button minusCalcBtn;

    @FXML
    private Button oneCalcBtn;

    @FXML
    private Button twoCalcBtn;

    @FXML
    private Button threeCalcBtn;

    @FXML
    private Button plusCalcBtn;

    @FXML
    private Button zeroCalcBtn;

    @FXML
    private Button commoCalcBtn;

    @FXML
    private Button equalCalcBtn;

    private String str_num = "";
    private float first_num;
    private char operation;

    @FXML
    void initialize() {
        zeroCalcBtn.setOnAction(event -> {
            addNumber(0);
        });
        oneCalcBtn.setOnAction(event -> {
            addNumber(1);
        });
        twoCalcBtn.setOnAction(event -> {
            addNumber(2);
        });
        threeCalcBtn.setOnAction(event -> {
            addNumber(3);
        });
        fourCalcBtn.setOnAction(event -> {
            addNumber(4);
        });
        fiveCalcBtn.setOnAction(event -> {
            addNumber(5);
        });
        sixCalcBtn.setOnAction(event -> {
            addNumber(6);
        });
        sevenCalcBtn.setOnAction(event -> {
            addNumber(7);
        });
        eightCalcBtn.setOnAction(event -> {
            addNumber(8);
        });
        nineCalcBtn.setOnAction(event -> {
            addNumber(9);
        });
        plusCalcBtn.setOnAction(event -> {
            mathAction('+');
        });
        minusCalcBtn.setOnAction(event -> {
            mathAction('-');
        });
        multCalcBtn.setOnAction(event -> {
            mathAction('*');
        });
        delCalcBtn.setOnAction(event -> {
            mathAction('/');
        });
        equalCalcBtn.setOnAction(event -> {
            if(this.operation == '+' || this.operation == '-' || this.operation == '/'
                    || this.operation == '*') {
                equalMethod();
            }
        });
        commoCalcBtn.setOnAction(event -> {
            if (!this.str_num.contains(".")) {
                this.str_num += ".";
                labelResultCalc.setText(str_num);
            }
        });
        percentCalcBtn.setOnAction(event -> {
            if(this.str_num != "") {
                float num = Float.parseFloat(this.str_num) * 0.1f;
                this.str_num = Float.toString(num);
                labelResultCalc.setText(str_num);
            }
        });
        plus_minusCalcBtn.setOnAction(event -> {
            if(this.str_num != "") {
                float num = Float.parseFloat(this.str_num) * -1;
                this.str_num = Float.toString(num);
                labelResultCalc.setText(str_num);
            }
        });
        ClearCalcBtn.setOnAction(event -> {
            labelResultCalc.setText("0");
            this.str_num = "";
            this.first_num = 0;
            this.operation = 'A';
        });

    }

    void equalMethod() {
        float res = 0;
        switch (this.operation) {
            case '+':
                res = this.first_num + Float.parseFloat(this.str_num);
                break;
            case '-':
                res = this.first_num - Float.parseFloat(this.str_num);
                break;
            case '/':
                if (Integer.parseInt(this.str_num) != 0) {
                    res = this.first_num / Float.parseFloat(this.str_num);
                    break;
                } else res = 0;
            case '*':
                res = this.first_num * Float.parseFloat(this.str_num);
                break;
        }

        labelResultCalc.setText(Float.toString(res));
        this.str_num = "";
        this.operation = 'A';
        this.first_num = 0;
    }

    void mathAction(char operation) {
        if(this.operation != '+' && this.operation != '-' && this.operation != '/'
                && this.operation != '*') {
            this.first_num = Float.parseFloat(this.str_num);
            labelResultCalc.setText(String.valueOf(operation));
            this.str_num = "";
            this.operation = operation;
        }
    }

    void addNumber(int number) {
        this.str_num += Integer.toString(number);
        labelResultCalc.setText(str_num);
    }
}