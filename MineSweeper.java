import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MineSweeper extends JFrame {
    private int rowTiles;
    private int colTiles;
    private int mines;
    private int mineCount;
    private JLabel mineCounter;
    private int fleshTime = 0;
    protected boolean flagMode = true;
    protected Timer time;

    GameWindow game;

    public MineSweeper(int rowTiles, int colTiles, int mines){
        this.rowTiles = rowTiles;
        this.colTiles = colTiles;
        this.mines = mines;
        this.mineCount = mines;
        
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Mineseeper");
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        // Create game window here
        JPanel header = new JPanel(new BorderLayout());
        JPanel footer = new JPanel(new BorderLayout());
        JPanel setFlagPanel = new JPanel(new BorderLayout());

        mineCounter = new JLabel(String.format("Mine Remain : %d", mines));
        JLabel timeCounter = new JLabel("Time : 00:00:00");
        JButton settingBtn = new JButton("setting");
        JButton mineBtn = new JButton("*");
        JButton flagBtn = new JButton("|>");

        mineCounter.setFont(new Font("Monospaced", Font.BOLD, 18));
        timeCounter.setFont(new Font("Monospaced", Font.BOLD, 18));
        game = new GameWindow(rowTiles, colTiles, mines, this);

        time = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fleshTime++;
                updateTimerLabel(timeCounter);
            }
        });
        // set default Btn
        flagMode(flagBtn, mineBtn);
        flagBtn.addActionListener(e -> flagMode(flagBtn, mineBtn));
        mineBtn.addActionListener(e -> flagMode(flagBtn, mineBtn));
        settingBtn.addActionListener(e -> new SettingWindow(this));
        time.start();

        setFlagPanel.add(mineBtn, BorderLayout.WEST);
        setFlagPanel.add(flagBtn, BorderLayout.EAST);

        header.add(mineCounter, BorderLayout.EAST);
        header.add(timeCounter, BorderLayout.WEST);
        footer.add(setFlagPanel, BorderLayout.EAST);
        footer.add(settingBtn, BorderLayout.WEST);
        
        add(header, BorderLayout.NORTH);
        add(game, BorderLayout.CENTER);
        add(footer, BorderLayout.SOUTH);
        setVisible(true);
    }

    public void newGame(Window window){
        this.remove(window);
        game = new GameWindow(rowTiles, colTiles, mines, this);
        this.add(game, BorderLayout.CENTER);
        resetTime();
        resetFlag();
        revalidate();
        repaint();
    }

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

    // time methods
    public void updateTimerLabel(JLabel timeCounter){
        int hour = fleshTime/360;
        int minute = (fleshTime/60) % 60;
        int second = fleshTime % 60;
        String hourStr = String.format("%02d", hour);
        String minuteStr = String.format("%02d", minute);
        String secondStr = String.format("%02d", second);
        timeCounter.setText(String.format("Time : %s:%s:%s", hourStr, minuteStr, secondStr));
    }
    
    public void stopTime(Timer timer){
        timer.stop();
    }

    public void resetTime(){
        fleshTime = 0;
        time.start();
    }

    public void resetFlag(){
        mineCount = mines;
        mineCounter.setText(String.format("Mine Remain : %d", mineCount));
    }

    public void attachWindow(Window window){
        this.remove(game);
        add(window, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

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
}

