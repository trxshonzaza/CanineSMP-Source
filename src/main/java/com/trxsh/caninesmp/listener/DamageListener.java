package com.trxsh.caninesmp.listener;

import com.trxsh.caninesmp.Main;
import com.trxsh.caninesmp.utility.IdentityUtility;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class DamageListener implements Listener {

    @EventHandler
    public void onDamage(EntityDamageEvent e) {

        if(e.getEntity() instanceof Wolf) {

            if(!Main.Instance.attackDogs) {

                e.setCancelled(true);
                return;

            }

            Wolf w = (Wolf) e.getEntity();

            if(IdentityUtility.dogExists(w.getUniqueId()))  {

                Player p = IdentityUtility.identifyDogOwner(w.getUniqueId());

                if(p == null)
                    return;

                if(e.getCause() == EntityDamageEvent.DamageCause.SUFFOCATION) {

                    int range = 10;
                    e.setCancelled(true);

                    for (int x = -range; x <= range; x++) {

                        for (int y = -range; y <= range; y++) {

                            for (int z = -range; z <= range; z++) {

                                Location blockLocation = w.getLocation().clone().add(x, y, z);

                                if (blockLocation.getBlock().getType() == Material.AIR) {

                                    p.sendMessage(ChatColor.RED + "Your Dog Was Suffocating And It Was Moved To A More Suitable Location. Please Use /locatedog Or /dogstats To Find Your Dog's New Location.");
                                    w.teleport(blockLocation);

                                    return;

                                }

                            }

                        }

                    }

                    return;

                }

                if(e.getCause() != EntityDamageEvent.DamageCause.ENTITY_ATTACK) {

                    return;

                }

                p.damage(e.getDamage());
                p.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "Your Dog Took Damage!");

            }

        }

    }

}
