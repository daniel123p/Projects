import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import org.json.*;
import org.apache.commons.csv.*;


public class AddNewGames {
    //Input  validation method that checks to see if the user inputs an actual file path, i could simply call a File reader here, then implement a try catch. I may make another method to demonstrate this
    private static String checkPath(String gamePath){
        Scanner scanner = new Scanner(System.in);
        //Booleans that find the \\ characters in the file path and check for an exe file
        Boolean slash = gamePath.contains("\\");
        Boolean exe = gamePath.endsWith(".exe");
        //If statement that validates the input, returns correctPath if they input something, other than a filepath.
        if (exe && slash){
            return gamePath;
        } else{
                //recursion
                System.err.println("Please enter a valid file path. Be sure to that it leads to an executable file(.exe)");
                gamePath = scanner.nextLine();
                checkPath(gamePath);
            }
            return gamePath;
        
    }

    //method that counts lines in the text file. When this program opens, this method counts the amount of games already in there and adds a corresponding number.
    private static int countLines(String filePath){
        int gameCount = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            while (br.readLine() != null) gameCount++;
        }catch (IOException e){
            System.out.println("Can not count lines in text file.");
        } finally{

        }
        return gameCount;

    }

    public static void updateJSON(String gameName, String gamePath){
       try {
           //make a new file path for my json file, JSON object to store gameName gamePath variables
           String jsonFilePath = "C:\\Users\\Daniel\\OneDrive\\Desktop\\test.json";
           JSONObject gameKeys = new JSONObject();
           //Filewriter object writes the json file, then closes it
           FileWriter jsonWriter = new FileWriter(jsonFilePath, true);
           gameKeys.put(gameName, gamePath);
           //Ive removed {}. as i improved this method, i want to be able to insert a new entry within the {} of the json file itself
           //this means {current:example} when this methods invoked becomes {current:example, new:item} working on this
           jsonWriter.write(gameKeys.toString().replace("{", "").replace("}", "")); 
           jsonWriter.close();

       } catch (Exception e) {
           throw new RuntimeException(e);
       }
        //I must import the appropriate class before i can work on this method, it will update the JSON file on my computer.
    }

    //this method updates the text files on the computer
    public static void updateTXT(String gameName, String gamePath){
        //String txtPath = "C:\\Users\\Daniel\\OneDrive\\Desktop\\Games\\GameFilePath.txt";
        //String txtName = "C:\\Users\\Daniel\\OneDrive\\Desktop\\Games\\GameListTXT.txt";
        String txtPath = "C:\\Users\\Daniel\\OneDrive\\Desktop\\test2.txt";
        String txtName = "C:\\Users\\Daniel\\OneDrive\\Desktop\\test.txt";
        try {
            FileWriter gP = new FileWriter(txtPath, true);
            FileWriter gN = new FileWriter(txtName, true);
            gN.write("\n" + countLines(txtName) + " - " + gameName);
            gP.write("\n" + gamePath);
            gN.close();
            gP.close();
        } catch (IOException e) {
            System.out.println("This is an invalid filepath, please check your locations and try again");
        }
    }

    public static void updateCSV(String gameName, String gamePath){
        String csvPath = "C:\\Users\\Daniel\\OneDrive\\Desktop\\Test.csv";
        ;
        //I must import the appropriate class before i can work on this method, it will update the CSV file on my computer.
    }

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.println("This java program adds a new game and an associated file path to files associated with the PMFG\\Game Runner programs.");
        System.out.println("Please enter the game name:");
        String gameName = scanner.nextLine();
        System.out.println("Please enter the game file path:");
        String gamePath = scanner.nextLine();

        //updateTXT(gameName, checkPath(gamePath));
        updateJSON(gameName, checkPath(gamePath));
        //updateCSV(gameName, checkPath(gamePath));
        System.out.println("Files updated successfully");
        
    }
}

