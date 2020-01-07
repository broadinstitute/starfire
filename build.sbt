lazy val root = (project in file(".")).aggregate(core, app)

lazy val core = project in file("core")
lazy val app = (project in file("app")).dependsOn(core)
