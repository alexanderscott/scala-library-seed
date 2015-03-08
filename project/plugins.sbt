
resolvers += "jgit-repo" at "http://download.eclipse.org/jgit/maven"

// To publish API ScalaDoc as html
addSbtPlugin("com.typesafe.sbt" % "sbt-site" % "0.8.1")

// Publish API to Github Pages
addSbtPlugin("com.typesafe.sbt" % "sbt-ghpages" % "0.5.3")

// Combined documentation
addSbtPlugin("com.eed3si9n" % "sbt-unidoc" % "0.3.1")

resolvers += Classpaths.sbtPluginReleases

// Publish to BinTray
resolvers += Resolver.url(
  "bintray-sbt-plugin-releases",
  url("http://dl.bintray.com/content/sbt/sbt-plugin-releases"))(
    Resolver.ivyStylePatterns)

addSbtPlugin("me.lessis" % "bintray-sbt" % "0.1.2")

// Dependency graph to prevent collisions during jar assembly
addSbtPlugin("net.virtual-void" % "sbt-dependency-graph" % "0.7.4")

// Release management
addSbtPlugin("com.github.gseitz" % "sbt-release" % "0.8.3")
