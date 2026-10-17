ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.12.18"

ThisBuild / assemblyMergeStrategy := {
  case PathList(ps @ _*) if ps.last == "module-info.class" =>
    MergeStrategy.discard
  case PathList("META-INF", "services", _ @ _*) =>
    MergeStrategy.concat
  case PathList("META-INF", _ @ _*) =>
    MergeStrategy.discard
  case "reference.conf"   => MergeStrategy.concat
  case "application.conf" => MergeStrategy.concat
  case _ => MergeStrategy.first
}

lazy val root = (project in file("."))
  .settings(
    name := "ETL_Simple",
    assembly / assemblyJarName := "etl_simple.jar",
    libraryDependencies ++= Seq(
      "org.apache.spark" %% "spark-core" % "3.5.1" % Provided,
      "org.apache.spark" %% "spark-sql" % "3.5.1" % Provided
    )
  )
