package me.revsky.morph.commands;

import me.revsky.morph.managers.MorphManager;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class DisguiseCommand implements CommandExecutor {

    private final MorphManager morphManager;

    public DisguiseCommand(MorphManager morphManager) {
        this.morphManager = morphManager;
    }

    @Override
    public boolean onCommand(CommandSender sender,
                             Command command,
                             String label,
                             String[] args) {

        if (!(sender instanceof Player player)) {
            sender.sendMessage("Only players can use this command.");
            return true;
        }

        if (args.length == 0) {
            player.sendMessage("§cUsage: /disguise <entity>");
            player.sendMessage("§cUsage: /disguise block <material>");
            return true;
        }

        if (args[0].equalsIgnoreCase("block")) {

            if (args.length < 2) {
                player.sendMessage("§cUsage: /disguise block <material>");
                return true;
            }

            try {
                Material material = Material.valueOf(args[1].toUpperCase());
                morphManager.disguiseAsBlock(player, material);
            } catch (IllegalArgumentException ex) {
                player.sendMessage("§cInvalid block material.");
            }

            return true;
        }

        try {
            EntityType type = EntityType.valueOf(args[0].toUpperCase());

            if (!type.isSpawnable()) {
                player.sendMessage("§cCannot disguise as that entity.");
                return true;
            }

            morphManager.disguiseAsEntity(player, type);

        } catch (IllegalArgumentException ex) {
            player.sendMessage("§cUnknown entity type.");
        }

        return true;
    }
}
