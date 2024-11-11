import csv
import subprocess
import elevate

#raise permissions
elevate.elevate()

#Open csv File
with open(r'C:\Users\Daniel\OneDrive\Desktop\Games\GameDict.csv') as file:
    gameCsv = csv.DictReader(file)
    
    #Create a dictionary from csv file
    gameDict = {}
    for row in gameCsv:
        gameDict.update(row)

#Ask user for the game, then validate the user entry.
while True:
    try:
        print("Welcome to GameRunner v2, this program uses a CSV file to load the game and the associated file paths.")
        print("Here are the available games: \n")
        for key in gameDict:
            print(key)
        gameSelect = str(input('\nInput the games name, then press Enter to play it: \n'))
        runGame = gameDict[gameSelect]
        break
    except KeyError:
        print("Please select a game from the list. Be sure to use correct spelling.")

#Run the selected game    
subprocess.run(runGame)


