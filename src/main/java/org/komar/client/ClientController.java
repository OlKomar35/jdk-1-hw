package org.komar.client;

public class ClientController {
    private boolean connected;
    private String name;
    private ClientView clientView;
    private ServerController server;

    //�������
    public void setClientView(ClientView clientView) {
        this.clientView = clientView;
    }

    public void setServer(ServerController server) {
        this.server = server;
    }

    /**
     * ����� ������� ����������� � �������. ���������� �� GUI
     * @param name ��� ������������, ������� ����� ����������� ��������� ���������
     * @return ����� �� �������. true, ���� ������ �����������
     */
    public boolean connectToServer(String name) {
        this.name = name;
        if (server.connectUser(this)){
            showOnWindow("�� ������� ������������!\n");
            connected = true;
            String log = server.getHistory();
            if (log != null){
                showOnWindow(log);
            }
            return true;
        } else {
            showOnWindow("����������� �� �������");
            return false;
        }
    }

    /**
     * ����� ���������� �� ������� ������������������ ��������
     */
    public void disconnectedFromServer() {
        if (connected) {
            connected = false;
            clientView.disconnectedFromServer();
            showOnWindow("�� ���� ��������� �� �������!");
        }
    }

    /**
     * ����� ���������� �� ������� ������������������ �������� (�������� ������� GUI)
     */
    public void disconnectFromServer() {
        server.disconnectUser(this);
    }

    /**
     * �����, � ������� �������� ������ �������� ������� ���������
     * @param text ����� ���������� �� �������
     */
    public void answerFromServer(String text) {
        showOnWindow(text);
    }

    /**
     * ����� ��� �������� ��������� �� ������
     * @param text ����� ������������� ���������
     */
    public void message(String text) {
        if (connected) {
            if (!text.isEmpty()) {
                server.message(name + ": " + text);
            }
        } else {
            showOnWindow("��� ����������� � �������");
        }
    }

    /**
     * ����� ������ ��������� �� GUI
     * @param text �����, ������� ��������� ������� �� �����
     */
    private void showOnWindow(String text) {
        clientView.showMessage(text);
    }
}
