package chapter78

import cats.effect.IO

object IOMonadAlmostHelloWorld {
  def main(args: Array[String]): Unit = {
    val hello = IO { println("Hello, world") }
  }
}
