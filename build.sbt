val dottyVersion = "0.26.0"
val versionNumber = "0.1.0"

lazy val runtime = project
  .in(file("runtime"))
  .settings(
    name := "zio-se",
    description := "A tool to find unexpected state in zio programs",

    version := versionNumber,

    scalaVersion := dottyVersion,

    libraryDependencies += "com.novocode" % "junit-interface" % "0.11" % Test,

    libraryDependencies += "dev.zio" %% "zio" % "1.0.1"
  )

lazy val test = project
  .in(file("test"))
  .dependsOn(runtime)
  .settings(
    name := "zio-se-test",
    description := "The test part of zio-se",

    version := versionNumber,

    scalaVersion := dottyVersion,

    libraryDependencies += "com.novocode" % "junit-interface" % "0.11" % Test,

    libraryDependencies += "dev.zio" %% "zio" % "1.0.1"
  )

lazy val benchmarks = project
  .in(file("benchmarks"))
  .dependsOn(
    runtime, test
  )
  .enablePlugins(JmhPlugin)
  .settings(
    scalaVersion := dottyVersion,
    skip in publish := true
  )
