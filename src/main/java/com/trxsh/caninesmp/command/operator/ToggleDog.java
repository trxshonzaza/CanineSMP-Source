package com.trxsh.caninesmp.command.operator;

import com.trxsh.caninesmp.data.DogList;
import com.trxsh.caninesmp.data.PlayerList;
import com.trxsh.caninesmp.player.DataPlayer;
import com.trxsh.caninesmp.utility.IdentityUtility;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;

public class ToggleDog implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {

            sender.sendMessage(ChatColor.RED + "Only A Valid Player Can Run This Command!");
            return true;

        }

        if(command.getName().equalsIgnoreCase("toggledog")) {

            if(!(sender.isOp())) {

                sender.sendMessage(ChatColor.RED + "You are not operator!");
                return true;

            }

            if(IdentityUtility.dogOwnerExists((Player)sender)) {

                Wolf w = (Wolf) IdentityUtility.getDogEntityByPlayer((Player)sender);

                if (w == null) {

                    sender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "Your Dog Could Not Be Found (Is It In A Different Dimension?)");
                    return true;

                }

                DataPlayer p = PlayerList.players.get(((Player)sender).getUniqueId());

                if(p.dogEntity != null) {

                    p.showStats = false;

                    p.getDogEntity().remove();
                    p.setDogEntity(null);

                    DogList.remove(p.dogUUID);

                    p.dogUUID = null;

                    sender.sendMessage(ChatColor.GREEN + "Your dog has been disabled.");

                } else {

                    p.spawnPersonalDog();
                    sender.sendMessage(ChatColor.GREEN + "Your dog has been enabled.");
                    return true;

                }

            } else {

                DataPlayer p = PlayerList.players.get(((Player)sender).getUniqueId());

                p.spawnPersonalDog();
                sender.sendMessage(ChatColor.GREEN + "Your dog has been enabled.");
                return true;

            }

            return true;

        }

        return false;

    }

}
