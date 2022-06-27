import java.util.HashMap;
import java.util.Map;

public class Achievements {

    User user;
    int score;
    Results results;
    int operationsNum;

    public void checkAll(User user, int score, Results results){
        this.user=user;
        this.score=score;
        this.results=results;
        countOperationsNum();
        if(!user.isGet1000()) checkGet1000();
        if(!user.isGet2000()) checkGet2000();
        if(!user.isGet5000()) checkGet5000();
        if(!user.isHardLevel()) checkHardLevel();
        if(!user.isGames10()) checkGames10();
        if(!user.isGames30()) checkGames30();
        if(!user.isGames100()) checkGames100();
        if(!user.isSeconds60()) checkSeconds60();
        if(!user.isSeconds30()) checkSeconds30();
        if(!user.isFails10()) checkFails10();
    }

    private void countOperationsNum() {
        if (results.getGameSettings().isAddition()) operationsNum++;
        if (results.getGameSettings().isSubtraction()) operationsNum++;
        if (results.getGameSettings().isMultiplication()) operationsNum++;
        if (results.getGameSettings().isDivision()) operationsNum++;
    }

    private void checkFails10() {
        if(operationsNum>2&&results.getGameSettings().getNumPairs()>19&&results.getMistakes()<10){
            user.setFails10(true);
            CongratulationMassage a=new CongratulationMassage("You received a new achievement!","images\\achievements\\fails10.png");
        }
    }

    private void checkSeconds30() {
        if(operationsNum>2&&results.getGameSettings().getNumPairs()>19&&results.getTime()<30){
            user.setSeconds30(true);
            CongratulationMassage a=new CongratulationMassage("You received a new achievement!","images\\achievements\\seconds30.png");
        }
    }

    private void checkSeconds60() {
        if(operationsNum>2&&results.getGameSettings().getNumPairs()>19&&results.getTime()<60){
            user.setSeconds60(true);
            CongratulationMassage a=new CongratulationMassage("You received a new achievement!","images\\achievements\\seconds60.png");
        }
    }

    private void checkGames100() {
        if(user.getNumGames()-1>=100){
            user.setGames100(true);
            CongratulationMassage a=new CongratulationMassage("You received a new achievement!","images\\achievements\\games100.png");
        }
    }

    private void checkGames30() {
        if(user.getNumGames()-1>=30) {
            user.setGames30(true);
            CongratulationMassage a = new CongratulationMassage("You received a new achievement!", "images\\achievements\\games30.png");
        }
    }

    private void checkGames10() {
        if(user.getNumGames()-1>=10){
            user.setGames10(true);
            CongratulationMassage a=new CongratulationMassage("You received a new achievement!","images\\achievements\\games10.png");
        }
    }

    private void checkHardLevel() {
        user.setHardLevel(true);
        CongratulationMassage a=new CongratulationMassage("You received a new achievement!","images\\achievements\\thehardest.png");
    }

    private void checkGet1000() {
        if(score>=1000){
            user.setGet1000(true);
            CongratulationMassage a=new CongratulationMassage("You received a new achievement!","images\\achievements\\get1000.png");
        }
    }

    private void checkGet2000() {
        if(score>=2000){
            user.setGet2000(true);
            CongratulationMassage a=new CongratulationMassage("You received a new achievement!","images\\achievements\\get2000.png");
        }
    }

    private void checkGet5000() {
        if(score>=5000){
            user.setGet5000(true);
            CongratulationMassage a=new CongratulationMassage("You received a new achievement!","images\\achievements\\get5000.png");
        }
    }
}
