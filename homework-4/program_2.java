import java.util.ArrayList;

public class Program_2 {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        long startTime, endTime;
        for (int i = 1; i <= 9999999; i++) {
            list.add(i);
        }

        startTime = System.nanoTime();
        for (int i = 0; i < list.size(); i++) {
            continue;
        }
        endTime = System.nanoTime();
        System.out.println("\nTime taken (for loop): " + (endTime - startTime));

        startTime = System.nanoTime();
        for (Integer i : list) {
            continue;
        }
        endTime = System.nanoTime();
        System.out.println("\nTime taken (for-each loop): " + (endTime - startTime));

        startTime = System.nanoTime();
        list.stream().forEach(obj -> {

        });
        endTime = System.nanoTime();
        System.out.println("\nTime taken (stream.for-each loop): " + (endTime - startTime));

        startTime = System.nanoTime();
        list.stream().parallel().forEach(obj -> {

        });
        endTime = System.nanoTime();
        System.out.println("\nTime taken (stream.parallel.for-each loop): " + (endTime - startTime));
    }
}
