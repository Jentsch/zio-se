
# Getting started

Add zio-se-runtime to your sbt dependencies with:
```sbt
libraryDependencies += "not.jet" %% "zio-se" % "1.0.0"
```

```scala
import zio.se.ZIOFacade

def example(zio: ZIOFacade): zio.UIO[Int] = {
  import zio._
  import zio.ZIO._

  succeed(1).race(succeed(2))
}
```

Test it with:

Add zio-se-test to your sbt test dependencies with:
```sbt
libraryDependencies += "not.jet" %% "zio-se" % "1.0.0" % Test
```

```scala
import org.junit.Test
import org.junit.Assert._

@Test def t1(): Unit = {
  val result = example(ZioSe)
    
  assertEquals(result.toSet, Set(1, 2))
}
```
