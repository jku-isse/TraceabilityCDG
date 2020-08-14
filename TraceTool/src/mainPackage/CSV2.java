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

public class CSV2 {
    static File file = new File("log\\MethodCallsQuantities.txt");



   
    
    static String headers="Index,gold,Program,MethodType,"

    		+ "CallersT,CallersN,CallersU,"
    		+ "CallersCallersT,CallersCallersN,CallersCallersU,"
    		+ "CalleesT,CalleesN,CalleesU,"
    		+ "CalleesCalleesT,CalleesCalleesN,CalleesCalleesU"; 
    
    
	public static void main (String [] args) throws Exception {
		ArrayList<String> programs = new ArrayList<String>();
		
		 FileWriter writer = new FileWriter(file,true);
		 int size=1;
        writer.write(headers+"\n");
			programs.add("chess");
			programs.add("gantt");
			programs.add("itrust");
			programs.add("jhotdraw");
			

			
	int i=0; 
	for(String programName: programs) {
		DatabaseInput.read(programName);
		System.out.println(size);
		 size=generateCSVFileNew(programName,writer,size);
		System.out.println(size);
		System.out.println("over");


	}
    writer.close();

	}
	private static int generateCSVFileNew(String programName, FileWriter writer, int size) throws IOException {
		// TODO Auto-generated method stub

			int i=1; 
			Seeder.seedInputClazzTraceValuesWithDeveloperGold();
			
			 
            for ( MethodRTMCell methodtrace : MethodRTMCell.methodtraces2HashMap.values()) {
            	if(!methodtrace.getGoldTraceValue().equals(RTMCell.TraceValue.UndefinedTrace)) {

       		 		String s= size+","+methodtrace.logGoldTraceValueString()
       		 			
       		 				+","+programName+","; 
       		 		
       		 		if(!methodtrace.getCallers().isEmpty() && !methodtrace.getCallees().isEmpty()) {
       		 			s=s+"Inner,"; 
       		 		}else if( methodtrace.getCallees().isEmpty() ) {
       		 			s=s+"Leaf,"; 
       		 		}else if( methodtrace.getCallers().isEmpty() ) {
       		 			s=s+"Root,"; 
       		 		}else if( methodtrace.getCallers().isEmpty() && methodtrace.getCallees().isEmpty()) {
       		 			s=s+"Isolated,"; 
       		 		}
       		 		
       		 		
       		 		
       		 	
       		 		
       		 		
       		 		
       		 		
			 			
		       		 	counts callers=generateCountsTNU(methodtrace.getCallers());
			 			s=s+callers.amountTperc+","+callers.amountNperc+","+callers.amountUperc+","; 
			 			
	   		 			counts callersCallers=generateCountsTNU(methodtrace.getCallers().getCallers());
	   		 			s=s+callersCallers.amountTperc+","+callersCallers.amountNperc+","+callersCallers.amountUperc+","; 
	   		 		
			 			counts callees=generateCountsTNU(methodtrace.getCallees());
			 			s=s+callees.amountTperc+","+callees.amountNperc+","+callees.amountUperc+","; 
			 			
			 			counts calleesCallees=generateCountsTNU(methodtrace.getCallees().getCallees());
			 			s=s+calleesCallees.amountTperc+","+calleesCallees.amountNperc+","+calleesCallees.amountUperc+","; 
			 			
			 			
			 			
			 			writer.write(s);
			 			writer.write("\n");
			 			
			 			
			 			
			 		
			 			
		 		
			 	
			 			size++; }
            	
            }
            
            System.out.println("size "+size);
            System.out.println("over");
           
            
         return size; 
            
            

	}
	
	
	
