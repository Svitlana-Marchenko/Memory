import javax.swing.*;

public class Message extends JOptionPane {

    JFrame f;
    Message(String label){
        f=new JFrame();
        JOptionPane.showMessageDialog(f,label,"Ooops, something went wrong...",JOptionPane.INFORMATION_MESSAGE);

    }

}
