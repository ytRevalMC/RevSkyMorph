package me.revsky.morph.managers;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MorphManager {

    private final Map<UUID, String> disguised = new HashMap<>();

    public void disguiseAsEntity(Player player, EntityType type) {

        disguised.put(player.getUniqueId(), type.name());

        player.sendMessage("§aYou are now disguised as §e" + type.name());

        player.addPotionEffect(new PotionEffect(
                PotionEffectType.INVISIBILITY,
                Integer.MAX_VALUE,
                1,
                false,
                false
        ));

        switch (type) {
            case CREEPER -> {
                player.setWalkSpeed(0.25f);
            }

            case SNAIL -> {
                player.setWalkSpeed(0.05f);
            }

            default -> {
                player.setWalkSpeed(0.2f);
            }
        }
    }

    public void disguiseAsBlock(Player player, Material material) {

        disguised.put(player.getUniqueId(), material.name());

        player.sendMessage("§aYou are now disguised as block §e" + material.name());

        player.addPotionEffect(new PotionEffect(
                PotionEffectType.INVISIBILITY,
                Integer.MAX_VALUE,
                1,
                false,
                false
        ));
    }

    public void undisguise(Player player) {

        disguised.remove(player.getUniqueId());

        player.removePotionEffect(PotionEffectType.INVISIBILITY);

        player.setWalkSpeed(0.2f);

        player.sendMessage("§cDisguise removed.");
    }

    public boolean isDisguised(Player player) {
        return disguised.containsKey(player.getUniqueId());
    }
}
