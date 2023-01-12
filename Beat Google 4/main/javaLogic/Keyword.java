public class Keyword {
	public String name;
	public double weight;
	private int language;
	
	public Keyword(String name, double weight, int language){
		this.name = name;
		this.weight = weight;
		this.language = language;
	}

	public String getName(){
		return name;
	}

	public int getlang(){
		return language;
	}
	
	@Override
	public String toString(){
		return "["+name+","+weight+"]";
	}
}

