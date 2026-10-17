package etl

import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType, DoubleType}
import org.apache.spark.sql.{DataFrame, SparkSession}

object Extract {
  def read(spark: SparkSession, path: String): DataFrame = {
    spark.read
      .option("header", "true")
      .schema(salesSchema)
      .csv(path)
  }

  private val salesSchema: StructType = StructType(Seq(
    StructField("order_id",      IntegerType, nullable = true),
    StructField("customer_name", StringType,  nullable = true),
    StructField("category",      StringType,  nullable = true),
    StructField("region",        StringType,  nullable = true),
    StructField("quantity",      IntegerType, nullable = true),
    StructField("price",         DoubleType,  nullable = true),
    StructField("order_date",    StringType,  nullable = true),
    StructField("status",        StringType,  nullable = true)
  ))
}
