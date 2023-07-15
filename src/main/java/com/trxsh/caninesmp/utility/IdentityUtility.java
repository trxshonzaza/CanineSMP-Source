package com.trxsh.caninesmp.utility;

import com.trxsh.caninesmp.data.DogList;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.UUID;

public class IdentityUtility {

    public static Player identifyDogOwner(UUID id) {

        for(UUID id1 : DogList.dogList.keySet())
            if(id.equals(id1))
                return DogList.dogList.get(id1);

        return null;

    }

    public static UUID getDogByPlayer(Player player) {

        for(UUID id : DogList.dogList.keySet()) {

            UUID playerID = DogList.dogList.get(id).getUniqueId();

            if(playerID.equals(player.getUniqueId()))
                return id;

        }

        return null;

    }

    public static LivingEntity getDogEntityByPlayer(Player player) {

        UUID id = null;

        for(UUID id1 : DogList.dogList.keySet())
            if(DogList.dogList.get(id1).getUniqueId().equals(player.getUniqueId()))
                id = id1;

        if(id == null)
            return null;

        return (LivingEntity) Bukkit.getEntity(id);

    }

    public static boolean dogExists(UUID id) {

        return DogList.dogList.containsKey(id);

    }

    public static boolean dogOwnerExists(Player player) {

        for(Player p : DogList.dogList.values())
            if(p.getUniqueId().equals(player.getUniqueId()))
                return true;

        return false;

    }

}
