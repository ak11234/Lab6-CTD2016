import java.util.Arrays;

/**
 * Created by Adam on 7/1/2016. Worksheet 6
 */
public class SimpleStack implements Stack {
    public static void main(String[] args){
        Stack s;
        s = new SimpleStack();
        for (int k = 0 ; k < 10 ; k++)
            s.push(k);
        System.out.println(s.top());
        System.out.println(s);
        System.out.println(s.isEmpty()); // false
        for (int k = 0 ; k < 10 ; k++)
            System.out.println(s.pop());
        System.out.println(s.isEmpty()); // true
        System.out.println(s.pop()); // null
    }
    private Object[] myElements;
    private int currentPlace;
    private int size=10;
    public SimpleStack()
    {
        myElements=new Object[size];
        currentPlace=0;
    }
    public boolean isEmpty()
    {
        //return (myElements[0]==null);
        return (currentPlace==0);
    }
    public boolean push(Object element)
    {
        if (currentPlace<size) {
            myElements[currentPlace]=element;
            currentPlace++;
            return true;
        } else {
            return false;
        }
    }
    public Object pop()
    {
        if (currentPlace==0){
        //Object result= myElements[currentPlace];
        //myElements[currentPlace]=null;
       // return result;
            return null;
        }else {
            currentPlace--;
            Object result=myElements[currentPlace];
            //myElements[currentPlace]=null;
            return result;
        }
    }
    public Object top()
    {
        return myElements[currentPlace-1];
    }
    public String toString(){
        return Arrays.toString(myElements);
    }
}
