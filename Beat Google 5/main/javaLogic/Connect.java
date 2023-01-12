import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;

public class Connect {

    public static void main(String[] args) throws InterruptedException, UnsupportedEncodingException {

        
		Scanner s = new Scanner(System.in);
		System.out.println("input:");
		String st = s.nextLine();
        if(st.charAt(0) == ' '){
            System.out.println("抓到!");
        }


        Facade f = new Facade();
        ArrayList<Tree> results = f.operate(st);
        ArrayList<String> title = new ArrayList<>();
        ArrayList<String> url = new ArrayList<>();

        for(int i = 0; i < 10; i++){
            title.add(results.get(i).getRoot().getPage().getTitle());
            url.add(results.get(i).getRoot().getPage().getURL());
        }


        //輸出
        System.out.println("-".repeat(60));
        for(int i = 0; i < 10; i++){
            System.out.println(i+1 + ": " + results.get(i).getRoot().getPage().getScore());
            System.out.println(">Title: " + results.get(i).getRoot().getPage().getTitle());
            System.out.println(">>>Url: " + results.get(i).getRoot().getPage().getURL());
        }
        System.out.println("-".repeat(60));

    }
	
}
