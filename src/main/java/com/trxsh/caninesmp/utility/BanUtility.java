package com.trxsh.caninesmp.utility;

import com.trxsh.caninesmp.data.BanList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.Objects;
import java.util.UUID;

public class BanUtility {

    public static void ban(UUID id) {

        BanList.add(id);

        OfflinePlayer player = Bukkit.getOfflinePlayer(id);

        Bukkit.getBanList(org.bukkit.BanList.Type.NAME).addBan(Objects.requireNonNull(player.getName()), getBanReason(), null, null);

    }

    public static void unban(UUID id) {

        BanList.remove(id);

        OfflinePlayer p = Bukkit.getOfflinePlayer(id);

        Bukkit.getBanList(org.bukkit.BanList.Type.NAME).pardon(Objects.requireNonNull(p.getName()));

    }

    public static String getBanReason() {

        return ChatColor.RED + "" + ChatColor.BOLD + "Your Dog Was Killed... \n" + ChatColor.GREEN + "Get A Friend To Revive You!";

    }

}
