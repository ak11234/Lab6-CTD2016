import java.awt.*;
import java.util.Stack;

/**
 * Created by Adam Kalman on 6/30/2016. For lab 4
 */
public class LargeNumber {

    public static void main(String[] args){
        //Test private methods here
        LargeNumber n1 = new LargeNumber("555111222");
        System.out.println(leftPad("123456", 2));
    }

    private String strNum;

    public LargeNumber(String number){
        strNum=number;
    }
    private static String leftPad(String nums, int zeros){
        String result="";
        for (int k=1 ; k<=zeros ; k++){
            result += "0";
        }
        return result+nums;
    }
    public String toString(){
        return strNum;
    }
    public LargeNumber plus(LargeNumber other){
        //First get lengths to see if we need to pad
        int len,lenother,difference,finallen;
        len=strNum.length();
        lenother=other.strNum.length();
        if (len<lenother){
            difference=lenother-len;
            finallen=lenother;
            strNum =leftPad(strNum,difference);
        }
        else if (len>lenother){
            difference=len-lenother;
            finallen=len;
            other.strNum=leftPad(other.strNum,difference);
        } else {
            finallen=len;
        }
        //Now adding time.
        int num1,num2,sum,remainder=0;
        Stack <String> answerstack = new Stack <String>();
        String strSum;
        for (int c=finallen ; c>0 ; c--){
            num1=Integer.parseInt(strNum.substring(c-1,c));
            num2=Integer.parseInt(other.strNum.substring(c-1,c));
            sum=num1+num2+remainder;
            remainder=0;
            strSum=Integer.toString(sum);
            if (sum>9){
                //split nums and do stuff
                answerstack.push(strSum.substring(1,2));
                remainder=Integer.parseInt(strSum.substring(0,1));
            } else {
                answerstack.push(strSum);
            }
        }
        if (remainder!=0){ //If the last number we calculate has a number to carry over, check and do it now.
            answerstack.push(Integer.toString(remainder));
        }
        LargeNumber result = new LargeNumber("");
        for (int k=answerstack.size() ; k>=1 ; k--){
            result.strNum+=answerstack.pop();
        }
        return result;
    }
    public LargeNumber minus(LargeNumber other){
        int len,lenother;
        boolean overflow=false;
        LargeNumber newToAdd = new LargeNumber("");
        LargeNumber oldToAdd = new LargeNumber(strNum);
        LargeNumber sum = new LargeNumber("");
        LargeNumber result = new LargeNumber("");
        len=strNum.length();
        lenother=other.strNum.length();
        if (len>lenother){
            int difference=len-lenother;
            other.strNum=leftPad(other.strNum,difference);
            lenother=len;
        }
        for (int c=1 ; c<=lenother ; c++){
            if (c==lenother){
                if (other.strNum.substring(c-1,c).equals("0")) {
                    newToAdd.strNum+="0";
                    overflow=true;
                } else {
                    newToAdd.strNum+=Integer.toString(10-(Integer.parseInt(other.strNum.substring(c-1,c))));
                }
            } else {
                newToAdd.strNum+=Integer.toString(9-(Integer.parseInt(other.strNum.substring(c-1,c))));
            }
            //System.out.println("nta: " + newToAdd);
        }
        sum = oldToAdd.plus(newToAdd);
        result.strNum = sum.strNum.substring(1); //shave off first number
        while (result.strNum.substring(0,1).equals("0")){ //Check to make sure first num isn't 0
            result.strNum = result.strNum.substring(1); //if it is, deal with it
        }
        if (overflow==true){
            result.strNum=Integer.toString(Integer.parseInt(result.strNum)+10);
        }
        return result;
    }
    public LargeNumber timesByOne(int other){
        int len,remainder=0;
        String strProduct;
        Stack <String> answerstack = new Stack <String>();
        len=strNum.length();
        int num1,num2,product=0;
        for (int c=len ; c>0 ; c--){
            num1=Integer.parseInt(strNum.substring(c-1,c));
            product=(num1*other)+remainder;
            remainder=0;
            strProduct=Integer.toString(product);
            if (product>9){
                //split nums and do stuff
                answerstack.push(strProduct.substring(1,2));
                remainder=Integer.parseInt(strProduct.substring(0,1));
            } else {
                answerstack.push(strProduct);
            }
        }
        if (remainder!=0){ //If the last number we calculate has a number to carry over, check and do it now.
            answerstack.push(Integer.toString(remainder));
        }
        LargeNumber result = new LargeNumber("");
        for (int k=answerstack.size() ; k>=1 ; k--){
            result.strNum+=answerstack.pop();
        }
        //System.out.println("timesone result is: " + result);
        return result;
    }
    public LargeNumber times(LargeNumber other){
        LargeNumber product= new LargeNumber("");
        LargeNumber result=new LargeNumber("0");
        int len,zeroamt=0;
        String zeros="";
        len=strNum.length();
        for (int c=len ; c>0 ; c--){
          //  System.out.println("hello world");
            zeroamt=(len-c);
            zeros="";
          //  System.out.println("zero: " + zeroamt);
            for (int z=zeroamt ; z>=1 ; z--){
                zeros+="0";
            }
            //System.out.println("post loop");
            product=(other.timesByOne(Integer.parseInt(strNum.substring(c-1,c))));
            product.strNum=product.strNum+zeros;
            //System.out.println("product is: " + product);
            result = result.plus(product);
            //System.out.println("result is currently: " + result);
            //System.out.println("end c: " + c);
        }
        return result;
    }
}
