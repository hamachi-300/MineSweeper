import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

class MineClearedWindow extends Window {

    private MineSweeper parent;
    private int rowTiles;
    private int colTiles;
    private int mines;
    private int difficulty;

    public MineClearedWindow(int rowTiles, int colTiles, int mines, MineSweeper parent, GameWindow game, int difficulty, String time){
        this.parent = parent;
        this.rowTiles = rowTiles;
        this.colTiles = colTiles;
        this.mines = mines;
        this.difficulty = difficulty;

        saveHistory(time.substring(7), difficulty);
        setLayout(new GridBagLayout());
        setLabel(this);
        parent.attachWindow(game, this);
        setVisible(true);
    }
    
    // save score to history.txt
    public void saveHistory(String time, int difficulty){
        // DD//MM/YY : DIFFCULTY : TIME
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.ENGLISH));
        String difficultyStr;
        String filePath = "history.txt";

        // convert difficulty integer to str
        switch (difficulty) {
            case 1:
                difficultyStr = "Easy";
                break;
            case 2:
                difficultyStr = "Normal";
                break;
            case 3:
                difficultyStr = "Hard";
                break;
            case 4:
                difficultyStr = "Extreme";
                break;
            default:
                difficultyStr = "Easy";
                break;
        }

        // sum everything together
        String info = String.format("%s : %s : %s \n", date, difficultyStr, time);
        try {
            FileWriter writer = new FileWriter(filePath, true);
            writer.write(info);
            writer.close();
        } catch (IOException e){
            System.err.println("Error while load file path : " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    @Override
    public void setLabel(Window window){
        JPanel gameOverPanel = new JPanel(new BorderLayout());
        JLabel gameOverLabel = new JLabel("Mine Cleared", SwingConstants.CENTER);
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
        JButton newGameButton = new JButton("New Game");
        JButton exitButton = new JButton("Exit");
        MineClearedWindow mineClearedWindow = this;

        exitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e){
                System.exit(0);
            }
        });

        newGameButton.addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent e){
                parent.attachWindow(mineClearedWindow, new GameWindow(rowTiles, colTiles, mines, parent, difficulty));
            }
        });

        buttonPanel.add(newGameButton);
        buttonPanel.add(exitButton);

        gameOverPanel.add(gameOverLabel, BorderLayout.CENTER);
        gameOverPanel.add(buttonPanel, BorderLayout.SOUTH);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;

        add(gameOverPanel, gbc);
    }
}