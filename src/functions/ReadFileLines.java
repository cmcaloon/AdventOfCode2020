package functions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReadFileLines {
    public static ArrayList<String> readLines(String filePath) throws IOException 
    {
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        ArrayList<String> readLines = new ArrayList<String>();
        
        try {
            String line;
            while ((line = br.readLine()) != null) {
                readLines.add(line);
            }
        } finally {
            br.close();
        }
        
        return readLines;
    }
}
