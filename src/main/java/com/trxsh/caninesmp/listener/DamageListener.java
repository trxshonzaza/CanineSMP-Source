package com.trxsh.caninesmp.listener;

import com.trxsh.caninesmp.utility.IdentityUtility;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class DamageListener implements Listener {

    @EventHandler
    public void onDamage(EntityDamageEvent e) {

        if(e.getEntity() instanceof Wolf) {

            Wolf w = (Wolf) e.getEntity();

            if(IdentityUtility.dogExists(w.getUniqueId()))  {

                Player p = IdentityUtility.identifyDogOwner(w.getUniqueId());

                if(p == null) {

                    Bukkit.broadcastMessage("player not identified");
                    return;

                }

                p.damage(e.getDamage());
                p.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "Your Dog Took Damage!");

            }

        }

    }

}
