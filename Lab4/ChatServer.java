package Lab4;

import java.io.*;
import java.net.*;
import java.util.concurrent.*;

public class ChatServer {

    // Tao thread pool toi da 10 client
    static ExecutorService pool = Executors.newFixedThreadPool(10);

    // Luu nickname va PrintWriter cua client
    static ConcurrentHashMap<String, PrintWriter> clients =
            new ConcurrentHashMap<>();

    public static void main(String[] args) throws Exception {

        ServerSocket server = new ServerSocket(5000);

        System.out.println("Chat Server dang chay...");

        while (true) {

            // Cho client ket noi
            Socket socket = server.accept();

            System.out.println("Co client ket noi!");

            // Tao thread xu ly client
            pool.execute(new ClientHandler(socket));
        }
    }

    static class ClientHandler implements Runnable {

        Socket socket;

        ClientHandler(Socket socket) {
            this.socket = socket;
        }

        public void run() {

            String nickname = "";

            try {

                BufferedReader in = new BufferedReader(
                        new InputStreamReader(
                                socket.getInputStream(), "UTF-8"));

                PrintWriter out = new PrintWriter(
                        new OutputStreamWriter(
                                socket.getOutputStream(), "UTF-8"), true);

                // Yeu cau nickname
                out.println("Nhap nickname:");

                nickname = in.readLine();

                // Kiem tra nickname
                if (nickname == null || nickname.isEmpty()) {
                    socket.close();
                    return;
                }

                // Kiem tra trung nickname
                if (clients.containsKey(nickname)) {
                    out.println("Nickname da ton tai!");
                    socket.close();
                    return;
                }

                // Them client vao danh sach
                clients.put(nickname, out);

                out.println("Dang nhap thanh cong!");

                System.out.println(nickname + " da tham gia.");

                String message;

                // Nhan lenh tu client
                while ((message = in.readLine()) != null) {

                    // QUIT
                    if (message.equals("QUIT")) {
                        break;
                    }

                    // USERS
                    if (message.equals("USERS")) {

                        out.println("USERS: " + clients.keySet());
                    }

                    // MSG
                    else if (message.startsWith("MSG ")) {

                        String content = message.substring(4);

                        sendMessage(nickname, content);
                    }

                    else {

                        out.println("Lenh khong hop le!");
                    }
                }

            } catch (Exception e) {

                System.out.println(nickname + " ngat ket noi.");
            }

            // Xoa client khi thoat
            finally {

                if (!nickname.isEmpty()) {

                    clients.remove(nickname);

                    System.out.println(nickname + " da roi chat.");
                }

                try {
                    socket.close();
                } catch (Exception e) {
                }
            }
        }
    }

    // Gui tin nhan cho cac client khac
    static void sendMessage(String sender, String message) {

        for (String name : clients.keySet()) {

            if (!name.equals(sender)) {

                PrintWriter out = clients.get(name);

                out.println("[" + sender + "]: " + message);
            }
        }
    }
}