import javax.swing.*;

public class signUp extends JOptionPane {
    JFrame f;
    String name;
    public signUp(){
        f=new JFrame();
        this.name=JOptionPane.showInputDialog(f,"Enter Name");
    }
    public String getInfo(){
        return name;
    }

}
