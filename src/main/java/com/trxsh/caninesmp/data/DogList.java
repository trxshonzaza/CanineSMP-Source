package com.trxsh.caninesmp.data;

import com.trxsh.caninesmp.player.DataPlayer;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class DogList {

    public static Map<UUID, UUID> dogList = new HashMap();

    public static void add(UUID dogID, UUID playerID) {

        dogList.put(dogID, playerID);

    }

    public static void remove(UUID dogID) {

        dogList.remove(dogID);

    }

    public static boolean exists(UUID dogID) {

        return dogList.containsKey(dogID);

    }

    public static boolean playerExists(UUID playerID) {

        for(UUID id : dogList.values())
            if(id.equals(playerID))
                return true;

        return false;

    }

    public static void replace(UUID dogID, UUID playerID) {

        if(playerExists(playerID)) {

            for(UUID id : dogList.keySet())
                if(playerID.equals(dogList.get(id)))
                    dogList.remove(id, dogList.get(id));

        }

        dogList.put(dogID, playerID);

    }

}
