import java.util.ArrayList;

import functions.ReadFileLines;

public class Day11 {
    public static void main(String[] args) throws Exception {
        String filePath = "src/table/waitingSeats.txt";
        ArrayList<String> seats = new ArrayList<String>();
        seats = ReadFileLines.readLines(filePath);
        long startTime = System.nanoTime();
        ArrayList<ArrayList<Character>> eachSeat = new ArrayList<>();
        eachSeat = individualizeSeats(seats);
        seatShuffle(eachSeat, 1, false);
        long endTime   = System.nanoTime();
        ReadFileLines.findTime(startTime, endTime);
        
    }

    private static void seatShuffle(ArrayList<ArrayList<Character>> seats, int l, Boolean hasChanged) {
        //Evaluate Each cell as it is
        //Add either update or Same to new Array list
        //Clear the first list and rerun the steps with the second
        ArrayList<ArrayList<Character>> seatsAdjusted = new ArrayList<>();
        hasChanged = false;
        for (int y = 0; y < seats.size(); y++){
            seatsAdjusted.add(new ArrayList<>());
            for (int x = 0; x < seats.get(y).size(); x++){
                if (seats.get(y).get(x) == '.'){
                    seatsAdjusted.get(y).add('.');
                }
                else if (seats.get(y).get(x) == 'L'){
                    Boolean hasAdjacent = false;
                    //Check Upper
                    if(y > 0){
                        if (seats.get(y-1).get(x) == '#'){
                            hasAdjacent = true;
                        }
                    }
                    //Check Lower
                    if(y < seats.size()-1){
                        if (seats.get(y+1).get(x) == '#'){
                            hasAdjacent = true;
                        }
                    }
                    //Check Left
                    if (x > 0){
                        if (seats.get(y).get(x-1) == '#'){
                            hasAdjacent = true;
                        }
                    }
                    //Check Right
                    if (x < seats.get(y).size()-1){
                        if (seats.get(y).get(x+1) == '#'){
                            hasAdjacent = true;
                        }
                    }
                    //Check corners
                    if (x < seats.get(y).size()-1 && y < seats.size()-1){
                        if (seats.get(y+1).get(x+1) == '#'){
                            hasAdjacent = true;
                        }
                    }
                    if (x < seats.get(y).size()-1 && y > 0){
                        if (seats.get(y-1).get(x+1) == '#'){
                            hasAdjacent = true;
                        }
                    }
                    if (x > 0 && y < seats.size()-1){
                        if (seats.get(y+1).get(x-1) == '#'){
                            hasAdjacent = true;
                        }
                    }
                    if (x > 0 && y > 0){
                        if (seats.get(y-1).get(x-1) == '#'){
                            hasAdjacent = true;
                        }
                    }
                    if (hasAdjacent)
                    seatsAdjusted.get(y).add('L');
                    else {
                        seatsAdjusted.get(y).add('#');
                        hasChanged = true;
                    }

                }
                if (seats.get(y).get(x) == '#'){
                    int adjacentCnt = 0;
                    //Check Upper
                    if(y > 0){
                        if (seats.get(y-1).get(x) == '#'){
                            adjacentCnt++;
                        }
                    }
                    //Check Lower
                    if(y < seats.size()-1){
                        if (seats.get(y+1).get(x) == '#'){
                            adjacentCnt++;
                        }
                    }
                    //Check Left
                    if (x > 0){
                        if (seats.get(y).get(x-1) == '#'){
                            adjacentCnt++;
                        }
                    }
                    //Check Right
                    if (x < seats.get(y).size()-1){
                        if (seats.get(y).get(x+1) == '#'){
                            adjacentCnt++;
                        }
                    }
                    if (x < seats.get(y).size()-1 && y < seats.size()-1){
                        if (seats.get(y+1).get(x+1) == '#'){
                            adjacentCnt++;
                        }
                    }
                    if (x < seats.get(y).size()-1 && y > 0){
                        if (seats.get(y-1).get(x+1) == '#'){
                            adjacentCnt++;
                        }
                    }
                    if (x > 0 && y < seats.size()-1){
                        if (seats.get(y+1).get(x-1) == '#'){
                            adjacentCnt++;
                        }
                    }
                    if (x > 0 && y > 0){
                        if (seats.get(y-1).get(x-1) == '#'){
                            adjacentCnt++;
                        }
                    }
                    if (adjacentCnt >= 4){
                    seatsAdjusted.get(y).add('L');
                    hasChanged = true;
                    }
                    else seatsAdjusted.get(y).add('#');
                }
            }
        }
        
        
        if (hasChanged){
            System.out.println("loop # " +l);
            l++;
            seatShuffle(seatsAdjusted, l, hasChanged);
        }
        else{
            int totalCnt = 0; 
            for (int i = 0; i < seatsAdjusted.size(); i++){
                for (int j = 0; j < seatsAdjusted.get(i).size(); j++){
                    if (seatsAdjusted.get(i).get(j) == '#')
                    totalCnt++;
                }
            }
            System.out.println("Total seats occupied is " +totalCnt);
        }
    }
        

    private static ArrayList<ArrayList<Character>> individualizeSeats(ArrayList<String> seats) {
        ArrayList<ArrayList<Character>> eachSeat = new ArrayList<>();
        for (int i = 0; i < seats.size(); i++){
            eachSeat.add(new ArrayList<>());
            for (int j = 0; j < seats.get(i).length(); j++){
                eachSeat.get(i).add(seats.get(i).charAt(j));
            }
        }
        return eachSeat;

        
    }

}