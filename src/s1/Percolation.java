package s1;

import edu.princeton.cs.algs4.QuickFindUF;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	
	private boolean worldOpen[][];
	private int openSites = 0;
	private int top = 0;
	private int bottom;
	private int size;
	private QuickFindUF uf;

	public Percolation(int N) // create N-by-N grid, with all sites initially blocked
	{	
		if(N < 0)
			throw new java.lang.IllegalArgumentException();
		bottom = 1 + N * N;
		uf = new QuickFindUF(2 + N * N);
		worldOpen = new boolean[N][N];
		size = N;	
	}
	
	private int idFinder(int row, int col)
	{
		return ((row * size) + col) + 1; // our world starts with 1 to N*N
	}
	
	public void open(int row, int col) // open the site (row, col) if it is not open already
	{	
		indexValidate(row, col);
		int id = idFinder(row, col);
		if(!worldOpen[row][col])
		{
			if(col != 0)
			{
				if(worldOpen[row][col-1])
				{
					uf.union(id, idFinder(row, col-1));
				}
			}		
			if(col != size-1)
			{
				if(worldOpen[row][col+1])
				{
					uf.union(id, idFinder(row, col+1));
				}
			}
			if(row != 0)
			{
				if(worldOpen[row-1][col])
				{
					uf.union(id, idFinder(row-1, col));
				}			
			}
			else
			{
				uf.union(id, top);
			}	
			if(row != size-1)
			{
				if(worldOpen[row+1][col])
				{
					uf.union(id, idFinder(row+1, col));
				}			
			}
			else
			{
				uf.union(id, bottom);
			}
			openSites++;
			worldOpen[row][col] = true;
		}
	}
	
	public boolean isOpen(int row, int col) // is the site (row, col) open?
	{
		indexValidate(row, col);
		return worldOpen[row][col];
	}
	
	public boolean isFull(int row, int col) // is the site (row, col) full?b
	{
		indexValidate(row, col);
		return uf.connected(idFinder(row,col), top);
	}
	
	public int numberOfOpenSites() // number of open sites
	{
		return openSites;
	}
	
	public boolean percolates() // does the system percolate?
	{
		return uf.connected(top, bottom);
	}
	
	private void indexValidate(int row, int col)
	{
		if (row < 0 || col < 0 || row >= size || col >= size)
			throw new java.lang.IndexOutOfBoundsException();
	}
	
	public static void main(String[] args) // unit testing (required)
	{
		
	}

}