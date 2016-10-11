package s4;

import edu.princeton.cs.algs4.Digraph;

public class SAP {
	
	private Digraph graph;

	// constructor takes a digraph ( not necessarily a DAG )
	public SAP (Digraph G){
		graph = G;
	}
	
	// length of shortest ancestral path between v and w; -1 if no such path
	public int length ( int v , int w){
		return w;
	}
	
	// a shortest common common ancestor of v and w; -1 if no such path
	public int ancestor ( int v , int w){
		return w;
	}
		
	// length of shortest ancestral path of vertex subsets A and B ; -1 if no such path
	public int length ( Iterable < Integer > A , Iterable < Integer > B){
		return 0;		
	}
		
	// a shortest common ancestor of vertex subsets A and B; -1 if no such path
	public int ancestor ( Iterable < Integer > A , Iterable < Integer > B){
		return 0;
	}
	
	// do unit testing of this class
	public static void main ( String [] args ){
		
	}

}
