import java.io.*;
import java.util.*;

class DefaultPath {
    // Default files to that store the names and file paths of games/programs
    public static String defaultTXTpath;
    public static String defaultTXTname;
    public static String defaultJSON;
    public static String defaultCSV;

    //creates file to store necessary program files
    private static File createFile() {
        File path = new File("AddNewGames\\PathsToFiles\\MainGamePaths");
        File loadGamefiles = new File("AddNewGames\\PathsToFiles\\MainGamePaths\\LoadGameFiles.txt");
        try {
            if (!loadGamefiles.exists()) {
                path.mkdirs();
                loadGamefiles.createNewFile();
            }
        } catch (IOException e) {
            System.err.println(e);
        }
        return loadGamefiles;
    }

    // default file paths on my computer (Test files)
    private static void setallDefaultpaths(){
        try {
            BufferedReader br = new BufferedReader(new FileReader(createFile()));
            ArrayList<String> pathlist = new ArrayList<>();
            String pathFiles;
            while((pathFiles = br.readLine()) != null){
                pathlist.add(pathFiles);
            }
            defaultCSV = pathlist.get(0);
            defaultJSON = pathlist.get(1);
            defaultTXTname = pathlist.get(2);
            defaultTXTpath = pathlist.get(3);

        } catch (Exception e) {System.err.println(e);}
    }

    //method that sets new files
    private static void setnewPaths() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter default CSV filepath: ");
        defaultCSV = checkPath(scanner.nextLine());
        System.out.println("Enter default JSON filepath: ");
        defaultJSON = checkPath(scanner.nextLine());
        System.out.println("Enter default TXT Program Name filepath: ");
        defaultTXTname = checkPath(scanner.nextLine());
        System.out.println("Enter default TXT files filepath: ");
        defaultTXTpath = checkPath(scanner.nextLine());
        try {
            FileWriter writer = new FileWriter(createFile());
            writer.write(defaultCSV);
            writer.write(defaultJSON);
            writer.write(defaultTXTname);
            writer.write(defaultTXTpath);
            writer.close();
        } catch (Exception e) {System.err.println(e);}
    }

    //checks file types. private method thats used for checkPath.
    private static Boolean fileTypes(String filePath) {
        String[] ftArray = {".exe", ".json", ".txt", ".csv"};
        for (String type : ftArray) {
            if (filePath.endsWith(type)) return true;
        }
        return false;
    }

    //method that checks if the file exits and is of the correct file type
    public static String checkPath(String filePath) {
        Scanner scanner = new Scanner(System.in);
        File check = new File(filePath);
        Boolean type = fileTypes(filePath);
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

    //main method that gets called in AddNewGames
    public static void defaultMain() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Has a default path been configured? (Y/N)");
        //while loop for input validation
        while (true) {
            String answer = scanner.nextLine().toLowerCase();
            if (answer.equals("yes") || answer.equals("y")) {
                setallDefaultpaths();
                break;
            } else if (answer.equals("no") || answer.equals("n")) {
                setnewPaths();
                break;
            } else {
                System.out.println("Please type in Yes or No");
            }
        }
        //prints out what each default method is.
        System.out.println("Default path for the txt file containing game locations is: " + defaultTXTpath);
        System.out.println("Default path for the txt file containing game name: " + defaultTXTname);
        System.out.println("Default path for the json file containing game names and locations is: " + defaultJSON);
        System.out.println("Default path for the csv file containing game names and locations is: " + defaultCSV);
    }
}

