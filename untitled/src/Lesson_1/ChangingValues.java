package Lesson_1;

import java.util.Arrays;

public class ChangingValues <T> {

    private  T [] one ;

    public void changingValuesArray ( ){
        one = (T[]) new String [] {" one ", " two ", " three ", " fore "};
        System.out.println("\nStart list " + Arrays.toString(one));
        String a = String.valueOf(one [0]);
        //String a = "a";
        //String b = "b";
        String b = String.valueOf(one [3]);
        String [] c;
        c = changingValues ((String[]) one, a, b);
        System.out.println("Modified list" + Arrays.toString(c)+"\n");
    }
    public String [] changingValues ( String [] origin, String a, String b) {

        String c = null;
        String z = null;
            for (int i = 0; i < origin.length; i++) {
                if (origin[i].equals(a)) {
                    for (int j = 0; j < origin.length; j++) {
                        if (origin[j].equals(b)) {
                            c = b;
                            origin[j] = a;
                            break;
                        }
                    }
                }
            }
            for (int i = 0; i < origin.length; i++) {
                if (origin[i].equals(a)) {
                    try {
                        if (c != null) {
                            a = c;
                            z = a;
                            origin[i] = a;
                            break;
                        } else {
                            throw new Exception(" there are no such values in the array ");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        break;
                    }
                }
            }
            try {
                if (c == null || z == null) {
                    throw new Exception(" there are no such values in the array ");
                }
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        return origin;
    }
}
