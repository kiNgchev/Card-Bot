package listeners

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import models.CardsBot
import net.dv8tion.jda.api.OnlineStatus
import net.dv8tion.jda.api.entities.Activity
import net.dv8tion.jda.api.events.session.ReadyEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import utils.SimpleLogger

class OnReadyListener(private val client: CardsBot) : ListenerAdapter() {
    companion object {
        private val logger: SimpleLogger = SimpleLogger()
    }

    override fun onReady(event: ReadyEvent) {
        logger.log("Success", "${event.jda.selfUser.name} успешно запущен!")

        logger.log("Info", "Информация")
        logger.log("Info", "Количество шардов: ${client.getJDA().shardsTotal}")
        logger.log("Info", "Дискорд объекты:")
        logger.log("Info", "Количество серверов: ${client.getJDA().guilds.size}")
        logger.log("Info", "Количество пользователей: ${client.getJDA().users.size}")
        logger.log("Info", "Количество потоков: ${Thread.activeCount()}")

        changeActivity(event);
    }

    private fun changeActivity(event: ReadyEvent) = GlobalScope.launch {
        while (true) {
            event.jda.presence.setPresence(
                OnlineStatus.ONLINE,
                Activity.streaming("${client.prefix}help", "https://www.twitch.tv/rf"),
                true
            )

            delay(30000L)

            event.jda.presence.setPresence(
                OnlineStatus.ONLINE,
                Activity.streaming("Серверов: ${event.jda.guilds.size}", "https://www.twitch.tv/rf"),
                false
            )

            delay(30000L)
        }
    }
}