package gov.frb.ma.msu.modelEZCommon;

public class Variable {
    private String Name;
    ////    int Type;     // must be Aim.Endogenous or Aim.Exogenous.
    private int DataType; // must be Aim.Data, Aim.Notd, or Aim.Dtrm
    int Delay;

    public Variable(String s) {
	setName(s);
    }

    ////    public void setType(int n) { Type = n; }
    public void setDataType(int n) { DataType = n; }
    public void setDelay(int n) { Delay = n; }

    public int returnDelay() { return Delay; }

	public int getDataType() {
		return DataType;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

}
