package Lesson_3;


import java.io.*;
import java.net.Socket;
import java.util.*;


public class ChatConsoleClient {
    private DataInputStream in;
    private DataOutputStream out;
    // private JTextArea charArea;
    BufferedReader brClient = new BufferedReader(new InputStreamReader(System.in));
    private String stClient;
    private String fromServer;

    //public ConsoleChatClient() {}
    public static void main(String[] args) {
        new ChatConsoleClient();
    }

    public ChatConsoleClient() {
        // this.charArea = charArea;
        final String[] a = new String[1];


        try {
            Socket socket = new Socket("127.0.0.1", 18443);
            System.out.println("Client info: " + socket);

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        DataInputStream in = new DataInputStream(socket.getInputStream());
                        try {
                            synchronized (a) {

                                System.out.println("\n Enter haw many previous messages you want to see - ");

                                int quantityMessage = Integer.parseInt(brClient.readLine());
                                System.out.println(quantityMessage);
                                System.out.println( "\n"+read(quantityMessage) +"\n");

                                a[0] = in.readUTF();
                                System.out.println("\n" + a[0] + "\n");
                            }
                        }catch (NullPointerException e) {
                            e.printStackTrace();
                        }
                        while (true) {
                            fromServer = in.readUTF();

                                if(fromServer.length() > 25) {
                                    int limit = 25;
                                    String subStr = fromServer.codePointCount(0, fromServer.length()) > limit ?
                                                    fromServer.substring(0, fromServer.offsetByCodePoints(0, limit)) : fromServer;
                                    System.out.println("\n" +subStr + "\n");
                                    continue;
                                }
                            try (BufferedWriter bw = new BufferedWriter(
                                                     new FileWriter("/java/Third_Course/Test_Third_Course_Lesson_3.txt",
                                                                    true))) {
                                bw.newLine();
                                bw.append(fromServer);
                            } catch (IOException e) {
                                throw new RuntimeException("SWW", e);
                            }
                                System.out.printf("\n" +fromServer + "\n");
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
                    synchronized (a) {
                        try {
                            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                            System.out.println("                       Enter your massage for server");
                            while (true) {

                                stClient = brClient.readLine();
                                System.out.println("                          For server: "   +   stClient);
                                out.writeUTF("Client// " + stClient);
                                try (BufferedWriter bw = new BufferedWriter(
                                        new FileWriter("/java/Third_Course/Test_Third_Course_Lesson_3.txt",
                                                true))) {
                                    bw.newLine();
                                    bw.append("Client// " + stClient);
                                } catch (IOException e) {
                                    throw new RuntimeException("SWW", e);
                                }

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
        File fileToRead = new File("/java/Third_Course/Test_Third_Course_Lesson_3.txt");
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
