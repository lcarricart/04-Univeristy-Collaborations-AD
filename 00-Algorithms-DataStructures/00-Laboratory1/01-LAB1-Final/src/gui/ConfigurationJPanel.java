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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

import files.DataFileWorker;
import files.SensorData;

public class ConfigurationJPanel extends JPanel implements ChangeListener, ActionListener {
    private static final long serialVersionUID = 1L;
    private ButtonPanel buttonPanel;
    private ComboBoxPanel comboBoxPanel;
    private CheckBoxPanel checkBoxPanel;
    private DrawingJPanel drawingJPanel;
    private JSlider scaleSlider = new JSlider(1, 100, 5);

    public ConfigurationJPanel(DrawingJPanel drawingJPanel) {
        super();
        this.drawingJPanel = drawingJPanel;
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

        add(Box.createVerticalStrut(20));
        JLabel title = new JLabel("  Sensors Toolkit  ");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(title);
        add(Box.createVerticalStrut(15));

        buttonPanel = new ButtonPanel();
        add(buttonPanel);

        comboBoxPanel = new ComboBoxPanel();
        add(comboBoxPanel);

        JLabel analysisLabel = new JLabel("Analysis Features");
        analysisLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(analysisLabel);
        add(Box.createVerticalStrut(10));
        
        checkBoxPanel = new CheckBoxPanel();
        add(checkBoxPanel);

        JLabel sliderLabel = new JLabel("Zoom Slider");
        sliderLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
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

        comboBoxPanel.getComboBox1().addActionListener(this);
        comboBoxPanel.getComboBox2().addActionListener(this);
        comboBoxPanel.getComboBox3().addActionListener(this);
        
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
            SensorData data = worker.loadData(this);

            if (data != null && !data.isEmpty()) {
                drawingJPanel.getScene().setSensorData(data);

                comboBoxPanel.populateComboBoxes(data.getDataColumnNames()); 

                comboBoxPanel.getComboBox1().addActionListener(this);
                comboBoxPanel.getComboBox2().addActionListener(this);
                comboBoxPanel.getComboBox3().addActionListener(this);

                if (data.getDataColumnNames().length > 0) {
                    drawingJPanel.getScene().getFunction().setSelectedColumn1(data.getDataColumnNames()[0]);
                }

                System.out.println("Imported " + data.getSize() + " data points");
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
 
        else if (source == comboBoxPanel.getComboBox1()) {
        	
            int index = comboBoxPanel.getComboBox1().getSelectedIndex();
            if (index < 1) { 
                drawingJPanel.getScene().getFunction().setSelectedColumn1(null);
            } else if (drawingJPanel.getScene().getSensorData() != null) {
                String[] columns = drawingJPanel.getScene().getSensorData().getDataColumnNames();
                if (index - 1 < columns.length) {
                    drawingJPanel.getScene().getFunction().setSelectedColumn1(columns[index - 1]);
                }
            }
            drawingJPanel.getScene().rescaleToSelectedData();
            drawingJPanel.repaint();
        } 
        else if (source == comboBoxPanel.getComboBox2()) {
        	
            int index = comboBoxPanel.getComboBox2().getSelectedIndex();
            if (index < 1) {
                drawingJPanel.getScene().getFunction().setSelectedColumn2(null);
            } else if (drawingJPanel.getScene().getSensorData() != null) {
                String[] columns = drawingJPanel.getScene().getSensorData().getDataColumnNames();
                if (index - 1 < columns.length) {
                    drawingJPanel.getScene().getFunction().setSelectedColumn2(columns[index - 1]);
                }
            }
            drawingJPanel.getScene().rescaleToSelectedData();
            drawingJPanel.repaint();
        } 
        else if (source == comboBoxPanel.getComboBox3()) {
        	
            int index = comboBoxPanel.getComboBox3().getSelectedIndex();
            if (index < 1) {
                drawingJPanel.getScene().getFunction().setSelectedColumn3(null);
            } else if (drawingJPanel.getScene().getSensorData() != null) {
                String[] columns = drawingJPanel.getScene().getSensorData().getDataColumnNames();
                if (index - 1 < columns.length) {
                    drawingJPanel.getScene().getFunction().setSelectedColumn3(columns[index - 1]);
                }
            }
            drawingJPanel.getScene().rescaleToSelectedData();
            drawingJPanel.repaint();
        } 
        else if (source == checkBoxPanel.getZerosBox()) {
            drawingJPanel.getScene().getFunction().setShowZeros(checkBoxPanel.getZerosBox().isSelected());
            drawingJPanel.repaint();
        } 
        else if (source == checkBoxPanel.getExtremasBox()) {
            drawingJPanel.getScene().getFunction().setShowExtremas(checkBoxPanel.getExtremasBox().isSelected());
            drawingJPanel.repaint();
        } 
        else if (source == checkBoxPanel.getHistogramBox()) {
            drawingJPanel.getScene().getFunction().setShowHistogram(checkBoxPanel.getHistogramBox().isSelected());
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
}