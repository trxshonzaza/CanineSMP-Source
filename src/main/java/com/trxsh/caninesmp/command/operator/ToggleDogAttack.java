package com.trxsh.caninesmp.command.operator;

import com.trxsh.caninesmp.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ToggleDogAttack implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(command.getName().equalsIgnoreCase("toggledogattack")) {

            if(!(sender.isOp())) {

                sender.sendMessage(ChatColor.RED + "You are not operator!");
                return true;

            }

            if(Main.Instance.attackDogs) {

                Main.Instance.attackDogs = false;

                Bukkit.broadcastMessage(ChatColor.RED + "" + ChatColor.BOLD + "Attacking Dogs Is Now Disabled.");

            } else {

                Main.Instance.attackDogs = true;

                Bukkit.broadcastMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "Attacking Dogs Is Now Enabled.");

            }

            return true;

        }

        return false;

    }
}
