import java.awt.Component;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class SettingWindow extends PopUpWindow{

    private MineSweeper parent;

    public SettingWindow(MineSweeper parent) {
        this.parent = parent;
        setSize(300, 400);
        setLabel(this);
        setVisible(true);
    }

    @Override
    public void setLabel(PopUpWindow window) {

        JPanel panel = new JPanel();
        JButton resumeBtn = new JButton("Resume");
        JButton menuBtn = new JButton("Menu");
        JButton exitBtn = new JButton("Exit");

        // center holizontally all btn 
        resumeBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        menuBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        resumeBtn.addActionListener(e -> this.setVisible(false));
        menuBtn.addActionListener(e -> toMenu());
        exitBtn.addActionListener(e -> System.exit(0));

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        // center vertically all btn 
        panel.add(Box.createVerticalGlue());
        panel.add(resumeBtn);
        panel.add(menuBtn);
        panel.add(exitBtn);
        panel.add(Box.createVerticalGlue());

        window.add(panel);
    }

    public void toMenu(){
        // change window to menu
        new Menu(parent);
    }
}
