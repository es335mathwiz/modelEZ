package gov.frb.ma.msu.modelEZCommon;

import gov.frb.ma.msu.toMatlab.Model;

import java.io.*;

public class SumNode extends Node
{
  private Node Summand1;
  private Node Summand2;

  public SumNode(Node n1, Node n2) {
    setSummand1(n1);
    setSummand2(n2);
  }
  
  public Node CopySubtree() { 
    SumNode sn = new SumNode(getSummand1().CopySubtree(), getSummand2().CopySubtree());
    return sn; 
  }

  public Node ExpandSubtree() {
    setSummand1(getSummand1().ExpandSubtree());
    setSummand2(getSummand2().ExpandSubtree());
    return this;
  }

  public int CountVariables() {
    return (getSummand1().CountVariables() + getSummand2().CountVariables());
  }

  public Node FindVariable() {
    Node n = getSummand1().FindVariable();
    if (n != null)
      return n;
    else
      return getSummand2().FindVariable();
  }

  public void PrintGMatrixEntries(Model m, int eqno, int side,
				  PrintStream pout) {
    getSummand1().PrintGMatrixEntries(m, eqno, side, pout);
    getSummand2().PrintGMatrixEntries(m, eqno, side, pout);
  }

  public void PrintHMatrixEntries(Model m, int eqno, int side,
				  PrintStream pout) {
    getSummand1().PrintHMatrixEntries(m, eqno, side, pout);
    getSummand2().PrintHMatrixEntries(m, eqno, side, pout);
  }

  public void PrintSubtree() {
    System.out.print("(");
    getSummand1().PrintSubtree();
    System.out.print(" +\n            ");
    getSummand2().PrintSubtree();
    System.out.print(")");
  }

  public void PrintTerm(PrintStream pout) {
    pout.print("(");
    getSummand1().PrintTerm(pout);
    pout.print("+");
    getSummand2().PrintTerm(pout);
    pout.print(")");
  }

  public int PowerErrorCheck() {
    return getSummand1().PowerErrorCheck() + getSummand2().PowerErrorCheck();
  }

  public int ProductErrorCheck() {
    // If either subtree has a value of zero, then there is an
    // additive constant term. In this case return 2 to assure 
    // that the error is detected. Otherwise, return larger value 
    // of subtree values.
    int Result1;
    int Result2;
    Result1 = getSummand1().ProductErrorCheck();
    Result2 = getSummand2().ProductErrorCheck();
    if ((Result1 == 0) || (Result2 == 0))
      return 2;
    else if (Result1 > Result2)
      return Result1;
    else
      return Result2;
  }

public Node getSummand1() {
	return Summand1;
}

public void setSummand1(Node summand1) {
	Summand1 = summand1;
}

public Node getSummand2() {
	return Summand2;
}

public void setSummand2(Node summand2) {
	Summand2 = summand2;
}
  
} // class SumNode

