import java.util.ArrayList;

public class KeywordS {
    private static ArrayList<Keyword> keywords;
    private static int chiNum;
    private static int engNum;

    public KeywordS(){
        keywords = new ArrayList<>();

//Chi 2
        keywords.add(new Keyword("語錄", 15, 20));
        keywords.add(new Keyword("金句", 15, 20));
        keywords.add(new Keyword("心靈雞湯", 10, 20));
        
        keywords.add(new Keyword("正能量", 10, 2));
        keywords.add(new Keyword("愛自己", 9, 2));
        keywords.add(new Keyword("勵志", 8, 2));

        keywords.add(new Keyword("心靈", 5, 2));
        keywords.add(new Keyword("感情", 5, 2));
        keywords.add(new Keyword("名言", 4, 2));
//Eng 1
       
        keywords.add(new Keyword("quote", 15, 10));
        keywords.add(new Keyword("said", 13, 10));
        keywords.add(new Keyword("words", 11, 10));
        keywords.add(new Keyword("love yourself", 17, 10));

        keywords.add(new Keyword("heal", 10, 1));
        keywords.add(new Keyword("find", 7, 1));
        keywords.add(new Keyword("believe", 7, 1));              
        keywords.add(new Keyword("inspirational", 5, 1));
        keywords.add(new Keyword("encourage", 5, 1));
        keywords.add(new Keyword("positive", 5, 1));       
        keywords.add(new Keyword("mental", 4, 1));
        keywords.add(new Keyword("love", 4, 1));      
        keywords.add(new Keyword("you", 4, 1));
        
        for(Keyword k: keywords){
            if(k.getlang() == 10){
                engNum++;
            }else if(k.getlang() == 20){
                chiNum++;
            }
        }
    }

    public static ArrayList<Keyword> getKeys(){
        return keywords;
    }

    public static int getEngNum(){
        return engNum;
    }

    public static int getChiNum(){
        return chiNum;
    }

    public static Keyword selectFrom(int index, int lang){
        ArrayList<Keyword> select = new ArrayList<>();
        for(Keyword k: keywords){
            if(lang == 10){
                select.add(k);
            }else if(lang == 20){
                select.add(k);
            }
        }

        return select.get(index);
    }



    
}
