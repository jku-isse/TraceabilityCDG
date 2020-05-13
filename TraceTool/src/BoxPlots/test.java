package BoxPlots;


//Initial Template for Java
import java.io.*;
import java.util.*;
//Position this line where user code will be pasted.
// Driver class
class test {
    
    // Driver code
	public static void main (String[] args) {
		
		boolean res=solution ("50", "??48"); 
		System.out.println(res);
	}
	private static boolean solution(String s1, String s2) {
		// TODO Auto-generated method stub
		char[] s1chars = s1.toCharArray(); 
		char[] s2chars = s2.toCharArray(); 
		
		
		String s1String=func(s1); 
		String s2String=func(s2); 
		System.out.println(s1String+"-");
		System.out.println(s2String);

		if(s1String.length()!=s2String.length()) return false; 
		else {
			for(int i=0; i<s1String.length(); i++) {
				if((s1String.charAt(i)==s2String.charAt(i))  || (s1String.charAt(i)==' ' && s2String.charAt(i)!=' ') || (s1String.charAt(i)!=' ' && s2String.charAt(i)==' ')) {
					
				}else {
					return false; 
				}
			}
		}

		return true; 
	}

			private static String func(String s1) {
				
				
			String s1String=""; 
			String digit=""; 
			
			
			
			boolean isdigit=false; 
			
			char[] s1chars = s1.toCharArray(); 
			
			for(int i=0; i<s1chars.length; i++) {
				if(Character.isDigit(s1.charAt(i)) && isdigit==false) {
					digit=digit+s1.charAt(i); 
					isdigit=true; 
					
				}else if(Character.isDigit(s1.charAt(i)) && isdigit==true) {
					digit=digit+s1.charAt(i); 
					isdigit=true; 
					
				}
				else if(isdigit==false &&  !Character.isDigit(s1.charAt(i))){
					s1String=s1String+s1.charAt(i); 
				}
				
				else if(!Character.isDigit(s1.charAt(i)) && isdigit==true) {
					int digINT=Integer.parseInt(digit);
					for(int j=0; j<digINT; j++)	s1String=s1String+" "; 
					digit=""; 
					isdigit=false; 
					
					s1String=s1String+s1.charAt(i); 
		
					digit=""; 
					isdigit=false; 
					
				}
				
				if(isdigit==true && i==s1.length()-1) {
					int digINT=Integer.parseInt(digit);
					for(int j=0; j<digINT; j++)	s1String=s1String+" "; 
				}
				
				
			}
			return s1String; 
			}
	private static int solution(int[] A) {
		HashMap<Integer, Integer> myhashmap = new HashMap<Integer, Integer>(); 
		// TODO Auto-generated method stub
		myhashmap.put(A.length-1, 1); 
//		System.out.println(A.length);
		if(isdescending(A) && A.length!=1) return 0; 
		if(A.length==1) return 0; 
		else {
			int slice =0; 
			int where=0; 
			for(int j=0; j<A.length; j++) {
//				System.out.println(A[j]);
			for(int i=j; i<A.length-1; i++) {
				if(A[i]<A[i+1]) {
					where=j; 
					slice ++; 
				}else {
					myhashmap.put(j, slice+1); 
					slice =0; 
					break; 
				}
			}
		}
//			System.out.println(where);
			myhashmap.put(where, slice+1); 
		}
		

		
		for (int key: myhashmap.keySet()){
            
            int value = myhashmap.get(key);  
            System.out.println(key + " " + value);  
} 
		int max=0; 
		int beginning=-1; 
		for (int key : myhashmap.keySet()) {
		   if(max<myhashmap.get(key)) {
			   max=myhashmap.get(key); 
			   beginning=key; 
		   }
		}
		return beginning; 
	}

	private static boolean isdescending(int[] a) {
		for (int i = 0; i < a.length-1; i++) {
	    if (a[i] < a[i+1]) {
	        return false;
	    }
	}
	return true;}
	


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	

}

