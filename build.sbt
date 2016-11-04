import sbt._
import sbt.inc.Analysis
import sbtassembly.AssemblyPlugin.assemblySettings


libraryDependencies ++= Seq(
  "biz.source_code"           %  "base64coder"        % "2010-12-19"
)

scalacOptions ++= Seq(
  "-unchecked",
  "-deprecation",
  "-Xlint",
  "-Ywarn-dead-code",
  "-language:_",
  "-target:jvm-1.7",
  "-encoding", "UTF-8"
)

lazy val commonSettings = Seq(
  organization := "nelly",
  version := "1.0",
  scalaVersion := "2.11.8",
  fork in run := true,
  parallelExecution in ThisBuild := false,
  parallelExecution in Test := false
)

lazy val versions = new {
  val guice = "4.0"
  val logback = "1.0.13"
  val avajeVersion = "3.0.0"
  val jackson = "2.8.2"
  val playJson = "2.3.4"
  val sqlite = "3.14.2.1"
  val play = "2.5.3"
}

lazy val projectAssemblySettings = Seq(
  assemblyJarName in assembly := name.value + ".jar",
  assemblyMergeStrategy in assembly := {
    case "BUILD" => MergeStrategy.discard
    case PathList("org", "jboss", "netty", xs @ _*)  =>  MergeStrategy.first
    case PathList("org", "apache", "commons", "logging", xs@_*) => MergeStrategy.first
    case PathList("META-INF", "maven","jline","jline", "pom.properties" ) => MergeStrategy.discard
    case PathList("META-INF", "maven","jline","jline", "pom.xml" ) => MergeStrategy.discard
    case PathList("META-INF", "sun-jaxb.episode") => MergeStrategy.first
    case PathList("META-INF", "MANIFEST.MF") => MergeStrategy.discard
    case PathList("META-INF", "io.netty.versions.properties", xs @ _*) => MergeStrategy.last
    case PathList("javax", "servlet", xs @ _*)         => MergeStrategy.first
    case PathList("javax", "transaction", xs @ _*)     => MergeStrategy.first
    case PathList("javax", "mail", xs @ _*)     => MergeStrategy.first
    case PathList("javax", "activation", xs @ _*)     => MergeStrategy.first
    case other => { println(s" OTHER: ${other}"); MergeStrategy.defaultMergeStrategy(other) }
  }
)

 lazy val chat = (project.in(file("chat")))
   .settings(commonSettings: _*)
   .settings(assemblySettings: _*)
  .enablePlugins(PlayJava, PlayEbean)
  .settings(
     name := "nelly-chat",
     libraryDependencies ++= Seq(
       "org.xerial" % "sqlite-jdbc" % versions.sqlite,
       "mysql" % "mysql-connector-java" % "5.1.18",
       "com.typesafe.play"         %% "play"               % versions.play     % "provided",
       javaWs,
       javaJdbc,
       cache,
       evolutions,
       "com.typesafe.play" %% "play-json" % versions.playJson,
       "com.fasterxml.jackson.core" % "jackson-databind" % versions.jackson,
       "com.fasterxml.jackson.datatype" % "jackson-datatype-joda" % versions.jackson,
       "com.fasterxml.jackson.module" % "jackson-module-scala_2.11" % versions.jackson,
       "com.typesafe.akka" %% "akka-http-core" % "2.4.11",
      "com.typesafe.akka" %% "akka-http-experimental" % "2.4.11",
      "com.typesafe.akka" %% "akka-http-jackson-experimental" % "2.4.11",
      "com.typesafe.akka" %% "akka-http-spray-json-experimental" % "2.4.11",
      "com.typesafe.akka" %% "akka-http-xml-experimental" % "2.4.11"

     )
  )

def enhanceEbeanClasses(classpath: Classpath, analysis: Analysis, classDirectory: File, pkg: String): Analysis = {
  // Ebean (really hacky sorry)
  val cp = classpath.map(_.data.toURI.toURL).toArray :+ classDirectory.toURI.toURL
  val cl = new java.net.URLClassLoader(cp)
  val t = cl.loadClass("com.avaje.ebean.enhance.agent.Transformer").getConstructor(classOf[Array[URL]], classOf[String]).newInstance(cp, "debug=0").asInstanceOf[AnyRef]
  val ft = cl.loadClass("com.avaje.ebean.enhance.ant.OfflineFileTransform").getConstructor(
    t.getClass, classOf[ClassLoader], classOf[String]
  ).newInstance(t, ClassLoader.getSystemClassLoader, classDirectory.getAbsolutePath).asInstanceOf[AnyRef]
  ft.getClass.getDeclaredMethod("process", classOf[String]).invoke(ft, pkg)
  analysis
}

def avajeEbeanormAgent = "org.avaje.ebeanorm" % "avaje-ebeanorm-agent" % "4.9.2"

def anorm = Seq("org.avaje.ebeanorm" % "avaje-ebeanorm" % "7.6.1", avajeEbeanormAgent)