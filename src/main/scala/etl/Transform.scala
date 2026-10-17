package etl

import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.functions.{col, current_timestamp, initcap, lower, round, trim, to_date}

object Transform {
  def clean(df: DataFrame): DataFrame = {
    df
      .dropDuplicates()
      .withColumn("customer_name", trim(col("customer_name")))
      .withColumn("category", initcap(trim(col("category"))))
      .withColumn("region", initcap(trim(col("region"))))
      .withColumn("status", lower(trim(col("status"))))
      .filter(col("customer_name").isNotNull && col("customer_name") =!= "")
      .filter(col("quantity") > 0)
      .filter(col("price") > 0)
      .withColumn("order_date", to_date(col("order_date"), "yyyy-MM-dd"))
      .filter(col("order_date").isNotNull)
      .filter(col("status").isin("paid", "shipped", "pending"))
      .dropDuplicates("order_id")
      .withColumn("total_amount", round(col("quantity") * col("price"), 2))
      .withColumn("processed_at", current_timestamp())
  }
}
