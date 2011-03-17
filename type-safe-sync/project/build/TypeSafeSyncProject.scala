import sbt._

class TypeSafeSyncProject(info: ProjectInfo) extends DefaultProject(info) {
  
  // Additional repositories
  val scalaToolsSnapshots = "Scala-Tools Maven2 Snapshots Repository" at "http://scala-tools.org/repo-snapshots"
  val templemoreRepo = "templemore sbt repo" at "http://templemore.co.uk/repo"

  // Test dependencies
  val scalatest = "org.scalatest" % "scalatest" % "1.3" % "test"
  val mockito = "org.mockito" % "mockito-all" % "1.8.5" % "test"
}

