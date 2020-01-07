version := "1.0.0"
name := "starfire-app"
organization := "org.broadinstitute"
scalaVersion := "2.13.1"

val sttpV = "2.0.0-RC6"


libraryDependencies ++= Seq(
  "com.softwaremill.sttp.client" %% "core" % sttpV,
  "com.github.pathikrit" %% "better-files" % "3.8.0",
  "com.lihaoyi" %% "fastparse" % "2.2.2",
  "org.scalatest" %% "scalatest" % "3.1.0" % "test",
  "joda-time" % "joda-time" % "2.10.5",
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
