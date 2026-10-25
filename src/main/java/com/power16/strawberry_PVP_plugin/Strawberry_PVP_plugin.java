package com.power16.strawberry_PVP_plugin;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public final class Strawberry_PVP_plugin extends JavaPlugin {

    private final Set<UUID> pvpEnabled = ConcurrentHashMap.newKeySet();

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents( new DamageListener(this), this);
        Objects.requireNonNull(getCommand("pvp"), "pvp command missing from plugin.yml")
                .setExecutor(new PVPCommand(this));
        getLogger().info("Strawberry PVP Toggler enabled [Coded by Power16]");
    }

    public void setPvpEnabled(Player player, boolean enabled) {
        if (enabled) {
            pvpEnabled.add(player.getUniqueId());

        }
        else {
            pvpEnabled.remove(player.getUniqueId());
        }

    }

    public boolean isPvpEnabled(Player player) {
        return pvpEnabled.contains(player.getUniqueId());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}


