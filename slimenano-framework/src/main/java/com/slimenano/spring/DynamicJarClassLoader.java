package com.slimenano.spring;

import java.io.File;
import java.io.IOException;
import java.net.*;


/**
 * @author xzy54
 */
public class DynamicJarClassLoader extends URLClassLoader {
    private File file;

    public File getFile() {
        return file;
    }

    public DynamicJarClassLoader(File libDir) {
        super(new URL[]{}, ClassLoader.getSystemClassLoader());
        try {
            URL element = libDir.toURI().normalize().toURL();
            addURL(element);
            file = libDir;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

}
