package org.komar.server;

import javax.swing.JTextArea;

public interface ServerView {
    boolean isActiveStartButton();
    JTextArea getJTextArea();
}
