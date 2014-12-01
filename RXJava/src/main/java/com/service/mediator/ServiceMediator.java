package com.service.mediator;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.schedulers.Schedulers;

import com.service.Service.HomePageContent;
import com.service.Service.HomeService;
import com.service.client.SearchResults.SearchResult;

public class ServiceMediator {

	List<SearchResult> list = new ArrayList<SearchResult>();

	public List<SearchResult> getHomeResults() throws Exception {

		HomeService homeService = new HomeService();

		// long startTime = System.currentTimeMillis();

		Observable.merge(getFirstDataAsynch(homeService),
				getSecondDataAsynch(homeService),
				getThirdDataAsynch(homeService),
				getForthDataAsynch(homeService),
				getFifthDataAsynch(homeService)).toBlocking().forEach(arg0 -> System.out.println("Content is: " + arg0));

		/*
		 * Observable.merge(getFirstSearchAsynch(searchService),
		 * getSecondSearchAsynch(searchService)).toBlocking().forEach(arg0 ->
		 * list.add(arg0));
		 */

		return list;
	}

	private Observable<HomePageContent> getFirstDataAsynch(
			HomeService searchService) throws Exception {
		Observable<HomePageContent> searchResultsOne = searchService
				.getHomeResults()
				.doOnSubscribe(
						() -> System.out.println("Search started One "
								+ System.currentTimeMillis()))
				.doOnCompleted(
						() -> System.out.println("Search completed One"
								+ System.currentTimeMillis()));

		Observable<HomePageContent> searchObOne = searchResultsOne
				.subscribeOn(Schedulers.newThread());

		return searchObOne;

	}

	private Observable<HomePageContent> getSecondDataAsynch(
			HomeService searchService) throws Exception {
		Observable<HomePageContent> searchResultsTwo = searchService
				.getHomeResults()
				.doOnSubscribe(
						() -> System.out.println("Search started Two"
								+ System.currentTimeMillis()))
				.doOnCompleted(
						() -> System.out.println("Search completed Two"
								+ System.currentTimeMillis()));

		Observable<HomePageContent> searchObTwo = searchResultsTwo
				.subscribeOn(Schedulers.newThread());

		return searchObTwo;
	}

	private Observable<HomePageContent> getThirdDataAsynch(
			HomeService searchService) throws Exception {
		Observable<HomePageContent> searchResultsTwo = searchService
				.getHomeResults()
				.doOnSubscribe(
						() -> System.out.println("Search started Third"
								+ System.currentTimeMillis()))
				.doOnCompleted(
						() -> System.out.println("Search completed Third"
								+ System.currentTimeMillis()));

		Observable<HomePageContent> searchObTwo = searchResultsTwo
				.subscribeOn(Schedulers.newThread());

		return searchObTwo;
	}

	private Observable<HomePageContent> getForthDataAsynch(
			HomeService searchService) throws Exception {
		Observable<HomePageContent> searchResultsTwo = searchService
				.getHomeResults()
				.doOnSubscribe(
						() -> System.out.println("Search started Forth"
								+ System.currentTimeMillis()))
				.doOnCompleted(
						() -> System.out.println("Search completed Forth"
								+ System.currentTimeMillis()));

		Observable<HomePageContent> searchObTwo = searchResultsTwo
				.subscribeOn(Schedulers.newThread());

		return searchObTwo;
	}

	private Observable<HomePageContent> getFifthDataAsynch(
			HomeService searchService) throws Exception {
		Observable<HomePageContent> searchResultsTwo = searchService
				.getHomeResults()
				.doOnSubscribe(
						() -> System.out.println("Search started Fifth"
								+ System.currentTimeMillis()))
				.doOnCompleted(
						() -> System.out.println("Search completed Fifth"
								+ System.currentTimeMillis()));

		Observable<HomePageContent> searchObTwo = searchResultsTwo
				.subscribeOn(Schedulers.newThread());

		return searchObTwo;
	}

}
