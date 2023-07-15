package com.trxsh.caninesmp.data.file;

import java.io.File;
import java.io.IOException;

public abstract class FileManager {

    public File file;

    public FileManager(File file) {

        this.file = file;

    }

    public abstract void save() throws IOException;

    public abstract void load() throws IOException;

    public boolean exists() {

        return file.exists();

    }

}
