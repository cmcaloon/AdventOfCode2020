import java.util.ArrayList;

import functions.ReadFileLines;

public class Day8 {
    public static void main(String[] args) throws Exception {
        String filePath = "src/table/videoGameInstructions.txt";
        ArrayList<String> gameInstructions = new ArrayList<String>();
        gameInstructions = ReadFileLines.readLines(filePath);
        long startTime = System.nanoTime();
        for (int x = 0; x < gameInstructions.size(); x++){
            Boolean end = false;
            if (gameInstructions.get(x).contains("jmp")){
                gameInstructions.set(x, gameInstructions.get(x).replace("jmp", "nop"));
                end = readInstructions(gameInstructions);
                gameInstructions.set(x, gameInstructions.get(x).replace("nop", "jmp"));
            }
            else if (gameInstructions.get(x).contains("nop")){
                gameInstructions.set(x, gameInstructions.get(x).replace("nop", "jmp"));
                end = readInstructions(gameInstructions);
                gameInstructions.set(x, gameInstructions.get(x).replace("jmp", "nop"));
            }
            if (end == true){
                break;
            }
        }
        long endTime   = System.nanoTime();
        ReadFileLines.findTime(startTime, endTime);
    }

    private static Boolean readInstructions(ArrayList<String> gameInstructions){
        int accumulator = 0;
        Boolean[] repeat = new Boolean[gameInstructions.size()];
        for (int x = 0; x < gameInstructions.size(); x++){
            repeat[x] = false;
        }

        for (int i = 0; i < gameInstructions.size(); i++){
            //System.out.println("Execution instruction position " +i+ " For Instruction "+gameInstructions.get(i)+" Boolen is "+repeat[i]);
            int stringMid;
            Boolean pos = true;
            if (repeat[i] == true)
            return false;

            if (gameInstructions.get(i).contains("+")){
                stringMid = gameInstructions.get(i).indexOf("+");
            }
            else {
                stringMid = gameInstructions.get(i).indexOf("-");
                pos = false;
            }
            
            if (gameInstructions.get(i).contains("jmp")){
                repeat[i] = true;
                String jumpIns = gameInstructions.get(i).substring(stringMid+1);
                i = executeInstruction(pos, jumpIns, i);
                i = i -1;
            }
            else if (gameInstructions.get(i).contains("acc")){
                String accumulate = gameInstructions.get(i).substring(stringMid+1);
                repeat[i] = true;
                accumulator = executeInstruction(pos, accumulate, accumulator);
            }
            else repeat[i] = true;
        }
        System.out.println("Accumulator Final " +accumulator);
        return true;
    }

    private static int executeInstruction(Boolean pos, String jumpIns, int i) {
        int interval = Integer.parseInt(jumpIns);
        if (pos == true){
            i = i + interval;
        }
        else i = i - interval;
        return i;
    }
}
