lazy val root = (project in file(".")).aggregate(core, auth, app, util, gcp)

lazy val util = (project in file("util"))
lazy val core = (project in file("core"))
lazy val auth = (project in file("auth")).dependsOn(util)
lazy val gcp = (project in file("gcp"))
lazy val app = (project in file("app")).dependsOn(core, auth, util, gcp)
