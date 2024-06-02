package main.lab2.proxi.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private Socket clientSocket;
    private ServerSocket server;
    private BufferedReader in;
    private BufferedWriter out;

    public void startServer(int port) {
        try {
            this.server = new ServerSocket(port);
        } catch (IOException ex) {
            System.err.println("Ошибка при запуске сервера: " + ex.getMessage());
        }
        System.out.println("Сервер запущен");
    }

    public void waitConnection() {
        try {
            clientSocket = server.accept();
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
        } catch (IOException ex) {
            System.err.println("Ошибка соединения с клиентом: " + ex.getMessage());
        }
    }

    public String readMessage() {
        if (!hasConnection()) {
            System.err.println("Отсутствует подключение с клиентом");
        }
        try {
            return in.readLine();
        } catch (IOException ex) {
            System.err.println("При чтении сообщения произошла ошибка: " + ex.getMessage());
        }
        return "";
    }
    public void writeMessage(String message) {
        if (!hasConnection()) {
            System.err.println("Отсутствует подключение с клиентом");
        }
        try {
            out.write(message + "\n");
            out.flush();
        } catch (IOException ex) {
            System.err.println("При отправке сообщения произошла ошибка: " + ex.getMessage());
        }
    }

    public boolean hasConnection() {
        return clientSocket != null && clientSocket.isConnected();
    }

    public void closeServer() {
        try {
            clientSocket.close();
            in.close();
            out.close();
            server.close();
        } catch (IOException ex) {
            System.err.println("Не удалось остановить сервер");
        }
    }
}
