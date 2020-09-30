package zio.se

import org.junit.Test
import org.junit.Assert._

final class DistributedFlag[F <: ZIOFacade & Singleton](val zio: F) {

    import zio._
    import zio.ZIO._
    import zio.Ref._

    case class Flag(r: Ref[Boolean])

    def make = Ref.make[Boolean](false).map(r => Flag(r))

    def raise(flag: Flag): UIO[Unit] = 
        flag.r.set(true)

    def get(flag: Flag): UIO[Boolean] =
        flag.r.get

    def sync(own: Flag, other: Flag): UIO[Unit] =
        get(other).flatMap {
            case true => own.r.set(true)
            case false => ZIO.unit
        }

}

class DistributedFlagTest {

  def run(zio: ZIOFacade): zio.UIO[Boolean] = {

    val flag = DistributedFlag(zio)

    import zio._
    import zio.ZIO._
    import flag._

    for {
      f <- flag.make
      _ <- flag.raise(f)
      r <- flag.get(f)
    } yield r
  }


  @Test def t1(): Unit = {
    val result = run(ZioSe)
    
    assertEquals(result, List(true))
  }
}
 