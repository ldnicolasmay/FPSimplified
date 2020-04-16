package chapter21

object Main {

  def main(args: Array[String]): Unit = {

    /** *************************************************************************************
     * Ch. 21
     */

    val doubleImp = (i: Int) => i * 2
    val doubleExp: Int => Int = i => i * 2
    println(doubleImp(2))
    println(doubleExp(3))

    val sumIntsImp = (a: Int, b: Int) => a + b
    val sumIntsExp: (Int, Int) => Int = (a, b) => a + b
    println(sumIntsImp(1, 2))
    println(sumIntsExp(2, 3))

    val isEvenImp = (i: Int) => i % 2 == 0
    val isEvenExp: Int => Boolean = i => i % 2 == 0
    println(isEvenImp(12))
    println(isEvenExp(123))

    val ints = List(1, 2, 3, 4)
    println(ints.filter(isEvenExp))
    println(ints.map(doubleExp))

    val double: Int => Int = i => i * 2
    val triple: Int => Int = i => i * 3
    println(ints.map(triple))

    val functions: Map[String, Int => Int] = Map("2x" -> double, "3x" -> triple)
    println(functions("2x")(3))

  }

}
