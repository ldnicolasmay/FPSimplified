package chapter25

object Main {

  // by-name parameter `blockOfCode` -- only run when called inside the method
  def timer[A](blockOfCode: => A): (A, Double) = {
    val startTime = System.nanoTime()
    val result = blockOfCode
    val stopTime = System.nanoTime()
    val delta = stopTime - startTime
    (result, delta/1000000d)
  }

  def readFile(filename: String): String = {
    val textSource = io.Source.fromFile(filename)
    val textString = textSource.getLines().mkString("\n")
    textSource.close()
    textString
  }

  def main(args: Array[String]): Unit = {

    /** *************************************************************************************
     * Ch. 25
     */

    val printHelloTimer = timer(println("Hello!"))
    println(printHelloTimer)

    val readEtcPasswd = timer(readFile("./src/main/scala/chapter25/Main.scala"))
    println(readEtcPasswd._1)
    println(readEtcPasswd._2)

  }

}
