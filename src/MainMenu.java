import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.util.Collections;
import java.util.Comparator;


public class MainMenu extends JFrame {

    JPanel mainPanel;
    User user;
    UsersFile userFile;
    JFrame mainFrame;


    public MainMenu(boolean afterGame, User user) throws IOException, UnsupportedAudioFileException {
        super("Mathmory");
        this.user=user;
        mainFrame=this;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(900, 900);
        this.userFile = new UsersFile();
        if(!afterGame)initWelcomePage(this);
        else init(this);

    }

    /**
     * We will sort ArrayList with users from the highest score to the lowest score. If several users have the same score, they will be sorted with alphabet
     */
    private void sortUserFile(){
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

        ImageIcon iconPlay = new ImageIcon("buttons\\play1.png");
        JLabel play = new JLabel(iconPlay);
        play.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                addSoundButtonEffect();
                mainPanel.setVisible(false);
                remove(mainPanel);
                initGameSettings(frame);
            }
        });
        mainPanel.add(play);

        ImageIcon iconAwards = new ImageIcon("buttons\\awards1.png");
        JLabel awards = new JLabel(iconAwards);
        awards.addMouseListener(new MouseAdapter() {
                                    @Override
                                    public void mousePressed(MouseEvent e) {
                                        super.mousePressed(e);
                                        addSoundButtonEffect();
                                        AchievementsMenu a = new AchievementsMenu(user);
                                        a.setBounds(200,0,640,830);
                                        a.setVisible(true);
                                    }
                                });
                mainPanel.add(awards);

        ImageIcon iconExit = new ImageIcon("buttons\\exit1.png");
        JLabel exit = new JLabel(iconExit);
       exit.addMouseListener(new MouseAdapter() {
                                 @Override
                                 public void mousePressed(MouseEvent e) {
                                     super.mousePressed(e);
                                     addSoundButtonEffect();
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
                                       addSoundButtonEffect();
                                       if (userFile.getUserArrayList().size() == 0) {
                                           addSoundProblemsEffect();
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
                                        addSoundButtonEffect();
                                        signUp typingName = new signUp();
                                        String name = typingName.getInfo();
                                        if(name!=null && name.equals("")){
                                            addSoundProblemsEffect();
                                            new Message("This name is empty");
                                        }
                                        else if(name!=null&&!checkTheNameOnExsiting(name)){
                                            userFile.getUserArrayList().add(new User(name));
                                            userFile.writeArrayListToTheFile(userFile.getUserArrayList());
                                            user = findUserUsingName(name);
                                            welcomePanel.setVisible(false);
                                            remove(welcomePanel);
                                            init(frame);
                                        }
                                        else if(name!=null&&checkTheNameOnExsiting(name)){
                                            addSoundProblemsEffect();
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

    private void initGameSettings(JFrame frame){
        ButtonGroup operation = new ButtonGroup();
        JPanel settingsPanel = new JPanel(new GridLayout(9, 2));
        settingsPanel.setBackground(Color.decode("#F7FAA5"));
        add(settingsPanel);

        JLabel operations = new JLabel("1) Operations:");
        operations.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 35));
        operations.setForeground(Color.decode("#970EAB"));
        settingsPanel.add(operations);

        JLabel label = new JLabel("      ");
        label.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 35));
        label.setForeground(Color.decode("#970EAB"));
        settingsPanel.add(label);

        ImageIcon iconAdd = new ImageIcon("MathOperations\\addition1.png");

        ImageIcon iconDiv = new ImageIcon("MathOperations\\division1.png");

        ImageIcon iconMult = new ImageIcon("MathOperations\\multiplication1.png");

        ImageIcon iconSub = new ImageIcon("MathOperations\\subtraction1.png");

        JCheckBoxMenuItem addition = new JCheckBoxMenuItem("", iconAdd);
        addition.setBackground(Color.decode("#F7FAA5"));
        operations.add(addition);
        settingsPanel.add(addition);

        JCheckBoxMenuItem subtraction = new JCheckBoxMenuItem("", iconSub);
        subtraction.setBackground(Color.decode("#F7FAA5"));
        operations.add(subtraction);
        settingsPanel.add(subtraction);

        JCheckBoxMenuItem multiplication = new JCheckBoxMenuItem("", iconMult);
        multiplication.setBackground(Color.decode("#F7FAA5"));
        operations.add(multiplication);
        settingsPanel.add(multiplication);

        JCheckBoxMenuItem division = new JCheckBoxMenuItem("", iconDiv);
        division.setBackground(Color.decode("#F7FAA5"));
        operations.add(division);
        settingsPanel.add(division);

        JLabel quantity = new JLabel("2) Num of cards:");
        quantity.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 35));
        quantity.setForeground(Color.decode("#970EAB"));
        settingsPanel.add(quantity);

        JSpinner cardsQ = new JSpinner(new SpinnerNumberModel(10, 4, 36, 2));
        cardsQ.getEditor().getComponent(0).setBackground(Color.decode("#F7FAA5"));
        cardsQ.setSize(700, 300);
        cardsQ.setFont(new Font("Arial", Font.PLAIN, 25));
        settingsPanel.add(cardsQ);

        JLabel max = new JLabel("3) Max number:");
        max.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 35));
        max.setForeground(Color.decode("#970EAB"));
        settingsPanel.add(max);

        JSpinner maxNum = new JSpinner(new SpinnerNumberModel(10, -1000, 1000, 1));
        maxNum.getEditor().getComponent(0).setBackground(Color.decode("#F7FAA5"));
        maxNum.setSize(700, 300);
        maxNum.setFont(new Font("Arial", Font.PLAIN, 25));
        settingsPanel.add(maxNum);

        JLabel min = new JLabel("4) Min number:");
        min.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 35));
        min.setForeground(Color.decode("#970EAB"));
        settingsPanel.add(min);

        JSpinner minNum = new JSpinner(new SpinnerNumberModel(-10, -1000, 1000, 1));
        minNum.getEditor().getComponent(0).setBackground(Color.decode("#F7FAA5"));
        minNum.setSize(700, 300);
        minNum.setFont(new Font("Arial", Font.PLAIN, 25));
        settingsPanel.add(minNum);

        JLabel maxQO = new JLabel("5) Min num of operations:");
        maxQO.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 35));
        maxQO.setForeground(Color.decode("#970EAB"));
        settingsPanel.add(maxQO);

        JSlider maxNumO = new JSlider(1, 5, 3);
        maxNumO.setBackground(Color.decode("#F7FAA5"));

        maxNumO.setFont(new Font("Arial", Font.PLAIN, 25));

        maxNumO.setPaintLabels(true);
        maxNumO.setPaintTicks(true);

        maxNumO.setPaintLabels(true);
        maxNumO.setMajorTickSpacing(1);

        maxNumO.setValueIsAdjusting(true);
        settingsPanel.add(maxNumO);

        JLabel minQO = new JLabel("6) Min num of operations:");
        minQO.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 35));
        minQO.setForeground(Color.decode("#970EAB"));
        settingsPanel.add(minQO);

        JSlider minNumO = new JSlider(1, 5, 1);
        minNumO.setBackground(Color.decode("#F7FAA5"));

        minNumO.setFont(new Font("Arial", Font.PLAIN, 25));

        minNumO.setPaintLabels(true);
        minNumO.setPaintTicks(true);

        minNumO.setPaintLabels(true);
        minNumO.setMajorTickSpacing(1);

        minNumO.setValueIsAdjusting(true);
        settingsPanel.add(minNumO);

        ImageIcon iconBack = new ImageIcon("buttons\\back1.png");
        JLabel back = new JLabel(iconBack);
        back.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                addSoundButtonEffect();
                settingsPanel.setVisible(false);
                remove(settingsPanel);
                init(frame);
            }
        });
        settingsPanel.add(back);

        ImageIcon iconStart = new ImageIcon("buttons\\start1.png");
        JLabel start = new JLabel(iconStart);
        start.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                addSoundButtonEffect();
                boolean add = addition.getState();
                boolean subtr = subtraction.getState();
                boolean mult = multiplication.getState();
                boolean div = division.getState();
                int numD = (int) cardsQ.getValue();
                int min = (int) minNum.getValue();
                int max = (int) maxNum.getValue();
                int minD = (int) minNumO.getValue();
                int maxD = (int) maxNumO.getValue();

                if(minD>maxD){
                    addSoundProblemsEffect();
                    new Message("Oh, the min number of operations is bigger than the max number.\n Please change it.");
                }

                else if(max<min){
                    addSoundProblemsEffect();
                    new Message("Oh, the min number is bigger than the max number.\n Please change it.");
                }

               else if((max>0 && min>0 && (1+max-min)<numD) || (max<0 && min<0 && (1+max-min)<numD) || (max>=0 && min<=0 && (max-min)<numD)){
                    addSoundProblemsEffect();
                    new Message("You`ve chosen too small number interval or too many cards.\nPlease, change interval or quantity of cards");
                }

               else if((add==false && subtr==false && mult==false && div==false)){
                    addSoundProblemsEffect();
                    new Message("You have not selected any operations. Please, select one at least.");
                }

               else {
                    CreatePairs a = new CreatePairs(add, subtr, mult, div, max, min, maxD, minD, numD);
                    settingsPanel.setVisible(false);
                    remove(settingsPanel);
                    Game.runGame(a,user);
                    setVisible(false);
                    dispose();
                }
            }
        });
        settingsPanel.add(start);

    }

    private void addSoundButtonEffect(){
        try {
            String click = "Sounds\\click.wav";
            AudioInputStream audioInputStream2 = AudioSystem.getAudioInputStream(new File(click).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream2);
            clip.loop(0);
        }catch(LineUnavailableException | IOException ex){
            System.err.println(ex.getMessage());
        } catch (UnsupportedAudioFileException ex) {
            ex.printStackTrace();
        }
    }

    private void addSoundProblemsEffect(){
        try {
            String click = "Sounds\\problemsSound.wav";
            AudioInputStream audioInputStream2 = AudioSystem.getAudioInputStream(new File(click).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream2);
            clip.loop(0);
        }catch(LineUnavailableException | IOException ex){
            System.err.println(ex.getMessage());
        } catch (UnsupportedAudioFileException ex) {
            ex.printStackTrace();
        }
    }

}