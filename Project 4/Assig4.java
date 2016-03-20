import java.util.*;
import java.io.*;

public class Assig4{
	private static int solutions = 0;

	public static void main(String[] args){
		try{
			File mazeFile = new File(args[0]);
			Scanner mazeReader = new Scanner(mazeFile);

			int height = -1;
			int width = -1;
			int startX = -1;
			int startY = -1;
			int[][] maze = null;

			for(int i = 0; mazeReader.hasNextInt(); i++){
				//int value = mazeReader.nextInt();

				height = mazeReader.nextInt();
				width = mazeReader.nextInt();
				maze = new int[height][width];

				for(int j = 0; j < maze.length; j++){
					for(int k = 0; k < maze[0].length; k++){
						int value2 = -1;
						if(k == 0 && j == 0){
							if(mazeReader.hasNextInt()) value2 = mazeReader.nextInt();
							startX = value2;
							if(mazeReader.hasNextInt()) value2 = mazeReader.nextInt();
							startY = value2;
							//System.out.printf("height: %d, width: %d, startX: %d, startY: %d\n", height, width, startX, startY);
						}
						if(mazeReader.hasNextInt()) value2 = mazeReader.nextInt();
						maze[j][k] = value2;
					}
				}

				System.out.println("----------------------------------\nInitial board: \n");
				for(int l = 0; l < maze.length; l++){
					for(int m = 0; m < maze[0].length; m++){
						if(maze[l][m] == 7) System.out.print("0 ");
						else System.out.print(maze[l][m] + " ");
					}
					System.out.println();
				}
				System.out.printf("\nStart position: (%d, %d)\n\n", startX, startY);

				traverseMaze(startY, startX, width, height, maze, startX, startY, new ArrayList<String>());

				solutions = 0;
			}

			mazeReader.close();

			//printOutput(maze, startX, startY);
		} catch(Exception e){
			System.out.println("ERROR");
		}
	}

	public static boolean traverseMaze(int currX, int currY, int width, int height, int[][] maze, int startX, int startY, ArrayList<String> path){
		if(maze[currY][currX] == 2){
			//maze[currY][currX] = 2;
			//return true;
			return false;
		} else{
			boolean found = false;
			//System.out.printf("\ncurrX: %d, currY: %d, width: %d, height: %d\n", currX, currY, width, height);
			// for(int j = 0; j < height; j++){
			// 	for(int k = 0; k < width; k++){
			// 		System.out.print(maze[j][k] + " ");
			// 	}
			// 	System.out.println();
			// }
			// System.out.println();
			maze[currY][currX] = 7;
			// for(int j = 0; j < height; j++){
			// 	for(int k = 0; k < width; k++){
			// 		System.out.print(maze[j][k] + " ");
			// 	}
			// 	System.out.println();
			// }
			// System.out.println();

			if(currX > 0){
				if(maze[currY][currX-1] == 2){
					printOutput(maze, startX, startY, path);
					solutions++;
					//return true;
				}
			}
			if(currY > 0){
				if(maze[currY-1][currX] == 2){
					printOutput(maze, startX, startY, path);
					solutions++;
					//return true;
				}
			}
			if(currX < maze[0].length-1){
				if(maze[currY][currX+1] == 2){
					printOutput(maze, startX, startY, path);
					solutions++;
					//return true;
				}
			}
			if(currY < maze.length-1){
				if(maze[currY+1][currX] == 2){
					printOutput(maze, startX, startY, path);
					solutions++;
					//return true;
				}
			}

			if(validMove(maze, currX, currY-1) && !found){
				if(maze[currY-1][currX] != 7){
					// for(int j = 0; j < height; j++){
					// 	for(int k = 0; k < width; k++){
					// 		System.out.print(maze[j][k] + " ");
					// 	}
					// 	System.out.println();
					// }
					// System.out.println();
					path.add("(" + (currY-1) + ", " + currX + ")");
					System.out.printf("Added (%d, %d) 1\n", currY, currX);
					found = traverseMaze(currX, currY-1, width, height, maze, startX, startY, path);
					// System.out.println("RETURNED HERE");
				}
			}
			if(validMove(maze, currX, currY+1) && !found){
				if(maze[currY+1][currX] != 7){
					// for(int j = 0; j < height; j++){
					// 	for(int k = 0; k < width; k++){
					// 		System.out.print(maze[j][k] + " ");
					// 	}
					// 	System.out.println();
					// }
					// System.out.println();
					path.add("(" + (currY+1) + ", " + currX + ")");
					System.out.printf("Added (%d, %d) 2\n", currY, currX);
					found = traverseMaze(currX, currY+1, width, height, maze, startX, startY, path);
				}
			}
			if(validMove(maze, currX-1, currY) && !found){
				if(maze[currY][currX-1] != 7){
					// for(int j = 0; j < height; j++){
					// 	for(int k = 0; k < width; k++){
					// 		System.out.print(maze[j][k] + " ");
					// 	}
					// 	System.out.println();
					// }
					// System.out.println();
					path.add("(" + currY + ", " + (currX-1) + ")");
					System.out.printf("Added (%d, %d) 3\n", currY, currX);
					found = traverseMaze(currX-1, currY, width, height, maze, startX, startY, path);
				}
			}
			if(validMove(maze, currX+1, currY) && !found){
				if(maze[currY][currX+1] != 7){
					// for(int j = 0; j < height; j++){
					// 	for(int k = 0; k < width; k++){
					// 		System.out.print(maze[j][k] + " ");
					// 	}
					// 	System.out.println();
					// }
					// System.out.println();
					path.add("(" + currY + ", " + (currX+1) + ")");
					System.out.printf("Added (%d, %d) 4\n", currY, currX);
					found = traverseMaze(currX+1, currY, width, height, maze, startX, startY, path);
				}
			}

			// System.out.printf("maze[%d][%d]: %d is now 0, startX: %d, startY: %d\n", currY, currX, maze[currY][currX], startX, startY);
			// if(!found) System.out.println("NOT found");
			// else System.out.println("found");
			if(!found && (currX != startX || currY != startY)){
				maze[currY][currX] = 0;
			}
			// for(int j = 0; j < height; j++){
			// 	for(int k = 0; k < width; k++){
			// 		System.out.print(maze[j][k] + " ");
			// 	}
			// 	System.out.println();
			// }
			// System.out.println();
			return found;
		}
	}

