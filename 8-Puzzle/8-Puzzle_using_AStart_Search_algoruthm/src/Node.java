import java.util.ArrayList;
import java.util.Iterator;


public class Node {
	
		Node parent;
		ArrayList<Node>children=new ArrayList<>();
		int h;                       //f(n)=g(n)+h(n)  for A* algorithm
		int g;
		int f=h+g;
		int[][] puzzle=new int[3][3];
		int[][] goal=new int[3][3];
			
		Node() {
			setGoal();
			parent=null;
			children.isEmpty();
			g=0;
			h=heuristicValue();
			f=g+h;
		}
		
		void setGoal()
		{
			goal[0][0]=1;
			goal[0][1]=2;
			goal[0][2]=3;
			goal[1][0]=4;
			goal[1][1]=5;
			goal[1][2]=6;
			goal[2][0]=7;
			goal[2][1]=8;
			goal[2][2]=0;
			
		}
		
		int heuristicValue()
		{
			int count=0;
			for(int i=0;i<3;i++)
			{
				for(int j=0;j<3;j++)
				{
					if(puzzle[i][j]!=goal[i][j])
					{
						count++;
					}
				}
			}
			return count;
		}
		
		void display()
		{
			for(int i=0;i<3;i++)
			{
				for(int j=0;j<3;j++)
				{
					System.out.print(puzzle[i][j]);
				}
				System.out.println();
			}
			System.out.println("");
			System.out.println("h="+h);
			System.out.println("g="+g);
			System.out.println("f="+f);
		}
		
		
		
		void createPossibleMoves()
		{
			if(this.h!=0)
			{
				moveToRight();
				moveToLeft();
				moveToUp();
				moveToDown();
			}
			
		}
		
		int[] locationOfZero() 
		{
			int[] l=new int[2];
			for(int i=0;i<3;i++)
			{
				for(int j=0;j<3;j++)
				{
					if(puzzle[i][j]==0)
					{
						l[0]=i;
						l[1]=j;
					}
				}
			}
			return l;
		}
		
		void moveToRight()
		{
			int[] zero=locationOfZero();
			int new_i,new_j;
			if(zero[1]!=2)
			{
				new_i=zero[0];
				new_j=zero[1]+1;
				createChild(zero[0],zero[1],new_i,new_j);
				
			}
			
			
			
		}
		
		void moveToLeft()
		{
			int[] zero=locationOfZero();
			int new_i,new_j;
			if(zero[1]!=0)
			{
				new_i=zero[0];
				new_j=zero[1]-1;
				createChild(zero[0],zero[1],new_i,new_j);
			}
			
		}
		
		void moveToUp()
		{
			int[] zero=locationOfZero();
			int new_i,new_j;
			if(zero[0]!=0)
			{
				new_i=zero[0]-1;
				new_j=zero[1];
				createChild(zero[0],zero[1],new_i,new_j);
			}
			
		}
		void moveToDown()
		{
			int[] zero=locationOfZero();
			int new_i,new_j;
			if(zero[0]!=2)
			{
				new_i=zero[0]+1;
				new_j=zero[1];
				createChild(zero[0],zero[1],new_i,new_j);
			}
		}
		
		void createChild(int old_i,int old_j,int new_i,int new_j)
		{
			Node child=new Node();
			
			
			for(int i=0;i<3;i++)                             //here ve set child puzzle as current puzzle 
			{
				for(int j=0;j<3;j++)
				{
					child.puzzle[i][j]=puzzle[i][j];
				}
					
			}
			
			int t;                                           //here we swap zero and the move location
			t=child.puzzle[old_i][old_j];
			child.puzzle[old_i][old_j]=child.puzzle[new_i][new_j];
			child.puzzle[new_i][new_j]=t;
			
			child.g=this.g+1;                              //here we set g value which is depth of node
			child.parent=this;
			child.h=child.heuristicValue();
			child.f=child.g+child.h;
			children.add(child);                           // we add child to children list
		
		
		}
		
		void displayChilds()
		{
			Iterator<Node> itr=children.iterator();
			while(itr.hasNext())
			{
				itr.next().display();
			}
		}

}


