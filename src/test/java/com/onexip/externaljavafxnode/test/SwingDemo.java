package com.onexip.externaljavafxnode.test;

import com.onexip.externaljavafxnode.ExternalContent;

import javax.swing.*;
import java.awt.*;

public class SwingDemo extends ExternalContent {

    private JWindow frame = null;

    public SwingDemo() throws HeadlessException {
        frame = new JWindow();
    }


    // private static void createAndShowGUT() {
    private void createAndShowGUI() {

        //Add the ubiquitous "Hello World" label.
        JLabel label = new JLabel("Hello World");
        frame.getContentPane().add(label);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void setPositionBQ(double x, double y) {
        SwingUtilities.invokeLater(() -> frame.setLocation((int) x, (int) y));
    }

    @Override
    public void setSizeBQ(double x, double y) {
        SwingUtilities.invokeLater(() -> frame.setSize((int) x, (int) y));
    }

    @Override
    public void setCloseRequestBQ() {
        SwingUtilities.invokeLater(() -> System.exit(0));
    }

    @Override
    public void setIconifiedBQ(boolean iconified) {
        //TODO Emilio: Minimize abgeschaltet wenn Window Undecorated
        SwingUtilities.invokeLater(() -> frame.setVisible(!iconified));

    }

    @Override
    public void init() throws HeadlessException {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });


    }


}
