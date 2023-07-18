package com.trxsh.caninesmp.listener;

import com.trxsh.caninesmp.utility.InventoryUtility;
import com.trxsh.caninesmp.utility.ItemUtility;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class InteractListener implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {

        if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {

            ItemStack item = e.getItem();

            if(item != null)
                if(item.isSimilar(ItemUtility.getReviveItem()))
                    e.getPlayer().openInventory(InventoryUtility.openReviveInventory(e.getPlayer()));

        }

    }

}
