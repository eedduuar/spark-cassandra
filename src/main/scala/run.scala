import com.datastax.spark.connector._
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.SparkContext._
import org.apache.spark.sql.cassandra.CassandraSQLContext

object Run {
  def main(args: Array[String]) =
        println("Running")
        var jars = new Array[String](1)
        jars(0)= "/home/usmc/workspace/spark-cassandra/target/scala-2.10/Spark-Cassandra-assembly-0.1.jar"
        //jars(1)="/home/usmc/cassandra-thrift-2.1.3.jar"
        //jars(2)="/home/usmc/.m2/repository/com/datastax/cassandra/cassandra-driver-core/2.1.4/cassandra-driver-core-2.1.4.jar"
        //jars(3)="/home/usmc/.m2/repository/joda-time/joda-time/2.6/joda-time-2.6.jar"
        //jars(4)="/home/usmc/workspace/scala/hello/target/scala-2.10/firstsparkapp_2.10-1.0-SNAPSHOT.jar"
        val conf = new SparkConf(true)
                .set("spark.cassandra.connection.host", "10.0.0.161")
                .set("spark.driver.host","10.0.0.131")
                .setJars(jars)
        val sc = new SparkContext("spark://10.0.0.131:7077", "scala", conf)

        //val rdd = sc.cassandraTable("usmc", "user_count_match2").

        val cc = new CassandraSQLContext(sc)


        val rdd = cc.sql("SELECT max(user_number) from usmc.numerics_id ")

        var fs = printf("max:%s",rdd.first)
        println(fs)
        //println(rdd.map(_.getInt("value")).sum)

}
