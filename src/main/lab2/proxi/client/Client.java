package main.lab2.proxi.client;

import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Proxy proxy = new Proxy();
        proxy.open("localhost", 5000);
        Scanner in = new Scanner(System.in);
        while (true) {
            if (proxy.hasConnection()) {
                System.out.print("Введите числа для умножения: ");
                String message = in.nextLine();
                String result;
                proxy.write(message);
                result = proxy.read();
                System.out.println(result);
            }
        }
    }
}
