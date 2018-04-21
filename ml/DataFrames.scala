// Databricks notebook source
// Start a simple Spark Session
import org.apache.spark.sql.SparkSession
val spark = SparkSession.builder().getOrCreate()


// COMMAND ----------

val df = spark.read.option("header","true").option("inferSchema","true").csv("/FileStore/tables/amazon_stock_data.csv")

// COMMAND ----------

df.show()

// COMMAND ----------

df.head(5) // returns an Array


// COMMAND ----------

for(line <- df.head(10)){
  println(line)
}


// COMMAND ----------

// Get column names
df.columns


// COMMAND ----------

df.printSchema()


// COMMAND ----------

// Describe DataFrame Numerical Columns
df.describe()

// COMMAND ----------

// Select columns .transform().action()
df.select("Volume").show()


// COMMAND ----------

// Multiple Columns
df.select($"Date",$"Close").show(2)


// COMMAND ----------

// Creating New Columns
val df2 = df.withColumn("HighPlusLow",df("High")-df("Low"))


// COMMAND ----------

df2.columns

// COMMAND ----------

df2.printSchema()

// COMMAND ----------

df2.select(df2("HighPlusLow").as("HPL"),df2("Close")).show()


// COMMAND ----------

// This import is needed to use the $-notation
import spark.implicits._


// COMMAND ----------

// Grabbing all rows where a column meets a condition
df.filter($"Close" > 480).show()
// Can also use SQL notation
//df.filter("Close > 480").show()


// COMMAND ----------

//df.filter("Close > 480").show()


// COMMAND ----------

// Count how many results
df.filter($"Close">480).count()
// Can also use SQL notation
// df.filter("Close > 480").count()


// COMMAND ----------

df.filter($"High"===484.40).show()
// Can also use SQL notation
// df.filter("High = 484.40").count()


// COMMAND ----------

df.filter($"Close"<480 && $"High"<480).show()
// Can also use SQL notation
// df.filter("Close<480 AND High < 484.40").show()


// COMMAND ----------

// Collect results into a scala object (Array)
val High484 = df.filter($"High"===484.40).collect()


// COMMAND ----------

//https://spark.apache.org/docs/latest/api/scala/index.html#org.apache.spark.sql.functions$

// COMMAND ----------

// Examples of Operations
import org.apache.spark.sql.functions._

// COMMAND ----------

df.select(max("Open")).show()

// COMMAND ----------

df.select(min("Open")).show()

// COMMAND ----------

df.select(corr("High","Low")).show() // Pearson Correlation

// COMMAND ----------

df.select(month(df("Date"))).show()

// COMMAND ----------

df.select(year(df("Date"))).show()


// COMMAND ----------

val df2 = df.withColumn("Year",year(df("Date")))

// COMMAND ----------

df2.select("Year").distinct().show()


// COMMAND ----------

df2.select("Year").distinct().count()

// COMMAND ----------

val dfavgs = df2.groupBy("Year").mean()


// COMMAND ----------

dfavgs.show()

// COMMAND ----------

val dfavgsCount = df2.groupBy("Year").count()

// COMMAND ----------

dfavgsCount.show()

// COMMAND ----------

df2.groupBy("Year").min().show()

// COMMAND ----------

df2.groupBy("Year").max().show()

// COMMAND ----------

df.orderBy("Open").show()

// COMMAND ----------

df.orderBy($"Open".desc).show()

// COMMAND ----------

val df = spark.read.option("header","true").option("inferSchema","true").csv("/FileStore/tables/ContainsNull.csv")


// COMMAND ----------

df.show()


// COMMAND ----------

df.na.drop().show()


// COMMAND ----------

df.na.fill(100).show()

// COMMAND ----------

df.na.fill("Emp Name Missing").show()


// COMMAND ----------

df.na.fill("Specific",Array("Name")).show()


// COMMAND ----------

df.na.fill(400.5).show()


// COMMAND ----------

val df = spark.read.option("header","true").option("inferSchema","true").csv("/FileStore/tables/Sales.csv")

// COMMAND ----------

df.show()

// COMMAND ----------

df.groupBy("Company")


// COMMAND ----------

df.groupBy("Company").mean().show()


// COMMAND ----------

df.groupBy("Company").max().show()


// COMMAND ----------

df.groupBy("Company").sum().show()


// COMMAND ----------

df.orderBy("Sales").show()


// COMMAND ----------

df.orderBy($"Sales".desc).show()


// COMMAND ----------

df.select(countDistinct("Sales")).show()

// COMMAND ----------

df.select("Sales").distinct().show()

// COMMAND ----------

df.select("Sales").distinct().count()

// COMMAND ----------


