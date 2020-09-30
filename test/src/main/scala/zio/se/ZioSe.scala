package zio.se

object ZioSe extends ZIOFacade {
  
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

  type Ref[A] = java.util.concurrent.atomic.AtomicReference[A]

  object Ref extends Ref_Object {
    def make[A](a: A) = (new java.util.concurrent.atomic.AtomicReference[A](a)) :: Nil
    
    def [A] (ref: Ref[A]).set(a: A): UIO[Unit] =
      UIO.succeed(ref.set(a))

    def [A] (ref: Ref[A]).get: UIO[A] =
      UIO.succeed(ref.get)
  }
}
