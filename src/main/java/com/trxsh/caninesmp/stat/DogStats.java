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
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;

public class DogStats {

    public static void showDogStats(DataPlayer p) {

        String dogTitle = "";

        try {

            Player player = p.getPlayer();

            if(IdentityUtility.dogOwnerExists(player)) {

                Wolf w = (Wolf) IdentityUtility.getDogEntityByPlayer(player);

                assert w != null;
                double health = w.getHealth() / 2;

                int airPockets = w.getRemainingAir();

                Location l = w.getLocation();

                p.lastDogLocation = l;

                String dogLocation = (int)l.getX() + ", " + (int)l.getY() + ", " + (int)l.getZ();

                World.Environment et = w.getWorld().getEnvironment();

                p.lastDimension = w.getWorld().getName();

                String dimension = "";

                if(et == World.Environment.NORMAL)
                    dimension = ChatColor.BLUE + "" + ChatColor.BOLD + "Overworld";
                else if(et == World.Environment.NETHER)
                    dimension = ChatColor.RED + "" + ChatColor.BOLD + "Nether";
                else if(et == World.Environment.THE_END)
                    dimension = ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "The End";
                else
                    dimension = "Undefined Dimension";

                dogTitle =
                        ChatColor.WHITE + "" + ChatColor.BOLD + "Health | " + ChatColor.RED + "❤" + health +
                                ChatColor.WHITE + "" + ChatColor.BOLD + " Air | " + ChatColor.BLUE + "\uD83C\uDF22" + airPockets +
                                ChatColor.WHITE + "" + ChatColor.BOLD + " Location | " + ChatColor.GREEN + dogLocation +
                                ChatColor.WHITE + "" + ChatColor.BOLD + " Dimension | " + dimension;

            }

            if(p.showStats)
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(dogTitle));

        }catch(Exception ignored){}

    }

    public static void start() {

        Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.Instance, () -> {

            for(DataPlayer p : PlayerList.players.values()) {

                if(p.isOnline && p.getPlayer() != null && p.dogUUID != null && p.dogEntity != null) {

                    showDogStats(p);

                }

            }

        }, 40L, 40L);

    }

}
