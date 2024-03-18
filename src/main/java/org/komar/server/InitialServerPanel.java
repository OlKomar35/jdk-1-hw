package org.komar.server;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InitialServerPanel extends JPanel implements ActionListener {
    private JPanel jTextPanel;
    private JPanel jBtnPanel;
    private JButton startBtn;
    private JButton stopBtn;
    private JTextArea jTextArea;
    private  JScrollPane jScrollPane;

    private boolean isStart = false;
    private ServerWindow serverWindow;

    public InitialServerPanel(ServerWindow serverWindow) {
        this.serverWindow = serverWindow;

        this.setLayout(new BorderLayout());
        this.jTextPanel = new JPanel(new GridLayout(1,1));
        this.jTextPanel.setBorder(new EmptyBorder(5,5,3,3));
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
        if(e.getSource() == startBtn){
            jTextArea.removeAll();
            String startMsg = "Сервер запущен!";
            jTextArea.setText(startMsg);
            jTextArea.setForeground(Color.RED);
            jTextPanel.repaint();

            serverWindow.activeStartButton();
        }
        if(e.getSource() == stopBtn){
            jTextArea.removeAll();
            String stopMsg = "Сервер остановлен!";
            jTextArea.setText(stopMsg);
            jTextArea.setForeground(Color.BLUE);
            jTextPanel.repaint();

             serverWindow.activeStopButton();
        }
    }

    public JTextArea getJTextArea() {
        return jTextArea;
    }
}
