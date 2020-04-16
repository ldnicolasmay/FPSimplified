package chapter43

case class Person(firstName: String, lastName: String)

object Main {
  def main(args: Array[String]): Unit = {

    val listDouble: List[Double] = List(1.0, 2.0, 3.0)
    val listIntFiltered: List[Int] = for {
      l <- listDouble
      i = l.toInt
      if i % 2 != 0
    } yield(i)
    println(listDouble)
    println(listIntFiltered)

    val people = List(
      Person("barney", "rubble"),
      Person("fred", "flinstone")
    )

    val namesStartingWithB: List[String] = for {
      p <- people
      fname = p.firstName
      if fname.toLowerCase.startsWith("b")
    } yield fname.capitalize
    namesStartingWithB.foreach(println)

  }
}
