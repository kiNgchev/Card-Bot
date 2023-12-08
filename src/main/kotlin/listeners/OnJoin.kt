package listeners

import models.Colors
import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.entities.MessageEmbed
import net.dv8tion.jda.api.events.guild.GuildJoinEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter

class OnJoin : ListenerAdapter() {
    override fun onGuildJoin(event: GuildJoinEvent) {
        val welcomeChannel = event.guild.textChannels.firstOrNull { it.canTalk() }
            ?: return

        val embed = EmbedBuilder()
            .setColor(Colors.PURPLE.getColor())
            .setTitle("Привет ${event.guild.name}")
            .setDescription(
            """
            hEy BRO!!!
    
            Я бот созданный лишь для того, чтобы ты играл со своими друзьями и подружками в меня!
            Ниже будут команды, скорее всего, нужные тебе:
    
            **c.help** / Получение полного списка команд, которые могут быть тебе полезными
            **c.play** / Старт игры
            **c.about** / Получение информации об этом боте
            
            **Желаю хорошего время провождения ;)**
            """.trimIndent()
            )
            .addField(MessageEmbed.Field("Разработчики", "kiNgchev\nNEPROGRAMMIST", false))
            .build()

        welcomeChannel.sendMessageEmbeds(embed).queue()
    }
}