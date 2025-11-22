/*******************************************************************************************************************
 * Objective of the class: Main application entry point and fullscreen window for welcome screen display.
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
 * 	- main() - Application entry point that launches the welcome screen
 *******************************************************************************************************************/

package welcoming;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;

public class MainWelcomeJFrame extends JFrame {
    private static final long serialVersionUID = 1L;
    private MainBackgroundJPanel backgrPanel;
    private WelcomeMenuJFrame welcMenuFrame;

    public MainWelcomeJFrame(String title) {
        super(title); 

        Dimension screen = this.getToolkit().getScreenSize();
        int frameW = screen.width;
        int frameH = screen.height;
        int menuW = frameW  / 3;
        int menuH = frameH / 3;
        
        initialization(frameW, frameH, menuW, menuH);
    }
    
    private void initialization(int frameW, int frameH, int menuW, int menuH) {
    	setBounds(0, 0, frameW, frameH);
    	
    	backgrPanel = new MainBackgroundJPanel();
    	
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(backgrPanel, BorderLayout.CENTER);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        
        welcMenuFrame = new WelcomeMenuJFrame(this, menuW, menuH);
    }
    
    public static void main(String[] args) {
        new MainWelcomeJFrame("Algorithms and Data Structures — Welcome");
    }
}