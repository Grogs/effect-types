name := "code"

version := "1.0"

scalaVersion := "2.12.8"

libraryDependencies ++= Seq(
  "org.typelevel" %% "cats-effect" % "1.2.0",
  "org.scalaz" %% "scalaz-zio" % "0.6.0",
  "org.scalaz" %% "scalaz-zio-interop-future" % "0.6.0",
  "org.scalaz" %% "scalaz-zio-interop-cats" % "0.6.0",
  "org.scalaz" %% "scalaz-zio-interop-monix" % "0.6.0",
  "io.monix" %% "monix" % "3.0.0-RC2",
)

resolvers += Resolver.sonatypeRepo("releases")

addCompilerPlugin("org.spire-math" %% "kind-projector" % "0.9.8")
