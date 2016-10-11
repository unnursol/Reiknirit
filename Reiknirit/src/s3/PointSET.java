package s3;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Out;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

public class PointSET {
	
	Set<Point2D> set;
	// construct an empty set of points
	public PointSET ()
	{
		set = new TreeSet<Point2D>();
	}
	// is the set empty ?
	public boolean isEmpty ()
	{
		return set.isEmpty();
	}
	// number of points in the set
	public int size ()
	{
		return set.size();
	}

	// add the point p to the set (if it is not already in the set )
	public void insert ( Point2D p)
	{
		if(!contains(p)){
			set.add(p);
		}
	}
	
	// does the set contain the point p?
	public boolean contains ( Point2D p)
	{
		return set.contains(p);	
	}

	// draw all of the points to standard draw
	public void draw ()
	{
		Iterator<Point2D> i = set.iterator();
		while(i.hasNext())
			i.next().draw();
	}

	// all points in the set that are inside the rectangle
	public Iterable < Point2D > range ( RectHV rect )
	{
		Set<Point2D> subSet = new TreeSet<Point2D>();
		Iterator<Point2D> i = set.iterator();
		while(i.hasNext()){
			Point2D subPoint = i.next();
			if(rect.contains(subPoint)){
				subSet.add(subPoint);
			}
		}
		
		return subSet;		
	}
	
	// a nearest neighbor in the set to p; null if set is empty
	public Point2D nearest ( Point2D p)
	{
		if(isEmpty())
		{
			return null;
		}
		else
		{
			Iterator<Point2D> i = set.iterator();
			Point2D nearestPoint = i.next();
			double nearestDist = nearestPoint.distanceSquaredTo(p);
			
			while(i.hasNext()){
				Point2D tempPoint = i.next();
				double dist = tempPoint.distanceSquaredTo(p);
				if(dist < nearestDist){
					nearestDist = dist;
					nearestPoint = tempPoint;
				}
			}
			return nearestPoint;
		}
		
	}
	
    public static void main(String[] args) {
        In in = new In();
        Out out = new Out();
        int nrOfRecangles = in.readInt();
        int nrOfPointsCont = in.readInt();
        int nrOfPointsNear = in.readInt();
        RectHV[] rectangles = new RectHV[nrOfRecangles];
        Point2D[] pointsCont = new Point2D[nrOfPointsCont];
        Point2D[] pointsNear = new Point2D[nrOfPointsNear];
        for (int i = 0; i < nrOfRecangles; i++) {
            rectangles[i] = new RectHV(in.readDouble(), in.readDouble(),
                    in.readDouble(), in.readDouble());
        }
        for (int i = 0; i < nrOfPointsCont; i++) {
            pointsCont[i] = new Point2D(in.readDouble(), in.readDouble());
        }
        for (int i = 0; i < nrOfPointsNear; i++) {
            pointsNear[i] = new Point2D(in.readDouble(), in.readDouble());
        }
        PointSET set = new PointSET();
        for (int i = 0; !in.isEmpty(); i++) {
            double x = in.readDouble(), y = in.readDouble();
            set.insert(new Point2D(x, y));
        }
        for (int i = 0; i < nrOfRecangles; i++) {
            // Query on rectangle i, sort the result, and print
            Iterable<Point2D> ptset = set.range(rectangles[i]);
            int ptcount = 0;
            for (Point2D p : ptset)
                ptcount++;
            Point2D[] ptarr = new Point2D[ptcount];
            int j = 0;
            for (Point2D p : ptset) {
                ptarr[j] = p;
                j++;
            }
            Arrays.sort(ptarr);
            out.println("Inside rectangle " + (i + 1) + ":");
            for (j = 0; j < ptcount; j++)
                out.println(ptarr[j]);
        }
        out.println("Contain test:");
        for (int i = 0; i < nrOfPointsCont; i++) {
            out.println((i + 1) + ": " + set.contains(pointsCont[i]));
        }

        out.println("Nearest test:");
        for (int i = 0; i < nrOfPointsNear; i++) {
            out.println((i + 1) + ": " + set.nearest(pointsNear[i]));
        }

        out.println();
    }
}
