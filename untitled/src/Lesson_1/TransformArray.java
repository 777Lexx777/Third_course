package Lesson_1;

import java.util.ArrayList;
import java.util.List;

public class TransformArray<T> {

    private  String[] two = {" one ", " two ", " three ", " fore "};
    private  List<T> arrayWords = new ArrayList<>( );

    public void tranArray( ){
        List<T> c;
        c = transformArray (two);
        System.out.println("ArrayList = " + c);
    }
    public List<T> transformArray(String [] origin) {

        for (int i = 0; i < origin.length; i++) {
           String array = String.valueOf(origin[i]);
           arrayWords.add((T) String.valueOf(array));
           System.out.println(arrayWords);
        }
        return  arrayWords;
    }
}
