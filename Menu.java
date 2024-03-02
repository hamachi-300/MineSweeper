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

    public Menu(MineSweeper parent, Window oldwindow) {
        // Load alien image
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
}
