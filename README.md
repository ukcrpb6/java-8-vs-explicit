Test project using JMH benchmarking tool evaluating simple java 8 streaming/functional vs explicit java code 
performance.


Benchmarks (16-11-2015)
-----------------------

    # Run complete. Total time: 00:12:32
    
    Benchmark                                       (size)  Mode  Cnt      Score      Error  Units
    CollectorBenchmark.testExplicit                      1  avgt   15      5.897 ±    0.044  ns/op
    CollectorBenchmark.testExplicit                     10  avgt   15    156.575 ±    1.574  ns/op
    CollectorBenchmark.testExplicit                    100  avgt   15   1543.808 ±  144.555  ns/op
    CollectorBenchmark.testExplicit                   1000  avgt   15  23583.931 ±  554.930  ns/op
    CollectorBenchmark.testParallelStream                1  avgt   15    105.697 ±    1.173  ns/op
    CollectorBenchmark.testParallelStream               10  avgt   15  27551.844 ± 1943.266  ns/op
    CollectorBenchmark.testParallelStream              100  avgt   15  34851.576 ± 1949.364  ns/op
    CollectorBenchmark.testParallelStream             1000  avgt   15  68714.702 ± 3539.062  ns/op
    CollectorBenchmark.testStream                        1  avgt   15     81.754 ±    1.305  ns/op
    CollectorBenchmark.testStream                       10  avgt   15    692.757 ±   21.249  ns/op
    CollectorBenchmark.testStream                      100  avgt   15   6255.365 ±   64.954  ns/op
    CollectorBenchmark.testStream                     1000  avgt   15  73050.733 ± 6616.346  ns/op
    ImmutableCollectorBenchmark.testExplicit             1  avgt   15      4.243 ±    0.037  ns/op
    ImmutableCollectorBenchmark.testExplicit            10  avgt   15    192.786 ±    2.219  ns/op
    ImmutableCollectorBenchmark.testExplicit           100  avgt   15   1434.923 ±   29.563  ns/op
    ImmutableCollectorBenchmark.testExplicit          1000  avgt   15  21345.682 ± 1737.230  ns/op
    ImmutableCollectorBenchmark.testParallelStream       1  avgt   15    127.235 ±   18.473  ns/op
    ImmutableCollectorBenchmark.testParallelStream      10  avgt   15  27006.299 ± 5291.027  ns/op
    ImmutableCollectorBenchmark.testParallelStream     100  avgt   15  35835.026 ± 5040.493  ns/op
    ImmutableCollectorBenchmark.testParallelStream    1000  avgt   15  80229.576 ± 6275.986  ns/op
    ImmutableCollectorBenchmark.testStream               1  avgt   15     95.860 ±    2.364  ns/op
    ImmutableCollectorBenchmark.testStream              10  avgt   15    724.917 ±   13.348  ns/op
    ImmutableCollectorBenchmark.testStream             100  avgt   15   5607.792 ±  129.365  ns/op
    ImmutableCollectorBenchmark.testStream            1000  avgt   15  69326.642 ± 7044.233  ns/op

