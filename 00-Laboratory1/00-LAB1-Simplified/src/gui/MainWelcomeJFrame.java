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

package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;

public class MainWelcomeJFrame extends JFrame {
    // The actual numeric value (here 1L) is arbitrary; you can pick any long literal.
    private static final long serialVersionUID = 1L;
    private MainBackgroundJPanel backgrPanel;
    private WelcomeMenuJFrame welcMenuFrame;

    public MainWelcomeJFrame(String title) {
        super(title); // inherit JFrame initialisation

        // Make this window full-screen
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
    	
        // Add the cloudy background panel
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(backgrPanel, BorderLayout.CENTER);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        
        welcMenuFrame = new WelcomeMenuJFrame(this, menuW, menuH);
    }
    
    // This is where my program starts
    public static void main(String[] args) {
        new MainWelcomeJFrame("Algorithms and Data Structures — Welcome");
    }
}














// I couldn't find JFrame library (or superclass), but this is what it looks like:
// The constructor is overloaded and accepts many parameters. If passed one value it automatically sets it as the title.

//package javax.swing;
//
//import java.awt.GraphicsConfiguration;
//import java.awt.HeadlessException;
//
///**
// * A top‐level window with a title and a content pane.
// */
//public class JFrame extends Frame {
//    private static final String ROOTPANE_BUTTON_KEY = "RootPane.windowDecorationButton"; // (internal detail)
//
//    /**
//     * Creates a <code>JFrame</code> that is initially invisible
//     * and has no title.
//     *
//     * @throws HeadlessException if GraphicsEnvironment.isHeadless()
//     *         returns true
//     */
//    public JFrame() throws HeadlessException {
//        this("", (GraphicsConfiguration) null);
//    }
//
//    /**
//     * Creates a <code>JFrame</code> that is initially invisible
//     * and has the specified title.
//     *
//     * @param title  the String to display in the frame's title bar
//     * @throws HeadlessException if GraphicsEnvironment.isHeadless()
//     *         returns true
//     */
//    public JFrame(String title) throws HeadlessException {
//        this(title, (GraphicsConfiguration) null);
//    }
//
//    /**
//     * Creates a <code>JFrame</code> that is initially invisible
//     * and is given a specific GraphicsConfiguration.
//     *
//     * @param gc  the GraphicsConfiguration of the target screen device
//     * @throws HeadlessException if GraphicsEnvironment.isHeadless()
//     *         returns true
//     */
//    public JFrame(GraphicsConfiguration gc) throws HeadlessException {
//        this("", gc);
//    }
//
//    /**
//     * Creates a <code>JFrame</code> that is initially invisible,
//     * has the specified title, and uses the specified GraphicsConfiguration.
//     *
//     * @param title  the String to display in the frame's title bar
//     * @param gc     the GraphicsConfiguration of the target screen device
//     * @throws HeadlessException if GraphicsEnvironment.isHeadless()
//     *         returns true
//     */
//    public JFrame(String title, GraphicsConfiguration gc) throws HeadlessException {
//        super(title, gc);
//        // …internal initialization (look‐and‐feel, root pane, etc.) happens here…
//        setRootPane(createRootPane());
//        // …more initialization…
//    }
//
//    // …plus various protected/private methods …
//}