scalaVersion := "2.13.9"
name         := "candidate-server"
version      := "1.1"

lazy val finagle = "0.33.0"
lazy val circe   = "0.13.0" // Finagle needs <= 0.13
lazy val slick   = "3.4.1"

libraryDependencies ++= Seq(
  "com.github.finagle" %% "finchx-core"    % finagle,
  "com.github.finagle" %% "finchx-circe"   % finagle,
  "io.circe"           %% "circe-core"     % circe,
  "io.circe"           %% "circe-generic"  % circe,
  "com.typesafe.slick" %% "slick"          % slick,
  "com.typesafe.slick" %% "slick-hikaricp" % slick,
  "org.slf4j"           % "slf4j-nop"      % "2.0.2",
  "com.h2database"      % "h2"             % "2.1.214"
)

scalacOptions ++= Seq(
  "-deprecation",           // Emit warning and location for usages of deprecated APIs.
  "-encoding",
  "utf-8",                  // Specify character encoding used by source files.
  "-explaintypes",          // Explain type errors in more detail.
  "-feature",               // Emit warning and location for usages of features that should be imported explicitly.
  "-language:existentials", // Existential types (besides wildcard types) can be written and inferred
  "-language:higherKinds",  // Allow higher-kinded types
  "-unchecked"              // Enable additional warnings where generated code depends on assumptions.

)
