package gov.frb.ma.msu.modelEZCommon;

//import gov.frb.ma.msu.toMatlab.AMAtoMatlab;

import java.io.FileOutputStream;
import java.io.PrintStream;

public class Model {

    public static final int Max_Array_Size = 10000;

    public static final int Left_Side = 100;
    public static final int Right_Side = 101;

	protected String Name;
	protected int NEq;
	private int NLag;
	private int NLead;
	private Equation[] Equations = new Equation[Max_Array_Size];
	private String[] Coefficients = new String[Max_Array_Size];
	private Variable[] Variables = new Variable[Max_Array_Size];
	protected int NVars;
	protected int NCoeffs;

	public Model() {
		super();
	}
	 public Model(String s) {
	      Name = s;
	      NEq = 0;
	      setNLag(0);
	      setNLead(0);
	      NVars = 0;
	      NCoeffs = 0;
	  }
	public int getNEq() { return NEq; }

	public int ErrorCheck() { 
	    int i;
	    int errorFound = 0;
	
	    //    System.err.println("Checking for errors......");
	
	    if (NEq != NVars) {
		System.err.println("Error: Number of variables not equal to number " +
				   "of equations.");
		errorFound++;
	    }
	
	    for (i = 0; i < NEq; i++)
	    {
		if ((getEquations()[i].getLHS().PowerErrorCheck() > 0) ||
		    (getEquations()[i].getRHS().PowerErrorCheck() > 0)) {
		    System.err.print("Error in equation #" + (i+1) + ": ");
		    System.err.print("Variables cannot be raised to a power nor ");
		    System.err.print("appear\n                    in an exponent");
		    System.err.println(" or denominator.");
		    errorFound++;
		}
		
		if ((getEquations()[i].getLHS().ProductErrorCheck() > 1) ||
		    (getEquations()[i].getRHS().ProductErrorCheck() > 1)) {
		    System.err.print("Error in equation #" + (i+1) + ": ");
		    System.err.println("Equation has additive constant or is nonlinear");
		    System.err.println("                    in its variables.");
		    errorFound++;
		}
	    }
	    return errorFound;
	  }

	public void AddEquation(Equation e) {
	getEquations()[NEq] = e;
	NEq++;
	}

	public void AddCoefficient(String s) {
	getCoefficients()[NCoeffs] = s;
	NCoeffs++;
	}

	public void AddVariable(Variable v) {
	      getVariables()[NVars] = v;
	      NVars++;
	  }

	public int FindCoefficientIndex(String s) {
	    // returns the index of the String in Coefficients
	    // that matches the String s, or -1 if there is no match.
	    int i = 0;
	    while ((i < NCoeffs) && !(getCoefficients()[i].equals(s)))
	      i++;
	    if (i < NCoeffs)
	      return i;
	    else
	      return -1;
	  }

	public int FindEquationIndex(String s) {
	    // returns the index of the equation in Equations
	    // that matches the String s, or -1 if there is no match.
	    int i = 0;
	    while ((i < NEq) && !(getEquations()[i].getName().equals(s)))
	      i++;
	    if (i < NEq)
	      return i;
	    else
	      return -1;
	  }

	public int FindVariableIndex(String s) {
	    // returns the index of the variable in Variables whose
	    // name matches the String s, or -1 if there is no match.
	    int i = 0;
	    while ((i < NVars) && !((getVariables()[i].getName()).equals(s)))
	      i++;
	    if (i < NVars)
	      return i;
	    else
	      return -1;
	  }

	public void Print() { // for debugging
	    int i;
	    for (i = 0; i < NEq; i++) {
	      System.out.println("Equation #" + (i+1) + ":");
	      getEquations()[i].Print();
	    }
	  }

	public void ExpandSubtrees() {
	    int i;
	    for (i = 0; i < NEq; i++)
	      getEquations()[i].ExpandSubtrees();
	  }

	public void setName(String s) { Name = s; }

	public void setNEq(int n) { NEq = n; }

	public void setNLag(int n) { NLag = n; }

	public void setNLead(int n) { NLead = n; }

	public void setNCoeffs(int n) { NCoeffs = n; }

