/*******************************************************************************************************************
 * Objective of the program: A Swing-based Java application for plotting and visualizing sensor data. The application
 * provides an interactive coordinate system with zooming and panning capabilities. Users can import sensor data files
 * (txt, csv, json formats) through a file chooser dialog. A side panel provides controls for plot configuration,
 * zoom level adjustment via slider, and plot selection through combo boxes. The GUI uses event-driven architecture
 * where actions propagate to the Scene object, which manages viewport transformations and triggers repaint. The
 * coordinate system supports both mouse-based rectangular zoom selection and slider-based zoom controls. Background
 * threading via SwingWorker ensures the GUI remains responsive during file I/O operations. The final deliverable
 * demonstrates aggregation vs composition distinctions, event-driven GUI, coordinate transformations, and proper
 * separation of concerns between GUI components and data visualization logic.
 *******************************************************************************************************************
 * Author: Luciano Carricart, https://github.com/lcarricart/
 * Status: Information Engineering student at HAW Hamburg, Germany.
 * Profile: https://www.linkedin.com/in/lucianocarricart/
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