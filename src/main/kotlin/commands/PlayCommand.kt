package commands

import models.commands.AbstractCommand
import net.dv8tion.jda.api.entities.Message
import net.dv8tion.jda.api.entities.MessageEmbed
import net.dv8tion.jda.api.utils.messages.MessageCreateData
import provider.CardProvider

class PlayCommand : AbstractCommand("play") {
    override suspend fun execute(message: Message, args: List<String>): Boolean {
        val card: MessageEmbed
        try {
            card = CardProvider.getCard()
        } catch (error: IllegalArgumentException) {
            println(error.stackTrace)
            return false
        }

        message.channel
            .sendMessage(MessageCreateData.fromEmbeds(card))
            .queue()

        return true
    }
}