package com.power16.strawberry_PVP_plugin;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Locale;

public class PVPCommand implements CommandExecutor, TabCompleter {

    private final Strawberry_PVP_plugin plugin;

    public PVPCommand(Strawberry_PVP_plugin plugin) {
        this.plugin = plugin;
    }


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {

        if (!(sender instanceof Player p)) {
            sender.sendMessage("You must be a player to use this command.");
            return true;
        }

        if (args.length != 1) {
            return false;
        }

        switch (args[0].toLowerCase(Locale.ROOT)) {
                case "on" -> {
                    plugin.setPvpEnabled(p, true);
                    p.sendMessage(ChatColor.GREEN + "PVP has been enabled.");
                }
                case "off" -> {
                    plugin.setPvpEnabled(p, false);
                    p.sendMessage(ChatColor.RED + "PVP has been disabled.");
                }
                default -> {
                return false;
                }
        }



        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        if (args.length == 1) {
            return List.of("on", "off");
        }
        return List.of();
    }
}
