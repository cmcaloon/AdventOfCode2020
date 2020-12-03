import java.util.ArrayList;
import functions.ReadFileLines;

public class Day3 {

    public static void main(String[] args) throws Exception {
        String filePath = "src/table/treeMap.txt";
        ArrayList<String> tobPath = new ArrayList<String>();
        
        tobPath = ReadFileLines.readLines(filePath);
        int routeA = tobogganPaths(tobPath, 1, 1);
        int routeB = tobogganPaths(tobPath, 1, 3);
        int routeC = tobogganPaths(tobPath, 1, 5);
        int routeD = tobogganPaths(tobPath, 1, 7);
        int routeE = tobogganPaths(tobPath, 2, 1);

        long multi = routeA * routeB;
        multi = multi * routeC;
        multi = multi * routeD;
        multi = multi * routeE;
        System.out.println("Route A is "+routeA);
        System.out.println("Route b is "+routeB);
        System.out.println("Route C is "+routeC);
        System.out.println("Route D is "+routeD);
        System.out.println("Route E is "+routeE);

        System.out.println("All Results Multiplied is "+multi);
    }

   private static int tobogganPaths(ArrayList<String> tobPath, int down, int right){
        int distance = tobPath.size() - 1;
        int x = 0;
        int count = 0;
        for (int y = down; y <= distance; y = y + down){
            String currentPath = tobPath.get(y);
            char treeCheck;
            x = x + right;
            while (x > currentPath.length() - 1)
            {
                currentPath = expandPath(currentPath);
            }
            treeCheck = currentPath.charAt(x);
            if (treeCheck == '#'){
                count++;
            }
            
        }
        return count;  
    }

    private static String expandPath(String string){
        String newString = string+string;
        return newString;
    }
}
