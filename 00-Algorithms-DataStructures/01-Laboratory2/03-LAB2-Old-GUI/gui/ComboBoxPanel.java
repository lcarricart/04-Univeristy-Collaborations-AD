/*******************************************************************************************************************
 * Objective of the class: Panel with dropdown menus for selecting which sensor data columns to plot.
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
 * 	- populateComboBoxes() - Fills combo boxes with available data column names from imported file
 * 	- getComboBox1() - Returns first combo box for plot selection
 * 	- getComboBox2() - Returns second combo box for plot selection
 * 	- getComboBox3() - Returns third combo box for plot selection
 *******************************************************************************************************************/

package gui;

import java.awt.Component;
import java.awt.Dimension;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ComboBoxPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private String[] options = {"None", "None", "None", "None"};
    private String[] options2 = {"None", "None", "None", "None", "None"};
    private String[] options3 = {"None", "None", "None"};
    private String[] options4 = {"None"};
    private String[] options5 = {"None"};
    private JComboBox<String> comboBox = new JComboBox<>(options);
    private JComboBox<String> comboBox2 = new JComboBox<>(options2);
    private JComboBox<String> comboBox3 = new JComboBox<>(options3);
    private JComboBox<String> quickSortBox = new JComboBox<>(options4);
    private JComboBox<String> randDataBox = new JComboBox<>(options5);

    public ComboBoxPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        int desiredHeight = 20;
        
        JLabel comboLabel_QuickSort = new JLabel("Choose the data to sort");
        quickSortBox.setMaximumSize(new Dimension(Integer.MAX_VALUE, desiredHeight));
        comboLabel_QuickSort.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(comboLabel_QuickSort);
        add(quickSortBox);
        add(Box.createVerticalStrut(60));

        JLabel comboLabel = new JLabel("Plot 1");

        comboBox.setMaximumSize(new Dimension(Integer.MAX_VALUE, desiredHeight));
        comboLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(comboLabel);
        add(comboBox);
        add(Box.createVerticalStrut(20));

        JLabel comboLabel2 = new JLabel("Plot 2");

        comboBox2.setMaximumSize(new Dimension(Integer.MAX_VALUE, desiredHeight));
        comboLabel2.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(comboLabel2);
        add(comboBox2);
        add(Box.createVerticalStrut(20));

        JLabel comboLabel3 = new JLabel("Plot 3");

        comboBox3.setMaximumSize(new Dimension(Integer.MAX_VALUE, desiredHeight));
        comboLabel3.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(comboLabel3);
        add(comboBox3);
        add(Box.createVerticalStrut(20));
        
        JLabel randBoxLabel = new JLabel("Randomized Data");
        
        randDataBox.setMaximumSize(new Dimension(Integer.MAX_VALUE, desiredHeight));
        randBoxLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(randBoxLabel);
        add(randDataBox);
        add(Box.createVerticalStrut(40));
    }
    
    public void populateComboBoxes(String[] dataColumns) {
        comboBox.removeAllItems();
        comboBox2.removeAllItems();
        comboBox3.removeAllItems();
        
        comboBox.addItem("None");
        comboBox2.addItem("None");
        comboBox3.addItem("None");

        for (String column : dataColumns) {
            comboBox.addItem(column);
            comboBox2.addItem(column);
            comboBox3.addItem(column);
        }
    }
    
    public JComboBox<String> getQuickSortBox() {
        return quickSortBox;
    }

    public JComboBox<String> getComboBox1() {
        return comboBox;
    }

    public JComboBox<String> getComboBox2() {
        return comboBox2;
    }

    public JComboBox<String> getComboBox3() {
        return comboBox3;
    }
    
    public JComboBox<String> getRandDataBox() {
        return randDataBox;
    }
}