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
### App Pages
```
├── /2023-Scouting/app/src/main/java/com/example/a2023_scouting_v2/ui
    ├── auto
        ├── AutoFragment << Main Page Program
        └── AutoViewModel
    ├── data
        ├── DataFragment << Main Page Program
        └── DataViewModel
    ├── driver
        ├── DriverFragment << Main Page Program
        └── DriverViewModel
    └── endgame
        ├── EndgameFragment  << Main Page Program
        └── EndgameViewModel
```
### XML Files
*Not important files have been removed for simplicity.*
```
├── /2023-Scouting/app/src/main/res
    ├── chargedup-logo.png
    ├── drawable
    ├── drawable-hdpi
    ├── drawable-mdpi
    ├── drawable-v24
    ├── drawable-xhdpi
    ├── drawable-xxhdpi
    ├── drawable-xxxhdpi
    ├── font
    ├── layout
        ├── activity_main.xml << Do Not Modify
        ├── custom_spinner.xml << Team Dropdown Theme File
        ├── fragment_auto.xml << Auto Page
        ├── fragment_data.xml << Data Page
        ├── fragment_driver.xml << Driver Page
        └── fragment_endgame.xml << Endgame Page
    ├── menu
    ├── mipmap-anydpi-v26
    ├── mipmap-hdpi
    ├── mipmap-mdpi
    ├── mipmap-xhdpi
    ├── mipmap-xxhdpi
    ├── mipmap-xxxhdpi
    ├── navigation
    ├── values
        ├── colors.xml << Do not change this file
        ├── dimens.xml
        ├── strings.xml << Teams list and app version number
        └── themes.xml << This is the correct theming file
    ├── values-night << This does not need to be changed
    └── xml
```
&nbsp;
## How to run
1. Download the zip file from the ustable (latest) branch on Github
2. Unzip the folder anywhere and open the folder in android studio
3. Plug in a tablet and wait for the name to appear in between the run configurations dropdown and run button.
4. Click the run button and wait for the app to download and launch on the tablet.
5. Once the app opens on the tablet it's safe to unplug

&nbsp;
## Changing Tablet Name and Color
When updating the tablets you will need to change lines in 4 different files to update tablet names and colors.
### Files
1. themes.xml (This changes the tablet color)
2. AutoViewModel at line 13 (This changes the tablet name)
3. DriverFragment at line 70 (This changes the tablet name)
4. EndgameViewModel at line 18 (This changes the tablet name)

### Lines

1. Change (Color #) to the tablet color and number.

`Example: textView.setText("Tele-Op - Blue 1 | " + saveteamString);`

2. In the themes file you will want to comment out the entire code block that contains the color scheme you DON'T WANT.
   Make sure the color scheme you DO want has syntax highlighting (colored words) and the other theme has grayed out words.
   Make sure only ONE theme is enabled at a time.