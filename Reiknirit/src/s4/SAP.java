package s4;

import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class SAP {
	
	
	// ------------------------------------------ SAP CLASS IMPLEMENTATION --------------------------------------------
	
	private Digraph graph;

	// constructor takes a digraph ( not necessarily a DAG )
	public SAP (Digraph G)
	{
		graph = G;
		checkIfAcyclic(graph);
		checkIfRooted(graph);
	}
	
	// length of shortest ancestral path between v and w; -1 if no such path
	public int length ( int v , int w)
	{
		verifyInput(v);
		verifyInput(w);
		BreadthFirstDirectedPaths bfsV = new BreadthFirstDirectedPaths(graph, v);
		BreadthFirstDirectedPaths bfsW = new BreadthFirstDirectedPaths(graph, w);
		
		int shortestPath = -1;
		for(int vertex = 0; vertex < graph.V(); vertex++)
		{
			//Checking if v and w have path to the same vertex and calculating the distance.
			if (bfsV.hasPathTo(vertex) && bfsW.hasPathTo(vertex)) 
			{
				int currentDist = bfsV.distTo(vertex) + bfsW.distTo(vertex);
				
				//Checking if the current distance is shorter then the current shortest path.
				if (currentDist < shortestPath || shortestPath < 0 ) 
				{
					shortestPath = currentDist;
				}
			}
		}
		return shortestPath;
	}
	
	// a shortest common common ancestor of v and w; -1 if no such path
	public int ancestor ( int v , int w)
	{
		verifyInput(v);
		verifyInput(w);
		BreadthFirstDirectedPaths bfsV = new BreadthFirstDirectedPaths(graph, v);
		BreadthFirstDirectedPaths bfsW = new BreadthFirstDirectedPaths(graph, w);
		
		int shortestPath = -1;
		int ancestor = -1;
		for (int vertex = 0; vertex < graph.V(); vertex++) 
		{
			//Finding the shortest path as in length() but returning the vertex they are both connected to(the ancestor).
			if (bfsV.hasPathTo(vertex) && bfsW.hasPathTo(vertex)) 
			{
				int currentDist = bfsV.distTo(vertex) + bfsW.distTo(vertex);

				if (currentDist < shortestPath || shortestPath < 0 ) 
				{
					ancestor = vertex;
					shortestPath = currentDist;
				}
			}
		}	
		return ancestor;
	}
		
	// length of shortest ancestral path of vertex subsets A and B ; -1 if no such path
	public int length ( Iterable < Integer > A , Iterable < Integer > B)
	{	
		verifyInput(A);
		verifyInput(B);
		BreadthFirstDirectedPaths bfsA = new BreadthFirstDirectedPaths(graph, A);
		BreadthFirstDirectedPaths bfsB = new BreadthFirstDirectedPaths(graph, B);

		int shortestPath = -1;
		for (int vertex = 0; vertex < graph.V(); vertex++) 
		{
			if (bfsA.hasPathTo(vertex) && bfsB.hasPathTo(vertex)) 
			{
				int currentDist = bfsA.distTo(vertex) + bfsB.distTo(vertex);
				
				if (currentDist < shortestPath || shortestPath < 0 ) 
				{
					shortestPath = currentDist;
				}
			}
		}
		return shortestPath;		
	}
		
	// a shortest common ancestor of vertex subsets A and B; -1 if no such path
	public int ancestor ( Iterable < Integer > A , Iterable < Integer > B)
	{
		verifyInput(A);
		verifyInput(B);
		BreadthFirstDirectedPaths bfsA = new BreadthFirstDirectedPaths(graph, A);
		BreadthFirstDirectedPaths bfsB = new BreadthFirstDirectedPaths(graph, B);
		
		int shortestPath = -1;
		int ancestor = -1;
		for (int vertex = 0; vertex < graph.V(); vertex++) 
		{
			//Finding the shortest path as in length() but returning the vertex they are both connected to(the ancestor).
			if (bfsA.hasPathTo(vertex) && bfsB.hasPathTo(vertex)) 
			{
				int currentDist = bfsA.distTo(vertex) + bfsB.distTo(vertex);

				if (currentDist < shortestPath || shortestPath < 0 ) 
				{
					ancestor = vertex;
					shortestPath = currentDist;
				}
			}
		}	
		return ancestor;
	}
	
	//------------------------------         Help functions         ------------------------------
	
   private void verifyInput(int vertex) 
   {
	   if (vertex < 0 || vertex > graph.V() - 1) 
	   {
		  throw new java.lang.IndexOutOfBoundsException();      
	   }
   }
	   
   private void verifyInput(Iterable<Integer> vertex) 
   {
      for (Integer v : vertex) 
      {
         if (v < 0 || v > graph.V() - 1)
         {
        	 throw new java.lang.IndexOutOfBoundsException();   
         }
      }    
   }
   
   private void checkIfAcyclic(Digraph graph)
   {
	   /*
	     	L - Empty list where we put the sorted elements
			Q - Set of all nodes with no incoming edges
			while Q is non-empty do
			    remove a node n from Q
			    insert n into L
			    for each node m with an edge e from n to m do
			        remove edge e from the graph
			        if m has no other incoming edges then
			            insert m into Q
			if graph has edges then
			    output error message (graph has a cycle)
			else 
			    output message (proposed topologically sorted order: L)
			    
		 If the input graph is not a DAG then throw an IllegalArgumentException with the messageGraph is not acyclic
		 i.e. throw new IllegalArgumentException("Graph is not acyclic");

	    */
	   
   }
   
   private void checkIfRooted(Digraph graph){
	   /*
	    * 
	    */
   }
	
	// do unit testing of this class
	public static void main ( String [] args ){	
		In in = new In(args[0]);
        Digraph G = new Digraph(in);
        SAP sap = new SAP(G);

        while (!StdIn.isEmpty()) {
            int v = StdIn.readInt();
            int w = StdIn.readInt();
            int length = sap.length(v, w);
            int ancestor = sap.ancestor(v, w);
            StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
        }
	}

}
