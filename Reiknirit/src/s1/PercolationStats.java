package s1;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.Stopwatch;

public class PercolationStats {
	
	 private Percolation p;
	 private int count;
	 private double[] arr;
	
	public PercolationStats(int N, int T) // perform T independent experiments on an N-by-N grid
	{
		if (N <= 0  || T <= 0)
			throw new java.lang.IllegalArgumentException();
		
		count = T;
		arr = new double[count];
		
		for(int i = 0; i < count; i++)
		{
			p = new Percolation(N);
			int openSites = 0;
			
			while(!p.percolates())
			{
				int row = StdRandom.uniform(N); 
				int col = StdRandom.uniform(N);
				
				if(!p.isOpen(row, col))
				{
					p.open(row, col);
					openSites++;				
				}
			}
			
			arr[i]= (double)openSites/(double)(N*N);
		}
	}
	public double mean() // sample mean of percolation threshold
	{	
		return StdStats.mean(arr);
	}
	public double stddev() // sample standard deviation of percolation threshold
	{
		return StdStats.stddev(arr);
	}
	public double confidenceLow() // low endpoint of 95% confidence interval
	{	
		return mean() - ((1.96 * stddev()) / Math.sqrt(count));
	}
	public double confidenceHigh() // high endpoint of 95% confidence interval
	{
		return mean() + ((1.96 * stddev()) / Math.sqrt(count));
	}	
	
	 public static void main(String[] args) {
		    int N = 400;
		    int T = 10;

		    Stopwatch stopwatch = new Stopwatch();

		    PercolationStats perStat = new PercolationStats(N, T);

		    System.out.printf("mean                    = %f\n", perStat.mean());
		    System.out.printf("stddev                  = %f\n", perStat.stddev());
		    System.out.printf("95%% confidence interval = %f, %f\n",
		        perStat.confidenceLow(), perStat.confidenceHigh());

		    System.out.println("------");
		    System.out.printf("Total time: %f secs. (for N=%d, T=%d)",
		        stopwatch.elapsedTime(), N, T);

	}
}
