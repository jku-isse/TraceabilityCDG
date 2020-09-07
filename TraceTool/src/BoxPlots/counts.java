package BoxPlots;

import model.MethodRTMCellList;
import model.RTMCell;

public class counts {
	public int T=0; 
	public int N=0; 
	public int U=0; 
	public String amountT="-1"; 
	public String amountN="-1"; 
	public String amountU="-1"; 

	public int amountTperc=0; 
	public int amountNperc=0; 
	public int amountUperc=0; 
	
	public String amountTQuantity=""; 
	public String amountNQuantity=""; 
	public String amountUQuantity=""; 
	
	
	public static counts countMethods(MethodRTMCellList methodsRTMs){
		counts counts= new counts(); 
		for(RTMCell methodRTM: methodsRTMs) {
			if(methodRTM.getGoldTraceValue().equals(RTMCell.TraceValue.Trace)) {
				counts.T++; 
			}else if(methodRTM.getGoldTraceValue().equals(RTMCell.TraceValue.NoTrace)) {
				counts.N++; 
			}else if(methodRTM.getGoldTraceValue().equals(RTMCell.TraceValue.UndefinedTrace)) {
				counts.U++; 

			}
		}
		
		
		return counts;
		
	}
	
}
