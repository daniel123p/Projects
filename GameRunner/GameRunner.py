from tkinter import *
from functools import partial
import json
import subprocess
import os
import sys
import elevate

#Adding permission to run programs that require administrator access
elevate.elevate()

#Tkinter constructor
main = Tk()

#Adding a title to the program
main.title("GameRunner")

#Framing the widgets together to create pages in the program
introFrame = Frame(main)
introFrame.pack(fill="both", expand=True)

addFileFrame = Frame(main)
addFileFrame.pack(fill="both", expand=True)

gameFrame = Frame(main)
gameFrame.pack(fill="both", expand=True)

#method that installs java if version 19 and above is unavailable.
def installJava():
    javaVersions = [
    "C:\\Program Files\\Java\\jdk-19\\bin\\",
    "C:\\Program Files\\Java\\jdk-20\\bin\\",
    "C:\\Program Files\\Java\\jdk-21\\bin\\",
    "C:\\Program Files\\Java\\jdk-22\\bin\\",
    "C:\\Program Files\\Java\\jdk-23\\bin\\"
    ]

    if getattr(sys, 'frozen', False):
        sysPath = sys._MEIPASS
    else:
        sysPath = "Z:\\"

    jdkFile = os.path.join(sysPath, "Java23Installer.exe")

    for version in javaVersions:
        if os.path.exists(version):
            break
    else:
        os.startfile(jdkFile)

#returns a jar file that will be used in our add games button
def getJAR():
    installJava()
    if getattr(sys, 'frozen', False):
        sysPath = sys._MEIPASS
    else:
        sysPath = "Z:\\"

    jarPath = os.path.join(sysPath, "AddGames.jar")
    return jarPath

#returns the run icon that will show in the left hand corner of our program
def getRunico():
    if getattr(sys, 'frozen', False):
        sysPath = sys._MEIPASS
    else:
        sysPath = "Z:\\"


    runPath = os.path.join(sysPath, "sonic.ico")
    return runPath
     
#returns the info icon for our popup window
def getInfoico():
    if getattr(sys, 'frozen', False):
        sysPath = sys._MEIPASS
    else:
        sysPath = "Z:\\"

    infoPath = os.path.join(sysPath, "info.ico")
    return infoPath

#popup to tell the user to restart the program to buttons for newly added programs
def rebootWindow():
    subprocess.run(["java", "-jar", getJAR()])
    rbWin = Toplevel(main)
    rbWin.iconbitmap(getInfoico())
    rbWin.title("Restart The Program")
    rbLabel = Label(rbWin, text="Please close the program and start it again to get your new game buttons")
    rbLabel.pack()

#loads the json file where the games and programs are stored.
def openFile():
    try:
        with open("C:\\Users\\Daniel\\OneDrive\\Desktop\\Games\\GamePathJSON.json", "r") as gpath:
            gamesPath = json.load(gpath)
    except FileNotFoundError:
        print("File path is missing, incorrect, or has been changed")

    return gamesPath
    
#beginning page
def introWindow():
    label2 = Label(introFrame, text="Welcome to GameRunner.")
    label2.pack()
    mssg = Canvas(introFrame, width=470, height=60)
    mssg.create_text(240, 35, text=" First, we will have you add your given games, then we can run them for you", fill="black")
    mssg.pack()
    start = Button(introFrame, text="Start", command= addPrograms)
    start.pack()
    
#add programs page
def addPrograms():
    introFrame.pack_forget()
    label3 = Label(addFileFrame, text="Please add games by clicking the add games button")
    label3.pack()
    javabtn = Button(addFileFrame, text="Add Games", command=partial(subprocess.run, ["java", "-jar", getJAR()]))
    javabtn.pack()
    donebttn = Button(addFileFrame, text="Done", command=programWindow)
    donebttn.pack()

#main window with programs on it.
def programWindow():
    addFileFrame.pack_forget()
    label1 = Label(gameFrame, text="Welcome to GameRunner. Press the button and the game runs")
    label1.pack()

    gameButtons = []
    for key in openFile():
        gameButtons.append(Button(gameFrame, text=key, width=len(key), command= partial(subprocess.run, openFile().get(key))))

    for button in gameButtons:
        button.pack()

    back = Button(gameFrame, text="Add More Games", command=rebootWindow, bg="blue", fg="white")
    back.pack()

#adds the run icon to the program
main.iconbitmap(getRunico())

#shows the intro window at the beginning
introWindow()

#starts the gui
main.mainloop()


