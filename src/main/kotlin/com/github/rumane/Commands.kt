package com.github.rumane

import com.github.rumane.BridgingSupportPlugin.Companion.instance
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

class Commands: CommandExecutor {
    val cmd = "cr"

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (cmd.equals(command.name, true)) {
            instance.reloadConfig()
        }
        return true
    }
}