package zio.se

/**
 * An incomplete facade of the zio package.
 */
trait ZIOFacade { self =>


  type ZIO[-R, +E, +A]
  type UIO[+A] = ZIO[Any, Nothing, A]

  val ZIO: ZIO_Object
  
  trait ZIO_Object {
    def unit: ZIO[Any, Nothing, Unit] = succeed(())

    def succeed[A](a: A): ZIO[Any, Nothing, A]

    def [A] (z1: ZIO[Any, Nothing, A]).race(z2: ZIO[Any, Nothing, A]): ZIO[Any, Nothing, A]

    def [A, B] (z1: ZIO[Any, Nothing, A]).map(f: A => B): ZIO[Any, Nothing, B]

    def [A, B] (z1: ZIO[Any, Nothing, A]).flatMap(f: A => ZIO[Any, Nothing, B]): ZIO[Any, Nothing, B]
  }

  object UIO {
    inline def succeed[A](a: A): UIO[A] =
      ZIO.succeed(a)
  }

  type Ref[A]

  val Ref: Ref_Object

  trait Ref_Object {
    def make[A](a: A): UIO[Ref[A]]

    def [A] (ref: Ref[A]).set(a: A): UIO[Unit]

    def [A] (ref: Ref[A]).get: UIO[A]
  }
}
