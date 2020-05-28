package chapter55_60

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.{Failure, Success}

object Main {
  def main(args: Array[String]): Unit = {

    val x = List("hi", "world").map(s => s.length)

    println(x)

    val y = List("foo", "bar").map(s => s.split("").toList)

    println(y)
    println(y.flatten)

    val z = List("foo", "bar").flatMap(s => s.split(""))

    println(z)

    def makeInt(s: String): Option[Int] = {
      try Some(s.trim.toInt)
      catch {
        case _: Exception => None
      }
    }

    // val a = makeInt("1")
    // val b = makeInt("2")
    // println(a + b) // doesn't work

    // flatMap on Options
    val c = for {
      a <- makeInt("1")
      b <- makeInt("2")
    } yield a + b

    println(c)

    val d = for {
      a <- makeInt("1")
      b <- makeInt("2")
      c <- makeInt("3")
      d <- makeInt("4")
    } yield a + b + c + d

    println(d)

    val e = for {
      a <- makeInt("1")
      b <- makeInt("cheezedoodles")
    } yield a + b

    println(e)

    println(makeInt("12").map(_ * 2))
    println(makeInt("foo").map(_ * 2))

    // flatMap on Lists
    val xs = List(1, 2, 3)
    val ys = List(100, 200, 300)
    val zs = for {
      x <- xs
      y <- ys
    } yield x * y

    println(zs)

    // flatMap on Futures
    val f1 = Future {
      Thread.sleep((0.5 * 1000).toInt); 1
    }
    val f2 = Future {
      Thread.sleep((1.0 * 1000).toInt); 2
    }
    val f3 = Future {
      Thread.sleep((1.5 * 1000).toInt); 3
    }

    val result = for {
      r1 <- f1
      r2 <- f2
      r3 <- f3
    } yield r1 + r2 + r3

    Thread.sleep(3 * 1000)
    result.onComplete {
      case Success(x) => println(s"result: $x")
      case Failure(e) => e.printStackTrace()
    }

  } // end main
} // Main
