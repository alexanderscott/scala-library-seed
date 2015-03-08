import sbt._
import sbt.Keys._
import com.typesafe.sbt.SbtGhPages._
import com.typesafe.sbt.SbtGit.{GitKeys => git}
import com.typesafe.sbt.SbtSite._
import sbt.LocalProject


object Resolvers {
  val libResolvers = Seq(
    "Typesafe repository snapshots" at "http://repo.typesafe.com/typesafe/snapshots/",
    "Typesafe repository releases" at "http://repo.typesafe.com/typesafe/releases/"
  )
}

object Dependencies {
  import sbt._

  object Versions {
    // Version numbers for package libraries
  }

  val scalaTest = "org.scalatest"   %% "scalatest"    % "2.2.1"
  //val specs2    = "org.specs2"      %% "specs2"       % "2.3.13"


  val libDependencies = Seq(
    scalaTest % "test"
    //specs2 % "test"
  )

}

object Publish {
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

}

object LibBuild extends Build {
  val baseSourceUrl = "https://github.com/alexanderscott/scala-library-seed/tree/"

  val v = version in ThisBuild

  lazy val standardSettings = Defaults.defaultSettings ++
    Seq(
      name                := "scala-library-seed",
      //version             := v,
      organization        := "com.crunchdevelopment.scala-library-seed",
      scalaVersion        := "2.10.4",
      crossScalaVersions  := Seq("2.11.4", "2.10.4"),
      licenses            += ("MIT", url("http://opensource.org/licenses/MIT")),
      //licenses += ("Apache-2.0", url("http://www.apache.org/licenses/LICENSE-2.0.html")),
      resolvers ++= Resolvers.libResolvers,

      pomExtra                := Publish.scm ++ Publish.developersXml,
      publishMavenStyle       := true,
      pomIncludeRepository    := { _ => false },

      bintray.Keys.bintrayOrganization in bintray.Keys.bintray := Some("crunchdevelopment"),

      git.gitRemoteRepo := "git@github.com:alexanderscott/scala-library-seed.git",

      scalacOptions ++= Seq(
        "-encoding", "UTF-8",
        "-Xlint",
        "-deprecation",
        "-Xfatal-warnings",
        "-feature",
        "-language:postfixOps",
        "-unchecked"
      ),

      /*
      ScalaDoc
       */
      scalacOptions in (Compile, doc) <++= baseDirectory in LocalProject("scala-library-seed") map { bd =>
        Seq(
          "-sourcepath", bd.getAbsolutePath
        )
      },
      autoAPIMappings := true,
      apiURL := Some(url("http://alexanderscott.github.io/scala-library-seed")),
      scalacOptions in (Compile, doc) <++= version in LocalProject("scala-library-seed") map { version =>
        val branch = if(version.trim.endsWith("SNAPSHOT")) "master" else version
        Seq[String](
          "-doc-source-url", baseSourceUrl + branch +"€{FILE_PATH}.scala"
        )
      }

    ) ++
    site.settings ++
    site.includeScaladoc(v +"/api") ++
    site.includeScaladoc("latest/api") ++
    ghpages.settings ++
    bintray.Plugin.bintrayPublishSettings


  lazy val root = Project(id = "scala-library-seed",
    base = file("."),
    settings = standardSettings ++ Seq(
      libraryDependencies ++= Dependencies.libDependencies
    )
  )
}
