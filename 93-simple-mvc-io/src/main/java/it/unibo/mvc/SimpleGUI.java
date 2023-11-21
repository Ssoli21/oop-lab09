package it.unibo.mvc;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUI {
    private final static int PROPORTION = 5;

    private final JFrame frame = new JFrame("SimpleGUI");

    public SimpleGUI(final Controller controller){
        final JPanel canvas = new JPanel();
        canvas.setLayout(new BorderLayout());
        final JTextArea tArea = new JTextArea();
        final JButton bSave = new JButton("Save");
        canvas.add(tArea, BorderLayout.CENTER);
        canvas.add(bSave, BorderLayout.SOUTH);
        frame.setContentPane(canvas);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        bSave.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                controller.write(tArea.getText());
            }
            
        });
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / PROPORTION, sh / PROPORTION);
        //frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        Controller ctrl = new Controller();
        SimpleGUI myGui = new SimpleGUI(ctrl);
    }

}
