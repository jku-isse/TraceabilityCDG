package mainPackage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import BoxPlots.counts;
import evaluation.Seeder;
import model.MethodRTMCell;
import model.MethodRTMCellList;
import model.PredictionPattern;
import model.RTMCell;
import model.RTMCellList;
import model.VariableList;
import traceValidator.TraceValidatorPredictionPattern;

public class CSV {
    static File file = new File("log\\data.txt");


//     static String  headers="gold,"
//    		 //inner rules 
//    		 +"T-x-T,T-x-N,T-x-U,T-x-NT,T-x-UT,T-x-UN,T-x-UNT,"
//       		 + "N-x-T,N-x-N,N-x-U,N-x-NT,N-x-UT,N-x-UN,N-x-UNT,"
//       		 +"U-x-T,U-x-N,U-x-U,U-x-NT,U-x-UT,U-x-UN,U-x-UNT,"
//       		 + "NT-x-T,NT-x-N,NT-x-U,NT-x-NT,NT-x-UT,NT-x-UN,NT-x-UNT,"
//       		 + "UT-x-T,UT-x-N,UT-x-U,UT-x-NT,UT-x-UT,UT-x-UN,UT-x-UNT,"
//       		 + "UN-x-T,UN-x-N,UN-x-U,UN-x-NT,UN-x-UT,UN-x-UN,UN-x-UNT,"
//       		 + "UNT-x-T,UNT-x-N,UNT-x-U,UNT-x-NT,UNT-x-UT,UNT-x-UN,UNT-x-UNT,"
//       		 
//       		 
//	    	+"T-T-x,T-N-x,T-U-x,T-NT-x,T-UT-x,T-UN-x,T-UNT-x,"
//	    	 + "N-T-x,N-N-x,N-U-x,N-NT-x,N-UT-x,N-UN-x,N-UNT-x,"
//	    	 +"U-T-x,U-N-x,U-U-x,U-NT-x,U-UT-x,U-UN-x,U-UNT-x,"
//	    	 + "NT-T-x,NT-N-x,NT-U-x,NT-NT-x,NT-UT-x,NT-UN-x,NT-UNT-x,"
//	    	 + "UT-T-x,UT-N-x,UT-U-x,UT-NT-x,UT-UT-x,UT-UN-x,UT-UNT-x,"
//	    	 + "UN-T-x,UN-N-x,UN-U-x,UN-NT-x,UN-UT-x,UN-UN-x,UN-UNT-x,"
//	    	 + "UNT-T-x,UNT-N-x,UNT-U-x,UNT-NT-x,UNT-UT-x,UNT-UN-x,UNT-UNT-x,"
// 		
//    	 
//		     +"x-T-T,x-T-N,x-T-U,x-T-NT,x-T-UT,x-T-UN,x-T-UNT,"
//		   	 + "x-N-T,x-N-N,x-N-U,x-N-NT,x-N-UT,x-N-UN,x-N-UNT,"
//		   	 +"x-U-T,x-U-N,x-U-U,x-U-NT,x-U-UT,x-U-UN,x-U-UNT,"
//		   	 + "x-NT-T,x-NT-N,x-NT-U,x-NT-NT,x-NT-UT,x-NT-UN,x-NT-UNT,"
//		   	 + "x-UT-T,x-UT-N,x-UT-U,x-UT-NT,x-UT-UT,x-UT-UN,x-UT-UNT,"
//		   	 + "x-UN-T,x-UN-N,x-UN-U,x-UN-NT,x-UN-UT,x-UN-UN,x-UN-UNT,"
//		   	 + "x-UNT-T,x-UNT-N,x-UNT-U,x-UNT-NT,x-UNT-UT,x-UNT-UN,x-UNT-UNT,"
//    		 
//     		+ "callersAtLeast1T,"
//     		+ "CalleesAtLeast1T,callersAllT,"
//     		+ "calleesAllT,CallersAtLeast1N,"
//     		+ "CalleesAtLeast1N,CallersAllN,"
//     		+ "CalleesAllN,"
////     		+ "InterfacesAtLeast1T,"
////     		+ "ImplememntationsAtleast1T,"
//     		+ "childrenAtLeast1T,parentsAtLeast1T,"
////     		+ "InterfacesAtLeast1N,ImplementationsAtLeast1N,"
//     		+ "childrenAtLeast1N,parentsAtLeast1N,"
////     		+ "InterfacesAllT,ImplementationsAllT,"
//     		+ "childrenAllT,parentsAllT,"
////     		+ "InterfacesAllN,ImplementationsAllN,"
//     		+ "childrenAllN,ParentsAllN,"
//     		+ "ParametersatLeast1T,FieldMethodsAtLeast1T,"
//     		+ "ReturnTypeAtLeast1T,ParametersAtLeast1N,"
//     		+ "FieldMethodsAtLeast1N,ReturnTypeN,"
//     		+ "ParametersAllT,FieldMethodsAllT,"
//     		+ "ParametersAllN,FieldMethodsAllN,"
//     		+"ClassGoldN,ClassGoldT,"
//     		+"Inner,Leaf,Root,Isolated,"
//     		+"EmptyCallers,EmptyCallees,EmptyCallersCallers,EmptyCalleesCallees,"
//
//     		+ "Program,Requirement,MethodID"; 
   
    
    static String headers="gold,classGold,Program,Requirement,MethodType,Top,Side,"
    		+"FieldMethods,Parameters,ReturnType,Parents,Children,"
    		+ "CallersT,CallersN,CallersU,"
    		+ "CallersCallersT,CallersCallersN,CallersCallersU,"
    		+ "CalleesT,CalleesN,CalleesU,"
    		+ "CalleesCalleesT,CalleesCalleesN,CalleesCalleesU,CompleteCallersCallees,"
    		+ "CompleteCallersCalleesCallersCallersCalleesCallees"; 
    
    
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
		generateCSVFileNew(programName,writer);


	}
    writer.close();

	}
	private static void generateCSVFileNew(String programName, FileWriter writer) throws IOException {
		// TODO Auto-generated method stub

			int i=1; 
			Seeder.seedInputClazzTraceValuesWithDeveloperGold();

            for ( MethodRTMCell methodtrace : MethodRTMCell.methodtraces2HashMap.values()) {
            	if(!methodtrace.getGoldTraceValue().equals(RTMCell.TraceValue.UndefinedTrace)) {

       		 		String s= methodtrace.logGoldTraceValueString()
       		 				+","+methodtrace.getClazzRTMCell().getTraceValue()
       		 				+","+programName+","+methodtrace.getRequirement().getID()+","; 
       		 		
       		 		if(!methodtrace.getCallers().isEmpty() && !methodtrace.getCallees().isEmpty()) {
       		 			s=s+"Inner,"+calculateTNU(methodtrace.getCallees())+","+calculateTNU(methodtrace.getCallers())+","; 
       		 		}else if( methodtrace.getCallees().isEmpty() ) {
       		 			s=s+"Leaf,"+calculateTNU(methodtrace.getCallers().getCallers())+","+calculateTNU(methodtrace.getCallers())+","; 
       		 		}else if( methodtrace.getCallers().isEmpty() ) {
       		 			s=s+"Root,"+calculateTNU(methodtrace.getCallees().getCallees())+","+calculateTNU(methodtrace.getCallees())+","; 
       		 		}else if( methodtrace.getCallers().isEmpty() && methodtrace.getCallees().isEmpty()) {
       		 			s=s+"Isolated,,,"; 
       		 		}
       		 		
       		 		
       		 		
       		 		
       		 		if(!methodtrace.getFieldMethods().isEmpty()) {
       		 			s=s+calculateTNU(methodtrace.getFieldMethods())+","; 
       		 		}else {
       		 			s=s+"-1,"; 

       		 		}
       		 	if(!methodtrace.getParameters().isEmpty()) {
   		 			s=s+calculateTNU(methodtrace.getParameters())+","; 
   		 		}else {
   		 			s=s+"-1,"; 

   		 			
   		 		}
       		 	
       			if(!methodtrace.getReturnTypeMethod().isEmpty()) {
   		 			s=s+calculateTNU(methodtrace.getReturnTypeMethod())+","; 
   		 		}else {
   		 			s=s+"-1,"; 

   		 		}
       			
       			if(!methodtrace.getParents().isEmpty()) {
   		 			s=s+calculateTNU(methodtrace.getParents())+","; 
   		 		}else {
   		 			s=s+"-1,"; 

   		 		}
       			
       			if(!methodtrace.getChildren().isEmpty()) {
   		 			s=s+calculateTNU(methodtrace.getChildren())+","; 
   		 		}else {
   		 			s=s+"-1,"; 

   		 		}
       		 		
       		 		
       		 		
       		 			counts c=generateCountsTNU(methodtrace.getCallers());
       		 			s=s+c.amountT+","+c.amountN+","+c.amountU+","; 
       		 		
	   		 			c=generateCountsTNU(methodtrace.getCallers().getCallers());
	   		 			s=s+c.amountT+","+c.amountN+","+c.amountU+","; 
	   		 		
			 			c=generateCountsTNU(methodtrace.getCallees());
			 			s=s+c.amountT+","+c.amountN+","+c.amountU+","; 
			 			
			 			c=generateCountsTNU(methodtrace.getCallees().getCallees());
			 			s=s+c.amountT+","+c.amountN+","+c.amountU+","; 
		 		
			 			if(!methodtrace.getCallees().isEmpty() && !methodtrace.getCallers().isEmpty()
			 			&& !methodtrace.getCallees().atLeast1GoldU() && !methodtrace.getCallers().atLeast1GoldU()) {
			 				s=s+"1,"; 
			 			}else {
			 				s=s+"0,"; 
			 			}
			 			if(!methodtrace.getCallees().isEmpty() && !methodtrace.getCallers().isEmpty()
			 			    && !methodtrace.getCallees().getCallees().isEmpty() && !methodtrace.getCallers().getCallers().isEmpty()
					 		&& !methodtrace.getCallees().atLeast1GoldU() && !methodtrace.getCallers().atLeast1GoldU()
					 		 && !methodtrace.getCallees().getCallees().atLeast1GoldU() && !methodtrace.getCallers().getCallers().atLeast1GoldU()) {
					 				s=s+"1,"; 
					 			}else {
					 				s=s+"0,"; 
					 			}
			 			s=s+"\n"; 
			 			writer.write(s);
            	}

            }
            
	}
	
	
	public static String calculateTNU(RTMCellList methodTraces) {
		boolean hasT = false;
		boolean hasN = false;
		boolean hasU = false;
		for(RTMCell cell: methodTraces) {
			
				if (cell.getGoldTraceValue().equals(RTMCell.TraceValue.Trace)) {
					hasT = true;
				}
				if (cell.getGoldTraceValue().equals(RTMCell.TraceValue.NoTrace)) {
					hasN = true;
				}
				if (cell.getGoldTraceValue().equals(RTMCell.TraceValue.UndefinedTrace)) {
					hasU = true;
				}
			
		}
		String returnValue=(hasT ? "T":"")+(hasN ? "N":"")+(hasU ? "U":""); 
		
		if(!returnValue.equals(""))return returnValue; 
		else return "-1"; 
	}
	private static counts generateCountsTNU(MethodRTMCellList callers) {
		counts c = counts.countMethods(callers); 
//		String amountT="0"; 
//		String amountN="0"; 
//		String amountU="0"; 

		if(c.T>=4) c.amountT="High"; 
		else if(c.T>1 && c.T<=3) c.amountT="Medium"; 
		else if(c.T==1) c.amountT="Low"; 
		
		
		if(c.N>=5) c.amountN="High"; 
		else if(c.N>1 && c.N<=4) c.amountN="Medium"; 
		else if(c.N==1) c.amountN="Low"; 
		
		
		if(c.U>=6) c.amountU="High"; 
		else if(c.U>1 && c.U<=5) c.amountU="Medium"; 
		else if(c.U==1) c.amountU="Low"; 
		
		return c; 
		
		
	}
	public static void generateCSVFile(String programName, FileWriter writer) {
		// TODO Auto-generated method stub
		TraceValidatorPredictionPattern.define(2);

		try {
			int i=1; 
			Seeder.seedInputClazzTraceValuesWithDeveloperGold();

            for ( MethodRTMCell methodtrace : MethodRTMCell.methodtraces2HashMap.values()) {
            	
            
            	
            	LinkedHashMap<String, String> matrix = new LinkedHashMap<String, String>(); 
            	fillMatrix(matrix); 
            	if(!methodtrace.getGoldTraceValue().equals(RTMCell.TraceValue.UndefinedTrace)) {
            		 
            		 String s= methodtrace.logGoldTraceValueString()+","; 
            		 
            		 
            		 
            			//INNER METHOD
         			if (!methodtrace.getCallers().isEmpty() && !methodtrace.getCallees().isEmpty()) {
         				PredictionPattern p = TraceValidatorPredictionPattern.getInnerPattern(methodtrace.getCallers(), methodtrace.getCallees(), false);
         				s=fillMatrixWith1and0sAndWriteLine(matrix, p.pattern, s); 
         			}
         			//LEAF METHOD
         			else if (!methodtrace.getCallers().isEmpty() && methodtrace.getCallees().isEmpty() && !methodtrace.getCallers().getCallers().isEmpty()) {
         				PredictionPattern p =TraceValidatorPredictionPattern.getLeafPattern(methodtrace.getCallers(), methodtrace.getCallers().getCallers(), false);
         				s=fillMatrixWith1and0sAndWriteLine(matrix, p.pattern,s ); 

         			}
         			//ROOT METHOD
         			else if (methodtrace.getCallers().isEmpty() && !methodtrace.getCallees().isEmpty() && !methodtrace.getCallees().getCallees().isEmpty()) {
         				PredictionPattern p =TraceValidatorPredictionPattern.getRootPattern(methodtrace.getCallees(), methodtrace.getCallees().getCallees(), false);
         				s=fillMatrixWith1and0sAndWriteLine(matrix, p.pattern,s ); 

         			}
         			//E ISOLATED
        			 
        			else {
         				s=fillMatrixWith1and0sAndWriteLine(matrix, "",s ); 
        			}
            				 
            				 
            				 
            				 
            				 s=s+methodtrace.getCallers().atLeast1GoldT()
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
            	                +    ","+ methodtrace.getClazzRTMCell().getTraceValue().equals(RTMCell.TraceValue.NoTrace)
            	                +    ","+ methodtrace.getClazzRTMCell().getTraceValue().equals(RTMCell.TraceValue.Trace)
            	                //inner
            	                +    ","+ (!methodtrace.getCallers().isEmpty() && !methodtrace.getCallees().isEmpty())
            	                //leaf
            	               + ","+(!methodtrace.getCallers().isEmpty() && methodtrace.getCallees().isEmpty() && !methodtrace.getCallers().getCallers().isEmpty())
            	               //root
            	               +","+(methodtrace.getCallers().isEmpty() && !methodtrace.getCallees().isEmpty() && !methodtrace.getCallees().getCallees().isEmpty()) 
            				//isolated
            	               +","+(methodtrace.getCallers().isEmpty() && methodtrace.getCallees().isEmpty())
            	               
            	               +","+(methodtrace.getCallers().isEmpty())+","+methodtrace.getCallees().isEmpty()+","+
            	               methodtrace.getCallers().getCallers().isEmpty()+","+methodtrace.getCallees().getCallees().isEmpty()
            	               
            	               ; 
            				 System.out.println(methodtrace.getClazzRTMCell().getTraceValue());
            	                s=s.replaceAll("false","0"); 
            	                s=s.replaceAll("true","1"); 
            	                s=s+","+programName+","+methodtrace.getRequirement().getID()+","+methodtrace.getMethod().getID(); 
            	                writer.write(s+"\n");
                      		  System.out.println(s);

                      		  
                      		  i++; 
            	}
            	
            	if(methodtrace.getImplementations().atLeast1GoldT()) {
            		System.out.println("yes");
            	}
              

            }
            System.out.println("over");
        } catch (Exception ex) {
        }
	}
	private static String fillMatrixWith1and0sAndWriteLine(LinkedHashMap<String, String> matrix, String pattern, String s) {
		// TODO Auto-generated method stub
		
		if(pattern.equals("")){
			for(String key: matrix.keySet()) {
				
					matrix.put(key, "0"); 				
					s=s+matrix.get(key)+","; 
			}
			}else {
				matrix.put(pattern, "1"); 
				for(String key: matrix.keySet()) {
					if(matrix.get(key).equals("")) {
						matrix.put(key, "0"); 
					}
					s=s+matrix.get(key)+","; 
				}
				System.out.println("over");
			}
		
		return s; 
	}
	private static void fillMatrix(LinkedHashMap<String, String> matrix) {
		// TODO Auto-generated method stub
		matrix.put("T-x-T", ""); 
		matrix.put("T-x-N", ""); 
		matrix.put("T-x-U", ""); 
		matrix.put("T-x-NT", ""); 
		matrix.put("T-x-UT", ""); 
		matrix.put("T-x-UN", ""); 
		matrix.put("T-x-UNT", ""); 
	
		
		matrix.put("N-x-T", ""); 
		matrix.put("N-x-N", ""); 
		matrix.put("N-x-U", ""); 
		matrix.put("N-x-NT", ""); 
		matrix.put("N-x-UT", ""); 
		matrix.put("N-x-UN", ""); 
		matrix.put("N-x-UNT", "");
		
		
		matrix.put("U-x-T", ""); 
		matrix.put("U-x-N", ""); 
		matrix.put("U-x-U", ""); 
		matrix.put("U-x-NT", ""); 
		matrix.put("U-x-UT", ""); 
		matrix.put("U-x-UN", ""); 
		matrix.put("U-x-UNT", "");
		
		matrix.put("NT-x-T", ""); 
		matrix.put("NT-x-N", ""); 
		matrix.put("NT-x-U", ""); 
		matrix.put("NT-x-NT", ""); 
		matrix.put("NT-x-UT", ""); 
		matrix.put("NT-x-UN", ""); 
		matrix.put("NT-x-UNT", "");


		
		matrix.put("UT-x-T", ""); 
		matrix.put("UT-x-N", ""); 
		matrix.put("UT-x-U", ""); 
		matrix.put("UT-x-NT", ""); 
		matrix.put("UT-x-UT", ""); 
		matrix.put("UT-x-UN", ""); 
		matrix.put("UT-x-UNT", "");
		
		
		matrix.put("UN-x-T", ""); 
		matrix.put("UN-x-N", ""); 
		matrix.put("UN-x-U", ""); 
		matrix.put("UN-x-NT", ""); 
		matrix.put("UN-x-UT", ""); 
		matrix.put("UN-x-UN", ""); 
		matrix.put("UN-x-UNT", "");
		
		
		matrix.put("UNT-x-T", ""); 
		matrix.put("UNT-x-N", ""); 
		matrix.put("UNT-x-U", ""); 
		matrix.put("UNT-x-NT", ""); 
		matrix.put("UNT-x-UT", ""); 
		matrix.put("UNT-x-UN", ""); 
		matrix.put("UNT-x-UNT", "");

   	
		
/*****************************************************/
		matrix.put("T-T-x", ""); 
		matrix.put("T-N-x", ""); 
		matrix.put("T-U-x", ""); 
		matrix.put("T-NT-x", ""); 
		matrix.put("T-UT-x", ""); 
		matrix.put("T-UN-x", ""); 
		matrix.put("T-UNT-x", ""); 
	

		matrix.put("N-T-x", ""); 
		matrix.put("N-N-x", ""); 
		matrix.put("N-U-x", ""); 
		matrix.put("N-NT-x", ""); 
		matrix.put("N-UT-x", ""); 
		matrix.put("N-UN-x", ""); 
		matrix.put("N-UNT-x", "");
		
		
		matrix.put("U-T-x", ""); 
		matrix.put("U-N-x", ""); 
		matrix.put("U-U-x", ""); 
		matrix.put("U-NT-x", ""); 
		matrix.put("U-UT-x", ""); 
		matrix.put("U-UN-x", ""); 
		matrix.put("U-UNT-x", "");
		
		matrix.put("NT-T-x", ""); 
		matrix.put("NT-N-x", ""); 
		matrix.put("NT-U-x", ""); 
		matrix.put("NT-NT-x", ""); 
		matrix.put("NT-UT-x", ""); 
		matrix.put("NT-UN-x", ""); 
		matrix.put("NT-UNT-x", "");


		
		matrix.put("UT-T-x", ""); 
		matrix.put("UT-N-x", ""); 
		matrix.put("UT-U-x", ""); 
		matrix.put("UT-NT-x", ""); 
		matrix.put("UT-UT-x", ""); 
		matrix.put("UT-UN-x", ""); 
		matrix.put("UT-UNT-x", "");
		
		
		matrix.put("UN-T", ""); 
		matrix.put("UN-N", ""); 
		matrix.put("UN-U", ""); 
		matrix.put("UN-NT", ""); 
		matrix.put("UN-UT", ""); 
		matrix.put("UN-UN", ""); 
		matrix.put("UN-UNT", "");
		
		
		matrix.put("UNT-T-x", ""); 
		matrix.put("UNT-N-x", ""); 
		matrix.put("UNT-U-x", ""); 
		matrix.put("UNT-NT-x", ""); 
		matrix.put("UNT-UT-x", ""); 
		matrix.put("UNT-UN-x", ""); 
		matrix.put("UNT-UNT-x", "");
		
		
		
		
		/*****************************************************/
		matrix.put("x-T-T", ""); 
		matrix.put("x-T-N", ""); 
		matrix.put("x-T-U", ""); 
		matrix.put("x-T-NT", ""); 
		matrix.put("x-T-UT", ""); 
		matrix.put("x-T-UN", ""); 
		matrix.put("x-T-UNT", ""); 
	

		matrix.put("x-N-T", ""); 
		matrix.put("x-N-N", ""); 
		matrix.put("x-N-U", ""); 
		matrix.put("x-N-NT", ""); 
		matrix.put("x-N-UT", ""); 
		matrix.put("x-N-UN", ""); 
		matrix.put("x-N-UNT", "");
		
		
		matrix.put("x-U-T", ""); 
		matrix.put("x-U-N", ""); 
		matrix.put("x-U-U", ""); 
		matrix.put("x-U-NT", ""); 
		matrix.put("x-U-UT", ""); 
		matrix.put("x-U-UN", ""); 
		matrix.put("x-U-UNT", "");
		
		matrix.put("x-NT-T", ""); 
		matrix.put("x-NT-N", ""); 
		matrix.put("x-NT-U", ""); 
		matrix.put("x-NT-NT", ""); 
		matrix.put("x-NT-UT", ""); 
		matrix.put("x-NT-UN", ""); 
		matrix.put("x-NT-UNT", "");


		
		matrix.put("x-UT-T", ""); 
		matrix.put("x-UT-N", ""); 
		matrix.put("x-UT-U", ""); 
		matrix.put("x-UT-NT", ""); 
		matrix.put("x-UT-UT", ""); 
		matrix.put("x-UT-UN", ""); 
		matrix.put("x-UT-UNT", "");
		
		
		matrix.put("x-UN-T", ""); 
		matrix.put("x-UN-N", ""); 
		matrix.put("x-UN-U", ""); 
		matrix.put("x-UN-NT", ""); 
		matrix.put("x-UN-UT", ""); 
		matrix.put("x-UN-UN", ""); 
		matrix.put("x-UN-UNT", "");
		
		
		matrix.put("x-UNT-T", ""); 
		matrix.put("x-UNT-N", ""); 
		matrix.put("x-UNT-U", ""); 
		matrix.put("x-UNT-NT", ""); 
		matrix.put("x-UNT-UT", ""); 
		matrix.put("x-UNT-UN", ""); 
		matrix.put("x-UNT-UNT", "");
		
		
	}

}
