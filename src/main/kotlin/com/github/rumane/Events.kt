package com.github.rumane

import com.github.rumane.BridgingSupportPlugin.Companion.instance
import org.bukkit.GameMode
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.ItemStack
import kotlin.math.cos
import kotlin.math.sin

class Events : Listener {
    @EventHandler
    fun onClick(event: PlayerInteractEvent) {
        val player = event.player
        val action = event.action
        val item = player.inventory.itemInMainHand
        val itemName = item.type.name.lowercase()
        val list = instance.config.getStringList("BridgingBlock")

        if (action == Action.RIGHT_CLICK_AIR && player.isSneaking && list.contains(itemName)) {
            setBlock(player, item)
            return
        }
        else return
    }

    private fun setBlock(player: Player, item: ItemStack) {
        val pLoc = player.location
        val world = player.world
        val x = pLoc.x
        val y = pLoc.y
        val z = pLoc.z
        val yaw = pLoc.yaw.toDouble()
        val name = item.type.name

        for (i in 1..16) {
            val block = Location(world, -sin(yaw.toRad()) * i / 2 + x, y - 1, cos(yaw.toRad()) * i / 2 + z).block
            val list = arrayListOf(Material.AIR, Material.WATER, Material.LAVA)
            if (!list.contains(block.type)) continue
            block.type = Material.getMaterial(name)!!
            Material.COBBLESTONE_SLAB
            if (player.gameMode != GameMode.CREATIVE)
                item.amount = item.amount - 1
            break
        }
        return
    }

    private fun Double.toRad(): Double {
        return this * Math.PI / 180.0
    }
}
