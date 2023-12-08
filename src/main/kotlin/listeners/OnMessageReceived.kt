package listeners

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import models.CardsBot
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter

class OnMessageReceived(private val client: CardsBot) : ListenerAdapter() {
    override fun onMessageReceived(event: MessageReceivedEvent) {
        handleCommand(event)
    }

    private fun handleCommand(event: MessageReceivedEvent) = GlobalScope.launch {
        if (event.author.isBot || event.isWebhookMessage) {
            return@launch
        }

        val prefix = client.prefix

        if (prefix.length == event.message.contentRaw.length) {
            return@launch
        }

        val args = event.message.contentRaw.substring(prefix.length).split(" +".toRegex()).toMutableList()
        val commandName = args.removeAt(0).lowercase()

        val cmd = client.getCommandsProvider().getCommand(commandName)

        cmd.execute(event.message, args.slice(1..args.size))
    }
}