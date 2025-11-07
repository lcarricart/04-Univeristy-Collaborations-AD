package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.JFrame;

public class WelcomeMenuJFrame extends JFrame {
    private static final long serialVersionUID = 1L;
    private MainWelcomeJFrame parentFrame;
    private WelcomeMenuJPanel welcMenuPanel;

    public WelcomeMenuJFrame(MainWelcomeJFrame parentFrame, int menuW, int menuH) {
        super("Welcome Menu");
        this.parentFrame = parentFrame;

        initialization(menuW, menuH);
    }

    private void initialization(int menuW, int menuH) {
        setSize(new Dimension(menuW, menuH));
        setLocationRelativeTo(parentFrame); // centre over background
        
        welcMenuPanel = new WelcomeMenuJPanel(parentFrame);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(welcMenuPanel, BorderLayout.CENTER);
        
        setDefaultCloseOperation(EXIT_ON_CLOSE); // do not kill JVM if closed manually
        setVisible(true);
    }
}