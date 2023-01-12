package code;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;

import javax.tools.DocumentationTool.Location;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class GoogleQuery 
{
	public String searchKeyword;
	public String url;
	public String content;

	public GoogleQuery(String searchKeyword)
	{
		this.searchKeyword = searchKeyword;
		this.url = "http://www.google.com/search?q="+searchKeyword+"&oe=utf8&num=20";
	}

	public GoogleQuery(String searchKeyword, int num)
	{
		this.searchKeyword = searchKeyword;
		this.url = "http://www.google.com/search?q="+searchKeyword+"&oe=utf8&num=" + num;
	}

	
	private String fetchContent() throws IOException

	{
		String retVal = "";

		URL u = new URL(url);

		URLConnection conn = u.openConnection();

		conn.setRequestProperty("User-agent", "Chrome/7.0.517.44");

		InputStream in = conn.getInputStream();

		InputStreamReader inReader = new InputStreamReader(in,"utf-8");

		BufferedReader bufReader = new BufferedReader(inReader);
		String line = null;

		while((line=bufReader.readLine())!=null)
		{
			retVal += line;

		}
		return retVal;
	}
	public HashMap<String, String> query() throws IOException, InterruptedException{

		if(content==null)
		{
			content= fetchContent();
		}

		HashMap<String, String> retVal = new HashMap<String, String>();
		
		Document doc = Jsoup.parse(content);
	//	System.out.println(doc.text());
		Elements lis = doc.select("div");
//		 System.out.println(lis);
		lis = lis.select(".kCrYT");
		// System.out.println(lis.size());
		
		
		for(Element li : lis)
		{
			try 
			{
				String citeUrl = li.select("a").get(0).attr("href");
				String title = li.select("a").get(0).select(".vvjwJb").text();
				if(title.equals("")) {
					continue;
				}
				
//				System.out.println(title + ","+citeUrl);
				citeUrl = turIntoUsableURL(citeUrl);

/* 				if(citeUrl.contains("https://m.momoshop.com.tw/")){	
					continue;
				}
				if(citeUrl.contains("hhttps://shopee.tw/")){
					continue;
				}
				if(citeUrl.contains("https://www.youtube.com")){
					continue;
				}
				if(citeUrl.contains("https://www.books.com.tw/")){
					continue;
				}
				if(citeUrl.contains("https://www.vogue.com.tw/")){
					continue;
				}
				if(citeUrl.contains("https://pagamo.fandom.com")){
					continue;
				}
				 */
					/* if(t.getRoot().getPage().getURL().contains("https://shopee.tw/")){
						t = null;
					}
		 */
				
//				citeUrl = java.net.URLEncoder.encode(citeUrl, "UTF-8");

				retVal.put(title, citeUrl);
				Thread.sleep(100);

			} catch (IndexOutOfBoundsException e) {
//				e.printStackTrace();
			}
		}
		return retVal;
	}

	
	public void printResult() throws IOException, InterruptedException{
		HashMap hm = this.query();
		for (Object name: hm.keySet()) {
			String key = name.toString();
			String value = hm.get(name).toString();
			
			System.out.println("Title: " + key + "\nurl: " + value);
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

	public String turIntoUsableURL(String url){
		url = url.replace("/url?q=", "");
		int index = url.indexOf("&sa=U&ved");
		url = url.substring(0, index);
		return url;
	} 
}