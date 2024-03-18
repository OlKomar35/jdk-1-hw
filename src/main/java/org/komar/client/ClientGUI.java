package org.komar.client;

import org.komar.server.ServerWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientGUI extends JFrame implements ActionListener {
    private static final int WIDTH = 300;
    private static final int HEIGHT = 507;
    private JPanel jMsgPanel;

    private JTextField jTextField;
    private JButton sendBtn;
    private JButton loginBtn;
    private JTextField textLogin;
    private JPanel jPanel;
    private CardLayout cardLayout;
    private JTextArea textArea;
    private ServerWindow serverWindow;
    private boolean isStart = false;

    public ClientGUI(ServerWindow serverWindow) {
        this.serverWindow = serverWindow;

        this.setSize(WIDTH, HEIGHT);
        this.setLocationRelativeTo(null);
        this.cardLayout = new CardLayout();

        this.setTitle("Client chat");
        this.setResizable(false);

        this.jPanel = new JPanel(cardLayout);
        InitialClientPanel icp = new InitialClientPanel();
        jPanel.add(icp);
        this.loginBtn = icp.getLoginBtn();
        this.textLogin = icp.getTextLogin();
        this.add(jPanel, BorderLayout.CENTER);

        this.jMsgPanel = new JPanel();

        this.jTextField = new JTextField();
        this.sendBtn = new JButton("Send");
        sendBtn.setEnabled(false);

        jMsgPanel.setLayout(new BoxLayout(jMsgPanel, BoxLayout.X_AXIS));
        jMsgPanel.add(Box.createHorizontalGlue());
        jMsgPanel.add(jTextField);
        jMsgPanel.add(Box.createHorizontalStrut(5));
        jMsgPanel.add(sendBtn);
        this.add(jMsgPanel, BorderLayout.SOUTH);

        this.sendBtn.addActionListener(this);
        this.loginBtn.addActionListener(this);

        this.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == loginBtn) {
            if (serverWindow.isStart()) {
                if (!textLogin.getText().trim().isEmpty()) {
                    serverWindow.sendMsgOnServer("Клиент " + textLogin.getText() + " подключился");
                    StartClientPanel scp = new StartClientPanel(serverWindow);
                    textArea = scp.getJTextArea();
                    this.jPanel.add(scp);
                    cardLayout.next(jPanel);
                    sendBtn.setEnabled(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Введите логин", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Сервер не запущен", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (e.getSource() == sendBtn) {
            if (serverWindow.isStart()) {
                if (!jTextField.getText().trim().isEmpty()) {
                    serverWindow.sendMsgOnServer(textLogin.getText()+": "+jTextField.getText());
                    textArea.setText(serverWindow.initMsg());
                }
            } else {
                JOptionPane.showMessageDialog(null, "Сервер не запущен", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

    }
}
