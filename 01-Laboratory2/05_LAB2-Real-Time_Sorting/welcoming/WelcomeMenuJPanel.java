/*******************************************************************************************************************
 * Objective of the class: Welcome screen content panel with interactive buttons and text fields for user feedback.
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
 * 	- None (all event handlers are defined as anonymous inner classes)
 *******************************************************************************************************************/

package welcoming;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import gui.DrawingJFrame;
import gui.ModernTheme;

public class WelcomeMenuJPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private MainWelcomeJFrame granParentFrame;
	private DrawingJFrame drawingJFrame;

    public WelcomeMenuJPanel(MainWelcomeJFrame grandParentFrame) {
    	super();	
    	this.granParentFrame = grandParentFrame;
    	
    	initialization();
    }
    
    private void initialization() {
    	setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    	setBackground(ModernTheme.PANEL_DARK);
    	setBorder(BorderFactory.createCompoundBorder(
    	    BorderFactory.createLineBorder(ModernTheme.BORDER_COLOR, 2),
    	    BorderFactory.createEmptyBorder(30, 30, 30, 30)
    	));
    	
    	JLabel title = new JLabel("Sensor Data Plotting Toolkit!");
    	title.setFont(new Font("Segoe UI", Font.BOLD, 22));
    	title.setForeground(ModernTheme.TEXT_PRIMARY);
    	title.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(title);
        add(Box.createVerticalStrut(15));	
        
        JLabel authors = new JLabel("Authors: Georgii & Luciano");
        authors.setFont(ModernTheme.LABEL_FONT);
        authors.setForeground(ModernTheme.TEXT_SECONDARY);
        authors.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(authors);
        add(Box.createVerticalStrut(15));
        
        JLabel greetingLabel = new JLabel("Say hello to the developers!");
        greetingLabel.setFont(ModernTheme.LABEL_FONT);
        greetingLabel.setForeground(ModernTheme.TEXT_PRIMARY);
        greetingLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(greetingLabel);
        add(Box.createVerticalStrut(5));
        
        JTextField greetingField = new JTextField(20);
        greetingField.setFont(ModernTheme.LABEL_FONT);
        greetingField.setBackground(ModernTheme.CONTROL_BG);
        greetingField.setForeground(ModernTheme.TEXT_PRIMARY);
        greetingField.setCaretColor(ModernTheme.TEXT_PRIMARY);
        greetingField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(ModernTheme.BORDER_COLOR, 1),
            BorderFactory.createEmptyBorder(6, 10, 6, 10)
        ));
        greetingField.setMaximumSize(new Dimension(300, 35));
        greetingField.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(greetingField);
        add(Box.createVerticalStrut(20));
        
        JButton continueBtn = new JButton(" Continue");
        styleButton(continueBtn);
        continueBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(continueBtn);
        add(Box.createVerticalStrut(12));
        
        JButton exitBtn = new JButton("Exit NOW");
        styleButton(exitBtn);
        exitBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(exitBtn);
        add(Box.createVerticalStrut(12));
        
        JButton fakeBtn = new JButton("I am fake!");
        styleButton(fakeBtn);
        fakeBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(fakeBtn);
        
        continueBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                granParentFrame.dispose(); // close background
                drawingJFrame = new DrawingJFrame("Algorithms and Data Structures - Drawing Window"); // open drawing stage
            }
        });
        
        exitBtn.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		System.exit(0);
        	}
        });
        
        fakeBtn.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		fakeBtn.setVisible(false);
        		
                JLabel fakeActionMessage = new JLabel(">:(");
                fakeActionMessage.setFont(ModernTheme.LABEL_FONT);
                fakeActionMessage.setForeground(ModernTheme.TEXT_PRIMARY);
                fakeActionMessage.setAlignmentX(Component.CENTER_ALIGNMENT);
                add(fakeActionMessage);
        	}
        });
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
        button.setMaximumSize(new Dimension(200, 36));
        button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }
}