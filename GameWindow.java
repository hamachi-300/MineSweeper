import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Timer;
import java.util.Random;
public class GameWindow extends Window {
    private int rowTiles;
    private int colTiles;
    private int mines;
    private Tile[][] tileList;
    private boolean gameOver = false;
    Random rand = new Random();

    public GameWindow(int rowTiles, int colTiles, int mines, MineSweeper parent) {
        this.rowTiles = rowTiles;
        this.colTiles = colTiles;
        this.mines = mines;
        tileList = new Tile[rowTiles][colTiles];
        setLayout(new GridLayout(rowTiles, colTiles, 0, 0));

        Timer timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // call gameOverWindow
                new GameOverWindow(parent);
            }
        });

        placeTiles(tileList, parent);
        placeMine(tileList, parent);
        for (int i = 0; i < rowTiles; i++){
            for (int j = 0; j < colTiles; j++){
                Tile tile = tileList[i][j];
                tile.setFocusable(false);
                tile.setMargin(new Insets(0, 0, 0, 0));
                tile.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e){
                        Tile tile = (Tile) e.getSource();

                        // if game over cannot click button
                        if (!gameOver){
                            // check flagMode
                            if (parent.flagMode == true){
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
                                        parent.stopTime(parent.time);
                                        timer.setRepeats(false); // Set to false if you want the timer to execute only once
                                        timer.start();
                                    } else {
                                        tile.revealTile(tileList);
                                        // tileCount is tiles that revealed
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
                                            // call gameClearWindow
                                            parent.stopTime(parent.time);
                                            new MineClearedWindow(parent);
                                        }
                                    }
                                }
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

                add(tile);
            }
        }
    }

    private void placeTiles(Tile[][] tileList, MineSweeper parent){
        for (int i = 0; i < rowTiles; i++){
            for (int j = 0; j < colTiles; j++){
                // check mine tile
                Tile tile = new NormalTile(i, j, rowTiles, colTiles, parent);
                tileList[i][j] = tile;
            }
        }
    }

    private void placeMine(Tile[][] tileList, MineSweeper parent){

        for (int i = 0; i < mines; i++){
            int row = rand.nextInt(rowTiles);
            int col = rand.nextInt(colTiles);

            if (!MineTile.class.isAssignableFrom(tileList[row][col].getClass())){
                tileList[row][col] = new MineTile(row, col, parent);
            } else {
                i--;
            }
        }
    }

    @Override
    public void setLabel(Window window){
        // do nothing
    }
}