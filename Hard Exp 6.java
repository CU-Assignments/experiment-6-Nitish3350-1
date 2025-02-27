import java.util.*;
import java.util.stream.*;

class Product {
    private String name;
    private String category;
    private double price;

    public Product(String name, String category, double price) {
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }
}

public class Main {
    public static void main(String[] args) {
        List<Product> products = Arrays.asList(
            new Product("Laptop", "Electronics", 1200),
            new Product("Phone", "Electronics", 800),
            new Product("TV", "Electronics", 1500),
            new Product("Shoes", "Fashion", 120),
            new Product("Jeans", "Fashion", 80),
            new Product("Blender", "Home Appliances", 150),
            new Product("Microwave", "Home Appliances", 300)
        );

        System.out.println("Products grouped by category:");
        Map<String, List<Product>> groupedByCategory = products.stream()
                .collect(Collectors.groupingBy(Product::getCategory));
        groupedByCategory.forEach((category, productList) -> {
            System.out.println(category + ": " + productList.stream()
                    .map(Product::getName)
                    .collect(Collectors.joining(", ")));
        });

        System.out.println("\nMost expensive product in each category:");
        Map<String, Optional<Product>> mostExpensiveByCategory = products.stream()
                .collect(Collectors.groupingBy(
                        Product::getCategory,
                        Collectors.maxBy(Comparator.comparingDouble(Product::getPrice))
                ));
        mostExpensiveByCategory.forEach((category, product) -> 
            System.out.println(category + ": " + product.map(Product::getName).orElse("No products")));

        double averagePrice = products.stream()
                .mapToDouble(Product::getPrice)
                .average()
                .orElse(0);
        System.out.println("\nAverage price of all products: " + averagePrice);
    }
}
