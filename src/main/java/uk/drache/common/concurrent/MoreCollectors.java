package uk.drache.common.concurrent;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * Adds additional Collectors.
 */
public class MoreCollectors {

  /**
   * A collector that builds a {@link CompletableFuture<Void>} object that obeys {@link
   * CompletableFuture#allOf(CompletableFuture[])}.
   */
  private static class AllOfCompletableFutureCollector
      implements Collector<CompletableFuture<?>, ImmutableList.Builder<CompletableFuture<?>>,
      CompletableFuture<Void>> {
    @Override
    public Supplier<ImmutableList.Builder<CompletableFuture<?>>> supplier() {
      return ImmutableList::builder;
    }

    @Override
    public BiConsumer<ImmutableList.Builder<CompletableFuture<?>>, CompletableFuture<?>> accumulator() {
      return ImmutableList.Builder::add;
    }

    @Override
    public BinaryOperator<ImmutableList.Builder<CompletableFuture<?>>> combiner() {
      return (a, b) -> a.addAll(b.build());
    }

    @Override
    public Function<ImmutableList.Builder<CompletableFuture<?>>, CompletableFuture<Void>> finisher() {
      return a -> {
        ImmutableList<CompletableFuture<?>> v = a.build();
        return CompletableFuture.allOf(v.toArray(new CompletableFuture<?>[v.size()]));
      };
    }

    @Override
    public Set<Characteristics> characteristics() {
      return ImmutableSet.of(Characteristics.UNORDERED);
    }
  }

  /**
   * A collector that builds {@link ImmutableSet} objects.
   */
  public static <T> Collector<? super T, ImmutableSet.Builder<T>, ImmutableSet<T>> toImmutableSet() {
    return new Collector<T, ImmutableSet.Builder<T>, ImmutableSet<T>>() {
      @Override
      public Supplier<ImmutableSet.Builder<T>> supplier() {
        return ImmutableSet::<T>builder;
      }

      @Override
      public BiConsumer<ImmutableSet.Builder<T>, T> accumulator() {
        return ImmutableSet.Builder::add;
      }

      @Override
      public BinaryOperator<ImmutableSet.Builder<T>> combiner() {
        return (x, y) -> {
          x.addAll(y.build());
          return x;
        };
      }

      @Override
      public Function<ImmutableSet.Builder<T>, ImmutableSet<T>> finisher() {
        return ImmutableSet.Builder::build;
      }

      @Override
      public Set<Characteristics> characteristics() {
        return Sets.immutableEnumSet(Characteristics.UNORDERED);
      }
    };
  }

  /**
   * A collector that builds {@link ImmutableSet} objects.
   */
  public static <T> Collector<? super T, ImmutableList.Builder<T>, ImmutableList<T>> toImmutableList() {
    return new Collector<T, ImmutableList.Builder<T>, ImmutableList<T>>() {
      @Override
      public Supplier<ImmutableList.Builder<T>> supplier() {
        return ImmutableList::<T>builder;
      }

      @Override
      public BiConsumer<ImmutableList.Builder<T>, T> accumulator() {
        return ImmutableList.Builder::add;
      }

      @Override
      public BinaryOperator<ImmutableList.Builder<T>> combiner() {
        return (x, y) -> x.addAll(y.build());
      }

      @Override
      public Function<ImmutableList.Builder<T>, ImmutableList<T>> finisher() {
        return ImmutableList.Builder::build;
      }

      @Override
      public Set<Characteristics> characteristics() {
        return Sets.immutableEnumSet(Characteristics.UNORDERED);
      }
    };
  }

  /**
   * Returns a collector that builds a {@link CompletableFuture<Void>} object that obeys {@link
   * CompletableFuture#allOf(CompletableFuture[])}.
   */
  public static Collector<CompletableFuture<?>,
      ImmutableList.Builder<CompletableFuture<?>>,
      CompletableFuture<Void>> allOfCompleteableFuture() {
    return new AllOfCompletableFutureCollector();
  }

  private MoreCollectors() {}
}
