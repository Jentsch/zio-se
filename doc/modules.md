
# Creating modules

We can not use the (layer)[https://zio.dev/docs/howto/howto_use_layers] dependency mechanism of Zio

```scala
package com.my.module

class MyModule[F <: ZIOFacade & Singleton](val zio: F) {
    // ^ The Singleton is needed to help the compiler

    import zio._
    import zio.UIO._
    // ^ don't import static types but from the provided zio value

    def myFunction: UIO[Int] =
        UIO.succeed(10)
}

object MyModule extends MyModule(zio.st.ActualZio)
// ^ Entrypoint for user, which don't use ZioSt
```
