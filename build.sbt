name := """recomm"""
organization := "com.example"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  "org.json" % "json" % "20210307",
)

lazy val root = (project in file(".")).enablePlugins(PlayJava)
  //.enablePlugins(PlayNettyServer).disablePlugins(PlayPekkoHttpServer) // uncomment to use the Netty backend

crossScalaVersions := Seq("2.13.14", "3.3.3")

scalaVersion := crossScalaVersions.value.head

libraryDependencies += guice
