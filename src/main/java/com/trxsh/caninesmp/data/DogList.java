package com.trxsh.caninesmp.data;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class DogList {

    public static Map<UUID, Player> dogList = new HashMap();

    public static void add(UUID dogID, Player player) {

        dogList.put(dogID, player);

    }

    public static void remove(UUID dogID) {

        dogList.remove(dogID);

    }

    public static boolean exists(UUID dogID) {

        return dogList.containsKey(dogID);

    }

    public static boolean playerExists(UUID playerID) {

        for(Player player : dogList.values())
            if(player.getUniqueId().equals(playerID))
                return true;

        return false;

    }

    public static void replace(UUID dogID, Player player) {

        if(playerExists(player.getUniqueId())) {

            for(UUID id : dogList.keySet())
                if(player.getUniqueId().equals(dogList.get(id).getUniqueId()))
                    dogList.remove(id, dogList.get(id));

        }

        dogList.put(dogID, player);

    }

}
