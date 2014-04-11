package gov.frb.ma.msu.toC;
import gov.frb.ma.msu.modelEZCommon.Model;
import gov.frb.ma.msu.modelEZCommon.Node;
import gov.frb.ma.msu.modelEZCommon.SumNode;

import java.io.*;

public class ProductNodeToC extends Node 
{
    Node Multiplicand1;
    Node Multiplicand2;

  public ProductNodeToC(Node n1, Node n2) {
    Multiplicand1 = n1;
    Multiplicand2 = n2;
  }
  
  public Node CopySubtree() {
    ProductNodeToC pn = new ProductNodeToC(Multiplicand1.CopySubtree(),
				     Multiplicand2.CopySubtree());
    return pn; 
  }

  public Node ExpandSubtree() {
    Node left;
    Node right;
    Node n1;
    Node n2;
    ProductNodeToC pn1;
    ProductNodeToC pn2;
    ProductNodeToC pn3;
    ProductNodeToC pn4;
    SumNode sn1;
    SumNode sn2;
    SumNode sn3;
    Multiplicand1 = Multiplicand1.ExpandSubtree();
    Multiplicand2 = Multiplicand2.ExpandSubtree();
    left = Multiplicand1;
    right = Multiplicand2;

    if ((left instanceof SumNode) && 
    	!(right instanceof SumNode))
      {
	pn1 = new ProductNodeToC(((SumNode)left).getSummand1(), right);
	pn2 = new ProductNodeToC(((SumNode)left).getSummand2(), right.CopySubtree());
	n1 = pn1.ExpandSubtree();
	n2 = pn2.ExpandSubtree();
	sn1 = new SumNode(n1, n2);
	return sn1;
      }
    else if (!(left instanceof SumNode) &&
	     (right instanceof SumNode))
      {
 	pn1 = new ProductNodeToC(left, ((SumNode)right).getSummand1());
 	pn2 = new ProductNodeToC(left.CopySubtree(), 
			      ((SumNode)right).getSummand2());
	n1 = pn1.ExpandSubtree();
	n2 = pn2.ExpandSubtree();
	sn1 = new SumNode(n1, n2);
 	return sn1;
      }
    else if ((left instanceof SumNode) &&
	     (right instanceof SumNode))
    {
      pn1 = new ProductNodeToC(((SumNode)left).getSummand1(),
			    ((SumNode)right).getSummand1());
      pn2 = new ProductNodeToC(((SumNode)left).getSummand1().CopySubtree(),
			    ((SumNode)right).getSummand2());
      pn3 = new ProductNodeToC(((SumNode)left).getSummand2(),
			    ((SumNode)right).getSummand1().CopySubtree());
      pn4 = new ProductNodeToC(((SumNode)left).getSummand2().CopySubtree(), 
			    ((SumNode)right).getSummand2().CopySubtree());
      sn1 = new SumNode(pn1, pn2);
      sn2 = new SumNode(pn3, pn4);
      n1 = sn1.ExpandSubtree();
      n2 = sn2.ExpandSubtree();
      sn3 = new SumNode(n1, n2);
      return sn3;
    }
    else  // neither left nor right is a SumNode
      {
 	Multiplicand1 = Multiplicand1.ExpandSubtree();
 	Multiplicand2 = Multiplicand2.ExpandSubtree();
 	return this;
      }
  } // end of ExpandSubtree()

  public int CountVariables() {
    return (Multiplicand1.CountVariables() +
	    Multiplicand2.CountVariables());
  }

  public Node FindVariable() {
    Node n = Multiplicand1.FindVariable();
    if (n != null)
      return n;
    else
      return Multiplicand2.FindVariable();
  }

  public void PrintGMatrixEntries(Model m, int eqno, int side,
				  PrintStream pout) {
    int eqnoPlus1;
    int index;
    int indexPlus1;
    Node term;
    VariableNodeToC vTerm;
    term = FindVariable();
    if (term == null) {
      eqnoPlus1 = eqno + 1;
      System.err.println("Error in equation " + eqnoPlus1 +
			 ": No variable in term.");
      System.exit(1);
    }
    vTerm = (VariableNodeToC) term;
    if ((vTerm.Period <= 0) && (vTerm.ELag == AMAtoC.No)) {
      index = ((vTerm.Period + m.getNLag()) * m.getNEq() +
	       m.FindVariableIndex(vTerm.Name))
	* m.getNEq() + eqno;
      indexPlus1 = index + 1;
      pout.print("  g[" + (indexPlus1-1) + "] = g[" + (indexPlus1-1) + "]");
      if (side == Model.Right_Side)
	pout.print(" - ");
      else
	pout.print(" + ");
      PrintTerm(pout);
      pout.print(";\n");
    }
  }

  public void PrintHMatrixEntries(Model m, int eqno, int side,
				  PrintStream pout) {
    int eqnoPlus1;
    int index;
    int indexPlus1;
    Node term;
    VariableNodeToC vTerm;
    term = FindVariable();
    if (term == null) {
      eqnoPlus1 = eqno + 1;
      System.err.println("Error in equation " + eqnoPlus1 +
			 ": No variable in term.");
      System.exit(1);
    }
    vTerm = (VariableNodeToC) term;
    if ((vTerm.Period > 0) || (vTerm.ELag == AMAtoC.Yes)) {
      index = ((vTerm.Period + m.getNLag()) * m.getNEq() +
	       m.FindVariableIndex(vTerm.Name))
	* m.getNEq() + eqno;
      indexPlus1 = index + 1;
      pout.print("  h[" + (indexPlus1-1) + "] = h[" + (indexPlus1-1) + "]");
      if (side == Model.Right_Side)
	pout.print(" - ");
      else
	pout.print(" + ");
      PrintTerm(pout);
      pout.print(";\n");
    }
  }
  
  public void PrintSubtree() {
    System.out.print("(");
    Multiplicand1.PrintSubtree();
    System.out.print(" * ");
    Multiplicand2.PrintSubtree();
    System.out.print(")");
  }

  public void PrintTerm(PrintStream pout) {
    pout.print("(");
    Multiplicand1.PrintTerm(pout);
    pout.print("*");
    Multiplicand2.PrintTerm(pout);
    pout.print(")");
  }

  public int PowerErrorCheck() {
    return (Multiplicand1.PowerErrorCheck() +
	    Multiplicand2.PowerErrorCheck());
  }

  public int ProductErrorCheck() {
    // Return sum of subtree values.
    return (Multiplicand1.ProductErrorCheck() +
	    Multiplicand2.ProductErrorCheck());
  }

} // class ProductNode

