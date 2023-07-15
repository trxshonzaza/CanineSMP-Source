package com.trxsh.caninesmp.data;

import com.trxsh.caninesmp.player.DataPlayer;
import org.bukkit.entity.Player;

import java.util.*;

public class PlayerList {

    public static Map<UUID, DataPlayer> players = new HashMap<UUID, DataPlayer>();

    public static void add(UUID id, DataPlayer player) {

        players.put(id, player);

    }

    public static void remove(UUID id) {

        players.remove(id);

    }

    public static boolean exists(UUID id) {

        return players.containsKey(id);

    }

}
