package gov.frb.ma.msu.toC;


import gov.frb.ma.msu.modelEZCommon.Equation;
import gov.frb.ma.msu.modelEZCommon.Variable;
import gov.frb.ma.msu.modelEZCommon.ModelAbstrct;

import java.io.*;


public class Model extends ModelAbstrct
{

  public Model(String s) {
      Name = s;
      NEq = 0;
      setNLag(0);
      setNLead(0);
      NVars = 0;
      NCoeffs = 0;
  }

  
}

