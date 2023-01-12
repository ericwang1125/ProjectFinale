package myPackage;

import org.json.simple.JSONArray;

public class userKeyword {
	
	private String keyword;
	private JSONArray jsonArray;
	
	public String getKeyword() {
	    return keyword;
	  }

	  public void setKeyword(String keyword) {
	    this.keyword = keyword;
	  }
	  
	  public void setJsonArray(JSONArray array) {
		this.jsonArray = array;
	}
	  public JSONArray getJsonArray() {
		return jsonArray;
	}

}
