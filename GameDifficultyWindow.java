import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameDifficultyWindow extends PopUpWindow {

    private MineSweeper parent;
    private Menu menu;
    private JLabel difficultyLabel;

    public GameDifficultyWindow(Menu menu, MineSweeper parent, JLabel difficultyLabel){
        this.parent = parent;
        this.menu = menu;
        this.difficultyLabel = difficultyLabel;

        setSize(300, 400);
        setLabel(this);
        setVisible(true);
    }

    @Override
    public void setLabel(PopUpWindow window){
        JPanel panel = new JPanel();

        // create diffculty buttons
        JButton easyBtn = new JButton("Easy");
        JButton normalBtn = new JButton("Normal");
        JButton hardBtn = new JButton("Hard");
        JButton extremeBtn = new JButton("Extreme");

        // add action listener for difficulty button
        easyBtn.addActionListener(e -> easyBtn());
        normalBtn.addActionListener(e -> normalBtn());
        hardBtn.addActionListener(e -> hardBtn());
        extremeBtn.addActionListener(e -> extremeBtn());

        // set all difficulty buttons center of window horizontally
        easyBtn.setAlignmentX(CENTER_ALIGNMENT);
        normalBtn.setAlignmentX(CENTER_ALIGNMENT);
        hardBtn.setAlignmentX(CENTER_ALIGNMENT);
        extremeBtn.setAlignmentX(CENTER_ALIGNMENT);

        // set all difficulty buttons center of window vertically
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(Box.createVerticalGlue());
        panel.add(easyBtn);
        panel.add(normalBtn);
        panel.add(hardBtn);
        panel.add(extremeBtn);
        panel.add(Box.createVerticalGlue());
        
        add(panel);
    }

    // methods for set preset of every difficulty button
    private void easyBtn(){
        parent.setDifficulty(10, 10, 5);
        menu.setDifficulty(10, 10, 5);
        menu.checkDifficulty(10, 5, difficultyLabel);
        dispose();
    }

    private void normalBtn(){
        parent.setDifficulty(10, 10, 10);
        menu.setDifficulty(10, 10, 10);
        menu.checkDifficulty(10, 10, difficultyLabel);
        dispose();
    }

    private void hardBtn(){
        parent.setDifficulty(20, 20, 20);
        menu.setDifficulty(20, 20, 20);
        menu.checkDifficulty(20, 20, difficultyLabel);
        dispose();
    }

    private void extremeBtn(){
        parent.setDifficulty(20, 20, 100);
        menu.setDifficulty(20, 20, 100);
        menu.checkDifficulty(20, 100, difficultyLabel);
        dispose();
    }
}
