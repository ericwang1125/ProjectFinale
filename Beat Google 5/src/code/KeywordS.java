package code;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class KeywordS {
    private ArrayList<Keyword> keywords;

    public KeywordS(){
        keywords = new ArrayList<>();
    }

    public KeywordS(String file, boolean close){
        String filename =   file;
        File f = new File(filename);
        keywords = new ArrayList<>();

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(f), "UTF-8")); // 指定讀取文件的編碼格式，以免出現中文亂碼
            String str = null;
            while ((str = reader.readLine()) != null) {
                Scanner sc = new Scanner(str);
                String name = sc.next();
                name = java.net.URLEncoder.encode(name, "UTF-8");
                String w = sc.next();
                w.replace("-", " ");
                double weight = Double.parseDouble(w);
                keywords.add(new Keyword(name, weight));

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(close){
                    reader.close();
                }  
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public ArrayList<Keyword> getKeys(){
        return this.keywords;
    }

    public void add(Keyword k){
        keywords.add(k);
    }

    public Keyword getKey(int index){
        return keywords.get(index);
    }

    
}
