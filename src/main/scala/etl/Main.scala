package etl

import org.apache.spark.sql.SparkSession

object Main {
  def main(args: Array[String]): Unit = {
    val params = parseArgs(args)
    val inputPath = params.getOrElse("input", sys.error("--input required"))
    val outPath = params.getOrElse("output", sys.error("--output required"))

    val master = sys.env.getOrElse("SPARK_MASTER", "local[*]")

    val spark = SparkSession.builder()
      .appName("ETL_Simple")
      .master(master)
      .getOrCreate()

    try {
      val raw = Extract.read(spark, inputPath)

      val cleaned = Transform.clean(raw)

      Load.write(cleaned, outPath)

      println("ETL completed success!")
    } finally {
      spark.stop()
    }
  }

  private def parseArgs(args: Array[String]): Map[String, String] = {
    args.sliding(2, 2).collect {
      case Array(key, value) if key.startsWith("--") => key.drop(2) -> value
    }.toMap
  }
}
