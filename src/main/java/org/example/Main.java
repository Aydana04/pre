package org.example;

import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.SwingUtilities;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    static ArrayList<Product> products = new ArrayList<>();

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new MainFrame().setVisible(true);
        });
    }

    public static void addProduct() {

        scanner.nextLine();

        System.out.print("Введите название товара: ");
        String name = scanner.nextLine();

        System.out.print("Введите количество: ");
        int quantity = scanner.nextInt();

        System.out.print("Введите цену за штуку: ");
        double price = scanner.nextDouble();

        Product product = new Product(name, quantity, price);

        products.add(product);
    }

    public static void showProducts() {

        System.out.println("Список товаров:");

        for (int i = 0; i < products.size(); i++) {

            Product product = products.get(i);

            System.out.println(
                    (i + 1) + ". "
                            + product.getName()
                            + " — "
                            + product.getQuantity()
                            + " шт. по "
                            + product.getPrice()
                            + " сом."
            );
        }
    }

    public static double calculateTotal() {

        double total = 0;

        for (Product product : products) {

            total += product.getQuantity() * product.getPrice();
        }

        return total;
    }
}