package core;

import javax.swing.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


public class Network {
    private String server_addr;
    private int server_port;

    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    private JTextArea textArea;

    public Network(String server_addr, int server_port) {
        this.server_addr = server_addr;
        this.server_port = server_port;
    }

    public void openConnection(JTextArea textArea) throws IOException {
        socket = new Socket(this.server_addr, this.server_port);
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());

		
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    while (true) {
                        onAuthClick();
                        String strFromServer = in.readUTF();
                        if(strFromServer.startsWith("/authok")) {
                            break;
                        }
                        textArea.append(strFromServer + "\n");
                    }
                    while (true) {
                        String strFromServer = in.readUTF();
                        if (strFromServer.equalsIgnoreCase("/end")) {
                            break;
                        }
                        textArea.append(strFromServer);
                        textArea.append("\n");

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void closeConnection() {
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

	public DataInputStream getInputStream() {
		return in;
	}

	public DataOutputStream getOutputStream() {
		return out;
	}


    public void onAuthClick() {
        try {
            out.writeUTF("/auth " + "login1" + " " + "pass1");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
