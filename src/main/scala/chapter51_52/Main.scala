package chapter51_52

import org.scalactic.{Bad, ErrorMessage, Good, Or}

import scala.util.{Failure, Success, Try}

// object Main extends App {
object Main {
  def main(args: Array[String]): Unit = {

    def makeIntOption(s: String): Option[Int] = {
      try
        Some(s.trim.toInt)
      catch {
        case e: Exception => None
      }
    }

    def printableResultOption(result: Option[Int]): String = result match {
      case Some(i) => s"Result: $i"
      case None => "String(s) couldn't be parsed to integer"
    }

    val happyResultOption = for {
      x <- makeIntOption("1")
      y <- makeIntOption("2")
      z <- makeIntOption("3")
    } yield x + y + z
    println(printableResultOption(happyResultOption))

    val unhappyResultOption = for {
      x <- makeIntOption("hello")
      y <- makeIntOption("2")
    } yield x + y
    println(printableResultOption(unhappyResultOption))

    def makeIntTry(s: String): Try[Int] = {
      Try(s.trim.toInt)
    }

    println(makeIntTry("1"))
    println(makeIntTry("foo"))

    def printableResultTry(result: Try[Int]): String = result match {
      case Success(i) => s"Result: $i"
      case Failure(e) => s"$e"
    }

    val happyResultTry = for {
      x <- makeIntTry("1")
      y <- makeIntTry("2")
    } yield x + y
    println(printableResultTry(happyResultTry))

    val unhappyResultTry = for {
      x <- makeIntTry("3")
      y <- makeIntTry("foo")
    } yield x + y
    println(printableResultTry(unhappyResultTry))

    def makeIntEither(s: String): Either[String, Int] = {
      try Right(s.trim.toInt)
      catch {
        case e: Exception => Left(s"$e")
      }
    }

    def printableResultEither(result: Either[String, Int]): String = result match {
      case Left(e) => s"$e"
      case Right(i) => s"Result: $i"
    }

    val happyResultEither = for {
      x <- makeIntEither("1")
      y <- makeIntEither("2")
    } yield x + y
    println(printableResultEither(happyResultEither))

    val unhappyResultEither = for {
      x <- makeIntEither("3")
      y <- makeIntEither("foo")
    } yield x + y
    println(printableResultEither(unhappyResultEither))

    def makeIntOr(s: String): Int Or ErrorMessage = {
      try Good(s.trim.toInt)
      catch {
        case e: Exception => Bad(s"$e")
      }
    }

    def printableResultOr(result: Int Or ErrorMessage): String = result match {
      case Good(i) => s"Result: $i"
      case Bad(e) => s"$e"
    }

    val happyResultOr = for {
      x <- makeIntOr("1")
      y <- makeIntOr("2")
    } yield x + y
    println(printableResultOr(happyResultOr))

    val unhappyResultOr = for {
      x <- makeIntOr("3")
      y <- makeIntOr("foo")
    } yield x + y
    println(printableResultOr(unhappyResultOr))

  }
}
