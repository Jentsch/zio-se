package zio

import scala.concurrent.ExecutionContext

import zio.internal._

object IOBenchmarks extends BootstrapRuntime {

  override val platform: Platform = Platform.benchmark

  val TracedRuntime = new BootstrapRuntime {
    override val platform = Platform.benchmark.withTracing(Tracing.enabled)
  }
}
