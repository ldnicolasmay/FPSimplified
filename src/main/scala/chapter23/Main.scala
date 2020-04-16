package chapter23

object Main {

  def main(args: Array[String]): Unit = {

    /** *************************************************************************************
     * Ch. 23
     */

    def sayHello(callback: () => Unit): Unit = {
      callback()
    }

    def helloNic(): Unit = println("Hello, Nic!")

    sayHello(helloNic)

    def holaLorenzo(): Unit = println("Hola, Lorenzo!")

    sayHello(holaLorenzo)

    // def intToUnit(f: Int => Unit): Unit = f(12)
    val intToUnit: (Int => Unit) => Unit = f => f(12)
    // def foo(i: Int): Unit = println(i)
    val foo: Int => Unit = i => println(i)
    intToUnit(foo)

    // def stringToInt(f: String => Int): Int = f("foobar")
    val stringToInt: (String => Int) => Int = f => f("foobar")
    // def bar(s: String): Int = s.length
    val bar: String => Int = s => s.length
    println(stringToInt(bar))

    def intIntToInt(f: (Int, Int) => Int): Int = {
      f(1, 2)
    }

    // def sumTwoInts(a: Int, b: Int): Int = a + b
    val sumTwoInts: (Int, Int) => Int = (a, b) => a + b
    println(intIntToInt(sumTwoInts))
    // def subtractTwoInts(a: Int, b: Int): Int = a - b
    val subtractTwoInts: (Int, Int) => Int = (a, b) => a - b
    println(intIntToInt(subtractTwoInts))
    // def multiplyTwoInts(a: Int, b: Int): Int = a * b
    val multiplyTwoInts: (Int, Int) => Int = (a, b) => a * b
    println(intIntToInt(multiplyTwoInts))

    def executeNTimes(f: () => Unit, n: Int): Unit = {
      for (i <- 1 to n) f()
    }

    def helloWorld(): Unit = println("Hello, world!")

    executeNTimes(helloWorld, 3)

    def executeAndPrint(f: (Int, Int) => Int, a: Int, b: Int): Unit = {
      val result = f(a, b)
      println(result)
    }

    executeAndPrint(sumTwoInts, 11, 13)
    executeAndPrint(multiplyTwoInts, 11, 13)

    def execTwoFunctions(
                          f: (Int, Int) => Int,
                          g: (Int, Int) => Int,
                          a: Int,
                          b: Int
                        ): (Int, Int) = {
      (f(a, b), g(a, b))
    }

    println(execTwoFunctions(sumTwoInts, multiplyTwoInts, 17, 19))

  }

}
