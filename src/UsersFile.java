import java.io.*;
import java.util.ArrayList;

public class UsersFile {
//ФАЙЛ
    static File file = new File("C:\\ab1\\game.txt");
    ArrayList<User> userArrayList;

    public static File getFile() {
        return file;
    }

    public static void setFile(File file) {
        UsersFile.file = file;
    }

    public ArrayList<User> getUserArrayList() {
        return userArrayList;
    }

    public void setUserArrayList(ArrayList<User> userArrayList) {
        this.userArrayList = userArrayList;
    }

    public UsersFile() throws FileNotFoundException {
        this.userArrayList = createArrayListFromFile();

    }

    private static ArrayList<User> createArrayListFromFile() throws FileNotFoundException {
        FileReader fr = new FileReader(file.getAbsolutePath());
        BufferedReader br = new BufferedReader(fr);
        String s;

        String name = "";
        int score=0;
        int numGames=0;
        boolean get1000=false;
        boolean get2000=false;
        boolean get5000=false;
        boolean hardLevel=false;
        boolean games10=false;
        boolean games30=false;
        boolean games100=false;
        boolean seconds60=false;
        boolean seconds30=false;
        boolean fails10=false;
        int num=-1;
ArrayList<User> answ = new ArrayList<>();
        try {
            do {
                s = br.readLine();
                if (s!=null ) {
                    if(s.equals(" ")){
                        num=0;
                    }
                    else if(num>=0){
                        switch(num){
                            case 0:
                                name=returnStringPart(s, 6);
                                break;
                            case 1:
                                score=Integer.valueOf(returnStringPart(s, 7));
                                break;
                            case 2:
                                numGames=Integer.valueOf(returnStringPart(s, 10));
                                break;
                            case 3:
                                get1000 = findAchivment(s);
                                break;
                            case 4:
                                get2000 = findAchivment(s);
                                break;
                            case 5:
                                get5000= findAchivment(s);
                                break;
                            case 6:
                                hardLevel= findAchivment(s);
                                break;
                            case 7:
                                games10= findAchivment(s);
                                break;
                            case 8:
                                games30= findAchivment(s);
                                break;
                            case 9:
                                games100= findAchivment(s);
                                break;
                            case 10:
                                seconds60= findAchivment(s);
                                break;
                            case 11:
                                seconds30=findAchivment(s);
                                break;
                            case 12:
                                fails10= findAchivment(s);
                                answ.add(new User(name, score, numGames, get1000, get2000, get5000, hardLevel, games10, games30, games100, seconds60, seconds30, fails10));
                                break;
                        }
                        num++;
                    }
                }
            }
            while (s != null);
        }catch (IOException e) {
            System.out.println(e);
        }
        return answ;
    }

    private static int findThehighestScore(ArrayList<User> user){
        int answ=0;
        for(int i=0; i<user.size(); i++){
            if(user.get(i).getScore()>answ)
                answ=user.get(i).getScore();
        }
        return answ;
    }

    private static void writeArrayListToTheFile(ArrayList<User> user) throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter(file.getAbsolutePath(), "UTF-8");
        writer.println("The higest score: "+findThehighestScore(user));
        writer.println(" ");
       for(int i=0; i<user.size(); i++){
           writer.println(user.get(i));
           writer.println(" ");
       }

        writer.close();
    }

    private static boolean findAchivment(String s){
        if(s.charAt(s.length()-2)=='u'){
            return true;
        }
        return false;
    }

    private static String returnStringPart(String s, int num){
        String answ="";
        for(int i=num; i<s.length(); i++){
            answ=answ+s.charAt(i);
        }
        return answ;
    }
}
