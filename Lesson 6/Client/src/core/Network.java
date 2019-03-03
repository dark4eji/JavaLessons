package core;

import javax.swing.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Network {
    private String server_addr;
    private int server_port;

    private Socket socket;
    public DataInputStream in;
    public DataOutputStream out;

    private JTextArea textArea;

    public Network(String server_addr, int server_port, JTextArea textArea) {
        this.server_addr = server_addr;
        this.server_port = server_port;
        this.textArea = textArea;
    }

    public void openConnection() throws IOException {
        socket = new Socket(this.server_addr, this.server_port);
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
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

    /**
     * Служит для отправки сообщений
     * @param textField
     * @param textArea
     */
    public void sendMessage(JTextField textField, JTextArea textArea) {
        if (!textField.getText().trim().isEmpty()) {
            try {
                String gottenText = textField.getText();
                textField.setText("");
                textArea.append(new SimpleDateFormat("hh:mm:ss").format(new Date())
                        + "\n"
                        + gottenText
                        + "\n\n");
                textField.grabFocus();
                out.writeUTF(new SimpleDateFormat("hh:mm:ss").format(new Date())
                        + "\n"
                        + gottenText
                        + "\n\n");
            } catch (IOException e){
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Ошибка" +
                        "отправки сообщения");
            }
        }
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


}
