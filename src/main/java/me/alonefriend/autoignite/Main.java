package me.alonefriend.autoignite;

import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {
    @Override
    public void onEnable() {
        loadConfig();

        this.getCommand("autoignite").setExecutor(new Commands(this));
        this.getServer().getPluginManager().registerEvents(new Events(this), this);

        getLogger().info(getDescription().getFullName() + " has been enabled.");
    }

    @Override
    public void onDisable() {
        getLogger().info(getDescription().getFullName() + " has been disabled.");
    }

    public void loadConfig() {
        getConfig().options().copyDefaults(true);
        saveConfig();
    }
}
