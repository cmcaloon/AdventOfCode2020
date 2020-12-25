import java.util.ArrayList;
import java.util.Collections;

import functions.ReadFileLines;

public class Day10 {
    public static void main(String[] args) throws Exception {
        String filePath = "src/table/adapters.txt";
        ArrayList<String> adapters = new ArrayList<String>();
        adapters = ReadFileLines.readLines(filePath);
        long startTime = System.nanoTime();
        ArrayList<Integer> adaptersNum = assignLong(adapters);
        checkAdapters(adaptersNum);
        long endTime   = System.nanoTime();
        ReadFileLines.findTime(startTime, endTime);

        startTime = System.nanoTime();
        checkAdaptersCombo(adaptersNum);
        endTime   = System.nanoTime();
        ReadFileLines.findTime(startTime, endTime);
        
    }

    public static void checkAdapters(ArrayList<Integer> adapters) {
        Collections.sort(adapters);
        int count1 = 0;
        int count3 = 0;
        for (int i = 1; i < adapters.size(); i++){
            int difference =  adapters.get(i) - adapters.get(i-1);
            //System.out.println( +adapters.get(i)+ " Minus " +adapters.get(i-1)+ " equals " +difference);

            if (difference == 1){
                count1++;
            }
            else if (difference == 3){
                count3++;
            }
            else System.out.println("Annomoly");
        }
        count3++;
        int answer = count1 * count3;
        System.out.println("Output Answer " +answer);

    }

    public static void checkAdaptersCombo(ArrayList<Integer> adapters) {
        Collections.sort(adapters);
        System.out.println(adapters);
        ArrayList<Integer> mixedCount = new ArrayList<>();
        long total = 1;
        for (int i = 0; i < adapters.size(); i++){
            long options = 0;
            mixedCount.add(adapters.get(i)); 
            if (i + 1 < adapters.size()){  
                if (adapters.get(i) + 3 == adapters.get(i+1)){
                    System.out.println("No more options here");
                    System.out.println(mixedCount);
                    if (mixedCount.size() == 2)
                    total = total * 1;
                    else if (mixedCount.size() == 3)
                    total = total * 2;
                    else if (mixedCount.size() == 4)
                    total = total * 4;
                    else if (mixedCount.size() == 5)
                    total = total * 7;
                    
                    mixedCount.clear();
                }
            } else System.out.println(mixedCount);
        }

        System.out.println("Output Answer " +total);
    }
        
    


    public static ArrayList<Integer> assignLong(ArrayList<String> adapters){
    ArrayList<Integer> adaptersNum = new ArrayList<Integer>();
    for (int x = 0; x < adapters.size(); x++){
        adaptersNum.add(Integer.parseInt(adapters.get(x)));
    }
    return adaptersNum;
}
}
