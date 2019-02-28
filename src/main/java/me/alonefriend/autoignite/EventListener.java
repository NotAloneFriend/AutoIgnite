package me.alonefriend.autoignite;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.ExplosionPrimeEvent;

public class EventListener implements Listener {

    private Main plugin;

    public EventListener(Main main) {
        plugin = main;
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if (plugin.getConfig().getBoolean("enabled")) {
            Block block = event.getBlockPlaced();
            Material material = block.getType();

            if (material.equals(Material.TNT)) {
                event.getBlock().setType(Material.AIR);

                Player player = event.getPlayer();
                TNTPrimed tnt = player.getWorld().spawn(block.getLocation(), TNTPrimed.class);

                tnt.setFuseTicks(plugin.getConfig().getInt("delay") * 20);
            }
        }
    }

    @EventHandler
    public void onEntityExplode(EntityExplodeEvent event) {
        if (plugin.getConfig().getBoolean("enabled") && event.getEntityType().equals(EntityType.PRIMED_TNT)) {
            if (!plugin.getConfig().getBoolean("destroy-blocks")) {
                event.blockList().clear();
            }
        }
    }

    @EventHandler
    public void onExplosion(ExplosionPrimeEvent event) {
        if (plugin.getConfig().getBoolean("enabled") && event.getEntityType().equals(EntityType.PRIMED_TNT)) {
            event.setFire(plugin.getConfig().getBoolean("fire"));
            event.setRadius(plugin.getConfig().getInt("radius"));
        }
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        System.out.println(event.getEntityType());
        if (plugin.getConfig().getBoolean("enabled") && event.getCause() == EntityDamageEvent.DamageCause.ENTITY_EXPLOSION) {
            System.out.println("Inside");
            event.setDamage(plugin.getConfig().getInt("damage"));
        }
    }
}