package models.gui;

import models.Message;
import models.chatClients.ChatClient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MainFrame extends JFrame {
    ChatClient chatClient;

    JTextField textInputName, textInputMessage;
    JTextArea textChat;

    JPanel panelMain;

    public MainFrame(int width, int height, ChatClient chatClient) {
        super.setTitle("Chat Client");
        setSize(width, height);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.chatClient = chatClient;

        initGui();
        setVisible(true);
        closeWindow();
    }

    private void initGui() {
        panelMain = new JPanel(new BorderLayout());

        panelMain.add(initLoginPanel(), BorderLayout.NORTH);
        panelMain.add(initLoggedUsersPanel(), BorderLayout.EAST);
        panelMain.add(initChatPanel(), BorderLayout.CENTER);
        panelMain.add(initPanelMessage(), BorderLayout.SOUTH);

        add(panelMain);
    }

    private JPanel initLoginPanel() {
        JPanel panelLogin = new JPanel((new FlowLayout(FlowLayout.LEFT)));
        panelLogin.add(new JLabel("Username"));

        textInputName = new JTextField("", 30);
        panelLogin.add(textInputName);

        JButton btnLogin;
        btnLogin = new JButton("Login");

        btnLogin.addActionListener(e -> {
            System.out.println("Login clicked - " + textInputName.getText());

            if (chatClient.isAuthenticated()) {
                chatClient.logout();
                btnLogin.setText("Login");
                textInputName.setEditable(true);
                textInputMessage.setEnabled(false);
                textChat.setEnabled(false);
            } else {
                String userName = textInputName.getText();
                if (userName.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Enter your username", "Error", JOptionPane.ERROR_MESSAGE);
                    textInputName.setText("");
                    return;
                }
                chatClient.login(textInputName.getText());
                btnLogin.setText("Logout");
                textInputName.setText("");
                textInputName.setEditable(false);
                textInputMessage.setEnabled(true);
                textChat.setEnabled(true);
            }
        });
        panelLogin.add(btnLogin);

        return panelLogin;
    }

    private JPanel initChatPanel() {
        JPanel panelChat = new JPanel();
        panelChat.setLayout(new BoxLayout(panelChat, BoxLayout.X_AXIS));

        textChat = new JTextArea();
        textChat.setEditable(false);
        textChat.setEnabled(false);

        JScrollPane scrollPane = new JScrollPane(textChat);
        panelChat.add(scrollPane);

        chatClient.addActionListener(e -> {
            if (e.getID() == 2)
                refreshMessages();
        });

        return panelChat;
    }

    private JPanel initPanelMessage() {
        JPanel panelMessage = new JPanel(new FlowLayout());

        textInputMessage = new JTextField("", 50);
        textInputMessage.setEnabled(false);

        panelMessage.add(textInputMessage);

        JButton btnSend = new JButton("Odeslat");
        btnSend.addActionListener(e -> {
            String messageText = textInputMessage.getText();
            System.out.println("btnSendClick " + messageText);
            chatClient.sendMessage(messageText);

            if (messageText.trim().isEmpty()) {
                textInputMessage.setText("");
                return;
            }

            if (!chatClient.isAuthenticated()) {
                textInputMessage.setText("");
                return;
            }

            textInputMessage.setText("");
        });
        panelMessage.add(btnSend);

        return panelMessage;
    }

    private JPanel initLoggedUsersPanel() {
        JPanel panel = new JPanel();

        JTable tblLoggedUsers = new JTable();

        LoggedUsersTableModel loggedUsersTableModel = new LoggedUsersTableModel(chatClient);
        tblLoggedUsers.setModel(loggedUsersTableModel);

        chatClient.addActionListener(e -> {
            if (e.getID() == 1)
                loggedUsersTableModel.fireTableDataChanged();
        });

        JScrollPane scrollpane = new JScrollPane(tblLoggedUsers);
        scrollpane.setPreferredSize(new Dimension(250, 500));

        panel.add(scrollpane);
        return panel;
    }

    private void refreshMessages() {
        if (!chatClient.isAuthenticated())
            return;

        textChat.setText("");
        for (Message mes : chatClient.getMessages()) {
            textChat.append(mes.toString() + "\n");
        }
    }

    private void closeWindow(){
        panelMain.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE){
                    System.out.println("exit");
                    System.exit(0);
                }
            }
        });
    }

}
