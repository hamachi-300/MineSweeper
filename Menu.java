import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Menu extends Window{

    private ImageIcon mine;
    private MineSweeper parent;
    private int rowTiles;
    private int colTiles;
    private int mines;


    public Menu(int rowTiles, int colTiles, int mines, MineSweeper parent, Window oldwindow) {
        this.parent = parent;
        this.rowTiles = rowTiles;
        this.colTiles = colTiles;
        this.mines = mines;

        // Load mine Image
        ImageIcon originalMineImage = new ImageIcon("images/mine.png");
        Image image = originalMineImage.getImage();
        Image newImage = image.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        mine = new ImageIcon(newImage);

        setLayout(new GridBagLayout());
        setLabel(this);
        parent.attachWindow(oldwindow, this);
        setVisible(true);
    }

    @Override
    public void setLabel(Window window){
        JPanel allMenu = new JPanel();
        allMenu.setLayout(new BoxLayout(allMenu, BoxLayout.Y_AXIS));
        JLabel mineImage = new JLabel(mine);
        JButton newGameBtn = new JButton("New Game");
        JButton resumeBtn = new JButton("Resume");
        JButton historyBtn = new JButton("History");
        JButton exitBtn = new JButton("Exit");

        mineImage.setAlignmentX(CENTER_ALIGNMENT);
        newGameBtn.setAlignmentX(CENTER_ALIGNMENT);
        resumeBtn.setAlignmentX(CENTER_ALIGNMENT);
        historyBtn.setAlignmentX(CENTER_ALIGNMENT);
        exitBtn.setAlignmentX(CENTER_ALIGNMENT);

        newGameBtn.addActionListener(e -> newGameBtn());
        exitBtn.addActionListener(e -> exitBtn());

        allMenu.add(mineImage);
        allMenu.add(Box.createVerticalStrut(20));
        allMenu.add(newGameBtn);
        allMenu.add(resumeBtn);
        allMenu.add(historyBtn);
        allMenu.add(exitBtn);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;

        add(allMenu, gbc);
    }

    private void newGameBtn(){
        parent.attachWindow(this, new GameWindow(rowTiles, colTiles, mines, parent));
    }

    private void exitBtn(){
        System.exit(0);
    }
}
