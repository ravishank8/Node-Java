package com.service.client;

import org.junit.Rule;
import org.junit.Test;

import com.carrotsearch.junitbenchmarks.BenchmarkOptions;
import com.carrotsearch.junitbenchmarks.BenchmarkRule;


public class TestRunner {

	// Add a JUnit4 rule that tells JUnit runners to attach benchmarking code to
    // your tests
    @Rule
    public BenchmarkRule benchmarkRun = new BenchmarkRule();
 
    /**
     * We can fine tune the benchmark with additional
     * annotated parameters using benchmark options.
     */
    @BenchmarkOptions(benchmarkRounds = 1000, warmupRounds = 20, concurrency=20)
    @Test
    public void testRXJavaClient() throws Exception {
 
    	RXJavaClientDriver driverProgram = new RXJavaClientDriver();
        driverProgram.getServiceResults();
    }
 
}
