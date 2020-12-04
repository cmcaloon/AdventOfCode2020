import java.util.ArrayList;
import functions.ReadFileLines;

public class Day4 {
	public static void main(String[] args) throws Exception {
        String filePath = "src/table/passports.txt";
        ArrayList<String> passportList = new ArrayList<String>();
        
        passportList = ReadFileLines.readLines(filePath);
        parsePassport(passportList);
    }

    private static void parsePassport(ArrayList<String> passportList){
        int totalValidPassports = 0;
        for (int x = 0; x < passportList.size() -1; x++){
            ArrayList<String> currentPassport = new ArrayList<String>();
            String currentLine = passportList.get(x);

            for(int y = 0;currentLine.isEmpty() == false; y++){
                currentPassport.add(y, currentLine);
                x++;
                if (x <= passportList.size()){
                    currentLine = passportList.get(x);
                }
                else currentLine = "";
            }
            if (validatePassport(currentPassport)){
                totalValidPassports++;
            }
        }
        System.out.println("the number of total Valid passports are " +totalValidPassports);
    }

    private static boolean validateChars(String color, Boolean isId) {
        Boolean valid = false;
        for (int x = 1; x < color.length(); x++)
        {
            if (color.charAt(x) == '0' || color.charAt(x) == '1' || color.charAt(x) == '2' ||
            color.charAt(x) == '3' || color.charAt(x) == '4' || color.charAt(x) == '5' ||
            color.charAt(x) == '6' || color.charAt(x) == '7' || color.charAt(x) == '8' ||
            color.charAt(x) == '9'){
                valid = true;
            }
            else if (isId == false && (color.charAt(x) == 'a' || color.charAt(x) == 'b' ||
            color.charAt(x) == 'c' || color.charAt(x) == 'd' || color.charAt(x) == 'e' ||
            color.charAt(x) == 'f')){
                valid = true;
            }
            else {
                valid = false;
                break;
            }
        }
        return valid;
    }

    private static boolean validatePassport(ArrayList<String> currentPassport) {
        int passportSize = currentPassport.size() - 1;
        Boolean checkBirth = false;
        Boolean checkIssue = false;
        Boolean checkExpiration = false;
        Boolean checkHeight = false;
        Boolean checkHair = false;
        Boolean checkEye = false;
        Boolean checkPassId = false;

        for (int x = 0; x <= passportSize; x++){
            String passportLine = currentPassport.get(x);
            String[] passportElements = passportLine.split(" ");
            for (int y = 0; y< passportElements.length; y++)
            {
                int begin = passportElements[y].indexOf(":")+1;
                if (passportElements[y].contains("byr")){
                    String birthYear = passportElements[y].substring(begin, passportElements[y].length());
                    if (Integer.parseInt(birthYear) >= 1920 && Integer.parseInt(birthYear) <= 2002)
                        checkBirth = true;
                }
                if (passportElements[y].contains("iyr")){
                    String issueYear = passportElements[y].substring(begin, passportElements[y].length());
                    if (Integer.parseInt(issueYear) >= 2010 && Integer.parseInt(issueYear) <= 2020)
                        checkIssue = true;
                }
                if (passportElements[y].contains("eyr")){
                    String expYear = passportElements[y].substring(begin, passportElements[y].length());
                    if (Integer.parseInt(expYear) >= 2020 && Integer.parseInt(expYear) <= 2030)
                        checkExpiration = true;
                }
                if (passportElements[y].contains("hgt")){
                    String unit = passportElements[y].substring(passportElements[y].length() - 2, passportElements[y].length());
                    String height = passportElements[y].substring(begin, passportElements[y].length() - 2);
                    if (unit.contains("cm")){
                        if (Integer.parseInt(height) >= 150 && Integer.parseInt(height) <= 193){
                            checkHeight = true;
                        }
                    }
                    else if (unit.contains("in")){
                        if (Integer.parseInt(height) >= 59 && Integer.parseInt(height) <= 76){
                            checkHeight = true;
                        }
                    }
                }
                if (passportElements[y].contains("hcl")){
                    String hairColor = passportElements[y].substring(begin, passportElements[y].length());
                    if (passportElements[y].charAt(begin) == '#' && hairColor.length() == 7){
                        if (validateChars(hairColor, false)){
                            checkHair = true;
                        }
                        
                    }
                }
                if (passportElements[y].contains("ecl")){
                    String eyeColor = passportElements[y].substring(begin, passportElements[y].length());
                    if ((eyeColor.contains("amb") || eyeColor.contains("blu") || eyeColor.contains("brn") ||
                    eyeColor.contains("gry") || eyeColor.contains("grn") || eyeColor.contains("hzl") ||
                    eyeColor.contains("oth")) && eyeColor.length() < 4){
                        checkEye = true;
                    }
                }
                if (passportElements[y].contains("pid")){
                    String passportId = passportElements[y].substring(begin, passportElements[y].length());
                    if (validateChars(passportId, true) && passportId.length() == 9){
                        checkPassId = true;
                    }
                }
            }
        }
        if (checkBirth && checkIssue && checkExpiration && checkHeight && checkHair && checkEye && checkPassId)
        {
            return true;
        }
        else return false;
    }

}
