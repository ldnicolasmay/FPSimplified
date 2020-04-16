package chapter28_37

import scala.math.Numeric._

object Main {

  val sumIntsFunction: List[Int] => Int = { // anonymous fxn syntax
    case Nil => 0
    case x :: xs => x + sumIntsFunction(xs)
  }

  val nToList: Int => List[Int] = n => (2 to n).toList

  val filterNs: (Int, List[Int]) => List[Int] =
    (n, ns) => ns.filter(_ % n != 0)

  val sieve: List[Int] => List[Int] = {
    case Nil => Nil
    case n :: ns => n :: sieve(filterNs(n, ns))
  }

  def sumIntsMethod(xs: List[Int]): Int = xs match {
    case Nil => 0
    case x :: xs => x + sumIntsMethod(xs)
  }

  def sumNumericMethod[T](xs: Seq[T])(implicit n: Numeric[T]): T = {
    xs match {
      case Nil => n.zero
      case b +: bs => n.plus(b, sumNumericMethod(bs))
    }
  }

  val quicksort: List[Int] => List[Int] = {
    case Nil => Nil
    case x :: xs => quicksort(xs.filter(_ < x)) ++ List(x) ++ quicksort(xs.filter(_ >= x))
  }

  def main(args: Array[String]): Unit = {

    println(sumIntsFunction(List()))
    println(sumIntsFunction(List(1, 2, 3)))

    println(sumIntsMethod(List()))
    println(sumIntsMethod(List(1, 2, 3)))

    val n: Int = 100
    println(sieve(nToList(n)))

    println(quicksort(List(3, 1, 5, 2, 4)))

    println(sumNumericMethod(List(1.2, 3, 5.4)))
    println(sumNumericMethod(Array(1, 2, 3)))
    println(sumNumericMethod(Seq(1.1, 2.2, 3.3, 4)))

  }
}
