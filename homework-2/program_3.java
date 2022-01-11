import java.util.*;

public class program_3 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        StringBuilder result = new StringBuilder(); // Will store the final resulting string

        HashSet<Character> isPresent = new HashSet<>(); // Allows us to check whether we have encountered a particular element before.

        for (int i = 0; i < input.length(); i++) {
            Character ch = input.charAt(i);
            Character upperCase = Character.toUpperCase(ch), lowerCase = Character.toLowerCase(ch);
            if (isPresent.contains(upperCase) || isPresent.contains(lowerCase))
                continue;
            // If we reach at this point, it means the current character is encountered for the first time
            result.append(ch);
            isPresent.add(upperCase);
            isPresent.add(lowerCase);
        }
        System.out.println(result);
    }
}
