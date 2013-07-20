package gov.frb.ma.msu.toMatlab;

import gov.frb.ma.msu.modelEZCommon.Node;

import java.io.*;


public class VariableNode extends Node 
{
  String Name;
  int Period;
  int ELag;   // this can only be AMA.Yes or AMA.No. is there a good way
  // to enforce this?
  
  public VariableNode(String s, int p, int e) {
    Name = s;
    Period = p;
    ELag = e;
  }
  
  public Node CopySubtree() { 
    VariableNode vn = new VariableNode(Name, Period, ELag);
    return vn; 
  }

  public Node ExpandSubtree() {
      return this;
  }

  public int CountVariables() {
    return 1;
  }

  public Node FindVariable() {
    return this;
  }

  public void PrintGMatrixEntries(Model m, int eqno, int side,
				  PrintStream pout) {
    int index;
    int indexPlus1;
    if ((Period <= 0) && (ELag == AMAtoMatlab.No)) {
      index = ((Period + m.NLag) * m.NEq +
	       m.FindVariableIndex(Name))
	* m.NEq + eqno;
      indexPlus1 = index + 1;
      pout.print("  g(" + indexPlus1 + ") = g(" + indexPlus1 + ")");
      if (side == AMAtoMatlab.Left_Side)
	pout.print(" + 1;\n");
      else
	pout.print(" - 1;\n");
    }
  }

  public void PrintHMatrixEntries(Model m, int eqno, int side,
				  PrintStream pout) {
    int index;
    int indexPlus1;
    if ((Period > 0 ) || (ELag == AMAtoMatlab.Yes)) {
      index = ((Period + m.NLag) * m.NEq + m.FindVariableIndex(Name))
	* m.NEq + eqno;
      indexPlus1 = index + 1;
      pout.print("  h(" + indexPlus1 + ") = h(" + indexPlus1 + ")");
      if (side == AMAtoMatlab.Left_Side)
	pout.print(" + 1;\n");
      else
	pout.print(" - 1;\n");
    }
  }

  public void PrintSubtree() {
    if (ELag == AMAtoMatlab.Yes)
      System.out.print("ELAG(" + Name + "," + (-1 * Period) + ")");
    else if (Period > 0)
      System.out.print(Name + "(" + Period + ")");
    else if (Period < 0)
      System.out.print(Name + "(" + Period + ")");
    else
      System.out.print(Name);
  }

  public void PrintTerm(PrintStream pout) {
    pout.print("1");
  }

  public int PowerErrorCheck() {
    return 0;
  }

  public int ProductErrorCheck() {
    return 1;
    }
  
} // class VariableNode


