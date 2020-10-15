package Lesson_4;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ChatConsoleThreadClientThree {
    private DataInputStream in;
    private DataOutputStream out;
    // private JTextArea charArea;
    BufferedReader brClient = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw;
    private String stClient;
    private String fromServer;
    ChatConsoleThreadServer chatConsoleThreadServerClientThree = new ChatConsoleThreadServer();
    //public ConsoleChatClient() {}
   // public static void main(String[] args) {new ChatConsoleThreadClientThree();}

    public void chatConsoleThreadClientThree() {
        // this.charArea = charArea;
        final String[] d = new String[1];

//        try { bw = new BufferedWriter(new FileWriter
//                ("/java/Third_Course/Test_Third_Course_Lesson_4_ThreadTree.txt", true));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        try {
            Socket socket = new Socket("127.0.0.1", 18443);
            System.out.println("Client info: " + socket);
            in = new DataInputStream(socket.getInputStream());

//            try {
//                synchronized (d) {
//
//                    System.out.println("\n Enter haw many previous messages you want to see - ");
//
//                   // int quantityMessage = Integer.parseInt(brClient.readLine());
//                    int quantityMessage = 2;
//                    System.out.println(quantityMessage);
//                    System.out.println( "\n"+read(quantityMessage) +"\n");
//
//                    d[0] = in.readUTF();
//                    System.out.println("\n" + d[0] + "\n");
//                }
//            }catch (NullPointerException e) {
//                e.printStackTrace();
//            }
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        while (true) {
//                            fromServer = in.readUTF();
//                                if(fromServer.length() > 25) {
//                                    int limit = 25;
//                                        String subStr = fromServer.codePointCount(0, fromServer.length()) > limit ?
//                                                        fromServer.substring(0, fromServer.offsetByCodePoints(0, limit)) : fromServer;
//                                            System.out.println("\n" +subStr + "\n");
//                                            continue;
//                                }
//                                    bw.newLine();
//                                bw.append(fromServer);
//
//                            System.out.printf("\n" +fromServer + "\n");
//                        }
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }catch (NullPointerException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }).start();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        out = new DataOutputStream(socket.getOutputStream());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    synchronized (chatConsoleThreadServerClientThree.getMessage()) {

                        try {
                                System.out.println("                       Enter your massage for server THREE");
                            try {
                                for (int i = 0; i <3; i++) {
                                while (!(chatConsoleThreadServerClientThree.getMessage().equals("Client3 "))) {
//                                     //stClient = brClient.readLine();
                                    chatConsoleThreadServerClientThree.getMessage().wait();
                                }
                                    stClient = "C";
                                               System.out.println("                          For server: " + stClient);
                                                try {
                                                    Thread.sleep(5000);
                                                    out.writeUTF("Client3 // " + stClient);
                                                } catch (InterruptedException e) {
                                                    e.printStackTrace();
                                                }
                                             out.writeUTF("Client1 // " + stClient);
                                           chatConsoleThreadServerClientThree.setMessage ("Client1 ");
                                        chatConsoleThreadServerClientThree.getMessage().notifyAll();
                                      bw.newLine();
                                    bw.append("Client3 // " + stClient);

                               // }
                                }
                            } catch (InterruptedException e) {
                                throw new RuntimeException("SWW",e );
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (NullPointerException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                out.close();
                in.close();
            }
            catch (NullPointerException e) {
                //e.printStackTrace();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Collection<String> read(int linesNumber) {
        File fileToRead = new File("/java/Third_Course/Test_Third_Course_Lesson_4_ThreadTree.txt");
        try (FileReader rd = new FileReader(fileToRead);
             BufferedReader br = new BufferedReader(rd)) {

            List<String> lines = new ArrayList<>();
            String str;
            while ((str = br.readLine()) != null) {
                lines.add(str);
            }

            List<String> lastLines = new ArrayList<>();
            for (int i = lines.size() - linesNumber; i < lines.size(); i++) {
                lastLines.add(lines.get(i));
            }

            return lastLines;
        } catch (Exception e) {
            throw new RuntimeException("SWW", e);
        }
    }
    public DataInputStream getIn() { return in; }

    public DataOutputStream getOut() { return out; }
}
