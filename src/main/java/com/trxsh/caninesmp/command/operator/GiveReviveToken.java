package com.trxsh.caninesmp.command.operator;

import com.trxsh.caninesmp.utility.ItemUtility;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GiveReviveToken implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(command.getName().equalsIgnoreCase("giverevivetoken")) {

            if(!(sender.isOp())) {

                sender.sendMessage(ChatColor.RED + "You are not operator!");
                return true;

            }

            if(!(sender instanceof Player)) {

                sender.sendMessage(ChatColor.RED + "You are not a valid player!");
                return true;

            }

            ((Player)sender).getInventory().addItem(ItemUtility.getReviveItem());
            sender.sendMessage(ChatColor.GREEN + "Received revive token.");
            return true;

        }

        return false;

    }

}
