# ZIO state explorer

A prototype to make ZIO even more testable.

## How to use

Instead of importing the static Zio paths, we need to use a parameter.

```scala
class MyModule(zio: ZIOFacade) {
    import zio._

    def doStuff: UIO[Int] =
        ZIO.succeed(1).race(ZIO.succeed(2))
}
```

To instantiate the module use:

```scala
object Retry extends Retry(ActualZio)
```

## Goals

1. Correctness of test results
2. Minimal deviation from normal ZIO
    * Near to zero impact on performance
    * Very similar usage
3. Easy to use

## Performance

The first benchmark shows a minor performance impact of ~ 5 % performance lost using the facade, well within the error margin.

| Benchmark                    |  Score                      |
|------------------------------|-----------------------------|
| IOMapBenchmark.zioFacadeMap  |  34903.797 ± 4079.546 ops/s |
| IOMapBenchmark.zioMap        |  36561.435 ± 2545.700 ops/s |

## Build

This is a normal sbt project, you can compile code with `sbt compile` and test it
with `sbt test`.


## Previous attempts

* https://github.com/Jentsch/modelchecker
* https://github.com/zio/zio/pull/1753
