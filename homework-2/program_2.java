import java.util.*;

public class program_2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        // Frequency map stores the count of each character in the String

        HashMap<Character, Integer> frequency = new HashMap<Character, Integer>();
        for (int i = 0; i < input.length(); i++) {
            char currentChar = input.charAt(i);
            if (frequency.containsKey(currentChar)) {
                frequency.put(currentChar, frequency.get(currentChar) + 1);
            } else {
                frequency.put(currentChar, 1);
            }
        }
        System.out.println("The repeating characters along with their count are: ");
        for (Map.Entry entry : frequency.entrySet()) {
            Integer count = (Integer) entry.getValue();
            if (count > 1) {
                System.out.println(entry.getKey() + " -> " + entry.getValue());
            }
        }
    }
}
