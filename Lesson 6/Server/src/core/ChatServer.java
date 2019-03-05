package core;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ChatServer extends JFrame {
	DataInputStream in;
	DataOutputStream out;

    private final int WIN_MARGIN = 4;
	
	private JPanel serviceMessagesPanel = new JPanel();
	private JPanel serviceButtonsPanel = new JPanel();
	private JPanel showMessagesPanel = new JPanel();
    private JPanel sendMessagesPanel = new JPanel();
	
	private JTextArea serviceTextArea = new JTextArea();
    private JTextArea textArea = new JTextArea();
    private JTextField textField = new JTextField();

    private JButton sendButton = new JButton("Отправить");
	//private JButton sendButton = new JButton("Запустить");
	//private JButton sendButton = new JButton("Остановить");
	
    private Container container = getContentPane();
    private Font messagesFont = new Font("Segoe UI", Font.PLAIN, 14);
    private JScrollPane scroll = new JScrollPane();
	
	public ChatServer() {
        super("CServer 0.1");
        setVisible(true);
        setSize(400, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		textArea.setFont(messagesFont);
        textArea.setLineWrap(true);
        textArea.setEditable(false);

        textField.grabFocus();

        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setViewportView(textArea);
		
		serviceMessagesPanel.setLayout(new BoxLayout(serviceMessagesPanel, BoxLayout.PAGE_AXIS));
		serviceMessagesPanel.add(serviceTextArea);
		serviceMessagesPanel.setBorder(BorderFactory.createEmptyBorder(WIN_MARGIN,
                WIN_MARGIN,
                WIN_MARGIN,
                0));

        showMessagesPanel.setLayout(new BoxLayout(showMessagesPanel, BoxLayout.PAGE_AXIS));
        showMessagesPanel.add(scroll);
		
		
        showMessagesPanel.setBorder(BorderFactory.createEmptyBorder(WIN_MARGIN,
                WIN_MARGIN,
                WIN_MARGIN,
                WIN_MARGIN));

        sendMessagesPanel.setLayout(new BoxLayout(sendMessagesPanel, BoxLayout.LINE_AXIS));
        sendMessagesPanel.add(textField);
        sendMessagesPanel.add(sendButton);
        sendMessagesPanel.setBorder(BorderFactory.createEmptyBorder(0,
                WIN_MARGIN,
                WIN_MARGIN,
                WIN_MARGIN));
				
		container.add(serviceMessagesPanel, BorderLayout.PAGE_START);
        container.add(showMessagesPanel, BorderLayout.CENTER);
        container.add(sendMessagesPanel, BorderLayout.PAGE_END);
		
		// Слушатель на отправку сообщения через кнопку
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage(textField, textArea);
            }
        });

        // Слушатель на отправку сообщений через клавишу ENTER
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    sendMessage(textField, textArea);
                }
            }
        });

        startServer();
	}
	
        public void startServer() {
            Socket socket = null;

                try (ServerSocket serverSocket = new ServerSocket(8189)) {
                    serviceTextArea.append("Сервер запущен, ожидаем подключения...\n");

                    socket = serverSocket.accept();
                    serviceTextArea.append("Клиент подключился\n");

                    in = new DataInputStream(socket.getInputStream());
                    out = new DataOutputStream(socket.getOutputStream());

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                while (true) {
                                    String msgFromClient = in.readUTF();
                                    if (msgFromClient.equals("/end")) {
                                        break;
                                    }
                                    Thread.sleep(50);
                                    textArea.append(msgFromClient);
                                    textArea.append("\n");
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();

                } catch (IOException e) {
                e.printStackTrace();
            }
    }
	
	//public void stopServer(){
	//	try {
    //        in.close();
    //    } catch (IOException e) {
    //        e.printStackTrace();
    //    }
    //    try {
    //        out.close();
    //    } catch (IOException e) {
    //        e.printStackTrace();
    //    }
    //    try {
    //        socket.close();
    //    } catch (IOException e) {
    //        e.printStackTrace();
    //    }
	//	serviceTextArea.append("Сервер остановлен\n")
	//}
	
	public void sendMessage(JTextField textField, JTextArea textArea) {
		if (!textField.getText().trim().isEmpty()) {
            try {
                String msgToSend = new SimpleDateFormat("hh:mm:ss").format(new Date())
                        + " (Admin)"
                        + "\n"
                        + textField.getText()
                        + "\n";
						
                textField.setText("");
                textArea.append(msgToSend);
                textField.grabFocus();

                out.writeUTF(msgToSend);

            } catch (IOException e){
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Ошибка" +
                        "отправки сообщения");
            }
        }
    }
}


