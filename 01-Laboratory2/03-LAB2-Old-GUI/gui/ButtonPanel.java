/*******************************************************************************************************************
 * Objective of the class: Panel containing action buttons for file import and view reset functionality.
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
 * 	- getResetView() - Returns the reset view button for event listener attachment
 * 	- getImportBtn() - Returns the import button for file selection functionality
 *******************************************************************************************************************/

package gui;

import java.awt.Component;
import java.awt.Font;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ButtonPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private JButton resetView = new JButton("Reset View");
    private JButton importBtn = new JButton("Import File");
    private JButton show3D = new JButton("Show in 3D");
    private JButton quickSort = new JButton("Quicksort");
    private JButton selectionSort = new JButton("Selection sort");
    private JButton randomData = new JButton("Generate Random Data");

    public ButtonPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        importBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(importBtn);
        add(Box.createVerticalStrut(20));

        resetView.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(resetView);
        add(Box.createVerticalStrut(20));
        
        show3D.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(show3D);
        add(Box.createVerticalStrut(20));
        
        randomData.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(randomData);
        add(Box.createVerticalStrut(20));
        
        quickSort.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(quickSort);
        add(Box.createVerticalStrut(20));
        
        selectionSort.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(selectionSort);
    }

    public JButton getResetView() {
        return resetView;
    }

    public JButton getImportBtn() {
        return importBtn;
    }
    
    public JButton getShow3D() {
        return show3D;
    }
    
    public JButton getRandDataBtn() {
        return randomData;
    }
    
    public JButton getQuickSort() {
        return quickSort;
    }
    
    public JButton getSelectionSort() {
        return selectionSort;
    }
}