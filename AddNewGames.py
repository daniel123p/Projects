import csv
import json

#method will update the csv file
def updateCSV(gameName, gamePath):
    with open(r'C:\Users\Daniel\OneDrive\Desktop\Test.csv', 'a') as file:
        test = [[gameName], [gamePath]]
        ex = csv.writer(file)
        #testing = [(gameName),(gamePath)]
        ex.writerows(test)
        

#method will update json file
def updateJSON(gameName, gamePath):

    with open(r"C:\Users\Daniel\OneDrive\Desktop\Games\GamePathJSON.json") as loadGames:
        newGames = json.load(loadGames)

    newGames[gameName] = gamePath

    with open(r"C:\Users\Daniel\OneDrive\Desktop\Games\GamePathJSON.json", "w") as writeGames:
        json.dump(newGames, writeGames)

#method will update two txt files
def updateTXT(gameName, gamePath):
    pass

#main method that executes the program
def main():
    print("This python program adds a new game and an associated file path to file associated with the PMFG programs. \n")
    gameName = str(input("Please enter the game name: \n"))
    gamePath = str(input("Please enter the game file path: \n"))

    updateCSV(gameName, gamePath)
    #updateJSON(gameName, gamePath)
    #updateTXT(gameName, gamePath)

    print("All files have been updated")    


main()