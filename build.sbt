name := "scala-library-seed"

organization := "com.crunchdevelopment.scala-library-seed"
scalaVersion := "2.11.8"

crossScalaVersions  := Seq("2.11.8", "2.10.4")
licenses += ("MIT", url("http://opensource.org/licenses/MIT"))


val scm = {
    <scm>
      <connection>scm:git:git@github.com:alexanderscott/scala-library-seed.git</connection>
      <developerConnection>scm:git:git@github.com:alexanderscott/scala-library-seed.git</developerConnection>
      <url>https://alexanderscott.info</url>
      <tag>HEAD</tag>
    </scm>
  }

val developersXml = {
	<url>https://github.com/alexanderscott/scala-library-seed</url>
		<scm>
			<url>git@github.com:alexanderscott/scala-library-seed.git</url>
			<connection>scm:git:git@github.com:alexanderscott/scala-library-seed.git</connection>
		</scm>
		<developers>
			<developer>
				<id>alexanderscott</id>
				<name>Alex Ehrnschwender</name>
				<url>http://alexanderscott.info</url>
			</developer>
		</developers>
}

pomExtra := scm ++ developersXml

fork in Test := true
parallelExecution in Test := false
publishArtifact in Test := false

lazy val root = (project in file("."))
  .enablePlugins(ScalaUnidocPlugin, GhpagesPlugin)
  .settings(
      autoAPIMappings := true,
      apiURL := Some(url("http://alexanderscott.github.io/scala-library-seed"))
  )


resolvers ++= Seq(
  Resolver.sonatypeRepo("releases"),
  Resolver.sonatypeRepo("snapshots"),
  "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/",
  "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"
)

libraryDependencies ++= Seq(
  "org.scalatest"   %% "scalatest"    % "3.0.1" % Test
)

bintrayOrganization in bintray := Some("crunchdevelopment")

git.remoteRepo := "git@github.com:alexanderscott/scala-library-seed.git"

scalacOptions ++= Seq(
	"-encoding", "UTF-8",
	"-Xlint",
	"-deprecation",
	"-Xfatal-warnings",
	"-feature",
	"-language:postfixOps",
	"-unchecked"
)

/*
ScalaDoc
scalacOptions in (Compile, doc) <++= baseDirectory in LocalProject("scala-library-seed") map { bd =>
	Seq(
		"-sourcepath", bd.getAbsolutePath
	)
}
 */

