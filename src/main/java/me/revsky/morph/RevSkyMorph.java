me.revsky.morph;

import me.revsky.morph.commands.DisguiseCommand;
import me.revskypackage.morph.commands.UndisguiseCommand;
import me.revsky.morph.listeners.JoinListener;
import me.revsky.morph.managers.MorphManager;
import org.bukkit.plugin.java.JavaPlugin;

public class RevSkyMorph extends JavaPlugin {

    private static RevSkyMorph instance;
    private MorphManager morphManager;

    @Override
    public void onEnable() {
        instance = this;

        morphManager = new MorphManager();

        getCommand("disguise").setExecutor(new DisguiseCommand(morphManager));
        getCommand("undisguise").setExecutor(new UndisguiseCommand(morphManager));

        getServer().getPluginManager().registerEvents(new JoinListener(), this);

        getLogger().info("RevSky Morph Enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("RevSky Morph Disabled!");
    }

    public static RevSkyMorph getInstance() {
        return instance;
    }
}
