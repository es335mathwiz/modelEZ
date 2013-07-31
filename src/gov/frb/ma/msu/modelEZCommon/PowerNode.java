package gov.frb.ma.msu.modelEZCommon;

import java.io.*;

public class PowerNode extends Node
{
    private Node Base;
    private Node Exponent;

    public PowerNode(Node b, Node e) {
	setBase(b);
	setExponent(e);
    }

    public Node CopySubtree() { 
      PowerNode pn = new PowerNode(getBase().CopySubtree(), getExponent().CopySubtree());
      return pn;
    }

    public Node ExpandSubtree() {
      return this;
    }

    public int CountVariables() {
      return getBase().CountVariables();
    }

    public Node FindVariable() {
      return null;
    }

    public void PrintGMatrixEntries(Model m, int eqno, int side,
				    PrintStream pout) {
      int eqnoPlus1;
      eqnoPlus1 = eqno + 1;
      System.err.println("Error in equation " + eqnoPlus1 +
			 ": Additive constants not permitted.\n");
      System.exit(1);
    }
  
  public void PrintHMatrixEntries(Model m, int eqno, int side,
				  PrintStream pout) {
    int eqnoPlus1 = eqno + 1;
    System.err.println("Error in equation " + eqnoPlus1 +
		       ": Additive constants not permitted.\n");
    System.exit(1);
  }
  
    public void PrintSubtree() {
      getBase().PrintSubtree();
      System.out.print("^(");
      getExponent().PrintSubtree();
      System.out.print(")");
    }

    public void PrintTerm(PrintStream pout) {
    pout.print("(");
    getBase().PrintTerm(pout);
    pout.print("^");
    getExponent().PrintTerm(pout);
    pout.print(")");
  }

    public int PowerErrorCheck() {
      return (getBase().CountVariables() + getExponent().CountVariables());
    }

  public int ProductErrorCheck() {
    return (getBase().CountVariables() + getExponent().CountVariables());
  }

public Node getBase() {
	return Base;
}

public void setBase(Node base) {
	Base = base;
}

public Node getExponent() {
	return Exponent;
}

public void setExponent(Node exponent) {
	Exponent = exponent;
}
    
} // class PowerNode

