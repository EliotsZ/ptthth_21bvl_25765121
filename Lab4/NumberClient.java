package Lab4;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class NumberClient {

    public static void main(String[] args) throws Exception {

        Socket socket = new Socket("localhost", 5000);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(socket.getInputStream(), "UTF-8"));

        PrintWriter out = new PrintWriter(
                new OutputStreamWriter(socket.getOutputStream(), "UTF-8"), true);

        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.print("Nhap so: ");
            String data = sc.nextLine();

            out.println(data);

            if (data.equals("QUIT")) {
                break;
            }

            String result = in.readLine();

            System.out.println("Server: " + result);
        }

        socket.close();
    }
}
