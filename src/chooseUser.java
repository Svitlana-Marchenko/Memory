import javax.swing.*;
import java.awt.*;

public class chooseUser extends JOptionPane {
JComboBox comboBox;

    public chooseUser(String [] userName) {
        JPanel panel = new JPanel(new GridBagLayout());
        comboBox = new JComboBox(userName);
        comboBox.setSelectedIndex(0);
        JOptionPane.showMessageDialog(null, comboBox, "Your account",
                JOptionPane.QUESTION_MESSAGE);
        panel.add(comboBox);


    }

    public String getInfo(){
       return (String) comboBox.getSelectedItem();
    }

}