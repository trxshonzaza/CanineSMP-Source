package com.trxsh.caninesmp.utility;

import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import static com.trxsh.caninesmp.data.BanList.banned;

import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;
import java.util.UUID;

public class ItemUtility {

    public static ItemStack getReviveItem() {

        ItemStack stack = new ItemStack(Material.GHAST_TEAR);

        ItemMeta meta = stack.getItemMeta();

        assert meta != null;
        meta.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "Revive Token");
        meta.setLore(Collections.singletonList(ChatColor.RED + "Used To Revive Players."));
        meta.addEnchant(Enchantment.DURABILITY, 5, true);

        stack.setItemMeta(meta);

        return stack;

    }

    public static ItemStack getReviveSkull(BanEntry entry) {

        ItemStack skull = new ItemStack(Material.SKELETON_SKULL);

        SkullMeta meta = (SkullMeta) skull.getItemMeta();

        assert meta != null;
        meta.setDisplayName(entry.getTarget());
        meta.setLore(Arrays.asList("Banned on " + entry.getCreated(), ChatColor.GREEN + "Click To Revive!"));

        skull.setItemMeta(meta);

        return skull;

    }

    public static boolean unbanByItem(ItemStack stack) {

        if(Bukkit.getBanList(BanList.Type.NAME).isBanned(Objects.requireNonNull(stack.getItemMeta()).getDisplayName())) {

            Bukkit.getBanList(BanList.Type.NAME).pardon(Objects.requireNonNull(stack.getItemMeta()).getDisplayName());

            banned.removeIf(id -> Objects.requireNonNull(Bukkit.getOfflinePlayer(id).getName()).equalsIgnoreCase(Objects.requireNonNull(stack.getItemMeta()).getDisplayName()));

            return true;

        }

        return false;

    }

}
