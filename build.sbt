name := "FPSimplified"

version := "0.1"

scalaVersion := "2.13.1"
// scalaVersion := "2.12.10" // to get chapter44_50 to work

val monocleVersion: String = "2.0.0"

libraryDependencies ++= Seq(
  "com.github.julien-truffaut" %%  "monocle-core"  % monocleVersion,
  "com.github.julien-truffaut" %%  "monocle-macro" % monocleVersion,
  "com.github.julien-truffaut" %%  "monocle-law"   % monocleVersion % "test"
)

// https://www.scalactic.org/install
libraryDependencies += "org.scalactic" %% "scalactic" % "3.1.1"
