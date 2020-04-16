package chapter22

object Main {

  def main(args: Array[String]): Unit = {

    /** *************************************************************************************
     * Ch. 22
     */

    val ints = List(1, 2, 3, 4)

    def doubleMethod(i: Int): Int = i * 2

    val doubleFunction: Int => Int = i => i * 2
    println(ints.map(doubleMethod))
    println(ints.map(doubleFunction))

    def tripleMethod(i: Int): Int = i * 3

    val tripleFunction = tripleMethod _ // `_` converts method to partially applied function... "Eta expansion"
    println(ints.map(tripleMethod))
    println(ints.map(tripleFunction))

  }

}
