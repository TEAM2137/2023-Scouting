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
## Downloading and Running the project
1. Download the zip file from the ustable (latest) branch on Github
2. Unzip the folder anywhere and open the folder in android studio

<details>
<summary><strong>Running on a tablet</strong></summary>
&nbsp;

3. Plug in a tablet to the laptop.
4. Wait for the tablet name to appear in the devices dropdown.
5. Click the run button and wait for the app to download and launch on the tablet.
6. Once the app opens on the tablet it's safe to unplug.
7. If you want to debug keep the tablet plugged in and open the run window in IntelliJ.
   &nbsp;
</details>

<details>
<summary><strong>Running virtually</strong></summary>
&nbsp;

3. Open the device manager.
4. Click "Create device".
5. Complete the device configuration.
6. Click the device dropdown and select the newly created virtual device.
7. Run the program.
   &nbsp;
</details>


## Changing Tablet Name and Color
When updating the tablets you will need to change lines in 5 different files to update tablet names and colors.
### Files
1. themes.xml (This changes the tablet color)
2. AutoViewModel at line 13 (This changes the tablet name)
3. DriverFragment at line 70 (This changes the tablet name)
4. EndgameViewModel at line 18 (This changes the tablet name)
5. SaveData at line 95 & 77 (This changes data saved to a csv file)

### Lines

1. Change (Color #) to the tablet color and number.

`Example: textView.setText("Tele-Op - Blue 1 | " + saveteamString);`

2. In the themes file you will want to comment out the entire code block that contains the color scheme you DON'T WANT.
   Make sure the color scheme you DO want has syntax highlighting (colored words) and the other theme has grayed out words.
   Make sure only ONE theme is enabled at a time.

### Screenshots
<center>Enable Blue Color Scheme</center>
<hr/>

![Blue Color Scheme](https://i.imgur.com/kU33Uxc.png)

<center>Enable Red Color Scheme</center>
<hr/>

![Red Color Scheme](https://i.imgur.com/mdJigLx.png)