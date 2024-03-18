package org.komar;

import org.komar.client.ClientGUI;
import org.komar.server.ServerWindow;

public class Program {
    public static void main(String[] args) {
        ServerWindow serverWindow = new ServerWindow();
        new ClientGUI(serverWindow);
        new ClientGUI(serverWindow);
    }
}
