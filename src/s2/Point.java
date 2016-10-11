package s2;

import java.util.Comparator;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Out;
import edu.princeton.cs.algs4.StdDraw;

/*************************************************************************
 * Compilation: javac Point.java Execution: Dependencies: StdDraw.java
 *
 * Description: An immutable data type for points in the plane.
 *
 * @author Magnus M. Halldorsson, email: mmh@ru.is
 *************************************************************************/
public class Point implements Comparable<Point> {

    public final int x, y;

    // compare points by slope
    public final Comparator<Point> SLOPE_ORDER = new Comparator<Point>(){
    	
		public int compare(Point p0, Point p1) {
		// TODO Auto-generated method stub

			
			double s0 = slopeTo(p0);
			double s1 = slopeTo(p1);
			 
			if(s0 < s1) 
			{
				return -1;
			}
			else if(s0 > s1)
			{
				return 1;
			}
			else
			{
				return 0;
			}
			
		}
    };
    // create the point (x, y)
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // plot this point to standard drawing
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    // draw line between this point and that point to standard drawing
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // slope between this point and that point
    public double slopeTo(Point that) {
        // TODO: Implement this
    	
    	double dy = that.y - this.y;
    	double dx = that.x - this.x;
    	
    	if((this.y == that.y) && (this.x == that.x))
    	{
    		return Double.NEGATIVE_INFINITY;
    	}
    	else if(dx == 0)
    	{
    		return Double.POSITIVE_INFINITY;
    	}
    	else if(dy == 0)
    	{
    		return 0;
    	} 
    	else 
    	{
    		return dy/dx;
    	}
    }

    /**
     * Is this point lexicographically smaller than that one? comparing
     * y-coordinates and breaking ties by x-coordinates
     */
    public int compareTo(Point that) {
        // TODO: Implement this
    	if(this.y < that.y || (this.y == that.y && this.x < that.x))
    	{
    		return -1;
    	}
    	else if((this.y == that.y) && (this.x == that.x))
    	{
    		return 0;
    	}
    	
    	return 1;
    }
    // return string representation of this point
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    public static void main(String[] args) {
        /*
         * Do not modify
         */
        In in = new In();
        Out out = new Out();
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt(), y = in.readInt();
            points[i] = new Point(x, y);
        }
        out.printf("Testing slopeTo method...\n");
        for (int i = 1; i < points.length; i++) {
            out.println(points[i].slopeTo(points[i - 1]));
        }
        out.printf("Testing compareTo method...\n");
        for (int i = 1; i < points.length; i++) {
            out.println(points[i].compareTo(points[i - 1]));
        }
        out.printf("Testing SLOPE_ORDER comparator...\n");
        for (int i = 2; i < points.length; i++) {
            out.println(points[i].SLOPE_ORDER.compare(points[i - 1],
                    points[i - 2]));
        }
    }
}

