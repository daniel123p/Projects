import json
import os
import elevate

#raise permissions
elevate.elevate()

#Opening csv file, throws error if the file path is incorrect
try:
    with open(r"C:\Users\Daniel\OneDrive\Desktop\Games\GamePathJSON.json", "r") as gpath:
        gamesPath = json.load(gpath)
except FileNotFoundError:
    print("File path is missing, incorrect, or has been changed")

#While loop for input validation, will throw and error if the user doesn enter a correct game
while True:
    try:
        print("Welcome to GameRunner v3, this program uses a JSON file to load the game and the associated file paths.")
        print("Here are the available games: \n")
        for key in gamesPath:
            print(key)
        gameSelect = str(input('\nInput the games name, then press Enter to play it: \n'))
        runGame = gamesPath[gameSelect]
        break
    except KeyError:
        print("Please select a game from the list. Be sure to use correct spelling.")
    
#runs the game
os.startfile(runGame)