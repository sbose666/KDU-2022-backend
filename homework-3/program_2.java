import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

class Pair implements Comparable<Pair> {
    String country, capital;

    Pair(String country, String capital) {
        this.country = country;
        this.capital = capital;
    }

    @Override
    public int compareTo(Pair pair) {
        return capital.compareTo(pair.capital);
    }
}

public class program_2 {
    public static void main(String[] args) {
        File csvFile = new File("/home/dell/Desktop/Backend/Assign/h3/src/country-list.csv");
        try {
            // Task-1
            Scanner tempScanner = new Scanner(csvFile);
            System.out.println("First 10 lines of the file: ");
            for (int lines = 1; lines <= 10; lines++) {
                System.out.println(tempScanner.nextLine());
            }
            tempScanner.close();
            System.out.println("");

            // Task-2
            Scanner sc = new Scanner(csvFile);
            sc.useDelimiter(",|\\n");
            TreeMap<String, String> map = new TreeMap<>();
            // skip first line
            sc.next();
            sc.next();
            sc.next();
            while (sc.hasNext()) {
                String country = sc.next(), capital = sc.next(), type = sc.next();
                map.put(country, capital);
            }

            // Task-3
            ArrayList<Pair> list = new ArrayList<>();
            for (Map.Entry<String, String> entry : map.entrySet()) {
                list.add(new Pair(entry.getKey(), entry.getValue()));
            }
            Collections.sort(list);
            System.out.println("The last 10 pairs: ");
            for (int i = list.size() - 10; i < list.size(); i++) {
                System.out.println(list.get(i).country + " -> " + list.get(i).capital);
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } finally {
            System.out.println("Exiting");
        }
    }
}
