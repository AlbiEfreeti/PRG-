import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class findword {
    
    public static void main(String[] args) {
        String inputfile = "C:/Users/49178/OneDrive/Desktop/CSE Asignaturas/PRG-Programming/ballena beluga.txt";
        String outputfile = "C:/Users/49178/OneDrive/Desktop/CSE Asignaturas/PRG-Programming/output.txt";
        String searchedWord = "belugas";
        
        try {
            searchForWord(inputfile, outputfile, searchedWord);
            System.out.println("File was successfully found and functionality executed.");
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }

    public static void searchForWord(String fileIn, String fileOut, String word) throws FileNotFoundException {
        try (Scanner input = new Scanner(new File(fileIn));
             PrintWriter lines = new PrintWriter(new File(fileOut))) {
            int linecount = 0;
            while (input.hasNextLine()) {
                String line = input.nextLine(); 
                linecount++;
                String[] data = line.trim().split("[\\s\\t]+");
                for (String wordInLine : data) {
                    if (wordInLine.equals(word)) { 
                        lines.println("In line: " + linecount + ", " + word + " is found: " + line );
                        break;
                    }
                } 
            }
        }
    }
}
