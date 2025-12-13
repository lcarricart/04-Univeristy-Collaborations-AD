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

    public ButtonPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        importBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(importBtn);
        add(Box.createVerticalStrut(20));

        resetView.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(resetView);
        add(Box.createVerticalStrut(20));
    }

    public JButton getResetView() {
        return resetView;
    }

    public JButton getImportBtn() {
        return importBtn;
    }
}