import java.util.HashMap;
import java.util.Map;

public class Achievements {

    public void checkAll(User user, int score, int operationsNum, int maxNum, int minNum, int pairNum, int time, int mistakes){
        if(!user.isGet1000()) checkGet1000(score);
        if(!user.isGet2000()) checkGet2000(score);
        if(!user.isGet5000()) checkGet5000(score);
        if(!user.isHardLevel()) checkHardLevel(operationsNum,maxNum,minNum);
        if(!user.isGames10()) checkGames10(user);
        if(!user.isGames30()) checkGames30(user);
        if(!user.isGames100()) checkGames100(user);
        if(!user.isSeconds60()) checkSeconds60(operationsNum,pairNum,time);
        if(!user.isSeconds30()) checkSeconds30(operationsNum,pairNum,time);
        if(!user.isFails10()) checkFails10(operationsNum,pairNum,mistakes);
    }

    private void checkFails10(int operationsNum, int pairNum, int mistakes) {
        if(operationsNum>2&&pairNum>19&&mistakes<10){
            //TODO add actionts when reward lessThan10mistakes is recieved
        }
    }

    private void checkSeconds30(int operationsNum, int pairNum, int time) {
        if(operationsNum>2&&pairNum>19&&time<30){
            //TODO add actionts when reward lessThan30s is recieved
        }
    }

    private void checkSeconds60(int operationsNum, int pairNum, int time) {
        if(operationsNum>2&&pairNum>19&&time<60){
            //TODO add actionts when reward lessThan60s is recieved
        }
    }

    private void checkGames100(User user) {
        if(user.getNumGames()>=100){
            //TODO add actionts when reward play100 is recieved
        }
    }

    private void checkGames30(User user) {
        if(user.getNumGames()>=30){
            //TODO add actionts when reward play30 is recieved
        }
    }

    private void checkGames10(User user) {
        if(user.getNumGames()>=10){
            //TODO add actionts when reward play10 is recieved
        }
    }

    private void checkHardLevel(int operationsNum, int maxNum, int minNum) {
        //TODO checking of reward doHardest when all points will be clear
    }

    private void checkGet1000(int score) {
        if(score>=1000){
            //TODO add actionts when reward get1000 is recieved
        }
    }

    private void checkGet2000(int score) {
        if(score>=2000){
            //TODO add actionts when reward get2000 is recieved
        }
    }

    private void checkGet5000(int score) {
        if(score>=5000){
            //TODO add actionts when reward get5000 is recieved
        }
    }
}
