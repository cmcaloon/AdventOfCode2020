import java.util.ArrayList;

import functions.ReadFileLines;

public class Day12 {
    public static void main(String[] args) throws Exception {
        String filePath = "src/table/directions.txt";
        ArrayList<String> directions = new ArrayList<String>();
        directions = ReadFileLines.readLines(filePath);
        long startTime = System.nanoTime();
        navigateShip(directions);
        long endTime   = System.nanoTime();
        ReadFileLines.findTime(startTime, endTime);
        
    }

    private static void navigateShip(ArrayList<String> directions) {
        ArrayList<String> compass = new ArrayList<String>();
        ArrayList<String> movement = new ArrayList<String>();
        for (int i = 0; i < directions.size(); i++){
            compass.add(directions.get(i).substring(0, 1));
            movement.add(directions.get(i).substring(1));
        }
        int EastWest = 0;
        int NorthSouth = 0;

        char F = 'E';
        for (int j = 0; j < directions.size();j++){
            int change = Integer.parseInt(movement.get(j));
            switch(compass.get(j).charAt(0)){
                case 'F':
                if (F == 'E')
                    EastWest = EastWest + change;
                else if (F == 'W')
                    EastWest = EastWest - change;
                else if (F == 'S')
                    NorthSouth = NorthSouth - change;
                else if (F == 'N')
                    NorthSouth = NorthSouth + change;
                break;

                case 'E':
                EastWest = EastWest + change;
                break;

                case 'W':
                EastWest = EastWest - change;
                break;

                case 'S':
                NorthSouth = NorthSouth - change;
                break;

                case 'N':
                NorthSouth = NorthSouth + change;
                break;

                case 'L':
                F = rotate('L', F, movement.get(j));
                break;

                case 'R':
                F = rotate('R', F, movement.get(j));
                break;

                default: System.out.println("No valid character found");
            }

            System.out.println("EastWest direction is " +EastWest);
        }

        int total = 0; 
        total = Math.abs(EastWest) + Math.abs(NorthSouth);
        System.out.println("Total after directions is " +total);
        
    }

    private static char rotate(char LR, char Face, String movement) {
        char[] cardinals = {'E', 'S', 'W', 'N'};
        int start = 0;

        if (Face == 'S')
        start = 1;
        else if (Face == 'W')
        start = 2; 
        else if (Face == 'N')
        start = 3; 

        int end = start;

        int rotate = Integer.parseInt(movement) / 90;

        for (int i = 0; i < rotate; i++){
            if (LR == 'L'){
                end--;
                if (end == -1)
                end = 3;
            }
            else if (LR == 'R'){
            end++;
            if (end == 4)
            end = 0;
            }
        }
        return(cardinals[end]);
    }
}
