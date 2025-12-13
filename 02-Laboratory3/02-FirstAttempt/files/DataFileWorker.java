/*******************************************************************************************************************
 * Objective of the class: Handles file selection and parsing of sensor data from various file formats.
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
 * 	- loadData() - Opens file chooser dialog and loads selected data file into SensorData object
 *******************************************************************************************************************/

package files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.awt.Component;
import java.awt.Color;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;
import gui.ModernTheme; 

public class DataFileWorker {
    public SensorData loadData(Component parent) {
        
        // Apply modern theme to file chooser
        applyModernFileChooserTheme();
        
        JFileChooser fileChooser = new JFileChooser();
        
        // Style the file chooser
        fileChooser.setBackground(ModernTheme.PANEL_DARK);
        fileChooser.setForeground(ModernTheme.TEXT_PRIMARY);

        File desktop = new File(System.getProperty("user.home"), "Desktop");
        if (desktop.exists()) fileChooser.setCurrentDirectory(desktop);

        fileChooser.setFileFilter(new FileNameExtensionFilter("Data files (*.txt, *.csv, *.json)", "txt", "csv", "json"));
        fileChooser.setMultiSelectionEnabled(false);
        fileChooser.setDialogTitle("Select a data file");

        int result = fileChooser.showOpenDialog(parent);
        
        // Restore default theme
        restoreDefaultTheme(); 

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            SensorData returnValue = parseFileToSensorData(selectedFile, parent);
            return returnValue;
        }
        
        return null;
    }

    private SensorData parseFileToSensorData(File file, Component parent) {
        
        SensorData sensorData = new SensorData();
        StringBuilder lineBuffer = new StringBuilder();
    
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
        	
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                
                String trimmedLine = currentLine.trim();

                if (trimmedLine.isEmpty() || trimmedLine.startsWith("[") || trimmedLine.startsWith("timestamp") || trimmedLine.equals("}")) {
                    continue;
                }

                assignData(lineBuffer.toString(), sensorData);
                lineBuffer.setLength(0);
                lineBuffer.append(trimmedLine);
            }
            
            if (lineBuffer.length() > 0) {
                assignData(lineBuffer.toString(), sensorData);
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(parent, "Error reading file: " + e.getMessage(), "File Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return null;
        }
        
        return sensorData;
    }

    private void assignData(String line, SensorData sensorData) {
    	
        String[] parts = line.split(",");

        if (parts.length == 8) {
        	
            double timestamp = Double.parseDouble(parts[0].trim());
            double accX = Double.parseDouble(parts[1].trim());
            double accY = Double.parseDouble(parts[2].trim());
            double accZ = Double.parseDouble(parts[3].trim());
            double gyroX = Double.parseDouble(parts[4].trim());
            double gyroY = Double.parseDouble(parts[5].trim());
            double gyroZ = Double.parseDouble(parts[6].trim());
            double temperature = Double.parseDouble(parts[7].trim());
            
            sensorData.addDataPoint(timestamp, accX, accY, accZ, gyroX, gyroY, gyroZ, temperature);
         }
    }
    
    private void applyModernFileChooserTheme() {
        // Save original colors for restoration
        UIManager.put("FileChooser.background", ModernTheme.PANEL_DARK);
        UIManager.put("Panel.background", ModernTheme.PANEL_DARK);
        UIManager.put("Label.foreground", ModernTheme.TEXT_PRIMARY);
        UIManager.put("List.background", ModernTheme.CONTROL_BG);
        UIManager.put("List.foreground", ModernTheme.TEXT_PRIMARY);
        UIManager.put("List.selectionBackground", ModernTheme.ACCENT_BLUE);
        UIManager.put("List.selectionForeground", ModernTheme.TEXT_PRIMARY);
        UIManager.put("Table.background", ModernTheme.CONTROL_BG);
        UIManager.put("Table.foreground", ModernTheme.TEXT_PRIMARY);
        UIManager.put("Table.selectionBackground", ModernTheme.ACCENT_BLUE);
        UIManager.put("Table.selectionForeground", ModernTheme.TEXT_PRIMARY);
        UIManager.put("TextField.background", ModernTheme.CONTROL_BG);
        UIManager.put("TextField.foreground", ModernTheme.TEXT_PRIMARY);
        UIManager.put("Button.background", ModernTheme.CONTROL_BG);
        UIManager.put("Button.foreground", ModernTheme.TEXT_PRIMARY);
        UIManager.put("ComboBox.background", ModernTheme.CONTROL_BG);
        UIManager.put("ComboBox.foreground", ModernTheme.TEXT_PRIMARY);
    }
    
    private void restoreDefaultTheme() {
        // Reset to system defaults after file chooser closes
        UIManager.put("FileChooser.background", null);
        UIManager.put("Panel.background", null);
        UIManager.put("Label.foreground", null);
        UIManager.put("List.background", null);
        UIManager.put("List.foreground", null);
        UIManager.put("List.selectionBackground", null);
        UIManager.put("List.selectionForeground", null);
        UIManager.put("Table.background", null);
        UIManager.put("Table.foreground", null);
        UIManager.put("Table.selectionBackground", null);
        UIManager.put("Table.selectionForeground", null);
        UIManager.put("TextField.background", null);
        UIManager.put("TextField.foreground", null);
        UIManager.put("Button.background", null);
        UIManager.put("Button.foreground", null);
        UIManager.put("ComboBox.background", null);
        UIManager.put("ComboBox.foreground", null);
    }
}