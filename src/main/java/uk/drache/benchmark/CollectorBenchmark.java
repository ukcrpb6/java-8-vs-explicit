package uk.drache.benchmark;

import com.google.common.collect.Lists;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Warmup;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(3)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class CollectorBenchmark extends BaseCollectorBenchmark {

  @Benchmark
  @SuppressWarnings("Convert2streamapi")
  public void testExplicit() {
    List<Integer> sizes = Lists.newArrayList();
    for (Collection c : getSubject()) {
      sizes.add(c.size());
    }
  }

  @Benchmark
  public void testStream() {
    getSubject().stream().map(Collection::size).collect(Collectors.toList());
  }

  @Benchmark
  public void testParallelStream() {
    getSubject().parallelStream().map(Collection::size).collect(Collectors.toList());
  }

}
