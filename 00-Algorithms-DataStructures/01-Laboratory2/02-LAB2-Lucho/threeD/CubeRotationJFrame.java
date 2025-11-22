package threeD;

import javax.swing.JFrame;
import files.SensorData;
import java.awt.BorderLayout;

public class CubeRotationJFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private CubeRotationJPanel cubePanel;
    private ThreeD_OperatorsJPanel operatorsPanel;

    public CubeRotationJFrame(SensorData sensorData) {
        super("3D Sensor View");

        cubePanel = new CubeRotationJPanel(sensorData);
        
        // Get the data size and pass it to the operators panel
        int dataSize = (sensorData != null) ? sensorData.getSize() : 0;
        operatorsPanel = new ThreeD_OperatorsJPanel(cubePanel, dataSize);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(cubePanel, BorderLayout.CENTER);
        getContentPane().add(operatorsPanel, BorderLayout.SOUTH);
        
        // Increased height from 400 to 450 to make room for the slider
        setSize(400, 450); 
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE); 
    }
}