/*******************************************************************************************************************
 * Objective of the class: Main configuration panel that aggregates all control components and handles user events.
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
 * 	- setSliderEnabled() - Enables or disables the zoom slider based on zoom mode
 * 	- actionPerformed() - Handles all button and combo box events
 * 	- stateChanged() - Responds to slider value changes for zoom adjustement
 *******************************************************************************************************************/

package gui;

import java.awt.Component;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.border.EmptyBorder;
import javax.swing.border.CompoundBorder;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import drawingTool.RandomNumber;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingUtilities;

import files.DataFileWorker;
import files.SensorData;
import sorting.QuickSort;
import sorting.SelectionSort;

import javax.swing.JOptionPane;
import threeD.CubeRotationJFrame;

public class ConfigurationJPanel extends JPanel implements ChangeListener, ActionListener {

    private static final long serialVersionUID = 1L;

    private ButtonPanel buttonPanel;
    private ComboBoxPanel comboBoxPanel;
    private CheckBoxPanel checkBoxPanel;

    private JSlider scaleSlider = new JSlider(1, 100, 5);

    private DrawingJPanel drawingJPanel;
    private QuickSort quickSort;
    private SelectionSort selectionSort;

    private int randomDataCounter = 0;
    private SensorData previousSensorData = null;

    public ConfigurationJPanel(DrawingJPanel drawingJPanel, QuickSort quickSort) {
        super();
        this.drawingJPanel = drawingJPanel;
        this.quickSort = quickSort;
        initialization();
    }

    public enum EnumPlot1 {
        SENSOR1, SENSOR2, SENSOR3, SENSOR4;
    }
    public enum EnumPlot2 {
        ITEM1, ITEM2, ITEM3, ITEM4, ITEM5;
    }
    public enum EnumPlot3 {
        DATA1, DATA2, DATA3;
    }

    private void initialization() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(ModernTheme.PANEL_DARK);
        setBorder(new CompoundBorder(
            BorderFactory.createMatteBorder(0, 1, 0, 0, ModernTheme.BORDER_COLOR),
            new EmptyBorder(ModernTheme.PADDING_LARGE, ModernTheme.PADDING_MEDIUM, ModernTheme.PADDING_LARGE, ModernTheme.PADDING_MEDIUM)
        ));
        setPreferredSize(new Dimension(280, 0));

        add(Box.createVerticalStrut(10));
        JLabel title = new JLabel("Sensors Toolkit");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(ModernTheme.TITLE_FONT);
        title.setForeground(ModernTheme.TEXT_PRIMARY);
        add(title);
        add(Box.createVerticalStrut(20));

        buttonPanel = new ButtonPanel();
        add(buttonPanel);
        add(Box.createVerticalStrut(10));

        comboBoxPanel = new ComboBoxPanel();
        add(comboBoxPanel);

        JLabel analysisLabel = new JLabel("Analysis Features");
        analysisLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        analysisLabel.setFont(ModernTheme.LABEL_FONT);
        analysisLabel.setForeground(ModernTheme.TEXT_PRIMARY);
        add(analysisLabel);
        add(Box.createVerticalStrut(10));
        
        checkBoxPanel = new CheckBoxPanel();
        add(checkBoxPanel);
        add(Box.createVerticalStrut(30));

        JLabel sliderLabel = new JLabel("Zoom Slider");
        sliderLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        sliderLabel.setFont(ModernTheme.LABEL_FONT);
        sliderLabel.setForeground(ModernTheme.TEXT_PRIMARY);
        scaleSlider.setBackground(ModernTheme.PANEL_DARK);
        scaleSlider.setForeground(ModernTheme.ACCENT_BLUE);
        scaleSlider.setPaintTrack(true);
        scaleSlider.setPaintTicks(true);
        scaleSlider.setPaintLabels(true);
        scaleSlider.setMajorTickSpacing(0);
        add(sliderLabel);
        add(scaleSlider);
        add(Box.createVerticalStrut(30));
        
        add(Box.createVerticalGlue());

