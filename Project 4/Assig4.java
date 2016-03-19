import java.util.*;
import java.io.*;

public class Assig4{
	public static void main(String[] args){
		try{
			File mazeFile = new File(args[0]);
			Scanner mazeReader = new Scanner(mazeFile);

			int height = -1;
			int width = -1;
			int startX = -1;
			int startY = -1;
			int[][] maze;

			for(int i = 0; mazeReader.hasNextInt(); i++){
				int value = mazeReader.nextInt();

				if(i == 0) height = value;
				else if(i == 1){
					width = value;
					maze = new int[height][width];
				}
				else if(i == 2) startX = value;
				else if(i == 3) startY = value;
				//else if(i >= 4 && maze != null) maze[i%height][i%width] = value;
			}

			maze = new int[height][width];

			for(int j = 0; j < maze.length; j++){
				for(int k = 0; k < maze[0].length; k++){
					int value = -1;
					if(mazeReader.hasNextInt()) value = mazeReader.nextInt();
					maze[j][k] = value;
					System.out.print(maze[j][k] + " ");
				}
				System.out.println();
			}

			System.out.printf("height: %d, width: %d, startX: %d, startY: %d\n", height, width, startX, startY);

			mazeReader.close();

			//traverseMaze(startX, startY, width, height, maze);
		} catch(Exception e){
			System.out.println("ERROR");
		}
	}

	public static boolean traverseMaze(int currX, int currY, int width, int height, int[][] maze){
		if(maze[currY][currX] == 2){
			System.out.println("SOLUTION FOUND");
			return true;
		} else{
			System.out.printf("currX: %d, currY: %d, width: %d, height: %d\n", currX, currY, width, height);
			if(validMove(maze, currX, currY-1)){
				System.out.println("1");
				maze[currY-1][currX] = -1;
				for(int j = 0; j < height; j++){
					for(int k = 0; k < width; k++){
						System.out.println(maze[j][k] + " ");
					}
					System.out.println();
				}
				return traverseMaze(currX, currY-1, width, height, maze);
			}
			if(validMove(maze, currX, currY+1) && !validMove(maze, currX, currY-1)){
				System.out.println("2");
				maze[currY+1][currX] = -1;
				for(int j = 0; j < height; j++){
					for(int k = 0; k < width; k++){
						System.out.println(maze[j][k] + " ");
					}
					System.out.println();
				}
				return traverseMaze(currX, currY+1, width, height, maze);
			}
			/*if(validMove(maze, currX-1, currY) && !validMove(maze, currX, currY+1)){
				System.out.println("3");
				return traverseMaze(currX-1, currY, width, height, maze);
			}
			if(validMove(maze, currX+1, currY) && !validMove(maze, currX-1, currY)){
				System.out.println("4");
				return traverseMaze(currX+1, currY, width, height, maze);
			}*/

			return false;
		}
	}

	public static boolean validMove(int[][] maze, int x, int y){
		//System.out.printf("x: %d, y: %d\n", x, y);
		if(y >= maze.length || x >= maze[0].length || y < 0 || x < 0) return false;
		else if(maze[y][x] == 1 && maze[y][x] != -1) return false;
		else return true;
	}
}
