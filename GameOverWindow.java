import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

class GameOverWindow extends Window {
    private MineSweeper parent;
    private int rowTiles;
    private int colTiles;
    private int mines;
    private int difficulty;

    public GameOverWindow(int rowTiles, int colTiles, int mines, MineSweeper parent, GameWindow game, int difficulty) {
        this.parent = parent;
        this.rowTiles = rowTiles;
        this.colTiles = colTiles;
        this.mines = mines;
        this.difficulty = difficulty;

        setLayout(new GridBagLayout());
        setLabel(this);
        // attact gameOverWindow in MineSweeper
        parent.attachWindow(game, this);
        setVisible(true);
    }

    @Override
    public void setLabel(Window window) {
        JPanel gameOverPanel = new JPanel(new BorderLayout());
        JLabel gameOverLabel = new JLabel("Game Over", SwingConstants.CENTER);
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
        JButton newGameButton = new JButton("New Game");
        JButton exitButton = new JButton("Exit");

        GameOverWindow gameOverWindow = this;

        // add mouseListener for exit button
        exitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                System.exit(0);
            }
        });

        // add mouseListner for newGame button
        newGameButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                parent.attachWindow(gameOverWindow, new GameWindow(rowTiles, colTiles, mines, parent, difficulty));
            }
        });

        // add newGame and exit button to buttonPanel
        buttonPanel.add(newGameButton);
        buttonPanel.add(exitButton);

        // add gameOverPanel and buttonPanel to gameOverPanel
        gameOverPanel.add(gameOverLabel, BorderLayout.CENTER);
        gameOverPanel.add(buttonPanel, BorderLayout.SOUTH);

        // create GridBagConstraints to center gameOverPanel
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;

        // add game with gcb for add gammOverPanel at center of window
        add(gameOverPanel, gbc);
    }
}
