resolvers += "jgit-repo" at "http://download.eclipse.org/jgit/maven"

resolvers += Classpaths.sbtPluginReleases

// To publish API ScalaDoc as html
addSbtPlugin("com.typesafe.sbt" % "sbt-site" % "1.2.0")

// Publish API to Github Pages
addSbtPlugin("com.typesafe.sbt" % "sbt-ghpages" % "0.6.0")

// Combined documentation
addSbtPlugin("com.eed3si9n" % "sbt-unidoc" % "0.4.0")


// Publish to BinTray
addSbtPlugin("me.lessis" % "bintray-sbt" % "0.3.0")

// Dependency graph to prevent collisions during jar assembly
addSbtPlugin("net.virtual-void" % "sbt-dependency-graph" % "0.8.0")

// Release management
addSbtPlugin("com.github.gseitz" % "sbt-release" % "1.0.4")
