package core;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MessageOperator {

    /**
     * Сохраняет текущую переписку
     * @param textArea
     */
    public static void saveCurrentChat(JTextArea textArea){
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(getFilepath() + ".txt"), "UTF-8"))) {
            writer.write(textArea.getText());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Открывает меню для выбора локации сохранения переписки и возвращает путь к файлу
     * @return
     */
    private static String getFilepath() {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(".txt", "txt");

        fileChooser.setFileFilter(filter);
        int returnValue = fileChooser.showSaveDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            return fileToSave.getAbsolutePath();

        } else {
            return "0";
        }
    }
}