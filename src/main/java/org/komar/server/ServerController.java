package org.komar.server;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.komar.client.ClientController;
import org.komar.client.ClientGUI;

public class ServerController {

  private List<ClientGUI> clientGUIList;
  private boolean work;
  private ServerView serverView;

  private File file;
  private FileOutputStream fileOutputStream;
  private FileInputStream fileInputStream;


  private String filePath = "log_chat.txt";

  public ServerController() {
    clientGUIList = new ArrayList<>();

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

  public void setServerView(ServerView serverView) {
    this.serverView = serverView;
  }

  public boolean connectUser(ClientGUI clientGUI) {
    if (!serverView.isActiveStartButton()) {
      return false;
    }
    clientGUIList.add(clientGUI);
    return true;
  }

  public String getHistory() {
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

  public void disconnectUser(ClientController clientController) {
  }

  public void message(String text){
    if (!serverView.isActiveStartButton()){
      return;
    }
    appendLog(text);
    answerAll(text);
   // saveInLog(text);
  }

  public void appendLog(String msg) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
      writer.write(msg);
      writer.newLine();
      serverView.getJTextArea().setForeground(Color.BLACK);
      serverView.getJTextArea().append("\n");
      serverView.getJTextArea().append(msg);
      System.out.println("Сообщение успешно добавлен в файл.");
    } catch (IOException e) {
      System.err.println("Ошибка при записи в файл: " + e.getMessage());
    }
  }


  private void answerAll(String text){
    for (ClientGUI clientGUI: clientGUIList){
      clientGUI.answer(text);
    }
  }
}
