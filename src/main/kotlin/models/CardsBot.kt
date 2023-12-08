package models

import commands.AboutCommand
import commands.HelpCommand
import commands.PlayCommand
import listeners.OnJoin
import listeners.OnMessageReceived
import listeners.OnReadyListener
import net.dv8tion.jda.api.requests.GatewayIntent
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder
import net.dv8tion.jda.api.sharding.ShardManager
import net.dv8tion.jda.api.utils.ChunkingFilter
import net.dv8tion.jda.api.utils.MemberCachePolicy
import provider.CommandsProvider

class CardsBot(val prefix: String, val token: String) {
    private val commandsProvider = CommandsProvider()
    private var sm: ShardManager? = null

    init {
        commandsProvider.addCommand(PlayCommand())
        commandsProvider.addCommand(AboutCommand())
        commandsProvider.addCommand(HelpCommand())
        start {
            addEventListeners(OnMessageReceived(this@CardsBot))
            addEventListeners(OnReadyListener(this@CardsBot))
            addEventListeners(OnJoin())
            setToken(token)
        }
    }

    fun getCommandsProvider(): CommandsProvider {
        return commandsProvider
    }

    fun getJDA(): ShardManager {
        if (sm == null) throw NullPointerException("Shard Manager is Null!")
        return sm!!
    }

    private fun start(options: DefaultShardManagerBuilder.() -> Unit) {
        sm = DefaultShardManagerBuilder.create(
            GatewayIntent.MESSAGE_CONTENT,
            GatewayIntent.DIRECT_MESSAGES,
            GatewayIntent.GUILD_MESSAGES,

            GatewayIntent.DIRECT_MESSAGE_TYPING,
            GatewayIntent.GUILD_MESSAGE_TYPING,

            GatewayIntent.GUILD_PRESENCES,
            GatewayIntent.GUILD_MEMBERS)
            .apply(options)
            .setChunkingFilter(ChunkingFilter.ALL)
            .setMemberCachePolicy(MemberCachePolicy.ALL)
            .setAutoReconnect(true)
            .build(true)
    }
}