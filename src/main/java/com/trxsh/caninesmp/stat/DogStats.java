package com.trxsh.caninesmp.stat;

import com.trxsh.caninesmp.Main;
import com.trxsh.caninesmp.data.PlayerList;
import com.trxsh.caninesmp.player.DataPlayer;
import com.trxsh.caninesmp.utility.IdentityUtility;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;

public class DogStats {

    public static void showDogStats(Player player) {

        String dogTitle = "";

        if(IdentityUtility.dogOwnerExists(player)) {

            Wolf w = (Wolf) IdentityUtility.getDogEntityByPlayer(player);

            assert w != null;
            double health = w.getHealth() / 2;

            double airPockets = w.getRemainingAir();

            Location l = w.getLocation();

            String dogLocation = (int)l.getX() + ", " + (int)l.getY() + ", " + (int)l.getZ();

            dogTitle =
                    ChatColor.WHITE + "" + ChatColor.BOLD + "Health | " + ChatColor.RED + "❤" + health + "\n" +
                            ChatColor.WHITE + "" + ChatColor.BOLD + "Air | " + ChatColor.BLUE + "\uD83C\uDF22" + airPockets + "\n" +
                            ChatColor.WHITE + "" + ChatColor.BOLD + "Location | " + ChatColor.GREEN + dogLocation + "\n";

        }

        player.sendTitle(null, dogTitle, 0, 70, 70);

    }

    public static void start() {

        Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.Instance, new Runnable() {

            @Override
            public void run() {

                for(DataPlayer p : PlayerList.players.values()) {

                    if(p.isOnline && p.showStats && p.getPlayer() != null) {

                        showDogStats(p.getPlayer());

                    }

                }

            }
        }, 40L, 40L);

    }

}
