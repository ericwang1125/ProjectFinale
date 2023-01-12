import java.util.ArrayList;
import java.util.Random;
import java.io.*;

public class Node {
	
    public Page page;
    public Double nodeScore;

    public Node parent;
    public ArrayList<Node> children = new ArrayList<>();

    public Node(Page page){
        this.page = page;
        this.nodeScore = 0.0;
    }

    public Page getPage(){
        return this.page;
    }

    public double getNodeScore(){
        return this.nodeScore;
    }

    public void setNodeScore() throws IOException{
		//this method should be called in post-order mode
		
		//compute webPage score
		page.setScore();
		//set webPage score to nodeScore
		nodeScore = page.getScore();		
		
		//nodeScore += all children's nodeScore 
		for(Node child : children){
			nodeScore += child.nodeScore;
		}		
	}

    public Node getChild(int index){
        return children.get(index);
    }

    public int childrenSize(){
        return children.size();
    }

    public void addChild(Node child){
		//add the WebNode to its children list
		this.children.add(child);
		child.parent = this;
	}
	
	public boolean isTheLastChild(){
		if(this.parent == null) return true;
		ArrayList<Node> siblings = this.parent.children;
		
		return this.equals(siblings.get(siblings.size() - 1));
	}
	
	public int getDepth(){
		int retVal = 1;
		Node currNode = this;
		while(currNode.parent != null){
			retVal++;
			currNode = currNode.parent;
		}
		return retVal;
	}

    public void setSubWebToChild() throws IOException{
		ArrayList<Page> pages = new ArrayList<>();
		int[] indexes = new int[10];

		Random rand = new Random(); //instance of random class
		int upperbound = pages.size();
		if(upperbound <= 0){
			return;
		}
		
		for(int i = 0; i < 10; i++){
			indexes[i] = rand.nextInt(upperbound); 
		}

		for(int i = 0; i < upperbound; i++){
			for(int j = 0; j < indexes.length; j++){
				if(i == j){
					this.addChild(new Node(pages.get(i)));
				}
			}   
        }
    }


	
	

}
