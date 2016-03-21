/*
James Hahn

This program is designed for project 4 in CS0445 taught by Dr. John Ramirez.

A command-line argument is taken, which is the name of the data file, which contains
all boards for the user to manage.  Each baord should be in this format:
height width
startingXValue startingYValue
values values values values
values values values values
values values values values

An example board:
4 5
0 0
0 0 0 0 1
0 1 1 0 1
0 1 2 0 1
0 1 0 0 1

It utilizes recursion and back-tracking to solve a maze/board and prints out
all solutions that begin at the starting position and end at the "2" in the board, which
is the end position.

In addition, once all solutions have been found, the shortest path from start to end
is displayed to the user.
*/

import java.util.*;
import java.io.*;

public class Assig4{
	//Create some global variables to keep track of several important stats of each board
	private static long solutions = 0;
	private static long recursiveCalls = 0;
	private static long shortestSolution = -1;
	private static ArrayList<String> shortestPath = new ArrayList<String>();

	public static void main(String[] args){
		try{
			//File found from command-line argument
			File mazeFile = new File(args[0]);
			Scanner mazeReader = new Scanner(mazeFile);

			//Initial board variables
			int height = -1;
			int width = -1;
			int startX = -1;
			int startY = -1;
			int endX = -1;
			int endY = -1;
			int[][] maze = null;

			//This for loop iterates for each board
			for(int i = 0; mazeReader.hasNextInt(); i++){
				height = mazeReader.nextInt();
				width = mazeReader.nextInt();
				maze = new int[height][width];

				//This for loop iterates for each line in the i-th board
				//It creates the initial board
				for(int j = 0; j < maze.length; j++){
					for(int k = 0; k < maze[0].length; k++){
						int value2 = -1;
						if(k == 0 && j == 0){
							if(mazeReader.hasNextInt()) value2 = mazeReader.nextInt();
							startX = value2;
							if(mazeReader.hasNextInt()) value2 = mazeReader.nextInt();
							startY = value2;
						}
						if(mazeReader.hasNextInt()) value2 = mazeReader.nextInt();
						maze[j][k] = value2;
					}
				}

				//Print the initial board
				System.out.println("----------------------------------\nInitial board: \n");
				for(int l = 0; l < maze.length; l++){
					for(int m = 0; m < maze[0].length; m++){
						if(maze[l][m] == 2){
							endY = l;
							endX = m;
						}
						System.out.print(maze[l][m] + " ");
					}
					System.out.println();
				}
				System.out.printf("\nStart position: (%d, %d)\n\n", startX, startY);

				//This is the actual path-finding program
				traverseMaze(startY, startX, width, height, maze, startX, startY, new ArrayList<String>());

				//Print out the end results of each board:
				//# of solutions, # of recursive calls, shortest path length, and the actual path on the board
				System.out.println("Total solutions: " + solutions);
				System.out.println("Total recursive calls: " + recursiveCalls);
				if(shortestSolution < 0) System.out.println("Shortest solution: NaN");
				else{
					System.out.println("Shortest solution: " + shortestSolution);
					parseShortestPath(maze, startX, startY, endX, endY, shortestPath);
				}

				//Make sure to reset the global variable values for each board
				shortestSolution = -1;
				recursiveCalls = 0;
				solutions = 0;
				shortestPath.clear();
			}

			mazeReader.close();
		} catch(Exception e){
			System.out.println("ERROR");
		}
	}

