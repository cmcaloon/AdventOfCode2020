import java.util.ArrayList;
import java.util.Collections;

import functions.ReadFileLines;

public class Day5 {

    public static void main(String[] args) throws Exception {
        String filePath = "src/table/boardingManifest.txt";
        ArrayList<String> boardingList = new ArrayList<String>();
        
        boardingList = ReadFileLines.readLines(filePath);
        processBoarding(boardingList);
    }

    private static void processBoarding(ArrayList<String> boardingList) {
        int maxSeatId = 0;
        ArrayList<Integer> allSeats = new ArrayList<Integer>();
        for (int x = 0; x < boardingList.size(); x++){
            String boardingPass = boardingList.get(x);
            int seatId = findSeatId(boardingPass);
            allSeats.add(seatId);
            if (maxSeatId < seatId)
                maxSeatId = seatId;
        }
        Collections.sort(allSeats);
        for (int y = 1; y < allSeats.size(); y++){
            if (allSeats.get(y) - 1 != allSeats.get(y-1))
            {
                int missingNum = allSeats.get(y)-1;
                System.out.println("Missing Seat Id " +missingNum);
            }
            //System.out.println("Seat Id " +allSeats.get(y));
        }
        System.out.println("Max seat id is " +maxSeatId);
    }

    private static int findSeatId(String boardingPass) {
        int midRow = 64;
        int midColumn = 4;
        int rowTally = 64;
        int columnTally = 4;
        for (int y = 0; y < boardingPass.length(); y++){
            char seatBin = boardingPass.charAt(y);
            switch(seatBin) {
                case 'F':
                    if ( y == 6)
                    {
                        midRow = midRow - 1;
                    }
                    else{
                    rowTally = rowTally / 2;
                    midRow = midRow - rowTally;
                    }
                    //System.out.println("Front new point "+midRow);
                    break;
                case 'B':
                    rowTally = rowTally / 2;
                    midRow = midRow + rowTally;
                    //System.out.println("Back new point "+midRow);
                    break;
                case 'L':
                    if (y == 9)
                    {
                       midColumn = midColumn - 1; 
                    }
                    else{
                    columnTally = columnTally / 2;
                    midColumn = midColumn - columnTally;
                    }
                    //System.out.println("Left new point "+midColumn);
                    
                    break;
                case 'R':
                    columnTally = columnTally / 2;
                    midColumn = midColumn + columnTally;
                    //System.out.println("Right new point "+midColumn);
                    break;
                default:
                    System.out.println("Invalid Input");
              }
        }
        int seatId = (midRow * 8) + midColumn;
        return seatId;
    }
    
}
