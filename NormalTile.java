public class NormalTile extends Tile {

    private int rowTile;
    private int colTile;

    public NormalTile(int row, int col, int rowTile, int colTile, GameWindow parent) {
        super(row, col, parent);

        this.rowTile = rowTile;
        this.colTile = colTile;
    }

    public int getRowTile() {
        return rowTile;
    }

    public int getColTile() {
        return colTile;
    }

    public void checkMine(int row, int col, Tile[][] tileList){

    }

    @Override
    public void renderTile(Tile[][] tileList){

        int count = 0;

        for (int i = Math.max(0, getRow()-1); i <= Math.min(getRow()+1, getRowTile()-1); i++){
            for (int j = Math.max(0, getCol()-1); j <= Math.min(getCol()+1, getColTile()-1); j++){
                if (MineTile.class.isAssignableFrom(tileList[i][j].getClass())){
                    count++;
                }
            }
        }

        if (count == 0){
            setEnabled(false);
            setText(" ");
            setRevealed(true);
            for (int i = Math.max(0, getRow()-1); i <= Math.min(getRow()+1, getRowTile()-1); i++){
                for (int j = Math.max(0, getCol()-1); j <= Math.min(getCol()+1, getColTile()-1); j++){
                    if (!tileList[i][j].isRevealed() && !MineTile.class.isAssignableFrom(tileList[i][j].getClass()) && !tileList[i][j].isFlag()){
                        tileList[i][j].renderTile(tileList);
                    }
                }
            }
        } else {
            setEnabled(false);
            setText(Integer.toString(count));
            setRevealed(true);
        }
    }
}
