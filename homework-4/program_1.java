import java.util.*;
import java.util.stream.Collectors;

class Product implements Comparable<Product> {
    int product_id, price, weight;

    Product(int id, int price, int weight) {
        this.product_id = id;
        this.price = price;
        this.weight = weight;
    }

    public int getPrice() {
        return price;
    }

    public int getProduct_id() {
        return product_id;
    }

    public int getWeight() {
        return weight;
    }

    public int compareTo(Product item) {
        // Let the ordering be based upon the price
        return Integer.compare(price, item.price);
    }


    public void printDetails() {
        System.out.println("Id: " + product_id + ", " + "Price: " + price + ", " + "Weight: " + weight);
    }

}

public class Program_1 {
    public static void main(String[] args) {

        ArrayList<Product> listOfProducts = new ArrayList<>();
        listOfProducts.add(new Product(1, 98, 500));
        listOfProducts.add(new Product(2, 45, 120));
        listOfProducts.add(new Product(3, 66, 300));
        listOfProducts.add(new Product(4, 100, 150));
        listOfProducts.add(new Product(5, 14, 20));
        listOfProducts.add(new Product(6, 45, 100));
        listOfProducts.add(new Product(7, 98, 250));

        // 1
        int maxPrice = listOfProducts.stream().max(Product::compareTo).get().price;
        System.out.println("The max price of a product: " + maxPrice);

        // 2
        int minPrice = listOfProducts.stream().min(Product::compareTo).get().price;
        System.out.println("The min price of a product: " + minPrice);

        // 3
        boolean anyPresent = listOfProducts.stream().anyMatch(obj -> obj.weight > 500); // checking for presence of any product with weight > 500
        System.out.println("Are there any product with weight greater than 500? " + anyPresent);

        // 4
        boolean allPresent = listOfProducts.stream().allMatch(obj -> obj.weight > 100); // checking if all the products weigh greater than 100
        System.out.println("Do all products weigh more than 100? " + allPresent);

        // 5
        ArrayList<Product> filteredResult = listOfProducts.stream().filter(obj -> obj.weight > 100).collect(Collectors.toCollection(ArrayList::new));
        // filtering out all the products with weight > 100
        System.out.println("The products with weight greater than 100: ");
        for (Product item : filteredResult) {
            item.printDetails();
        }

        // 6
        ArrayList<Product> sortedResult = listOfProducts.stream().sorted().collect(Collectors.toCollection(ArrayList::new));
        System.out.println("The products in sorted order according to their price: ");
        for (Product item : sortedResult) {
            item.printDetails();
        }

        // 7
        Map<Integer, List<Product>> groupedResult = listOfProducts.stream().collect(Collectors.groupingBy(Product::getPrice));
        System.out.println("The products grouped according to their price: ");
        for (Map.Entry<Integer, List<Product>> entry : groupedResult.entrySet()) {
            System.out.print("Price: " + entry.getKey() + " -> ");
            for (Product p : entry.getValue()) {
                p.printDetails();
            }
            System.out.println();
        }

        // 8
        int averagePrice = listOfProducts.stream().map(Product::getPrice).reduce(0, Integer::sum) / listOfProducts.size();
        System.out.println("Average Price of all the products: " + averagePrice);

        // 9
        int sumOfIncreasedPrices = listOfProducts.stream().map(item -> item.price + (item.price * 25) / 100).reduce(0, Integer::sum);
        System.out.println("Sum of the price of all products after an increase of 25%: " + sumOfIncreasedPrices);
    }
}
