import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
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
        initWelcomePage(this);


    }

    /**
     * We will sort ArrayList with users from the highest score to the lowest score. If several users have the same score, they will be sorted with alphabet
     */
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

        ImageIcon iconPlay = new ImageIcon("buttons\\Play.png");
        JLabel play = new JLabel(iconPlay);
        play.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
            }
        });
        mainPanel.add(play);

        ImageIcon iconAwards = new ImageIcon("buttons\\Awards.png");
        JLabel awards = new JLabel(iconAwards);
        awards.addMouseListener(new MouseAdapter() {
                                    @Override
                                    public void mousePressed(MouseEvent e) {
                                        super.mousePressed(e);
                                    }
                                });
                mainPanel.add(awards);

        ImageIcon iconExit = new ImageIcon("buttons\\Exit.png");
        JLabel exit = new JLabel(iconExit);
       exit.addMouseListener(new MouseAdapter() {
                                 @Override
                                 public void mousePressed(MouseEvent e) {
                                     super.mousePressed(e);
                                     System.exit(0);
                                 }
                             });
               mainPanel.add(exit);
    }

    private void initWelcomePage(JFrame frame){
       JPanel welcomePanel = new JPanel(new GridLayout(4, 1));
        welcomePanel.setBackground(Color.decode("#F7FAA5"));
        add(welcomePanel);
        JLabel welcome = new JLabel("Welcome!", SwingConstants.CENTER);
        welcome.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 70));
        welcome.setForeground(Color.decode("#970EAB"));
        welcomePanel.add(welcome);

        ImageIcon iconLogin = new ImageIcon("buttons\\login.png");
        JLabel login = new JLabel(iconLogin);
        login.addMouseListener(new MouseAdapter() {
                                   @Override
                                   public void mousePressed(MouseEvent e) {
                                       super.mousePressed(e);
                                       if (userFile.getUserArrayList().size() == 0) {
                                           new Message("Please, sign up before logining");
                                       } else {
                                           chooseUser choose = new chooseUser(createArrayFromArrayList());
                                           String username = choose.getInfo();
                                           user = findUserUsingName(username);
                                           welcomePanel.setVisible(false);
                                           remove(welcomePanel);
                                           init(frame);
                                       }
                                   }
                               });
                welcomePanel.add(login);


        JLabel or = new JLabel("or", SwingConstants.CENTER);
        or.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 70));
        or.setForeground(Color.decode("#970EAB"));
        welcomePanel.add(or);

        ImageIcon iconSignup = new ImageIcon("buttons\\signup.png");
        JLabel signup = new JLabel(iconSignup);
        signup.addMouseListener(new MouseAdapter() {
                                    @Override
                                    public void mousePressed(MouseEvent e) {
                                        super.mousePressed(e);
                                        signUp typingName = new signUp();
                                        String name = typingName.getInfo();
                                        if(name!=null&&!checkTheNameOnExsiting(name)){
                                            userFile.getUserArrayList().add(new User(name));
                                            try {
                                                userFile.writeArrayListToTheFile(userFile.getUserArrayList());
                                            } catch (FileNotFoundException ex) {
                                                ex.printStackTrace();
                                            } catch (UnsupportedEncodingException ex) {
                                                ex.printStackTrace();
                                            }
                                            user = findUserUsingName(name);
                                            welcomePanel.setVisible(false);
                                            remove(welcomePanel);
                                            init(frame);
                                        }
                                        else if(name!=null&&checkTheNameOnExsiting(name)){
                                            new Message("This user has already been created");
                                        }
                                    }
                                });
                welcomePanel.add(signup);
    }


    /**
     * We will create array with user name from arraylist
     * @return array with user name
     */
    private String[] createArrayFromArrayList(){
        String[] answ = new String[userFile.getUserArrayList().size()];
        for(int i=0; i<userFile.getUserArrayList().size(); i++){
            answ[i] = userFile.getUserArrayList().get(i).getName();
        }
        return answ;
    }

    private User findUserUsingName(String name){
        for(int i=0; i<userFile.getUserArrayList().size(); i++){
            if(name.equals(userFile.getUserArrayList().get(i).getName()))
                return userFile.getUserArrayList().get(i);
        }
        return null;
    }
public boolean checkTheNameOnExsiting(String name){
        for(int i=0; i<userFile.getUserArrayList().size(); i++){
            if(name.equals(userFile.getUserArrayList().get(i).getName()))
                return true;
        }
        return false;
}
}