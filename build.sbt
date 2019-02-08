import Dependencies._

lazy val Orgs = new {
  val DIG = "org.broadinstitute.dig"
}

lazy val Paths = new {
  //`publish` will produce artifacts under this path
  val LocalRepo = "/humgen/diabetes/users/dig/loamstream/repo"
}

lazy val Resolvers = new {
  val LocalRepo = Resolver.file("localRepo", new File(Paths.LocalRepo))
  val SonatypeReleases = Resolver.sonatypeRepo("releases")
  val SonatypeSnapshots = Resolver.sonatypeRepo("snapshots")
}

// disable .jar publishing 
publishArtifact in (Compile / packageBin) := false
// disable publishing the javadoc jar
publishArtifact in (Compile / packageDoc) := false
// disable publishing the source jar
publishArtifact in (Compile / packageSrc) := false

// create an Artifact for publishing a .zip file instead of a .jar
artifact in (Compile / packageBin) := {
  val previous: Artifact = (artifact in (Compile / packageBin)).value

  previous.withType("zip").withExtension("zip")
}

lazy val root = (project in file("."))
  .settings(
    name := "dig-loam-images",
    organization := Orgs.DIG,
    //NB: version set in version.sbt
    publishTo := Some(Resolvers.LocalRepo),
    // add the .zip file to what gets published 
    addArtifact(artifact in (Compile / packageBin), Compile / packageBin).settings
  )

//Make sure the contents of recipes/ makes it into binary artifact
(resourceDirectory in Compile) := baseDirectory.value / "recipes"

//Enables `buildInfoTask`, which bakes git version info into the LS jar.
enablePlugins(GitVersioning)

val buildInfoTask = taskKey[Seq[File]]("buildInfo")

buildInfoTask := {
  val dir = (resourceManaged in Compile).value
  val n = name.value
  val v = version.value
  val branch = git.gitCurrentBranch.value
  val lastCommit = git.gitHeadCommit.value
  val describedVersion = git.gitDescribedVersion.value
  val anyUncommittedChanges = git.gitUncommittedChanges.value

  val buildDate = java.time.Instant.now

  val file = dir / "versionInfo.properties"

  val contents = s"name=${n}\nversion=${v}\nbranch=${branch}\nlastCommit=${lastCommit.getOrElse("")}\nuncommittedChanges=${anyUncommittedChanges}\ndescribedVersion=${describedVersion.getOrElse("")}\nbuildDate=${buildDate}\n"

  IO.write(file, contents)

  Seq(file)
}

(resourceGenerators in Compile) += buildInfoTask.taskValue

//TODO: Is the below necessary?
import ReleaseTransformations._

releaseProcess := Seq[ReleaseStep](
  checkSnapshotDependencies, // : ReleaseStep
  inquireVersions, // : ReleaseStep
  runClean, // : ReleaseStep
  runTest, // : ReleaseStep
  setReleaseVersion, // : ReleaseStep
  commitReleaseVersion, // : ReleaseStep, performs the initial git checks
  tagRelease,           // : ReleaseStep
  // run 'publishLocal' instead of 'publish', since publishing to a repo on the Broad FS never resulted in
  // artifacts that could be resolved by other builds. :(
  // See: https://github.com/sbt/sbt-release#can-we-finally-customize-that-release-process-please
  //      https://stackoverflow.com/questions/44058275/add-docker-publish-step-to-sbt-release-process-with-new-tag
  //      https://github.com/sbt/sbt/issues/1917
  releaseStepCommand("publishLocal"),
  setNextVersion, // : ReleaseStep
  commitNextVersion, // : ReleaseStep
  pushChanges // : ReleaseStep, also checks that an upstream branch is properly configured
)

