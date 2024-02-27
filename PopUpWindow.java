import javax.swing.JPanel;

abstract class PopUpWindow extends JPanel{
    public PopUpWindow(){
        setVisible(true);
    }

    abstract public void setLabel(PopUpWindow window);
}
