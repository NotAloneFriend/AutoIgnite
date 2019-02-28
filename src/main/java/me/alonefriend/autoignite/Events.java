package me.alonefriend.autoignite;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.ExplosionPrimeEvent;

public class Events implements Listener {
    private Main plugin;

    public Events(Main main) {
        plugin = main;
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if (plugin.getConfig().getBoolean("enabled")) {
            Block block = event.getBlockPlaced();
            Material material = block.getType();

            if (material == Material.TNT) {
                block.setType(Material.AIR);

                Player player = event.getPlayer();
                TNTPrimed tnt = player.getWorld().spawn(block.getLocation(), TNTPrimed.class);

                tnt.setFuseTicks(plugin.getConfig().getInt("delay") * 20);
            }
        }
    }

    @EventHandler
    public void onEntityExplode(EntityExplodeEvent event) {
        if (plugin.getConfig().getBoolean("enabled") && event.getEntityType() == EntityType.PRIMED_TNT) {
            if (!plugin.getConfig().getBoolean("destroy-blocks")) {
                event.blockList().clear();
            }
        }
    }

    @EventHandler
    public void onExplosion(ExplosionPrimeEvent event) {
        if (plugin.getConfig().getBoolean("enabled") && event.getEntityType() == EntityType.PRIMED_TNT) {
            event.setFire(plugin.getConfig().getBoolean("fire"));
            event.setRadius(plugin.getConfig().getInt("radius"));
        }
    }

    @EventHandler
    public void EntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (plugin.getConfig().getBoolean("enabled") && event.getDamager().getType() == EntityType.PRIMED_TNT) {
            event.setDamage(plugin.getConfig().getInt("damage"));
        }
    }
}
