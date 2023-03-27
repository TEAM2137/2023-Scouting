# The 2023 Charged-Up Scouting Program
&nbsp;
&nbsp;
## Android Settings
```
Android Gradle Plugin Version: 7.4.1
Gradle Version 7.5
```
&nbsp;
## File Structure
```
├── /2023-Scouting/app/src/main/java/com/example/a2023_scouting_v2/ui
│   ├── auto
|       ├── AutoFragment << Main Page Program
|       └── endgame
│   ├── driver
|       ├── DriverFragment << Main Page Program
|       └── DriverViewModel
│   └── endgame
|       ├── EndgameFragment  << Main Page Program
|       └── EndgameViewModel
```
&nbsp;
## How to run
1. Download the zip file
2. Unzip and open the folder in android studio
3. Plug in a tablet and wait for it to show up in the avaliable devices list
4. Click the run button

&nbsp;
## ! Important !
&nbsp;
When updating the tablets you will need to change 4 different files to update tablet names and colors.
&nbsp;
1. themes.xml (This changes the tablet color)
2. AutoViewModel at line 13 (This changes the tablet name)
3. DriverFragment at line 70 (This changes the tablet name)
4. EndgameViewModel at line 18 (This changes the tablet name)

&nbsp;
Change (Color #) to the tablet color and number. (Blue 1)
In the themes file you will want to comment out the entire code block that contains the color scheme you DON'T WANT.
Make sure the color scheme you DO want has syntax highlighting (colored words) and the other theme has grayed out words.
Make sure only ONE theme is enabled at a time.