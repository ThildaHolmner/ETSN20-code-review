import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;


public class StringSearch {

    public static void main(String[] args) {

        if (args.length < 2) {
            System.out.println("Format: search <pattern> <file> [-i]");
            return;
        }

        String pattern = args[0];
        String filePath = args[1];
        boolean ignoreCase = args.length > 2 && args[2].equalsIgnoreCase("-i");

        try {
            searchFile(pattern, filePath, ignoreCase);
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    /**
     * Searches for a pattern in a file and prints matching lines.
     * 
     * @param pattern       Search pattern.
     * @param filePath      Path to file.
     * @param ignoreCase    If we ignore case in the search
     * @throws IOException  If error occurs when reading the file.
     */

    private static void searchFile(String pattern, String filePath, boolean ignoreCase) throws IOException {
       
        Pattern regexPattern = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE); 

        // Read the file line by line
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line; //line which will contain pattern
            int lineNbr = 0;

            while ((line = reader.readLine()) != null) {
                lineNbr++;
                Matcher matcher = regexPattern.matcher(line);
                if (matcher.find()) {
                    System.out.println("Line " + lineNbr + ": " + line);
                }
            }
        }
    }
}
