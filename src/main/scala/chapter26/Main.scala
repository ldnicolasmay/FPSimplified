package chapter26

object Main {

  /** *************************************************************************************
   * Ch. 26
   */

  // Call by-name method
  @scala.annotation.tailrec
  def whilst(testCondition: => Boolean)(codeBlock: => Unit): Unit = {
    if (testCondition) {
      codeBlock
      whilst(testCondition)(codeBlock)
    } else {
      ()
    }
  }

  // Call by-name method
  def ifBothTrue(cond1: => Boolean)
                (cond2: => Boolean)
                (codeBlock: => Unit): Unit = {
    if (cond1 && cond2) codeBlock else ()
  }

  // Method using implicit value
  def printIntIfTrue(a: Int)(implicit b: Boolean): Unit = if (b) println(a) else ()

  // Method using default values
  def sumTwoInts(a: Int = 1)(b: Int = 2): Int = a + b

  def main(args: Array[String]): Unit = {

    /**
     * Call by-name
     */
    var i = 0
    whilst(i < 5) {
      println(i)
      i = i + 1
    }

    val age = 19
    val numAccidents = 0
    ifBothTrue(age > 18)(numAccidents == 0) {
      println("Discount!")
    }

    /**
     * Implicit parameters / values
     */
    printIntIfTrue(1)(true)

    // printIntIfTrue(12) // throws error because an implicit Boolean variable isn't yet defined

    implicit val boo: Boolean = true
    printIntIfTrue(123) // works because implicit Boolean variable `boo` is defined

    /**
     * Default values
     */
    println(sumTwoInts()())
    println(sumTwoInts(10)())
    println(sumTwoInts()(10))

  }

}
