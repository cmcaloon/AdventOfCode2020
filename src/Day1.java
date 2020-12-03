import java.util.Arrays;
import table.listOfNum;

public class Day1 {
        static void find2020() {
            Arrays.sort(listOfNum.taxList);
            int start = 0;
            int last = listOfNum.taxList.length - 1;
            int total = 0;
            int sum;
    
            for (int x = 0; total != 2020; x++){
                for (int y = 1; y <= last; y++){
                    total = listOfNum.taxList[x] + listOfNum.taxList[y];
                    if (total == 2020) {
                        start = x;
                        last = y;
                        break;
                    }
                    else if (total > 2020)
                        y = last;
                }
                System.out.println("Loop " + x);
            }
    
            sum = listOfNum.taxList[start] * listOfNum.taxList[last];
            System.out.println("Final answer is " + sum);
        }

        static void find2020Trio() {
            Arrays.sort(listOfNum.taxList);
            int first = 0;
            int second = 50;
            int last = listOfNum.taxList.length - 1;
            int total = 0;
            int sum;
    
            for (int x = 0; total != 2020; x++){
                for (int y = 1; y <= last; y++){
                    for (int z = 2; z <= last; z++){
                        total = listOfNum.taxList[x] + listOfNum.taxList[y] + listOfNum.taxList[z];
                        if (total == 2020) {
                            first = x;
                            second = y;
                            last = z;
                            break;
                        }
                        else if (total > 2020)
                            z = last;
                    }
                       
                }
            }
    
            sum = listOfNum.taxList[first] * listOfNum.taxList[last] * listOfNum.taxList[second];
            System.out.println("Final answer is " + sum);
        }
    
    public static void main(String[] args) throws Exception {
        long startTime = System.nanoTime(); 
        find2020();
        long endTime   = System.nanoTime();
        long totalTime = endTime - startTime;
        totalTime = totalTime / 1000;
        System.out.println("First Time = " +totalTime);
        startTime = System.nanoTime(); 
        find2020Trio();
        endTime = System.nanoTime();
        totalTime = endTime - startTime;
        totalTime = totalTime / 1000;
        System.out.println("Second Time = " +totalTime);
    }
}
