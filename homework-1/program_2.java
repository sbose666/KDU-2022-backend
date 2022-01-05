public class program_2 {
    public static int sumOfDigits(int x) {
        int sum = 0;
        while (x > 0) {
            sum += (x % 10);
            x /= 10;
        }
        return sum;
    }
    public static void checker(int n) {
        // This method displays all the numbers between 1 and n which are divisible by 3
        // Rule of Divisibility for 3: Sum of all digits of the number must be divisible by 3
        System.out.println("The numbers between 1 and " + n + " which are divisible by 3 are: ");
        for(int i = 1; i <= n; i++) {
            int digitSum = sumOfDigits(i);
            if (digitSum % 3 == 0) {
                System.out.print(i + " ");
            }
        }
        System.out.println("");
    }
    public static void main(String[] args) {
        checker(50);
        checker(1000);
    }
}
