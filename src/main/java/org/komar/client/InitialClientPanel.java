package org.komar.client;

import org.komar.server.ServerWindow;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;


public class InitialClientPanel extends JPanel {
    private JPanel jLoginPanel;
    private JPanel jTextPanel;

    private JTextArea jTextArea;
    private JScrollPane jScrollPane;
    private JTextField localPort;
    private JTextField port;
    private JTextField textLogin;
    private JPasswordField passwordField;
    private JButton loginBtn;

    public InitialClientPanel() {

        this.setLayout(new BorderLayout());
        this.jLoginPanel = new JPanel(new GridLayout(2, 3, 2, 2));
        this.jTextPanel = new JPanel(new GridLayout(1, 1));
        this.jTextPanel.setBorder(new EmptyBorder(5, 5, 3, 3));

        this.jTextArea = new JTextArea();
        this.jTextArea.setLineWrap(true);

        this.jScrollPane = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
        jScrollPane.setVerticalScrollBar(jScrollPane.getVerticalScrollBar());
        jScrollPane.setViewportView(jTextArea);

        this.jTextPanel.add(jScrollPane);

        this.localPort = new JTextField("127.0.0.1");
        this.port = new JTextField("8189");
        this.textLogin = new JTextField();
        this.textLogin.setToolTipText("Введите ваше имя...");
        this.passwordField = new JPasswordField();
        this.loginBtn = new JButton("Login");

        this.jLoginPanel.add(localPort);
        this.jLoginPanel.add(port);
        this.jLoginPanel.add(new JLabel());
        this.jLoginPanel.add(textLogin);
        this.jLoginPanel.add(passwordField);
        this.jLoginPanel.add(loginBtn);

        this.add(jLoginPanel, BorderLayout.NORTH);
        this.add(jTextPanel, BorderLayout.CENTER);

        this.setVisible(true);

    }
    public JButton getLoginBtn() {
        return loginBtn;
    }

    public JTextField getTextLogin() {
        return textLogin;
    }
}
