import javax.swing.JPanel;
// import java.awt.Color;

abstract public class Window extends JPanel {

    public Window(){
        // setBackground(new Color(220, 242, 241));
        setFocusable(true);
        setDoubleBuffered(true);
    }

    abstract public void setLabel(Window window);
}
