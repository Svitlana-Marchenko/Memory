import javax.imageio.ImageIO;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import javax.swing.event.AncestorListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;


public class Game extends JFrame {

    static int cardsCols;
    static int cardsRows;
    static ArrayList<Pair> cards;
    static int cardSize=100;
     int openedCard=-1;
    int secondOpenedCard=-1;
    JPanel gamePanel;
    static ArrayList<Integer> openedCards;
    JLabel opened;
    JLabel secondOpened;
    boolean clean;
    static long startTime;
    int mistakes;
    static CreatePairs gameSettings;
    static User player;

    public Game(){
        super("Mathmory");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(cardsCols*cardSize, cardsRows*cardSize);
        initGame(this);
    }

    public static void runGame(CreatePairs settings, User user) {
        player=user;
        gameSettings=settings;
        startTime = System.currentTimeMillis();
        openedCards=new ArrayList<>();
        cards=settings.getArrayCards();
        cards = sortRandomly(cards);
        countRowsAndCols();
        Game a = new Game();
        a.setBounds(200,0,cardsCols*cardSize+cardsCols*7,(cardsRows+1)*cardSize+cardsRows*20);
        a.setVisible(true);
    }

    private MouseListener createListener(int finalNum){
        MouseListener listener=new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(clean){
                    try {
                        clean();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    clean=false;
                }
                if(openedCard!=finalNum&&!alreadyOpened(finalNum)) {
                    gamePanel.remove(finalNum+cardsCols);
                    int size = calculateSize(cards.get(finalNum).getValue());
                    if(opened==null) {
                        opened = new JLabel(cards.get(finalNum).getValue());
                        opened.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, size));
                        opened.setPreferredSize(new Dimension(cardSize, cardSize));
                        gamePanel.add(opened,finalNum+cardsCols);
                    }else{
                        secondOpenedCard=finalNum;
                        secondOpened = new JLabel(cards.get(finalNum).getValue());
                        secondOpened.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, size));
                        secondOpened.setPreferredSize(new Dimension(cardSize, cardSize));
                        gamePanel.add(secondOpened,finalNum+cardsCols);
                    }
                    gamePanel.revalidate();
                    repaint();
                    if (secondOpened!=null) {
                        if (cards.get(openedCard).getPairValue().equals(cards.get(finalNum).getValue())) {
                            openedCards.add(openedCard);
                            openedCards.add(finalNum);
                            openedCard=-1;
                            secondOpenedCard=-1;
                            opened=null;
                            secondOpened=null;
                            if(openedCards.size()==cards.size()) Victory();
                        } else {
                            mistakes++;
                            clean=true;
                        }
                    }else{
                        openedCard = finalNum;
                    }
                }
            }
        };
        return listener;
    }

    private void Victory()  {
        long elapsedTime = System.currentTimeMillis() - startTime;
        long elapsedSeconds = elapsedTime / 1000;
        player.setNumGames(player.getNumGames()+1);
        Results res=new Results(mistakes, (int) elapsedSeconds,gameSettings);
        int score= countScore(res);
        CongratulationMassage mes=new CongratulationMassage("Your score is "+score,"images\\firework.jpg");
        new Achievements().checkAll(player,score,res);
        setVisible(false);
        dispose();
        MainMenu a= new MainMenu(true,player);
        a.setBounds(200,0,900,900);
        a.setVisible(true);
    }


    private int countScore(Results res){
        double answ = 10000-res.getMistakes();
        answ = answ/ res.getTime();
        if(res.getGameSettings().isSubtraction()){
            answ=answ*1.4;
        }
        if(res.getGameSettings().isMultiplication()){
            answ=answ*1.6;
        }
        if(res.getGameSettings().isDivision()){
            answ=answ*1.8;
        }
        answ = answ*res.getGameSettings().getMaxNumDoing()*(res.getGameSettings().getMaxNumDoing()-res.getGameSettings().getMinNumDoing())/10;
        if(res.getGameSettings().getMinNum()<0){
            answ = answ*1.2;
        }
        if(res.getGameSettings().getMaxNum()>600){
            answ=answ*1.5;
        }
        answ = answ*res.getGameSettings().getNumPairs()*(res.getGameSettings().getMaxNum()-res.getGameSettings().getMinNum())/100;

        return (int) answ;
    }

    private void initGame(JFrame frame) {
        try {
            gamePanel = new JPanel(new GridLayout(cardsRows+1, cardsCols));
            gamePanel.setBackground(Color.decode("#F7FAA5"));
            add(gamePanel);

            JLabel nick = new JLabel(player.getName(), SwingConstants.CENTER);
            nick.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, calculateSize(player.getName())));
            nick.setForeground(Color.decode("#970EAB"));
            gamePanel.add(nick);
            for(int i=1;i<cardsCols-1;i++){
                gamePanel.add(new JLabel(""));
            }
            ImageIcon iconExit = new ImageIcon(ImageIO.read(new File("buttons\\exit.png")).getScaledInstance(cardSize, cardSize * 2 / 3, Image.SCALE_DEFAULT));
            JLabel exit = new JLabel(iconExit);
            gamePanel.add(exit);
            exit.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    setVisible(false);
                    dispose();
                    MainMenu a= new MainMenu(true,player);
                    a.setBounds(200,0,600,800);
                    a.setVisible(true);
                }
            });

            int num = 0;
            for (int r = 1; r <= cardsRows; r++) {
                for (int c = 0; c < cardsCols; c++) {
                    ImageIcon iconCard = new ImageIcon(ImageIO.read(new File("images\\card.png")).getScaledInstance(cardSize, cardSize, Image.SCALE_DEFAULT));
                    JLabel card = new JLabel(iconCard);
                    gamePanel.add(card);
                    int finalNum = num;
                    card.addMouseListener(createListener(finalNum));
                    num++;
                }
            }
        } catch(IOException e){
            System.out.println("Image was not found");
        }
    }

    private boolean alreadyOpened(int finalNum) {
        for (int a:openedCards) {
            if(a==finalNum) return true;
        }
        return false;
    }

    private void clean() throws IOException {
        gamePanel.remove(opened);
        JLabel one=new JLabel(new ImageIcon(ImageIO.read(new File("images\\card.png")).getScaledInstance(cardSize, cardSize, Image.SCALE_DEFAULT)));
        one.addMouseListener(createListener(openedCard));
        gamePanel.add(one,openedCard+cardsCols);
        gamePanel.remove(secondOpened);
        JLabel two=new JLabel(new ImageIcon(ImageIO.read(new File("images\\card.png")).getScaledInstance(cardSize, cardSize, Image.SCALE_DEFAULT)));
        two.addMouseListener(createListener(secondOpenedCard));
        gamePanel.add(two,secondOpenedCard+cardsCols);
        gamePanel.revalidate();
        repaint();
        openedCard=-1;
        secondOpenedCard=-1;
        opened=null;
        secondOpened=null;
    }

    private int calculateSize(String textGiven) {
        int size=40;
        Font font = new Font("Arial Rounded MT Bold", Font.PLAIN, size);
        FontRenderContext frc = new FontRenderContext(new AffineTransform(), true, true);
        int textwidth = (int)(font.getStringBounds(textGiven, frc).getWidth());
        while(textwidth>=cardSize){
            size--;
            font = new Font("Arial Rounded MT Bold", Font.PLAIN, size);
            textwidth = (int)(font.getStringBounds(textGiven, frc).getWidth());
        }
        return size;
    }

    private static void countRowsAndCols() {
        switch (cards.size()){
            case 4: case 6:
                cardsRows=2;
                cardsCols=cards.size()/2;
                break;
            case 8: case 12: case 16:
                cardsRows=4;
                cardsCols=cards.size()/4;
                break;
            default:
                if((cards.size()%6>=cards.size()%5&&cards.size()%5!=0)||cards.size()%6==0){
                    cardsCols=6;
                    cardsRows=(int)(cards.size()/cardsCols);
                    if(cards.size()%6!=0) cardsRows++;
                }else{
                    cardsCols=5;
                    cardsRows=(int)(cards.size()/cardsCols);
                    if(cards.size()%5!=0) cardsCols++;
                }
                break;
        }
    }

    private static ArrayList<Pair> sortRandomly(ArrayList<Pair> list){
    Pair[] pairsArray = createArrayFromArrayList(list);
    randomSort(pairsArray);
    return createArrayListFromArray(pairsArray);
    }

    private static ArrayList<Pair> createArrayListFromArray(Pair[] info){
        ArrayList<Pair> answ= new ArrayList<>();
        for(int i=0; i<info.length; i++){
            answ.add(info[i]);
        }
        return answ;
    }

    private static Pair[] randomSort(Pair[] info){
        Random rnd = new Random();
        for(int i = 0; i < info.length; i++) {
            int index = rnd.nextInt(i + 1);
            Pair a = info[index];
            info[index] = info[i];
            info[i] = a;
        }
        return info;
    }

    private static Pair[] createArrayFromArrayList(ArrayList<Pair> info){
        Pair[] answ = new Pair[info.size()];
        for(int i=0; i<info.size(); i++){
            answ[i] = info.get(i);
        }
        return answ;
    }
}