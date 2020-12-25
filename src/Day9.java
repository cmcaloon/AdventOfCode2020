import java.util.ArrayList;
import java.util.Collections;

import functions.ReadFileLines;

public class Day9 {
    public static void main(String[] args) throws Exception {
        String filePath = "src/table/xmasCypher.txt";
        ArrayList<String> xmasCode = new ArrayList<String>();
        xmasCode = ReadFileLines.readLines(filePath);
        long startTime = System.nanoTime();
        long firstInvalid = findFirstFail(xmasCode);
        crackInvalid(firstInvalid, xmasCode);
        long endTime   = System.nanoTime();
        ReadFileLines.findTime(startTime, endTime);
    }

    private static Long findFirstFail(ArrayList<String> xmasCode) {
        Boolean notValid = false;
        for (int i = 25; i < xmasCode.size(); i++)
        {
            Long curNum = Long.parseLong(xmasCode.get(i));
            notValid = isValid(curNum, xmasCode, i);
            if (notValid == false){
            System.out.println("The number " +curNum+ " does not match");
            return curNum;
            }
        }
        long fail = 0;
        return fail;
    }

    private static Boolean isValid(Long curNum, ArrayList<String> xmasCode, int i) {
        ArrayList<Long> xmasInt = new ArrayList<Long>();
        for (int x = 0; x <= 25; x++){
            //int test = i-x;
            //System.out.println("i - x is " +test+ " which is at " +xmasCode.get(i-x));
            xmasInt.add(Long.parseLong(xmasCode.get(i-x)));
        }
        for (int y = 0; y < xmasInt.size(); y++)
        {
            for (int z = y+1; z < xmasInt.size(); z++){
                if (xmasInt.get(y) + xmasInt.get(z) == curNum)
                return true;
            }
        }
        return false;
        
    }

    private static void crackInvalid(long curNum, ArrayList<String> xmasCode) {
        ArrayList<Long> xmasInt = new ArrayList<Long>();
        ArrayList<Long> candidates = new ArrayList<Long>();
        long tally;
        for (int x = 0; x < xmasCode.size(); x++){
            if (Long.parseLong(xmasCode.get(x)) < curNum)
            xmasInt.add(Long.parseLong(xmasCode.get(x)));
        }
        for (int y = 0; y < xmasInt.size(); y++)
        {
            tally = (long) 0;
            int z = y;
            //System.out.println("z of " +z+ " should equal " +y+ "Tally reset to " +tally );
            while(tally < curNum && z < xmasInt.size()){
                //System.out.println("Adding " +tally+ " to " +xmasInt.get(z)+ "After intervals "+z);
                tally = tally + xmasInt.get(z);
                z++;
                //System.out.println(tally+ " Comparing to " +curNum);
            }
            //System.out.println("Tallying " +tally+ " Comparing to " +curNum+ "After intervals "+z);
            if (tally == curNum){
                System.out.println("Triggered "+z);
                for (int k = y; k < z; k++) {
                    candidates.add(xmasInt.get(k));
                }
                System.out.println(candidates);
                //candidates.add((long)0);
            }
            z = 0;
        }
        Collections.sort(candidates);
        long encryption = candidates.get(0) + (candidates.get(candidates.size()-1));
        System.out.println("Printing out " +candidates.get(0)+ " , " +candidates.get(candidates.size()-1)) ;
        System.out.println(encryption);
        
    }
}
