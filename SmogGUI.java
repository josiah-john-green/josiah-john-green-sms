
/**
 * Write a description of class SmogGUI here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * A simple form to record smog levels in the city
 *
 * @author Final Examination
 * @version 1.0a
 */

public class SmogGUI extends JFrame {

    private JFrame frame;

    private SmogApp smapp;
    private JTextField distance; // distance
    private JTextField smogLevel; // smog level
    private JTextArea displayArea;
    private JButton save;
    private JButton clear;
    private JButton clearAll;
    private JButton exit;
    private JPanel command;
    private JPanel display;

    /**
     * Constructor for objects of class SmogMeter
     */

    public SmogGUI(SmogApp smapp, JFrame frame) {

        this.frame = frame;

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        command = new JPanel();
        command.setLayout(new GridLayout(2, 4));

        display = new JPanel();
        displayArea = new JTextArea("Readings displayed here");
        display.setBackground(Color.blue);
        display.add(displayArea);

        save = new JButton("Save");
        clear = new JButton("Clear");
        clearAll = new JButton("Clear All");
        exit = new JButton("Exit");

        distance = new JTextField(4);
        smogLevel = new JTextField(4);

        command.add(new JLabel("Distance:"));
        command.add(distance);
        command.add(new JLabel("Smog Level:"));
        command.add(smogLevel);

        command.add(save);
        save.addActionListener(new saveButtonListener());

        command.add(clear);
        clear.addActionListener(new clearButtonListener());

        command.add(clearAll);
        clearAll.addActionListener(new clearAllButtonListener());

        command.add(exit);
        exit.addActionListener(new exitButtonListener());

        add(display, BorderLayout.CENTER);
        add(command, BorderLayout.SOUTH);

        pack();
        setVisible(true);

    }

    public static void main(String[] args) {

        JFrame frame = new JFrame("City Smog Meter");

        SmogApp smapp = new SmogApp();

        String app = smapp.toString();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new SmogGUI(smapp, frame));

        frame.pack();
        frame.setVisible(true);

    }

    private class exitButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

    private class saveButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if ((distance.getText() != null) && (smogLevel.getText() != null)) {

                double dist = Double.parseDouble(distance.getText());
                double smog = Double.parseDouble(smogLevel.getText());

                SmogApp smogread = new SmogApp();

                smogread.addReading(dist, smog);

                String app = smogread.toString();

                displayArea.setText(app);

                Path path = Paths.get("output.txt");

                try
                {
                    Files.writeString(path, app, StandardCharsets.UTF_8);
                }
                catch (IOException ex)
                {
                    // Handle exception
                }

            }
        }
    }

    private class clearButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            distance.setText("");
            smogLevel.setText("");
        }
    }

    private class clearAllButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            displayArea.setText("Reading Cleared!");
            smapp.clearLog();

        }
    }
}
