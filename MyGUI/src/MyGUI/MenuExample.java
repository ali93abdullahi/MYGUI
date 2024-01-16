package MyGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class MenuExample {
    private JFrame frame;
    private JTextArea textArea;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new MenuExample().initialize();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void initialize() throws IOException {
        frame = new JFrame("Menu Example");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        frame.add(scrollPane, BorderLayout.CENTER);

        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        JMenu fileMenu = new JMenu("File");

        JMenuItem showDateTimeItem = new JMenuItem("Show Date and Time");
        showDateTimeItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showDateTime();
            }
        });
        fileMenu.add(showDateTimeItem);

        JMenuItem writeToFileItem = new JMenuItem("Write to File");
        writeToFileItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                writeToFile();
            }
        });
        fileMenu.add(writeToFileItem);

        JMenuItem changeBackgroundColorItem = new JMenuItem("Change Background Color");
        changeBackgroundColorItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeBackgroundColor();
            }
        });
        fileMenu.add(changeBackgroundColorItem);

        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        fileMenu.add(exitItem);

        menuBar.add(fileMenu);

        frame.setVisible(true);
    }

    private void showDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateTime = dateFormat.format(new Date());
        textArea.setText(dateTime);
    }

    private void writeToFile() {
        try (FileWriter writer = new FileWriter("log.txt")) {
            writer.write(textArea.getText());
            JOptionPane.showMessageDialog(frame, "Contents written to log.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void changeBackgroundColor() {
        Random random = new Random();
        float hue = random.nextFloat();
        Color backgroundColor = Color.getHSBColor(hue, 0.8f, 0.8f);
        frame.getContentPane().setBackground(backgroundColor);
    }
}
