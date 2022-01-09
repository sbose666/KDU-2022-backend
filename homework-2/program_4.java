import java.util.Scanner;

public class program_4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s1 = sc.nextLine();
        String s2 = sc.nextLine();
        int insertionIndex = sc.nextInt();

        // Following 0 based indexing

        // Task-1
        // To check whether s2 is a substring of s1 and print the starting index of the last occurrence of s2 in s1
        if (s2.length() > s1.length()) {
            // If this is the case, it is impossible for s1 to contain s2
            System.out.println(-1);
        } else {
            int index = -1; // This stores the index of the last occurrence of s2 in s1, or -1 if no such match is found
            for (int i = 0; i <= s1.length() - s2.length(); i++) {
                if (s1.substring(i, i + s2.length()).equals(s2)) {
                    index = i;
                }
            }
            System.out.println(index); // If index == -1, it denotes no occurrence of s2 in s1
        }

        // Task-2
        if (insertionIndex < 0 || insertionIndex >= s1.length()) {
            // Since we are following 0 based indexing, the valid indexes are -> [0, Length of s1)
            // Thus, if this "if block" is executed, we have encountered an invalid index
            System.out.println("Invalid index!");
        } else {
            // We can use the substring method to clip out parts of the string as per our need
            String result = s1.substring(0, insertionIndex + 1) + s2 + s1.substring(insertionIndex + 1, s1.length());
            System.out.println("The resultant string: " + result);
        }
    }
}
