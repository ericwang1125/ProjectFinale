import java.util.ArrayList;
import java.io.*;

public class Tree {
    private Node root;
	private Keyword keywords;
	private double totalScore;

    public Tree(Page rootPage){
		this.root = new Node(rootPage);
	}

	public Node getRoot(){
		return this.root;
	}

	public void printRootInfo(){
		System.out.println(root.getPage().getTitle());
		System.out.println(">>>" + totalScore);
	}

	public double getTreeScore(){
		return this.totalScore;
	}
	
	public void setPostOrderScore() throws IOException{
		setPostOrderScore(root);
		this.totalScore = root.getNodeScore();
	}
	
	private void setPostOrderScore(Node startNode) throws IOException{
		//2. compute the score of children nodes via post-order, then setNodeScore for startNode
		
		for(int i = 0; i < startNode.childrenSize(); i++) {
            Node n = startNode.getChild(i);
			setPostOrderScore(n);
			n.setNodeScore();
		}
		startNode.setNodeScore();
		
	}
	
	
}
    


    

