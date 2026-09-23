package Lab4;

import java.io.*;
import java.net.*;

public class CalcServer {

    public static void main(String[] args) throws Exception {

        ServerSocket server = new ServerSocket(5000);

        System.out.println("Calc Server dang chay...");

        Socket socket = server.accept();

        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        socket.getInputStream(), "UTF-8"));

        PrintWriter out = new PrintWriter(
                new OutputStreamWriter(
                        socket.getOutputStream(), "UTF-8"), true);

        String request;

        while ((request = in.readLine()) != null) {

            // Tach du lieu
            String[] parts = request.split(" ");

            // Kiem tra du 4 phan
            if (parts.length != 4 ||
                    !parts[0].equals("CALC")) {

                out.println("ERR INVALID_FORMAT");
                continue;
            }

            String operator = parts[1];

            double a;
            double b;

            // Chuyen chuoi thanh so
            try {

                a = Double.parseDouble(parts[2]);
                b = Double.parseDouble(parts[3]);

            } catch (Exception e) {

                out.println("ERR INVALID_NUMBER");
                continue;
            }

            double result;

            // Tinh toan
            if (operator.equals("+")) {

                result = a + b;

            } else if (operator.equals("-")) {

                result = a - b;

            } else if (operator.equals("*")) {

                result = a * b;

            } else if (operator.equals("/")) {

                if (b == 0) {
                    out.println("ERR DIVIDE_BY_ZERO");
                    continue;
                }

                result = a / b;

            } else {

                out.println("ERR UNSUPPORTED_OPERATOR");
                continue;
            }

            // Tra ket qua
            out.println("OK " + result);
        }

        socket.close();
        server.close();
    }
}