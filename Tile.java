import javax.swing.JButton;

abstract public class Tile extends JButton {
    private int row;
    private int  col;
    private boolean isRevealed = false;
    private boolean isFlag = false;
    private MineSweeper parent;

    public Tile(int row, int col, MineSweeper parent){
        this.row = row;
        this.col= col;
        this.parent = parent;
    }

    public void revealTile(Tile[][] tileList){
        renderTile(tileList);
    }

    public void setFlag(){
        renderFlag();
        isFlag = true;
        parent.updateMineCounter(1);
    }

    public int getRow(){
        return this.row;
    }

    public int getCol(){
        return this.col;
    }

    public void setRevealed(boolean bool){
        isRevealed = bool;
    }

    public boolean isFlag(){
        return this.isFlag;
    }

    public boolean isRevealed(){
        return this.isRevealed;
    }

    public void removeFlag(){
        renderTileDefault();
        isFlag = false;
        parent.updateMineCounter(0);
    }

    public void renderFlag(){
        setText("|>");
    }

    public void renderTileDefault(){
        setText(" ");
    }

    abstract public void renderTile(Tile[][] tileList);
}
