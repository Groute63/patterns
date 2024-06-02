package main.lab2.proxi.client;

import java.io.*;
import java.net.Socket;

public class Proxy {
    private Socket socket;
    private BufferedReader in;
    private BufferedWriter out;
    public void open(String host, int port) {
        try {
            this.socket = new Socket(host, port);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException ex) {
            System.err.println("Ошибка при запуске клиента: " + ex.getMessage());
        }
        System.out.println("Клиент запущен");
    }

    public String read() {
        if (!hasConnection()) {
            System.err.println("Отсутствует подключение с сервером");
        }
        try {
            return in.readLine();
        } catch (IOException ex) {
            System.err.println("При чтении сообщения произошла ошибка: " + ex.getMessage());
        }
        return "";
    }

    public void write(String message) {
        if (!hasConnection()) {
            System.err.println("Отсутствует подключение с сервером");
        }
        try {
            out.write(message + "\n");
            out.flush();
        } catch (IOException ex) {
            System.err.println("При отправке сообщения произошла ошибка: " + ex.getMessage());
        }
    }

    public boolean hasConnection() {
        return socket != null && socket.isConnected();
    }

    public void close() {
        try {
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            System.err.println("Не удалось остановить клиент");
        }
    }
}
