import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.AncestorListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Objects;
import java.util.concurrent.TimeUnit;


public class Game extends JFrame {

    static int cardsCols;
    static int cardsRows;
    static ArrayList<Pair> cards;
    static int cardSize=70;
     int openedCard=-1;
    int secondOpenedCard=-1;
    static JPanel gamePanel;
    static ArrayList<Integer> openedCards;
    static JLabel opened;
    static JLabel secondOpened;
    boolean clean;

    public Game(){
        super("Mathmory");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(cardsCols*cardSize, cardsRows*cardSize);
        initGame(this);
    }

    public static void main(String[] args) {
        openedCards=new ArrayList<>();
        cards=CreatePairs.temp();
        countRowsAndCols();
        Game a = new Game();
        a.setBounds(200,0,cardsCols*cardSize+cardsCols*7,(cardsRows+1)*cardSize+cardsRows*20);
        a.setVisible(true);
    }

    private MouseListener createListener(int finalNum){
        MouseListener listener=new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(clean){
                    clean();
                    clean=false;
                }
                if(openedCard!=finalNum&&!alreadyOpened(finalNum)) {
                    gamePanel.remove(finalNum+cardsCols);
                    System.out.println(finalNum);
                    int size = calulateSize(cards.get(finalNum).getValue());
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
                        } else {
                            clean=true;
                        }
                    }else{
                        openedCard = finalNum;
                    }
                }
            }
            public void mousePressed(MouseEvent e) {}
            public void mouseReleased(MouseEvent e) {}
            public void mouseEntered(MouseEvent e) {}
            public void mouseExited(MouseEvent e) {}
        };
        return listener;
    }
    private void initGame(JFrame frame) {
        try {
            gamePanel = new JPanel(new GridLayout(cardsRows+1, cardsCols));
            gamePanel.setBackground(Color.decode("#F7FAA5"));
            add(gamePanel);

            for(int i=0;i<cardsCols-1;i++){
                gamePanel.add(new JLabel(""));
            }
            ImageIcon iconExit = new ImageIcon(ImageIO.read(new File("buttons\\exit.png")).getScaledInstance(cardSize, cardSize * 2 / 3, Image.SCALE_DEFAULT));
            JLabel exit = new JLabel(iconExit);
            gamePanel.add(exit);

            int num = 0;
            for (int r = 1; r <= cardsRows; r++) {
                for (int c = 0; c < cardsCols; c++) {
                    ImageIcon iconCard = new ImageIcon("images\\card.png");
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

    private void clean(){
        gamePanel.remove(opened);
        JLabel one=new JLabel(new ImageIcon("images\\card.png"));
        one.addMouseListener(createListener(openedCard));
        gamePanel.add(one,openedCard+cardsCols);
        gamePanel.remove(secondOpened);
        JLabel two=new JLabel(new ImageIcon("images\\card.png"));
        two.addMouseListener(createListener(secondOpenedCard));
        gamePanel.add(two,secondOpenedCard+cardsCols);
        gamePanel.revalidate();
        repaint();
        openedCard=-1;
        secondOpenedCard=-1;
        opened=null;
        secondOpened=null;
    }

    private static int calulateSize(String textGiven) {
        int size=20;
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

}