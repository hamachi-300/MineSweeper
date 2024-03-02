import java.awt.BorderLayout;

import javax.swing.JFrame;

public class MineSweeper extends JFrame {


    public MineSweeper(int rowTiles, int colTiles, int mines){

        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Mineseeper");
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        attachWindow(null, new Menu(rowTiles, colTiles, mines, this, null));

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
}

