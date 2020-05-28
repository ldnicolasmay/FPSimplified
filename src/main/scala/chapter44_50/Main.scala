package chapter44_50

import scala.collection.mutable.ArrayBuffer

case class Sequence[A](initialElems: A*) {
  private val elems = scala.collection.mutable.ArrayBuffer[A]()
  elems ++= initialElems
  /*
  ++= works like this:
  for {
    e <- initialElems
  } elems += e
   */

  def foreach(block: A => Unit): Unit = {
    elems.foreach(block)
  }

  def map[B](f: A => B): Sequence[B] = {
    val abMap = elems.map(f)
    Sequence(abMap: _*)
  }

  def withFilter(p: A => Boolean): Sequence[A] = {
    val tmpArrayBuffer = elems.filter(p)
    Sequence(tmpArrayBuffer: _*)
  }

  def flatMap[B](f: A => Sequence[B]): Sequence[B] = {
    val mapRes: Sequence[Sequence[B]] = map(f)
    flattenLike(mapRes)
  }

  private def flattenLike[B](seqOfSeq: Sequence[Sequence[B]]): Sequence[B] = {
    var xs = ArrayBuffer[B]()
    for (listB: Sequence[B] <- seqOfSeq) {
      for (e <- listB) {
        xs += e
      }
    }
    Sequence(xs: _*)
  }
}

object Main {
  def main(args: Array[String]): Unit = {

    val ints: Sequence[Int] = Sequence(1, 2, 3, 4, 5)

    ints.foreach(println)
    println()

    val res = for {
      i <- ints
      if i > 2
    } yield i * 2

    res.foreach(println)
    println()

    case class Person(name: String)
    val myFriends = Sequence(Person("Adam"), Person("David"), Person("Frank"))
    val adamsFriends = Sequence(Person("Nick"), Person("David"), Person("Frank"))

    val mutualFriends = for {
      myFriend <- myFriends
      adamsFriend <- adamsFriends
      if myFriend == adamsFriend
    } yield myFriend

    mutualFriends.foreach(println)
    println()

    val friendTuples = for {
      myFriend <- myFriends
      adamsFriend <- adamsFriends
    } yield (myFriend, adamsFriend)

    friendTuples.foreach(println)
    println()


    def letterToNum(c: Char): Int = c.toLower.toInt - 'a'.toInt + 1
    def wordToNums(s: String): IndexedSeq[Int] = s.map(letterToNum)

    val words: Seq[String] = Seq("Apple", "Banana", "Orange")

    println(words.map(wordToNums))
    println(words.flatMap(wordToNums))

  }
}
