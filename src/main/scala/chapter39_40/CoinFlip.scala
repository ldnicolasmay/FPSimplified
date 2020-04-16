package chapter39_40

import scala.util.Random
import scala.annotation.tailrec
import chapter39_40.CoinFlipUtils._

case class GameState(numFlips: Int, numCorrect: Int)

object CoinFlip extends App {

  val s = GameState(0, 0)
  val r = Random
  mainLoop(s, r)

  @tailrec
  def mainLoop(gameState: GameState, random: Random): Unit = {

    showPrompt()
    val userInput: String = getUserInput

    userInput match {
      case "H" | "T" => {
        val coinTossResult = tossCoin(random)
        val newNumFlips: Int = gameState.numFlips + 1
        if (userInput == coinTossResult) {
          val newNumCorrect: Int = gameState.numCorrect + 1
          val newGameState: GameState = gameState.copy(numFlips = newNumFlips, numCorrect = newNumCorrect)
          printGameState(printableFlipResult(coinTossResult), newGameState)
          mainLoop(newGameState, random)
        } else {
          val newGameState: GameState = gameState.copy(numFlips = newNumFlips)
          printGameState(printableFlipResult(coinTossResult), newGameState)
          mainLoop(newGameState, random)
        }
      }
      case _ => {
        printGameOver()
        printGameState(gameState)
      }
    }
  }

}
