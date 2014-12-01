package com.poc;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Supplier;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Request;
import org.junit.Test;

public class CompletionAsyncDriver {
	
	@Test
	public void getResults() throws Exception{
		
		ExecutorService executor = Executors.newFixedThreadPool(20);
		
		
		CompletableFuture<String> f1 = CompletableFuture.supplyAsync(new Supplier<String>() {
		    @SuppressWarnings("finally")
			@Override
		    public String get() {
		    	String returnString = null;
		    	Object turn = null;
		    	try{
		    	returnString = Request
						.Get("http://delvmpllbbab09:7003//rest/model/com/sgs/browse/EndecaContentCollectionActor/getCategories")
						.execute().returnContent().asString();
				
				ScriptEngineManager engineManager = new ScriptEngineManager();
				ScriptEngine engine = engineManager
						.getEngineByName("nashorn");

				engine.eval(new FileReader(
						"D:/NodePOCs/handlebars/mustache.js"));
				Invocable invocable = (Invocable) engine;

				byte[] encoded = Files.readAllBytes(Paths
						.get("D:/Apps/luna/projectfiles/navbar.txt"));
				String template = (new String(encoded));

				Object json = engine.eval("JSON");
				Object mustache = engine.eval("Mustache");
				Object returnStr = invocable.invokeMethod(json,
						"parse", returnString);
				turn = invocable.invokeMethod(mustache,
						"render", template, returnStr);
				System.out.println("Return String: " + turn.toString());
				
				}catch(Exception e){
					e.printStackTrace();
				}finally{
					return turn.toString();	
				}
		    }
		}, executor);
		
		CompletableFuture<String> f2 = CompletableFuture.supplyAsync(new Supplier<String>() {
		    @SuppressWarnings("finally")
			@Override
		    public String get() {
		    	String returnString = null;
		    	Object turn = null;
		    	try{
		    	returnString = Request
		    	
						.Get("http://delvmpllbbab09:7003//rest/model/com/sgs/browse/EndecaContentCollectionActor/getCategories")
						.execute().returnContent().asString();
				
				ScriptEngineManager engineManager = new ScriptEngineManager();
				ScriptEngine engine = engineManager
						.getEngineByName("nashorn");

				engine.eval(new FileReader(
						"D:/NodePOCs/handlebars/mustache.js"));
				Invocable invocable = (Invocable) engine;

				byte[] encoded = Files.readAllBytes(Paths
						.get("D:/Apps/luna/projectfiles/navbar.txt"));
				String template = (new String(encoded));

				Object json = engine.eval("JSON");
				Object mustache = engine.eval("Mustache");
				Object returnStr = invocable.invokeMethod(json,
						"parse", returnString);
				turn = invocable.invokeMethod(mustache,
						"render", template, returnStr);
				System.out.println("Return String: " + turn.toString());
				
				}catch(Exception e){
					e.printStackTrace();
				}finally{
					return turn.toString();	
				}
		    }
		}, executor);
		
		CompletableFuture<String> f3 = CompletableFuture.supplyAsync(new Supplier<String>() {
		    @SuppressWarnings("finally")
			@Override
		    public String get() {
		    	String returnString = null;
		    	Object turn = null;
		    	try{
		    	returnString = Request
		    	
						.Get("http://delvmpllbbab09:7003//rest/model/com/sgs/browse/EndecaContentCollectionActor/getCategories")
						.execute().returnContent().asString();
				
				ScriptEngineManager engineManager = new ScriptEngineManager();
				ScriptEngine engine = engineManager
						.getEngineByName("nashorn");

				engine.eval(new FileReader(
						"D:/NodePOCs/handlebars/mustache.js"));
				Invocable invocable = (Invocable) engine;

				byte[] encoded = Files.readAllBytes(Paths
						.get("D:/Apps/luna/projectfiles/navbar.txt"));
				String template = (new String(encoded));

				Object json = engine.eval("JSON");
				Object mustache = engine.eval("Mustache");
				Object returnStr = invocable.invokeMethod(json,
						"parse", returnString);
				turn = invocable.invokeMethod(mustache,
						"render", template, returnStr);
				System.out.println("Return String: " + turn.toString());
				
				}catch(Exception e){
					e.printStackTrace();
				}finally{
					return turn.toString();	
				}
		    }
		}, executor);
		
		CompletableFuture<String> f4 = CompletableFuture.supplyAsync(new Supplier<String>() {
		    @SuppressWarnings("finally")
			@Override
		    public String get() {
		    	String returnString = null;
		    	Object turn = null;
		    	try{
		    	returnString = Request
		    	
						.Get("http://delvmpllbbab09:7003//rest/model/com/sgs/browse/EndecaContentCollectionActor/getCategories")
						.execute().returnContent().asString();
				
				ScriptEngineManager engineManager = new ScriptEngineManager();
				ScriptEngine engine = engineManager
						.getEngineByName("nashorn");

				engine.eval(new FileReader(
						"D:/NodePOCs/handlebars/mustache.js"));
				Invocable invocable = (Invocable) engine;

				byte[] encoded = Files.readAllBytes(Paths
						.get("D:/Apps/luna/projectfiles/navbar.txt"));
				String template = (new String(encoded));

				Object json = engine.eval("JSON");
				Object mustache = engine.eval("Mustache");
				Object returnStr = invocable.invokeMethod(json,
						"parse", returnString);
				turn = invocable.invokeMethod(mustache,
						"render", template, returnStr);
				System.out.println("Return String: " + turn.toString());
				
				}catch(Exception e){
					e.printStackTrace();
				}finally{
					return turn.toString();	
				}
		    }
		}, executor);
		
		CompletableFuture<String> f5 = CompletableFuture.supplyAsync(new Supplier<String>() {
		    @SuppressWarnings("finally")
			@Override
		    public String get() {
		    	String returnString = null;
		    	Object turn = null;
		    	try{
		    	returnString = Request
		    	
						.Get("http://delvmpllbbab09:7003//rest/model/com/sgs/browse/EndecaContentCollectionActor/getCategories")
						.execute().returnContent().asString();
				
				ScriptEngineManager engineManager = new ScriptEngineManager();
				ScriptEngine engine = engineManager
						.getEngineByName("nashorn");

				engine.eval(new FileReader(
						"D:/NodePOCs/handlebars/mustache.js"));
				Invocable invocable = (Invocable) engine;

				byte[] encoded = Files.readAllBytes(Paths
						.get("D:/Apps/luna/projectfiles/navbar.txt"));
				String template = (new String(encoded));

				Object json = engine.eval("JSON");
				Object mustache = engine.eval("Mustache");
				Object returnStr = invocable.invokeMethod(json,
						"parse", returnString);
				turn = invocable.invokeMethod(mustache,
						"render", template, returnStr);
				System.out.println("Return String: " + turn.toString());
				
				}catch(Exception e){
					e.printStackTrace();
				}finally{
					return turn.toString();	
				}
		    }
		}, executor);
		
		
		f1.get();
		f2.get();
		f3.get();
		f4.get();
		f5.get();
		CompletableFuture<Object> F3 = f1.thenCombine(f2, (a, b) -> findRoute(a, b));
		CompletableFuture<Object> F4 = F3.thenCombine(f3, (a, b) -> findRoute((String) a, b));
		CompletableFuture<Object> F5 = F4.thenCombine(f4, (a, b) -> findRoute((String) a, b));
		CompletableFuture<Object> F6 = F5.thenCombine(f5, (a, b) -> findRoute((String) a, b));
	}
	
	public CompletableFuture<Double> findRoute(String cust, Object f2){
		System.out.println("Execution");
		return new CompletableFuture<Double>();
	}
	
}
