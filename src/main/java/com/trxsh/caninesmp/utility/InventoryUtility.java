package com.trxsh.caninesmp.utility;

import static com.trxsh.caninesmp.data.BanList.banned;

import org.bukkit.BanEntry;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.UUID;

public class InventoryUtility {

    public static Inventory current = null;

    public static Inventory openReviveInventory(Player player) {

        Inventory inventory = Bukkit.createInventory(null, 54, "Revive Menu");

        for(UUID id : banned) {

            BanEntry entry = BanUtility.getBanEntry(id);

            inventory.addItem(ItemUtility.getReviveSkull(entry));

        }

        current = inventory;

        return inventory;

    }

}
