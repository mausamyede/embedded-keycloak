inThisBuild(
  List(
    scalaVersion := "2.13.3",
    version := "0.2.0",
    name := "embedded-keycloak",
    description := "embedded keycloak for testing",
    organization := "io.github.mausamyede",
    homepage := Some(url("https://github.com/mausamyede/embedded-keycloak")),
    scmInfo := Some(ScmInfo(url("https://github.com/mausamyede/embedded-keycloak"), "git@github.com:mausamyede/embedded-keycloak.git")),
    licenses := List(
      "Apache-2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0")
    ),
    developers := List(
      Developer("mausamyede", "TMT", "", url("https://github.com/mausamyede"))
    ),
    scalacOptions ++= Seq(
      "-encoding",
      "UTF-8",
      "-feature",
      "-unchecked",
      "-deprecation",
      "-Xlint",
      "-Ywarn-dead-code"
    ),
    publishMavenStyle := true,
    pomIncludeRepository := { _ => false }
  )
)

lazy val `embedded-keycloak-root` = project.in(file("."))
lazy val `embedded-keycloak` = project
  .settings(
    libraryDependencies ++= Seq(
      "com.lihaoyi"       %% "requests"    % "0.6.5",
      "com.lihaoyi"       %% "os-lib"      % "0.7.1",
      "com.lihaoyi"       %% "upickle"     % "1.2.2",
      "com.lihaoyi"       %% "ujson"       % "1.2.2",
      "com.iheart"        %% "ficus"       % "1.5.0",
      //AKKA-DOWNLOADER
      "com.typesafe.akka" %% "akka-http"   % "10.2.1",
      "com.typesafe.akka" %% "akka-stream" % "2.6.10",
      //TEST
      "org.scalatest"     %% "scalatest"   % "3.2.2" % Test
    ),
    parallelExecution in Test in ThisBuild := false
  )

ThisBuild / publishTo := sonatypePublishToBundle.value
