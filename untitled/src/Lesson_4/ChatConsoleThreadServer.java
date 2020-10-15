package Lesson_4;
/*
1. Создать три потока, каждый из которых выводит определенную букву (A, B и C) 5 раз
(порядок – ABСABСABС). Используйте wait/notify/notifyAll.
2. На серверной стороне сетевого чата реализовать управление потоками через ExecutorService.
 */

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ChatConsoleThreadServer {
    private DataInputStream in;
    private DataOutputStream out;
    private String stServer;
    private String message = "Client1 ";
    String [] numberClient = {"Client1 "," "," // "};
    //String [] numberClient = new String[2];

    BufferedReader brServerThread = new BufferedReader(new InputStreamReader(System.in));



    public static void main(String[] args) {

       // chatConsoleThreadServerClient.numberClient = chatConsoleThreadServerClient.message.split(" ");

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        ChatConsoleThreadClientOne chatConsoleThreadClientOneServer = new ChatConsoleThreadClientOne();
        ChatConsoleThreadClientTwo chatConsoleThreadClientTwoServer = new ChatConsoleThreadClientTwo();
        ChatConsoleThreadClientThree chatConsoleThreadClientThreeServer = new ChatConsoleThreadClientThree();
        ChatConsoleThreadServer chatConsoleThreadServerClient = new ChatConsoleThreadServer();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                chatConsoleThreadServerClient.chatConsoleThreadServer();
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                chatConsoleThreadClientOneServer.chatConsoleThreadClientOne();
            }
        });
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                chatConsoleThreadClientTwoServer.chatConsoleThreadClientTwo();
            }
        });
        Thread t4 = new Thread(new Runnable() {
            @Override
            public void run() {
                chatConsoleThreadClientThreeServer.chatConsoleThreadClientThree();
            }
        });

        executorService.submit(t1);
        executorService.submit(t2);
        executorService.submit(t3);
        executorService.submit(t4);

    }

    public void setMessage(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }
    public void setNumberClient(String[] numberClient) {
        this.numberClient = numberClient;
    }

    public void chatConsoleThreadServer() {

        try {
            System.out.println("Server is starting up...");
            ServerSocket serverSocket = new ServerSocket(18443);
            System.out.println("Server waiting for connection...");

            Socket socket = serverSocket.accept();
            System.out.println("Client connected: " + socket);
            //synchronized ( numberClient) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            in = new DataInputStream(socket.getInputStream());
                            while (true) {
                                message = in.readUTF();
                                System.out.println(getMessage() + "\n\10");
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (NullPointerException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
           // }

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        out = new DataOutputStream(socket.getOutputStream());
                        out.writeUTF("Hello i'am your server: ");
                        System.out.println("                          Enter your massage for client");

                        while (true) {
                            if (message == null) {
                                synchronized (message) {
                                    // stServer = brServerThread.readLine();
                                    //System.out.println("                          For client: "   +   stServer);
                                    //out.writeUTF("Server// "  + stServer);
                                    numberClient = message.split(" ");
                                    //                                try {
                                    //                                    Thread.sleep(1000);
                                    //                                } catch (InterruptedException e) {
                                    //                                    e.printStackTrace();
                                    //                                }
                                    //System.out.println(numberClient[0]);
                                    if (numberClient[0].equals("Client1")) {
                                        System.out.println("                          For client ONE: " + message);
                                        try {
                                            Thread.sleep(1000);
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                        out.writeUTF("Server ONE// " + message);
                                    }
                                    if (numberClient[0].equals("Client2")) {
                                        System.out.println("                          For client TWO: " + message);
                                        try {
                                            Thread.sleep(1500);
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                        out.writeUTF("Server TWO// " + message);
                                    }
                                    if (numberClient[0].equals("Client3")) {
                                        System.out.println("                          For client TREE: " + message);
                                        try {
                                            Thread.sleep(2000);
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                        out.writeUTF("Server TREE// " + message);
                                    }

                                }
                                break;
                            }
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
