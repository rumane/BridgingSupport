package com.github.rumane

import org.bukkit.plugin.Plugin
import org.bukkit.plugin.java.JavaPlugin

class BridgingSupportPlugin: JavaPlugin() {
    companion object {
        lateinit var instance: Plugin
    }
    override fun onEnable() {
        instance = this
        loadConfig()
        logger.info("Hello world!")
        setupEvents()
        setupCommands()
    }

    override fun onDisable() {
        this.reloadConfig()
        saveConfig()
    }

    private fun setupEvents() {
        server.pluginManager.registerEvents(Events(), this)
    }

    private fun setupCommands() {
        val c = Commands()
        server.getPluginCommand(c.cmd)!!.setExecutor(c)
    }

    private fun loadConfig() {
        this.config.addDefault("BridgingBlock", ArrayList<String>())
        this.config.options().copyDefaults(true)
        saveDefaultConfig()
    }
}