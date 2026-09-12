package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    static ArrayList<String> names = new ArrayList<>();
    static ArrayList<Integer> quantities = new ArrayList<>();
    static ArrayList<Double> prices = new ArrayList<>();

    public static void main(String[] args) {

        while (true) {

            printMenu();

            int choice = scanner.nextInt();

            switch (choice) {

                case 1:
                    addProduct();
                    break;

                case 2:
                    showProducts();
                    break;

                case 3:
                    System.out.println(
                            "Общая стоимость товаров на складе: "
                                    + calculateTotal()
                                    + " сом."
                    );
                    break;

                case 4:
                    System.out.println("Программа завершена.");
                    return;

                default:
                    System.out.println("Неверный пункт меню.");
            }
        }
    }

    public static void printMenu() {

        System.out.println("=== Склад товаров ===");
        System.out.println("1. Добавить товар");
        System.out.println("2. Показать список");
        System.out.println("3. Посчитать общую стоимость");
        System.out.println("4. Выход");
    }

    public static void addProduct() {

        scanner.nextLine();

        System.out.print("Введите название товара: ");
        String name = scanner.nextLine();

        System.out.print("Введите количество: ");
        int quantity = scanner.nextInt();

        System.out.print("Введите цену за штуку: ");
        double price = scanner.nextDouble();

        names.add(name);
        quantities.add(quantity);
        prices.add(price);
    }

    public static void showProducts() {

        System.out.println("Список товаров:");

        for (int i = 0; i < names.size(); i++) {

            System.out.println(
                    (i + 1) + ". "
                            + names.get(i)
                            + " — "
                            + quantities.get(i)
                            + " шт. по "
                            + prices.get(i)
                            + " руб."
            );
        }
    }

    public static double calculateTotal() {

        double total = 0;

        for (int i = 0; i < names.size(); i++) {

            total += quantities.get(i) * prices.get(i);
        }

        return total;
    }
}