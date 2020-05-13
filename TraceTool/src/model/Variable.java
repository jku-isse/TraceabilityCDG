package model;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class Variable {
	public Clazz ownerclazz; 
	public String variableName; 
	public Clazz dataType; 
	public Method method;
	
	public static  LinkedHashMap<String, VariableList> variablesReadHashMap = new LinkedHashMap<>(); 
	public static  LinkedHashMap<String, VariableList> variablesWrittenHashMap = new LinkedHashMap<>(); 

	public Variable(Clazz ownerclazz, String variableName, Clazz dataType, Method method) {
		super();
		this.ownerclazz = ownerclazz;
		this.variableName = variableName;
		this.dataType = dataType;
		this.method = method;
	}
	public Variable() {
		// TODO Auto-generated constructor stub
	}
	public Variable(Clazz ownerClass, String fieldname, Clazz fieldTypeDataType) {super();
		this.ownerclazz = ownerClass;
		this.variableName = fieldname;
		this.dataType = fieldTypeDataType;}
	public Variable(String variableName, Clazz variableclazz) {
		super();
		this.ownerclazz = variableclazz;
		this.variableName = variableName;
		
	} 
	  public ClazzRTMCellList getVariableRTMCell(Requirement requirement) {
	    	ClazzRTMCellList cellList = new ClazzRTMCellList();
				cellList.add (ClazzRTMCell.getClazzRTMCell(requirement, this.dataType)); 
			
			return cellList;
		}
}
