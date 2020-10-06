package Lesson_2_MySQL.Lesson_2_ChatMySQL;

import java.io.*;
import java.net.Socket;

public class ClientApplicationOne {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 8888);
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            InputStreamReader isr=new InputStreamReader(System.in);
            BufferedReader br= new BufferedReader(isr);

            System.out.println("enter login and password  " );
            String st = br.readLine();
            out.writeUTF(st);
            System.out.println(st);
            st = br.readLine();
                 new Thread(new Runnable() {
                        @Override
                        public void run() {
                            while (true) {
                                try {
                                    String message = in.readUTF();
                                    System.out.println(message);
                                    if (message.contains("Incorrect credentials")) {
                                        String st2 = br.readLine();
                                        out.writeUTF(st2);
                                    }
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }).start();

            out.writeUTF("-first try finished");
        } catch (IOException e) {

            e.printStackTrace();
          //  new ClientHandler().closeConnection();
        }
    }
}
