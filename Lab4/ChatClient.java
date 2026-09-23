package Lab4;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ChatClient {

    public static void main(String[] args) throws Exception {

        Socket socket = new Socket("localhost", 5000);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        socket.getInputStream(), "UTF-8"));

        PrintWriter out = new PrintWriter(
                new OutputStreamWriter(
                        socket.getOutputStream(), "UTF-8"), true);

        Scanner sc = new Scanner(System.in);

        // Nhap nickname
        System.out.println(in.readLine());

        String nickname = sc.nextLine();

        out.println(nickname);

        String result = in.readLine();

        System.out.println(result);

        // Neu nickname bi trung thi thoat
        if (result.equals("Nickname da ton tai!")) {
            socket.close();
            return;
        }

        // Tao thread de nhan tin nhan
        Thread receive = new Thread(() -> {

            try {

                String message;

                while ((message = in.readLine()) != null) {

                    System.out.println();
                    System.out.println(message);
                    System.out.print("> ");
                }

            } catch (Exception e) {

                System.out.println("Server da ngat ket noi.");
            }
        });

        receive.start();

        // Gui lenh
        while (true) {

            String message = sc.nextLine();

            out.println(message);

            if (message.equals("QUIT")) {
                break;
            }
        }

        socket.close();
    }
}