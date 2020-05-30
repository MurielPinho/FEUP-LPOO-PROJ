# LPOO_T3_G38

# Dungeon Escape


You wake up imprisoned in a dungeon with endless mazes, to escape you must get through all maze floors as fast as you can. There is a maze in each floor and each maze contains one key that must be acquired to unlock an exit, after passing through all floors you are free from the dungeon.

This project was developed by André Mamprin Mori (up201700493@fe.up.pt), Daniel Gazola Bradaschia (up201700494@fe.up.pt) and Muriel Pinho (up201700132@fe.up.pt) for LPOO 2019⁄20.

## Implemented Features


* **Moving** - Player can move to all 4 directions using the arrowkeys.
* **Time Counter** - Keeps track of the time the player spent on the maze, uses a separate thread so it updates only once every second.
* **Maze Changes** - Mazes changes as the player progresses, steadily increasing the difficulty.
* **Wall Detection**  - Check if player positions conflicts with a wall, if it does player doesn't move.
* **Key** - First objective of the maze, disappears after being acquired.
* **Exit** - Checks if the player has finished the maze, only appears on the map after the player has acquired the key.    
* **Close application** - The user may press 'ESC' to close the app.
* **Menu System** - Contains a set of options and information for the player, uses menu selector and press to continue instead of text input to control the game.
* **Menu Selector** - Draws a selector on the menu so the player can choose which options it wants.
* **Give Up** - Player can give up on a maze anytime, by pressing the 'Q' key, counts as an immediate defeat and brings the user back to the main menu.

## Design

### Design Patterns

* **Adapter** - we want to use an existing class, and the interface does not match the one we need. For example, the player controller and the player itself are the same, but the maze must change as requirements are fulfilled.
![Adapter](https://github.com/FEUP-LPOO/lpoo-2020-g38/blob/master/docs/Images/Adapter.png?raw=true)

* **Singleton** - during gameplay, we must ensure that exactly one instance of certain classes, namely the player controller and the maze class, are instantiated at a time. The game itself must be instantiated only once.
![Use Cases](https://github.com/FEUP-LPOO/lpoo-2020-g38/blob/master/docs/Images/Singleton.png?raw=true)

* **Composite** - elements of the game needed to be represented as part-whole hierarchies of objects. An element of the game may be an exit, a key, etc.
![Composite](https://github.com/FEUP-LPOO/lpoo-2020-g38/blob/master/docs/Images/Composite.png?raw=true)

* **State** - a cell alters its behavior, between passage and wall, to update the maze as the player progresses through the game.
![State](https://github.com/FEUP-LPOO/lpoo-2020-g38/blob/master/docs/Images/State.png?raw=true)

* **Command** - we used the command pattern to control user input, having the gameModel class read general input and having each different class handle the input differently.
![Command](https://github.com/FEUP-LPOO/lpoo-2020-g38/blob/master/docs/Images/Command.png?raw=true)

### Architectural Patterns

* **MVC** - the project is broken into 3 components. In addition to dividing the application into these components, 
         the model–view–controller design defines the interactions between them.
    
        Model: The central component of the pattern. It is the application's dynamic data structure, independent of the user interface. It directly manages the data, logic and rules of the application.

        View: Any representation of information such as a chart, diagram or table. Multiple views of the same information are possible, such as a bar chart for management and a tabular view for accountants.
        
        Controller: Accepts input and converts it to commands for the model or view.

## Known Code Smells and Refactoring Suggestions
  

#### Data class

The Key, Wall, Exit and Player classes all contain only fields, but they do not contain any additional functionality and cannot independently operate on the data that they own.

A way to solve the problem would be to Encapsulate a collection of elements.

#### Dead Code

As the game got more complex, certain verifications and files have become obsolete, due to better options being implemented.

A way to solve this is to delete unused code and unneeded files

## Testing Results

Tests are currently being developed

## Self-evaluation

The project was developed equally by all members, most of it was done in discord meetings in which the ideas and solutions for the code were discussed and implemented. For this reason all members had equal contribution in all parts of the project.
