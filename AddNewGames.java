import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class AddNewGames {

    //Input validation method that checks to see if the user inputs an actual file path
    private static String checkPath(String gamePath){
        Scanner scanner = new Scanner(System.in);
        String correctPath = "";
        //Booleans that find the \\ characters in the file path and check for an exe file
        Boolean slash = gamePath.contains("\\");
        Boolean exe = gamePath.contains(".exe");
        //If statement that validates the input, returns correctPath if they input something, other than a filepath.
        if (exe == true && slash == true){
            return gamePath;
        } else{
            while (exe == false || slash == false){
                System.out.println("Please enter a file path that leads to an exe file and has slashes:");
                correctPath = scanner.nextLine();
                Boolean slash2 = correctPath.contains("\\");
                Boolean exe2 = correctPath.contains(".exe");
                if (exe2 == true && slash2 == true) {
                    break;
                }
            }
            return correctPath;
        }

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
        ;
        //I must import the appropriate class before i can work on this method, it will update the JSON file on my computer.
    }

    //this method updates the text files on the computer
    public static void updateTXT(String gameName, String gamePath){
        String filePath = "C:\\Users\\Daniel\\OneDrive\\Desktop\\Games\\GameFilePath.txt";
        String fileName = "C:\\Users\\Daniel\\OneDrive\\Desktop\\Games\\GameListTXT.txt";
        try {
            FileWriter gP = new FileWriter(filePath, true);
            FileWriter gN = new FileWriter(fileName, true);
            gN.write("\n" + countLines(fileName) + " - " + gameName);
            gP.write("\n" + gamePath);
            gN.close();
            gP.close();
        } catch (IOException e) {
            System.out.println("This is an invalid filepath, please check your locations and try again");
        }
    }

    public static void updateCSV(String gameName, String gamePath){
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
        String checked = checkPath(gamePath);

        updateTXT(gameName, checked);
        System.out.println("Files updated successfully");
        
    }
}