        addListeners();
    }

    private void addListeners() {
        buttonPanel.getImportBtn().addActionListener(this);
        buttonPanel.getResetView().addActionListener(this);
        buttonPanel.getShow3D().addActionListener(this);	
        buttonPanel.getQuickSort().addActionListener(this);
        buttonPanel.getSelectionSort().addActionListener(this);
        buttonPanel.getRandDataBtn().addActionListener(this);

        comboBoxPanel.getComboBox1().addActionListener(this);
        comboBoxPanel.getComboBox2().addActionListener(this);
        comboBoxPanel.getComboBox3().addActionListener(this);
        comboBoxPanel.getQuickSortBox().addActionListener(this);
        comboBoxPanel.getRandDataBox().addActionListener(this);
        
        checkBoxPanel.getZerosBox().addActionListener(this);
        checkBoxPanel.getExtremasBox().addActionListener(this);
        checkBoxPanel.getHistogramBox().addActionListener(this);
        
        scaleSlider.addChangeListener(this);
    }
    
    public void setSliderEnabled(boolean isEnabled) {
        scaleSlider.setEnabled(isEnabled);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        Object source = e.getSource();

        if (source == buttonPanel.getImportBtn()) {
            DataFileWorker worker = new DataFileWorker();
            SensorData data = worker.loadData(SwingUtilities.getWindowAncestor(this));

            if (data != null && !data.isEmpty()) {
                System.out.println("Data loaded: " + data.getSize() + " points");
                System.out.println("Columns: " + String.join(", ", data.getDataColumnNames()));
                System.out.println("First timestamp: " + data.getTimestamps().get(0));
                
                drawingJPanel.getScene().setSensorData(data);

                // provide data to sorting module
                quickSort.setSensorData(data);

                comboBoxPanel.populateComboBoxes(data.getDataColumnNames()); 

                comboBoxPanel.getComboBox1().addActionListener(this);
                comboBoxPanel.getComboBox2().addActionListener(this);
                comboBoxPanel.getComboBox3().addActionListener(this);
                comboBoxPanel.getQuickSortBox().addActionListener(this);

                if (data.getDataColumnNames().length > 0) {
                    drawingJPanel.getScene().getDataPlotter().setSelectedColumn1(data.getDataColumnNames()[0]);
                }
                
                randomDataCounter = 0;
                comboBoxPanel.getRandDataBox().removeActionListener(this);
                comboBoxPanel.getRandDataBox().removeAllItems();
                comboBoxPanel.getRandDataBox().addItem("Empty");
                comboBoxPanel.getRandDataBox().addActionListener(this);
                comboBoxPanel.getQuickSortBox().removeActionListener(this);
                comboBoxPanel.getQuickSortBox().removeAllItems();
                comboBoxPanel.getQuickSortBox().addItem("None");
                for (String column : data.getDataColumnNames()) {
                    comboBoxPanel.getQuickSortBox().addItem(column);
                }
                comboBoxPanel.getQuickSortBox().addActionListener(this);

                System.out.println("Imported " + data.getSize() + " data points)");
                drawingJPanel.requestFocusInWindow();
                drawingJPanel.repaint();
            }
        } 
        
        else if (source == buttonPanel.getResetView()) {
            drawingJPanel.getScene().resetZoom();
            scaleSlider.setEnabled(true);
            scaleSlider.setValue(5);
            drawingJPanel.repaint();
        } 
 
        else if (source == buttonPanel.getShow3D()) {
            SensorData data = drawingJPanel.getScene().getSensorData();
            if (data != null && !data.isEmpty()) {
                CubeRotationJFrame cubeFrame = new CubeRotationJFrame(data);
                cubeFrame.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(this), "Please import sensor data first.", "No Data", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        
        else if (source == buttonPanel.getRandDataBtn()) {
        	if (randomDataCounter == 0) {
        		previousSensorData = drawingJPanel.getScene().getSensorData();
        		System.out.println("Saved previous data: " + (previousSensorData == null ? "null" : previousSensorData.getSize() + " points"));
        	}
        	SensorData randomData = generateRandomData();
        	drawingJPanel.getScene().setSensorData(randomData);
        	comboBoxPanel.populateComboBoxes(randomData.getDataColumnNames());
        	
        	// Just add a new random data option to the combobox
        	comboBoxPanel.getRandDataBox().addItem("Random " + randomDataCounter);
        	comboBoxPanel.getRandDataBox().setSelectedIndex(comboBoxPanel.getRandDataBox().getItemCount() - 1);
        	comboBoxPanel.getQuickSortBox().addItem("Random " + randomDataCounter);
        	System.out.println("Generated Random " + randomDataCounter + " with " + randomData.getSize() + " data points");
        	randomDataCounter++;
        }
        
        else if (source == buttonPanel.getQuickSort()) {
        	SensorData data = drawingJPanel.getScene().getSensorData();
        	if (data != null && !data.isEmpty()) {
        		// ensure the sort data matches current selection
        		int index = comboBoxPanel.getQuickSortBox().getSelectedIndex();
        		if (index == 0) {
        			JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(this), "Please select a column to sort.", "No Column Selected", JOptionPane.INFORMATION_MESSAGE);
        		} else {
	        		String[] columns = data.getDataColumnNames();
	        		if (index - 1 < columns.length) {
	        			quickSort.setSortData(columns[index - 1]);
	        		}
	        		quickSort.start();
	        		
	        		drawingJPanel.getScene().getDataPlotter().setQuickSort(quickSort);
	        		drawingJPanel.getScene().getDataPlotter().isSorted(true);
	                drawingJPanel.repaint();
        		}
        	}
        	else {
        		JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(this), "Please import/choose sensor data first.", "No Data", JOptionPane.INFORMATION_MESSAGE);
        	}
        }
        
        else if (source == buttonPanel.getSelectionSort()) {
        	SensorData data = drawingJPanel.getScene().getSensorData();
        	if (data != null && !data.isEmpty()) {
        		// ensure the sort data matches current selection
        		int index = comboBoxPanel.getQuickSortBox().getSelectedIndex();
        		if (index == 0) {
        			JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(this), "Please select a column to sort.", "No Column Selected", JOptionPane.INFORMATION_MESSAGE);
        		} else {
	        		String[] columns = data.getDataColumnNames();
	        		if (index - 1 < columns.length) {
	        			quickSort.setSortData(columns[index - 1]);
	        		}
	        		quickSort.start();
	        		
	        		drawingJPanel.getScene().getDataPlotter().setQuickSort(quickSort);
	        		drawingJPanel.getScene().getDataPlotter().isSorted(true);
	                drawingJPanel.repaint();
        		}
        	}
        	else {
        		JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(this), "Please import/choose sensor data first.", "No Data", JOptionPane.INFORMATION_MESSAGE);
        	}
        }
        
        else if (source == comboBoxPanel.getComboBox1()) {
        	
            int index = comboBoxPanel.getComboBox1().getSelectedIndex();
            if (index < 1) { 
                drawingJPanel.getScene().getDataPlotter().setSelectedColumn1(null);
            } else if (drawingJPanel.getScene().getSensorData() != null) {
                String[] columns = drawingJPanel.getScene().getSensorData().getDataColumnNames();
                if (index - 1 < columns.length) {
                    drawingJPanel.getScene().getDataPlotter().setSelectedColumn1(columns[index - 1]);
                }
            }
            drawingJPanel.getScene().rescaleToSelectedData();
            drawingJPanel.repaint();
        } 
        else if (source == comboBoxPanel.getComboBox2()) {
        	
            int index = comboBoxPanel.getComboBox2().getSelectedIndex();
            if (index < 1) {
                drawingJPanel.getScene().getDataPlotter().setSelectedColumn2(null);
            } else if (drawingJPanel.getScene().getSensorData() != null) {
                String[] columns = drawingJPanel.getScene().getSensorData().getDataColumnNames();
                if (index - 1 < columns.length) {
                    drawingJPanel.getScene().getDataPlotter().setSelectedColumn2(columns[index - 1]);
                }
            }
            drawingJPanel.getScene().rescaleToSelectedData();
            drawingJPanel.repaint();
        } 
        else if (source == comboBoxPanel.getComboBox3()) {
        	
            int index = comboBoxPanel.getComboBox3().getSelectedIndex();
            if (index < 1) {
                drawingJPanel.getScene().getDataPlotter().setSelectedColumn3(null);
            } else if (drawingJPanel.getScene().getSensorData() != null) {
                String[] columns = drawingJPanel.getScene().getSensorData().getDataColumnNames();
                if (index - 1 < columns.length) {
                    drawingJPanel.getScene().getDataPlotter().setSelectedColumn3(columns[index - 1]);
                }
            }
            drawingJPanel.getScene().rescaleToSelectedData();
            drawingJPanel.repaint();
        } 
        
        else if (source == comboBoxPanel.getRandDataBox()) {
        	int index = comboBoxPanel.getRandDataBox().getSelectedIndex();
        	
        	if (index == 0) {
        		System.out.println("Restoring previous data: " + (previousSensorData == null ? "null" : previousSensorData.getSize() + " points"));
        		drawingJPanel.getScene().setSensorData(previousSensorData);
        		randomDataCounter = 0;
        		comboBoxPanel.getRandDataBox().removeActionListener(this);
        		comboBoxPanel.getRandDataBox().removeAllItems();
        		comboBoxPanel.getRandDataBox().addItem("Empty");
        		comboBoxPanel.getRandDataBox().addActionListener(this);
        		comboBoxPanel.getQuickSortBox().removeActionListener(this);
        		comboBoxPanel.getQuickSortBox().removeAllItems();
        		comboBoxPanel.getQuickSortBox().addItem("None");
        		if (previousSensorData != null) {
        			for (String column : previousSensorData.getDataColumnNames()) {
        				comboBoxPanel.getQuickSortBox().addItem(column);
        			}
        		}
        		comboBoxPanel.getQuickSortBox().addActionListener(this);
        		if (previousSensorData != null) {
        			comboBoxPanel.populateComboBoxes(previousSensorData.getDataColumnNames());
        			drawingJPanel.getScene().getDataPlotter().setSelectedColumn1(previousSensorData.getDataColumnNames()[0]);
        		} else {
        			drawingJPanel.getScene().getDataPlotter().setSelectedColumn1(null);
        			drawingJPanel.getScene().getDataPlotter().setSelectedColumn2(null);
        			drawingJPanel.getScene().getDataPlotter().setSelectedColumn3(null);
        		}
        	} else {
        		SensorData randomData = drawingJPanel.getScene().getSensorData();
        		drawingJPanel.getScene().getDataPlotter().setSelectedColumn1(randomData.getDataColumnNames()[0]);
        	}
        	
        	drawingJPanel.getScene().rescaleToSelectedData();
        	drawingJPanel.repaint();
        }
        
        else if (source == comboBoxPanel.getQuickSortBox()) {
            int index = comboBoxPanel.getQuickSortBox().getSelectedIndex();
            
            if (index < 1) {
                quickSort.setSortData(null);
            } else if (drawingJPanel.getScene().getSensorData() != null) {
                String[] columns = drawingJPanel.getScene().getSensorData().getDataColumnNames();
                if (index - 1 < columns.length) {
                    quickSort.setSortData(columns[index - 1]);
                }
            }
        }
        
        else if (source == checkBoxPanel.getZerosBox()) {
            drawingJPanel.getScene().getDataPlotter().setShowZeros(checkBoxPanel.getZerosBox().isSelected());
            drawingJPanel.repaint();
        } 
        else if (source == checkBoxPanel.getExtremasBox()) {
            drawingJPanel.getScene().getDataPlotter().setShowExtremas(checkBoxPanel.getExtremasBox().isSelected());
            drawingJPanel.repaint();
        } 
        else if (source == checkBoxPanel.getHistogramBox()) {
            drawingJPanel.getScene().getDataPlotter().setShowHistogram(checkBoxPanel.getHistogramBox().isSelected());
            drawingJPanel.repaint();
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if (scaleSlider.isEnabled()) {
            drawingJPanel.getScene().sliderZoom(scaleSlider.getValue());
            drawingJPanel.repaint();
        }
    }
    
    private SensorData generateRandomData() {
    	SensorData randomData = new SensorData();
    	double randomPoint;
    	
    	double maxTimestamp = 10000.0;
    	SensorData existingData = previousSensorData != null ? previousSensorData : drawingJPanel.getScene().getSensorData();
    	if (existingData != null && !existingData.isEmpty()) {
    		ArrayList<Double> timestamps = existingData.getTimestamps();
    		maxTimestamp = timestamps.get(timestamps.size() - 1);
    	}
    	
    	double timeStep = maxTimestamp / 999.0;
    	
    	for (int i = 0; i < 1000; i++) {
    		double timestamp = i * timeStep;
    		randomPoint = RandomNumber.between(-100, 100) / 100.0;
    		randomData.addDataPoint(timestamp, randomPoint, 0, 0, 0, 0, 0, 0);
    	}
    	
    	return randomData;
    }
}