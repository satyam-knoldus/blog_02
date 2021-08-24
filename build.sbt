lazy val root = project
  .in(file("."))
  .settings(name := "CalciteBlog")
  .settings(version := "1.0")
  .settings(scalaVersion := "2.13.6")
  .settings(libraryDependencies ++= Seq(
    "org.apache.calcite" % "calcite-core" % "1.27.0"
  ))