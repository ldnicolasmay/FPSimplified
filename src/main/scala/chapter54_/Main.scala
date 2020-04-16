package chapter54_

object Main {
  def main(args: Array[String]): Unit = {

    trait Personhood {
      def name: String
      def age: Int
      override def toString = s"name: $name, age: $age"
    }

    abstract class Person extends Personhood

    /**
     * 1 - anonymous class
     */

    val mary: Person = new Person {
      val name = "mary"
      val age = 22
    }
    println(mary)


    /**
     * 2 - function that takes a by-name parameter
     */

    def timer[A](codeBlock: => A): (A, Double) = {
      val startTime = System.nanoTime()
      val result = codeBlock
      val stopTime = System.nanoTime()
      val delta = stopTime - startTime
      (result, delta/1000000d)
    }

    val (result, time) = timer {
      Thread.sleep(1000)
      42
    }
    println(time, result)


    /**
     * 3 - class that takes a function parameter
     */

    case class StringToInt(run: String => Int)

    val stringToInt = StringToInt { s: String =>
      s.length
    }
    println(stringToInt.run("supercalifragilisticexpialidocious"))

    def len(s: String): Int = s.length
    val stringToInt2 = StringToInt(len)
    println(stringToInt2.run("supercalifragilisticexpialidocious"))
    println(stringToInt2.run("""ORDER &#039;ACTOR&#039; ON INSOUND: <a href=\\"http://www.insound.com/search/searchmain.jsp?query=st.+vincent+actor\\" target=\\"_blank\\" rel=\\"nofollow\\" onmousedown='UntrustedLink.bootstrap($(this), \\"\\", event)'>http://www.insound.com/search/searchmain.jsp?query=st.+vincent+actor</a>"""))

    case class Transform2ParamsTo1Param[A,B](fun: (A, A) => B)

    val myTransformer1 = Transform2ParamsTo1Param { (s1: String, s2: String) =>
      s1.length + s2.length
    }
    println(myTransformer1.fun("Pants", "McBrain"))

    val myTransformer2 = Transform2ParamsTo1Param{ (a: Int, b: Int) =>
      a + b
    }
    println(myTransformer2.fun(11, 13))


    /**
     * 4 - function that takes a function input parameter
     */

    // implemented as a function
    def s2i[A](s:String)(f: String => A): A = f(s)

    val res1 = s2i("hello") { s: String =>
      s.length
    }
    println(s"res1: $res1")

    // implemented as a case class
    case class S2I[A](s: String)(f: String => A) {
      def fun: A = f(s)
    }

    val res2 = S2I("hello") { s: String =>
      s.length
    }
    println(s"res2.fun: ${res2.fun}")

  }
}
