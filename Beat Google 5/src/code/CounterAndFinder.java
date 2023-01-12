package code;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.util.ArrayList;

public class CounterAndFinder{
	private String urlStr;
    private String content;

	public CounterAndFinder (String urlStr){
    	this.urlStr = urlStr;
    }
    
	public String fetchContent() throws IOException{
		URL url = new URL(this.urlStr);
		URLConnection conn = url.openConnection();
		InputStream in = conn.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
	
		String retVal = "";
	
		String line = null;
		
		while ((line = br.readLine()) != null){
		    retVal = retVal + line + "\n";
		}
		
		return retVal;
    }

	public int last(char c, String P){
    	// Bonus: Implement last occurence function
    	for(int i = P.length() - 1; i >= 0; i--) {
    		if(c==P.charAt(i)) {
    			return i;
    		}
    	}
    	
        return -1;
    }

    public int min(int a, int b){
        if (a < b)
            return a;
        else if (b < a)
            return b;
        else 
            return a;
    }
    
    public int CountBoyerMoore(String T, String P){
        int a = P.length() -1;
        int b = P.length() -1;
        int retVal = 0;
        while(a < T.length()) {
        	if(T.charAt(a) == P.charAt(b)) {
        		if(b == 0) {
        			retVal++;
        			//a += P.length()-1;
        			a++;
        		}else {
        			a--;
        			b--;
        		}
        	}else {
        		a = a+P.length() - min(b, 1+last(T.charAt(a), P));
        		b = P.length()-1;
        	}
        }
        // Bonus: Implement Boyer-Moore Algorithm     

        return retVal;
    }

    public int countKeyword(String keyword) throws IOException{
		if (content == null){
		    content = fetchContent();
		}
		
		//To do a case-insensitive search, we turn the whole content and keyword into upper-case:
		content = content.toUpperCase();
		keyword = keyword.toUpperCase();
	
		// int retVal = 0; 
		// 1. calculates appearances of keyword (Bonus: Implement Boyer-Moore Algorithm)
		/* 
		int n = content.length();
		int m = keyword.length(); */

		return CountBoyerMoore(content, keyword);
    }
/* 
	public boolean FindBoyerMoore(String T, String P){
        int a = P.length() -1;
        int b = P.length() -1;
        while(a < T.length()) {
        	if(T.charAt(a) == P.charAt(b)) {
        		if(b == 0) {
        			return true;
        		}else {
        			a--;
        			b--;
        		}
        	}else {
        		a = a+P.length() - min(b, 1+last(T.charAt(a), P));
        		b = P.length()-1;
        	}
        }
        // Bonus: Implement Boyer-Moore Algorithm     

        return false;
    }

	public boolean findKeyword(String keyword) throws IOException{
		if (content == null){
		    content = fetchContent();
		}
		
		//To do a case-insensitive search, we turn the whole content and keyword into upper-case:
		content = content.toUpperCase();
		keyword = keyword.toUpperCase();
	
		// int retVal = 0; 
		// 1. calculates appearances of keyword (Bonus: Implement Boyer-Moore Algorithm)
		/* 
		int n = content.length();
		int m = keyword.length();
 
		return FindBoyerMoore(content, keyword);
		
    } */
   
}
