package gui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ComboBoxPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private String[] options = {"Sensor 1", "Sensor 2", "Sensor 3", "Sensor 4"};
    private String[] options2 = {"Item 1", "Item 2", "Item 3", "Item 4", "Item 5"};
    private String[] options3 = {"Data 1", "Data 2", "Data 3"};

    private JComboBox<String> comboBox = new JComboBox<>(options);
    private JComboBox<String> comboBox2 = new JComboBox<>(options2);
    private JComboBox<String> comboBox3 = new JComboBox<>(options3);

    public ComboBoxPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel comboLabel = new JLabel("Plot 1");
        int desiredHeight = 20;

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
        add(Box.createVerticalStrut(30));
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

    public JComboBox<String> getComboBox1() {
        return comboBox;
    }

    public JComboBox<String> getComboBox2() {
        return comboBox2;
    }

    public JComboBox<String> getComboBox3() {
        return comboBox3;
    }
}