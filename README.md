# Light-and-Vision-Demo

This project demonstrates light and vision capabilities in a 2D world.

The application is designed to be ran at the windowsize at which it launches. If any adjustments are made to the windowsize the application might not behave as intended anymore. Furthermore the application is divided into three tabs, each demonstrating a different example of light and vision capabilities.

## Mouse follower

The first tab contains a light source that will follow your mouse when moved around in the application. The light will be blocked by walls to mimic a real light source.
 
## Cube spawner

In this tab a static light source can be moved by clicking the right mouse button. The black dot indicates the origin of the light source. 

The light source will be blocked by cubes which can be spawned in by the "spawn cube" button. These cubes can also be moved when clicking on them with the left mouse button and dragging them.

To mimic a real light source the application will render the light in real time. So when blocks are dragged the blocking of the light source will also be recalculated.

## First person perspective

The last tab contains a 2D map made out of blocks on the right half of the screen. Inside this map a dot representing the player is drawn along with its field of vision.

On the left half of the screen a semi-3D visualisation of the 2D map is drawn from the perspective of the player.

The player can be moved forward into the direction of its field of vision by clicking the left mouse button repeatedly. The field of vision can be turned into any direction by scrolling up or down on the mouse wheel.

# Build requirements

- To successfully build the application, fxgraphics2d-1.10.jar needs to be added to the library of the project. The jar is located in the /lib folder.
- Because of the usage of JavaFX, Java 1.8 must be set as the SDK.
- Entrypoint into the application is GuiMain.java located in the application package.

# Inspired by

- https://ncase.me/sight-and-light/
- https://www.redblobgames.com/articles/visibility/