	//This method takes a board (maze) as input, as well as the starting position and dimensions of the board.
	//The ultimate goal is to find ALL solutions to reach the end of the maze (labelled as "2") beginning at the start position
	public static void traverseMaze(int currX, int currY, int width, int height, int[][] maze, int startX, int startY, ArrayList<String> path){
		//If you come across a tile on the board, mark it so we know we've been there.
		//Since the maze is int[][], I decide to use 7 to logically keep track of "travelled paths"
		if(maze[currY][currX] != 2) maze[currY][currX] = 7;
		//Make sure to add the starting position to the path
		if(currY == startX && currX == startY) path.add(currY + "," + currX);
		//Make sure the end of the maze is added to the path
		if(maze[currY][currX] == 2) path.add(currY + "," + currX);

		if(currX > 0){
			if(maze[currY][currX-1] == 2){ //A solution is found
				printOutput(maze, startX, startY, path);
				solutions++;
				if(shortestSolution <= 0){ //There has not been a shortest solution found yet
					shortestSolution = path.size()+1;
					shortestPath.clear();
					shortestPath.addAll(path);
				} else if(path.size() < shortestSolution){ //A new shortest solution is found
					shortestSolution = path.size()+1;
					shortestPath.clear();
					shortestPath.addAll(path);
				}
			}
		}
		if(currY > 0){
			if(maze[currY-1][currX] == 2){ //A solution is found
				printOutput(maze, startX, startY, path);
				solutions++;
				if(shortestSolution <= 0){ //There has not been a shortest solution found yet
					shortestSolution = path.size()+1;
					shortestPath.clear();
					shortestPath.addAll(path);
				} else if(path.size() < shortestSolution){ //A new shortest solution is found
					shortestSolution = path.size()+1;
					shortestPath.clear();
					shortestPath.addAll(path);
				}
			}
		}
		if(currX < maze[0].length-1){
			if(maze[currY][currX+1] == 2){ //A solution is found
				printOutput(maze, startX, startY, path);
				solutions++;
				if(shortestSolution <= 0){ //There has not been a shortest solution found yet
					shortestSolution = path.size()+1;
					shortestPath.clear();
					shortestPath.addAll(path);
				} else if(path.size() < shortestSolution){ //A new shortest solution is found
					shortestSolution = path.size()+1;
					shortestPath.clear();
					shortestPath.addAll(path);
				}
			}
		}
		if(currY < maze.length-1){
			if(maze[currY+1][currX] == 2){ //A solution is found
				printOutput(maze, startX, startY, path);
				solutions++;
				if(shortestSolution <= 0){ //There has not been a shortest solution found yet
					shortestSolution = path.size()+1;
					shortestPath.clear();
					shortestPath.addAll(path);
				} else if(path.size() < shortestSolution){ //A new shortest solution is found
					shortestSolution = path.size()+1;
					shortestPath.clear();
					shortestPath.addAll(path);
				}
			}
		}

		//Checks if we can navigate UP in the maze
		if(validMove(maze, currX, currY-1)){
			if(maze[currY-1][currX] != 7 && maze[currY-1][currX] != 2){
				path.add((currY-1) + "," + currX); //Add this position to the path
				recursiveCalls++;
				traverseMaze(currX, currY-1, width, height, maze, startX, startY, path);
			}
		}
		//Checks if we can navigate DOWN in the maze
		if(validMove(maze, currX, currY+1)){
			if(maze[currY+1][currX] != 7 && maze[currY+1][currX] != 2){
				path.add((currY+1) + "," + currX); //Add this position to the path
				recursiveCalls++;
				traverseMaze(currX, currY+1, width, height, maze, startX, startY, path);
			}
		}
		//Checks if we can navigate LEFT in the maze
		if(validMove(maze, currX-1, currY)){
			if(maze[currY][currX-1] != 7 && maze[currY][currX-1] != 2){
				path.add(currY + "," + (currX-1)); //Add this position to the path
				recursiveCalls++;
				traverseMaze(currX-1, currY, width, height, maze, startX, startY, path);
			}
		}
		//Checks if we can navigate RIGHT in the maze
		if(validMove(maze, currX+1, currY)){
			if(maze[currY][currX+1] != 7 && maze[currY][currX+1] != 2){
				path.add(currY + "," + (currX+1)); //Add this position to the path
				recursiveCalls++;
				traverseMaze(currX+1, currY, width, height, maze, startX, startY, path);
			}
		}

		//When the program is backtracking, remove the 7 on each tile and reset it back to 0
		if(currX != startY || currY != startX){
			if(maze[currY][currX] != 2) maze[currY][currX] = 0;
			path.remove(currY + "," + currX);
		}
	}

	//This method checks to see if a given position in the maze is valid
	public static boolean validMove(int[][] maze, int x, int y){
		if(y >= maze.length || x >= maze[0].length || y < 0 || x < 0) return false; //The coordinates are out of the bounds of the maze
		else if(maze[y][x] == 1 || maze[y][x] == 7) return false; //A 1 means the tile is a wall and a 7 means the tile has already been traversed
		else return true;
	}

	//Print out a solution that was found
	public static void printOutput(int[][] maze, int startX, int startY, ArrayList<String> path){
		int x = 0;
		int endX = -1;
		int endY = -1;

		//Print out the solution, replacing the old 7s with Xs
		for(int k = 0; k < maze.length; k++){
			for(int l = 0; l < maze[0].length; l++){
				if(maze[k][l] == 7 || maze[k][l] == 2){
					if(maze[k][l] == 7) System.out.print("x ");
					else if(maze[k][l] == 2){
						System.out.print("2 ");
						endY = k;
						endX = l;
					}
					x++;
				}
				else System.out.print(maze[k][l] + " ");
			}
			System.out.println();
		}
		path.add(endY + "," + endX); //Add the end of the solution to the path

		System.out.println("Solution found with " + (path.size()) + " segments");

		//Print out the path
		System.out.print("Path: ");
		for(int i = 0; i < path.size(); i++){
			System.out.print("(" + path.get(i) + ") ");
		}
		System.out.println("\n");

		//Remove the end of the solution because now we have begun backtracking
		path.remove(endY + "," + endX);
	}

	//Call this method when the a shortest path to a board has been found
	//It essentially functions the same as the printOutput
	public static void parseShortestPath(int[][] maze, int startX, int startY, int endX, int endY, ArrayList<String> path){
		//Parse the path ArrayList for each coordinate
		for(String s: path){
			String[] coord = s.split(",");
			int x = Integer.parseInt(coord[0]);
			int y = Integer.parseInt(coord[1]);
			maze[x][y] = 7;
		}

		//Start printing out the actual shortest path on the baord
		for(int i = 0; i < maze.length; i++){
			for(int j = 0; j < maze[0].length; j++){
				if(maze[i][j] == 7) System.out.print("x ");
				else System.out.print(maze[i][j] + " ");
			}
			System.out.println();
		}

		//Print out the path's coordinates
		System.out.print("Path: ");
		for(int i = 0; i < path.size(); i++){
			System.out.print("(" + path.get(i) + ") ");
		}
		System.out.println("(" + endY + "," + endX + ")");
	}
}
