package core;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.regex.*;


public class ClientHandler implements Runnable {
    private Socket clientSocket;
    private Server server;
    private PrintWriter outMsg;
    private Scanner inMsg;
    private static int clientCount = 0;

    private String name = "";

    public ClientHandler(Socket clientSocket, Server server) {
        try {
            clientCount++;
            this.clientSocket = clientSocket;
            this.server = server;
            this.outMsg = new PrintWriter(clientSocket.getOutputStream());
            this.inMsg = new Scanner(clientSocket.getInputStream());
            this.name = name;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            server.notifyAllClients("New client in our chat");
            server.notifyAllClients("In our chat = " + clientCount + "clients!");

            while (true) {
                if (inMsg.hasNext()) {
                    String clientMsg = inMsg.nextLine();
                    if (clientMsg.equalsIgnoreCase("QUIT")) {
                        break;
                    }

                    if (checkWhisper(clientMsg)) {
                        String nickname = getNicknameFromString(clientMsg);
                        String privateMessage = clientMsg.replaceAll("/w " + nickname + " ", "");
                        server.sendPrivateMessage(privateMessage, nickname);
                    }

                    System.out.println(clientMsg);
                    server.notifyAllClients(clientMsg);
                }
            }

            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            exitFromChat();
        }
    }

    private void exitFromChat() {
        clientCount--;
        server.notifyAllClients("Client exited. In out chat = " + clientCount + " clients!");
        server.removeClient(this);
    }

    public void sendMessage(String msg) {
        try {
            outMsg.println(msg);
            outMsg.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean checkWhisper(String text) {
        String whisp = "";
        Matcher whisper = Pattern.compile("^\\S+\\s").matcher(text);

        if (whisper.find()) whisp = whisper.group(0);

        if (whisp.equals("/w ")) {
            return true;
        } else {
            return false;
        }
    }

    private String getNicknameFromString(String text){
        Matcher nickname = Pattern.compile("\\s([A-Za-z])+").matcher(text);
        String n = "";

        if (nickname.find()) {
            n = nickname.group(0);
        }
        return n.replaceAll("\\s+", "");
    }

    public String getName(){
        return name;
    }

    public void authentication() throws IOException {
        while (true) {
            String str = inMsg.nextLine();
            if (str.startsWith("/auth")) {
                String[] parts = str.split("\\s");
                String nick = server.getAuthService().getNickByLoginPass(parts[1], parts[2]);
                if (nick != null) {
                    if (!server.isNickBusy(nick)) {
                        sendMessage("/authok " + nick);
                        name = nick;
                        server.notifyAllClients(name + " зашел в чат");
                        server.subscribe(this);
                        return;
                    } else {
                        sendMessage("Учетная запись уже используется");
                    }
                } else {
                    sendMessage("Неверные логин/пароль");
                }
            }
        }
    }

    public void readMessages() throws IOException {
        while (true) {
            String strFromClient = inMsg.nextLine();
            System.out.println("от " + name + ": " + strFromClient);
            if (strFromClient.equals("/end")) {
                return;
            } else if (checkWhisper(strFromClient)) {
                String nickname = getNicknameFromString(strFromClient);
                String privateMessage = strFromClient.replaceAll("/w " + nickname + " ", name + " whispers: ");
                server.sendPrivateMessage(privateMessage, nickname);
            } else {
                server.notifyAllClients(name + ": " + strFromClient);
            }
        }

    }

}
