package org.komar;

import org.komar.client.ClientController;
import org.komar.client.ClientGUI;
import org.komar.server.Server;
import org.komar.server.ServerController;

public class Program {
    public static void main(String[] args) {
        //создание объектов сервера и создание связи между ними
        Server serverWindow = new Server();
        ServerController serverController = new ServerController();
        serverController.setServerView(serverWindow);
        serverWindow.setServerController(serverController);

        //создание объектов клиента1 и создание связи между ними
        ClientGUI clientGUI1 = new ClientGUI();
        ClientController clientController1 = new ClientController();
        clientController1.setClientView(clientGUI1);
        clientGUI1.setClient(clientController1);
        clientController1.setServer(serverController);

        //создание объектов клиента2 и создание связи между ними
        ClientGUI clientGUI2 = new ClientGUI();
        ClientController clientController2 = new ClientController();
        clientController2.setClientView(clientGUI2);
        clientGUI2.setClient(clientController2);
        clientController2.setServer(serverController);
    }
}
