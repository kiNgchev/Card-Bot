package provider

import models.Colors
import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.entities.MessageEmbed
import java.io.File

object CardProvider {
    @JvmStatic
    fun getCard(): MessageEmbed {
        val type = listOf<String>(
            "asks/LightAsks",
            "asks/HardAsks",
            "actions/LightActions",
            "actions/HardActions"
        ).random()
        val file: File = File("src/main/resources/cards/${type}.txt")
        val title: String
        val description: String
        val color: Int

        if (type === "asks/LightAsks") {
            title = "Лёгкий вопрос"
            color = Colors.GREEN.getColor()
            description = file.readLines().random()
        } else if (type === "asks/HardAsks") {
            title = "Сложный вопрос"
            color = Colors.DANGER.getColor()
            description = file.readLines().random()
        } else if (type === "actions/LightActions") {
            title = "Лёгкое действие"
            color = Colors.GREEN.getColor()
            description = file.readLines().random()
        } else if (type === "actions/HardActions") {
            title = "Сложное действие"
            color = Colors.DANGER.getColor()
            description = file.readLines().random()
        } else {
            throw IllegalArgumentException("List not contains this type: $type")
        }

        return EmbedBuilder()
            .setColor(color)
            .setTitle(title)
            .setDescription(description)
            .build()
    }
}