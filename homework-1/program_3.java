import java.util.Scanner;

public class program_3 {
    public static boolean isPalin(String s) {
        for (int i = 0, j = s.length() - 1; i <= j; i++, j--) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
        }
        return true;
    }
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        if (isPalin(input)) {
            System.out.println(input + " is a Palindrome");
        }
        else {
            System.out.println(input + " is not a Palindrome");
        }
        sc.close();
    }
}
