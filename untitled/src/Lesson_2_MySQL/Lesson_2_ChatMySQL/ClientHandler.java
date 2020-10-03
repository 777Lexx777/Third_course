package Lesson_2_MySQL.Lesson_2_ChatMySQL;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;

/**
 * Represents client session
 */
public class ClientHandler {
    private String nameUser;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private Server server;
    private int timeout;

   // public ClientHandler(Socket socket) { this.socket = socket; }

    public ClientHandler(Socket socket, Server server) {
        this.socket = socket;
        try {
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());
            start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.server = server;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void start() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    try {
                        socket.setSoTimeout(20000);
                        authenticate();
                        socket.setSoTimeout(0);
                    }catch (SocketException e) {
                            e.printStackTrace();
                            closeConnection();
                    }
                    System.out.println("Start : readMessage()");
                    readMessage();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    closeConnection();
                }
            }
        }).start();
    }
    public int getSoTimeout(int timeout) throws SocketException{
        this.timeout = timeout;
        return 0;
    }
    public void setSoTimeout(int timeout)throws SocketException { }
    public void authenticate() throws IOException {
        System.out.println("Client auth is on going...");

        while (true) {
            String loginInfo = in.readUTF();
                String[] splittedLoginInfo = loginInfo.split("\\s");
                    AuthenticationService.Client maybeClient =
                         server.getAuthenticationService().findByLoginAndPassword(
                                 splittedLoginInfo[0],
                                 splittedLoginInfo[1]
                         );
                    if (maybeClient != null) {
                        if (! server.checkLogin(maybeClient.getNameUser())) {
                            sendMessage("status: authok");
                            nameUser = maybeClient.getNameUser();
                            server.broadcast(String.format("%s came in", nameUser));
                            System.out.println("Client auth completed");
                            server.subscribe(this);
                            return;
                        } else {
                            sendMessage(String.format("%s already logged in", maybeClient.getNameUser()));
                        }
                    } else {
                        sendMessage("Incorrect credentials");
                    }
        }
    }

    public void closeConnection() {
        server.unsubscribe(this);
        server.broadcast(String.format("%s left", nameUser));
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readMessage() throws IOException {
        while (true) {
            String message = in.readUTF();
            String formatterMessage = String.format("Message from %s: %s", nameUser, message);
            System.out.println(formatterMessage);
            if (message.equalsIgnoreCase("-exit server")) {
                return;
            }
            server.broadcast(formatterMessage);
        }
    }

    public void sendMessage(String message) {
        try {
            out.writeUTF(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
