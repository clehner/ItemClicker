package com.celehner.itemclicker;

import java.util.Map;
import java.util.HashMap;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;

/**
 * Item clicker plugin for Bukkit
 *
 * @author clehner
 */
public class ItemClicker extends JavaPlugin {
    private final ItemClickerListener listener =
        new ItemClickerListener(this);
    Map<Material,String> itemsToCommands = new HashMap<Material,String>();

    @Override
    public void onDisable() {
        getLogger().info("Goodbye world!");
    }

    @Override
    public void onEnable() {
        // Register our events
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(listener, this);

        // Register our commands
        //getCommand("debug").setExecutor(new SampleDebugCommand(this));

        PluginDescriptionFile desc = this.getDescription();
        getLogger().info(desc.getName() + " version " + desc.getVersion() +
                " is enabled!");

        saveDefaultConfig();
        ConfigurationSection itemsConf =
            getConfig().getConfigurationSection("items");
        if (itemsConf == null) {
            getLogger().warning("Unable to find items in configuration");
        } else {
            getLogger().info("Getting items in configuration");
            for (Map.Entry<String,Object> entry :
                    itemsConf.getValues(false).entrySet()) {
                String itemName = entry.getKey();
                String cmd = (String)entry.getValue();
                Material mat = Material.matchMaterial(itemName);
                itemsToCommands.put(mat, cmd);
            }
        }
    }

    public String getCommandForItemClick(Material material) {
        return itemsToCommands.get(material);
    }
}
