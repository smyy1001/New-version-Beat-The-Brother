package com.f1;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class maze {
    Integer MAZE_HEIGHT;
    Integer MAZE_WIDTH;
    Integer numberOfLocations;
    Stack<Integer> XStack;
    Stack<Integer> YStack;

    ArrayList<ArrayList<Integer>> visitedLocations; 

    ArrayList<Integer> v = new ArrayList<Integer>();

    Integer currentXLocation;
    Integer currentYLocation;

    char[][] maze;

    Random rand = new Random();
    
    
    // constructor that initializes variables, generates an empty map of given size, makes the intitial 
    // position the upper left corner and add that location to visited locations
    maze(Integer aMAZE_HEIGHT, Integer aMAZE_WIDTH){

        XStack = new Stack<Integer>();
        YStack = new Stack<Integer>();

        numberOfLocations = aMAZE_HEIGHT * aMAZE_WIDTH;
        MAZE_HEIGHT = aMAZE_HEIGHT*4;
        MAZE_WIDTH = aMAZE_WIDTH*4;
        maze = generateEmptyMaze(aMAZE_HEIGHT, aMAZE_WIDTH);

        visitedLocations = new ArrayList<>();
        currentXLocation = 2;
        currentYLocation = 2;

        addVisitedLocation(currentXLocation, currentYLocation);
        
    }


    // takes a location with x and y coordinates and adds that location to visited locations
    public void addVisitedLocation( Integer xLocation, Integer yLocation) {
        ArrayList<Integer> visitedLocation = new ArrayList<Integer>();
        visitedLocation.add(xLocation);
        visitedLocation.add(yLocation);
        visitedLocations.add(visitedLocation);
        XStack.push(xLocation);
        YStack.push(yLocation);
    }



    // this function generates an empty maze with walls in every possible location. 
    // generateMaze function later is going to break some of the walls to create a maze.
    public char[][] generateEmptyMaze( Integer aMAZE_HEIGHT, Integer aMAZE_WIDTH ) {


        MAZE_HEIGHT = aMAZE_HEIGHT*4;
        MAZE_WIDTH = aMAZE_WIDTH*4;


        char[][] maze = new char[MAZE_HEIGHT + 1][MAZE_WIDTH + 1];

        int count = 0;
        for (int j = 0; j < maze[0].length; j++) {
            maze[0][j] = 'X';
        }
        

        for (int i = 1; i < maze.length; i++) {

            for (int j = 0; j < maze[0].length-1; j = j + 4) {
                maze[i][j] = 'X';
                maze[i][j+1] = 'O';
                maze[i][j+2] = 'O';
                maze[i][j+3] = 'O';
            }
            maze[i][maze[0].length-1] = 'X';
                            
            count++;
            
            // /*
            if (count > 3) {
                count = 0;
                for (int j = 0; j < maze[0].length; j++) {
                    maze[i][j] = 'X';
                }
            }
            // */
                   
        }
        return maze;
    }



    // moves right: moves current location to one square right and breaks the walls in its way.
    public void moveRight() {

        if (isMoveRightPossible(currentXLocation, currentYLocation)) {
            maze[currentYLocation-1][currentXLocation + 2] = 'O';
            maze[currentYLocation][currentXLocation + 2] = 'O';
            maze[currentYLocation+1][currentXLocation + 2] = 'O';
    
            currentXLocation += 4;

            addVisitedLocation(currentXLocation, currentYLocation);

        }


    }

    // moves left: moves current location to one square left and breaks the walls in its way.
    public void moveLeft() {

        if (isMoveLeftPossible(currentXLocation, currentYLocation)) {
            maze[currentYLocation-1][currentXLocation - 2] = 'O';
            maze[currentYLocation][currentXLocation - 2] = 'O';
            maze[currentYLocation+1][currentXLocation - 2] = 'O';
    
            currentXLocation -= 4;

            addVisitedLocation(currentXLocation, currentYLocation);
        }



    }

    // moves up: moves current location to one square up and breaks the walls in its way.
    public void moveUp() {

        if (isMoveUpPossible(currentXLocation, currentYLocation)) {
            maze[currentYLocation-2][currentXLocation - 1] = 'O';
            maze[currentYLocation-2][currentXLocation] = 'O';
            maze[currentYLocation-2][currentXLocation + 1] = 'O';
    
            currentYLocation -= 4;  
            
            addVisitedLocation(currentXLocation, currentYLocation);
        }


    }

    // moves down: moves current location to one square down and breaks the walls in its way.
    public void moveDown() {

        if (isMoveDownPossible(currentXLocation, currentYLocation)) {
            maze[currentYLocation+2][currentXLocation - 1] = 'O';
            maze[currentYLocation+2][currentXLocation] = 'O';
            maze[currentYLocation+2][currentXLocation + 1] = 'O';
    
            currentYLocation += 4;

            addVisitedLocation(currentXLocation, currentYLocation);
        }



    }

    // returns if moving to right possible. It is not possible if the location one square right of the 
    // location given by the input coordinates is not in the boundries or is visited before
    public boolean isMoveRightPossible(Integer xLocation, Integer yLocation) {
        if (xLocation + 4 > 0 && yLocation > 0 && xLocation + 4 < MAZE_WIDTH && yLocation < MAZE_HEIGHT) {

            if (isVisited(xLocation + 4, yLocation) == false) {
                return true;
            }
        }
        return false;
    }

    // returns if moving to left possible. It is not possible if the location one square left of the 
    // location given by the input coordinates is not in the boundries or is visited before
    public boolean isMoveLeftPossible(Integer xLocation, Integer yLocation) {
        if (xLocation - 4 > 0 && yLocation > 0 && xLocation - 4 < MAZE_WIDTH && yLocation < MAZE_HEIGHT) {

            if (isVisited(xLocation - 4, yLocation) == false) {
                return true;
            }
        }
        return false;
    }

    // returns if moving to up possible. It is not possible if the location one square up of the 
    // location given by the input coordinates is not in the boundries or is visited before
    public boolean isMoveUpPossible(Integer xLocation, Integer yLocation) {
        if (xLocation > 0 && yLocation - 4 > 0 && xLocation < MAZE_WIDTH && yLocation - 4 < MAZE_HEIGHT) {

            if (isVisited(xLocation, yLocation - 4) == false) {
                return true;
            }
        }
        return false;
    }

    // returns if moving to down possible. It is not possible if the location one square down of the 
    // location given by the input coordinates is not in the boundries or is visited before   
    public boolean isMoveDownPossible(Integer xLocation, Integer yLocation) {
        if (xLocation > 0 && yLocation + 4 > 0 && xLocation < MAZE_WIDTH && yLocation + 4 < MAZE_HEIGHT) {

            if (isVisited(xLocation, yLocation + 4) == false) {
                return true;
            }
        }
        return false;
    }


    // checks if the given position with x and y coordinates is visited before
    public boolean isVisited( Integer aXLocation, Integer aYLocation ) {
        for (int i = 0; i < visitedLocations.size(); i++) {
            if (visitedLocations.get(i).get(1) == aYLocation && visitedLocations.get(i).get(0) == aXLocation) {
                return true;
            }
        }
        return false;
        
    }


    // moveRandomly() function selects a random direction among possible movements and moves along that way
    public void moveRandomly() {
        
        ArrayList<Character> choices = new ArrayList<Character>();

        if (isMoveRightPossible(currentXLocation, currentYLocation)) {
            choices.add('R');
        }
        if (isMoveLeftPossible(currentXLocation, currentYLocation)) {
            choices.add('L');
        }
        if (isMoveUpPossible(currentXLocation, currentYLocation)) {
            choices.add('U');
        }
        if (isMoveDownPossible(currentXLocation, currentYLocation)) {
            choices.add('D');
        }

        if (choices.size() > 0) {
            Character choice = choices.get(rand.nextInt(choices.size()));

            if (choice == 'R') {
                moveRight();
            }
            else if (choice == 'L') {
                moveLeft();
            }
            else if (choice == 'U') {
                moveUp();;
            }
            else if (choice == 'D') {
                moveDown();;
            }
        }
        
                
    }


    // returns if there exist a possible move from the current location or are we stuck.
    public boolean movingPossible() {
        boolean result = isMoveRightPossible(currentXLocation, currentYLocation) ||
         isMoveLeftPossible(currentXLocation, currentYLocation) || isMoveUpPossible(currentXLocation, currentYLocation) ||
         isMoveDownPossible(currentXLocation, currentYLocation);

        return result;
    }


    // returns a boolean indicating if we visited everywhere on the maze
    public boolean isEverywhereVisited() {
        if (visitedLocations.size() >= numberOfLocations) {
            return true;
        }
        return false;
    }

    //  generates the maze with recursive backtracking algorithm. It generates the maze by moving randomly and breaking the walls 
    // along its way while doing that. When it's stuck at some locations, it goes back until it is not stuck and selects a 
    // different route this time to break some more walls and go the unvisited places. When all of the places are visited, the
    // maze is ready.
    public void generateMaze() {
        
        while (isEverywhereVisited() == false) {
            if (movingPossible()) {
                moveRandomly();
            }
            else{
                XStack.pop();
                currentXLocation = XStack.peek();

                YStack.pop();
                currentYLocation = YStack.peek();

                moveRandomly();
            }
        }
    }



}
