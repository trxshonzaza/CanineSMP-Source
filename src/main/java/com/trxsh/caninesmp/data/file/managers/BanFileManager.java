package com.trxsh.caninesmp.data.file.managers;

import com.trxsh.caninesmp.data.BanList;
import com.trxsh.caninesmp.data.file.FileManager;
import com.trxsh.caninesmp.utility.NameUtility;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.UUID;

public class BanFileManager extends FileManager {

    public BanFileManager(File file) {
        super(file);
    }

    @Override
    public void save() throws IOException {

        FileWriter writer = new FileWriter(file);
        StringBuilder sb = new StringBuilder();

        for(UUID id : BanList.banned)
            sb.append(id.toString() + NameUtility.getSeparator());

        writer.write(sb.toString());

    }

    @Override
    public void load() throws IOException {

        for(String line : Files.readAllLines(file.toPath())) {

            UUID id = UUID.fromString(line);
            BanList.add(id);

        }

    }

}
