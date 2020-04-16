package chapter24

object Main {

  def main(args: Array[String]): Unit = {

    /** *************************************************************************************
     * Ch. 24
     */

    val ints: List[Int] = 1.to(10).toList

    val doubleFxn: Int => Int = i => i * 2
    val tripleFxn: Int => Int = i => i * 3

    val isEvenImp = (i: Int) => i % 2 == 0
    val isEvenExp: Int => Boolean = i => i % 2 == 0

    val isOddImp = (i: Int) => i % 2 == 1
    val isOddExp: Int => Boolean = i => i % 2 == 1


    def mapList[A, B](f: A => B, xs: List[A]): List[B] = {
      // for (x <- xs) yield f(x)
      xs match {
        case Nil => Nil
        case head :: tail => f(head) :: mapList(f, tail)
      }
    }

    println(mapList(doubleFxn, ints))

    def mapSeq[A, B](f: A => B, xs: Seq[A]): Seq[B] = {
      // for (x <- xs) yield f(x)
      xs match {
        case Nil => Nil
        case head +: tail => f(head) +: mapSeq(f, tail)
      }
    }

    println(mapSeq(tripleFxn, ints))

    def filterList[A](p: A => Boolean, xs: List[A]): List[A] = {
      // for (x <- xs; if p(x)) yield x
      xs match {
        case Nil => Nil
        case head :: tail =>
          if (p(head)) head :: filterList(p, tail)
          else filterList(p, tail)
      }
    }

    println(filterList(isEvenExp, ints))

    def filterSeq[A](p: A => Boolean, xs: Seq[A]): Seq[A] = {
      // for (x <- xs; if p(x)) yield x
      xs match {
        case Nil => Nil
        case head +: tail =>
          if (p(head)) head +: filterSeq(p, tail)
          else filterSeq(p, tail)
      }
    }

    println(filterSeq(isOddExp, ints))

  }

}
