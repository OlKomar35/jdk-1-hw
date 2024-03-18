package org.komar.server;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class ServerWindow extends JFrame  implements ButtonActivationListener {
    private static final int WIDTH = 300;
    private static final int HEIGHT = 507;
    private  File file;
    private FileOutputStream fileOutputStream;
    private FileInputStream fileInputStream;
    private JTextArea jTextArea;

    String filePath = "log_chat.txt";

    public boolean isStart() {
        return isStart;
    }

    private boolean isStart;
    public ServerWindow(){
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(WIDTH, HEIGHT);
        this.setLocationRelativeTo(null);

        this.setTitle("Server chat");
        this.setResizable(false);

        InitialServerPanel initialServerPanel = new InitialServerPanel(this);
        jTextArea = initialServerPanel.getJTextArea();
        this.add(initialServerPanel);

        this.setVisible(true);

        file = new File(filePath);

        try {
            if (!file.exists()) {
                file.createNewFile();
                System.out.println("Файл создан: " + filePath);
            }

            fileInputStream = new FileInputStream(file);

            fileOutputStream = new FileOutputStream(file, true);

            fileInputStream.close();
            fileOutputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void activeStartButton() {
        isStart = true;
    }

    @Override
    public void activeStopButton() {
        isStart = false;
    }

    public void sendMsgOnServer(String msg){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(msg);
            writer.newLine();
            jTextArea.setForeground(Color.BLACK);
            jTextArea.append("\n");
            jTextArea.append(msg);
            System.out.println("Сообщение успешно добавлен в файл.");
        } catch (IOException e) {
            System.err.println("Ошибка при записи в файл: " + e.getMessage());
        }
    }

    public String initMsg() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            StringBuilder content = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                content.append(line).append('\n');
            }

            return content.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
