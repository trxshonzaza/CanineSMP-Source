package com.trxsh.caninesmp.listener;

import com.trxsh.caninesmp.data.DogList;
import com.trxsh.caninesmp.data.PlayerList;
import com.trxsh.caninesmp.utility.BanUtility;
import com.trxsh.caninesmp.utility.IdentityUtility;
import com.trxsh.caninesmp.utility.ItemUtility;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class DeathListener implements Listener {

    @EventHandler
    public void onEntityDeath(EntityDeathEvent e) {

        if(e.getEntity() instanceof Wolf) {

            Wolf w = (Wolf) e.getEntity();

            if(IdentityUtility.dogExists(w.getUniqueId())) {

                Player p = IdentityUtility.identifyDogOwner(w.getUniqueId());

                DogList.remove(w.getUniqueId());

                w.getWorld().dropItem(w.getLocation(), ItemUtility.getReviveItem());


                assert p != null;
                BanUtility.ban(p.getUniqueId());

                Bukkit.broadcastMessage(ChatColor.RED + "" + ChatColor.BOLD + p.getName() + "'s Dog Was Killed..");

                p.kickPlayer(BanUtility.getBanReason());

            }

        }

    }

}