	public static boolean validMove(int[][] maze, int x, int y){
		if(y >= maze.length || x >= maze[0].length || y < 0 || x < 0) return false;
		else if(maze[y][x] == 1 || maze[y][x] == 7) return false;
		else return true;
	}

	public static void printOutput(int[][] maze, int startX, int startY, ArrayList<String> path){
		//ArrayList<String> path = new ArrayList<String>();
		int x = 0;
		for(int k = 0; k < maze.length; k++){
			for(int l = 0; l < maze[0].length; l++){
				if(maze[k][l] == 7 || maze[k][l] == 2){
					if(maze[k][l] == 7) System.out.print("x ");
					else if(maze[k][l] == 2) System.out.print("2 ");
					x++;
					//path.add("(" + k + ", " + l + ")");
				}
				else System.out.print(maze[k][l] + " ");
			}
			System.out.println();
		}
		System.out.println("Solution found with " + x + " segments");
		System.out.print("Path: ");
		/*int currX = startX;
		int currY = startY;
		path.add("(" + currY + ", " + currX + ")");
		for(int j = 0; j < x-1; j++){
			try{
				if(maze[currY-1][currX] == 7 || maze[currY-1][currX] == 2){
					if(maze[currY-1][currX] == 7) path.add("(" + (currY--) + ", " + currX + ")");
					else{
						path.add("(" + (currY--) + ", " + currX + ")");
						break;
					}
				} else if(maze[currY+1][currX] == 7 || maze[currY+1][currX] == 2){
					if(maze[currY+1][currX] == 7) path.add("(" + (currY++) + ", " + currX + ")");
				} else if(maze[currY][currX-1] == 7 || maze[currY][currX-1] == 2){
					path.add("(" + currY + ", " + (currX--) + ")");
				} else if(maze[currY][currX+1] == 7 || maze[currY][currX+1] == 2){
					path.add("(" + currY + ", " + (currX++) + ")");
				}
			}
		}*/
		for(int i = 0; i < path.size(); i++){
			System.out.print(path.get(i) + " ");
		}
		System.out.println("\n");
	}
}
