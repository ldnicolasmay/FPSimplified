package chapter73_77

object IOTest1 {
  def main(args: Array[String]): Unit = {

    for {
      _         <- putStrLn("First name?")
      firstName <- getLine
      _         <- putStrLn("Last name?")
      lastName  <- getLine
      _         <- putStrLn(s"Your full name is $firstName $lastName.")
    } yield ()

  }
}
