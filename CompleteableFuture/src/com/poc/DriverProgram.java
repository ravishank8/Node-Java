package com.poc;

public class DriverProgram {
	
	public static void main(String[] args) throws Exception{
		while (true){
			CompletionAsyncDriver facade = new CompletionAsyncDriver();
			facade.getResults();
		}
	}

}
