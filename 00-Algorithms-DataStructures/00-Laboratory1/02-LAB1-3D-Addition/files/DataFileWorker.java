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

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter; 

public class DataFileWorker {
    public SensorData loadData(Component parent) {
        
        JFileChooser fileChooser = new JFileChooser();

        File desktop = new File(System.getProperty("user.home"), "Desktop");
        if (desktop.exists()) fileChooser.setCurrentDirectory(desktop);

        fileChooser.setFileFilter(new FileNameExtensionFilter("Data files (*.txt, *.csv, *.json)", "txt", "csv", "json"));
        fileChooser.setMultiSelectionEnabled(false);
        fileChooser.setDialogTitle("Select a data file");

        int result = fileChooser.showOpenDialog(parent); 

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

                // Skip header/empty lines
                if (trimmedLine.isEmpty() || trimmedLine.startsWith("timestamp") || trimmedLine.equals("}") || trimmedLine.contains("first packet from")) {
                    continue; // Skip this line
                }

                // Append the cleaned data line
                lineBuffer.append(trimmedLine);
                
                // Check if we have a full set of 8 values
                String[] parts = lineBuffer.toString().split(",");
                if (parts.length >= 8) {
                    // We have enough parts, try to parse.
                    assignData(lineBuffer.toString(), sensorData);
                    lineBuffer.setLength(0); // Clear the buffer
                }
                // If we have < 8 parts, we just loop and append the next line
            }
            
            // Process any remaining data in the buffer
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
}