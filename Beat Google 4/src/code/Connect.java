package code;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Connect {
	static JSONArray rArray = new JSONArray();
    static JSONObject result = new JSONObject();

    public static void main(String[] args) throws InterruptedException, UnsupportedEncodingException {

        FacadeInsp f = new FacadeInsp();
        f.load();
        
		Scanner s = new Scanner(System.in);
        System.out.println("input:");
		
        while(s.hasNext()){
            
		    String st = s.nextLine();

            if(st.charAt(0) == ' '){
                System.out.println("抓到!");
                System.out.println("input:");
                continue;
            }


            
            ArrayList<Tree> results = f.operate(st);
            ArrayList<String> title = new ArrayList<>();
            ArrayList<String> url = new ArrayList<>();

            int outputNum;
            if(results.size() < 10){
                outputNum = results.size();
            }else{
                outputNum = 10;
            }

            for(int i = 0; i < outputNum; i++){
                title.add(results.get(i).getRoot().getPage().getTitle());
                url.add(results.get(i).getRoot().getPage().getURL());
            }


        //輸出
            System.out.println("-".repeat(60));
            
            for(int i = 0; i < 10; i++){
            	
            	result.put("webName", results.get(i).getRoot().getPage().getTitle());
            	result.put("URL", results.get(i).getRoot().getPage().getURL());
            	rArray.add(result);
            	
                System.out.println(i+1 + ": " + results.get(i).getRoot().getPage().getScore());
                System.out.println(">Title: " + results.get(i).getRoot().getPage().getTitle());
                System.out.println(">>>Url: " + results.get(i).getRoot().getPage().getURL());
            }
            System.out.println("-".repeat(60));

            System.out.println("input:");
        }

    }
	public JSONArray getArray() {
		return rArray;
	}
}
