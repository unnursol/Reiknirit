package s2;

import java.util.Arrays;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Out;
import edu.princeton.cs.algs4.Stopwatch;

public class Fast2 {
	
	public static void main(String[] args) {
        In in = new In();
        Out out = new Out();
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt(), y = in.readInt();
            points[i] = new Point(x, y);
        }
        
		for (int i = 0; i < n; i++)
		{
			Point p1 = points[i];
			Point[] p2 = new Point[n-1];
			for (int j = 0; j < points.length; j++)
			{
				if (j < i) p2[j] = points[j];
				if (j > i) p2[j-1] = points[j];
			}
			Arrays.sort(p2, p1.SLOPE_ORDER);
			
			for (int j = 0; j < p2.length-3;)
			{
				int count = 1;
				int index = j;
				double tempSlope = p1.slopeTo(p2[j]);
				while( p1.slopeTo(p2[index]) == tempSlope )
				{
					count ++;
					index ++;
				}
				if ( count > 3 )
				{
					System.out.println();
					
					System.out.print(p1.toString());
					for ( int k = j; k < index ; k++)
					{
						System.out.print("->"+p2[k].toString());
					}
					System.out.println();
				}
				j = index;
			}
		}
	}
}