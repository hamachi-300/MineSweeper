public class MineTile extends Tile {

    boolean isRevealed = false;

    public MineTile(int row, int col, GameWindow parent) {
        super(row, col, parent);
    }

    @Override
    public void renderTile(Tile[][] tileList){
        setText("*");
    }
}
