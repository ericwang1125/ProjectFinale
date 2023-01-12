package code;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.net.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class NeoFinder
{
	public String url;
	public final int lastDash ;
	public String content;
	
	public NeoFinder(String url)
	{

		this.url = url;//.replace("/url?q=", "");
		lastDash = last(url);
	}
	
	private String fetchContent() throws IOException
	{
		String retVal = "";

        // 第一步
        String encoder = java.net.URLEncoder.encode(url, "UTF-8");
        //第二步
        String decoder = java.net.URLDecoder.decode(encoder, "UTF-8");
		
		/*try{
			
		 }catch (MalformedURLException e){

		}*/
		
		URL url = new URL (decoder);
			URLConnection conn = url.openConnection();
			//set HTTP header
			conn.setRequestProperty("User-agent", "Chrome/107.0.5304.107");
			InputStream in = conn.getInputStream();

			InputStreamReader inReader = new InputStreamReader(in, "utf-8");
			BufferedReader bufReader = new BufferedReader(inReader);
			String line = null;
			while((line = bufReader.readLine()) != null)
			{
			retVal += line;
			}
        return retVal;
		
	}
	
	public ArrayList<String> findSubWeb() throws IOException
	{
		if(content == null)
		{
			content = fetchContent();
		}

		ArrayList<String> retVal = new ArrayList<>();
		
		//using Jsoup analyze html string
		Document doc = Jsoup.parse(content);
		
		//select particular element(tag) which you want 
		Elements links = doc.select("a[href]");
		
		for(Element lk : links)
		{
			try 
			{	
//			System.out.println(li.select("a").get(0).attr("href"));
			String citeUrl = lk.select("a").get(0).attr("href");
/*				String title = li.select("a").get(0).select(".vvjwJb").text();
				
				if(title.equals("")) 
				{
					continue;
				} */

				
//				System.out.println("Title: " + title + " , url: " + citeUrl);
				
				//put title and pair into HashMap
				if(containHTML(citeUrl) && citeUrl.substring(0, lastDash).equals(url.substring(0,lastDash))){
						for(int i = 0; i <= 10; i++){
						retVal.add(citeUrl);
						}                       
				}

			} catch (IndexOutOfBoundsException e) 
			{
//				e.printStackTrace();
			}

		}
		content = null;
		return retVal;
	}

	public void printResult() throws IOException{
		ArrayList<String> al = this.findSubWeb();
		for (Object o: al) {
			String url = o.toString();
			System.out.println(">url: " + url);
		}
	}

    public boolean containHTML(String url){
        if(url.substring(0, 8).equals("https://")){
            return true;
        }else if(url.substring(0, 8).equals("http://")){
            return true;
        }else{
            return false;
        }
    }

	public int last(String P){
    	// Bonus: Implement last occurence function
    	for(int i = P.length() - 1; i >= 0; i--) {
    		if(P.charAt(i) == '/') {
    			return i;
    		}
    	}
    	
        return -1;
    }

}