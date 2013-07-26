package gov.frb.ma.msu.toC;

import gov.frb.ma.msu.modelEZCommon.Node;
import gov.frb.ma.msu.modelEZCommon.PowerNode;

import java.io.PrintStream;

public class PowerNodeToC extends PowerNode {

	public PowerNodeToC(Node b, Node e) {
		super(b, e);
		// TODO Auto-generated constructor stub
	}
	 
    public void PrintSubtree() {
      System.out.print("pow(");
      getBase().PrintSubtree();
      System.out.print(",(");
      getExponent().PrintSubtree();
      System.out.print("))");
    }

    public void PrintTerm(PrintStream pout) {
    pout.print("pow(");
    getBase().PrintTerm(pout);
    pout.print(",");
    getExponent().PrintTerm(pout);
    pout.print(")");
  }

}
