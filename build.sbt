lazy val commonSettings = Seq(
  organization := "com.example",
  scalaVersion := "2.13.3",
  libraryDependencies ++= Seq(
    "org.scalatest" %% "scalatest" % "3.2.0" % Test
    ),
  assemblyOption in assembly := (assemblyOption in assembly).value.copy(includeScala = true),
  assemblyJarName in assembly := s"${name.value}-${version.value}.jar"
  )

lazy val root = (project in file("."))
  .settings(
    name := "price-basket",
    commonSettings,
    addArtifact(artifact in(Compile, assembly), assembly)
    )
