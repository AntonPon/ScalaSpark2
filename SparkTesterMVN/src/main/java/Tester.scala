
import org.apache.spark.graphx._
import org.apache.spark.graphx.lib.TriangleCount
import org.apache.spark.{SparkConf, SparkContext}

object Tester {

  def main(args: Array[String]): Unit = {
/*    val spark = SparkSession.builder.master("local[*]").appName("Simple Spark Project").getOrCreate()
    val sc = spark.sparkContext

    val graph = GraphLoader.edgeListFile(sc, "followers.txt", true)
      .partitionBy(PartitionStrategy.RandomVertexCut)
    val triCounts = graph.triangleCount().vertices
    triCounts.take(6)
    println(triCounts.values.sum())
    println(triCounts.map(_._2).sum())
    println(triCounts.map(_._2).reduce(_ + _))*/

    val conf = new SparkConf().setAppName("Count Triangles")
    val sc = new SparkContext(conf)
    sc.setLogLevel("ERROR")

//    val spark = SparkSession.builder.master("local[*]").appName("Simple Spark Project").getOrCreate()
//    val sc = spark.sparkContext
    val graph = GraphLoader.edgeListFile(sc, "followers.txt", true)
      .partitionBy(PartitionStrategy.RandomVertexCut)

    standartCount(graph)
    customizedCount(graph)


  }

  def standartCount(graph: Graph[Int, Int]) = {
    println("Triangle count standard impl")
    val triangleCount = TriangleCount.run(graph).vertices
    val triCounts = graph.triangleCount().vertices
    triCounts.take(6)
    println("triangle numbers: "+triangleCount.map(_._2).reduce(_ + _)/3)

  }

  def customizedCount(graph: Graph[Int, Int]) = {
    println("Triangle count super impl")
    val triangleCount = TriangleCountSuperImpl.run(graph).vertices
    val triCounts = graph.triangleCount().vertices
    triCounts.take(6)
    println("triangle numbers: "+triangleCount.map(_._2).reduce(_ + _)/3)
  }





}
