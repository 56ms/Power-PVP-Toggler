package com.power16.strawberry_PVP_plugin;

import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.projectiles.ProjectileSource;

public class DamageListener implements Listener {
    private final Strawberry_PVP_plugin plugin;

    public DamageListener(Strawberry_PVP_plugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onDamage(EntityDamageByEntityEvent event) {
        if (!(event.getEntity() instanceof Player victim)) {
            return;
        }

        Player attacker = resolveAttacker(event.getDamager());
        if (attacker == null || attacker.equals(victim)) {
            return;
        }

        if (!plugin.isPvpEnabled(attacker) || !plugin.isPvpEnabled(victim)) {
            event.setCancelled(true);
        }
    }


    private Player resolveAttacker(Entity damager) {
        if (damager instanceof Player player) {
            return player;
        }
        if (damager instanceof Projectile projectile) {
            ProjectileSource shooter = projectile.getShooter();
            if (shooter instanceof Player player) {
                return player;
            }
        }
        return null;
    }


    @EventHandler
    public void OnPlayerJoin(PlayerJoinEvent event) {

        plugin.setPvpEnabled(event.getPlayer(), false);
        event.getPlayer().sendMessage(ChatColor.BLUE + "PVP is disabled! You can enabled it by running the command: " + ChatColor.YELLOW + "/pvp on" + ChatColor.BLUE + " or " + ChatColor.YELLOW + "/pvp off" + ChatColor.BLUE + "!");
    }

}


