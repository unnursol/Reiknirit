package s2;
import java.util.Arrays;

import edu.princeton.cs.algs4.*;

public class Brute {

	public static void main(String[] args) {
        In in = new In();
        Out out = new Out();
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt(), y = in.readInt();
            points[i] = new Point(x, y);
        }
        
        Stopwatch stopwatch = new Stopwatch();
        Arrays.sort(points);
        for(int i = 0; i < n; i++)
        {
            for(int j = i+1; j < n; j++)
            {
                for(int k = j+1; k < n; k++)
                {
                	if(points[i].slopeTo(points[j]) == points[i].slopeTo(points[k]))
                    {
                		for(int l = k+1; l < n; l++)
                		{
                			if(points[i].slopeTo(points[l]) == points[i].slopeTo(points[k]))
                			{
                				//System.out.println(points[i].toString() + " -> " + points[j].toString() + " -> " + points[k].toString() + " -> " + points[l].toString());
                			}
                		}
                    }
                }
            }
        }
	    System.out.printf("Total time: " + stopwatch.elapsedTime());
	}

}