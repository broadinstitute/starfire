version := "1.0.0"
name := "starfire-auth"
organization := "org.broadinstitute"
scalaVersion := "2.13.1"

val sttpV = "2.0.0-M6"
val googleAuthV = "0.18.0"


libraryDependencies ++= Seq(
  "com.softwaremill.sttp.client" %% "core" % sttpV,
  "com.google.auth" % "google-auth-library-oauth2-http" % googleAuthV,
  "com.github.pathikrit" %% "better-files" % "3.8.0",
  "org.scalatest" %% "scalatest" % "3.0.8" % "test",
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