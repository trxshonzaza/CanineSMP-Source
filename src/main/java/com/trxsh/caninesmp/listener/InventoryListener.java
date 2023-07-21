package com.trxsh.caninesmp.listener;

import com.trxsh.caninesmp.utility.InventoryUtility;
import com.trxsh.caninesmp.utility.ItemUtility;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class InventoryListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e) {

        if(e.getView().getTitle() != "Revive Menu")
            return;

        e.setCancelled(true);

        if(e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR)
            return;

        if(!(e.getWhoClicked() instanceof Player))
            return;

        Player p = (Player) e.getWhoClicked();

        boolean u = ItemUtility.unbanByItem(e.getCurrentItem());

        if(u) {

            p.closeInventory();

            p.sendMessage(ChatColor.GREEN + "Successfully Unbanned!");
            p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1f, 1f);

            for(ItemStack stack : p.getInventory()) {

                if(stack.isSimilar(ItemUtility.getReviveItem())) {

                    if(stack.getAmount() == 1)
                        stack.setAmount(0);
                    else
                        stack.setAmount(stack.getAmount() - 1);

                    break;

                }

            }

        } else {

            p.closeInventory();
            p.sendMessage(ChatColor.RED + "Failed To Unban. Please Try Again. If The Problem Persists, Please Contact Developer.");

        }

    }

}
