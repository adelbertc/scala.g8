import de.heikoseeberger.sbtheader.license.Apache2_0

lazy val buildSettings = List(
  organization       := "com.adelbertc",
  licenses           += ("Apache 2.0", url("https://www.apache.org/licenses/LICENSE-2.0")),
  headers            := Map(("scala", Apache2_0("2016", "Adelbert Chang"))),
  scalaVersion       := "2.12.1",
  crossScalaVersions := List("2.10.6", "2.11.8", scalaVersion.value),
  version            := "0.1.0-SNAPSHOT"
)

val catsVersion         = "0.9.0"
val specs2Version       = "3.8.8"
val disabledReplOptions = Set("-Ywarn-unused-import")

lazy val commonSettings = List(
  scalacOptions ++= List(
    "-deprecation",
    "-encoding", "UTF-8",
    "-feature",
    "-language:existentials",
    "-language:higherKinds",
    "-language:implicitConversions",
    "-unchecked",
    "-Xfatal-warnings",
    "-Xlint",
    "-Yno-adapted-args",
    "-Ywarn-dead-code",
    "-Ywarn-numeric-widen",
    "-Ywarn-value-discard"
  ) ++ scalaVersionFlags(scalaVersion.value),
  scalacOptions in (Compile, console) ~= { _.filterNot(disabledReplOptions.contains(_)) },
  scalacOptions in (Test, console) := (scalacOptions in (Compile, console)).value,
  libraryDependencies ++= List(
    compilerPlugin("org.spire-math" % "kind-projector" % "0.9.3" cross CrossVersion.binary),
    "org.typelevel" %% "cats-kernel"       % catsVersion,
    "org.typelevel" %% "cats-core"         % catsVersion,
    "org.typelevel" %% "cats-free"         % catsVersion,
    "org.typelevel" %% "cats-laws"         % catsVersion   % "test",
    "org.specs2"    %% "specs2-core"       % specs2Version % "test",
    "org.specs2"    %% "specs2-scalacheck" % specs2Version % "test"
  ) ++ scalaVersionDeps(scalaVersion.value)
)

lazy val $name;format="camel"$Settings = buildSettings ++ commonSettings

lazy val $name;format="camel"$ =
  project.in(file(".")).
  settings(name := "$name$").
  settings(description := "").
  settings($name;format="camel"$Settings)

def scalaVersionFlags(version: String): List[String] = CrossVersion.partialVersion(version) match {
  case Some((2, 12)) => List("-Ypartial-unification", "-Ywarn-unused-import")
  case Some((2, 11)) => List("-Ywarn-unused-import")
  case _             => List.empty
}

def scalaVersionDeps(version: String): List[ModuleID] = CrossVersion.partialVersion(version) match {
  case Some((2, 11)) => List(
                          compilerPlugin("com.milessabin" % "si2712fix-plugin_2.11.8" % "1.2.0")
                        )
  case Some((2, 10)) => List(
                          compilerPlugin("org.scalamacros" % "paradise"                % "2.1.0" cross CrossVersion.full),
                          compilerPlugin("com.milessabin"  % "si2712fix-plugin_2.10.6" % "1.2.0")
                        )
  case _             => List.empty
}
