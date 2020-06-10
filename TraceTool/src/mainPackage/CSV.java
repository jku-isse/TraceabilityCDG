package mainPackage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import model.MethodRTMCell;
import model.RTMCell;
import model.VariableList;

public class CSV {
    static File file = new File("log\\data.txt");


     static String  headers="gold,callersAtLeast1T,"
     		+ "CalleesAtLeast1T,callersAllT,"
     		+ "calleesAllT,CallersAtLeast1N,"
     		+ "CalleesAtLeast1N,CallersAllN,"
     		+ "CalleesAllN,"
//     		+ "InterfacesAtLeast1T,"
//     		+ "ImplememntationsAtleast1T,"
     		+ "childrenAtLeast1T,parentsAtLeast1T,"
//     		+ "InterfacesAtLeast1N,ImplementationsAtLeast1N,"
     		+ "childrenAtLeast1N,parentsAtLeast1N,"
//     		+ "InterfacesAllT,ImplementationsAllT,"
     		+ "childrenAllT,parentsAllT,"
//     		+ "InterfacesAllN,ImplementationsAllN,"
     		+ "childrenAllN,ParentsAllN,"
     		+ "ParametersatLeast1T,FieldMethodsAtLeast1T,"
     		+ "ReturnTypeAtLeast1T,ParametersAtLeast1N,"
     		+ "FieldMethodsAtLeast1N,ReturnTypeN,"
     		+ "ParametersAllT,FieldMethodsAllT,"
     		+ "ParametersAllN,FieldMethodsAllN"
     		+ ",Program,Requirement,MethodID"; 
	
	public static void main (String [] args) throws Exception {
		ArrayList<String> programs = new ArrayList<String>();
		
		 FileWriter writer = new FileWriter(file,true);

        writer.write(headers+"\n");
			programs.add("chess");
			programs.add("gantt");
			programs.add("itrust");
			programs.add("jhotdraw");
			

			
	int i=0; 
	for(String programName: programs) {
		DatabaseInput.read(programName);
		generateCSVFile(programName,writer);


	}
    writer.close();

	}
	public static void generateCSVFile(String programName, FileWriter writer) {
		// TODO Auto-generated method stub
        try {
        
            for ( MethodRTMCell methodtrace : MethodRTMCell.methodtraces2HashMap.values()) {
            	if(!methodtrace.getGoldTraceValue().equals(RTMCell.TraceValue.UndefinedTrace)) {
            		  String s=
            	                methodtrace.logGoldTraceValueString()+","+methodtrace.getCallers().atLeast1GoldT()
            	                + ","+ methodtrace.getCallees().atLeast1GoldT()  +","+methodtrace.getCallers().allGoldT() 
            	               + ","+ methodtrace.getCallees().allGoldT()  +","+ methodtrace.getCallers().atLeast1GoldN()
            	               + ","+ methodtrace.getCallees().atLeast1GoldN()+","+methodtrace.getCallers().allGoldN()
            	                + ","+ methodtrace.getCallees().allGoldN()
//            	                +","+ methodtrace.getInterfaces().atLeast1GoldT() 
            	             
//								  +","+ methodtrace.getImplementations().atLeast1GoldT() 
								  +","+ methodtrace.getChildren().atLeast1GoldT()  
								 +","+ methodtrace.getParents().atLeast1GoldT()
//								 +","+ methodtrace.getInterfaces().atLeast1GoldN()  
//								  +","+ methodtrace.getImplementations().atLeast1GoldN() 
								  +","+ methodtrace.getChildren().atLeast1GoldN() 
								  +","+ methodtrace.getParents().atLeast1GoldN() 
//								  +","+ methodtrace.getInterfaces().allGoldT() 
//								  +","+ methodtrace.getImplementations().allGoldT() 
								  +","+ methodtrace.getChildren().allGoldT()  
								 +","+ methodtrace.getParents().allGoldT() 
//								 +","+ methodtrace.getInterfaces().allGoldN() 
//								  +","+ methodtrace.getImplementations().allGoldN()
								  +","+ methodtrace.getChildren().allGoldN() 
								   +","+ methodtrace.getParents().allGoldN()+","+   methodtrace.getParameters().atLeast1T()
  
  
 								+ ","+ methodtrace.getFieldMethods().atLeast1T()   + ","+ methodtrace.getReturnTypeMethod().atLeast1T() 
            	                +","+methodtrace.getParameters().atLeast1N()   +","+ methodtrace.getFieldMethods().atLeast1N()
            	               + ","+  methodtrace.getReturnTypeMethod().atLeast1N()   +","+ methodtrace.getParameters().allTs()
            	              + ","+ methodtrace.getFieldMethods().allTs() + ","+ methodtrace.getParameters().allNs()
            	                +    ","+ methodtrace.getFieldMethods().allNs() 
            	                ; 
            	                s=s.replaceAll("false","0"); 
            	                s=s.replaceAll("true","1"); 
            	                s=s+","+programName+","+methodtrace.getRequirement().getID()+","+methodtrace.getMethod().getID(); 
            	                writer.write(s+"\n");
                      		  System.out.println(s);

            	}
            	
            	if(methodtrace.getImplementations().atLeast1GoldT()) {
            		System.out.println("yes");
            	}
              

            }
            System.out.println("over");
        } catch (Exception ex) {
        }
	}

}
