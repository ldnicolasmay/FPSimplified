package chapter38

import scala.annotation.tailrec

object Main {

  //  @tailrec
  //  private def sumWithAcc(xs: List[Int], acc: Long): Long = {
  //    xs match {
  //      case Nil     => acc
  //      case x :: xs => sumWithAcc(xs, acc + x)
  //    }
  //  }
  //  def callSumWithAcc(xs: List[Int]): Long = sumWithAcc(xs, 0)
  @tailrec
  val sumWithAcc: (List[Int], Long) => Long = (xs, acc) => {
    xs match {
      case Nil => acc
      case x :: xs => sumWithAcc(xs, acc + x)
    }
  }
  val callSumWithAcc: List[Int] => Long = xs => sumWithAcc(xs, 0)

  @tailrec
  private def productWithAcc(xs: List[Int], acc: Long): Long = {
    xs match {
      case Nil => acc
      case x :: xs => productWithAcc(xs, acc * x)
    }
  }

  def callProductWithAcc(xs: List[Int]): Long = productWithAcc(xs, 1)

  @tailrec
  private def concatWithAcc(xs: List[String], acc: String): String = {
    xs match {
      case Nil => acc
      case x :: xs => concatWithAcc(xs, acc ++ x)
    }
  }

  def callConcatWithAcc(xs: List[String]): String = concatWithAcc(xs, acc = "")

  def main(args: Array[String]): Unit = {
    val intList = (1 to 5).toList
    println(callSumWithAcc(intList))
    println(callProductWithAcc(intList))

    val strList = List("a", "ab", "abc", "abcd", "abcde")
    println(callConcatWithAcc(strList))
  }

}
