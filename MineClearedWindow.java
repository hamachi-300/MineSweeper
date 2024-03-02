import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

class MineClearedWindow extends Window {

    private MineSweeper parent;
    private int rowTiles;
    private int colTiles;
    private int mines;

    public MineClearedWindow(int rowTiles, int colTiles, int mines, MineSweeper parent, GameWindow game){
        this.parent = parent;
        this.rowTiles = rowTiles;
        this.colTiles = colTiles;
        this.mines = mines;

        setLayout(new GridBagLayout());
        setLabel(this);
        parent.attachWindow(game, this);
        setVisible(true);
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
                parent.attachWindow(mineClearedWindow, new GameWindow(rowTiles, colTiles, mines, parent));
            }
        });

        buttonPanel.add(newGameButton);
        buttonPanel.add(exitButton);

        gameOverPanel.add(gameOverLabel, BorderLayout.CENTER);
        gameOverPanel.add(buttonPanel, BorderLayout.SOUTH);
        GridBagConstraints gbc = new GridBagConstraints();
        // gbc.gridx = 0;
        // gbc.gridy = 0;
        // gbc.weightx = 1.0;
        // gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.CENTER;

        add(gameOverPanel, gbc);
    }
}