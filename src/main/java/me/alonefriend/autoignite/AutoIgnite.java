package me.alonefriend.autoignite;

import org.bukkit.plugin.java.JavaPlugin;

public final class AutoIgnite extends JavaPlugin {
    @Override
    public void onEnable() {
        loadConfig();

        this.getCommand("autoignite").setExecutor(new AutoIgniteCommand(this));
        this.getServer().getPluginManager().registerEvents(new TNTListener(this), this);
    }

    public void loadConfig() {
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();
    }
}
