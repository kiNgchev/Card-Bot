package models.commands

import net.dv8tion.jda.api.entities.Message

abstract class AbstractCommand(private val name: String) {
    fun getName(): String {
        return name
    }

    abstract suspend fun execute(message: Message, args: List<String>): Boolean
}