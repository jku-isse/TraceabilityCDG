package traceRefiner;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import model.Clazz;
import model.MethodList;
import model.MethodRTMCell;
import model.MethodRTMCellList;
import model.RTMCell;
import model.Variable;
import model.VariableList;
import traceValidator.TraceValidatorPredictionPattern;

public class TraceRefiner2 {

	static public void step1_classNs2MethodNs() {
//		Map<String, MethodRTMCell> treeMap = new TreeMap<String,MethodRTMCell>(MethodRTMCell.methodtraces2HashMap);

		for (MethodRTMCell methodtrace : MethodRTMCell.methodtraces2HashMap.values()) {
			if (methodtrace.getClazzRTMCell().getTraceValue().equals(RTMCell.TraceValue.NoTrace))
				methodtrace.setPrediction(TraceRefinerPredictionPattern.Step1ClassNoTraceImpliesMethodNoTracePattern);
		}
		
	}

	static public void step2_propagateMethodNs() {
		int iteration = 0;
//		Map<String, MethodRTMCell> treeMap = new TreeMap<String,MethodRTMCell>(MethodRTMCell.methodtraces2HashMap);

		do {
			RTMCell.modified = false;

			
				for (MethodRTMCell methodtrace : MethodRTMCell.methodtraces2HashMap.values()) {
					if (methodtrace.getPredictedTraceValue().equals(RTMCell.TraceValue.UndefinedTrace)) {
						if (methodtrace.getCallers().allPredictedNs())
							methodtrace.setPrediction(TraceRefinerPredictionPattern.Step2AllCallersDoNoTracePattern);
						else if (methodtrace.getCallees().allPredictedNs())
							methodtrace.setPrediction(TraceRefinerPredictionPattern.Step2AllCalleesDoNoTracePattern);
						
						
//						//added these three 
//						else if (methodtrace.getVariableReads().allPredictedNs())
//							methodtrace.setPrediction(TraceRefinerPredictionPattern.Step2AllParametersDoNoTracePattern);
//						else if (methodtrace.getVariableWrites().allPredictedNs())
//							methodtrace.setPrediction(TraceRefinerPredictionPattern.Step2AllFieldMethodsDoNoTracePattern);
						//added these three 
//						
//						
//						else if (methodtrace.getParameters().atLeast1N())
//							methodtrace.setPrediction(TraceRefinerPredictionPattern.Step2AllParametersMethodsDoNoTracePattern);
//						else if (methodtrace.getFieldMethods().atLeast1N())
//							methodtrace.setPrediction(TraceRefinerPredictionPattern.Step2AllFieldMethodsDoNoTracePattern);			
//						else if (methodtrace.getReturnTypeMethod().atLeast1N())
//							methodtrace.setPrediction(TraceRefinerPredictionPattern.Step2ReturnTypeDoNoTracePattern);	
						
					
					}
				}
			
			iteration++;
		} while (RTMCell.modified);
	}

	static public void step3_classTs2MethodTs() {
//		Map<String, MethodRTMCell> treeMap = new TreeMap<String,MethodRTMCell>(MethodRTMCell.methodtraces2HashMap);

		for (MethodRTMCell methodtrace : MethodRTMCell.methodtraces2HashMap.values()) {
			String reqMethod = methodtrace.getRequirement().ID+"-"+methodtrace.getMethod().ID;
//			System.out.println(reqMethod);
			if (methodtrace.getTraceValue().equals(RTMCell.TraceValue.UndefinedTrace) &&
				methodtrace.getClazzRTMCell().getTraceValue().equals(RTMCell.TraceValue.Trace)) {

				//XXX getclasses from first condition missing
				if (methodtrace.getCallers().getClazzes().atLeast1T() &&
						methodtrace.getCallees().getClazzes().atLeast1T() &&
						methodtrace.getCallers().noPredictedNs() &&     //supposed to be noNs
						methodtrace.getCallees().noPredictedNs()) {
					methodtrace.setPrediction(TraceRefinerPredictionPattern.Step3CallerAndCalleeClassTracesImpliesMethodTracePattern);
				} else if (methodtrace.getCallees().isEmpty() &&
						methodtrace.getCallers().getClazzes().atLeast1T() &&
						methodtrace.getCallers().noPredictedNs()) {
					methodtrace.setPrediction(TraceRefinerPredictionPattern.Step3LeafCallerClassTracesImpliesMethodTracePattern);
				} else if (methodtrace.getCallers().getClazzes().allTs() &&
						methodtrace.getCallers().noPredictedNs() &&
						methodtrace.getCallees().noPredictedNs()) {
					methodtrace.setPrediction(TraceRefinerPredictionPattern.Step3CallerClassOnlyTracesImpliesMethodTracePattern);
				} else if (methodtrace.getCallees().getClazzes().allTs() &&
						methodtrace.getCallers().noPredictedNs() &&
						methodtrace.getCallees().noPredictedNs()) {
					methodtrace.setPrediction(TraceRefinerPredictionPattern.Step3CalleeClassOnlyTracesImpliesMethodTracePattern);
				}	
				
			}
		}
	}

