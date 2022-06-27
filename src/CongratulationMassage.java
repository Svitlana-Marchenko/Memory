import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class CongratulationMassage extends JOptionPane{

    JFrame frame;
    CongratulationMassage(String message,String imagePath) {
        try {
            ImageIcon iconExit = new ImageIcon(ImageIO.read(new File(imagePath)).getScaledInstance(100, 100, Image.SCALE_DEFAULT));
            frame = new JFrame();
            JOptionPane.showMessageDialog(frame,
                    message, "Congratulation",
                    JOptionPane.INFORMATION_MESSAGE,
                    iconExit);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
