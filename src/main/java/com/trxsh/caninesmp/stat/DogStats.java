package com.trxsh.caninesmp.stat;

import com.trxsh.caninesmp.Main;
import com.trxsh.caninesmp.data.PlayerList;
import com.trxsh.caninesmp.player.DataPlayer;
import com.trxsh.caninesmp.utility.IdentityUtility;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;

public class DogStats {

    public static void showDogStats(Player player) {

        String dogTitle = "";

        try {

            if(IdentityUtility.dogOwnerExists(player)) {

                Wolf w = (Wolf) IdentityUtility.getDogEntityByPlayer(player);

                assert w != null;
                double health = w.getHealth() / 2;

                int airPockets = w.getRemainingAir();

                Location l = w.getLocation();

                String dogLocation = (int)l.getX() + ", " + (int)l.getY() + ", " + (int)l.getZ();

                dogTitle =
                        ChatColor.WHITE + "" + ChatColor.BOLD + "Health | " + ChatColor.RED + "❤" + health +
                                ChatColor.WHITE + "" + ChatColor.BOLD + " Air | " + ChatColor.BLUE + "\uD83C\uDF22" + airPockets +
                                ChatColor.WHITE + "" + ChatColor.BOLD + " Location | " + ChatColor.GREEN + dogLocation;

            }

            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(dogTitle));

        }catch(Exception ignored){}

    }

    public static void start() {

        Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.Instance, () -> {

            for(DataPlayer p : PlayerList.players.values()) {

                if(p.isOnline && p.showStats && p.getPlayer() != null && p.dogUUID != null && p.dogEntity != null) {

                    showDogStats(p.getPlayer());

                }

            }

        }, 40L, 40L);

    }

}
