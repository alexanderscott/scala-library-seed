name := "scala-library-seed"

organization := "com.crunchdevelopment"

scalaVersion := "2.10.4"

crossScalaVersions := Seq("2.10.4", "2.11.4")

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "2.2.1" % "test"
)

bintraySettings

bintray.Keys.bintrayOrganization in bintray.Keys.bintray := Some("crunchdevelopment")

licenses += ("MIT", url("http://opensource.org/licenses/MIT"))
