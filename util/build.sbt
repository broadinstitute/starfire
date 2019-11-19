version := "1.0.0"
name := "starfire-app"
organization := "org.broadinstitute"
scalaVersion := "2.13.1"

libraryDependencies ++= Seq(
  "com.github.pathikrit" %% "better-files" % "3.8.0",
  "org.scalatest" %% "scalatest" % "3.0.8" % "test",
  "joda-time" % "joda-time" % "2.10.5",
  "junit" % "junit" % "4.12" % "test"
)

resolvers ++= Seq(
  Resolver.mavenLocal
)

scalacOptions := Seq(
  "-unchecked",
  "-deprecation",
  "-feature"
)

publishArtifact in (Compile, packageDoc) := false
