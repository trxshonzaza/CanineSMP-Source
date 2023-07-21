package com.trxsh.caninesmp.command;

import com.trxsh.caninesmp.utility.IdentityUtility;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;

import java.util.UUID;

public class DogLocation implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!(sender instanceof Player)) {

            sender.sendMessage(ChatColor.RED + "Only A Valid Player Can Run This Command!");
            return true;

        }

        if(command.getName().equalsIgnoreCase("locatedog")) {

            Player p = (Player) sender;

            if(IdentityUtility.dogOwnerExists(p)) {

                Wolf w = (Wolf) IdentityUtility.getDogEntityByPlayer(p);

                if(w == null) {

                    sender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "Your Dog Could Not Be Found (Is It In A Different Dimension?)");
                    return true;

                }

                Location l = w.getLocation();

                World.Environment et = w.getWorld().getEnvironment();

                String dimension = "";

                if(et == World.Environment.NORMAL)
                    dimension = "Overworld";
                else if(et == World.Environment.NETHER)
                    dimension = "Nether";
                else if(et == World.Environment.THE_END)
                    dimension = "The End";
                else
                    dimension = "Undefined Dimension";

                p.sendMessage(ChatColor.GREEN + "Your Dog Is Located At " + (int)l.getX() + ", " + (int)l.getY() + ", " + (int)l.getZ() + " And Is In '" + dimension + "'.");
                return true;

            } else {

                sender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "You Do Not Own A Dog Or It Is Not Registered! (re-login to fix.)");
                return true;

            }

        }

        return false;

    }

}
