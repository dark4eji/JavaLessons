package GUI;

import core.MessageOperator;
import core.Network;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class MainWindow extends JFrame {
    private final int WIN_MARGIN = 4;

    private JPanel showMessagesPanel = new JPanel();
    private JPanel sendMessagesPanel = new JPanel();

    private JMenuBar menuBar = new JMenuBar();
    private JMenu actionsMenu = new JMenu("Действия");
    //private JMenu settingsMenu = new JMenu("Настройки");

    private JMenuItem save = new JMenuItem("Сохранить переписку");
    private JMenuItem exit = new JMenuItem("Выход");

    private JTextArea textArea = new JTextArea();
    private JTextField textField = new JTextField();
    private JButton sendButton = new JButton("Отправить");
    private Container container = getContentPane();
    private Font messagesFont = new Font("Segoe UI", Font.PLAIN, 14);
    private JScrollPane scroll = new JScrollPane();

    Network network = new Network("localhost", 8189, textArea);

    public MainWindow(){
        super("Chat 0.1");
        setVisible(true);
        setSize(600, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        try {
            network.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Добавление панели меню
        actionsMenu.add(save);
        actionsMenu.addSeparator();
        actionsMenu.add(exit);

        menuBar.add(actionsMenu);
        //menuBar.add(settingsMenu);

        setJMenuBar(menuBar);

        textArea.setFont(messagesFont); // Установка кастомного шрифта
        textArea.setLineWrap(true);
        textArea.setEditable(false);

        textField.grabFocus();

        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setViewportView(textArea);

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

        container.add(showMessagesPanel, BorderLayout.CENTER);
        container.add(sendMessagesPanel, BorderLayout.PAGE_END);

        // Слушатель на отправку сообщения через кнопку
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                network.sendMessage(textField, textArea);
            }
        });

        // Слушатель на отправку сообщений через клавишу ENTER
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    network.sendMessage(textField, textArea);
                }
            }
        });

        // Слушатель на сохранение текущей беседы в файл
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MessageOperator.saveCurrentChat(textArea);
            }
        });


        // Слушатель на выход из программы
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                try {
                    network.out.writeUTF("/end");
                    network.closeConnection();
                } catch (IOException exc) {
                    exc.printStackTrace();
                }
            }
        });
    }
}




