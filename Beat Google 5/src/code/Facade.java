package code;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.*;

//import javax.servlet.ServletContext;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;

public class Facade {
	private  ArrayList<Tree> rsA ;
//	private  ArrayList<Tree> rsB ;
	private KeywordS kcn;
	private KeywordS ken;
	private KeywordS kcs;
	private KeywordS kes;
	
	public void load(){
		kcn = new KeywordS("keywordChineseNormal.txt", false);
		ken = new KeywordS("keywordEnglishNormal.txt", false);
		kcs = new KeywordS("keywordChineseSearch.txt", false);
    	kes = new KeywordS("keywordEnglishSearch.txt",true);

		for(Keyword k: kcs.getKeys()){
			kcn.add(k);
		}
		for(Keyword k: kes.getKeys()){
			ken.add(k);
		}
		for(Keyword k: kcn.getKeys()){
			ken.add(k);
		}

		System.out.println("Load complete!");
		
	}

	/* public void print(){
		for(Keyword k: kcs.getKeys()){
			System.out.println(k.getName() + " " + k.weight + " |");
		}
		for(Keyword k: kes.getKeys()){
			System.out.println(k.getName() + " " + k.weight + " |");
		}
		for(Keyword k: kcn.getKeys()){
			System.out.println(k.getName() + " " + k.weight + " |");
		}
		for(Keyword k: ken.getKeys()){
			System.out.println(k.getName() + " " + k.weight + " |");
		}
	} */

    public ArrayList<Tree> operate(String st) throws InterruptedException, UnsupportedEncodingException 
	{
	//	Keyword key = new Keyword(st, 5);
	//	KeywordS ks = new KeywordS();
	//	String st2 = "";
	/* 	boolean chiornot = false;

		if(st.matches("[\\u4E00-\\u9FA5]+"))  
    	{  
			int index = (int)(Math.random()*kcs.getKeys().size());
			st2 = kcs.getKey(index).getName();
			chiornot = true;
    	}else{
			int index = (int)(Math.random()*kes.getKeys().size());
			st2 = kes.getKey(index).getName();
		}
			
		ks.getKeys().add(key);
		 */

		System.out.println("Searching...");

		rsA = searchKeyword(st);
	//	rsB = searchKeyword(st2);

	/* 	try{
			for(Tree t: rsB){
//				System.out.println(">>>+" + t.getRoot().getPage().getTitle());
				if(! t.getRoot().getPage().findKeyword(key.getName())){
					t = null;
				}
			}
		} 
		catch (FileNotFoundException f){
   
	   }catch (IOException e) 
	   {
//		e.printStackTrace();
	   } */

	   	rsA.remove(null);
	//	rsB.remove(null);

	   	try{
	//		if(chiornot){
				for(int i = 0; i < rsA.size(); i++){
					Tree t = rsA.get(i);
	//			
	//				t.setPostOrderScore(kcn);
					t.setPostOrderScore(ken);
					System.out.println(">>>+" + t.getRoot().getPage().getTitle());
					System.out.println(">>>+>>" + t.getRoot().getPage().getScore());
				}
		/* 	}else{
				for(Tree t: rsA){
	//			
					System.out.println("ENG.");
					t.setPostOrderScore(ken);
					System.out.println(">>>+" + t.getRoot().getPage().getTitle());
					System.out.println(">>>+>>" + t.getRoot().getPage().getScore());
				}
			}
			 */
		} 
		catch (FileNotFoundException f){
		   
	   	}catch (IOException e) 
	   	{
   //		e.printStackTrace();
	   	}
	   	System.out.println("rsA finished.");
	//   key.weightPlus();
	//   System.out.println(key.weight);

	/*  try{
			for(Tree t: rsB){
//				
				t.setPostOrderScore(ks);
				System.out.println(">>>+" + t.getRoot().getPage().getTitle());
				System.out.println(">>>+>>" + t.getRoot().getPage().getScore());
			}
		} 
		catch (FileNotFoundException f){
	   
   		}catch (IOException e) 
   		{
//		e.printStackTrace();
   		} */
	//		System.out.println("Finish Computing!" + ("-").repeat(40));

	//mix

		/* for(Tree t: rsB){
			rsA.add(t);
		}

		rsB.clear();
 */

			class treeSort implements Comparator<Tree>{
				public int compare(Tree a, Tree b) 
					{ 
						double result = b.getTreeScore() - a.getTreeScore(); 
						return (int) result;
					} 
			}

		Collections.sort(rsA, new treeSort());
			
		/* 	System.out.println("Printing result:\n" + ("-").repeat(40));
			for(Tree t: rsA){
				t.printRootInfo();
			}
 */
		/* 	System.out.println("\n" + "*".repeat(60));
			for(Keyword k: ks.getKeys()){
				System.out.printf("%-20s%3s\n", k.name, k.weight);
			}
 */
		//	ks.getKeys().remove(key);
		return rsA;
			
		
	}

	public static ArrayList<Tree> searchKeyword(String word) throws InterruptedException 
	{	
		ArrayList<Tree> resultArray = new ArrayList<>();

		try 
		{
			String st1 = java.net.URLEncoder.encode(word, "UTF-8");

			
			GoogleQuery g1 = new GoogleQuery(st1,100);

			HashMap<String, String> query1 = g1.query();

			//request.setAttribute("query", rs);
			for(Entry<String, String> entry : query1.entrySet()) {
				String key = entry.getKey();
				String value = entry.getValue();
				Page p = new Page(key, value);
				Tree t = new Tree(p);

		//		System.out.println(key + "\nhref: " + value);

				resultArray.add(t);
		 	}

		//	System.out.println("-".repeat(40));

			for(Tree t: resultArray){
				Node n = t.getRoot();
				n.setSubWebToChild();
			}
		} 
		catch (IOException e) 
		{
			//e.printStackTrace();
		}

		return resultArray;
	}

/*	public void search(){
		try 
		{
			Scanner s = new Scanner(System.in);
			System.out.println("input:");
			String st = s.nextLine();
			st = java.net.URLEncoder.encode(st, "UTF-8");
			
			GoogleQuery g = new GoogleQuery(st);
			g.query();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	} */
}

	
	