import java.util.ArrayList;

public class CreatePairs {

   private static boolean addition; //0
   private static boolean subtraction; //1
   private static boolean multiplication; //2
   private static boolean division; //3


    private static int maxNum;
   private static int minNum;

   private static int numPairs;

   private static int maxNumDoing;
   private static int minNumDoing;

   private static ArrayList<Pair> arrayListWithPair ;


    public static void main(String[] args) {

CreatePairs a = new CreatePairs(true, true, true, true, 50, -50, 5, 1, 2);
        a.fillArrayList();
        for(int i=0; i<a.getArrayListWithPair().size(); i++)
            System.out.println(a.getArrayListWithPair().get(i));

    }

    public CreatePairs(boolean addition, boolean subtraction, boolean multiplication, boolean division, int maxNum, int minNum, int maxNumDoing, int minNumDoing, int numPairs ) {
        this.addition = addition;
        this.subtraction = subtraction;
        this.multiplication = multiplication;
        this.division = division;

        this.maxNum = maxNum;
        this.minNum = minNum;

        this.maxNumDoing = maxNumDoing;
        this.minNumDoing = minNumDoing;

        this.numPairs = numPairs;

        this.arrayListWithPair =new ArrayList<>();
    }

    public static boolean isAddition() {
        return addition;
    }

    public static void setAddition(boolean addition) {
        CreatePairs.addition = addition;
    }

    public static boolean isSubtraction() {
        return subtraction;
    }

    public static void setSubtraction(boolean subtraction) {
        CreatePairs.subtraction = subtraction;
    }

    public static boolean isMultiplication() {
        return multiplication;
    }

    public static void setMultiplication(boolean multiplication) {
        CreatePairs.multiplication = multiplication;
    }

    public static boolean isDivision() {
        return division;
    }

    public static void setDivision(boolean division) {
        CreatePairs.division = division;
    }

    public static int getMaxNum() {
        return maxNum;
    }

    public static void setMaxNum(int maxNum) {
        CreatePairs.maxNum = maxNum;
    }

    public static int getMinNum() {
        return minNum;
    }

    public static void setMinNum(int minNum) {
        CreatePairs.minNum = minNum;
    }

    public static int getNumPairs() {
        return numPairs;
    }

    public static void setNumPairs(int numPairs) {
        CreatePairs.numPairs = numPairs;
    }

    public static int getMaxNumDoing() {
        return maxNumDoing;
    }

    public static void setMaxNumDoing(int maxNumDoing) {
        CreatePairs.maxNumDoing = maxNumDoing;
    }

    public static int getMinNumDoing() {
        return minNumDoing;
    }

    public static void setMinNumDoing(int minNumDoing) {
        CreatePairs.minNumDoing = minNumDoing;
    }

    public static ArrayList<Pair> getArrayListWithPair() {
        return arrayListWithPair;
    }

    public static void setArrayListWithPair(ArrayList<Pair> arrayListWithPair) {
        CreatePairs.arrayListWithPair = arrayListWithPair;
    }

    private static String replaceFirstStr(String string, String target, String replacment){
        int numToReplace;
        boolean found = true;
        String answ = string;
        for(int i=0; i<string.length(); i++){
            if(string.charAt(i)==target.charAt(0)){
                found = true;
                for(int x=i+1, c=1; x<string.length() && c<target.length(); x++, c++){
                    if(string.charAt(x)!=target.charAt(c)){
                        found = false;
                        break;
                    }
                }
                if(found){
                    numToReplace = i;
                    answ = changeStr(string, numToReplace, target.length(), replacment);
                    break;
                }
            }
        }
        return answ;
    }

    private static String changeStr (String str, int numFrom, int length, String replacment){
        String answ = "";
        for(int i=0; i<numFrom; i++){
            answ = answ+str.charAt(i);
        }
        answ = answ+replacment;
        for(int i= numFrom+length; i<str.length(); i++){
            answ = answ+str.charAt(i);
        }
        return answ;
    }

    private static void fillArrayList() {
        ArrayList<String> answers = new ArrayList<>();
        for (int i = 0; i < numPairs; i++) {
            boolean excite = false;
            Pair a;

            do{
                excite = false;
                a = createTask();
                for(int q=0; q< answers.size(); q++){
                    if(a.getPairValue().equals(answers.get(q))){
                        excite = true;
                    }
                }
            }while(excite);


            arrayListWithPair.add(a);
            arrayListWithPair.add(changeValue(a));
            answers.add(a.getPairValue());

        }
    }

    private static Pair changeValue (Pair p){
        return new Pair(p.getPairValue(), p.getValue());
    }


