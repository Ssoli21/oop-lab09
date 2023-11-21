package it.unibo.mvc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;


/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUIWithFileChooser {

    private final static int PROPORTION = 4;

    private final JFrame frame = new JFrame("newGUI");

    public SimpleGUIWithFileChooser(final Controller controller){
        final JPanel canvas = new JPanel();
        canvas.setLayout(new BorderLayout());
        final JTextArea tArea = new JTextArea();
        final JButton bSave = new JButton("Save");
        final JPanel subPanel = new JPanel(new BorderLayout());
        final JTextField tField = new JTextField(controller.getCurrentPath());
        tField.setEditable(false);
        final JButton bBrowse = new JButton("Browse...");
        canvas.add(subPanel, BorderLayout.NORTH);
        canvas.add(tArea, BorderLayout.CENTER);
        canvas.add(bSave, BorderLayout.SOUTH);
        subPanel.add(tField, BorderLayout.CENTER);
        subPanel.add(bBrowse, BorderLayout.LINE_END);
        frame.setContentPane(canvas);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        bSave.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                controller.write(tArea.getText());
            }
            
        });
        bBrowse.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                final JFileChooser fc = new JFileChooser("Where should I save?");
                fc.setSelectedFile(controller.getCurrent());
                final int result = fc.showSaveDialog(frame);
                switch (result) {
                    case JFileChooser.APPROVE_OPTION:
                        final File newDest = fc.getSelectedFile();
                        controller.setCurrent(newDest);
                        tField.setText(controller.getCurrentPath());
                        break;
                    case JFileChooser.CANCEL_OPTION:
                        break;
                    default:
                        JOptionPane.showMessageDialog(frame, result, "Meh!", JOptionPane.ERROR_MESSAGE);
                        break;
                }
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
        SimpleGUIWithFileChooser myGui = new SimpleGUIWithFileChooser(ctrl);
    }
}
