# Beat-The-Brother
-Contributers --> Sümeyye Acar / Yusuf Toraman / Enes Ağırman / Mehmet Kutay Buyruk / Berin Su İyici

-A 2-Player game where each player are trying to complete the maze they are in and while doing that, they use the orbs they encounter to power up themselves or power down the enemy. The mazes are generated randomly using a type of recursive backtracking algorithm. The player who reaches the end of the maze first wins a score. The game ends when one of the players reach score three. 

-Java and libGDX game engine are used in this project

-Note that due to the difference in screen width and height the game may not look the same on your local screen!

--------------------------------------------------------------------------------------------------------------------

Instructions: In order to run the game on your local machine; you have to make sure you have a suitable environment to run java code (VS Code), you have to setup libGDX and download gradle (a VS code Extension). After these setups, download the repository as a zip folder and unzip it. Move the whole folder called "Beat The Brother" out of the repository folder and make sure the folder "Beat The Brother" is NOT in any folder! It should be directly on your destop. In order to run the game this "Beat The Brother" folder is the only neede folder. After securing "Beat The Brother" folder, remove the rest of the repository folder from your device. After this point, all you need to do is running the code located in core/src.

Attention!!! If you are using a Macbook or any device with operating system MacOS do the following: 
In order to run the game, you should add; api "com.badlogicgames.gdx:gdx-lwjgl3-glfw-awt-macos:$gdxVersion" command to the dependencies under the project(":desktop") that is in the build.gradle file.
