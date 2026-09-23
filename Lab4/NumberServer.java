package Lab4;

import java.io.*;
import java.net.*;

public class NumberServer {

    public static void main(String[] args) throws Exception {

        ServerSocket server = new ServerSocket(5000);

        System.out.println("Server dang chay...");

        Socket socket = server.accept();

        BufferedReader in = new BufferedReader(
                new InputStreamReader(socket.getInputStream(), "UTF-8"));

        PrintWriter out = new PrintWriter(
                new OutputStreamWriter(socket.getOutputStream(), "UTF-8"), true);

        String data;

        while ((data = in.readLine()) != null) {

            if (data.equals("QUIT")) {
                break;
            }

            String result;

            switch (data) {
                case "0":
                    result = "không";
                    break;

                case "1":
                    result = "một";
                    break;

                case "2":
                    result = "hai";
                    break;

                case "3":
                    result = "ba";
                    break;

                case "4":
                    result = "bốn";
                    break;

                case "5":
                    result = "năm";
                    break;

                case "6":
                    result = "sáu";
                    break;

                case "7":
                    result = "bảy";
                    break;

                case "8":
                    result = "tám";
                    break;

                case "9":
                    result = "chín";
                    break;

                default:
                    result = "ERR INVALID_DIGIT";
            }

            out.println(result);
        }

        socket.close();
        server.close();
    }
}