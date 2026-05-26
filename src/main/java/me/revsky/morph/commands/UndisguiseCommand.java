 mepackage.revsky.morph.commands;

import me.revsky.morph.managers.MorphManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class UndisguiseCommand implements CommandExecutor {

    private final MorphManager morphManager;

    public UndisguiseCommand(MorphManager morphManager) {
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

        morphManager.undisguise(player);

        return true;
    }
}