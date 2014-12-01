package com.service.client;

import java.util.List;

import org.junit.Test;

import com.service.client.SearchResults.SearchResult;
import com.service.mediator.ServiceMediator;

public class RXJavaClientDriver {

	ServiceMediator serviceMediator = new ServiceMediator();

	@Test
	public void getServiceResults() throws Exception {

		long startTime = System.currentTimeMillis();
		List<SearchResult> results = serviceMediator.getHomeResults();
	}

}
