version := "1.0.1"
name := "swagger-sttp-client"
organization := "io.swagger"
scalaVersion := "2.13.1"

val sttpV = "2.0.0-RC6"
val circeV = "0.12.3"


libraryDependencies ++= Seq(
  "com.softwaremill.sttp.client" %% "core" % sttpV,
  "com.softwaremill.sttp.client" %% "circe" % sttpV,
  "io.circe" %% "circe-core" % circeV,
  "io.circe" %% "circe-generic" % circeV,
  "io.circe" %% "circe-parser" % circeV,
  "com.github.pathikrit" %% "better-files" % "3.8.0",
  "joda-time" % "joda-time" % "2.10.5",
  "org.joda" % "joda-convert" % "2.2.1",
  "org.scalatest" %% "scalatest" % "3.1.0" % "test",
  "junit" % "junit" % "4.12" % "test"
)

resolvers ++= Seq(
  Resolver.mavenLocal
)

scalacOptions := Seq(
  "-unchecked",
  "-deprecation",
  "-feature",
  "-Ymacro-annotations"
)

publishArtifact in (Compile, packageDoc) := false

