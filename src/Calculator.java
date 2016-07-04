import acm.gui.*;
import acm.program.*;
import java.awt.event.*;
import java.util.Arrays;
import javax.swing.*;

public class Calculator extends Program {
    private JLabel myDisplay;
    private SimpleStack myStack;
    private Boolean midNum = true;

    public static void main(String[] args) {
        new Calculator().start();
    }

    public void init() {
        myStack = new SimpleStack();
        myDisplay = new JLabel("", SwingConstants.RIGHT);

        setLayout(new TableLayout(9, 4));

        add(myDisplay, "gridwidth=4");
        add(new JButton("7"));
        add(new JButton("8"));
        add(new JButton("9"));
        add(new JButton("+"));

        add(new JButton("4"));
        add(new JButton("5"));
        add(new JButton("6"));
        add(new JButton("-"));
        add(new JButton("1"));
        add(new JButton("2"));
        add(new JButton("3"));
        add(new JButton("*"));
        add(new JButton("0"));
        add(new JButton("Enter"), "gridwidth=2");
        add(new JButton("C"));

        addActionListeners();
        myStack.push("0");
        myDisplay.setText(myStack.top().toString());
    }

    public void actionPerformed(ActionEvent e) {
        System.out.println(myStack);
        String key;

        key = e.getActionCommand();
        //myDisplay.setText(key);
        String[] digits = {"1", "2", "3", "4", "5", "6", "7", "8", "9","0"};
        if (Arrays.asList(digits).contains(key)) {


            if (midNum) { //while user is entering numbers
                if (myStack.top().equals("0")){
                    myStack.push(key);
                } else {
                    myStack.push(myStack.pop() + key);
                }
            }
            if (!midNum){
                midNum=true;
                myStack.push(key);
            }
        }

        if (key.equals("Enter")&&!midNum) {
            myStack.push(myStack.top());
        }
        if (key.equals("Enter")&&midNum){
            midNum=false;
        }
        if (key.equals("C")) {
            myStack.pop();
            myStack.push("0");
            midNum = true;
        }

/*        if (!midNum && key.equals("0")) {
            myStack.push(key);
            midNum = true;
        }*/
        if (key.equals("+")) {
            LargeNumber num1;
            LargeNumber num2;
            if (myStack.isEmpty()){
                num1 =new LargeNumber("0");
            } else {
                 num1 = new LargeNumber(myStack.pop().toString());
            }
            if (myStack.isEmpty()){
                num2 = new LargeNumber("0");
            } else {
                num2 = new LargeNumber(myStack.pop().toString());
            }
            myStack.push(num1.plus(num2).toString());
            midNum=false;
        }
        if (key.equals("-")) {
            LargeNumber num1;
            LargeNumber num2;
            if (myStack.isEmpty()){
                num1 =new LargeNumber("0");
            } else {
                num1 = new LargeNumber(myStack.pop().toString());
            }
            if (myStack.isEmpty()){
                num2 = new LargeNumber("0");
            } else {
                num2 = new LargeNumber(myStack.pop().toString());
            }
            myStack.push(num2.minus(num1).toString());
            midNum=false;
        }
        if (key.equals("*")) {
            LargeNumber num1;
            LargeNumber num2;
            if (myStack.isEmpty()){
                num1 =new LargeNumber("0");
            } else {
                num1 = new LargeNumber(myStack.pop().toString());
            }
            if (myStack.isEmpty()){
                num2 = new LargeNumber("0");
            } else {
                num2 = new LargeNumber(myStack.pop().toString());
            }
            myStack.push(num2.times(num1).toString());
            midNum=false;
        }
        myDisplay.setText(myStack.top().toString());
    }
}