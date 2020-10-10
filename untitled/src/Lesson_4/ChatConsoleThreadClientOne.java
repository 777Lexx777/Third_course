package Lesson_4;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ChatConsoleThreadClientOne{
    private DataInputStream in;
    private DataOutputStream out;
    // private JTextArea charArea;
    BufferedReader brClientOne = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw;
    private String stClient;
    private String fromServer;
    ChatConsoleThreadServer chatConsoleThreadServerClientOne = new ChatConsoleThreadServer();

//    public static void main(String[] args) {
//     ChatConsoleThreadClientOne chatConsoleThreadClientOneServer = new ChatConsoleThreadClientOne();
//        chatConsoleThreadClientOneServer.chatConsoleThreadClientOne();
//    }

    public void chatConsoleThreadClientOne() {
        // this.charArea = charArea;
        final String[] b = new String[1];
//
//        try { bw = new BufferedWriter(new FileWriter
//                      ("/java/Third_Course/Test_Third_Course_Lesson_4_ThreadOne.txt", true));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        try { Socket socket = new Socket("127.0.0.1", 18443 );
              System.out.println("Client info: " + socket);
            in = new DataInputStream(socket.getInputStream());
//            try {
//                synchronized (b) {
//                    System.out.println("\n Enter haw many previous messages you want to see - ");
//                   // int quantityMessage = Integer.parseInt(brClientOne.readLine());
//                    int quantityMessage = 2;
//                    System.out.println(quantityMessage);
//                    System.out.println( "\n"+read(quantityMessage) +"\n");
//
//                    b[0] = in.readUTF();
//                    System.out.println("\n" + b[0] + "\n");
//                }
//            }catch (NullPointerException e) {
//                e.printStackTrace();
//            }

//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//
//                        while (true) {
//                            fromServer = in.readUTF();
//                                if(fromServer.length() > 25) {
//                                    int limit = 25;
//                                        String subStr = fromServer.codePointCount(0, fromServer.length()) > limit ?
//                                                        fromServer.substring(0, fromServer.offsetByCodePoints(0, limit)) : fromServer;
//                                            System.out.println("\n" +subStr + "\n");
//                                        continue;
//                                }
//                                    bw.newLine();
//                                bw.append(fromServer);
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
                    synchronized (chatConsoleThreadServerClientOne.getMessage()) {
                        try {
                                System.out.println("                       Enter your massage for serverONE");
                                   for (int i = 0; i <3; i++){
                                        while (!(chatConsoleThreadServerClientOne.getMessage().equals("Client1 "))) {
//                                             stClient = brClientOne.readLine();
                                           try {
                                               chatConsoleThreadServerClientOne.getMessage().wait();
                                           } catch (InterruptedException e) {
                                               throw new RuntimeException("SWW",e );
                                           }
                                        }
                                            stClient = "A";
                                                    System.out.println("                          For server: " + stClient);
                                                   try {
                                                       Thread.sleep(5000);
                                                       out.writeUTF("Client1 // " + stClient);
                                                   } catch (InterruptedException e) {
                                                       e.printStackTrace();
                                                   }
                                                   // bw.newLine();
                                              //  bw.append("Client1// " + stClient);
                                            chatConsoleThreadServerClientOne.setMessage( "Client2 ");
                                       chatConsoleThreadServerClientOne.getMessage().notifyAll();
                                        //}
                                   }
                              //  out.writeUTF(null);
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
        File fileToRead = new File("/java/Third_Course/Test_Third_Course_Lesson_4_ThreadOne.txt");
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
