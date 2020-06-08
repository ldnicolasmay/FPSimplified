package chapter78

import cats.effect.IO

object Program extends App {
  val program: IO[Unit] = for {
    _      <- IO { println("Welcome to Scala! What's your name?") }
    name   <- IO { scala.io.StdIn.readLine }
    nameUC <- IO { name.toUpperCase }
    _      <- IO { println(s"Well hello, $nameUC") }
  } yield ()

  // ...
  // ...
  // ...

  program.unsafeRunSync()
}
