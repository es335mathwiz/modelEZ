package gov.frb.ma.msu.modelEZCommon;


public class Equation {
	  private String Name;
	  private Node LHS;
	  private Node RHS;
	  private int EqType;  // must be Aim.Stoch or Aim.Imposed.

	  public Equation(String s, Node left, Node right, int etype) {
	    setName(s);
	    setLHS(left);
	    setRHS(right);
	    setEqType(etype);
	  }
	  
	  public Equation(String s, int etype) {
	    setName(s);
	    setEqType(etype);
	  }

	  public void setLHS(Node n) {
	    LHS = n;
	  }
	  
	  public void setRHS(Node n) {
	    RHS = n;
	  }
	  
	  public void Print() { 
	    getLHS().PrintSubtree();
	    System.out.print(" =\n         ");
	    getRHS().PrintSubtree();
	    System.out.println("\n\n");
	}

	  public void ExpandSubtrees() {
	    setLHS(getLHS().ExpandSubtree());
	    setRHS(getRHS().ExpandSubtree());
	  }

	public Node getLHS() {
		return LHS;
	}

	public Node getRHS() {
		return RHS;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public int getEqType() {
		return EqType;
	}

	public void setEqType(int eqType) {
		EqType = eqType;
	}

}
