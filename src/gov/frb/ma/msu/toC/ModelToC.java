package gov.frb.ma.msu.toC;

import gov.frb.ma.msu.modelEZCommon.Model;
import gov.frb.ma.msu.toC.AMAtoC;

import java.io.FileOutputStream;
import java.io.PrintStream;

public class ModelToC extends Model {
	public ModelToC(String fileName) {
		// TODO Auto-generated constructor stub
		super(fileName);
	}

	public void PrintFunctions() {
	    int i;
	    PrintStream dataPS;
	    PrintStream matrixPS;
	    String lcName = Name;
	    lcName.toLowerCase();
	    String dataFileName = lcName + "_AMA_data.c";
	    String matrixFileName = lcName + "_AMA_matrices.c";
	    
	    try {
		dataPS = new PrintStream(new FileOutputStream(dataFileName));

		dataPS.println("int " + lcName + "_AMA_data(int param,np,char *modname,int eqtype,int endog,int delay,int vtype) {");
		dataPS.println();
		dataPS.println("// " + lcName + "_AMA_data()");
		dataPS.println("//     This function will return various information about the AMA model,");
		dataPS.println("//     but will not compute the G and H matrices.");
		dataPS.println();
		
		dataPS.println("int delay[" + NEq + "][1];");
		dataPS.println("int vtype[" + NEq + "][1];");
		dataPS.println("int eqtype[" + NEq + "][1];");
		dataPS.println();
		dataPS.println("char modname[] = '" + Name + "';");
		dataPS.println("int neq = " + NEq + ";");
		dataPS.println("int np = " + NCoeffs + ";");
		dataPS.println("int nlag = " + getNLag() + ";");
		dataPS.println("int nlead = " + getNLead() + ";");
		dataPS.println("int eqtype[" + NEq + "][1];");
		dataPS.println();
		
		
		for (i = 0; i < NEq; i++)
		    dataPS.println("  eqname[" + (i+1) + "] = '" +
				   getEquations()[i].getName() + "';");
		dataPS.println("  eqname_ = char(eqname);");
		dataPS.println();
	
		for (i = 0; i < NEq; i++) {
		    dataPS.print(" eqtype[" + (i+1) + "][1] = " + getEquations()[i].getEqType() +
				   ";   ");
		    if (i % 3 == 2)
		      dataPS.println();
		}
		if (i % 3 != 1)
		  dataPS.println();
		dataPS.println("  eqtype_ = eqtype;");
		dataPS.println();
	
		for (i = 0; i < NCoeffs; i++)
		dataPS.println("  param[" + (i+1) + "] = '" + getCoefficients()[i]
			       + "';");
		dataPS.println("  param_ = char(param);");
		dataPS.println();
	
		for (i = 0; i < NVars; i++)
		  dataPS.println("    endog[" + (i+1) + "] = '" +
				 getVariables()[i].getName() + "';");
		dataPS.println("  endog_ = char(endog);");
		dataPS.println();
	
		for (i = 0; i < NVars; i++) {
		  dataPS.print("  delay[" + (i+1) + "][1] = " + getVariables()[i].returnDelay() +
			       ";   ");
		  if (i % 3 == 2)
		    dataPS.println();
		}
		if (i % 3 != 1)
		  dataPS.println();
		dataPS.println("  delay_ = delay;");
		dataPS.print("\n");
		
		for (i = 0; i < NEq; i++) {
		  dataPS.print("   vtype[" + (i+1) + "][1] = " + getVariables()[i].getDataType() +
			       ";   ");
		  if (i % 3 == 2)
		    dataPS.println();
		}
		if (i % 3 != 1)
		  dataPS.println();
		dataPS.println("  vtype_ = vtype;");
		dataPS.print("\n\n\n");
	
		dataPS.println();
		dataPS.println("return(0);");
		dataPS.println("}");
		
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
	      
	     
	      matrixPS.println("int " + lcName + "_AMA_matrices(double *paramvalues, double *cofg, double *cofh) {");
	      matrixPS.println("//     This script will compute the G and H matrices.");
	      matrixPS.println("#include <math.h>");
	      	      matrixPS.println();
	      
	     

	      
	      for (i = 0; i < NCoeffs; i++) {
	        
	    	matrixPS.println("double " + getCoefficients()[i] + ";");
	        matrixPS.println(getCoefficients()[i] + "= paramvalues[" + i + "];");
	        }
	      	matrixPS.println("");
	        matrixPS.println();
	        
	      matrixPS.println("double g[" + NEq + "*"+ (getNLag()+1)*NEq +"];");
	      matrixPS.println("double  h[" + NEq + "*"+ (getNLag()+1+getNLead())*NEq +"] ;");
	      matrixPS.println();
	      
	      matrixPS.println("int i;");

	      matrixPS.println("for(i = 0;i <" + NEq + "*" + (getNLag()+1)*NEq + " ; i++ ){");
	      matrixPS.println("g[i] = 0;");
	      matrixPS.println("}");
	      
	      matrixPS.println("for(i = 0;i <" + NEq + "*" + (getNLag()+1+getNLead())*NEq  + " ; i++ ){");
	      matrixPS.println("h[i] = 0;");
	      matrixPS.println("}");
	      
	      for (i = 0; i < NEq; i++) {
		getEquations()[i].getLHS().PrintGMatrixEntries(this, i, AMAtoC.Left_Side,
						     matrixPS);
		getEquations()[i].getRHS().PrintGMatrixEntries(this, i, AMAtoC.Right_Side,
						     matrixPS);
		getEquations()[i].getLHS().PrintHMatrixEntries(this, i, AMAtoC.Left_Side,
						     matrixPS);
		getEquations()[i].getRHS().PrintHMatrixEntries(this, i, AMAtoC.Right_Side,
						     matrixPS);
	      }
	
	      matrixPS.println();
	      matrixPS.println("  cofg = g;");
	      matrixPS.println("  cofh = h;");
	      
	      matrixPS.println();
		  matrixPS.println("return(0);");
	      matrixPS.println("}");
	      
	      matrixPS.close();
	    } catch (Exception e) {
	      System.err.println("ERROR: " + e.getMessage());
	    }
	  }

}
