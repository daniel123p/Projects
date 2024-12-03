import java.io.*;
import java.util.*;
import com.fasterxml.jackson.databind.*;


public class AddPrograms {

    public static String checkPath(String filePath) {
        Scanner scanner = new Scanner(System.in);
        File check = new File(filePath);
        Boolean type = filePath.endsWith(".exe");
        //If statement that validates the input, returns correctPath if they input something, other than a filepath.
        if (type && check.exists()) {
            return filePath;
        } else {//recursion
            System.err.println("Please enter a valid file path. Be sure to that it leads to the stated default file path type.");
            filePath = scanner.nextLine();
            checkPath(filePath);
        }
        return filePath;
    }

    //method that creates the file
    private static File createFile() {
        File path = new File("C:\\JSON");
        File makeJSON = new File("C:\\JSON\\ProgramMap.json");
        ObjectMapper mapper = new ObjectMapper();
        //empty hashmap that we will write to our new file for later
        Map<String, String> empty = new HashMap<>();  
        try {//checks to see if the json file is already there
            if (!makeJSON.exists()) {
                //Makes directory and file
                path.mkdirs();
                makeJSON.createNewFile();
                //writes hashmap to file
                mapper.writeValue(makeJSON, empty);
            }
        } catch (IOException e) {
            System.err.println(e);
        }
        return makeJSON;
    }

    public static void updateJSON(String programName, String programPath){
       try {
           //Mapper from the jackson library will read and write our games
           ObjectMapper mapper = new ObjectMapper();
           //load file contents into a hash map
           Map<String, String> newProgram = mapper.readValue(createFile(), Map.class);
           //add new user input into hashmap
           newProgram.put(programName, programPath);
           //write modified hashmap into the file
           mapper.writeValue(createFile(), newProgram);
       } catch (Exception e) {
           throw new RuntimeException(e);
       }
    }



    public static void main(String[] args){
        //Main method that prompts user for program information
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello, we will be adding a new program to ProgramRunner! \n");
        System.out.println("Please enter the program name:");
        String programName = scanner.nextLine();
        System.out.println("Please enter the programs file path: (Note: the program file must be an executable (.exe))");
        String programPath = checkPath(scanner.nextLine());
        updateJSON(programName, programPath);
        System.out.println("Programs added successfuly"); 
    }
}

