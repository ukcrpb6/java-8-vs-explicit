package uk.drache.benchmark;

import com.google.common.collect.ImmutableSet;

import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

import java.util.Set;

/**
 * Base collector benchmark.
 */
@State(Scope.Thread)
public abstract class BaseCollectorBenchmark {

  private Set<Set<Integer>> subject;

  @Param({"1", "10", "100", "1000"})
  private int size;

  @Setup
  public void prepare() {
    ImmutableSet.Builder<Set<Integer>> answer = ImmutableSet.builder();
    for (int i = 0; i < size; i++) {
      ImmutableSet.Builder<Integer> builder = ImmutableSet.builder();
      for (int j = 0; j < i; j++) {
        builder.add(j);
      }
      answer.add(builder.build());
    }
    subject = answer.build();
  }

  public Set<Set<Integer>> getSubject() {
    return subject;
  }
}
