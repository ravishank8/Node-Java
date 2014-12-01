package com.poc;

import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import org.apache.http.client.fluent.Request;
import org.junit.Test;

public class TraditionalMultiThreadingDriver {

	@Test
	public void getResults() throws Exception {

		ExecutorService executorService = Executors.newFixedThreadPool(20);

		Future<?> futureOne = getFirstResult(executorService);
		Future<?> futureTwo = getFirstResult(executorService);
		Future<?> futureThree = getFirstResult(executorService);
		Future<?> futureFour = getFirstResult(executorService);
		Future<?> futureFive = getFirstResult(executorService);

		/*
		 * futureOne.get(); futureTwo.get(); futureThree.get();
		 * futureFour.get(); futureFive.get();
		 */

		executeJS(futureOne.get());
		executeJS(futureTwo.get());
		executeJS(futureThree.get());
		executeJS(futureFour.get());
		executeJS(futureFive.get());

		executorService.shutdown();
	}

	private void executeJS(Object data) throws Exception {
		ScriptEngineManager engineManager = new ScriptEngineManager();
		ScriptEngine engine = engineManager.getEngineByName("nashorn");

		engine.eval(new FileReader("D:/NodePOCs/handlebars/mustache.js"));
		Invocable invocable = (Invocable) engine;

		byte[] encoded = Files.readAllBytes(Paths
				.get("D:/Apps/luna/projectfiles/navbar.txt"));
		String template = (new String(encoded));
		
		Object json = engine.eval("JSON");
		Object mustache = engine.eval("Mustache");
		data = invocable.invokeMethod(json, "parse", data);
		Object turn = invocable
				.invokeMethod(mustache, "render", template, data);
		System.out.println("Return String: " + turn.toString());
	}

	private Future<?> getFirstResult(ExecutorService executorService) {
		Future<?> future = executorService
				.submit(() -> {
					String returnString = Request
							.Get("http://delvmpllbbab09:7003//rest/model/com/sgs/browse/EndecaContentCollectionActor/getCategories")
							.execute().returnContent().asString();
					return returnString;
					// return StringEscapeUtils.escapeJava(returnString);
				});
		return future;
	}

	/*private Future<?> getSecondResult(ExecutorService executorService) {
		Future<?> future = executorService.submit(() -> {
			System.out.println("Asynchronous Callable");
			String returnString = Request
					.Get("http://delvmpllbbab09/store/Home?format=json")
					.execute().returnContent().asString();
			return returnString;
		});
		return future;
	}*/

}
