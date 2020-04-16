package chapter41_42

import monocle.Lens
import monocle.macros.GenLens
import monocle.Traversal
import monocle.function.Each.each

/**
 * Chapter 41
 *
 * @param name     Person name
 * @param relation Person relationship
 */
case class Person(name: String, relation: String)

/**
 * Chapter 41
 *
 * @param name               BaseballTeam name
 * @param lastWorldSeriesWin BaseballTeam last World Series win year
 */
case class BaseballTeam(name: String, lastWorldSeriesWin: Int)

/**
 * Chapter 42
 *
 * // @param creditCards Seq of CreditCards
 *
 * @param creditCards List of CreditCards
 */
// case class BillingInfo(creditCards: Seq[CreditCard])
case class BillingInfo(creditCards: List[CreditCard])

/**
 * Chapter 42
 *
 * @param firstName First name
 * @param mi        Middle initial
 * @param lastName  Last Name
 */
case class Name(firstName: String,
                mi: String,
                lastName: String)

/**
 * Chapter 42
 *
 * @param id          User ID number
 * @param name        User Name
 * @param billingInfo User BillingInfo
 * @param phone       User telephone number
 * @param email       User email address
 */
case class User(id: Int,
                name: Name,
                billingInfo: BillingInfo,
                phone: String,
                email: String)

/**
 * Chapter 42
 *
 * @param name   Name on CreditCard
 * @param number CreditCard number
 * @param month  Expiration month
 * @param year   Expiration year
 * @param cvv    Security code
 */
case class CreditCard(name: Name,
                      number: String,
                      month: Int,
                      year: Int,
                      cvv: String)

object Main {

  def main(args: Array[String]): Unit = {

    /**
     * Chapter 41
     */

    // no need for `new` keyword with case class objects
    val christina = Person("Christina", "niece")
    println(christina)
    println(christina.name)
    println(christina.relation)

    // christina.name = "Fred"  // Error: reassignment to val

    // `unapply` method allows for match-case expressions
    println(christina match {
      case Person(n, r) => (n, r)
    })

    // `copy` method for copy-and-update
    val cubs1908 = BaseballTeam("Chicago Cubs", 1908)
    val cubs2016 = cubs1908.copy(lastWorldSeriesWin = 2016)
    val wsox2005 = cubs2016.copy(name = "Chicago White Sox", lastWorldSeriesWin = 2005)
    println(cubs1908)
    println(cubs2016)
    println(wsox2005)

    // `equals` and `hashCode` methods
    val hannah = Person("Hannah", "niece")
    val christina_copy1 = christina.copy()
    val christina_copy2 = Person("Christina", "niece")
    println(christina.hashCode())
    println(hannah.hashCode())
    println(christina_copy1.hashCode())
    println(christina_copy2.hashCode())
    println(christina == hannah)
    println(christina == christina_copy1)
    println(christina == christina_copy2)

    // `toString` method
    println(christina.toString)


    /**
     * Chapter 42
     */

    val hannahName = Name("Hannah", "C", "Jones")
    val hannahUser1 = User(
      id = 1,
      name = hannahName,
      phone = "123-456-7890",
      email = "hannah@gmail.com",
      billingInfo = BillingInfo(
        // creditCards = Seq(
        creditCards = List(
          CreditCard(
            name = hannahName,
            number = "1111222233334444",
            month = 1,
            year = 2021,
            cvv = "123"
          ) // CreditCard
          // ) // Seq
        ) // List
      ) // BillingInfo
    ) // User
    println(hannahUser1)

    // updating User phone number
    val hannahUser2 = hannahUser1.copy(phone = "321-654-0987")
    println(hannahUser2)

    // updating the last name
    val hannahNewName = hannahUser2.name.copy(lastName = "Smith")
    println(hannahNewName)
    val hannahUser3 = hannahUser2.copy(name = hannahNewName)
    println(hannahUser3)

    // updating the credit card
    val oldCC: CreditCard = hannahUser3.billingInfo.creditCards(0)
    val newCC: CreditCard = oldCC.copy(name = hannahNewName)
    // val newCCSeq: Seq[CreditCard] = Seq(newCC)
    val newCCList: List[CreditCard] = List(newCC)
    // val hannahUser4: User = hannahUser3.copy(billingInfo = BillingInfo(newCCSeq))
    val hannahUser4: User = hannahUser3.copy(billingInfo = BillingInfo(newCCList))
    println(hannahUser4)

    // Monocle package
    println("=== Monocle ===")

    // User(
    //   BillingInfo(
    //     // Seq(
    //     List(
    //       CreditCard
    //     )
    //   )
    // )
    val billingInfo: Lens[User, BillingInfo] = GenLens[User](_.billingInfo)
    val creditCards: Lens[BillingInfo, List[CreditCard]] = GenLens[BillingInfo](_.creditCards)
    val cc_name: Lens[CreditCard, Name] = GenLens[CreditCard](_.name)
    val firstName: Lens[Name, String] = GenLens[Name](_.firstName)
    val mi: Lens[Name, String] = GenLens[Name](_.mi)
    val lastName: Lens[Name, String] = GenLens[Name](_.lastName)
    val user_name: Lens[User, Name] = GenLens[User](_.name)
    val month: Lens[CreditCard, Int] = GenLens[CreditCard](_.month)
    val cvv: Lens[CreditCard, String] = GenLens[CreditCard](_.cvv)

    println(hannahUser4)
    val hannahUser5 = user_name.composeLens(mi).modify(_.toLowerCase)(hannahUser4)
    println(hannahUser4)
    println(hannahUser5)

    val allCreditCards: Traversal[User, CreditCard] =
      billingInfo
        .composeLens(creditCards)
        .composeTraversal(each)
    val hannahUser6 = (allCreditCards composeLens month).modify(_ + 2)(hannahUser5)
    val hannahUser7 =
      allCreditCards
        .composeLens(cc_name)
        .composeLens(firstName).modify(_.toLowerCase)(hannahUser6)
    println(hannahUser7)

    val hannahUser8 =
      allCreditCards
      .composeLens(cvv)
      .set("777")(hannahUser7)
    println(hannahUser8)

  }

}
