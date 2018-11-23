package com.itgorillaz.lnk2pwn.view.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class ExitAction implements ActionListener {

    private static final Logger LOGGER = LogManager.getLogger(ExitAction.class);
    
    @Override
    public void actionPerformed(ActionEvent event) {
        LOGGER.info("Shutting down the application");
        System.exit(0);
    }

}
