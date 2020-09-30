package zio.se

object ZioSt extends ZIOFacade {
  
  type ZIO[-R, +E, +A] = List[A]

  object ZIO extends ZIO_Object {
    def succeed[A](a: A): ZIO[Any, Nothing, A] = a :: Nil

    def [A] (z1: ZIO[Any, Nothing, A]).race(z2: ZIO[Any, Nothing, A]): ZIO[Any, Nothing, A] =
      z1 ++ z2

    def [A, B] (z1: ZIO[Any, Nothing, A]).map(f: A => B): ZIO[Any, Nothing, B] =
      z1.map(f)

    def [A, B] (z1: ZIO[Any, Nothing, A]).flatMap(f: A => ZIO[Any, Nothing, B]): ZIO[Any, Nothing, B] =
      z1.flatMap(f)
  }
}
