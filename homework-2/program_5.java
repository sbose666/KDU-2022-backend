import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class program_5 {
    public static void main(String[] args) {

        // IO Exception
        try {
            FileInputStream fileInputStream = new FileInputStream("random.txt");
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } finally {
            System.out.println("Exiting program");
        }

//        // Number Format Exception
//        String invalidNumber = "hello";
//        try {
//            Integer number = Integer.parseInt(invalidNumber);
//            System.out.println(number);
//        } catch (NumberFormatException ex) {
//            ex.printStackTrace();
//        } finally {
//            System.out.println("Exiting");
//        }

//        // Arithmetic Exception
//        int numerator = 8, denominator = 0;
//        try {
//            int quotient = numerator / denominator;
//        } catch (ArithmeticException ex) {
//            ex.printStackTrace();
//        } finally {
//            System.out.println("Exiting");
//        }

//        // ArrayIndexOutOfBounds Exception
//        int[] arr = new int[4];
//        try {
//            arr[9] = 34;
//        } catch (ArrayIndexOutOfBoundsException ex) {
//            ex.printStackTrace();
//        } finally {
//            System.out.println("Exiting");
//        }

        // Nested try-catch-finally
//        int num1 = 92, num2 = 0;
//        int arr[] = new int[34];
//        String invalidNumber = "hey";
//        try {
//            int random = num1 / num2;
//            System.out.println(random);
//        } catch (ArithmeticException ex1) {
//            System.out.println("catch block 1");
//            try {
//                arr[343] = 454;
//            } catch (ArrayIndexOutOfBoundsException ex2) {
//                System.out.println("catch block 2");
//                try {
//                    Integer number = Integer.parseInt(invalidNumber);
//                } catch (NumberFormatException ex3) {
//                    System.out.println("catch block 3");
//                } finally {
//                    System.out.println("finally block 3");
//                }
//            } finally {
//                System.out.println("finally block 2");
//            }
//        } finally {
//            System.out.println("finally block 1");
//        }
    }
}
