package main.lab2.proxi.server;

public class Main {
    public static void main(String[] args) {
        Server server = new Server();
        server.startServer(5000);
        while (true) {
            if(!server.hasConnection()) {
                server.waitConnection();
            }
            String message = server.readMessage();
            try {
                String[] command = message.split(" ");
                if (command.length == 0) {
                    throw new RuntimeException("Ошибка в синтаксисе команды");
                }
                server.writeMessage(String.valueOf(Double.parseDouble(command[0]) * Double.parseDouble(command[1])));
            } catch (RuntimeException e) {
                server.writeMessage(e.getMessage());
            }
        }
    }
}
