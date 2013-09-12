package gov.frb.ma.msu.toR;

import java.io.*;

public class RunTheParser implements AimConstants {

public static String runTheParser(String fName){
	String res = "Success";
	
	String [] inFileName = makeArray(fName);
	Aim spModelName = new Aim(java.lang.System.in);
	try{
	spModelName.runParser(inFileName);
	}
	catch(ParseException e){
		System.out.println(e.getMessage());
		res = "Failure";
	}

	return res;
}


public static String[] makeArray(String filename){
	String [] out = new String[1];
	out[0]=filename;
	return out;
}

public static void main(String[] args){
	
}
}