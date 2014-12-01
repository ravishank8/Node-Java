package com.service.client;

import java.util.ArrayList;
import java.util.List;

public class SearchResults {
	
	List<SearchResult> results = new ArrayList<SearchResult>();
	
	public List<SearchResult> getSearchResults(){
		results.add(new SearchResult());		
		results.add(new SearchResult());
		results.add(new SearchResult());
		results.add(new SearchResult());
		results.add(new SearchResult());
		results.add(new SearchResult());
		return results;
	}
	
	public class SearchResult{
		public String data="The data that was returned";
	}
	
	
	

}
