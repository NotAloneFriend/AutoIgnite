package me.alonefriend.autoignite;

import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {
    @Override
    public void onEnable() {
        loadConfig();

        this.getCommand("autoignite").setExecutor(new Commands(this));
        this.getServer().getPluginManager().registerEvents(new Events(this), this);
    }

    public void loadConfig() {
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();
    }
}
