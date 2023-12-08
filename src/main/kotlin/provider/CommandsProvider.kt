package provider

import models.commands.AbstractCommand

class CommandsProvider {
    private val commands: HashMap<String, AbstractCommand> = HashMap()

    fun addCommand(command: AbstractCommand) {
        commands[command.getName()] = command
    }

    fun getCommand(name: String): AbstractCommand {
        return commands[name] ?: throw NullPointerException("Command with name $name does not exists")
    }
}