public class Results {
    private int mistakes;
    private int time;
    private CreatePairs gameSettings;

    /**
     * class for results of the game
     * @param mistakes num of mistakes
     * @param time time of gaming in seconds
     * @param gameSettings the starting settings of the game
     */
    public Results(int mistakes, int time, CreatePairs gameSettings) {
        this.mistakes = mistakes;
        this.time = time;
        this.gameSettings = gameSettings;
    }

    public int getMistakes() {
        return mistakes;
    }

    public void setMistakes(int mistakes) {
        this.mistakes = mistakes;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public CreatePairs getGameSettings() {
        return gameSettings;
    }

    public void setGameSettings(CreatePairs gameSettings) {
        this.gameSettings = gameSettings;
    }
}