	private static counts generateCountsTNU(MethodRTMCellList callers) {
		counts c = counts.countMethods(callers); 
		int total=c.T+c.N+c.U; 
		double amountT=(double)c.T/total*100; 
		double amountN=(double)c.N/total*100; 
		double amountU=(double)c.U/total*100; 
		
		int amountTperc =(int) amountT; 
		int amountNperc=(int) amountN; 
		int amountUperc=(int) amountU; 
		
		c.amountTperc=amountTperc; 
		c.amountNperc=amountNperc; 
		c.amountUperc=amountUperc; 
		
		
		
		
		return c; 
		
		
	}
//	public static void generateCSVFile(String programName, FileWriter writer) {
//		// TODO Auto-generated method stub
//		TraceValidatorPredictionPattern.define(2);
//
//		try {
//			int i=1; 
//			Seeder.seedInputClazzTraceValuesWithDeveloperGold();
//
//            for ( MethodRTMCell methodtrace : MethodRTMCell.methodtraces2HashMap.values()) {
//            	
//            
//            	
//            	LinkedHashMap<String, String> matrix = new LinkedHashMap<String, String>(); 
//            	fillMatrix(matrix); 
//            	if(!methodtrace.getGoldTraceValue().equals(RTMCell.TraceValue.UndefinedTrace)) {
//            		 
//            		 String s= methodtrace.logGoldTraceValueString()+","; 
//            		 
//            		 
//            		 
//            			//INNER METHOD
//         			if (!methodtrace.getCallers().isEmpty() && !methodtrace.getCallees().isEmpty()) {
//         				PredictionPattern p = TraceValidatorPredictionPattern.getInnerPattern(methodtrace.getCallers(), methodtrace.getCallees(), false);
//         				s=fillMatrixWith1and0sAndWriteLine(matrix, p.pattern, s); 
//         			}
//         			//LEAF METHOD
//         			else if (!methodtrace.getCallers().isEmpty() && methodtrace.getCallees().isEmpty() && !methodtrace.getCallers().getCallers().isEmpty()) {
//         				PredictionPattern p =TraceValidatorPredictionPattern.getLeafPattern(methodtrace.getCallers(), methodtrace.getCallers().getCallers(), false);
//         				s=fillMatrixWith1and0sAndWriteLine(matrix, p.pattern,s ); 
//
//         			}
//         			//ROOT METHOD
//         			else if (methodtrace.getCallers().isEmpty() && !methodtrace.getCallees().isEmpty() && !methodtrace.getCallees().getCallees().isEmpty()) {
//         				PredictionPattern p =TraceValidatorPredictionPattern.getRootPattern(methodtrace.getCallees(), methodtrace.getCallees().getCallees(), false);
//         				s=fillMatrixWith1and0sAndWriteLine(matrix, p.pattern,s ); 
//
//         			}
//         			//E ISOLATED
//        			 
//        			else {
//         				s=fillMatrixWith1and0sAndWriteLine(matrix, "",s ); 
//        			}
//            				 
//            				 
//            				 
//            				 
//            				 s=s+methodtrace.getCallers().atLeast1GoldT()
//            	                + ","+ methodtrace.getCallees().atLeast1GoldT()  +","+methodtrace.getCallers().allGoldT() 
//            	               + ","+ methodtrace.getCallees().allGoldT()  +","+ methodtrace.getCallers().atLeast1GoldN()
//            	               + ","+ methodtrace.getCallees().atLeast1GoldN()+","+methodtrace.getCallers().allGoldN()
//            	                + ","+ methodtrace.getCallees().allGoldN()
////            	                +","+ methodtrace.getInterfaces().atLeast1GoldT() 
//            	             
////								  +","+ methodtrace.getImplementations().atLeast1GoldT() 
//								  +","+ methodtrace.getChildren().atLeast1GoldT()  
//								 +","+ methodtrace.getParents().atLeast1GoldT()
////								 +","+ methodtrace.getInterfaces().atLeast1GoldN()  
////								  +","+ methodtrace.getImplementations().atLeast1GoldN() 
//								  +","+ methodtrace.getChildren().atLeast1GoldN() 
//								  +","+ methodtrace.getParents().atLeast1GoldN() 
////								  +","+ methodtrace.getInterfaces().allGoldT() 
////								  +","+ methodtrace.getImplementations().allGoldT() 
//								  +","+ methodtrace.getChildren().allGoldT()  
//								 +","+ methodtrace.getParents().allGoldT() 
////								 +","+ methodtrace.getInterfaces().allGoldN() 
////								  +","+ methodtrace.getImplementations().allGoldN()
//								  +","+ methodtrace.getChildren().allGoldN() 
//								   +","+ methodtrace.getParents().allGoldN()+","+   methodtrace.getParameters().atLeast1T()
//  
//  
// 								+ ","+ methodtrace.getFieldMethods().atLeast1T()   + ","+ methodtrace.getReturnTypeMethod().atLeast1T() 
//            	                +","+methodtrace.getParameters().atLeast1N()   +","+ methodtrace.getFieldMethods().atLeast1N()
//            	               + ","+  methodtrace.getReturnTypeMethod().atLeast1N()   +","+ methodtrace.getParameters().allTs()
//            	              + ","+ methodtrace.getFieldMethods().allTs() + ","+ methodtrace.getParameters().allNs()
//            	                +    ","+ methodtrace.getFieldMethods().allNs() 
//            	                +    ","+ methodtrace.getClazzRTMCell().getTraceValue().equals(RTMCell.TraceValue.NoTrace)
//            	                +    ","+ methodtrace.getClazzRTMCell().getTraceValue().equals(RTMCell.TraceValue.Trace)
//            	                //inner
//            	                +    ","+ (!methodtrace.getCallers().isEmpty() && !methodtrace.getCallees().isEmpty())
//            	                //leaf
//            	               + ","+(!methodtrace.getCallers().isEmpty() && methodtrace.getCallees().isEmpty() && !methodtrace.getCallers().getCallers().isEmpty())
//            	               //root
//            	               +","+(methodtrace.getCallers().isEmpty() && !methodtrace.getCallees().isEmpty() && !methodtrace.getCallees().getCallees().isEmpty()) 
//            				//isolated
//            	               +","+(methodtrace.getCallers().isEmpty() && methodtrace.getCallees().isEmpty())
//            	               
//            	               +","+(methodtrace.getCallers().isEmpty())+","+methodtrace.getCallees().isEmpty()+","+
//            	               methodtrace.getCallers().getCallers().isEmpty()+","+methodtrace.getCallees().getCallees().isEmpty()
//            	               
//            	               ; 
//            				 System.out.println(methodtrace.getClazzRTMCell().getTraceValue());
//            	                s=s.replaceAll("false","0"); 
//            	                s=s.replaceAll("true","1"); 
//            	                s=s+","+programName+","+methodtrace.getRequirement().getID()+","+methodtrace.getMethod().getID(); 
//            	                writer.write(s+"\n");
//                      		  System.out.println(s);
//
//                      		  
//                      		  i++; 
//            	}
//            	
//            	if(methodtrace.getImplementations().atLeast1GoldT()) {
//            		System.out.println("yes");
//            	}
//              
//
//            }
//            System.out.println("over");
//        } catch (Exception ex) {
//        }
//	}
//	private static String fillMatrixWith1and0sAndWriteLine(LinkedHashMap<String, String> matrix, String pattern, String s) {
//		// TODO Auto-generated method stub
//		
//		if(pattern.equals("")){
//			for(String key: matrix.keySet()) {
//				
//					matrix.put(key, "0"); 				
//					s=s+matrix.get(key)+","; 
//			}
//			}else {
//				matrix.put(pattern, "1"); 
//				for(String key: matrix.keySet()) {
//					if(matrix.get(key).equals("")) {
//						matrix.put(key, "0"); 
//					}
//					s=s+matrix.get(key)+","; 
//				}
//				System.out.println("over");
//			}
//		
//		return s; 
//	}
//	private static void fillMatrix(LinkedHashMap<String, String> matrix) {
//		// TODO Auto-generated method stub
//		matrix.put("T-x-T", ""); 
//		matrix.put("T-x-N", ""); 
//		matrix.put("T-x-U", ""); 
//		matrix.put("T-x-NT", ""); 
//		matrix.put("T-x-UT", ""); 
//		matrix.put("T-x-UN", ""); 
//		matrix.put("T-x-UNT", ""); 
//	
//		
//		matrix.put("N-x-T", ""); 
//		matrix.put("N-x-N", ""); 
//		matrix.put("N-x-U", ""); 
//		matrix.put("N-x-NT", ""); 
//		matrix.put("N-x-UT", ""); 
//		matrix.put("N-x-UN", ""); 
//		matrix.put("N-x-UNT", "");
//		
//		
//		matrix.put("U-x-T", ""); 
//		matrix.put("U-x-N", ""); 
//		matrix.put("U-x-U", ""); 
//		matrix.put("U-x-NT", ""); 
//		matrix.put("U-x-UT", ""); 
//		matrix.put("U-x-UN", ""); 
//		matrix.put("U-x-UNT", "");
//		
//		matrix.put("NT-x-T", ""); 
//		matrix.put("NT-x-N", ""); 
//		matrix.put("NT-x-U", ""); 
//		matrix.put("NT-x-NT", ""); 
//		matrix.put("NT-x-UT", ""); 
//		matrix.put("NT-x-UN", ""); 
//		matrix.put("NT-x-UNT", "");
//
//
//		
//		matrix.put("UT-x-T", ""); 
//		matrix.put("UT-x-N", ""); 
//		matrix.put("UT-x-U", ""); 
//		matrix.put("UT-x-NT", ""); 
//		matrix.put("UT-x-UT", ""); 
//		matrix.put("UT-x-UN", ""); 
//		matrix.put("UT-x-UNT", "");
//		
//		
//		matrix.put("UN-x-T", ""); 
//		matrix.put("UN-x-N", ""); 
//		matrix.put("UN-x-U", ""); 
//		matrix.put("UN-x-NT", ""); 
//		matrix.put("UN-x-UT", ""); 
//		matrix.put("UN-x-UN", ""); 
//		matrix.put("UN-x-UNT", "");
//		
//		
//		matrix.put("UNT-x-T", ""); 
//		matrix.put("UNT-x-N", ""); 
//		matrix.put("UNT-x-U", ""); 
//		matrix.put("UNT-x-NT", ""); 
//		matrix.put("UNT-x-UT", ""); 
//		matrix.put("UNT-x-UN", ""); 
//		matrix.put("UNT-x-UNT", "");
//
//   	
//		
///*****************************************************/
//		matrix.put("T-T-x", ""); 
//		matrix.put("T-N-x", ""); 
//		matrix.put("T-U-x", ""); 
//		matrix.put("T-NT-x", ""); 
//		matrix.put("T-UT-x", ""); 
//		matrix.put("T-UN-x", ""); 
//		matrix.put("T-UNT-x", ""); 
//	
//
//		matrix.put("N-T-x", ""); 
//		matrix.put("N-N-x", ""); 
//		matrix.put("N-U-x", ""); 
//		matrix.put("N-NT-x", ""); 
//		matrix.put("N-UT-x", ""); 
//		matrix.put("N-UN-x", ""); 
//		matrix.put("N-UNT-x", "");
//		
//		
//		matrix.put("U-T-x", ""); 
//		matrix.put("U-N-x", ""); 
//		matrix.put("U-U-x", ""); 
//		matrix.put("U-NT-x", ""); 
//		matrix.put("U-UT-x", ""); 
//		matrix.put("U-UN-x", ""); 
//		matrix.put("U-UNT-x", "");
//		
//		matrix.put("NT-T-x", ""); 
//		matrix.put("NT-N-x", ""); 
//		matrix.put("NT-U-x", ""); 
//		matrix.put("NT-NT-x", ""); 
//		matrix.put("NT-UT-x", ""); 
//		matrix.put("NT-UN-x", ""); 
//		matrix.put("NT-UNT-x", "");
//
//
//		
//		matrix.put("UT-T-x", ""); 
//		matrix.put("UT-N-x", ""); 
//		matrix.put("UT-U-x", ""); 
//		matrix.put("UT-NT-x", ""); 
//		matrix.put("UT-UT-x", ""); 
//		matrix.put("UT-UN-x", ""); 
//		matrix.put("UT-UNT-x", "");
//		
//		
//		matrix.put("UN-T", ""); 
//		matrix.put("UN-N", ""); 
//		matrix.put("UN-U", ""); 
//		matrix.put("UN-NT", ""); 
//		matrix.put("UN-UT", ""); 
//		matrix.put("UN-UN", ""); 
//		matrix.put("UN-UNT", "");
//		
//		
//		matrix.put("UNT-T-x", ""); 
//		matrix.put("UNT-N-x", ""); 
//		matrix.put("UNT-U-x", ""); 
//		matrix.put("UNT-NT-x", ""); 
//		matrix.put("UNT-UT-x", ""); 
//		matrix.put("UNT-UN-x", ""); 
//		matrix.put("UNT-UNT-x", "");
//		
//		
//		
//		
//		/*****************************************************/
//		matrix.put("x-T-T", ""); 
//		matrix.put("x-T-N", ""); 
//		matrix.put("x-T-U", ""); 
//		matrix.put("x-T-NT", ""); 
//		matrix.put("x-T-UT", ""); 
//		matrix.put("x-T-UN", ""); 
//		matrix.put("x-T-UNT", ""); 
//	
//
//		matrix.put("x-N-T", ""); 
//		matrix.put("x-N-N", ""); 
//		matrix.put("x-N-U", ""); 
//		matrix.put("x-N-NT", ""); 
//		matrix.put("x-N-UT", ""); 
//		matrix.put("x-N-UN", ""); 
//		matrix.put("x-N-UNT", "");
//		
//		
//		matrix.put("x-U-T", ""); 
//		matrix.put("x-U-N", ""); 
//		matrix.put("x-U-U", ""); 
//		matrix.put("x-U-NT", ""); 
//		matrix.put("x-U-UT", ""); 
//		matrix.put("x-U-UN", ""); 
//		matrix.put("x-U-UNT", "");
//		
//		matrix.put("x-NT-T", ""); 
//		matrix.put("x-NT-N", ""); 
//		matrix.put("x-NT-U", ""); 
//		matrix.put("x-NT-NT", ""); 
//		matrix.put("x-NT-UT", ""); 
//		matrix.put("x-NT-UN", ""); 
//		matrix.put("x-NT-UNT", "");
//
//
//		
//		matrix.put("x-UT-T", ""); 
//		matrix.put("x-UT-N", ""); 
//		matrix.put("x-UT-U", ""); 
//		matrix.put("x-UT-NT", ""); 
//		matrix.put("x-UT-UT", ""); 
//		matrix.put("x-UT-UN", ""); 
//		matrix.put("x-UT-UNT", "");
//		
//		
//		matrix.put("x-UN-T", ""); 
//		matrix.put("x-UN-N", ""); 
//		matrix.put("x-UN-U", ""); 
//		matrix.put("x-UN-NT", ""); 
//		matrix.put("x-UN-UT", ""); 
//		matrix.put("x-UN-UN", ""); 
//		matrix.put("x-UN-UNT", "");
//		
//		
//		matrix.put("x-UNT-T", ""); 
//		matrix.put("x-UNT-N", ""); 
//		matrix.put("x-UNT-U", ""); 
//		matrix.put("x-UNT-NT", ""); 
//		matrix.put("x-UNT-UT", ""); 
//		matrix.put("x-UNT-UN", ""); 
//		matrix.put("x-UNT-UNT", "");
//		
//		
//	}

}
