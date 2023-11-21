package it.unibo.mvc;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.BoxLayout;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.Toolkit;

import java.util.List;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUI {

    private final JFrame frame = new JFrame("myGUI");

    public SimpleGUI(final Controller controller){
        final JPanel canvas = new JPanel();
        canvas.setLayout(new BorderLayout());
        final JTextField tField = new JTextField();
        final JTextArea tArea = new JTextArea();
        final JButton bPrint = new JButton("Print");
        final JButton bShow = new JButton("Show history");
        final JPanel subPanel = new JPanel();
        subPanel.setLayout(new BoxLayout(subPanel, BoxLayout.LINE_AXIS));
        canvas.add(tField, BorderLayout.NORTH);
        canvas.add(tArea, BorderLayout.CENTER);
        canvas.add(subPanel, BorderLayout.SOUTH);
        subPanel.add(bPrint);
        subPanel.add(bShow);
        frame.add(canvas);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        bShow.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                final StringBuilder text = new StringBuilder();
                final List<String> history = controller.getHistory();
                for (final String print: history) {
                    text.append(print);
                    text.append('\n');
                }
                if (!history.isEmpty()) {
                    text.deleteCharAt(text.length() - 1);
                }
                tArea.setText(text.toString());
            }
        });
    
        bPrint.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                tField.setText(controller.getNextString());
                controller.printNextString();
            }
            
        });
    }

    public void display(){
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / 4, sh / 4);
        //frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        Controller ctrl = new SimpleController();
        SimpleGUI gui = new SimpleGUI(ctrl);
        ctrl.setNextString("ciao");
        gui.display();

    }
}
