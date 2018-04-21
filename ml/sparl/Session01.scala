// Databricks notebook source
val sparkInfoRdd1 = sc.textFile("/mnt/learningai1/spark_info.txt")

// COMMAND ----------

sparkInfoRdd1.count()

// COMMAND ----------

sparkInfoRdd1.getNumPartitions

// COMMAND ----------

sparkInfoRdd1.glom().collect()

// COMMAND ----------

sparkInfoRdd1.first()

// COMMAND ----------

val sparkLines = sparkInfoRdd1.filter(line => line.contains("Spark"))

// COMMAND ----------

sparkLines.collect()

// COMMAND ----------

sparkLines.count()

// COMMAND ----------

val scalaLines = sparkInfoRdd1.filter(line => line.contains("Scala"))

// COMMAND ----------

scalaLines.collect()

// COMMAND ----------

scalaLines.count()

// COMMAND ----------

val mapWords = sparkInfoRdd1.map(line => line.split(" "))


// COMMAND ----------

mapWords.collect()

// COMMAND ----------

sparkInfoRdd1.collect()

// COMMAND ----------

val flatMapWords = sparkInfoRdd1.flatMap(line => line.split(" "))


// COMMAND ----------

flatMapWords.collect()

// COMMAND ----------

flatMapWords.count()

// COMMAND ----------

val wordWithOne = flatMapWords.map(word => (word,1))


// COMMAND ----------

wordWithOne.collect()

// COMMAND ----------

val wordCount = wordWithOne.reduceByKey((x,y) => x + y)


// COMMAND ----------

wordCount.collect()

// COMMAND ----------

// MAGIC %md Assignments
// MAGIC 
// MAGIC 1.Remove words with lenght less than 3.  
// MAGIC 
// MAGIC 2.Remove words with less than count 2.
// MAGIC 
// MAGIC 3.Sort the words based on frequency in reverse order

// COMMAND ----------

val rdd = sc.parallelize(List(1,2,3,4))

// COMMAND ----------

rdd.first()

// COMMAND ----------

// MAGIC %md map

// COMMAND ----------

val multRdd = rdd.map(x => x*x)

// COMMAND ----------

multRdd.collect()

// COMMAND ----------

val addRdd = rdd.map(x => x+x)

// COMMAND ----------

addRdd.collect()

// COMMAND ----------

val multRdd2 = addRdd.map(x => x*x)

// COMMAND ----------

val rddA = sc.parallelize(List("animal", "human", "bird", "rat"))

// COMMAND ----------

val rddB = rddA.map(x =>x.length)
//val rdd_b = rdd_a.map(_.length)

// COMMAND ----------

rddB.collect()

// COMMAND ----------

val rddC = rddA.zip(rddB)


// COMMAND ----------

rddC.collect()


// COMMAND ----------

// MAGIC %md filter

// COMMAND ----------



// COMMAND ----------

val rddA = sc.parallelize(1 to 10, 3)

// COMMAND ----------

val rddB = rddA.filter(x => x % 3 == 0)
//val rddB = rddA.filter(_ % 3 == 0)

// COMMAND ----------

4,8,12,16,20
4+4,8+8,12+12
8*8,16*16


// COMMAND ----------

rddB.collect()

// COMMAND ----------

// MAGIC %md distinct

// COMMAND ----------

val rddC = sc.parallelize(List("John", "Jack", "Mike", "Jack"))
val distRdd = rddC.distinct()
distRdd.collect()

// COMMAND ----------

// MAGIC %md intersection

// COMMAND ----------

val rddX = sc.parallelize(1 to 10)
val rddY = sc.parallelize(5 to 15)
val rddZ = rddX.intersection(rddY)
rddZ.collect

// COMMAND ----------

// MAGIC %md map & flatMap difference

// COMMAND ----------

val rddA = sc.parallelize(1 to 5)

// COMMAND ----------

rddA.collect()

// COMMAND ----------

1 to 1

// COMMAND ----------

1 to 2

// COMMAND ----------

val mapRdd = rddA.map(x =>1 to x)
//val mapRdd = rddA.map(1 to _)

// COMMAND ----------

mapRdd.collect()

// COMMAND ----------

val flatMap = rddA.flatMap(1 to _)

// COMMAND ----------

flatMap.collect()

// COMMAND ----------

sc.parallelize(List(5, 10, 20), 2).map(x => List(x, x, x)).collect

// COMMAND ----------

sc.parallelize(List(5, 10, 20), 2).flatMap(x => List(x, x, x)).collect

// COMMAND ----------

// MAGIC %md keys and values

// COMMAND ----------

val rddA = sc.parallelize(List("black", "blue", "white", "green", "grey"), 2)

// COMMAND ----------

val rddB = rddA.map(x => (x.length, x))

// COMMAND ----------

rddB.collect()

// COMMAND ----------

rddB.keys.collect

// COMMAND ----------

rddB.values.collect

// COMMAND ----------

// MAGIC %md groupByKey

// COMMAND ----------

val rddA = sc.parallelize(List("animal", "human", "bird", "rat", "cat", "rabit", "sheep", "lion", "peacock"), 3)
//val rdd_b = rdd_a.map(x =>x.length)
val rddB = rddA.map(_.length)
val rddC = rddB.zip(rddA)
rddC.collect

// COMMAND ----------

rddC.groupByKey.collect

// COMMAND ----------

// MAGIC %md reduceByKey

// COMMAND ----------

val rddA = sc.parallelize(List("black", "blue", "white", "green", "grey"), 2)
val rddB = rddA.map(x => (x.length, x))
rddB.collect()

// COMMAND ----------

//val reduce_by_key_result = rddB.reduceByKey((x,y) => x+y)
val reduceByKeyResult = rddB.reduceByKey(_ + _)

// COMMAND ----------

reduceByKeyResult.collect()

// COMMAND ----------

val rddA = sc.parallelize(List("black", "blue", "white", "orange"), 2)
val rddB = rddA.map(x => (x.length, x))


// COMMAND ----------

rddB.collect()

// COMMAND ----------

rddB.reduceByKey(_ + _).collect

// COMMAND ----------

sc.parallelize(List(2, 3, 4)).count()
sc.parallelize(List(2, 3, 4)).first()
sc.parallelize(List(2, 3, 4)).take(2)

// COMMAND ----------



// COMMAND ----------

// MAGIC %md Assignments
// MAGIC 
// MAGIC 1.what is the difference between map and flatmap.  
// MAGIC 
// MAGIC 2.what is the difference between reduceByKey and groupByKey.
// MAGIC 
// MAGIC 3.Give example for each transformation and action 
// MAGIC 
// MAGIC 4.what is the difference between coalesce and repartition 
// MAGIC 
// MAGIC 5.Mow many transformations and action spark has ?. Define each of them with example ;).
// MAGIC 
// MAGIC 6.Write python code for above transformations, actions along with above 5 Assignments
// MAGIC 
// MAGIC 7.Try to understand SQL 
// MAGIC https://www.w3schools.com/sql/

// COMMAND ----------


