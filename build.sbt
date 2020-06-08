name := "FPSimplified"

version := "0.1"

// scalaVersion := "2.13.1"
scalaVersion := "2.12.10" // to get chapter44_50 to work

// Monocle fo lenses
val monocleVersion: String = "2.0.0"
libraryDependencies ++= Seq(
  "com.github.julien-truffaut" %%  "monocle-core"  % monocleVersion,
  "com.github.julien-truffaut" %%  "monocle-macro" % monocleVersion,
  "com.github.julien-truffaut" %%  "monocle-law"   % monocleVersion % "test"
)

// Better(?) alternative to Monocle for lenses: QuickLens
// https://github.com/softwaremill/quicklens

// https://www.scalactic.org/install
libraryDependencies += "org.scalactic" %% "scalactic" % "3.1.1"

//addSbtPlugin("org.lyranthe.sbt" % "partial-unification" % "1.1.2")

//libraryDependencies ++= Seq(
//  "org.typelevel" %% "cats" % "0.9.0",
//  "org.typelevel" %% "cats-effect" % "0.3"
//)

libraryDependencies += "org.typelevel" %% "cats-core" % "2.1.1"
libraryDependencies += "org.typelevel" %% "cats-effect" % "2.1.3"

scalacOptions += "-deprecation"
scalacOptions += "-Ypartial-unification"
