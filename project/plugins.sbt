// Plug-in availability statuses for SBT 1.x migration are listed at:
// https://github.com/sbt/sbt/wiki/sbt-1.x-plugin-migration

resolvers += "Typesafe repository" at "https://dl.bintray.com/typesafe/maven-releases/"

addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.14.5")

addSbtPlugin("com.typesafe.sbt" % "sbt-git" % "0.9.3")

addSbtPlugin("com.github.gseitz" % "sbt-release" % "1.0.7")
