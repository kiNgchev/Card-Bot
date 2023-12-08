package commands

import models.Colors
import models.commands.AbstractCommand
import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.entities.Message
import net.dv8tion.jda.api.utils.messages.MessageCreateBuilder

class AboutCommand : AbstractCommand("about") {
    override suspend fun execute(message: Message, args: List<String>): Boolean {
        val embed = EmbedBuilder()
            .setColor(Colors.PURPLE.getColor())
            .setTitle("Обо мне!")
            .setDescription(
                """
            hEy BRO!!!
    
            Я бот созданный для того, чтобы Ты играл со своими друзьями в меня!
            Список команд можно получить используя команду c!help
            
            **Желаю хорошего время провождения ;)**
            """.trimIndent()
            )
            .setFooter("Сделано с любовью (Все права защищены от нападения инопланетян)")
            .build()

        val helpMessage = MessageCreateBuilder()
            .setEmbeds(embed)
            .build()

        message.channel
            .sendMessage(helpMessage)
            .queue()

        return true
    }
}