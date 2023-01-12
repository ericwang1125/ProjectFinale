
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
	

    public ArrayList<Tree> operate(String st) throws InterruptedException, UnsupportedEncodingException 
	{
		KeywordS ks = new KeywordS();

		KeywordS.getKeys().add(new Keyword(st, 5, 0));

		System.out.println("Searching...");
			
		rsA = searchMentalChickenSoup(st);
		rsA.remove(null);

	   try{
			for(Tree t: rsA){
	//			System.out.println(">>>+" + t.getRoot().getPage().getTitle());
				t.setPostOrderScore();
			}
		} 
		catch (FileNotFoundException f){
		   
	   }catch (IOException e) 
	   {
   //		e.printStackTrace();
	   }
	//		System.out.println("Finish Computing!" + ("-").repeat(40));


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
			return rsA;
			

	}

	public static ArrayList<Tree> searchMentalChickenSoup(String word) throws InterruptedException 
	{	
		ArrayList<Tree> resultArray = new ArrayList<>();

		String st2 = "";

		if(word.matches("[\\u4E00-\\u9FA5]+"))  
    	{  
			int index = (int)(Math.random()*KeywordS.getChiNum());
			st2 = KeywordS.selectFrom(index, 20).getName();//chi
    	}else{
			int index = (int)(Math.random()*KeywordS.getEngNum());
			st2 = KeywordS.selectFrom(index, 10).getName();//eng
		}
		
		

		try 
		{
			String st1 = java.net.URLEncoder.encode(word, "UTF-8");
			st2 = java.net.URLEncoder.encode(st2, "UTF-8");

			
			GoogleQuery g1 = new GoogleQuery(st1,100);
			GoogleQuery g2 = new GoogleQuery(st2,50);

			HashMap<String, String> query1 = g1.query();
			HashMap<String, String> query2 = g2.query();

			//request.setAttribute("query", rs);
			for(Entry<String, String> entry : query1.entrySet()) {
				String key = entry.getKey();
				String value = entry.getValue();
				Page p = new Page(key, value);
				Tree t = new Tree(p);

		//		System.out.println(key + "\nhref: " + value);

				resultArray.add(t);
		 	}

			 for(Entry<String, String> entry : query2.entrySet()) {
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

	
	