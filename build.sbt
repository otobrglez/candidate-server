scalaVersion := "2.13.6"
name := "candidate-server"
version := "1.0"

libraryDependencies ++= Seq(
  "com.github.finagle" %% "finchx-core" % "0.32.1",
  "com.github.finagle" %% "finchx-circe" % "0.32.1",
  "io.circe" %% "circe-core" % "0.14.1",
  "io.circe" %% "circe-generic" % "0.14.1",
  "com.typesafe.slick" %% "slick" % "3.3.3",
  "org.slf4j" % "slf4j-nop" % "1.7.32",
  "com.typesafe.slick" %% "slick-hikaricp" % "3.3.3",
  "com.h2database" % "h2" % "1.4.200"
)
