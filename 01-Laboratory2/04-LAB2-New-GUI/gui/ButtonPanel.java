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
import java.awt.Dimension;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.BorderFactory;

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
        setOpaque(false);

        styleButton(importBtn);
        styleButton(resetView);
        styleButton(show3D);
        styleButton(randomData);
        styleButton(quickSort);
        styleButton(selectionSort);

        importBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(importBtn);
        add(Box.createVerticalStrut(12));

        resetView.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(resetView);
        add(Box.createVerticalStrut(12));
        
        show3D.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(show3D);
        add(Box.createVerticalStrut(12));
        
        randomData.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(randomData);
        add(Box.createVerticalStrut(12));
        
        quickSort.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(quickSort);
        add(Box.createVerticalStrut(12));
        
        selectionSort.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(selectionSort);
    }
    
    private void styleButton(JButton button) {
        button.setFont(ModernTheme.BUTTON_FONT);
        button.setForeground(ModernTheme.TEXT_PRIMARY);
        button.setBackground(ModernTheme.CONTROL_BG);
        button.setFocusPainted(false);
        button.setBorderPainted(true);
        button.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(ModernTheme.BORDER_COLOR, 1),
            BorderFactory.createEmptyBorder(8, 16, 8, 16)
        ));
        button.setMaximumSize(new Dimension(240, 36));
        button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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