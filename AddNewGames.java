import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class AddNewGames {
    public static void updateJSON(String gameName, String gamePath){
        pass;
    }

    public static void updateTXT(String gameName, String gamePath){
        try {
            FileWriter gN = new FileWriter("C:/Users/Daniel/OneDrive/Desktop/test.txt", true);
            gN.write("\n"+gameName);
            gN.close();
        } catch (IOException e) {
            System.out.println("Ok");
        }

    }
    public static void updateCSV(String gameName, String gamePath){
        pass;
    }

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.println("This java program adds a new game and an associated file path to file associated with the PMFG programs.");
        System.out.println("Please enter the game name:");
        String gameName = scanner.nextLine();
        System.out.println("Please enter the game file path:");
        String gamePath = scanner.nextLine();

        updateTXT(gameName, gamePath);
        
    }
}