/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.cajerogui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CajeroGUI extends JFrame {
    private JTextField balanceField;
    private JTextField depositField;
    private JTextField withdrawField;
    private JPasswordField passwordField;

    private int balance = 0;
    private final String password = "1234"; // Clave fija

    public CajeroGUI() {
        setTitle("Cajero Automático");
        setSize(300, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear los componentes de la interfaz
        JLabel balanceLabel = new JLabel("Saldo: ");
        balanceField = new JTextField(10);
        balanceField.setEditable(false);

        JLabel passwordLabel = new JLabel("Clave: ");
        passwordField = new JPasswordField(10);

        JLabel depositLabel = new JLabel("Depositar: ");
        depositField = new JTextField(10);
        JButton depositButton = new JButton("Depositar");

        JLabel withdrawLabel = new JLabel("Retirar: ");
        withdrawField = new JTextField(10);
        JButton withdrawButton = new JButton("Retirar");

        // Crear el panel y añadir los componentes
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2));
        panel.add(balanceLabel);
        panel.add(balanceField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(depositLabel);
        panel.add(depositField);
        panel.add(withdrawLabel);
        panel.add(withdrawField);
        panel.add(depositButton);
        panel.add(withdrawButton);

        // Añadir el panel al frame
        add(panel);

        // Colocar los botones de "Depositar" y "Retirar" junto a los campos correspondientes
        panel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        panel.add(depositLabel, constraints);
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        panel.add(depositField, constraints);
        constraints.gridx = 2;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        panel.add(depositButton, constraints);
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 1;
        panel.add(withdrawLabel, constraints);
        constraints.gridx = 1;
        constraints.gridy = 3;
        constraints.gridwidth = 1;
        panel.add(withdrawField, constraints);
        constraints.gridx = 2;
        constraints.gridy = 3;
        constraints.gridwidth = 1;
        panel.add(withdrawButton, constraints);

        setVisible(true);
        
        depositButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deposit();
            }
        });
        
        withdrawButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                withdraw();
            }
        });
    }

    private void deposit() {
        String inputPassword = new String(passwordField.getPassword());
        if (inputPassword.equals(password)) {
            int amount = Integer.parseInt(depositField.getText());
            balance += amount;
            updateBalance();
            depositField.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Clave incorrecta");
        }
        passwordField.setText("");
    }

    private void withdraw() {
        String inputPassword = new String(passwordField.getPassword());
        if (inputPassword.equals(password)) {
            int amount = Integer.parseInt(withdrawField.getText());
            if (amount <= balance) {
                balance -= amount;
                updateBalance();
            } else {
                JOptionPane.showMessageDialog(this, "Fondos insuficientes");
            }
            withdrawField.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Clave incorrecta");
        }
        passwordField.setText("");
    }

    private void updateBalance() {
        balanceField.setText(Integer.toString(balance));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new CajeroGUI().setVisible(true);
            }
        });
    }
}
