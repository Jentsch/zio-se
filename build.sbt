val dottyVersion = "0.26.0"

lazy val root = project
  .in(file("."))
  .settings(
    name := "zio-se",
    description := "A tool to find unexpected state in zio programs",

    version := "0.1.0",

    scalaVersion := dottyVersion,

    libraryDependencies += "com.novocode" % "junit-interface" % "0.11" % "test",

    libraryDependencies += "dev.zio" %% "zio" % "1.0.1"
  )

lazy val benchmarks = project
  .in(file("benchmarks"))
  .dependsOn(
    root
  )
  .enablePlugins(JmhPlugin)
  .settings(
    scalaVersion := dottyVersion,
    skip in publish := true
  )
