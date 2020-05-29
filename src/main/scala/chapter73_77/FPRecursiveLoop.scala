package chapter73_77

object FPRecursiveLoop {
  def main(args: Array[String]): Unit = {

    def loop: IO[Unit] = for {
      _     <- putStr("Type something: ")
      input <- getLine
      _     <- putStrLn(s"You said '$input'.")
      _     <- if (input.toLowerCase == "quit") IO(()) else loop
    } yield ()

    loop.run

  }
}
