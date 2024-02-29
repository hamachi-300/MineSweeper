import javax.swing.JFrame;

abstract class PopUpWindow extends JFrame{
    public PopUpWindow(){
        setSize(300, 400);
        setLocationRelativeTo(null);
    }

    abstract public void setLabel(PopUpWindow window);
}
