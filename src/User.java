public class User {
    private String name;
    private int score;
    private int numGames;
    private boolean get1000;
    private boolean get2000;
    private boolean get5000;
    private boolean hardLevel;
    private boolean games10;
    private boolean games30;
    private boolean games100;
    private boolean seconds60;
    private boolean seconds30;
    private boolean fails10;


    public User(String name, int score, int numGames, boolean get1000, boolean get2000, boolean get5000, boolean hardLevel, boolean games10, boolean games30, boolean games100, boolean seconds60, boolean seconds30, boolean fails10) {
        this.name = name;
        this.score = score;
        this.numGames = numGames;
        this.get1000 = get1000;
        this.get2000 = get2000;
        this.get5000 = get5000;
        this.hardLevel = hardLevel;
        this.games10 = games10;
        this.games30 = games30;
        this.games100 = games100;
        this.seconds60 = seconds60;
        this.seconds30 = seconds30;
        this.fails10 = fails10;
    }

    public User(String name) {
        this.name = name;
        this.numGames = 0;
        this.score=0;
        this.get1000 = false;
        this.get2000 = false;
        this.get5000 = false;
        this.hardLevel=false;
        this.games10=false;
        this.games30=false;
        this.games100=false;
        this.seconds60=false;
        this.seconds30=false;
        this.fails10=false;
    }

    @Override
    public String toString() {
        return "Name= " + name + '\n' +
                "Score= " + score + '\n' +
                "NumGames= " + numGames + '\n' +
                "Get1000= " + get1000 + '\n' +
                "Get2000= " + get2000 + '\n' +
                "Get5000= " + get5000 + '\n' +
                "HardLevel= " + hardLevel + '\n' +
                "Games10= " + games10 + '\n' +
                "Games30= " + games30 + '\n' +
                "Games100= " + games100 + '\n' +
                "Seconds60= " + seconds60 + '\n' +
                "Seconds30= " + seconds30 + '\n' +
                "Fails10= " + fails10 ;
    }

    public int getNumGames() {
        return numGames;
    }

    public void setNumGames(int numGames) {
        this.numGames = numGames;
    }

    public boolean isGet1000() {
        return get1000;
    }

    public void setGet1000(boolean get1000) {
        this.get1000 = get1000;
    }

    public boolean isGet2000() {
        return get2000;
    }

    public void setGet2000(boolean get2000) {
        this.get2000 = get2000;
    }

    public boolean isGet5000() {
        return get5000;
    }

    public void setGet5000(boolean get5000) {
        this.get5000 = get5000;
    }

    public boolean isHardLevel() {
        return hardLevel;
    }

    public void setHardLevel(boolean hardLevel) {
        this.hardLevel = hardLevel;
    }

    public boolean isGames10() {
        return games10;
    }

    public void setGames10(boolean games10) {
        this.games10 = games10;
    }

    public boolean isGames30() {
        return games30;
    }

    public void setGames30(boolean games30) {
        this.games30 = games30;
    }

    public boolean isGames100() {
        return games100;
    }

    public void setGames100(boolean games100) {
        this.games100 = games100;
    }

    public boolean isSeconds60() {
        return seconds60;
    }

    public void setSeconds60(boolean seconds60) {
        this.seconds60 = seconds60;
    }

    public boolean isSeconds30() {
        return seconds30;
    }

    public void setSeconds30(boolean seconds30) {
        this.seconds30 = seconds30;
    }

    public boolean isFails10() {
        return fails10;
    }

    public void setFails10(boolean fails10) {
        this.fails10 = fails10;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean isAchivment1() {
        return get1000;
    }

    public void setAchivment1(boolean achivment1) {
        this.get1000 = achivment1;
    }

    public boolean isAchivment2() {
        return get2000;
    }

    public void setAchivment2(boolean achivment2) {
        this.get2000 = achivment2;
    }

    public boolean isAchivmentN() {
        return get5000;
    }

    public void setAchivmentN(boolean achivmentN) {
        this.get5000 = achivmentN;
    }
}
