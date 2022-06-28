import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

public class AchievementsMenu extends JFrame {

    JPanel achievementsPanel;
    User user;

    public AchievementsMenu(User user) {
        super("Achievements");
        this.user=user;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(640, 830);
        init(this);
    }

    private void init(JFrame frame) {
        achievementsPanel = new JPanel(new GridLayout(4, 3));
        achievementsPanel.setBackground(Color.decode("#F7FAA5"));
        add(achievementsPanel);

        try {
            JLabel get1000, get2000, get5000, hardLevel, games10, games30, games100, seconds60, seconds30, fails10;
            if (user.isGet1000()) get1000 = new JLabel(new ImageIcon("images\\achievements\\get1000.png"));
            else get1000 = new JLabel(new ImageIcon(ImageIO.read(new File("images\\achievements\\get1000Inactive.png")).getScaledInstance(200, 200, Image.SCALE_DEFAULT)));
            achievementsPanel.add(get1000);

            if (user.isGet2000()) get2000 = new JLabel(new ImageIcon("images\\achievements\\get2000.png"));
            else get2000 = new JLabel(new ImageIcon(ImageIO.read(new File("images\\achievements\\get2000Inactive.png")).getScaledInstance(200, 200, Image.SCALE_DEFAULT)));
            achievementsPanel.add(get2000);

            if (user.isGet5000()) get5000 = new JLabel(new ImageIcon("images\\achievements\\get5000.png"));
            else get5000 = new JLabel(new ImageIcon(ImageIO.read(new File("images\\achievements\\get5000Inactive.png")).getScaledInstance(200, 200, Image.SCALE_DEFAULT)));
            achievementsPanel.add(get5000);

            if (user.isHardLevel()) hardLevel = new JLabel(new ImageIcon("images\\achievements\\thehardest.png"));
            else hardLevel = new JLabel(new ImageIcon(ImageIO.read(new File("images\\achievements\\thehardestInactive.png")).getScaledInstance(200, 200, Image.SCALE_DEFAULT)));
            achievementsPanel.add(hardLevel);

            if (user.isGames10()) games10 = new JLabel(new ImageIcon("images\\achievements\\games10.png"));
            else games10 = new JLabel(new ImageIcon(ImageIO.read(new File("images\\achievements\\games10Inactive.png")).getScaledInstance(200, 200, Image.SCALE_DEFAULT)));
            achievementsPanel.add(games10);

            if (user.isGames30()) games30 = new JLabel(new ImageIcon("images\\achievements\\games30.png"));
            else games30 = new JLabel(new ImageIcon(ImageIO.read(new File("images\\achievements\\games30Inactive.png")).getScaledInstance(200, 200, Image.SCALE_DEFAULT)));
            achievementsPanel.add(games30);

            if (user.isGames100()) games100 = new JLabel(new ImageIcon("images\\achievements\\games100.png"));
            else games100 = new JLabel(new ImageIcon(ImageIO.read(new File("images\\achievements\\games100Inactive.png")).getScaledInstance(200, 200, Image.SCALE_DEFAULT)));
            achievementsPanel.add(games100);

            if (user.isSeconds60()) seconds60 = new JLabel(new ImageIcon("images\\achievements\\seconds60.png"));
            else seconds60 = new JLabel(new ImageIcon(ImageIO.read(new File("images\\achievements\\seconds60Inactive.png")).getScaledInstance(200, 200, Image.SCALE_DEFAULT)));
            achievementsPanel.add(seconds60);

            if (user.isSeconds30()) seconds30 = new JLabel(new ImageIcon("images\\achievements\\seconds30.png"));
            else seconds30 = new JLabel(new ImageIcon(ImageIO.read(new File("images\\achievements\\seconds30Inactive.png")).getScaledInstance(200, 200, Image.SCALE_DEFAULT)));
            achievementsPanel.add(seconds30);

            if (user.isFails10()) fails10 = new JLabel(new ImageIcon("images\\achievements\\fails10.png"));
            else fails10 = new JLabel(new ImageIcon(ImageIO.read(new File("images\\achievements\\fails10Inactive.png")).getScaledInstance(200, 200, Image.SCALE_DEFAULT)));
            achievementsPanel.add(fails10);

            achievementsPanel.add(new JLabel(""));

            JLabel exit = new JLabel(new ImageIcon(ImageIO.read(new File("buttons\\exit.png")).getScaledInstance(200, 200 * 2 / 3, Image.SCALE_DEFAULT)));
            achievementsPanel.add(exit);
            exit.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    super.mousePressed(e);
                    achievementsPanel.setVisible(false);
                    remove(achievementsPanel);
                    setVisible(false);
                    dispose();
                }
            });
        }catch(IOException e){
            e.printStackTrace();
        }
    }

}
