public class MineSweeperTest {
    public static void main(String[] args){
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run(){
                new MineSweeper(10, 10, 10);
            }
        });
    }
}