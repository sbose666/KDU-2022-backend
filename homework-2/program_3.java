import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Scanner;

public class program_3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        input = input.toLowerCase(); // Since case is considered insensitive
        StringBuilder result = new StringBuilder(); // Will store the final resulting string
        HashSet<Character> isPresent = new HashSet<>(); // Allows us to check whether we have encountered a particular element before.
        for (int i = 0; i < input.length(); i++) {
            if (!isPresent.contains(input.charAt(i))) {
                // If we reach here, it means that this is the first time we have encountered the character at index i.
                // So, we can add the current character to the resulting string and update our set.
                result.append(input.charAt(i));
                isPresent.add(input.charAt(i));
            }
        }
        System.out.println(result);
    }
}
