package models.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    JTextField textInputName, textInputMessage;
    JTextArea textChat;

    public MainFrame(int width, int height) {
        super.setTitle("Chat Client");
        setSize(width, height);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        initGui();
        setVisible(true);
    }

    private void initGui() {
        JPanel panelMain = new JPanel(new BorderLayout());

        panelMain.add(initLoginPanel(), BorderLayout.NORTH);
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
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Login clicked - " + textInputName.getText());
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

        JScrollPane scrollPane = new JScrollPane(textChat);
        panelChat.add(scrollPane);

        return panelChat;
    }

    private JPanel initPanelMessage() {
        JPanel panelMessage = new JPanel(new FlowLayout());

        textInputMessage = new JTextField("", 50);
        panelMessage.add(textInputMessage);

        JButton btnSend = new JButton("Odeslat");
        btnSend.addActionListener(e -> {
            System.out.println("btnSendClick " + textInputMessage.getText());
            textChat.append(textInputMessage.getText() + "\n");
            textInputMessage.setText("");
        });
        panelMessage.add(btnSend);

        return panelMessage;
    }

}
