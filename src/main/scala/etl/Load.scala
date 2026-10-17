package etl

import org.apache.spark.sql.{DataFrame, SparkSession}

object Load {
  def write(df: DataFrame, path:String): Unit = {
    df.write.mode("overwrite").parquet(path)
  }
}