	public void PrintFunctions() {
	    int i;
	    PrintStream dataPS;
	    PrintStream matrixPS;
	    String lcName = Name;
	    lcName.toLowerCase();
	    String dataFileName = lcName + "_AMA_data.m";
	    String matrixFileName = lcName + "_AMA_matrices.m";
	    
	    try {
		dataPS = new PrintStream(new FileOutputStream(dataFileName));
	
		dataPS.println("function [param_,np,modname,neq,nlag,nlead,eqname_,eqtype_,endog_,delay_,vtype_] = ...");
		dataPS.println("     " + lcName + "_AMA_data()");
		dataPS.println();
		dataPS.println("% " + lcName + "_AMA_data()");
		dataPS.println("%     This function will return various information about the AMA model,");
		dataPS.println("%     but will not compute the G and H matrices.");
		dataPS.println();
		dataPS.println("  eqname = cell(" + NEq + ", 1);");
		dataPS.println("  param = cell(" + NCoeffs + ", 1);");
		dataPS.println("  endog = cell(" + NEq + ", 1);");
		dataPS.println("  delay = zeros(" + NEq + ", 1);");
		dataPS.println("  vtype = zeros(" + NEq + ", 1);");
		dataPS.println("  eqtype = zeros(" + NEq + ", 1);");
		dataPS.println();
		dataPS.println("  modname = '" + Name + "';");
		dataPS.println("  neq = " + NEq + ";");
		dataPS.println("  np = " + NCoeffs + ";");
		dataPS.println("  nlag = " + getNLag() + ";");
		dataPS.println("  nlead = " + getNLead() + ";");
		dataPS.println();
	
		for (i = 0; i < NEq; i++)
		    dataPS.println("  eqname(" + (i+1) + ") = cellstr('" +
				   getEquations()[i].getName() + "');");
		dataPS.println("  eqname_ = char(eqname);");
		dataPS.println();
	
		for (i = 0; i < NEq; i++) {
		    dataPS.print("  eqtype(" + (i+1) + ") = " + getEquations()[i].getEqType() +
				   ";   ");
		    if (i % 3 == 2)
		      dataPS.println();
		}
		if (i % 3 != 1)
		  dataPS.println();
		dataPS.println("  eqtype_ = eqtype;");
		dataPS.println();
	
		for (i = 0; i < NCoeffs; i++)
		dataPS.println("  param(" + (i+1) + ") = cellstr('" + getCoefficients()[i]
			       + "');");
		dataPS.println("  param_ = char(param);");
		dataPS.println();
	
		for (i = 0; i < NVars; i++)
		  dataPS.println("  endog(" + (i+1) + ") = cellstr('" +
				 getVariables()[i].getName() + "');");
		dataPS.println("  endog_ = char(endog);");
		dataPS.println();
	
		for (i = 0; i < NVars; i++) {
		  dataPS.print("  delay(" + (i+1) + ") = " + getVariables()[i].returnDelay() +
			       ";   ");
		  if (i % 3 == 2)
		    dataPS.println();
		}
		if (i % 3 != 1)
		  dataPS.println();
		dataPS.println("  delay_ = delay;");
		dataPS.print("\n");
		
		for (i = 0; i < NEq; i++) {
		  dataPS.print("  vtype(" + (i+1) + ") = " + getVariables()[i].getDataType() +
			       ";   ");
		  if (i % 3 == 2)
		    dataPS.println();
		}
		if (i % 3 != 1)
		  dataPS.println();
		dataPS.println("  vtype_ = vtype;");
		dataPS.print("\n\n\n");
	
		dataPS.close();
	    } catch (Exception e) {
		System.err.println("ERROR: " + e.getMessage());
	    }
	
	/*****************************************************************
	  Now print out the function compute_AMA_matrices(). This function
	  will compute the G and H matrices.  Actually this is a script not
	  a function.  It is easier to deal with the parameters in Matlab if
	  this part is a script since you don't have to declare them globals
	  or reassign them inside the function.
	******************************************************************/
	
	    try {
	      matrixPS = new PrintStream(new FileOutputStream(matrixFileName));
      
	      
	      matrixPS.println("% " + lcName + "_AMA_matrices()");
	      matrixPS.println("%     This script will compute the G and H matrices.");
	      matrixPS.println();
	
	      matrixPS.println("  g = zeros(" + NEq + ", " + ((getNLag()+1)*NEq)
			       + ");");
	      matrixPS.println("  h = zeros(" + NEq + ", " + ((getNLag()+1+getNLead())*NEq)
			       + ");");
	      matrixPS.println();
	
	      for (i = 0; i < NEq; i++) {
		getEquations()[i].getLHS().PrintGMatrixEntries(this, i, Left_Side,
						     matrixPS);
		getEquations()[i].getRHS().PrintGMatrixEntries(this, i, Right_Side,
						     matrixPS);
		getEquations()[i].getLHS().PrintHMatrixEntries(this, i, Left_Side,
						     matrixPS);
		getEquations()[i].getRHS().PrintHMatrixEntries(this, i, Right_Side,
						     matrixPS);
	      }
	
	      matrixPS.println();
	      matrixPS.println("  cofg = g;");
	      matrixPS.println("  cofh = h;");
	
	      matrixPS.close();
	    } catch (Exception e) {
	      System.err.println("ERROR: " + e.getMessage());
	    }
	  }

	public int getNLag() {
		return NLag;
	}

	public Variable[] getVariables() {
		return Variables;
	}

	public void setVariables(Variable[] variables) {
		Variables = variables;
	}

	public Equation[] getEquations() {
		return Equations;
	}

	public void setEquations(Equation[] equations) {
		Equations = equations;
	}

	public int getNLead() {
		return NLead;
	}
	public String[] getCoefficients() {
		return Coefficients;
	}
	public void setCoefficients(String[] coefficients) {
		Coefficients = coefficients;
	}

}