package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.util.List;
import java.util.function.Consumer;


// Import file dependencies
import java.io.File;
import javax.swing.JFrame;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import files.DataFileWorker;

public class DrawingJFrame extends JFrame {
    private static final long serialVersionUID = 1L;
    private int basicScale = 10;
    
    private DrawingJPanel drawingJPanel;
    private ConfigurationJPanel configJPanel;

    public DrawingJFrame(String title) {
        super(title);

        Dimension screen = this.getToolkit().getScreenSize();
        int frameW = screen.width;
        int frameH = screen.height;
        
        initialization(frameW, frameH);
    }
    
    private void initialization(int frameW, int frameH) {
    	setBounds(0, 0, frameW, frameH);
        getContentPane().setLayout(new BorderLayout());
        
        drawingJPanel = new DrawingJPanel(frameW, frameH, this);
        configJPanel = new ConfigurationJPanel(drawingJPanel);
        
        // Adds the drawingJPanel to the frame, where the sensor data plots are drawn
        getContentPane().add(drawingJPanel, BorderLayout.CENTER);
        // Adds the ConfigurationJPanel to the same frame, and therefore they're placed together and can interact in the same frame window.
        getContentPane().add(configJPanel, BorderLayout.EAST);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        
        configJPanel.getImportBtn().addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		JFileChooser chooser = new JFileChooser();

                // Start in Desktop
                File desktop = new File(System.getProperty("user.home"), "Desktop");
                if (desktop.exists()) chooser.setCurrentDirectory(desktop);

                // Data file filter
                chooser.setFileFilter(new FileNameExtensionFilter("Data files (*.txt, *.csv, *.json)", "txt", "csv", "json"));

                chooser.setMultiSelectionEnabled(false);
                chooser.setDialogTitle("Select a data file");

                int result = chooser.showOpenDialog(DrawingJFrame.this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selected = chooser.getSelectedFile();
                    
                    // Use DataFileWorker to load the file in background
                    DataFileWorker worker = new DataFileWorker(selected, DrawingJFrame.this, data -> {
                        // This callback runs when file loading is complete
                        if (data != null && !data.isEmpty()) {
                            drawingJPanel.getScene().setSensorData(data);
                            configJPanel.populateComboBoxes(data.getDataColumnNames());
                            
                            // Auto-select first column for Plot 1
                            if (data.getDataColumnNames().length > 0) {
                                drawingJPanel.getScene().getFunction().setSelectedColumn1(data.getDataColumnNames()[0]);
                            }
                            
                            System.out.println("Imported: " + selected.getAbsolutePath() + " (" + data.getSize() + " data points)");
                            drawingJPanel.requestFocusInWindow();
                            drawingJPanel.repaint();
                        }
                    });
                    worker.execute();
                }
        	}
        });
        
        configJPanel.getResetView().addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		drawingJPanel.getScene().resetZoom();
                configJPanel.setSliderEnabled(true);
                configJPanel.getScaleSlider().setValue(5);
                drawingJPanel.repaint();
        	}
        });
        
        configJPanel.getZoomInBtn().addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		increaseBasicScale();
        		drawingJPanel.getScene().setScale(basicScale);
        		drawingJPanel.repaint();
        	}
        });
        
        configJPanel.getZoomOutBtn().addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		decreaseBasicScale();
        		drawingJPanel.getScene().setScale(basicScale);
        		drawingJPanel.repaint();
        	}
        });
    }
    
    private void increaseBasicScale() {
        basicScale += 10;
    }

    private void decreaseBasicScale() {
        if (basicScale > 10) {
            basicScale -= 10;
        }
    }
    
    public ConfigurationJPanel getConfigurationJPanel() {
    	return configJPanel;
    }
}