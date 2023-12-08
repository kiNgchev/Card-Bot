import models.CardsBot

object Launcher {
    @JvmStatic
    fun main(array: Array<String>) {
        val client = CardsBot("put your prefix here", "put you token here")
    }
}