import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.Comparator;


public class MainMenu extends JFrame {

    JPanel mainPanel;
User user;
static UsersFile userFile;

    public MainMenu() throws FileNotFoundException {
        super("Mathmory");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 900);
        this.userFile = new UsersFile();
        init(this);


    }

    private static void sortUserFile(){
        Collections.sort(userFile.getUserArrayList(), new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                if(o1.getScore()<o2.getScore()){
                    return 1;
                }
                if(o1.getScore()>o2.getScore()) {
                    return -1;
                }
                return o1.getName().compareToIgnoreCase(o2.getName());
            }
        });

    }

    private void init(JFrame frame) {
        mainPanel = new JPanel(new GridLayout(5, 1));
        mainPanel.setBackground(Color.decode("#F7FAA5"));
        add(mainPanel);
JLabel gameName = new JLabel("MathMory", SwingConstants.CENTER);
gameName.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 70));
gameName.setForeground(Color.decode("#970EAB"));
mainPanel.add(gameName);


 JTextArea  textArea = new JTextArea(userFile.getUserArrayList().size(), 2);
        JScrollPane scrollPane = new JScrollPane(textArea);
       textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setFont(new Font("Arial", Font.PLAIN, 25));

mainPanel.add(scrollPane);
sortUserFile();
textArea.append("Top players:\n");
for(int i=0; i<userFile.getUserArrayList().size(); i++){
        textArea.append((i+1)+") "+userFile.getUserArrayList().get(i).getName()+" - "+userFile.getUserArrayList().get(i).getScore()+"\n");
}

textArea.setBackground(Color.decode("#F7FAA5"));

        ImageIcon iconPlay = new ImageIcon("C:\\Lab3\\Play.png");
        JLabel play = new JLabel(iconPlay);
        play.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        mainPanel.add(play);

        ImageIcon iconAwards = new ImageIcon("C:\\Lab3\\Awards.png");
        JLabel awards = new JLabel(iconAwards);
        awards.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        mainPanel.add(awards);

        ImageIcon iconExit = new ImageIcon("C:\\Lab3\\Exit.png");
        JLabel exit = new JLabel(iconExit);
       exit.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            System.exit(0);
            }

            @Override
            public void mousePressed(MouseEvent e) {

                System.exit(0);
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        mainPanel.add(exit);
    }

}