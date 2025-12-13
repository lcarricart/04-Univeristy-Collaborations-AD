package files;

import java.awt.Component;
import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;
import java.util.Locale;
import java.util.ArrayList;
import java.util.Collections;
import java.util.function.Consumer;

import javax.swing.SwingWorker;
import javax.swing.JOptionPane;

// <SensorData, Void>. The first parameter is the result type, and the second is used for progress updates (since we don't use it, we define it Void)
// This  SwingWorker class in Java is part of the Swing library and is designed to handle long-running tasks in a background thread while keeping the GUI responsive.
public class DataFileWorker extends SwingWorker<SensorData, Void> {
    private final File file;
    private Exception error;
    private final Component dialogParent;                  
    private final Consumer<SensorData> onSuccess;

    public DataFileWorker(File file, Component dialogParent, Consumer<SensorData> onSuccess) {
        this.file = file;
        this.dialogParent = dialogParent;
        this.onSuccess = onSuccess;
    }

    @Override
    // These methods are defined protected in the superclass SwingWorker
    protected SensorData doInBackground() {
        try {
            String name = file.getName().toLowerCase(Locale.ROOT);
            // if (name.endsWith(".csv"))   return this.parseCsv(file);
            // if (name.endsWith(".json"))  return this.parseJson(file);
            if (name.endsWith(".txt"))      return this.parseTxt(file);
            return this.parseTxt(file); // fallback
        } catch (Exception ex) {
            error = ex;
            return new SensorData();
        }
    }

    @Override
    protected void done() {
        if (error != null) {
            JOptionPane.showMessageDialog(
                dialogParent,
                "Failed to load file:\n" + error.getMessage(),
                "Import Error",
                JOptionPane.ERROR_MESSAGE
            );
            error.printStackTrace();
            return;
        }
        try {
            SensorData data = get();
            if (onSuccess != null) onSuccess.accept(data);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private SensorData parseTxt(File file) throws IOException {
    	SensorData data = new SensorData();

        // Fixed positions (0-based)
        final int TIMESTAMP = 0;
        final int ACC_X = 1;
        final int ACC_Y = 2;
        final int ACC_Z = 3;
        final int GYRO_X = 4;
        final int GYRO_Y = 5;
        final int GYRO_Z = 6;
        final int TEMPERATURE = 7;

        try (BufferedReader br = Files.newBufferedReader(file.toPath(), StandardCharsets.UTF_8)) {
            String line;

            // 1) Skip leading log/meta lines like: [2025-10-19 ...]
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty() && !line.startsWith("[")) break; // this should be the header
            }
            if (line == null) return data; // no header/data found

            // 2) We assume fixed column order from your header; move on to data rows

            // 3) Parse data lines
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty() || line.startsWith("[")) continue; // skip blanks or stray logs
                String[] parts = line.split(",");
                if (parts.length < 8) continue; // need all 8 columns

                try {
                    double timestamp = Double.parseDouble(parts[TIMESTAMP].trim());
                    double accX = Double.parseDouble(parts[ACC_X].trim());
                    double accY = Double.parseDouble(parts[ACC_Y].trim());
                    double accZ = Double.parseDouble(parts[ACC_Z].trim());
                    double gyroX = Double.parseDouble(parts[GYRO_X].trim());
                    double gyroY = Double.parseDouble(parts[GYRO_Y].trim());
                    double gyroZ = Double.parseDouble(parts[GYRO_Z].trim());
                    double temp = Double.parseDouble(parts[TEMPERATURE].trim());
                    
                    data.addDataPoint(timestamp, accX, accY, accZ, gyroX, gyroY, gyroZ, temp);
                } catch (NumberFormatException ignore) {
                    // skip malformed numeric rows
                }
            }
        }
        return data;
    }
}