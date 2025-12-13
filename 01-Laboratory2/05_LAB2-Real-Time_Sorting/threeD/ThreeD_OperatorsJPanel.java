package threeD;

import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import java.util.Hashtable;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ThreeD_OperatorsJPanel extends JPanel implements ChangeListener {
	
	private static final long serialVersionUID = 1L;
    private JSlider timeSlider;
    private CubeRotationJPanel cubePanel;
   
    public ThreeD_OperatorsJPanel(CubeRotationJPanel cubePanel, int dataSize) {
        
        this.cubePanel = cubePanel;
        
        int maxIndex = (dataSize > 0) ? dataSize - 1 : 0;
        
        timeSlider = new JSlider(JSlider.HORIZONTAL, 0, maxIndex, 0);

        JLabel sliderLabel = new JLabel("Time Index (ms):");
        sliderLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        timeSlider.setPaintTrack(true);
        timeSlider.setPaintTicks(true);
        
        // Create a table for custom labels
        Hashtable<Integer, JLabel> labelTable = new Hashtable<>();
        
        // Add only the first label
        labelTable.put(0, new JLabel("0"));
        
        // Add only the last label
        labelTable.put(maxIndex, new JLabel(String.valueOf(maxIndex)));

        timeSlider.setLabelTable(labelTable);
        timeSlider.setPaintLabels(true);

        add(sliderLabel);
        add(timeSlider);
        
        timeSlider.addChangeListener(this);
    }
    
    @Override
    public void stateChanged(ChangeEvent e) {
        if (timeSlider.isEnabled()) {
            int newIndex = timeSlider.getValue();
            cubePanel.setTimestamp(newIndex); 
            cubePanel.repaint(); 
        }
    }
}