    private static Pair createTask() {
        String task;
        String answ;
        do {
            int numDoing = (int) (Math.random() * (maxNumDoing - minNumDoing + 1) + minNumDoing);
            task = createStringTask(numDoing);
            answ = solveTask(task);
        } while (answ.equals("AAA"));
        return new Pair(task, answ);
    }


    private static String solveTask(String task){
        task = solveAllMultAndDiv(task);
        if(task.equals("AAA"))
            return task;
        task = solveAllAddandSub(task);

        return task;
    }

    private static String solveAllAddandSub(String task){
        boolean work = true;
        String prev = task;
        while(work){
            task = solveAddandSub(task);
            if(task.equals("AAA")){
                return task;
            }
            if(prev.equals(task)){
                work=false;
            }
            prev = task;
        }
        return task;
    }

    private static String solveAddandSub(String task){
        int divMultNumInTask = -1;
        boolean hasAdd = false;
        boolean hasSub = false;
        for(int i=0; i<task.length(); i++){
            if(task.charAt(i)=='+') {
                hasAdd = true;
                divMultNumInTask = i;
                break;
            }
            else  if(task.charAt(i)=='-') {
                if(i!=0 && task.charAt(i-1)!='(' ) {
                    hasSub = true;
                    divMultNumInTask = i;
                    break;
                }
            }

        }
        if(hasAdd || hasSub){
            int prev = findPrevNum(task, divMultNumInTask);
            int next = findNextNum(task, divMultNumInTask);
            int newNum;
            String toReplace;
            if(hasSub) {
                newNum = prev-next;
                if(newNum>maxNum || newNum<minNum){
                    return "AAA";
                }
                if(prev<0&& next<0){
                    toReplace = "("+prev+")"+"-"+"("+next+")";
                }
                else if(prev<0){
                    toReplace = "("+prev+")"+"-"+next;
                }
                else if (next<0){
                    toReplace = prev+"-"+"("+next+")";
                }
                else{
                    toReplace = prev+"-"+next;
                }
            }
            else {
                newNum = prev+next;

                if(newNum>maxNum || newNum<minNum){
                    return "AAA";
                }

                if(prev<0&& next<0){
                    toReplace = "("+prev+")"+"+"+"("+next+")";
                }
                else if(prev<0){
                    toReplace = "("+prev+")"+"+"+next;
                }
                else if (next<0){
                    toReplace = prev+"+"+"("+next+")";
                }
                else{
                    toReplace = prev+"+"+next;
                }
            }



            String replacedNum = String.valueOf(newNum);
            if(newNum<0)
                replacedNum = "("+replacedNum+")";
            //task = task.replaceFirst(toReplace, replacedNum);
            task = replaceFirstStr(task, toReplace, replacedNum);
        }
        return task;
    }


    private static String solveAllMultAndDiv(String task){
        boolean work = true;
        String prev = task;
        while(work){
            task = solveDivAndMultiplication(task);
            if(task.equals("AAA")){
                return task;
            }
            if(prev.equals(task)){
                work=false;
            }
            prev = task;
        }
        return task;
    }

    private static String solveDivAndMultiplication(String task){
        int divMultNumInTask = -1;
        boolean hasMult = false;
        boolean hasDiv = false;
        for(int i=0; i<task.length(); i++){
            if(task.charAt(i)=='*') {
                hasMult = true;
                divMultNumInTask = i;
                break;
            }
            else  if(task.charAt(i)=='/') {
                hasDiv = true;
                divMultNumInTask = i;
                break;
            }

        }
        if(hasDiv || hasMult){
            int prev = findPrevNum(task, divMultNumInTask);
            int next = findNextNum(task, divMultNumInTask);
            int newNum;
            String toReplace;
            if(hasDiv) {
                newNum = prev/next;
                double newNumDouble = (double)prev/next;
                if(newNum!=newNumDouble)
                    return "AAA";
                if(newNum>maxNum || newNum<minNum){
                    return "AAA";
                }
                if(prev<0&& next<0){
                    toReplace = "("+prev+")"+"/"+"("+next+")";
                }
                else if(prev<0){
                    toReplace = "("+prev+")"+"/"+next;
                }
                else if (next<0){
                    toReplace = prev+"/"+"("+next+")";
                }
                else{
                    toReplace = prev+"/"+next;
                }
            }
            else {
                newNum = prev * next;
                if(newNum>maxNum || newNum<minNum){
                    return "AAA";
                }
                if(prev<0&& next<0){
                    toReplace = "("+prev+")"+"*"+"("+next+")";
                }
                else if(prev<0){
                    toReplace = "("+prev+")"+"*"+next;
                }
                else if (next<0){
                    toReplace = prev+"*"+"("+next+")";
                }
                else{
                    toReplace = prev+"*"+next;
                }
            }

            String replacedNum = String.valueOf(newNum);
            if(newNum<0)
                replacedNum = "("+replacedNum+")";
            task = replaceFirstStr(task, toReplace, replacedNum);
        }
        return task;
    }

