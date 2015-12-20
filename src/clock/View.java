package clock;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Observer;
import java.util.Observable;

public class View implements Observer {

    ClockPanel panel;

    public View(Model model) {
        JFrame frame = new JFrame();
        panel = new ClockPanel(model);
        //frame.setContentPane(panel);
        frame.setTitle("Java Clock");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenuBar mb = new JMenuBar();

        JMenu file = new JMenu("File");
        mb.add(file);
        JMenuItem addAlarm = new JMenuItem("Add Alarm");
        file.add(addAlarm);
        JMenuItem saveAlarm = new JMenuItem("Save Alarms");
        file.add(saveAlarm);
        JMenuItem printAlarms = new JMenuItem("Print Alarms");
        file.add(printAlarms);

        addAlarm.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.addalarmdialogue();
            }
        });
        saveAlarm.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.addalarmdialogue();
            }
        });
        printAlarms.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               String aids = model.printAlarms();
                System.out.println(aids);
            }
        });
        frame.setJMenuBar(mb);
        // Start of border layout code
        
        // I've just put a single button in each of the border positions:
        // PAGE_START (i.e. top), PAGE_END (bottom), LINE_START (left) and
        // LINE_END (right). You can omit any of these, or replace the button
        // with something else like a label or a menu bar. Or maybe you can
        // figure out how to pack more than one thing into one of those
        // positions. This is the very simplest border layout possible, just
        // to help you get started.

        JFrame pane = new JFrame();
        JPanel form = new JPanel();
        JPanel clockframe = new JPanel();
        form.setLayout(new BoxLayout(form, BoxLayout.PAGE_AXIS));
        JButton button = new JButton("Button 1 (PAGE_START)");
        pane.add(button, BorderLayout.PAGE_START);

        panel.setPreferredSize(new Dimension(400, 400));
        clockframe.add(panel, BorderLayout.CENTER);




        JButton but=new JButton();
        but.setText("Set Alarm");


        form.add(but);
        form.add(Box.createRigidArea(new Dimension(10, 10)));
        clockframe.add(form);
        but.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.addalarmdialogue();
            }
        });
        form.setPreferredSize(new Dimension(200, 200));
        // End of borderlayout code

        frame.add(clockframe);

        frame.pack();
        frame.setVisible(true);
    }
    
    public void update(Observable o, Object arg) {
        panel.repaint();
    }




}

