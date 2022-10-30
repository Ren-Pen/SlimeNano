package com.slimenano.utils;

import java.io.File;

public class Directorys {

    public static final File pluginDir = new File("plugins");

    public static void createDirectorys(){
        pluginDir.mkdirs();
    }

}
