package com.trxsh.caninesmp.command.operator;

import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Objects;

import static com.trxsh.caninesmp.data.BanList.banned;

public class UnbanPlayer implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(command.getName().equalsIgnoreCase("unbandataplayer")) {

            if(!(sender.isOp())) {

                sender.sendMessage(ChatColor.RED + "You are not operator!");
                return true;

            }

            if(args[0] != null) {

                if(Bukkit.getBanList(BanList.Type.NAME).isBanned(Objects.requireNonNull(args[0]))) {

                    Bukkit.getBanList(BanList.Type.NAME).pardon(Objects.requireNonNull(args[0]));

                    banned.removeIf(id -> Objects.requireNonNull(Bukkit.getOfflinePlayer(id).getName()).equalsIgnoreCase(Objects.requireNonNull(args[0])));

                    sender.sendMessage(ChatColor.GREEN + "Successfully Unbanned!");

                    return true;

                } else {

                    sender.sendMessage(ChatColor.RED + "This player is not banned");
                    return true;

                }

            }

        }

        return false;

    }

}
