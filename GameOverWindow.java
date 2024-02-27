import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

class GameOverWindow extends PopUpWindow {
    MineSweeper parent;

    public GameOverWindow(MineSweeper parent) {
        super();
        this.parent = parent;
        setLayout(new GridBagLayout());
        setLabel(this);
        parent.attachWindow(this);
    }

    @Override
    public void setLabel(PopUpWindow window) {
        JPanel gameOverPanel = new JPanel(new BorderLayout());
        JLabel gameOverLabel = new JLabel("Game Over", SwingConstants.CENTER);
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
        JButton newGameButton = new JButton("New Game");
        JButton exitButton = new JButton("Exit");

        exitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                System.exit(0);
            }
        });

        newGameButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                parent.newGame(window);
            }
        });

        buttonPanel.add(newGameButton);
        buttonPanel.add(exitButton);

        gameOverPanel.add(gameOverLabel, BorderLayout.CENTER);
        gameOverPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Centering the gameOverPanel
        GridBagConstraints gbc = new GridBagConstraints();
        // gbc.gridx = 0;
        // gbc.gridy = 0;
        // gbc.weightx = 1.0;
        // gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.CENTER;

        add(gameOverPanel, gbc);
    }
}