package Lesson_3;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ChatConsoleServer {
    private DataInputStream in;
    private DataOutputStream out;
    BufferedReader brServer = new BufferedReader(new InputStreamReader(System.in));
    private String stServer;

    public static void main(String[] args) {
        new ChatConsoleServer();
    }
    public ChatConsoleServer() {

        try {
            System.out.println("Server is starting up...");
            ServerSocket serverSocket = new ServerSocket(18443);
            System.out.println("Server waiting for connection...");

            Socket socket = serverSocket.accept();
            System.out.println("Client connected: " + socket);

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        in = new DataInputStream(socket.getInputStream());
                        while (true) {
                            String message = in.readUTF();
                            System.out.println(message + "\n\10");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }catch (NullPointerException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        out = new DataOutputStream(socket.getOutputStream());
                        out.writeUTF("Hello i'am your server: ");
                        System.out.println("                          Enter your massage for client");

                        while (true) {

                            stServer = brServer.readLine();
                            System.out.println("                          For client: "   +   stServer);
                            out.writeUTF("Server// "  + stServer);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }catch (NullPointerException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }catch (NullPointerException e) {
                //e.printStackTrace();
            }
        }
    }
}
