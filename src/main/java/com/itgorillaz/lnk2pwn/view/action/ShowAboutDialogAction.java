package com.itgorillaz.lnk2pwn.view.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itgorillaz.lnk2pwn.view.AboutDialog;
import com.itgorillaz.lnk2pwn.view.core.WindowController;

@Component
public class ShowAboutDialogAction implements ActionListener {

    private static final Logger LOGGER = LogManager.getLogger(ShowAboutDialogAction.class);
    
    @Autowired
    private WindowController windowController;
    
    @Autowired
    private AboutDialog aboutDialog;
    
    @Override
    public void actionPerformed(ActionEvent event) {
        LOGGER.info("About button clicked");
        LOGGER.info("Openning the about modal dialog");
        aboutDialog.setLocationRelativeTo(windowController.getRootFrame());
        aboutDialog.setVisible(true);		
    }

}
