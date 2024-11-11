import subprocess
import elevate

#raise permissions
elevate.elevate()

# Open txt file of file paths
with open("C:\\Users\Daniel\OneDrive\Desktop\Games\GameFilePath.txt", "r") as gpfile:
    filePaths =  gpfile.read().splitlines()

#Open txt file for list of games
with open(r"C:\Users\Daniel\OneDrive\Desktop\Games\GameListTXT.txt", "r") as glist:
    gamelist = glist.read().splitlines()

#replaces Sonic Heroes' file path to the original exe file. Makes it more compatible with run() method
filePaths.pop(8)
filePaths.insert(8, r'Z:\SONICHEROES\Tsonic_win.exe')

#Asking the user to select the games, validates users input
while True:
    try:
        print("Welcome to GameRunner v1, this program uses 2 TXT files to load the game and the associated file paths.")
        print("These Games Are Available For Play: \n ")
        for game in gamelist:
            print(game)
        gameSelect = int(input("\nInput the games corresponding number, then press Enter to play it: \n"))
        if gameSelect < 0:
            gameSelect = "Q"
        runGames = filePaths[gameSelect]
        break
    except ValueError:
        print("Please enter a number that corresponds with the game \n")
    except IndexError:
        print("This number is not listed, please enter a listed number \n")
    except TypeError:
        print("Please enter a positive number \n")

#runs users selected game
subprocess.run(runGames)
