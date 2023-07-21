package com.trxsh.caninesmp.command;

import com.trxsh.caninesmp.utility.IdentityUtility;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;

public class TradeDogHealth implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {

            sender.sendMessage(ChatColor.RED + "Only A Valid Player Can Run This Command!");
            return true;

        }

        if(command.getName().equalsIgnoreCase("tradedoghealth")) {

            Player p = (Player) sender;

            if(IdentityUtility.dogOwnerExists(p)) {

                Wolf w = (Wolf) IdentityUtility.getDogEntityByPlayer(p);

                if (w == null) {

                    sender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "Your Dog Could Not Be Found (Is It In A Different Dimension?)");
                    return true;

                }

                double healthNeeded = 20 - Math.round(p.getHealth());

                if (healthNeeded <= 0) {

                    p.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "You are at full health!");
                    p.playSound(p.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1f, .5f);

                    return true;

                } else if (healthNeeded >= w.getHealth())
                    healthNeeded = w.getHealth();

                if (healthNeeded >= w.getHealth()) {

                    p.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "Your dog does not have sufficient health!");
                    p.playSound(p.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1f, .5f);

                    return true;

                } else
                    w.setHealth(w.getHealth() - healthNeeded);

                p.setHealth(Math.round(p.getHealth() + healthNeeded));

                p.sendMessage(ChatColor.GREEN + "You took " + ChatColor.RED + "❤" + (healthNeeded / 2) + ChatColor.GREEN + " from your dog.");
                p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1f, 1f);

                return true;

            } else {

                sender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "You Do Not Own A Dog Or It Is Not Registered! (re-login to fix.)");
                return true;

            }

        }

        return false;

    }

}
