import com.datastax.spark.connector._
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.SparkContext._
import org.apache.spark.sql.cassandra.CassandraSQLContext
import com.typesafe.config._


object Run {
  def main(args: Array[String]) =
        
        println("Running")
        val appConf = ConfigFactory.load()
        val cassandra = appConf.getString("app.cassandra")
        val spark = appConf.getString("app.spark")
        var jars = new Array[String](1)
        jars(0)= appConf.getString("app.project_jar")
        val conf = new SparkConf(true)
                .set("spark.cassandra.connection.host", cassandra)
                .set("spark.driver.host",spark)
                .setJars(jars)
		.set("spark.cores.max","2")
        val sc = new SparkContext("spark://"+spark+":7077", "scala", conf)

        //val rdd = sc.cassandraTable("usmc", "user_count_match2").

        val cc = new CassandraSQLContext(sc)


        val rdd = cc.sql("SELECT count(*) from usmc.usersimpletaxonomies3 group by usmcuserid having count(*) > 1  ")

        var fs = printf("cont:%s",rdd.first)
        println(fs)
        //println(rdd.map(_.getInt("value")).sum)

}
