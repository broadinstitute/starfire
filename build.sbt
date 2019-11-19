lazy val root = (project in file(".")).aggregate(core, auth, app, util)

lazy val util = (project in file("util"))
lazy val core = (project in file("core"))
lazy val auth = (project in file("auth")).dependsOn(util)
lazy val app = (project in file("app")).dependsOn(core, auth, util)



