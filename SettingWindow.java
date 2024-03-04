import java.awt.Component;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class SettingWindow extends PopUpWindow{

    private MineSweeper parent;
    private GameWindow game;
    private int rowTiles;
    private int colTiles;
    private int mines;

    public SettingWindow(int rowTiles, int colTiles, int mines, MineSweeper parent, GameWindow game) {
        this.parent = parent;
        this.game = game;
        this.rowTiles = rowTiles;
        this.colTiles = colTiles;
        this.mines = mines;

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

        resumeBtn.addActionListener(e -> resumeBtn());
        menuBtn.addActionListener(e -> menuBtn());
        exitBtn.addActionListener(e -> exitBtn());

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        // center vertically all btn 
        panel.add(Box.createVerticalGlue());
        panel.add(resumeBtn);
        panel.add(menuBtn);
        panel.add(exitBtn);
        panel.add(Box.createVerticalGlue());

        window.add(panel);
    }
    
    // when click resumeBtn will dispose setting window
    private void resumeBtn(){
        dispose();
    }

    // when click menuBtn will change to menu window and dispose setting window
    private void menuBtn(){
        new Menu(rowTiles, colTiles, mines, parent, game);
        dispose();
    }

    // when click exitBtn will exit game
    private void exitBtn(){
        System.exit(0);
        dispose();
    }
}
