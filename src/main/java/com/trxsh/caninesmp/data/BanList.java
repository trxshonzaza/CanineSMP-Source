package com.trxsh.caninesmp.data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BanList {

    public static List<UUID> banned = new ArrayList<>();

    public static void add(UUID id) {

        banned.add(id);

    }

    public static void remove(UUID id) {

        banned.remove(id);

    }

    public static boolean contains(UUID id) {

        return banned.contains(id);

    }

}
