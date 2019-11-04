lazy val root = (project in file(".")).aggregate(core, auth)

lazy val core = (project in file("core"))
lazy val auth = (project in file("auth"))

