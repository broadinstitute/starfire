lazy val root = (project in file(".")).aggregate(codegen)

lazy val codegen = (project in file("codegen"))
