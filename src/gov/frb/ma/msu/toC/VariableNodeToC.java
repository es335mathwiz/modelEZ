package gov.frb.ma.msu.toC;
import gov.frb.ma.msu.modelEZCommon.Node;
import gov.frb.ma.msu.modelEZCommon.Model;

import java.io.*;


public class VariableNodeToC extends Node 
{
  String Name;
  int Period;
  int ELag;   // this can only be AMA.Yes or AMA.No. is there a good way
  // to enforce this?
  
  public VariableNodeToC(String s, int p, int e) {
    Name = s;
    Period = p;
    ELag = e;
  }
  
  public Node CopySubtree() { 
    VariableNodeToC vn = new VariableNodeToC(Name, Period, ELag);
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
    if ((Period <= 0) && (ELag == AMAtoC.No)) {
      index = ((Period + m.getNLag()) * m.getNEq() +
	       m.FindVariableIndex(Name))
	* m.getNEq() + eqno;
      pout.print("  g[" + index + "] = g[" + index + "]");
      if (side == Model.Left_Side)
	pout.print(" + 1;\n");
      else
	pout.print(" - 1;\n");
    }
  }

  public void PrintHMatrixEntries(Model m, int eqno, int side,
				  PrintStream pout) {
    int index;
    if ((Period > 0 ) || (ELag == AMAtoC.Yes)) {
      index = ((Period + m.getNLag()) * m.getNEq() + m.FindVariableIndex(Name))
	* m.getNEq() + eqno;
      pout.print("  h[" + index + "] = h[" + index + "]");
      if (side == Model.Left_Side)
	pout.print(" + 1;\n");
      else
	pout.print(" - 1;\n");
    }
  }

  public void PrintSubtree() {
    if (ELag == AMAtoC.Yes)
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


