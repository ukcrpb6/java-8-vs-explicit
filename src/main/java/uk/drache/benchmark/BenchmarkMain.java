package uk.drache.benchmark;

import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

/**
 * Main class for running benchmarks.
 */
public class BenchmarkMain {
  public static void main(String[] args) throws RunnerException {
    Options opt = new OptionsBuilder()
        //.include(CollectorBenchmark.class.getSimpleName())
        .include(ImmutableCollectorBenchmark.class.getSimpleName())
        .warmupIterations(5)
        .measurementIterations(5)
        .forks(3)
        .build();

    new Runner(opt).run();
  }
}
