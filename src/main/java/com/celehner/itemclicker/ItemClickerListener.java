package com.celehner.itemclicker;

import org.bukkit.entity.Player;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.Material;

/**
 * Handle events for Player related events
 * @author clehner
 */
public class ItemClickerListener implements Listener {
    private final ItemClicker plugin;

    public ItemClickerListener(ItemClicker instance) {
        plugin = instance;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Action action = event.getAction();
        if (action == Action.LEFT_CLICK_AIR ||
                action == Action.LEFT_CLICK_BLOCK) {
            Material material =
                player.getInventory().getItemInHand().getType();
            String cmd = plugin.getCommandForItemClick(material);
			if (cmd != null) {
				player.performCommand(cmd);
			}
        }
    }
}
