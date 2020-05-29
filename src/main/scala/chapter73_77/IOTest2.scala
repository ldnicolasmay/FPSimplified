package chapter73_77

object IOTest2 {
  def main(args: Array[String]): Unit = {

    def forExpression: IO[Unit] = for {
      _         <- putStrLn("First name?")
      firstName <- getLine
      _         <- putStrLn("Last name?")
      lastName  <- getLine
      fNameUC   <- IO(firstName.toUpperCase)
      lNameUC   <- IO(lastName.toUpperCase)
      _         <- putStrLn(s"First: $fNameUC, Last: $lNameUC")
    } yield ()

    forExpression.run

  }
}
