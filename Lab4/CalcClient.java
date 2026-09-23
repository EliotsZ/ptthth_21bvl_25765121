package Lab4;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class CalcClient {

    public static void main(String[] args) throws Exception {

        Socket socket = new Socket("localhost", 5000);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        socket.getInputStream(), "UTF-8"));

        PrintWriter out = new PrintWriter(
                new OutputStreamWriter(
                        socket.getOutputStream(), "UTF-8"), true);

        Scanner sc = new Scanner(System.in);

        System.out.println("Nhap lenh CALC:");

        while (true) {

            System.out.print("> ");

            String request = sc.nextLine();

            out.println(request);

            String response = in.readLine();

            System.out.println(response);

            // Thoat
            if (request.equals("QUIT")) {
                break;
            }
        }

        socket.close();
    }
}
