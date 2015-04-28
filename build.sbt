name := "Spark-Cassandra"

version := "0.1"

resolvers += Resolver.sonatypeRepo("public")

libraryDependencies ++= Seq(
  "org.apache.spark"  %% "spark-core"              % "1.1.0"  ,
  "org.apache.spark"  %% "spark-sql"               % "1.1.0"  ,
  "com.datastax.cassandra" % "cassandra-driver-core" % "2.1.0" ,
  "com.datastax.spark" %% "spark-cassandra-connector" % "1.1.0" 
)

mergeStrategy in assembly <<= (mergeStrategy in assembly) { (old) =>
  {
    case PathList("javax", "servlet", xs @ _*) => MergeStrategy.last
    case PathList("org", "apache", xs @ _*) => MergeStrategy.last
    case PathList("com", "esotericsoftware", xs @ _*) => MergeStrategy.last
    case PathList("com", "datastax", xs @ _*) => MergeStrategy.last
    case "about.html" => MergeStrategy.rename
    case "plugin.properties" => MergeStrategy.discard
    case x => old(x)
   }
}
