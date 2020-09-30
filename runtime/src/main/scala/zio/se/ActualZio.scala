package zio.se

object ActualZio extends ZIOFacade {

  type ZIO[-R, +E, +A] = zio.ZIO[R, E, A]

  object ZIO extends ZIO_Object {
    def succeed[A](a: A): ZIO[Any, Nothing, A] = zio.ZIO.succeed(a)

    def [A] (z1: ZIO[Any, Nothing, A]).race(z2: ZIO[Any, Nothing, A]): ZIO[Any, Nothing, A] =
      z1.race(z2)

    def [A, B] (z1: ZIO[Any, Nothing, A]).map(f: A => B): ZIO[Any, Nothing, B] =
      z1.map(f)
    
    def [A, B] (z1: ZIO[Any, Nothing, A]).flatMap(f: A => ZIO[Any, Nothing, B]): ZIO[Any, Nothing, B] =
      z1.flatMap(f)
  }
}
