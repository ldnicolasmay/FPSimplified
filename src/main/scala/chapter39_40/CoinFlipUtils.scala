package chapter39_40

import scala.io.StdIn.readLine
import scala.util.Random

object CoinFlipUtils {

  def showPrompt(): Unit = print("(h)eads, (t)ails, or (q)uit: ")
  //  val showPrompt: Unit => Unit = Unit => print("(h)eads, (t)ails, or (q)uit: ")

  def getUserInput: String = readLine().trim.toUpperCase
  //  val getUserInput: Unit => String = Unit => readLine.trim.toUpperCase

  def tossCoin(random: Random): String = random.nextInt(2) match {
    case 0 => "H"
    case 1 => "T"
  }
  //  val tossCoin: Random => String = random => random.nextInt(2) match {
  //    case 0 => "H"
  //    case 1 => "T"
  //  }

  def printableFlipResult(coinToss: String): String = coinToss match {
    case "H" => "Flip was Heads."
    case "T" => "Flip was Tails."
  }

  def printGameState(coinFlipResult: String, gameState: GameState): Unit =
    println(s"$coinFlipResult #Flips: ${gameState.numFlips} #Correct: ${gameState.numCorrect}\n")

  def printGameOver(): Unit = println("\n=== GAME OVER ===")

  def printGameState(gameState: GameState): Unit =
    println(s"#Flips: ${gameState.numFlips} #Correct: ${gameState.numCorrect}")

}
