package com.trxsh.caninesmp.command;

import com.trxsh.caninesmp.utility.IdentityUtility;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;

public class StopAttacking implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {

            sender.sendMessage(ChatColor.RED + "Only A Valid Player Can Run This Command!");
            return true;

        }

        if(command.getName().equalsIgnoreCase("stopattacking")) {

            Player p = (Player) sender;

            if(IdentityUtility.dogOwnerExists(p)) {

                Wolf w = (Wolf) IdentityUtility.getDogEntityByPlayer(p);

                if (w == null) {

                    sender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "Your Dog Could Not Be Found (Is It In A Different Dimension?)");
                    return true;

                }

                w.setAngry(false);
                w.setTarget(null);

                p.sendMessage(ChatColor.GREEN + "Your Dog Has Stopped Attacking The Target.");
                return true;

            } else {

                sender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "You Do Not Own A Dog Or It Is Not Registered! (re-login to fix.)");
                return true;

            }

        }

        return false;

    }
}
