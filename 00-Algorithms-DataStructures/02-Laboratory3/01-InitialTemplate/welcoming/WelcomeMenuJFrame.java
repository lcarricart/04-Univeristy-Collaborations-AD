/*******************************************************************************************************************
 * Objective of the class: Welcome dialog window that appears on startup with user greeting and navigation options.
 *******************************************************************************************************************
 * Context: This is part of a major programming project, where live telemetry is transmitted to a PC, to be later
   manipulated and displayed with a Java GUI application.
 *******************************************************************************************************************
 * Authors: 
 * 	- Luciano Carricart, https://github.com/lcarricart/
 * 	- Georgii Molyboga, https://github.com/Georgemolyboga/
 * Status: Information Engineering students, HAW Hamburg, Germany.
 * Date: November 2024
 *******************************************************************************************************************
 * Public methods:
 * 	- None (initialization handled internally)
 *******************************************************************************************************************/

package welcoming;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;

public class WelcomeMenuJFrame extends JFrame {
    private static final long serialVersionUID = 1L;
    private MainWelcomeJFrame parentFrame;
    private WelcomeMenuJPanel welcMenuPanel;

    public WelcomeMenuJFrame(MainWelcomeJFrame parentFrame, int menuW, int menuH) {
        super();
        this.parentFrame = parentFrame;
        setUndecorated(true);

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