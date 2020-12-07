import java.util.ArrayList;

import functions.ReadFileLines;

public class Day6 {
    public static void main(String[] args) throws Exception {
        String filePath = "src/table/customsForms.txt";
        ArrayList<String> customForms = new ArrayList<String>();
        
        customForms = ReadFileLines.readLines(filePath);
        long startTime = System.nanoTime();
        countYes(customForms);
        long endTime   = System.nanoTime();
        ReadFileLines.findTime(startTime, endTime);
    }

    private static void countYes(ArrayList<String> customForms) {
        ArrayList<Integer> count = new ArrayList<Integer>();
        for (int x = 0; x < customForms.size(); x++){
            ArrayList<String> groupForms = new ArrayList<String>();
            String currentGroup = customForms.get(x);
            for(int y = 0;currentGroup.isEmpty() == false; y++){
                groupForms.add(y, currentGroup);
                x++;
                if (x < customForms.size()){
                    currentGroup = customForms.get(x);
                }
                else currentGroup = "";
            }
            //count.add(checkGroupForm(groupForms));
            count.add(checkGroupForm2(groupForms));

        }
        addResult(count);
    }

    private static void addResult(ArrayList<Integer> count) {
        int total = 0;
        for (int x = 0; x < count.size(); x++){
            total = total + count.get(x);
        }
        System.out.println("Final count is " +total);
    }

    private static int checkGroupForm(ArrayList<String> groupForms) {
        ArrayList<Character> yesAnswers = new ArrayList<Character>();
        for (int x = 0; x < groupForms.size(); x++){
            for (int y = 0; y < groupForms.get(x).length(); y++){
                Boolean addChar = isDuplicate(groupForms.get(x).charAt(y), yesAnswers);
                if (addChar == false){
                    yesAnswers.add(groupForms.get(x).charAt(y));
                }
            }
        }
        int totalCount = yesAnswers.size();
        return totalCount;
    }

    private static int checkGroupForm2(ArrayList<String> groupForms) {
        //messy messy...
        ArrayList<Character> yesAnswers = new ArrayList<Character>();
        ArrayList<Character> parseAnswers = new ArrayList<Character>();
        ArrayList<Character> reParseAnswers = new ArrayList<Character>();
        ArrayList<Character> reParseAnswers2 = new ArrayList<Character>();
        ArrayList<Character> reParseAnswers3 = new ArrayList<Character>();
        ArrayList<Character> reParseAnswers4 = new ArrayList<Character>();
        ArrayList<Character> noAnswers = new ArrayList<Character>();
        
        for (int x = 0; x < groupForms.size(); x++){
           
            for (int y = 0; y < groupForms.get(x).length(); y++){
                if (x == 2){
                    reParseAnswers2.add(groupForms.get(x).charAt(y));
                }
                if (x == 3){
                    reParseAnswers3.add(groupForms.get(x).charAt(y));
                }
                if (x == 4){
                    reParseAnswers4.add(groupForms.get(x).charAt(y));
                }
                if (groupForms.size() == 1){
                    yesAnswers.add(groupForms.get(x).charAt(y));
                }
                else if (x == 0) {
                    reParseAnswers.add(groupForms.get(x).charAt(y));
                }
                else if (x == 1){
                    Boolean addChar = isDuplicate(groupForms.get(x).charAt(y), reParseAnswers);
                    if (addChar){
                        parseAnswers.add(groupForms.get(x).charAt(y));
                    }
                    else if(addChar == false){
                        noAnswers.add(groupForms.get(x).charAt(y));
                    }
                }
                else {
                    Boolean addChar = isDuplicate(groupForms.get(x).charAt(y), parseAnswers);
                    if (addChar == false){
                        noAnswers.add(groupForms.get(x).charAt(y));
                    }
                }
            }
        }
        if (groupForms.size() > 2){
            for (int i = 0; i < groupForms.get(0).length(); i++){
                Boolean addChar = isDuplicate(groupForms.get(0).charAt(i), reParseAnswers2);
                if(addChar == false){
                    noAnswers.add(groupForms.get(0).charAt(i));
                }
            }
        }
        if (groupForms.size() > 3){
            for (int i = 0; i < groupForms.get(0).length(); i++){
                Boolean addChar = isDuplicate(groupForms.get(0).charAt(i), reParseAnswers3);
                if(addChar == false){
                    noAnswers.add(groupForms.get(0).charAt(i));
                }
            }
        }
        for (int z = 0; z < parseAnswers.size(); z++)
        {
            Boolean testChar = isDuplicate(parseAnswers.get(z), noAnswers);
            if (testChar == false){
                yesAnswers.add(parseAnswers.get(z));
            }
        }
        int totalCount = yesAnswers.size();
        return totalCount;
    }

    private static Boolean isDuplicate(char charAt, ArrayList<Character> yesAnswers) {
        for (int x = 0; x < yesAnswers.size(); x++){
            if (charAt == yesAnswers.get(x)){
                return true;
            }
        }
        return false;
    }

}
