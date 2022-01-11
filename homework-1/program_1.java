public class program_1 {
    public static void main(String[] args) {
        // Series 1
        /*
        Write a program to generate the following series:
        1, 4, 7, 12, 23, 42, 77, ....
    
        1st term = 1
        2nd term = 4
        3rd term = 7

        nth term = (n - 1)th term + (n - 2)th term + (n - 3)th term, or,
        a(n) = a(n - 1) + a(n - 2) + a(n - 3), if a(n) denotes the nth term.
        */
        System.out.println("The first 15 terms of Series 1 are: ");
        int a1 = 1, a2 = 4, a3 = 7;
        System.out.print(a1 + " " + a2 + " " + a3 + " ");
        for (int i = 1; i <= 12; i++) {
            int cur = a1 + a2 + a3;
            System.out.print(cur + " ");
            a1 = a2;
            a2 = a3;
            a3 = cur;
        }
        System.out.println("");

        // ----

        // Series 2
        /*
        Write a program to generate the following series:
        1, 4, 9, 25, 36, 49, 81, 100, ...
        -> This is a series of square of numbers except the ones divisible by 8
        */
        System.out.println("The first 15 terms of Series 2 are: ");
        int n = 0;
        for (int i = 1; n <= 15; i++) {
            int squared = i * i;
            if (squared % 8 != 0) {
                n++;
                System.out.print(squared + " ");
            }
        }
        System.out.println("");

        // ----

        // Series 3
        /*
            Write a program to generate the following series:
            1, 5, 13, 29, 49, 77, ...
            -> a(1) = 1
            -> General term a(n) = a(n - 1) + 4 * n (if n is not divisible by 3)
        */
        n = 0;
        System.out.println("The first 15 terms of Series 3 are: ");
        for (int i = 1, j = 1; n <= 15; j++) {
            if (j % 3 != 0) {
                n++;
                System.out.print(i + " ");
                i = i + 4 * j;
            }
        }
        System.out.println("");
    }
}