package com.itgorillaz.lnk2pwn.config;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.itgorillaz.lnk2pwn.view.form.FormPanel;

@Configuration
public class UACBypassConfig {

    private static final Logger LOGGER = LogManager.getLogger(UACBypassConfig.class);
    
    private final String LINE_SEPARATOR = System.lineSeparator();
    private final String VBS_SOURCE_CODE_PATH = "vbs/uac_bypass.vbs";
    
    @Bean(name="VBSSourceCode")
    public String getVBSSourceCode() {
        LOGGER.info("Loading VBS(UAC Bypass) source code");
        
        InputStream in = FormPanel.class.getClassLoader()
                .getResourceAsStream(VBS_SOURCE_CODE_PATH);
        
        return new BufferedReader(new InputStreamReader(in))
                  .lines()
                  .collect(Collectors.joining(LINE_SEPARATOR));
    }
    
}
