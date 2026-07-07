package com.schmoll.tlkw.utils;

import javax.swing.filechooser.FileFilter;
import java.io.File;

public class MyFileFilter extends FileFilter {



    String ends;
    String description;

    public MyFileFilter(String ends, String description) {

        this.ends = ends;

        this.description = description;

    }

    public boolean accept(File file) {

        String fileName = file.getName();

        if (file.getPath().toUpperCase().startsWith("".toUpperCase())){
            return true;
        }
        else if (file.getPath().toUpperCase().startsWith("/".toUpperCase()) & fileName.toUpperCase().equals("net1".toUpperCase())){
            return true;
        }
        else if (file.getPath().toUpperCase().startsWith("/".toUpperCase()) & fileName.toUpperCase().equals("mnt".toUpperCase())){
            return true;
        }
        else {
            return false;
        }

    }

    public String getDescription() {

        return description;

    }

}
