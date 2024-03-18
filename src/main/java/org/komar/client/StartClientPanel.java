package org.komar.client;

import org.komar.server.ServerWindow;

import javax.swing.*;
import java.awt.*;

public class StartClientPanel extends JPanel {
    private JTextArea jTextArea;
    private  JScrollPane jScrollPane;
    private ServerWindow serverWindow;
    public StartClientPanel(ServerWindow serverWindow){
        this.serverWindow = serverWindow;

        this.setLayout(new GridLayout(1,1));

        this.jTextArea = new JTextArea();
        this.jTextArea.setLineWrap(true);

        this.jScrollPane = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
        jScrollPane.setVerticalScrollBar(jScrollPane.getVerticalScrollBar());
        jScrollPane.setViewportView(jTextArea);

        this.add(jScrollPane, BorderLayout.CENTER);

        setVisible(true);
        
        initPanel();
    }

    private void initPanel() {
        jTextArea.setText(serverWindow.initMsg());
        repaint();
    }

    public JTextArea getJTextArea() {
        return jTextArea;
    }
}
