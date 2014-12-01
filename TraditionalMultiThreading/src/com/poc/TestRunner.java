package com.poc;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.MethodRule;

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
    @BenchmarkOptions(benchmarkRounds = 20, warmupRounds = 2, concurrency=2)
    @Test
    public void testTraditionalMultiThreading() throws Exception {
 
        TraditionalMultiThreadingDriver driverProgram = new TraditionalMultiThreadingDriver();
        driverProgram.getResults();
    }
 
}
