package com.itgorillaz.lnk2pwn.view.action;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alee.extended.filechooser.WebDirectoryChooser;
import com.alee.laf.optionpane.WebOptionPane;
import com.alee.utils.swing.DialogOptions;
import com.itgorillaz.lnk2pwn.controller.ShortcutController;
import com.itgorillaz.lnk2pwn.model.Shortcut;
import com.itgorillaz.lnk2pwn.view.core.WindowController;

@Component
public class GenerateShortcutAction implements ActionListener {
    
    private static final Logger LOGGER = LogManager.getLogger(GenerateShortcutAction.class);
    
    @Autowired
    private Shortcut shortcut;
    
    @Autowired
    private ShortcutController controller;
    
    @Autowired
    private WindowController windowController;
    
    private WebDirectoryChooser directoryChooser;
    
    @Override
    public void actionPerformed(ActionEvent event) {
        LOGGER.info("Generate button clicked");
        
        if (directoryChooser == null) {
            directoryChooser = new WebDirectoryChooser(windowController.getRootFrame());
        }
            
        LOGGER.info("Openning the directory chooser");
        directoryChooser.setVisible(true);
        if (directoryChooser.getResult () == DialogOptions.CANCEL_OPTION){
            LOGGER.info("No output path selected, canceling the action");
            return;
        }
                
        File outputPath = directoryChooser.getSelectedDirectory();		
        shortcut.setOutputPath(outputPath.getAbsolutePath());
                    
        try {
            
            controller.generateShortcut(shortcut);
            int result = WebOptionPane.showConfirmDialog(
                    windowController.getRootFrame(), 
                    "Shortcut successfully generated!\nWould you like to open the folder location?", 
                    "Shortcut", 
                    WebOptionPane.YES_NO_OPTION,
                    WebOptionPane.INFORMATION_MESSAGE);
            
            if (WebOptionPane.YES_OPTION == result) {
                LOGGER.info("Openning the output path");
                Desktop.getDesktop().open(outputPath);
            }
            
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

}
