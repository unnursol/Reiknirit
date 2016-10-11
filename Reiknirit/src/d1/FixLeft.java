package d1;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class FixLeft {
	public static void main(String[] args) {
		Stack<String> ops = new Stack<String>();
		Stack<String> vals = new Stack<String>();
		while (!StdIn.isEmpty())
		{ // Read token, push if operator.
			String s = StdIn.readString();
			if (s.equals("+")) ops.push(s);
			else if (s.equals("-")) ops.push(s);
			else if (s.equals("*")) ops.push(s);
			else if (s.equals("/")) ops.push(s);
			else if (s.equals("sqrt")) ops.push(s);
			else if (s.equals(")"))
			{ // Pop, evaluate, and push result if token is ")".
				String theString = ")";
				theString = vals.pop() + " " + theString;
				theString = ops.pop() + " " + theString;
				theString = vals.pop() + " " + theString;
				theString = "( " + theString;
				
				vals.push(theString);
			} // Token not operator or paren: push double value.
			else vals.push(s);
		}
		
		StdOut.println(vals.pop());
	}
}
