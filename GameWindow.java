import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.util.Random;

public class GameWindow extends Window {
    private int rowTiles;
    private int colTiles;
    private int mines;
    private int mineCount;
    private int difficulty;
    private int fleshTime = 0;
    private boolean gameOver = false;
    protected boolean flagMode = true;

    protected Timer timer;
    private JLabel mineCounter;
    private Tile[][] tileList;
    Random rand = new Random();

    public GameWindow(int rowTiles, int colTiles, int mines, MineSweeper parent, int difficulty) {
        this.rowTiles = rowTiles;
        this.colTiles = colTiles;
        this.mines = mines;
        this.mineCount = mines;
        this.difficulty = difficulty;
        tileList = new Tile[rowTiles][colTiles];
        setLayout(new BorderLayout());

        JPanel header = new JPanel(new BorderLayout());
        JPanel footer = new JPanel(new BorderLayout());
        JPanel setFlagPanel = new JPanel(new BorderLayout());
        JPanel board = new JPanel(new GridLayout(rowTiles, colTiles, 0, 0));

        // create mineCounter label
        mineCounter = new JLabel(String.format("Mine Remain : %d", mines));
        JLabel timeCounter = new JLabel("Time : 00:00:00");
        JButton settingBtn = new JButton("setting");
        JButton mineBtn = new JButton("*");
        JButton flagBtn = new JButton("|>");

        // set Monospaced font
        mineCounter.setFont(new Font("Monospaced", Font.BOLD, 18));
        timeCounter.setFont(new Font("Monospaced", Font.BOLD, 18));

        // create timer when game start
        timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fleshTime++;
                updateTimerLabel(timeCounter);
            }
        });

        // switch bettween flag mode and mine mode
        flagMode(flagBtn, mineBtn);
        flagBtn.addActionListener(e -> flagMode(flagBtn, mineBtn));
        mineBtn.addActionListener(e -> flagMode(flagBtn, mineBtn));
        settingBtn.addActionListener(e -> new SettingWindow(rowTiles, colTiles, mines, parent, this));
        // start timer
        timer.start();

        // add all component
        setFlagPanel.add(mineBtn, BorderLayout.WEST);
        setFlagPanel.add(flagBtn, BorderLayout.EAST);

        header.add(mineCounter, BorderLayout.EAST);
        header.add(timeCounter, BorderLayout.WEST);
        footer.add(setFlagPanel, BorderLayout.EAST);
        footer.add(settingBtn, BorderLayout.WEST);

        // set tile and mine
        placeTiles(tileList, parent);
        placeMine(tileList, parent);

        GameWindow gameWindow = this;
        // loop for add all Tile and add MouseListener
        for (int i = 0; i < rowTiles; i++){
            for (int j = 0; j < colTiles; j++){
                Tile tile = tileList[i][j];
                tile.setFocusable(false);
                tile.setMargin(new Insets(0, 0, 0, 0));
                tile.addMouseListener(new MouseAdapter() {
                    // method handle when mouse is pressed
                    @Override
                    public void mousePressed(MouseEvent e){
                        Tile tile = (Tile) e.getSource();

                        // if game over cannot click button
                        if (!gameOver){
                            // check flagMode
                            // if true both left or right will set flag
                            if (flagMode == true){
                                if (e.getButton() == MouseEvent.BUTTON1 || e.getButton() == MouseEvent.BUTTON3){
                                    Tile thisTile = (Tile) e.getSource();
                                    if (thisTile.isEnabled()){
                                        if (tile.isFlag()){
                                            tile.removeFlag();
                                        } else {
                                            tile.setFlag();
                                        }
                                    }
                                }
                            } else {
                                // left click mouse will reveal tile
                                if (e.getButton() == MouseEvent.BUTTON1 && !tile.isFlag()){
                                    // expose all mine if tile is mine
                                    if (MineTile.class.isAssignableFrom(tile.getClass())){
                                        for (Tile[] tileRow : tileList){
                                            for (Tile tile_c : tileRow) {
                                                if (MineTile.class.isAssignableFrom(tile_c.getClass())){
                                                    tile_c.revealTile(tileList);
                                                }
                                            }
                                        }
                                        gameOver = true;
                                        timer.stop();
                                        startOverWindow(parent);
                                    } else {
                                        tile.revealTile(tileList);
                                        // count all tile was revealed
                                        int tileCount = 0;
                                        for (Tile[] tileRow : tileList){
                                            for (Tile thisTile : tileRow){
                                                if (thisTile.isRevealed()){
                                                    tileCount += 1;
                                                }
                                            }
                                        }
    
                                        // check game is cleared
                                        if ((rowTiles * colTiles) - tileCount - mines == 0) {
                                            // call gameClearWindow if game was cleared
                                            timer.stop();
                                            new MineClearedWindow(rowTiles, colTiles, mines, parent, gameWindow, difficulty, timeCounter.getText());
                                        }
                                    }
                                }

                                // right click mouse will set flag
                                if (e.getButton() == MouseEvent.BUTTON3){
                                    Tile thisTile = (Tile) e.getSource();
                                    if (thisTile.isEnabled()){
                                        if (tile.isFlag()){
                                            tile.removeFlag();
                                        } else {
                                            tile.setFlag();
                                        }
                                    }
                                }
                            }
                        }

                        parent.revalidate();
                        parent.repaint();
                    }
                });

                
                board.add(tile);
            }
        }
        add(header, BorderLayout.NORTH);
        add(board, BorderLayout.CENTER);
        add(footer, BorderLayout.SOUTH);
    }

    // set timerLabel text
    public void updateTimerLabel(JLabel timeCounter){
        int hour = fleshTime/360;
        int minute = (fleshTime/60) % 60;
        int second = fleshTime % 60;
        String hourStr = String.format("%02d", hour);
        String minuteStr = String.format("%02d", minute);
        String secondStr = String.format("%02d", second);
        timeCounter.setText(String.format("Time : %s:%s:%s", hourStr, minuteStr, secondStr));
    }
    
    // delay 1 second before create GameOverWindow
    private void startOverWindow(MineSweeper parent){
        GameWindow game = this;
        Timer gameOverDelay = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // call gameOverWindow
                new GameOverWindow(rowTiles, colTiles, mines, parent, game, difficulty);
            }
        });
        gameOverDelay.setRepeats(false);
        gameOverDelay.start();
        
    }

    // place tiles method
    private void placeTiles(Tile[][] tileList, MineSweeper parent){
        for (int i = 0; i < rowTiles; i++){
            for (int j = 0; j < colTiles; j++){
                // check mine tile
                Tile tile = new NormalTile(i, j, rowTiles, colTiles, this);
                tileList[i][j] = tile;
            }
        }
    }

    // place mines method
    private void placeMine(Tile[][] tileList, MineSweeper parent){

        for (int i = 0; i < mines; i++){
            int row = rand.nextInt(rowTiles);
            int col = rand.nextInt(colTiles);

            if (!MineTile.class.isAssignableFrom(tileList[row][col].getClass())){
                tileList[row][col] = new MineTile(row, col, this);
            } else {
                i--;
            }
        }
    }

    // method handle flagMode
    // if flagMode is true it will set false
    // if flagMode is false it will set true
    public void flagMode(JButton flagBtn, JButton mineBtn){
        if (flagMode == false){
            mineBtn.setEnabled(true);
            flagBtn.setEnabled(false);
            flagMode = true;
        } else {
            mineBtn.setEnabled(false);
            flagBtn.setEnabled(true);
            flagMode = false;
        }
    }

    // method will update mineCounter
    // when num is 0 mean mineCount will increase
    public void updateMineCounter(int num){
        if (num == 0) {
            mineCount+=1;
            if (mineCount >= 0){
                mineCounter.setText(String.format("Mine Remain : %d", mineCount));
            }
        }

        if  (num == 1) {
            mineCount-=1;
            if (mineCount >= 0){
                mineCounter.setText(String.format("Mine Remain : %d", mineCount));
            }
        }
    }

    @Override
    public void setLabel(Window window){
        // do nothing
    }
}