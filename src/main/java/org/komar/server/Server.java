package org.komar.server;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Server extends JFrame implements ServerView, ActionListener {

  private static final int WIDTH = 300;
  private static final int HEIGHT = 507;

  private JPanel jTextPanel;
  private JPanel jBtnPanel;
  private JButton startBtn;
  private JButton stopBtn;
  private JTextArea jTextArea;
  private JScrollPane jScrollPane;

  private ServerController serverController;
  private boolean isStart = false;

  public Server() {
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.setSize(WIDTH, HEIGHT);
    this.setLocationRelativeTo(null);

    this.setTitle("Server chat");
    this.setResizable(false);

    this.setLayout(new BorderLayout());
    this.jTextPanel = new JPanel(new GridLayout(1, 1));
    this.jTextPanel.setBorder(new EmptyBorder(5, 5, 3, 3));
    this.jBtnPanel = new JPanel(new GridLayout(1, 2));

    this.startBtn = new JButton("Start");
    this.stopBtn = new JButton("Stop");

    this.jBtnPanel.add(startBtn);
    this.jBtnPanel.add(stopBtn);

    this.jTextArea = new JTextArea();
    this.jTextArea.setText("Сервер не запущен!");
    this.jTextArea.setLineWrap(true);

    this.jScrollPane = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                                       ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    jScrollPane.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
    jScrollPane.setVerticalScrollBar(jScrollPane.getVerticalScrollBar());
    jScrollPane.setViewportView(jTextArea);

    this.jTextPanel.add(jScrollPane);

    this.add(jTextPanel, BorderLayout.CENTER);
    this.add(jBtnPanel, BorderLayout.SOUTH);

    this.setVisible(true);

    this.startBtn.addActionListener(this);
    this.stopBtn.addActionListener(this);
  }


  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == startBtn) {
      jTextArea.removeAll();
      String startMsg = "Сервер запущен!";
      jTextArea.setText(startMsg);
      jTextArea.setForeground(Color.RED);
      jTextPanel.repaint();
      isStart = true;
    }
    if (e.getSource() == stopBtn) {
      jTextArea.removeAll();
      String stopMsg = "Сервер остановлен!";
      jTextArea.setText(stopMsg);
      jTextArea.setForeground(Color.BLUE);
      jTextPanel.repaint();
      isStart = false;
    }
  }

  @Override
  public JTextArea getJTextArea() {
    return jTextArea;
  }

  public void setServerController(ServerController serverController) {
    this.serverController = serverController;
  }

  @Override
  public boolean isActiveStartButton() {
    return isStart;
  }
}
