package org.example;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class MainFrame extends JFrame {

    private JTextField nameField;
    private JTextField quantityField;
    private JTextField priceField;

    private DefaultTableModel tableModel;
    private JTable table;

    public MainFrame() {

        setTitle("Склад товаров");
        setSize(800, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);


        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(15, 15));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));


        JLabel title = new JLabel("📦  СКЛАД ТОВАРОВ");
        title.setFont(new Font("Arial", Font.BOLD, 26));

        JLabel subtitle = new JLabel("Управление товарами");
        subtitle.setFont(new Font("Arial", Font.PLAIN, 15));

        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.add(title);
        headerPanel.add(subtitle);

        mainPanel.add(headerPanel, BorderLayout.NORTH);


        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBorder(
                BorderFactory.createTitledBorder("Добавить товар")
        );

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;


        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(new JLabel("Название:"), gbc);

        nameField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        inputPanel.add(nameField, gbc);


        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        inputPanel.add(new JLabel("Количество:"), gbc);

        quantityField = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 1;
        inputPanel.add(quantityField, gbc);


        gbc.gridx = 2;
        gbc.gridy = 1;
        inputPanel.add(new JLabel("Цена:"), gbc);

        priceField = new JTextField(10);
        gbc.gridx = 3;
        gbc.gridy = 1;
        inputPanel.add(priceField, gbc);

        // Кнопка
        JButton addButton = new JButton("＋ Добавить товар");

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 4;

        inputPanel.add(addButton, gbc);


        String[] columns = {
                "№",
                "Товар",
                "Количество",
                "Цена",
                "Сумма"
        };

        tableModel = new DefaultTableModel(columns, 0);

        table = new JTable(tableModel);

        table.setRowHeight(30);
        table.getTableHeader().setFont(
                new Font("Arial", Font.BOLD, 14)
        );

        JScrollPane scrollPane = new JScrollPane(table);

        JPanel tablePanel = new JPanel(new BorderLayout(10, 10));

        JLabel listTitle = new JLabel("📋 Список товаров");
        listTitle.setFont(new Font("Arial", Font.BOLD, 18));

        tablePanel.add(listTitle, BorderLayout.NORTH);
        tablePanel.add(scrollPane, BorderLayout.CENTER);


        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        JLabel totalLabel = new JLabel(
                "💰 Общая стоимость: 0 сом"
        );

        totalLabel.setFont(
                new Font("Arial", Font.BOLD, 18)
        );

        bottomPanel.add(totalLabel);


        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout(15, 15));

        centerPanel.add(inputPanel, BorderLayout.NORTH);
        centerPanel.add(tablePanel, BorderLayout.CENTER);

        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        add(mainPanel);


        addButton.addActionListener(e -> {

            try {

                String name = nameField.getText();

                int quantity = Integer.parseInt(
                        quantityField.getText()
                );

                double price = Double.parseDouble(
                        priceField.getText()
                );

                Product product = new Product(
                        name,
                        quantity,
                        price
                );

                Main.products.add(product);

                double sum = quantity * price;

                tableModel.addRow(new Object[]{
                        tableModel.getRowCount() + 1,
                        product.getName(),
                        product.getQuantity(),
                        product.getPrice(),
                        sum
                });

                totalLabel.setText(
                        "💰 Общая стоимость: "
                                + Main.calculateTotal()
                                + " сом"
                );

                // Очистить поля
                nameField.setText("");
                quantityField.setText("");
                priceField.setText("");

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Введите правильное количество и цену!",
                        "Ошибка",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });
    }
}