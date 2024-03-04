import javax.swing.JButton;

abstract public class Tile extends JButton {
    private int row;
    private int  col;
    private boolean isRevealed = false;
    private boolean isFlag = false;
    private GameWindow parent;

    public Tile(int row, int col, GameWindow parent){
        this.row = row;
        this.col= col;
        this.parent = parent;
    }

    // reveal tile method
    public void revealTile(Tile[][] tileList){
        renderTile(tileList);
    }

    // set flag method
    public void setFlag(){
        renderFlag();
        isFlag = true;
        // parameter 1 mean mineCounter will decease
        parent.updateMineCounter(1);
    }

    // get row method
    public int getRow(){
        return this.row;
    }

    // get col method
    public int getCol(){
        return this.col;
    }

    // set reavealed true
    public void setRevealed(boolean bool){
        isRevealed = bool;
    }
    
    // check flag are set
    public boolean isFlag(){
        return this.isFlag;
    }

    // check is revealed
    public boolean isRevealed(){
        return this.isRevealed;
    }

    // remove flag
    public void removeFlag(){
        // render flag frome tile
        renderTileDefault();
        isFlag = false;
        parent.updateMineCounter(0);
    }

    // render flag 
    public void renderFlag(){
        setText("|>");
    }

    // render default tile
    public void renderTileDefault(){
        setText(" ");
    }

    
    abstract public void renderTile(Tile[][] tileList);
}
