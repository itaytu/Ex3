   ## Ex3
      
   In this section we've created a platform enabling the user to play a simple simulation of a **Pacman-Fruit Game** on a single map.
   The game can be played by two different options:
   - Loading a CSV file containing data regarding the Pacmans and Fruits.
   - Adding fruits and Pacmans manualy by clicking points on the map.
   
   The Project is based on part of the information from previous project (Ex2). 
   The game supports various functions, such as: loading games from CSV files, saving games into KML/CSV files, converting coordinates
   to pixels and vice versa, running the game by an Algorithm that calculates paths for each pacman created/loaded to every fruit on the
   map.
   
   # Project Hierarchy
  The Hierarchy of this project is as follows:
  1. A CSV file is loaded on to the map\A game is created by adding fruits and pacmans manualy on to the map.
  2. In order to see the movement and paths for each pacman the user runs the game by pressing the "Run" button.
  3. After we finish watching the game animation we can either Save the game into a KML/CSV file or clear the game and create a new one.

   # How to use Project
   Short explanation on how to use the project:
   1. Open the Main package and run the class.
   2. Either load a CSV file onto the board, or create a game manualy by adding fruits and pacmans onto the map.
   3. Run the game in order to animate a pacman movement that creates a path for fruits getting eaten by pacmans.
   4. After the run simulation the user can either chose to run another simulation, or save the game into a CSV/Kml file, or clear the         game and create a new one.
   
   # Short explanation about the packages
   Algorithms - This package contains classes that are in charge of the " Brains of the Game" . These classes enable us to convert map
                coordinates onto pixels on frame and vice versa, calculating paths for each pacman, and showing it all on the game board
                by GUI implementation.
   
   FileFormat - This package is in charge of loading CSV files, Saving games into KML/CSV files. This is important because it links 
                between the raw data from csv files and the game containing the data of the pacmans and fruits, it also links between
                the game object and the KMl files data.
   
   GIS - This package contains all the game objects.
   
   GUI - This package is in charge of all the "View of the game" including the Board Game visualation, Pacman, Fruit, Line and movement
         visualation.
   
   Junit - Tests for calculations needed in the project.
   
   Main - Running the project.
   
   Utils - extra tools needed for calculations in project.
        
   ## Movement Algorithm short explanation
   **ShortestPathAlgo:** 
   This class is the main part for calculating paths of fruits for each pacman.
   The way the algorithm for this class works is as such:
   1. At first the Algorithm calculates every possible path from pacman to fruit to each pacman and returns the shortest path by
   time to each pacman.
   2. After every pacman has one "shortest path" we rearrange those paths from the shortest one to the longest one.
   3. We then create the calculated next step for all the pacmans, making sure there is no more than one pacman going to each
   different fruit.
   4. We initiate the next step calculated and make the changes needed for the next round 
   (changes such as: changing the position and time for the pacmans in order to calculate their new paths, remove the fruits that
   were "eaten" on the current round).
  
   ### Other Projects
   For previous version of this project you can go into:
   [GPS-Project](https://github.com/itaytu/GPS-Project).
    
