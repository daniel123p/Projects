import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.io.File;
import com.fasterxml.jackson.databind.*;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class AddNewGames {
    //Input validation method that checks to see if the user inputs an actual file path, ensures there is a real file there.
    private static String checkPath(String filePath){
        Scanner scanner = new Scanner(System.in);
        File check = new File(filePath);
        Boolean type = fileTypes(filePath);
        //If statement that validates the input, returns correctPath if they input something, other than a filepath.
        if (type && check.exists()) {return filePath;}
        else{//recursion
                System.err.println("Please enter a valid file path. Be sure to that it leads to an exe, json, txt, or csv file.");
                filePath = scanner.nextLine();
                checkPath(filePath);
            }
            return filePath;
        
    }
    private static Boolean fileTypes(String filePath){
        String[] ftArray = {".exe", ".json", ".txt", ".csv"};
        for (String type: ftArray){if (filePath.endsWith(type)) return true;}
        return false;
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
           //String jsonFilePath = "C:\\Users\\Daniel\\OneDrive\\Games\\GamePathJSON.json";
           String jsonFilePath = checkPath("C:\\Users\\Daniel\\OneDrive\\Desktop\\test.json");
           //Mapper from the jackson library will read and write our games
           ObjectMapper mapper = new ObjectMapper();
           Map<String, String> newGames = mapper.readValue(new File(jsonFilePath), Map.class);
           newGames.put(gameName, gamePath);
           mapper.writeValue(new File(jsonFilePath), newGames);
       } catch (Exception e) {
           throw new RuntimeException(e);
       }
    }

    //this method updates the text files on the computer
    public static void updateTXT(String gameName, String gamePath){
        //String txtPath = checkPath("C:\\Users\\Daniel\\OneDrive\\Desktop\\Games\\GameFilePath.txt");
        //String txtName = checkPath("C:\\Users\\Daniel\\OneDrive\\Desktop\\Games\\GameListTXT.txt");
        String txtPath = checkPath("C:\\Users\\Daniel\\OneDrive\\Desktop\\test2.txt");
        String txtName = checkPath("C:\\Users\\Daniel\\OneDrive\\Desktop\\test.txt");
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

    //writes to the csv file. contains a logic error somewhere.
    public static void updateCSV(String gameName, String gamePath) {
        String csvPath = checkPath("C:\\Users\\Daniel\\OneDrive\\Desktop\\Test.csv");
        try(FileReader fr = new FileReader(csvPath)) {
            List<String[]> list = new ArrayList<>();
            String[] csvList;
            CSVReader csvReader = new CSVReader(fr);
            while (null != (csvList = csvReader.readNext())) {list.add(csvList);}
            list.get(list.size() - 2)[list.get(list.size() - 1).length - 1] = gameName;
            list.get(list.size() - 1)[list.get(list.size() - 1).length - 1] = gamePath;
            for(String[] path : list){
                for(String rowe : path){
                    System.out.print(rowe);
                }
                System.out.println();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.println("This java program adds a new game and an associated file path to files associated with the PMFG\\Game Runner programs.");
        System.out.println("Please enter the game name:");
        String gameName = scanner.nextLine();
        System.out.println("Please enter the game file path:");
        String gamePath = scanner.nextLine();

        //updateTXT(gameName, checkPath(gamePath));
        //updateJSON(gameName, checkPath(gamePath));
        updateCSV(gameName, checkPath(gamePath));
        System.out.println("Files updated successfully");
        
    }
}

