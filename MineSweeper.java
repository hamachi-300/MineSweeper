import java.awt.BorderLayout;

import javax.swing.JFrame;

public class MineSweeper extends JFrame {

    private int rowTiles;
    private int colTiles;
    private int mines;

    public MineSweeper(int rowTiles, int colTiles, int mines){

        this.rowTiles = rowTiles;
        this.colTiles = colTiles;
        this.mines = mines;

        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Mineseeper");
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        attachWindow(null, new Menu(this.rowTiles, this.colTiles, this.mines, this, null));

        setVisible(true);
    }

    public void attachWindow(Window oldWindow, Window newWindow){
        if (!(oldWindow == null)){
            this.remove(oldWindow);
        }
        add(newWindow, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    public void setDifficulty(int colTiles, int rowTiles, int mines){
        this.colTiles = colTiles;
        this.rowTiles = rowTiles;
        this.mines = mines;
    }
}

