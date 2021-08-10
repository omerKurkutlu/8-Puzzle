/*This program ha written by "Omer KURKUTLU"
 * it is used for solving 8-puzzle with A* search algorith
 *input can be
 *               1 2 3 
 *               0 4 5 
 *               6 7 8
 *goal is
 *               1 2 3 
 *               4 5 6 
 *               7 8 0
 * 
 *This program tell us which move we should do to get goal puzzle
 *
 *!!!!!!It is not possible to solve an instance of 8 puzzle if number of inversions is odd in the input state!!!!
 *  this page explain solvable or not              https://www.geeksforgeeks.org/check-instance-8-puzzle-solvable/
 *               
 * */

import java.util.Scanner;



public class Main {

	public static void main(String[] args) {
		
		Scanner scan=new Scanner(System.in);
		AStar_Search_Engine start=new AStar_Search_Engine();
		int [][] puzzle=new int[3][3];
		System.out.println("Enter 3*3 puzzle");
		for(int i=0;i<3;i++)
		{
			for(int j=0;j<3;j++)
			{
				puzzle[i][j]=scan.nextInt();
			}
		}
		start.starter(puzzle);
	}

}
