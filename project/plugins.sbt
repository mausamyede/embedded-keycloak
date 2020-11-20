addSbtPlugin("com.github.cb372" % "sbt-explicit-dependencies" % "0.2.15")
addSbtPlugin("com.timushev.sbt" % "sbt-updates"               % "0.5.1")
addSbtPlugin("org.scalameta"    % "sbt-scalafmt"              % "2.4.2")
addSbtPlugin("com.jsuereth"     % "sbt-pgp"                   % "2.0.1")
addSbtPlugin("org.xerial.sbt"   % "sbt-sonatype"              % "3.9.5")

scalacOptions ++= Seq(
  "-encoding",
  "UTF-8",
  "-feature",
  "-unchecked",
  "-deprecation",
  //"-Xfatal-warnings",
  "-Xlint:-unused,_",
  "-Yno-adapted-args",
  "-Ywarn-dead-code",
  "-Xfuture"
)
