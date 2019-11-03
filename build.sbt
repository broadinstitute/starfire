lazy val root = (project in file(".")).aggregate(generated)

lazy val generated = (project in file("generated"))
