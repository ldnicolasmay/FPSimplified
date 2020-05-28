package chapter61_72

object Main {
  def main(args: Array[String]): Unit = {
    def f1(a: Int): Int = a * 2

    def g1(a: Int): Int = a * 3

    val x = g1(f1(100))
    println(x)


    def f2(a: Int): (Int, String) = {
      val result: Int = a * 2
      (result, s"\nf2 result: $result")
    }

    def g2(a: Int): (Int, String) = {
      val result: Int = a * 3
      (result, s"\ng2 result: $result")
    }

    val (f2Int: Int, f2String: String) = f2(100)
    val (g2Int: Int, g2String: String) = g2(f2Int)
    val logMessage = f2String + g2String
    println(s"result: $g2Int, log: $logMessage")


    // def bind(fun: Int => (Int, String), tup: (Int, String)): (Int, String) = {
    //   val givenInt = tup._1
    //   val givenString = tup._2
    //
    //   val (intResult, stringResult) = fun(givenInt)
    //   val newString = givenString + stringResult
    //
    //   (intResult, newString)
    // }
    def bind(fun: Int => (Int, String), tup: (Int, String)): (Int, String) = {
      val (intResult, stringResult) = fun(tup._1)
      (intResult, tup._2 + stringResult)
    }

    def f3(a: Int): (Int, String) = {
      val result: Int = a * 2
      (result, s" f3 result: $result")
    }

    def g3(a: Int): (Int, String) = {
      val result: Int = a * 3
      (result, s" g3 result: $result")
    }

    def h3(a: Int): (Int, String) = {
      val result: Int = a * 4
      (result, s" h3 result: $result")
    }

    val f3Result = f3(100)
    val g3Result = bind(g3, f3Result)
    val h3Result = bind(h3, g3Result)
    println(h3Result)


    //    //
    //    class Wrapper[A](value: A) {
    //      def map[B](f: A => B): Wrapper[B] = {
    //        new Wrapper(f(value))
    //      }
    //
    //      def flatMap[B](f: A => Wrapper[B]): Wrapper[B] = {
    //        f(value)
    //      }
    //
    //      override def toString: String = value.toString
    //    }
    //
    //    val result: Wrapper[Int] = for {
    //      a <- new Wrapper(1)
    //      b <- new Wrapper(2)
    //      c <- new Wrapper(3)
    //    } yield a + b + c
    //    println(result)
    //
    //    val stringResult: Wrapper[String] = for {
    //      a <- new Wrapper("a")
    //      b <- new Wrapper("b")
    //      c <- new Wrapper("c")
    //    } yield a + b + c
    //    println(stringResult)

    class Wrapper[A] private(value: A) {
      def map[B](f: A => B): Wrapper[B] = {
        new Wrapper(f(value))
      }

      def flatMap[B](f: A => Wrapper[B]): Wrapper[B] = {
        f(value)
      }

      override def toString: String = value.toString
    }

    object Wrapper {
      def apply[A](value: A): Wrapper[A] = new Wrapper(value)
    }

    val result: Wrapper[Int] = for {
      a <- Wrapper(1)
      b <- Wrapper(2)
      c <- Wrapper(3)
    } yield a + b + c
    println(result)

    val stringResult: Wrapper[String] = for {
      a <- Wrapper("a")
      b <- Wrapper("b")
      c <- Wrapper("c")
    } yield a + b + c
    println(stringResult)


    //    //
    //    def f4(a: Int): Wrapper[(Int, String)] = {
    //      val result: Int = a * 2
    //      Wrapper((result, s" f4 result: $result"))
    //    }
    //    def g4(a: Int): Wrapper[(Int, String)] = {
    //      val result: Int = a * 3
    //      Wrapper((result, s" g4 result: $result"))
    //    }
    //    def h4(a: Int): Wrapper[(Int, String)] = {
    //      val result: Int = a * 4
    //      Wrapper((result, s" h4 result: $result"))
    //    }
    //
    //    val finalResult = for {
    //      f4Result <- f4(100)
    //      g4Result <- g4(f4Result)
    //      h4Result <- h4(g4Result)
    //    } yield h4Result
    //    println(finalResult)


    //    //
    //    case class Debuggable[A](value: A) {
    //      def map[B](f: A => B): Debuggable[B] = {
    //        Debuggable(f(value))
    //      }
    //      def flatMap[B](f: A => Debuggable[B]): Debuggable[B] = {
    //        f(value)
    //      }
    //    }

    //    case class Debuggable(value: Int, message: String) {
    //      def map(f: Int => Int): Debuggable = {
    //        val newValue: Int = f(value)
    //        Debuggable(newValue, message)
    //      }
    //      def flatMap(f: Int => Debuggable): Debuggable = {
    //        val newDebuggable: Debuggable = f(value)
    //        Debuggable(newDebuggable.value, message + newDebuggable.message)
    //      }
    //    }

    //    def f5(a: Int): Debuggable = {
    //      val result = a * 2
    //      Debuggable(result, s" f5 result: $result")
    //    }
    //    def g5(a: Int): Debuggable = {
    //      val result = a * 3
    //      Debuggable(result, s" g5 result: $result")
    //    }
    //    def h5(a: Int): Debuggable = {
    //      val result = a * 4
    //      Debuggable(result, s" h5 result: $result")
    //    }
    //
    //    val finalResult = for {
    //      f5Result <- f5(100)
    //      g5Result <- g5(f5Result)
    //      h5Result <- h5(g5Result)
    //    } yield h5Result
    //    println(finalResult)


    //    //
    //    case class Debuggable[A](value: A, message: String) {
    //      def map[B](f: A => B): Debuggable[B] = {
    //        val newValue: B = f(value)
    //        Debuggable(newValue, message)
    //      }
    //      def flatMap[B](f: A => Debuggable[B]): Debuggable[B] = {
    //        val newDebuggable: Debuggable[B] = f(value)
    //        Debuggable(newDebuggable.value, message + newDebuggable.message)
    //      }
    //    }
    //
    //    def f6(a: Int): Debuggable[Int] = {
    //      val result = a * 2
    //      Debuggable(result, s" f6 result: $result")
    //    }
    //    def g6(a: Int): Debuggable[Int] = {
    //      val result = a * 3
    //      Debuggable(result, s" g6 result: $result")
    //    }
    //    def h6(a: Int): Debuggable[Int] = {
    //      val result = a * 4
    //      Debuggable(result, s" h6 result: $result")
    //    }
    //
    //    val finalResult = for {
    //      f6Result <- f6(100)
    //      g6Result <- g6(f6Result)
    //      h6Result <- h6(g6Result)
    //    } yield h6Result
    //    println(finalResult)


    //
    case class Debuggable[A](value: A, logList: List[String]) {
      def map[B](f: A => B): Debuggable[B] = {
        val newValue: B = f(value)
        Debuggable(newValue, logList)
      }

      def flatMap[B](f: A => Debuggable[B]): Debuggable[B] = {
        val newDebuggable: Debuggable[B] = f(value)
        Debuggable(newDebuggable.value, logList ::: newDebuggable.logList)
      }
    }

    def f7(a: Int): Debuggable[Int] = {
      val result = a * 2
      Debuggable(result, List(s"f6 result: $result"))
    }

    def g7(a: Int): Debuggable[Int] = {
      val result = a * 3
      Debuggable(result, List(s"g6 result: $result"))
    }

    def h7(a: Int): Debuggable[Int] = {
      val result = a * 4
      Debuggable(result, List(s"h6 result: $result"))
    }

    val finalResult = for {
      f7Result <- f7(100)
      g7Result <- g7(f7Result)
      h7Result <- h7(g7Result)
    } yield h7Result
    println(finalResult)
  }
}
