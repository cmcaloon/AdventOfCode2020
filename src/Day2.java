import java.util.ArrayList;
import fuctions.ReadFileLines;

public class Day2 {
    public static void main(String[] args) throws Exception {
        String filePath = "src/table/passwordFile.txt";
        ArrayList<String> passwords = new ArrayList<String>();

        passwords = ReadFileLines.readLines(filePath);
        sledPasswordVerification(passwords);
        tobogganPasswordVerification(passwords);

    }

    // Read string and find that criteria listed before the : is met
    private static void sledPasswordVerification(ArrayList<String> passwords) {
        int criteria = passwords.size() - 1;
        int validPasswords = 0;
        for (int x = 0; x <= criteria; x++){
            String passVal = passwords.get(x);
            int dash = passVal.indexOf("-");
            int midpoint = passVal.indexOf(":");
            int midpoint2 = passVal.indexOf(" ");
            int lowRange = Integer.parseInt(passVal.substring(0, dash));
            int highRange = Integer.parseInt(passVal.substring(dash+1, midpoint2));
            char passKey = passVal.charAt(midpoint - 1);
            String password = passVal.substring(midpoint+1);

            int count = 0;

            for (int y = 0; y < password.length(); y++) {
                char current = password.charAt(y);
                if (current == passKey){
                    count++;
                }
            }
            if (count >= lowRange && count <= highRange){
                validPasswords++;
            }
        }
        System.out.println("The number of valid Sled passwords are "+validPasswords);
    }

    private static void tobogganPasswordVerification(ArrayList<String> passwords) {
        int criteria = passwords.size() - 1;
        int validPasswords = 0;
        for (int x = 0; x <= criteria; x++){
            Boolean valid = false;
            String passVal = passwords.get(x);
            int dash = passVal.indexOf("-");
            int midpoint = passVal.indexOf(":");
            int midpoint2 = passVal.indexOf(" ");
            int lowRange = Integer.parseInt(passVal.substring(0, dash));
            int highRange = Integer.parseInt(passVal.substring(dash+1, midpoint2));
            char passKey = passVal.charAt(midpoint - 1);
            String password = passVal.substring(midpoint+1);

            for (int y = 0; y < password.length(); y++) {
                char current = password.charAt(y);
                if (y == lowRange && current == passKey){
                    valid = true;
                }
                else if (y == highRange && current == passKey && valid == true){
                    valid = false;
                }
                else if (y == highRange && current == passKey){
                    valid = true;
                }
            }
            if (valid){
                validPasswords++;
            }
        }
        System.out.println("The number of valid Toboggan passwords are "+validPasswords);
    }

    // TBD - Split string by : character
}
