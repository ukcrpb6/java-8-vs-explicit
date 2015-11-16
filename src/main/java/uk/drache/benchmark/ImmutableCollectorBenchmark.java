package uk.drache.benchmark;

import com.google.common.collect.ImmutableList;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Warmup;
import uk.drache.common.concurrent.MoreCollectors;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(3)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class ImmutableCollectorBenchmark extends BaseCollectorBenchmark {

  @Benchmark
  public void testExplicit() {
    ImmutableList.Builder<Integer> sizes = ImmutableList.builder();
    for (Collection c : getSubject()) {
      sizes.add(c.size());
    }
    sizes.build();
  }

  @Benchmark
  public void testStream() {
    getSubject().stream().map(Collection::size).collect(MoreCollectors.toImmutableList());
  }

  @Benchmark
  public void testParallelStream() {
    getSubject().parallelStream().map(Collection::size).collect(MoreCollectors.toImmutableList());
  }

}
