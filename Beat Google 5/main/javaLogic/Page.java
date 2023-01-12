
import java.util.ArrayList;
import java.io.*;
import java.net.MalformedURLException;

public class Page {

	private String url;
	private String title;
	private Double score;
    private CounterAndFinder cf;
	private NeoFinder nf;
	

	
	public Page(String title, String url) {
		this.url = url;
		this.title = title;
		this.score = 0.0;
		this.cf = new CounterAndFinder(url);
		this.nf = new NeoFinder(url);
	}

	public Page(String url) {
		this.url = url;
		this.title = null;
		this.score = 0.0;
		this.cf = new CounterAndFinder(url);
		this.nf = new NeoFinder(url);
	}

	public String getTitle(){
		return this.title;
	}

	public String getURL(){
		return this.url;
	}

    public double getScore(){
        return score;
    }

    public void setScore() throws IOException{
		score = 0.0;
//		1. calculate score
	for(Keyword k: KeywordS.getKeys()){
		score += cf.countKeyword(k.name) * k.weight;
	}
		
//May have problem Q
	}	

	public void setTitle(String s){
		this.title = s;
	}

	public ArrayList<Page> oldSubWeb() throws IOException{
		return cf.findSubWeb();
	}

	
	public ArrayList<Page> subWeb() throws IOException{
		
		ArrayList<String> al = nf.findSubWeb();
		ArrayList<Page> retVal = new ArrayList<>();
		for(String s: al){
			retVal.add(new Page(s));
		}
		return retVal;
	}
}
