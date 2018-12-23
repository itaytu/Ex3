# Ex2-Ex4
[Ex3 Project](#Ex3)


## Ex2
OOP project for Geometric and Geographic points 

In this project we implemented a simple method to turn CSV files into Objects in java, containing Geometric, Geographic and other data 
(such as: Name, LastSeen, Type etc.). After turning the files into objects we used a method to turn those objects into KML files.

# How to use project
Short explanation on how to use the project:
  1. If only one CSV file is wanted use the following steps:
      1. open the mainTest class and create a String for the CSVfile directory path (for example: "C:/Folder_name/File_name.csv").
      2. Create a String for the new KML file output path as desired (for example: "C:/Desired_folder_name/new_file_name.kml").
      3. Use the function Csv2Kml.writeFileKML(CSVfile String, KMLfile String).
    
  2. If a few CSV files are desired use the Following steps:
      1. open the mainTest class and create a String for the folder directory path containing the CSV files 
         (for example: "C:/Folder_name").
      2. Create a String for the KML file output path as desired (for example: "C:/Desired_folder_name/new_file_name.kml").
      3. Create a new GIS_project and scan the folder by using the MultiCSV.parseForCsvFiles.
      4. Use the function MultiCSV.ProjectToKML to take the project and create a new KML file with the output path.
 
 # Short explanation about the packages
   Algorithms - Contains all the classes needed to implement the interfaces in order to create GIS elements, layers and projects.
                This package also contains the class needed to scan for few CSV files and creating a KML file from it.
    
   Coords - Contains the classes needed to calculate relevant geographic and geometric information.
            Such as: Distance3D, Distance2D, Add between 2 Points etc.

   File_format - Contains relevent classes in order to read a CSV file and create a KML file.
   
   Geom - Contains the classes representing the Geometric data of an element.
   
   GIS - Contains the classes representing the metadata, element, layer, project interfaces.
   
   Tests - Contains the Junit classes for testing.
   
   

   
   ## Ex3
   
   [Ex2 Project](#Ex2)
   
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
   **ShortestPathAlgo:** - This class is the main part for calculating paths of fruits for each pacman.
                             The way the algorithm for this class works is as such:
                             1. At first the Algorithm calculates every possible path from pacman to fruit to each pacman and returns 
                                the shortest path by time to each pacman.
                             2. After every pacman has one "shortest path" we rearrange those paths from the shortest one to the 
                                longest one.
                             3. We then create the calculated next step for all the pacmans, making sure there is no more than one
                                pacman going to each different fruit.
                             4. We initiate the next step calculated and make the changes needed for the next round (changes such as:
                                changing the position and time for the pacmans in order to calculate their new paths, remove the fruits
                                that were "eaten" on the current round).
    
    
