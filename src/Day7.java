import java.util.ArrayList;
import functions.ReadFileLines;

public class Day7 {
    public static void main(String[] args) throws Exception {
        String filePath = "src/table/bags.txt";
        ArrayList<String> bags = new ArrayList<String>();
        
        bags = ReadFileLines.readLines(filePath);
        long startTime = System.nanoTime();
        parseBags(bags);
        long endTime   = System.nanoTime();
        ReadFileLines.findTime(startTime, endTime);
    }

    private static void parseBags(ArrayList<String> bags) {
        String[] bagString;
        ArrayList<ArrayList<String>> masterBags = new ArrayList<>();
        ArrayList<String> parentBags = new ArrayList<String>();
        ArrayList<ArrayList<Integer>> totalBagCount = new ArrayList<>();
        for (int x = 0; x < bags.size(); x++){
            String bagList = bags.get(x);
            bagString = bagList.split(" contain ");
            for (int y = 1; y < bagString.length; y++){ 
                parentBags.add(bagString[0]);
                masterBags.add(new ArrayList<>());
                totalBagCount.add(new ArrayList<>());
                if (bagString[1].contains(",")){
                    String[] subBags;
                    subBags = bagString[y].split(", ");
                    for (int z = 0; z < subBags.length; z++)
                    { 
                        subBags[z] = subBags[z].replaceAll("[.]","");
                        masterBags.get(x).add(subBags[z]);
                    }
                }
                else {
                    bagString[1] = bagString[1].replaceAll("[.]","");
                    masterBags.get(x).add(bagString[1]);
                }
            }
        }
        //parentBags = Each bag in the order they appear in the txt file
        //master bags = # and name of each bag Linked index to parent
        //Total bag count = # of each bag found Starting at 0

        int goldIndex = findBagIndex(parentBags, "shiny gold bags");
        //for (int i = 0; i < parentBags.size(); i++){
        sortBagCount(parentBags, masterBags, totalBagCount, goldIndex, 1, 0);
        //sortBagCount(parentBags, masterBags, totalBagCount, i, 1, i);
    //}
        System.out.println("Shiney Gold bag count = " +totalBagCount);
        System.out.println("Shiney Gold bag count = " +totalBagCount.get(goldIndex).size());
        //removeDups(totalBagCount.get(goldIndex));
        int grandTotal = 0;
        for (int i = 0; i < totalBagCount.size(); i++){
            for (int j = 0; j < totalBagCount.get(i).size(); j++){
                grandTotal = grandTotal + totalBagCount.get(i).get(j);
            }
        }
        System.out.println("Grand Total is " +grandTotal);
}

private static ArrayList<ArrayList<Integer>> sortBagCount(ArrayList<String> parentBags,
    ArrayList<ArrayList<String>> masterBags, 
    ArrayList<ArrayList<Integer>> totalBagCount, int index, int multi, int pare) {
        for(int i = 0; i < masterBags.get(index).size(); i++){
            if ((masterBags.get(index).get(i)).equals("no other bags")){
                return totalBagCount;
            }
            else {
                String bagName = masterBags.get(index).get(i).substring(2);
                int bagIndex = findBagIndex(parentBags, bagName);
                String numOfBags = String.valueOf(masterBags.get(index).get(i).charAt(0));
                int bagCount = Integer.parseInt(numOfBags) * multi;
                totalBagCount.get(bagIndex).add(bagCount);
                //totalBagCount.get(bagIndex).add(pare);
                //System.out.println("Adding " +bagCount+ " to the bag " +bagName);
                sortBagCount(parentBags, masterBags, totalBagCount, bagIndex, bagCount, pare);
            }
        }
        return totalBagCount;

    }

private static int findBagIndex(ArrayList<String> parentBags, String bagName) {
    int bagIndex = 0;
    String bagsName = bagName + "s";
    for (int i = 0; i < parentBags.size(); i++){
        if (bagName.equals(parentBags.get(i)) || bagsName.equals(parentBags.get(i))){
            //System.out.println("Here with " +bagName);
            bagIndex = i;
    }
    }
    //System.out.println("Index in " +bagIndex);
    return bagIndex;
}

private static void removeDups(ArrayList<Integer> totalBagCount){
    ArrayList<Integer> test = new ArrayList<Integer>();
        test.add(totalBagCount.get(0));
        for (int j = 0; j < totalBagCount.size(); j++){
            int testSize = test.size() - 1;
            if (totalBagCount.get(j).equals(test.get(testSize))){

            }
            else test.add(totalBagCount.get(j));
        }
        System.out.println("Shiney Gold bag count = " +test.size());
}
}