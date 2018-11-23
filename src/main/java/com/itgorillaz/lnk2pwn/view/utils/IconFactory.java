package com.itgorillaz.lnk2pwn.view.utils;

import java.net.URL;

import javax.swing.ImageIcon;

public class IconFactory {

    private static final String ICON_PATH = "icons/";
    
    public static ImageIcon getIcon(String name) {
        ClassLoader loader = IconFactory.class.getClassLoader();
        URL url = loader.getResource(ICON_PATH + name);        
        return new ImageIcon(url);
    }
}
