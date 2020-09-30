package zio.se

import org.junit.Test
import org.junit.Assert._

class ZioSeTest {

  def run(zio: ZIOFacade): zio.UIO[Int] = {
    import zio._
    import zio.ZIO._

    for {
      r1 <- ZIO.succeed(1).race(ZIO.succeed(2))
      r2 <- ZIO.succeed(1).race(ZIO.succeed(2))
    } yield r1 * r2
  }


  @Test def t1(): Unit = {
    val result = run(ZioSt)
    
    assertEquals(result.toSet, Set(1, 2, 4))
  }
}
