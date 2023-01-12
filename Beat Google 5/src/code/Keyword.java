package code;
public class Keyword {
	public String name;
	public double weight;
	
	public Keyword(String name, double weight){
		this.name = name;
		this.weight = weight;
	}

	public String getName(){
		return name;
	}

	public void weightPlus(){
		weight += 95;
	}
	
	@Override
	public String toString(){
		return "["+name+","+weight+"]";
	}
}

