package com.service.Service;

import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import org.apache.http.client.fluent.Request;

import rx.Observable;

import com.service.client.SearchResults;
import com.service.client.SearchResults.SearchResult;

public class HomeService {

	public Observable<SearchResult> getSearchResults() throws Exception {

		SearchResults searchResults = new SearchResults();

		Observable<SearchResult> searchObservable = Observable.create(arg0 -> {
			List<SearchResult> results = null;
			try {
				results = searchResults.getSearchResults();
				for (SearchResult t : results) {
					arg0.onNext(t);
					Thread.sleep(1000);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			arg0.onCompleted();

		});

		return searchObservable;
	}

	public Observable<HomePageContent> getHomeResults() throws Exception {

		HomePageContent content = new HomePageContent();

		Observable<HomePageContent> contentObservable = Observable
				.create(arg0 -> {
					try {
						String returnString = Request
								.Get("http://delvmpllbbab09:7003//rest/model/com/sgs/browse/EndecaContentCollectionActor/getCategories")
								.execute().returnContent().asString();
						content.returnContent = returnString;

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
						Object turn = invocable.invokeMethod(mustache,
								"render", template, returnStr);
						content.returnContent = turn.toString();
						System.out.println("Return String: " + turn.toString());

					} catch (Exception e) {
						e.printStackTrace();
					}
					arg0.onNext(content);
					arg0.onCompleted();

				});

		return contentObservable;
	}

	/*
	 * SearchResults searchResults= new SearchResults();
	 * 
	 * Observable<SearchResult> searchObservable = Observable .create(arg0 -> {
	 * List<SearchResult> results = null; try { results =
	 * searchResults.getSearchResults(); for (SearchResult t : results) {
	 * arg0.onNext(t); Thread.sleep(1000); } } catch (Exception e) {
	 * e.printStackTrace(); } arg0.onCompleted();
	 * 
	 * });
	 * 
	 * return searchObservable; }
	 */

}
