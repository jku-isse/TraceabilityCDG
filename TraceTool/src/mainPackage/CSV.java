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
	
	public static boolean AtLeastOneInstance=true; 

    static File file = new File("log\\data.txt");


    CSV csv=new CSV();  
   
    static String headers="gold,Program,MethodType,"

    		+ "CallersT,CallersN,CallersU,"
    		+ "CallersCallersT,CallersCallersN,CallersCallersU,"
    		+ "CalleesT,CalleesN,CalleesU,"
    		+ "CalleesCalleesT,CalleesCalleesN,CalleesCalleesU,CompleteCallersCallees,"
    		+ "classGold"
    		
    		; 
    
    static String headersAtLeastOneInstance="gold,MethodType,"

    		+ "CallersT,CallersN,CallersU,"
    		+ "CallersCallersT,CallersCallersN,CallersCallersU,"
    		+ "CalleesT,CalleesN,CalleesU,"
    		+ "CalleesCalleesT,CalleesCalleesN,CalleesCalleesU"
    		
    		; 
	public static void main (String [] args) throws Exception {
		ArrayList<String> programs = new ArrayList<String>();
		 FileWriter writer = new FileWriter(file,true);
		 if(!AtLeastOneInstance)
			 writer.write(headers+"\n");
		 else 
			 writer.write(headersAtLeastOneInstance+"\n");

			programs.add("chess");
			programs.add("gantt");
			programs.add("itrust");
			programs.add("jhotdraw");
			
//			programs.add("vod");
			System.out.println("countNoCalleesU,countLowCalleesU,countMediumCalleesU,countHighCalleesU,countNoCallersU,countLowCallersU,countMediumCallersU,"
					+ "countHighCallersU,NoCallersUAndNoCalleesU,LowCombination,MediumCombination,HighCombination"); 
			
	int i=0; 
	for(String programName: programs) {
		DatabaseInput.read(programName);
		generateCSVFile("",writer);
	}
    writer.close();

	}
	private static void generateCSVFile(String programName, FileWriter writer) throws IOException {
		// TODO Auto-generated method stub
			counts callers = new counts(); 
			counts callersCallers= new counts(); 
			counts callees= new counts(); 
			counts calleesCallees= new counts(); 
			ArrayList<String> programs = new ArrayList<String>();

	
			int i=1; 
			Seeder.seedInputClazzTraceValuesWithDeveloperGold();
			int countNoCallersU=0; int countLowCallersU=0; int countMediumCallersU=0; int countHighCallersU=0; 
			int countNoCalleesU=0; int countLowCalleesU=0; int countMediumCalleesU=0; int countHighCalleesU=0; 
			int NoCallersUAndNoCalleesU=0; int LowCombination=0; int MediumCombination=0;int HighCombination=0;
			int size=0; 
			int k=0; 
            for ( MethodRTMCell methodtrace : MethodRTMCell.methodtraces2HashMap.values()) {
            	if(!methodtrace.getGoldTraceValue().equals(RTMCell.TraceValue.UndefinedTrace)) {

       		 		String s= methodtrace.logGoldTraceValueString()+","; 
       		 		if(!AtLeastOneInstance)	
       		 			s=s+programName+","; 
       		 		
       		 		if(!methodtrace.getCallers().isEmpty() && !methodtrace.getCallees().isEmpty()) {
       		 			s=s+"Inner,"; 
       		 		}else if( methodtrace.getCallees().isEmpty() ) {
       		 			s=s+"Leaf,"; 
       		 		}else if( methodtrace.getCallers().isEmpty() ) {
       		 			s=s+"Root,"; 
       		 		}else if( methodtrace.getCallers().isEmpty() && methodtrace.getCallees().isEmpty()) {
       		 			s=s+"Isolated,"; 
       		 		}
       		 		
       		 		
       		 		
       		 	
       		 		
       		 		
       		 		
       		 		if(!AtLeastOneInstance) {
	       		 		 callers=generateCountsTNU(methodtrace.getCallers());
	   		 			 callersCallers=generateCountsTNU(methodtrace.getCallers().getCallers());
	   		 			 callees=generateCountsTNU(methodtrace.getCallees());
			 			 calleesCallees=generateCountsTNU(methodtrace.getCallees().getCallees());
       		 		}else if(AtLeastOneInstance) {
       		 			 
	       		 		 callers=generateCountsTNUAtLeastOneInstance(methodtrace.getCallers());
	   		 			 callersCallers=generateCountsTNUAtLeastOneInstance(methodtrace.getCallers().getCallers());
	   		 			 callees=generateCountsTNUAtLeastOneInstance(methodtrace.getCallees());
			 			 calleesCallees=generateCountsTNUAtLeastOneInstance(methodtrace.getCallees().getCallees());
       		 		}
			 			
		       		 	
			 			
			 			s=s+callers.amountT+","+callers.amountN+","+callers.amountU+","; 
	   		 			s=s+callersCallers.amountT+","+callersCallers.amountN+","+callersCallers.amountU+","; 
			 			s=s+callees.amountT+","+callees.amountN+","+callees.amountU+","; 
			 			s=s+calleesCallees.amountT+","+calleesCallees.amountN+","+calleesCallees.amountU; 
			 			
			 			if(callers.amountU.equals("-1") && callees.amountU.equals("-1") && !AtLeastOneInstance) {
			 				s=s+"1,"; 
			 			}else if(!AtLeastOneInstance) {
			 				s=s+"0,"; 
			 			}
			 			if(!AtLeastOneInstance)
			 				s=s+","+methodtrace.getClazzRTMCell().getTraceValue(); 
			 			
			 			s=s+"\n"; 
						writer.write(s);
			 			
			 			
			 			//SEPARATE CALLERS AND CALLEES 
			 			
			 			if(callers.amountU.equals("-1")) countNoCallersU++; 
			 			else if(callers.amountU.equals("Low")) countLowCallersU++;
			 			else if(callers.amountU.equals("Medium")) countMediumCallersU++;
			 			else if(callers.amountU.equals("High")) countHighCallersU++; 
			 			
			 			
			 			if(callees.amountU.equals("-1")) countNoCalleesU++; 
			 			else if(callees.amountU.equals("Low")) countLowCalleesU++;
			 			else if(callees.amountU.equals("Medium")) countMediumCalleesU++;
			 			else if(callees.amountU.equals("High")) countHighCalleesU++; 
			 			
			 			
			 			if(callers.amountU.equals("-1") && callees.amountU.equals("-1"))  {
			 				NoCallersUAndNoCalleesU++; 
			 			}
			 			else if ((callers.amountU.equals("Low") && (callees.amountU.equals("Low") || callees.amountU.equals("-1")))
			 					|| (callees.amountU.equals("Low") && callers.amountU.equals("-1"))
			 					) {
			 				LowCombination++; 
			 			}
			 			else if ((callers.amountU.equals("Medium") && (callees.amountU.equals("Medium") || callees.amountU.equals("Low")|| callees.amountU.equals("-1")))
			 					|| (callees.amountU.equals("Medium") && (callers.amountU.equals("Low")||callers.amountU.equals("-1")))
			 					) {
			 				MediumCombination++; 
			 			}
			 			else if ((callers.amountU.equals("High") && (callees.amountU.equals("High") ||callees.amountU.equals("Medium") || callees.amountU.equals("Low")|| callees.amountU.equals("-1")))
			 					|| (callees.amountU.equals("High") && (callers.amountU.equals("Medium")||callers.amountU.equals("Low")||callers.amountU.equals("-1")))
			 					) {
			 				HighCombination++; 
			 			}
			 			
			 		
			 			
		 		
			 	
			 			size++; 
			 			}
            	k++; 
            	
            }
            double countNoCalleesUdouble=(double)countNoCalleesU/size*100; 
            int countNoCalleesUperc= (int) Math.round(countNoCalleesUdouble); 
            
            
            double countLowCalleesUdouble=(double)countLowCalleesU/size*100; 
            int countLowCalleesUperc= (int) Math.round(countLowCalleesUdouble); 

            
            double countMediumCalleesUdouble=(double)countMediumCalleesU/size*100; 
            int countMediumCalleesUperc= (int) Math.round(countMediumCalleesUdouble); 
            
            
            double countHighCalleesUdouble =(double)countHighCalleesU/size*100; 
            int countHighCalleesUperc= (int) Math.round(countHighCalleesUdouble); 

            double countNoCallersUdouble=(double)countNoCallersU/size*100; 
            int countNoCallersUperc=(int) Math.round(countNoCallersUdouble); 
            
            
            double countLowCallersUdouble=(double)countLowCallersU/size*100; 
            int countLowCallersUperc= (int) Math.round(countLowCallersUdouble); 
            
            
            double countMediumCallersUdouble=(double)countMediumCallersU/size*100;
            int countMediumCallersUperc=(int)Math.round(countMediumCallersUdouble); 
            
            double countHighCallersUdouble=(double)countHighCallersU/size*100;
            int countHighCallersUperc=(int)Math.round(countHighCallersUdouble); 
            /******/
            double NoCallersUAndNoCalleesUdouble=(double)NoCallersUAndNoCalleesU/size*100;
            int NoCallersUAndNoCalleesUperc=(int)Math.round(NoCallersUAndNoCalleesUdouble); 
            
            double LowCombinationdouble=(double)LowCombination/size*100;
            int LowCombinationperc=(int)Math.round(LowCombinationdouble); 
            
            double MediumCombinationdouble=(double)MediumCombination/size*100;
            int MediumCombinationperc=(int)Math.round(MediumCombinationdouble);
            
            double HighCombinationdouble=(double)HighCombination/size*100;
            int HighCombinationperc=(int)Math.round(HighCombinationdouble);
            
           
            
            System.out.println(countNoCalleesUperc+","+countLowCalleesUperc+","+countMediumCalleesUperc+","+countHighCalleesUperc+","+countNoCallersUperc+","+countLowCallersUperc+","+countMediumCallersUperc+","+countHighCallersUperc+","+
		 			NoCallersUAndNoCalleesUperc+","+LowCombinationperc+","+MediumCombinationperc+","+HighCombinationperc);
            
            

	}
	

	private static counts generateCountsTNUAtLeastOneInstance(MethodRTMCellList callees) {
		// TODO Auto-generated method stub
		counts c = counts.countMethods(callees); 
		if(c.T>=1) c.amountT="1"; 
		else if(c.T==0) c.amountT="0"; 
		
		if(c.N>=1) c.amountN="1"; 
		else if(c.N==0) c.amountN="0"; 
		
		if(c.U>=1) c.amountU="1"; 
		else if(c.U==0)  c.amountU="0"; 
		
		return c; 
	}
	//////////////////////////////////////////////////////////////////////
	private static counts generateCountsTNU(MethodRTMCellList callers) {
		counts c = counts.countMethods(callers); 


		if(c.T>=4) c.amountT="High"; 
		else if(c.T>1 && c.T<=3) c.amountT="Medium"; 
		else if(c.T==1) c.amountT="Low"; 
		
		
		if(c.N>=5) c.amountN="High"; 
		else if(c.N>1 && c.N<=4) c.amountN="Medium"; 
		else if(c.N==1) c.amountN="Low"; 
		
		
		if(c.U>=6) c.amountU="High"; 	
		else if(c.U>1 && c.U<=5) c.amountU="Medium"; 	
		else if(c.U==1) c.amountU="Low"; 
		
		/*************************************************/
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
	

}
