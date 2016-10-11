package s3;

import java.util.Arrays;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Out;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.StdDraw;

public class KdTree {
	
	private static class Node 
	{
		 private Point2D p; // the point
		 private RectHV rect; // the axis-aligned rectangle
		 private Node lb; // the left/bottom subtree
		 private Node rt; // the right/top subtree
	};
	
	Node root;
	int size;
    // construct an empty set of points
    public KdTree() 
    {
    	root = null;
    }

    // is the set empty?
    public boolean isEmpty() 
    {
        return root == null;
    }

    // number of points in the set
    public int size() 
    {
        return size;
    }

    // add the point p to the set (if it not already in the set)
    public void insert(Point2D p) 
    {
   		RectHV rect = new RectHV(0, 0, 1, 1);
    	root = insert(root, p, 0, rect);
    };
    
    public Node insert(Node node, Point2D p, int count, RectHV rect)
    {
    	if(node == null)
    	{
    		node = new Node();
    		node.p = p;
    		node.lb = null;
    		node.rt = null;
    		node.rect = rect;
    		size++;
    	}
    	else if(!contains(p))
    	{
	    	if(count%2 == 0)
	    	{
	    		if(node.p.x() > p.x())
	    		{
	    			RectHV r = new RectHV(rect.xmin(), rect.ymin(), node.p.x(), rect.ymax());
	    			node.lb = insert(node.lb, p, count++, r);
	    		}
	    		else
	    		{
	    			RectHV r = new RectHV(node.p.x(), rect.ymin(), rect.xmax() , rect.ymax());
	    			node.rt = insert(node.rt, p, count++, r);
	    		}
	    	}
	    	else
	    	{
	    		if
	    		(node.p.y() > p.y()){
	    			RectHV r = new RectHV(rect.xmin(), rect.ymin(), rect.xmax() , node.p.y());
	    			node.lb = insert(root.lb, p, count++, r);
	    		}
	    		else
	    		{
	    			RectHV r = new RectHV(rect.xmin(), node.p.y(), rect.xmax(), rect.ymax());
	    			node.rt = insert(node.rt, p, count++, r);
	    		}
	    	}
    	}
    	return node;
    }

    // does the set contain the point p?
    public boolean contains(Point2D p) 
    {	
    	return contains(root, p, 0);
    }
    
    public boolean contains(Node node, Point2D p, int count) 
    {
    		
	    	if(node == null)
	    	{
	    		return false;
	    	}
    		else if(node.p.equals(p))
	    	{
	    		return true;
	    	}
	    	else if(count%2 == 0)
	    	{
	    		if(node.p.x() > p.x())
	    		{
	    			return contains(node.lb, p, count++);
	    		}
	    		else
	    		{
	    			return contains(node.rt, p, count++);
	    		}
	    	}
	    	else
	    	{
	    		if(node.p.y() > p.y())
	    		{
	    			return contains(node.lb, p, count++);
	    		}
	    		else
	    		{
	    			return contains(node.rt, p, count++);
	    		}
	    	}
    }
    

    // draw all of the points to standard draw
    public void draw() 
    {
    	draw(root, 0);
    }
    
    private void draw(Node node, int count) {
        if (node == null) 
        {
            return;
        }
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.01);
        node.p.draw();
        if (count%2 == 0) 
        {
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.setPenRadius(0.001);
            StdDraw.line(node.p.x(), node.rect.ymin(), node.p.x(), node.rect.ymax());
        } 
        else 
        {
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.setPenRadius(0.001);
            StdDraw.line(node.rect.xmin(), node.p.y(), node.rect.xmax(), node.p.y());
        }
        draw(node.lb, count++);
        draw(node.rt, count++);
    }

    // all points in the set that are inside the rectangle
    public Iterable<Point2D> range(RectHV rect) {
    	SET <Point2D> rangeNumbers = new SET<Point2D>();
    	return range(root,rect,rangeNumbers);
    }
    
    public SET<Point2D> range(Node node,RectHV rect,SET<Point2D> rangeNumbers)
    {
    	if(node==null)
    	{
    		return rangeNumbers;
    	}
    	
    	if(rect.contains(node.p))
    	{
    		rangeNumbers.add(node.p);
    	}
    	
    	if(node.lb!=null&&rect.intersects(node.lb.rect))
    	{
    		rangeNumbers = range(node.lb,rect,rangeNumbers);
    	}
    	if(node.rt!=null&&rect.intersects(node.rt.rect))
    	{
    		rangeNumbers = range(node.rt,rect,rangeNumbers); 
    	}
    	return rangeNumbers;
    	
    }

    // a nearest neighbor in the set to p; null if set is empty
    public Point2D nearest(Point2D p) {
        
    	if(isEmpty())
    	{
    		return null;
    	}
    	else
    	{
	    	return nearest(p, root, root.p, Double.MAX_VALUE, 0);
    	}
    }
    
    public Point2D nearest(Point2D p, Node node, Point2D nearestNeighbor, double nearestDistance, int count)
    {
    	if(node != null)
    	{	                   
	        double distance = node.p.distanceSquaredTo(p);	  
	        Node tree1, tree2;                            
	        
	        if(distance <= nearestDistance)
	        {
	        	nearestNeighbor = node.p;
	        	nearestDistance = distance;
	        }
	        
	    	if(count%2 == 0)
	    	{
	    		if(node.p.x() > p.x())
	    		{
	    			tree1 = node.lb;
	    			tree2 = node.rt;
	    		}
	    		else
	    		{
	    			tree1 = node.rt;
	    			tree2 = node.lb;
	    		}
	    	}
	    	else
	    	{
	    		if(node.p.y() > p.y())
	    		{
	    			tree1 = node.lb;
	    			tree2 = node.rt;
	    		}
	    		else
	    		{
	    			tree1 = node.rt;
	    			tree2 = node.lb;
	    		}
	    	}
	    
			if(tree2 != null && tree2.rect.distanceSquaredTo(p) < nearestDistance)
			{
				nearestDistance = nearestNeighbor.distanceSquaredTo(p);
				nearestNeighbor = nearest(p, tree2, nearestNeighbor, nearestDistance, count++);
			}
			
	    	if(tree1 != null && tree1.rect.distanceSquaredTo(p) < nearestDistance)
			{
				nearestDistance = nearestNeighbor.distanceSquaredTo(p);
				nearestNeighbor = nearest(p, tree1, nearestNeighbor, nearestDistance, count++); 
			}
			

    	}
    		
    	return nearestNeighbor;
    }

    /*******************************************************************************
     * Test client
     ******************************************************************************/
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
        KdTree set = new KdTree();
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
        /*KdTree set = new KdTree();
        Point2D p1 = new Point2D(0.3,0.5);
        Point2D p2 = new Point2D(0.1,0.8);
        Point2D p3 = new Point2D(0.5,0.3);
        Point2D p4 = new Point2D(0.2,0.1);
    	set.insert(p1);
    	set.insert(p2);
    	set.insert(p3);
    	set.insert(p4);*/
    }
}
