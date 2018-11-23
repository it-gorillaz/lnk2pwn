package com.itgorillaz.lnk2pwn.view.core;

import java.awt.Window;

import javax.swing.JFrame;

public interface WindowController {
    
    public void show(JFrame frame, BoundsPolicy policy);
        
    public Window getActiveWindow();
    
    public JFrame getRootFrame();

}
