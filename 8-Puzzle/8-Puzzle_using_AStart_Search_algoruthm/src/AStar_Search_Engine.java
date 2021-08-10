import java.util.*;

public class AStar_Search_Engine {
	
	ArrayList<Node> openList=new ArrayList<>();
	ArrayList<Node> closedList=new ArrayList<>();
		
	void starter(int[][] p) 
	{
		Node root=new Node();                       //  create "ROOT" node
		
		for(int i=0;i<3;i++)                        //initialize puzzle values  for root node
		{
			for(int j=0;j<3;j++)
			{
				root.puzzle[i][j]=p[i][j];
			}
		}
		
		root.h=root.heuristicValue();           // update h value for root node
		root.f=root.h+root.g;                   // update f value for root node
		
		openList.add(root);
		search();
	}
	
	
	void search()
	{
		
		while(!openList.isEmpty())
		{
			// 1) Find the node with the lowest F cost in the open list and place it in the closed list
			Node currentNode=new Node();
			currentNode.f=9999;
			for(int i=0;i<openList.size();i++)
			{
				if(openList.get(i).f<currentNode.f)
				{
					currentNode=openList.get(i);
				}
			}
			openList.remove(currentNode);
			closedList.add(currentNode);
			
			if(currentNode.h==0)
			{
				System.out.println("The goal node is Found");
				displaySolutionPath(currentNode);
				break;
			}
			else 
			{			
				//2)   Expand this node and for the adjacent nodes to this node
				currentNode.createPossibleMoves();
				
				// i)   If they are on the closed list, ignore.
				// ii)  If not on the open list, add to open list; store this child node 
				// iii) If on the open list, compare the G costs of this node and the old node.
				//       if old node G is less then this node then
				//                   *update parent-of-this-node as parent-of-old-node
				//                   *update f,g,h value of this-node as old-node 
				// !!!DONT FORGET, here we are comparing PUZZLE values not the node values
				
				for(int i=0;i<currentNode.children.size();i++)
				{
					Node child=currentNode.children.get(i);
					int isInClosedList=0;
					int isInOpenList=0;
					
					for(int j=0;j<closedList.size();j++)
					{
						if(child.puzzle.equals(closedList.get(j).puzzle))
						{
							isInClosedList=1;
						}
					}
					
					if(isInClosedList==0)
					{
						for(int j=0;j<openList.size();j++)
						{
							if(child.puzzle.equals(openList.get(j).puzzle))
							{
								isInOpenList=1;
								child=openList.get(j);                // if in the openList we update parent g,h,f values 
							}
						}
						if(isInOpenList==0)
						{
							openList.add(child);                     // if nor in the openList then add child in openList
						}
					}
				}
			}
			//System.out.println("Size of openList = "+openList.size());
			//System.out.println("Size of closedList = "+closedList.size());
		}
	}


	void displaySolutionPath(Node node)
	{
		while(true)
		{
			System.out.println("-------Step ="+node.g+" ---------");
			node.display();
			if(node.parent==null)break;
			else node=node.parent;
		}
	}
	
	
}
