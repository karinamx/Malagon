/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.chat;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.vdurmont.emoji.EmojiParser; // Importar la clase EmojiParser de la biblioteca Emoji-Java

public class Chat extends JFrame {
    private JTextArea chatTextArea;
    private JTextField messageField;
    private String user1Name;
    private String user2Name;
    private JComboBox<String> userSelector;

    public Chat(String user1Name, String user2Name) {
        this.user1Name = user1Name;
        this.user2Name = user2Name;

        setTitle("ChatApp");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Setup components
        chatTextArea = new JTextArea();
        chatTextArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(chatTextArea);
        messageField = new JTextField(30);

        String[] users = {user1Name, user2Name};
        userSelector = new JComboBox<>(users);

        JButton sendButton = new JButton("Enviar");
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });

        JButton emojiButton = new JButton("Emojis");
        emojiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showEmojisDialog();
            }
        });

        // Layout
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);

        JPanel inputPanel = new JPanel();
        inputPanel.add(userSelector);
        inputPanel.add(messageField);
        inputPanel.add(sendButton);
        inputPanel.add(emojiButton);
        add(inputPanel, BorderLayout.SOUTH);

        createMenuBar();

        setVisible(true);
    }

    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu userMenu = new JMenu("Usuario");
        JMenuItem user1MenuItem = new JMenuItem(user1Name);
        JMenuItem user2MenuItem = new JMenuItem(user2Name);

        user1MenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userSelector.setSelectedItem(user1Name);
            }
        });

        user2MenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userSelector.setSelectedItem(user2Name);
            }
        });

        userMenu.add(user1MenuItem);
        userMenu.add(user2MenuItem);
        menuBar.add(userMenu);
        setJMenuBar(menuBar);
    }

    private void sendMessage() {
        String user = (String) userSelector.getSelectedItem();
        String message = messageField.getText();
        if (!message.isEmpty()) {
            // Procesar el texto del mensaje para reemplazar los emojis con sus representaciones Unicode
            String processedMessage = EmojiParser.parseToUnicode(message);
            chatTextArea.append(user + ": " + processedMessage + "\n");
            messageField.setText("");
        }
    }

    private void showEmojisDialog() {
        JDialog emojiDialog = new JDialog(this, "Emojis", true);
        emojiDialog.setSize(300, 300);
        emojiDialog.setLocationRelativeTo(this);

        String[] emojis = {":)", "<3", ":c", "( ͡❛ ‿‿ ͡❛)","( ͡❛ ⏥ ͡❛)", "( ᵔ︡ ⏥ ᵔ︠ )","¯\\_( °︡ ⏥ °︠ )_/¯","(っ ^︡ ⏥ ^︠ )っ🎔","ლ( ◕ ⏥ ◕ )ლ","(҂ ◕ ⏥ ◕ )9","凸 ( ㆆ ⏥ ㆆ )凸","⊂( * U* )⊃","⊂( * ︵ * )⊃","*︣ ▿ *᷅"};
        JList<String> emojiList = new JList<>(emojis);
        emojiList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        emojiList.setLayoutOrientation(JList.VERTICAL);

        emojiList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                String selectedEmoji = emojiList.getSelectedValue();
                if (selectedEmoji != null) {
                    messageField.setText(messageField.getText() + selectedEmoji);
                }
                emojiDialog.dispose();
            }
        });

        JScrollPane scrollPane = new JScrollPane(emojiList);
        emojiDialog.add(scrollPane);

        emojiDialog.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            String user1Name = JOptionPane.showInputDialog(null, "Ingrese el nombre del Usuario 1:", "Usuario 1", JOptionPane.PLAIN_MESSAGE);
            if (user1Name == null || user1Name.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Debe ingresar el nombre del Usuario 1 para iniciar el chat.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                String user2Name = JOptionPane.showInputDialog(null, "Ingrese el nombre del Usuario 2:", "Usuario 2", JOptionPane.PLAIN_MESSAGE);
                if (user2Name == null || user2Name.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Debe ingresar el nombre del Usuario 2 para iniciar el chat.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    new Chat(user1Name, user2Name);
                }
            }
        });
    }
}
