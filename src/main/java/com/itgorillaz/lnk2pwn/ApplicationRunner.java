package com.itgorillaz.lnk2pwn;

import javax.swing.SwingUtilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.alee.laf.WebLookAndFeel;
import com.itgorillaz.lnk2pwn.view.Lnk2PwnFrame;
import com.itgorillaz.lnk2pwn.view.core.BoundsPolicy;
import com.itgorillaz.lnk2pwn.view.core.WindowController;

@Component
public class ApplicationRunner implements CommandLineRunner {

    private static final Logger LOGGER = LogManager.getLogger(ApplicationRunner.class);
    
    @Autowired
    private WindowController windowController;
    
    @Autowired
    private Lnk2PwnFrame frame;
    
    @Override
    public void run(String... args) throws Exception {
        LOGGER.info("Installing Web Look And Feel");
        WebLookAndFeel.install();
        
        SwingUtilities.invokeLater(new Runnable() {			
            @Override
            public void run() {
                LOGGER.info("Running the main frame");
                windowController.show(frame, BoundsPolicy.MAXIMIZE);
            }
        });	
    }

}
