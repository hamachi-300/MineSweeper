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
    private int difficulty;

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
        JLabel difficultyLabel = new JLabel("Difficulty : Normal");
        JLabel mineImage = new JLabel(mine);
        JButton newGameBtn = new JButton("New Game");
        JButton resumeBtn = new JButton("Resume");
        JButton difficultyBtn = new JButton("Diffculty");
        JButton historyBtn = new JButton("History");
        JButton exitBtn = new JButton("Exit");

        difficultyLabel.setAlignmentX(CENTER_ALIGNMENT);
        mineImage.setAlignmentX(CENTER_ALIGNMENT);
        newGameBtn.setAlignmentX(CENTER_ALIGNMENT);
        resumeBtn.setAlignmentX(CENTER_ALIGNMENT);
        difficultyBtn.setAlignmentX(CENTER_ALIGNMENT);
        historyBtn.setAlignmentX(CENTER_ALIGNMENT);
        exitBtn.setAlignmentX(CENTER_ALIGNMENT);

        newGameBtn.addActionListener(e -> newGameBtn());
        difficultyBtn.addActionListener(e -> difficultyBtn(difficultyLabel));
        historyBtn.addActionListener(e -> historyBtn());
        exitBtn.addActionListener(e -> exitBtn());

        checkDifficulty(rowTiles, mines, difficultyLabel);

        allMenu.add(difficultyLabel);
        allMenu.add(Box.createVerticalStrut(20));
        allMenu.add(mineImage);
        allMenu.add(Box.createVerticalStrut(20));
        allMenu.add(newGameBtn);
        allMenu.add(resumeBtn);
        allMenu.add(difficultyBtn);
        allMenu.add(historyBtn);
        allMenu.add(exitBtn);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;

        add(allMenu, gbc);
    }

    private void newGameBtn(){
        parent.attachWindow(this, new GameWindow(this.rowTiles, this.colTiles, this.mines, parent, this.difficulty));
    }

    private void difficultyBtn(JLabel difficultyLabel){
        new GameDifficultyWindow(this, parent, difficultyLabel);
    }

    private void historyBtn(){
        new HistoryWindow();
    }

    private void exitBtn(){
        System.exit(0);
    }

    public void setDifficulty(int colTiles, int rowTiles, int mines){
        this.colTiles = colTiles;
        this.rowTiles = rowTiles;
        this.mines = mines;
    }

    public void checkDifficulty(int rowTiles, int mines, JLabel difficultyLabel){
        if (rowTiles == 10 && mines == 5) {
            difficulty = 1;
            difficultyLabel.setText("Difficulty : Easy");
        } else if (rowTiles == 10 && mines == 10) {
            difficulty = 2;
            difficultyLabel.setText("Difficulty : Normal");
        } else if (rowTiles == 20 && mines == 20) {
            difficulty = 3;
            difficultyLabel.setText("Difficulty : Hard");
        } else if (rowTiles == 20 && mines == 100) {
            difficulty = 4;
            difficultyLabel.setText("Difficulty : Extreme");
        } else {
            difficultyLabel.setText("Difficulty : Normal");
        }
    }
}
