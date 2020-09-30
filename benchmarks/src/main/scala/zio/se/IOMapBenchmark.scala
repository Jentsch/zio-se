package zio

import java.util.concurrent.TimeUnit

import scala.annotation.tailrec

import org.openjdk.jmh.annotations._


/**
 * Modified copy of https://github.com/zio/zio/blob/master/benchmarks/src/main/scala/zio/IOMapBenchmark.scala
 */
@State(Scope.Thread)
@BenchmarkMode(Array(Mode.Throughput))
@OutputTimeUnit(TimeUnit.SECONDS)
class IOMapBenchmark {
  @Param(Array("500"))
  var depth: Int = _

  @Benchmark
  def zioMap(): BigInt = zioMap(zio.IOBenchmarks)

  @Benchmark
  def zioTracedMap(): BigInt = zioMap(zio.IOBenchmarks.TracedRuntime)

  private[this] def zioMap(runtime: Runtime[Any]): BigInt = {
    @tailrec
    def sumTo(t: UIO[BigInt], n: Int): UIO[BigInt] =
      if (n <= 1) t
      else sumTo(t.map(_ + n), n - 1)

    runtime.unsafeRun(sumTo(IO.effectTotal(0), depth))
  }


  @Benchmark
  def zioFacadeMap(): BigInt = zioFaMap(zio.IOBenchmarks)


  private[this] def zioFaMap(runtime: Runtime[Any]): BigInt = {
    runtime.unsafeRun(myMap(zio.se.ActualZio))
  }

  def myMap(zio: se.ZIOFacade): zio.ZIO[Any, Nothing, BigInt] = {
    import zio._
    import zio.ZIO._

    @tailrec
    def sumTo(t: zio.ZIO[Any, Nothing, BigInt], n: Int): zio.ZIO[Any, Nothing, BigInt] =
      if (n <= 1) t
      else sumTo(t.map(_ + n), n - 1)

    sumTo(ZIO.succeed(0), depth)
  }
}
