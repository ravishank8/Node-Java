package com.poc;

import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import org.apache.http.client.fluent.Request;
import org.junit.Test;

public class TraditionalSingleThreadingDriver {

	@Test
	public void getResults() throws Exception {
		
		for (int i = 0; i < 5; i++) {
			String returnString = Request
					.Get("http://delvmpllbbab09:7003//rest/model/com/sgs/browse/EndecaContentCollectionActor/getCategories")
					.execute().returnContent().asString();

			ScriptEngineManager engineManager = new ScriptEngineManager();
			ScriptEngine engine = engineManager.getEngineByName("nashorn");

			engine.eval(new FileReader("D:/NodePOCs/handlebars/mustache.js"));
			Invocable invocable = (Invocable) engine;

			byte[] encoded = Files.readAllBytes(Paths
					.get("D:/Apps/luna/projectfiles/navbar.txt"));
			String template = (new String(encoded));

			Object json = engine.eval("JSON");
			Object mustache = engine.eval("Mustache");
			Object returnStr = invocable.invokeMethod(json, "parse",
					returnString);
			Object turn = invocable.invokeMethod(mustache, "render", template,
					returnStr);
			System.out.println("Returned String - " + turn.toString());
		}

	}

}
