package chapter78

import cats.effect.IO

object IOMonadHelloWorld {
  def main(args: Array[String]): Unit = {
    val helloEffect: IO[Unit] = IO { println("Hello, world") }
    // ...
    // ...
    // ...
    helloEffect.unsafeRunSync()
  }
}
