package org.komar.client;

import org.komar.server.ServerWindow;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ClientGUI extends JFrame implements ClientView, ActionListener {
    private static final int WIDTH = 300;
    private static final int HEIGHT = 507;

    private JPanel jMsgPanel;
    private JTextField jTextField;
    private JButton sendBtn;
    private JButton loginBtn;
    private JTextField textLogin;
    private JPanel jPanel;
    private JPanel jPanelLogin;
    private JPanel jLoginPanel;
    private JPanel jTextPanel;

    private JTextArea jTextArea;
    private JScrollPane jScrollPane;
    private JTextField localPort;
    private JTextField port;
    private JPasswordField passwordField;
    private ServerWindow serverWindow;
    private boolean isStart = false;

    public ClientGUI(ServerWindow serverWindow) {
        this.serverWindow = serverWindow;

        setting();
        createPanel();

        this.setVisible(true);
    }

    private void setting() {
        this.setSize(WIDTH, HEIGHT);
        this.setLocationRelativeTo(null);

        this.setTitle("Client chat");
        this.setResizable(false);
    }

    private void createPanel() {
        add(createHeaderPanel(), BorderLayout.NORTH);
        add(createLog(), BorderLayout.CENTER);
        add(createFooter(), BorderLayout.SOUTH);
    }


    private JPanel createHeaderPanel() {
        this.jLoginPanel = new JPanel(new GridLayout(2, 3, 2, 2));
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
        this.loginBtn.addActionListener(this);
        return jLoginPanel;
    }

    private JPanel createLog() {
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
        return jTextPanel;
    }

    private JPanel createFooter() {
        this.jMsgPanel = new JPanel();

        this.jTextField = new JTextField();
        jTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == '\n') {
                    message();
                }
            }
        });
        this.sendBtn = new JButton("Send");
        sendBtn.setEnabled(false);

        jMsgPanel.setLayout(new BoxLayout(jMsgPanel, BoxLayout.X_AXIS));
        jMsgPanel.add(Box.createHorizontalGlue());
        jMsgPanel.add(jTextField);
        jMsgPanel.add(Box.createHorizontalStrut(5));
        jMsgPanel.add(sendBtn);
        this.sendBtn.addActionListener(this);
        return jMsgPanel;
    }

    /**
     * Метод вывода текста на экран GUI. Вызывается из контроллера
     * @param msg текст, который требуется отобразить на экране
     */
    @Override
    public void showMessage(String msg) {
        log.append(msg + "\n");
    }

    /**
     * Метод, описывающий отключение клиента от сервера со стороны сервера
     */
    @Override
    public void disconnectedFromServer(){
        hideHeaderPanel(true);
    }

    /**
     * Метод, описывающий отключение клиента от сервера со стороны клиента
     */
    public void disconnectFromServer(){
        clientController.disconnectFromServer();
    }

    /**
     * Метод изменения видимости верхней панели экрана, на которой виджеты для авторизации (например кнопка логин)
     * @param visible true, если надо сделать панель видимой
     */
    public void hideHeaderPanel(boolean visible){
        headerPanel.setVisible(visible);
    }

    /**
     * Метод, срабатывающий при нажатии кнопки авторизации
     */
    public void login(){
        if (clientController.connectToServer(tfLogin.getText())){
            headerPanel.setVisible(false);
        }
    }

    /**
     * Метод для отправки сообщения. Используется при нажатии на кнопку send
     */
    private void message(){
        clientController.message(tfMessage.getText());
        tfMessage.setText("");
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == loginBtn) {
            if (serverWindow.isStart()) {
                if (!textLogin.getText().trim().isEmpty()) {
                    serverWindow.sendMsgOnServer("Клиент " + textLogin.getText() + " подключился");
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
                    jTextArea.setText(serverWindow.initMsg());
                }
            } else {
                JOptionPane.showMessageDialog(null, "Сервер не запущен", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void disconnectedFromServer() {

    }
}
