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

public class FacadeInsp {
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

		System.out.println("Load complete!");
		
	}

    public ArrayList<Tree> operate(String st) throws InterruptedException, UnsupportedEncodingException 
	{
        rsA = new ArrayList<>();
		Keyword key = new Keyword(st, 10);
		KeywordS ks = new KeywordS();
			
		ks.getKeys().add(key);

	 	boolean chiornot = false;

		if(st.matches("[\\u4E00-\\u9FA5]+"))  
    	{ 
			chiornot = true;
        }	
		 
		System.out.println("Searching...");

        ArrayList<Tree> sr = new ArrayList<>();

        if(chiornot){
            int index = (int)(Math.random()*kcs.getKeys().size());
            for(int i = index; i < index + kcs.getKeys().size()-1; i++){
                int in = i;
                if(i >= kcs.getKeys().size()){
                    in = i-kcs.getKeys().size();
                }
                Keyword k = kcs.getKey(in);
                String search = st + " " + k.getName();
                sr = searchKeyword(search, 30);
                System.out.println(">>" + search + "add!");
            }
            for(Tree t: sr){
                rsA.add(t);
            }
            sr.clear();
        }else{//eng
            System.out.println("Eng");
            int index = (int)(Math.random()*kes.getKeys().size());
            for(int i = index; i < index + kes.getKeys().size()-1; i++){
                int in = i;
                if(i >= kes.getKeys().size()){
                    in = i-kes.getKeys().size();
                }
                Keyword k = kes.getKey(in);
                String search = st + " " + k.getName();
                sr = searchKeyword(search, 30);
                System.out.println(">>" + search + " add!");
            }
            sr.remove(null);
            for(Tree t: sr){
                if(t == null){
                    continue;
                }
                System.out.println("complete");
                rsA.add(t);
            }
            sr.clear();
        }

        for(Keyword k: kcs.getKeys()){
			kcn.add(k);
		}
		for(Keyword k: kes.getKeys()){
			ken.add(k);
		}
		for(Keyword k: kcn.getKeys()){
			ken.add(k);
		}

        for(Keyword k: ken.getKeys()){
            ks.add(k);
        }


	   	rsA.remove(null);

	   	try{
	//		if(chiornot){
				for(int i = 0; i < rsA.size(); i++){

					Tree t = rsA.get(i);
					t.setPostOrderScore(ks);
					System.out.println(">>>+" + t.getRoot().getPage().getTitle());
					System.out.println(">>>+>>" + t.getRoot().getPage().getScore());
				}
		} 
		catch (FileNotFoundException f){
		   
	   	}catch (IOException e) 
	   	{
   //		e.printStackTrace();
	   	}
	   	System.out.println("rsA finished.");
	

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
		ks.getKeys().remove(key);
		return rsA;
			
		
	}

	public static ArrayList<Tree> searchKeyword(String word, int num) throws InterruptedException 
	{	
		ArrayList<Tree> resultArray = new ArrayList<>();

		try 
		{
			String st1 = java.net.URLEncoder.encode(word, "UTF-8");

			
			GoogleQuery g1 = new GoogleQuery(st1,num);

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

