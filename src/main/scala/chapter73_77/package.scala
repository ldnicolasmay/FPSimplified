package object chapter73_77 {

  def getLine: IO[String] = IO(scala.io.StdIn.readLine())

  def putStr(s: String): IO[Unit] = IO(print(s))

  def putStrLn(s: String): IO[Unit] = IO(println(s))

}
