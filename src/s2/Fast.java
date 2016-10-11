package s2;

import java.util.Arrays;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Out;

public class Fast {
	
	public static void main(String[] args) {
        In in = new In();
        Out out = new Out();
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt(), y = in.readInt();
            points[i] = new Point(x, y);
        }
       
        for(int i = 0; i < n; i++)
        {
            Arrays.sort(points);
            int counter = 2;
        	Point p = points[i];
        	Arrays.sort(points, i, n, p.SLOPE_ORDER);
        	
        	for(int j = i+1; j < n; j++)
        	{
        		if(points[j] != p)
        		{
        			if(p.SLOPE_ORDER.compare(points[j], points[j-1]) == 0)
        			{
        				counter++;
        				
        		       	if(points[j] == points[n-1] && counter > 3){
        		       		
        		       		System.out.print(p.toString()); 
  
        					for(int k = j-counter+2; k < j+1; k++)
        					{
        						System.out.print(" -> " + points[k].toString());
        					}
        					System.out.println();
        		       	}
        			}
        			else
        			{
        				if(counter > 3)
        				{
        					System.out.print(p.toString() + " -> ");

        					for(int k = j-counter+1; k < j; k++)
        					{
        						System.out.print(points[k].toString());
        							if(k < j-1)
        								System.out.print(" -> ");
        					}
        					System.out.println();
        				}
        				counter = 2;
        			}
        		}
        	}	
        }
	}
}
