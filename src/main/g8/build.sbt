lazy val buildSettings = List(
  organization       := "com.adelbertc",
  licenses           += ("Apache-2.0", new URL("https://www.apache.org/licenses/LICENSE-2.0.txt")),
  scalaVersion       := "2.13.1",
  version            := "0.1.0-SNAPSHOT",

  // sbt-header stuff
  organizationName   := "Adelbert Chang",
  startYear          := Some(2018)
)

val catsVersion         = "2.0.0"
val catsEffectVersion   = "2.0.0"
val specs2Version       = "4.7.1"

lazy val commonSettings = List(
  libraryDependencies ++= List(
    compilerPlugin("org.typelevel % "kind-projector" % "0.11.0" cross CrossVersion.full),
    "org.typelevel" %% "cats-kernel"        % catsVersion,
    "org.typelevel" %% "cats-core"          % catsVersion,
    "org.typelevel" %% "cats-free"          % catsVersion,
    "org.typelevel" %% "cats-effect"        % catsEffectVersion,
    "org.typelevel" %% "cats-laws"          % catsVersion         % "test",
    "org.specs2"    %% "specs2-core"        % specs2Version       % "test",
    "org.specs2"    %% "specs2-scalacheck"  % specs2Version       % "test"
  )
)

lazy val $name;format="camel"$Settings = buildSettings ++ commonSettings

lazy val $name;format="camel"$ =
  project.in(file(".")).
  settings(name := "$name$").
  settings(description := "").
  settings($name;format="camel"$Settings)