    private static int findPrevNum(String s, int numD){
        String numberS = "";
        int num = numD-1;
        while(s.charAt(num)>='0'&&s.charAt(num)<='9' || s.charAt(num)=='-' || s.charAt(num)=='(' || s.charAt(num)==')'){
            if(s.charAt(num)!='(' && s.charAt(num)!=')'&& s.charAt(num)!='-' || s.charAt(num)=='-'&&s.charAt(num-1)=='(') {
                numberS = s.charAt(num) + numberS;

                num--;
            }
            else if (s.charAt(num)==')'){
                num--;
            }
            else
                break;
            if (num == -1)
                break;

        }
        return Integer.valueOf(numberS);
    }

    private static int findNextNum(String s, int numD){
        String numberS = "";
        int num = numD+1;
        while(s.charAt(num)>='0'&&s.charAt(num)<='9' || s.charAt(num)=='-' || s.charAt(num)=='(' || s.charAt(num)==')'){
            if(s.charAt(num)!='(' && s.charAt(num)!=')' && s.charAt(num)!='-' || s.charAt(num)=='-'&&s.charAt(num-1)=='(') {
                numberS = numberS + s.charAt(num);
                num++;
            }
            else if (s.charAt(num)=='(')
                num++;
            else
                break;
            if(num==s.length())
                break;
        }
        return Integer.valueOf(numberS);
    }

    private static String createStringTask(int numDoing){
        int prevNum = ((int) (Math.random() * (maxNum - minNum + 1) + minNum));
        String task;
        if(prevNum>=0)
            task = String.valueOf(prevNum);
        else
            task = "("+prevNum+")";
        boolean nextNum = false;
        boolean checkDiv = false;

        for (int i = 0; i < numDoing * 2; i++) {
            if (nextNum) {
                int next = ((int) (Math.random() * (maxNum - minNum + 1) + minNum));
                if(checkDiv){
                    while (checkDiv) {
                        if (next != 0 && prevNum % next == 0) {
                            if (next < 0)
                                task = task + "(" + next + ")";
                            else
                                task = task + next;
                            prevNum = next;
                            nextNum = false;
                            checkDiv = false;
                        } else {
                            next = ((int) (Math.random() * (maxNum - minNum + 1) + minNum));
                        }
                    }
                }
                else{
                    if (next < 0)
                        task = task + "(" + next + ")";
                    else
                        task = task + next;
                    prevNum = next;
                    nextNum = false;
                }
            }
            else {
                int d = generateDoing();
                switch(d){
                    case 0:
                        task=task+"+";
                        break;
                    case 1:
                        task = task+"-";
                        break;
                    case 2:
                        task = task+"*";
                        break;
                    case 3:
                        task = task+"/";
                        checkDiv=true;
                        break;
                }
                nextNum=true;
            }
        }
        return task;
    }

    private static int generateDoing(){
        if(addition&&subtraction&&multiplication&&division)
            return (int) (Math.random()*4);

        else if(addition&&subtraction&&multiplication)
            return (int) (Math.random()*3);

        else if (addition&&subtraction&&division){
            int k = (int) (Math.random()*3);
            if(k==2)
                k=3;
            return k;
        }

        else if(addition&&multiplication&&division){
            int k = (int) (Math.random()*3);
            if(k==1)
                k=3;
            return k;
        }

        else if(subtraction&&multiplication&&division)
            return (int) (Math.random()*3+1);

        else if(addition&&subtraction)
            return (int) (Math.random()*2);

        else if (multiplication&&division)
            return (int) (Math.random()*2+2);

        else if(subtraction&&multiplication)
            return(int)(Math.random()*2+1);

        else if(addition&&division){
            int k = (int) (Math.random()*2);
            if(k==1)
                k=3;
            return k;
        }

        else if(addition&&multiplication){
            int k = (int) (Math.random()*2);
            if(k==1)
                k=2;
            return k;
        }

        else if(subtraction&&division){
            int k = (int) (Math.random()*2+1);
            if(k==2)
                k=3;
            return k;
        }

        else if(addition)
            return 0;

        else if (subtraction)
            return 1;

        else if(multiplication)
            return 2;

        else if(division)
            return 3;

        return 4;
    }
}