	static public void step4_propagateMethodTs() {
		int iteration=0;
//		Map<String, MethodRTMCell> treeMap = new TreeMap<String,MethodRTMCell>(MethodRTMCell.methodtraces2HashMap);

		do {
			RTMCell.modified=false;

				for (MethodRTMCell methodtrace : MethodRTMCell.methodtraces2HashMap.values()) {
					String reqMethod=methodtrace.getRequirement().ID+"-"+methodtrace.getMethod().ID; 
//					System.out.println(methodtrace.getRequirement().ID+"-"+methodtrace.getMethod().ID);
					
					if (methodtrace.getPredictedTraceValue().equals(RTMCell.TraceValue.UndefinedTrace)) {
						if (methodtrace.getCallees().isEmpty() && methodtrace.getCallers().atLeast1PredictedT())
							methodtrace.setPrediction(TraceRefinerPredictionPattern.Step4LeafCallerTracesImpliesMethodTracesPattern);
						else if ( methodtrace.getCallers().allPredictedTs())
							methodtrace.setPrediction(TraceRefinerPredictionPattern.Step4CallerTracesImpliesMethodTracesPattern);
						else if ( methodtrace.getCallees().allPredictedTs())
							methodtrace.setPrediction(TraceRefinerPredictionPattern.Step4CalleeTracesImpliesMethodTracesPattern);
//						//added these three predictions 
//						else if ( methodtrace.getVariableReads().allPredictedTs())
//							methodtrace.setPrediction(TraceRefinerPredictionPattern.Step4ParameterImpliesMethodTracesPattern);
//						else if ( methodtrace.getVariableWrites().allPredictedTs())
//							methodtrace.setPrediction(TraceRefinerPredictionPattern.Step4FieldMethodsImpliesMethodTracesPattern);
//	
//						else 	if (methodtrace.getParameters().atLeast1T())
//							methodtrace.setPrediction(TraceRefinerPredictionPattern.Step4ParametersImpliesMethodTracesPattern);
//						else if (methodtrace.getFieldMethods().atLeast1T())
//							methodtrace.setPrediction(TraceRefinerPredictionPattern.Step4FieldMethodsImpliesMethodTracesPattern);			
//						else if (methodtrace.getReturnTypeMethod().atLeast1T())
//							methodtrace.setPrediction(TraceRefinerPredictionPattern.Step4ReturnTypeImpliesMethodTracesPattern);		
					
					}
				}
			
			
			iteration++;
		} while (RTMCell.modified);
	}

	public static void step5_variablePredictions() {

		
		int iteration=0;

		do {
			RTMCell.modified=false;
		
		
		for (MethodRTMCell methodtrace : MethodRTMCell.methodtraces2HashMap.values()) {
			
			for(Variable variable: methodtrace.getMethod().getVariableReads()) {
				String variableName= variable.variableName; 
				 Clazz ownerclazz = variable.ownerclazz; 
					String mykey =""; 
					if(ownerclazz!=null)
					       mykey = variableName+"-"+ownerclazz.ID; 

					 VariableList variablesWritten= Variable.variablesWrittenHashMap.get(mykey); 
					 if(variablesWritten!=null) {
						 MethodRTMCellList methodsVariablesWritten = variablesWritten.getMethods(methodtrace.getRequirement()); 
						 if(!methodsVariablesWritten.atLeast1PredictedT() && methodtrace.getPredictedTraceValue().equals(RTMCell.TraceValue.UndefinedTrace)) {
								methodtrace.setPrediction(TraceRefinerPredictionPattern.Step5AllVariablesWrittenTrace);
						 }else if (methodsVariablesWritten.atLeast1PredictedN() && methodtrace.getPredictedTraceValue().equals(RTMCell.TraceValue.UndefinedTrace)) {
								methodtrace.setPrediction(TraceRefinerPredictionPattern.Step5AllVariablesWrittenNoTrace);

						 }
						 
						 VariableList variablesRead = Variable.variablesReadHashMap.get(mykey); 
						 if(variablesWritten!=null) {
							 MethodRTMCellList methodsVariablesRead = variablesRead.getMethods(methodtrace.getRequirement()); 
							 if(!methodsVariablesRead.atLeast1PredictedT() && methodtrace.getPredictedTraceValue().equals(RTMCell.TraceValue.UndefinedTrace)) {
									methodtrace.setPrediction(TraceRefinerPredictionPattern.Step5AllVariablesReadTrace);
							 }else if (methodsVariablesRead.atLeast1PredictedN() && methodtrace.getPredictedTraceValue().equals(RTMCell.TraceValue.UndefinedTrace)) {
									methodtrace.setPrediction(TraceRefinerPredictionPattern.Step5AllVariablesReadNoTrace);

							 }
					 
					 
				}
				
			
			}
			}
		
	}
		iteration++;
	}while (RTMCell.modified);
		
	}
}
