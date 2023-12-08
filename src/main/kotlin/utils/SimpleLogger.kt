package utils

import java.text.SimpleDateFormat
import java.util.*

class SimpleLogger {
    companion object {
        val messageFormat: SimpleDateFormat = SimpleDateFormat("dd/MM/yyyy hh:mm:ss")
        val colors: Map<String, String> = mapOf(
            "blue" to "\u001B[34m",
            "red" to "\u001B[31m",
            "criticalRed" to "\u001B[37m\u001B[41m",
            "green" to "\u001B[32m",
            "yellow" to "\u001B[33m",
            "purple" to "\u001B[35m",
            "default" to "\u001B[0m"
        )
    }

    fun log(logType: String, message: String) {
        val color = when (logType) {
            "Debug" -> colors["blue"]
            "Error" -> colors["red"]
            "Critical" -> colors["criticalRed"]
            "Info" -> colors["green"]
            "Warn" -> colors["yellow"]
            "Success" -> colors["green"]
            else -> colors["default"]
        }
        println("${colors["purple"]}[${messageFormat.format(Date())}]" + color + "|${logType}|${colors["default"]}: $message")
    }
}