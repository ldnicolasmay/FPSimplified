package chapter27

object Main {

  val plusFunction: (Int, Int) => Int = (a, b) => a + b  // <- normal function definition
  val plus1Function: Int => Int = plusFunction(1, _)     // <- partially applied function

  // Partially applied method
  def plusMethod(a: Int)(b: Int): Int = a + b
  def plus11Method: Int => Int = plusMethod(11)(_)

  // Partially applied function
  val addFunction: Int => Int => Int = a => a + _
  val add111Function: Int => Int = addFunction(111)

  def main(args: Array[String]): Unit = {

    println(plusFunction(1, 2))
    println(plus1Function(2))

    println(plusMethod(11)(22))
    println(plus11Method(22))

    println(addFunction(111)(222))
    println(add111Function(222))

  }

}
