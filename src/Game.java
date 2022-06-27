import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.AncestorListener;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class Game extends JFrame {

    static int cardsCols;
    static int cardsRows;
    static ArrayList<Pair> cards;
    static int cardSize=70;

    public static void main(String[] args) {
        cards=CreatePairs.temp();
        countRowsAndCols();

        JFrame gameFrame = new JFrame("Mathmory");
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addComponentsToPane(gameFrame.getContentPane());
        gameFrame.pack();
//        gameFrame.setSize(cardSize*cardsCols,cardSize*(cardsRows+1));
        gameFrame.setVisible(true);
    }

    private static void addComponentsToPane(Container pane) {
        try {
            pane.setLayout(new GridBagLayout());
            GridBagConstraints constraints = new GridBagConstraints();
            pane.setBackground(Color.decode("#F7FAA5"));

/*            JLabel name = new JLabel("MathMory");
            name.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 20));
            name.setForeground(Color.decode("#970EAB"));
            constraints.gridx = 0;
            constraints.gridy = 0;
            pane.add(name, constraints);*/

            ImageIcon iconExit = new ImageIcon(ImageIO.read(new File("buttons\\exit.png")).getScaledInstance(cardSize, cardSize * 2 / 3, Image.SCALE_DEFAULT));
            JLabel exit = new JLabel(iconExit);
            constraints.anchor = GridBagConstraints.FIRST_LINE_END;
            constraints.insets = new Insets(0, 0, cardSize / 3, 0);  //top padding
            constraints.gridx = cardsCols - 1;
            constraints.gridy = 0;
            pane.add(exit, constraints);

            int num = 0;
            for (int r = 1; r <= cardsRows; r++) {
                for (int c = 0; c < cardsCols; c++) {
                    ImageIcon iconCard = new ImageIcon("images\\card.png");
                    JLabel card = new JLabel(iconCard);
                    card.setSize(cardSize, cardSize);
                    constraints.insets=new Insets(0,0,0,0);
                    constraints.gridx = c;
                    constraints.gridy = r;
                    pane.add(card, constraints);
                    int finalNum = num;
                    card.addMouseListener(new MouseListener() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            System.out.println(finalNum);
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
                    num++;
                }
            }
        } catch(IOException e){
            System.out.println("Image was not found");
        }
    }

    public Game(ArrayList<Pair> cards) {
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

    static BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) {
        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = resizedImage.createGraphics();
        graphics2D.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
        graphics2D.dispose();
        return resizedImage;
    }
}