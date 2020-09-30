# ZIO state explorer

A prototype to make ZIO even more testable.

## How to use

Instead of importing the static Zio paths, we need to use a paramter.

```scala
class MyModule(zio: ZIOFacade) {
    import zio._

    def doStuff: UIO[Int] =
        ZIO.succeed(1).race(ZIO.succeed(2))
}
```

To instanziate the module use:

```scala
object Retry extends Retry(ActualZio)
```

## Goals

1. Correctness of test results
2. Minimal deviation from normal ZIO
    * Near to zero impact on performance
    * Very similar usage
3. Easy to use

## Build

This is a normal sbt project, you can compile code with `sbt compile` and run it
with `sbt run`, `sbt console` will start a Dotty REPL.

For more information on the sbt-dotty plugin, see the
[dotty-example-project](https://github.com/lampepfl/dotty-example-project/blob/master/README.md).

## Previous attempts

* https://github.com/Jentsch/modelchecker
* https://github.com/zio/zio/pull/1753
