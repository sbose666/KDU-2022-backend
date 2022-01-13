import java.util.ArrayList;
import java.util.Collections;

class Anything<T> {
    T instance;

    Anything(T instance) {
        this.instance = instance;
    }

    public T getInstance() {
        return instance;
    }
}

public class program_3 {
    public static void main(String[] args) {

        Anything<String> object_1 = new Anything<>("Hello"); // Passing a String
        System.out.println(object_1.getInstance());
        Anything<Integer> object_2 = new Anything<>(45); // Passing an Integer
        System.out.println(object_2.getInstance());

        ArrayList<String> arrOfString = new ArrayList<>(); // ArrayList of String
        arrOfString.add("Hey");
        arrOfString.add("There");

        ArrayList<Integer> arrOfIntegers = new ArrayList<>(); // ArrayList of Integers
        arrOfIntegers.add(5);
        arrOfIntegers.add(3);

        Collections.sort(arrOfString);
        Collections.sort(arrOfIntegers);

        for (Integer i : arrOfIntegers) {
            System.out.print(i + " ");
        }
        System.out.println("");
        for (String s : arrOfString) {
            System.out.print(s + " ");
        }
    }
}
