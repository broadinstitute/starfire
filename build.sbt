lazy val root = (project in file(".")).aggregate(core, auth, app)

lazy val core = (project in file("core"))
lazy val auth = (project in file("auth"))
lazy val app = (project in file("app")).dependsOn(core, auth)



