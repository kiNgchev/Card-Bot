package commands

import models.Colors
import models.commands.AbstractCommand
import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.entities.Message
import net.dv8tion.jda.api.utils.messages.MessageCreateBuilder

class HelpCommand : AbstractCommand("help") {
    override suspend fun execute(message: Message, args: List<String>): Boolean {
        val embed = EmbedBuilder()
            .setColor(Colors.PURPLE.getColor())
            .setTitle("Список моих команд")
            .setDescription(
                """
            **c.help** / Чтобы получить полный список команд которые тоже могут быть тебе полезны
            **c.play** / Чтобы начать игру
            **c.about** / Информация о том кто создал этого бота, а также переход на сайт где вы можете найти более подробную информацию
            """.trimIndent()
            )
            .setFooter("Сделано с любовью командой Angular (Все права защищены от нападения инопланетян)")
            .build()

        val aboutMessage = MessageCreateBuilder()
            .setEmbeds(embed)
            .build()

        message.channel
            .sendMessage(aboutMessage)
            .queue()

        return true
    }
}